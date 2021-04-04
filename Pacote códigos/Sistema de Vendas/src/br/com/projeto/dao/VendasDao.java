/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.Planos;
import br.com.projeto.model.Situacao;
import br.com.projeto.model.Vendas;
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
public class VendasDao {

    private Connection con;

    public VendasDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para criar um insert simples, pq é preciso colocar no botão novo
     * pra gerar o número correto da requisição.
     *
     * @param obj
     */
    public void inserir(Vendas obj) {
        try {

            String sql = "insert into tb_vendas (observacoes) values (?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getObservacoes());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para editar uma venda feita.
     *
     * @param obj
     */
    public void editar(Vendas obj) {
        try {

            String sql = "update tb_vendas set id_situacao=?,emissao=?,parcelas=?,"
                    + "id_cliente=?,total=?,observacoes=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getSituacao().getId());
            stmt.setString(2, obj.getEmissao());
            stmt.setInt(3, obj.getParcelas());
            stmt.setInt(4, obj.getCliente().getId());
            stmt.setDouble(5, obj.getTotal_vendas());
            stmt.setString(6, obj.getObservacoes());
            stmt.setInt(7, obj.getId());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir uma venda feita.
     *
     * @param id
     */
    public void excluir(int id) {
        try {

            String sql = "delete from tb_vendas where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para listar todas as vendas feitas.
     *
     * @return
     */
    public List<Vendas> listarVendas() {

        try {

            List<Vendas> lista = new ArrayList<>();

            String sql = "select v.id,v.id_cliente,c.nome,v.emissao,v.total "
                    + "from tb_vendas as v inner join tb_clientes as c "
                    + "on (v.id_cliente = c.id) order by v.id";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                obj.setId(rs.getInt("v.id"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("v.id_cliente"));
                objCli.setNome(rs.getString("c.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("v.emissao"));

                obj.setTotal_vendas(rs.getDouble("v.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas, com base no cliente buscado.
     *
     * @param nome
     * @return
     */
    public List<Vendas> listarPorNome(String nome) {
        try {

            List<Vendas> lista = new ArrayList<>();

            String sql = "select v.id,v.id_cliente,c.nome,v.emissao,v.total "
                    + "from tb_vendas as v inner join tb_clientes as c "
                    + "on (v.id_cliente = c.id) where c.nome like ? order by v.id ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Vendas obj = new Vendas();
                obj.setId(rs.getInt("v.id"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("v.id_cliente"));
                objCli.setNome(rs.getString("c.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("v.emissao"));

                obj.setTotal_vendas(rs.getDouble("v.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas, sem especificar o cliente, com
     * base na data de emissão da venda.
     *
     * @param inicio
     * @param fim
     * @return
     */
    public List<Vendas> filtrarPorEmissaoTudo(String inicio, String fim) {
        try {
            List<Vendas> lista = new ArrayList<>();

            String sql = "select v.id, v.id_cliente, c.nome, v.emissao, v.total from tb_vendas as v "
                    + "inner join tb_clientes as c on (v.id_cliente = c.id) where v.emissao between ? and ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, inicio);
            stmt.setString(2, fim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Vendas obj = new Vendas();

                obj.setId(rs.getInt("v.id"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("v.id_cliente"));
                objCli.setNome(rs.getString("c.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("v.emissao"));
                obj.setTotal_vendas(rs.getDouble("v.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas, com base no cliente e na data
     * de emissão da venda.
     *
     * @param id_cliente
     * @param inicio
     * @param fim
     * @return
     */
    public List<Vendas> filtrarPorEmissaoPorCliente(int id_cliente, String inicio, String fim) {
        try {
            List<Vendas> lista = new ArrayList<>();

            String sql = "select v.id, v.id_cliente, c.nome, v.emissao, v.total from tb_vendas as v "
                    + "inner join tb_clientes as c on (v.id_cliente = c.id) where v.id_cliente = ? and v.emissao between ? and ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            stmt.setString(2, inicio);
            stmt.setString(3, fim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Vendas obj = new Vendas();

                obj.setId(rs.getInt("v.id"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("v.id_cliente"));
                objCli.setNome(rs.getString("c.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("v.emissao"));
                obj.setTotal_vendas(rs.getDouble("v.total"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para buscar uma venda especifica, com base no id da venda.
     *
     * @param id
     * @return
     */
    public Vendas consultarPorId(int id) {
        try {

            String sql = "select v.id,v.id_situacao,s.nome,v.emissao,v.parcelas,v.id_cliente,c.nome,v.total,"
                    + "v.observacoes from tb_vendas as v inner join tb_situacao as s inner join tb_clientes as c "
                    + "on (v.id_situacao = s.id) and (v.id_cliente = c.id) where v.id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Vendas obj = new Vendas();
            if (rs.next()) {

                obj.setId(rs.getInt("v.id"));

                Situacao objSit = new Situacao();
                objSit.setId(rs.getInt("v.id_situacao"));
                objSit.setNome(rs.getString("s.nome"));
                obj.setSituacao(objSit);

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("v.id_cliente"));
                objCli.setNome(rs.getString("c.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("v.emissao"));
                obj.setParcelas(rs.getInt("v.parcelas"));
                obj.setTotal_vendas(rs.getDouble("v.total"));
                obj.setObservacoes(rs.getString("v.observacoes"));
            }
            stmt.close();
            return obj;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para retornar o id da última venda, servirá para gerar o id da
     * venda seguinte.
     *
     * @return
     */
    public int ultimoCadastro() {
        try {
            int idCadastro = 0;

            String sql = "select max(id)id from tb_vendas";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Vendas obj = new Vendas();
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
