/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Produtos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Godoy
 */
public class ProdutosDao {

    private Connection con;

    public ProdutosDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir um novo produto.
     *
     * @param obj
     */
    public void inserir(Produtos obj) {

        try {

            String sql = "insert into tb_produtos (nome,tipo,ncm,cod_barras,quantidade,atualizacao,"
                    + "valor_compra,margem_lucro,valor_venda,desconto_vista,"
                    + "valor_vista,observacoes) values (?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTipo());
            stmt.setString(3, obj.getNcm());
            stmt.setString(4, obj.getCod_barras());
            stmt.setInt(5, obj.getQuantidade());
            stmt.setString(6, obj.getAtualizacao());
            stmt.setDouble(7, obj.getValor_compra());
            stmt.setDouble(8, obj.getMargem_lucro());
            stmt.setDouble(9, obj.getValor_venda());
            stmt.setDouble(10, obj.getDesconto_vista());
            stmt.setDouble(11, obj.getValor_vista());
            stmt.setString(12, obj.getObservacoes());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Arquivo cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para editar um produto.
     *
     * @param obj
     */
    public void editar(Produtos obj) {

        try {

            String sql = "update tb_produtos set nome=?,tipo=?,ncm=?,cod_barras=?,quantidade=?,atualizacao=?,"
                    + "valor_compra=?,margem_lucro=?,valor_venda=?,desconto_vista=?,valor_vista=?,observacoes=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTipo());
            stmt.setString(3, obj.getNcm());
            stmt.setString(4, obj.getCod_barras());
            stmt.setInt(5, obj.getQuantidade());
            stmt.setString(6, obj.getAtualizacao());
            stmt.setDouble(7, obj.getValor_compra());
            stmt.setDouble(8, obj.getMargem_lucro());
            stmt.setDouble(9, obj.getValor_venda());
            stmt.setDouble(10, obj.getDesconto_vista());
            stmt.setDouble(11, obj.getValor_vista());
            stmt.setString(12, obj.getObservacoes());
            stmt.setInt(13, obj.getId());
            stmt.execute();
            stmt.close();
            JOptionPane.showMessageDialog(null, "Arquivo alterado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para editar apenas os valores do produto, quando ao inserir a
     * compra do produto, escolher a opção sim, para atualizar o preço direto na
     * hora da compra.
     *
     * @param obj
     */
    public void editarValores(Produtos obj) {

        try {

            String sql = "update tb_produtos set valor_compra=?,valor_venda=?,valor_vista=?,atualizacao=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setDouble(1, obj.getValor_compra());
            stmt.setDouble(2, obj.getValor_venda());
            stmt.setDouble(3, obj.getValor_vista());
            stmt.setString(4, obj.getAtualizacao());
            stmt.setInt(5, obj.getId());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir um produto.
     *
     * @param id
     */
    public void excluir(int id) {

        try {

            String sql = "delete from tb_produtos where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo excluído com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para retornar a lista de todos os produtos.
     *
     * @return
     */
    public List<Produtos> listarProdutos() {

        try {

            List<Produtos> lista = new ArrayList<>();

            String sql = "select * from tb_produtos order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTipo(rs.getString("tipo"));
                obj.setNcm(rs.getString("ncm"));
                obj.setCod_barras(rs.getString("cod_barras"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setAtualizacao(rs.getString("atualizacao"));
                obj.setValor_compra(rs.getDouble("valor_compra"));
                obj.setMargem_lucro(rs.getDouble("margem_lucro"));
                obj.setValor_venda(rs.getDouble("valor_venda"));
                obj.setDesconto_vista(rs.getDouble("desconto_vista"));
                obj.setValor_vista(rs.getDouble("valor_vista"));
                obj.setObservacoes(rs.getString("observacoes"));
                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para retornar a lista de produtos, com base no nome buscado.
     *
     * @param nome
     * @return
     */
    public List<Produtos> consultarPorNome(String nome) {

        try {

            List<Produtos> lista = new ArrayList<>();

            String sql = "select id,nome,quantidade,valor_compra,valor_venda,valor_vista from tb_produtos where nome like ? "
                    + "order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Produtos obj = new Produtos();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setValor_compra(rs.getDouble("valor_compra"));
                obj.setValor_venda(rs.getDouble("valor_venda"));
                obj.setValor_vista(rs.getDouble("valor_vista"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para retornar os dados de um produto, ao buscar pelo ID.
     *
     * @param id
     * @return
     */
    public Produtos consultarPorId(int id) {

        try {

            String sql = "select * from tb_produtos where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Produtos obj = new Produtos();
            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTipo(rs.getString("tipo"));
                obj.setNcm(rs.getString("ncm"));
                obj.setCod_barras(rs.getString("cod_barras"));
                obj.setQuantidade(rs.getInt("quantidade"));
                obj.setAtualizacao(rs.getString("atualizacao"));
                obj.setValor_compra(rs.getDouble("valor_compra"));
                obj.setMargem_lucro(rs.getDouble("margem_lucro"));
                obj.setValor_venda(rs.getDouble("valor_venda"));
                obj.setDesconto_vista(rs.getDouble("desconto_vista"));
                obj.setValor_vista(rs.getDouble("valor_vista"));
                obj.setObservacoes(rs.getString("observacoes"));
            }
            stmt.close();
            return obj;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para retornar o id do último cadastro.
     *
     * @return
     */
    public int ultimoCadastro() {
        try {
            int idCadastro = 0;

            String sql = "select max(id)id from tb_produtos";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Produtos obj = new Produtos();
                obj.setId(rs.getInt("id"));
                idCadastro = obj.getId();
            }
            stmt.close();
            return idCadastro;

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

    /**
     * Método para retornar a quantidade em estoque do produto.
     *
     * @param id
     * @return
     */
    public int retornaEstoque(int id) {
        try {

            String sql = "select quantidade from tb_produtos where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);

            ResultSet rs = stmt.executeQuery();

            int estoque = 0;
            if (rs.next()) {
                estoque = rs.getInt("quantidade");
            }
            stmt.close();
            return estoque;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para atualizar o estoque do produto, ao realizar uma compra ou
     * venda do mesmo.
     *
     * @param id
     * @param qtd_nova
     */
    public void AtualizaEstoque(int id, int qtd_nova) {
        try {

            String sql = "update tb_produtos set quantidade=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, qtd_nova);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }
}
