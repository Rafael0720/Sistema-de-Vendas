/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Compras;
import br.com.projeto.model.Fornecedores;
import br.com.projeto.model.Planos;
import br.com.projeto.model.Situacao;
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
public class ComprasDao {

    private Connection con;

    public ComprasDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para criar um insert simples, pq é preciso colocar no botão novo
     * pra gerar o número correto da requisição.
     *
     * @param obj
     */
    public void inserir(Compras obj) {
        try {

            String sql = "insert into tb_compras (observacoes) values (?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getObservacoes());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para editar uma compra feita.
     *
     * @param obj
     */
    public void editar(Compras obj) {
        try {

            String sql = "update tb_compras set id_situacao=?,emissao=?,parcelas=?,"
                    + "id_fornecedor=?,total=?,observacoes=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getSituacao().getId());
            stmt.setString(2, obj.getEmissao());
            stmt.setInt(3, obj.getParcelas());
            stmt.setInt(4, obj.getFornecedor().getId());
            stmt.setDouble(5, obj.getTotal_compras());
            stmt.setString(6, obj.getObservacoes());
            stmt.setInt(7, obj.getId());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir uma compra feita.
     *
     * @param id
     */
    public void excluir(int id) {
        try {

            String sql = "delete from tb_compras where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para listar todas as compras feitas.
     *
     * @return
     */
    public List<Compras> listarVendas() {

        try {

            List<Compras> lista = new ArrayList<>();

            String sql = "select c.id,c.id_fornecedor,f.nome,c.emissao,c.total "
                    + "from tb_compras as c inner join tb_fornecedores as f "
                    + "on (c.id_fornecedor = f.id) order by c.id";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Compras obj = new Compras();
                obj.setId(rs.getInt("c.id"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("c.id_fornecedor"));
                objFor.setNome(rs.getString("f.nome"));
                obj.setFornecedor(objFor);

                obj.setEmissao(rs.getString("c.emissao"));

                obj.setTotal_compras(rs.getDouble("c.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as compras feitas, com base no fornecedor
     * buscado.
     *
     * @param nome
     * @return
     */
    public List<Compras> listarPorNome(String nome) {
        try {

            List<Compras> lista = new ArrayList<>();

            String sql = "select c.id,c.id_fornecedor,f.nome,c.emissao,c.total "
                    + "from tb_compras as c inner join tb_fornecedores as f "
                    + "on (c.id_fornecedor = f.id) where f.nome like ? order by c.id ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Compras obj = new Compras();
                obj.setId(rs.getInt("c.id"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("c.id_fornecedor"));
                objFor.setNome(rs.getString("f.nome"));
                obj.setFornecedor(objFor);

                obj.setEmissao(rs.getString("c.emissao"));

                obj.setTotal_compras(rs.getDouble("c.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as compras feitas, sem especificar o fornecedor,
     * com base na data de emissão da compra.
     *
     * @param inicio
     * @param fim
     * @return
     */
    public List<Compras> filtrarPorEmissaoTudo(String inicio, String fim) {
        try {
            List<Compras> lista = new ArrayList<>();

            String sql = "select c.id, c.id_fornecedor, f.nome, c.emissao, c.total from tb_compras as c "
                    + "inner join tb_fornecedores as f on (c.id_fornecedor = f.id) where c.emissao between ? and ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Compras obj = new Compras();

                obj.setId(rs.getInt("c.id"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("c.id_fornecedor"));
                objFor.setNome(rs.getString("f.nome"));
                obj.setFornecedor(objFor);

                obj.setEmissao(rs.getString("c.emissao"));
                obj.setTotal_compras(rs.getDouble("c.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as compras feitas, com base no fornecedor e na
     * data de emissão da compra.
     *
     * @param id_fornecedor
     * @param inicio
     * @param fim
     * @return
     */
    public List<Compras> filtrarPorEmissaoPorFornecedor(int id_fornecedor, String inicio, String fim) {
        try {
            List<Compras> lista = new ArrayList<>();

            String sql = "select c.id, c.id_fornecedor, f.nome, c.emissao, c.total from tb_compras as c "
                    + "inner join tb_fornecedores as f on (c.id_fornecedor = f.id) where c.id_fornecedor = ? and c.emissao between ? and ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_fornecedor);
            stmt.setString(2, inicio);
            stmt.setString(3, fim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Compras obj = new Compras();

                obj.setId(rs.getInt("c.id"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("c.id_fornecedor"));
                objFor.setNome(rs.getString("f.nome"));
                obj.setFornecedor(objFor);

                obj.setEmissao(rs.getString("c.emissao"));
                obj.setTotal_compras(rs.getDouble("c.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para buscar uma compra especifica, com base no id da compra.
     *
     * @param id
     * @return
     */
    public Compras consultarPorId(int id) {
        try {

            String sql = "select c.id,c.id_situacao,s.nome,c.emissao,c.parcelas,c.id_fornecedor,f.nome,c.total,"
                    + "c.observacoes from tb_compras as c inner join tb_situacao as s inner join tb_fornecedores as f "
                    + "on (c.id_situacao = s.id) and (c.id_fornecedor = f.id) where c.id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Compras obj = new Compras();
            if (rs.next()) {

                obj.setId(rs.getInt("c.id"));

                Situacao objSit = new Situacao();
                objSit.setId(rs.getInt("c.id_situacao"));
                objSit.setNome(rs.getString("s.nome"));
                obj.setSituacao(objSit);

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("c.id_fornecedor"));
                objFor.setNome(rs.getString("f.nome"));
                obj.setFornecedor(objFor);

                obj.setEmissao(rs.getString("c.emissao"));
                obj.setParcelas(rs.getInt("c.parcelas"));
                obj.setTotal_compras(rs.getDouble("c.total"));
                obj.setObservacoes(rs.getString("c.observacoes"));
            }
            stmt.close();
            return obj;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para retornar o id da última compra, servirá para gerar o id da
     * compra seguinte.
     *
     * @return
     */
    public int ultimoCadastro() {
        try {
            int idCadastro = 0;

            String sql = "select max(id)id from tb_compras";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Compras obj = new Compras();
                obj.setId(rs.getInt("id"));
                idCadastro = obj.getId();
            }
            stmt.close();
            return idCadastro;

        } catch (Exception e) {
            throw new RuntimeException(e);

        }
    }

}
