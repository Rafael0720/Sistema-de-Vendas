/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Fornecedores;
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
public class FornecedoresDao {

    private Connection con;

    public FornecedoresDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir um novo fornecedor.
     *
     * @param obj
     */
    public void inserir(Fornecedores obj) {
        try {

            String sql = "insert into tb_fornecedores (nome,cnpj,ie,telefone,celular,email,endereco,"
                    + "numero,complemento,bairro,cidade,uf,cep,observacoes) values (?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getIe());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getEmail());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            stmt.setString(13, obj.getCep());
            stmt.setString(14, obj.getObservacoes());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para editar um fornecedor.
     *
     * @param obj
     */
    public void editar(Fornecedores obj) {
        try {

            String sql = "update tb_fornecedores set nome=?,cnpj=?,ie=?,telefone=?,celular=?,email=?,endereco=?,"
                    + "numero=?,complemento=?,bairro=?,cidade=?,uf=?,cep=?,observacoes=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCnpj());
            stmt.setString(3, obj.getIe());
            stmt.setString(4, obj.getTelefone());
            stmt.setString(5, obj.getCelular());
            stmt.setString(6, obj.getEmail());
            stmt.setString(7, obj.getEndereco());
            stmt.setString(8, obj.getNumero());
            stmt.setString(9, obj.getComplemento());
            stmt.setString(10, obj.getBairro());
            stmt.setString(11, obj.getCidade());
            stmt.setString(12, obj.getUf());
            stmt.setString(13, obj.getCep());
            stmt.setString(14, obj.getObservacoes());
            stmt.setInt(15, obj.getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo alterado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir um fornecedor.
     *
     * @param id
     */
    public void excluir(int id) {
        try {

            String sql = "delete from tb_fornecedores where id = ?";

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
     * Método para retornar a lista de todos os fornecedores.
     *
     * @return
     */
    public List<Fornecedores> listarFornecedores() {
        try {

            List<Fornecedores> lista = new ArrayList<>();

            String sql = "select id,nome,cnpj,telefone from tb_fornecedores order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setTelefone(rs.getString("telefone"));

                lista.add(obj);
            }
            stmt.close();
            return lista;
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * Método para retornar a lista de fornecedores, com base no nome buscado.
     *
     * @param nome
     * @return
     */
    public List<Fornecedores> consultarPorNome(String nome) {
        try {

            List<Fornecedores> lista = new ArrayList<>();

            String sql = "select id, nome, cnpj, telefone from tb_fornecedores where nome like ? order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Fornecedores obj = new Fornecedores();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setTelefone(rs.getString("telefone"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * Método para retornar os dados de um fornecedor, ao buscar pelo ID.
     *
     * @param id
     * @return
     */
    public Fornecedores consultarPorId(int id) {
        try {

            String sql = "select * from tb_fornecedores where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Fornecedores obj = new Fornecedores();

            if (rs.next()) {

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCnpj(rs.getString("cnpj"));
                obj.setIe(rs.getString("ie"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setEmail(rs.getString("email"));
                obj.setEndereco(rs.getString("endereco"));
                obj.setNumero(rs.getString("numero"));
                obj.setComplemento(rs.getString("complemento"));
                obj.setBairro(rs.getString("bairro"));
                obj.setCidade(rs.getString("cidade"));
                obj.setUf(rs.getString("uf"));
                obj.setCep(rs.getString("cep"));
                obj.setObservacoes(rs.getString("observacoes"));
            }
            stmt.close();
            return obj;

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
            return null;
        }
    }

    /**
     * Método para retornar o id do último cadastro.
     *
     * @return
     */
    public int ultimoCadastro() {
        try {

            int id = 0;

            String sql = "select max(id)id from tb_fornecedores";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id");
            }
            stmt.close();
            return id;
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
