/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Planos;
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
public class PlanosDao {

    private Connection con;

    public PlanosDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir um novo plano.
     *
     * @param obj
     */
    public void inserir(Planos obj) {
        try {

            String sql = "insert into tb_planos (nome,tipo,porcentagem,observacoes) values (?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTipo());
            stmt.setDouble(3, obj.getPorcentagem());
            stmt.setString(4, obj.getObservacoes());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo cadastrado com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para editar um plano.
     *
     * @param obj
     */
    public void editar(Planos obj) {
        try {

            String sql = "update tb_planos set nome=?,tipo=?,porcentagem=?,observacoes=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getTipo());
            stmt.setDouble(3, obj.getPorcentagem());
            stmt.setString(4, obj.getObservacoes());
            stmt.setInt(5, obj.getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo alterado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir um plano.
     *
     * @param id
     */
    public void excluir(int id) {
        try {

            String sql = "delete from tb_planos where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo excluído com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para retornar a lista de todos os planos.
     *
     * @return
     */
    public List<Planos> listarPlanos() {

        try {

            List<Planos> lista = new ArrayList<>();

            String sql = "select * from tb_planos order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Planos obj = new Planos();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTipo(rs.getString("tipo"));
                obj.setPorcentagem(rs.getDouble("porcentagem"));
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
     * Método para retornar a lista de planos, com base no nome buscado.
     *
     * @param nome
     * @return
     */
    public List<Planos> consultarPorNome(String nome) {
        try {
            List<Planos> lista = new ArrayList<>();

            String sql = "select * from tb_planos where nome like ? order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Planos obj = new Planos();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTipo(rs.getString("tipo"));
                obj.setPorcentagem(rs.getDouble("porcentagem"));
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
     * Método para retornar os dados de um plano, ao buscar pelo ID.
     *
     * @param id
     * @return
     */
    public Planos consultarPorId(int id) {
        try {

            String sql = "select * from tb_planos where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Planos obj = new Planos();
            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setTipo(rs.getString("tipo"));
                obj.setPorcentagem(rs.getDouble("porcentagem"));
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

            String sql = "select max(id)id from tb_planos";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Planos obj = new Planos();
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
     * Método para retornar taxa de porcentagem do plano, com base no id.
     *
     * @param id
     * @return
     */
    public double retornaTaxa(int id) {
        try {

            String sql = "select porcentagem from tb_planos where id=? ";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            double taxa = 0;
            if (rs.next()) {
                taxa = rs.getDouble("porcentagem");
            }
            stmt.close();
            return taxa;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }
}
