/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Funcionarios;
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
public class FuncionariosDao {

    private Connection con;

    public FuncionariosDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir um novo funcionário.
     *
     * @param obj
     */
    public void inserir(Funcionarios obj) {

        try {

            String sql = "insert into tb_funcionarios (nome,cpf,rg,nascimento,telefone,celular,cargo,email,senha,"
                    + "nivel_acesso,endereco,numero,complemento,bairro,cidade,uf,cep,observacoes) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getRg());
            stmt.setString(4, obj.getNascimento());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCargo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getSenha());
            stmt.setString(10, obj.getNivel_acesso());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            stmt.setString(17, obj.getCep());
            stmt.setString(18, obj.getObservacoes());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo cadastrado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }

    }

    /**
     * Método para editar um funcionário.
     *
     * @param obj
     */
    public void editar(Funcionarios obj) {

        try {

            String sql = "update tb_funcionarios set nome=?,cpf=?,rg=?,nascimento=?,telefone=?,celular=?,cargo=?,email=?,"
                    + "senha=?,nivel_acesso=?,endereco=?,numero=?,complemento=?,bairro=?,cidade=?,uf=?,cep=?,observacoes=?"
                    + "where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getNome());
            stmt.setString(2, obj.getCpf());
            stmt.setString(3, obj.getRg());
            stmt.setString(4, obj.getNascimento());
            stmt.setString(5, obj.getTelefone());
            stmt.setString(6, obj.getCelular());
            stmt.setString(7, obj.getCargo());
            stmt.setString(8, obj.getEmail());
            stmt.setString(9, obj.getSenha());
            stmt.setString(10, obj.getNivel_acesso());
            stmt.setString(11, obj.getEndereco());
            stmt.setString(12, obj.getNumero());
            stmt.setString(13, obj.getComplemento());
            stmt.setString(14, obj.getBairro());
            stmt.setString(15, obj.getCidade());
            stmt.setString(16, obj.getUf());
            stmt.setString(17, obj.getCep());
            stmt.setString(18, obj.getObservacoes());
            stmt.setInt(19, obj.getId());
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo alterado com sucesso!");

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir um funcionário.
     *
     * @param id
     */
    public void excluir(int id) {

        try {

            String sql = "delete from tb_funcionarios where id=?";

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
     * Método para retornar a lista de todos os funcionários.
     *
     * @return
     */
    public List<Funcionarios> listarFuncionarios() {
        try {

            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select id,nome,celular,cargo from tb_funcionarios order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionarios obj = new Funcionarios();

                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCelular(rs.getString("celular"));
                obj.setCargo(rs.getString("cargo"));
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
     * Método para retornar a lista de funcionários, com base no nome buscado.
     *
     * @param nome
     * @return
     */
    public List<Funcionarios> consultarPorNome(String nome) {
        try {

            List<Funcionarios> lista = new ArrayList<>();

            String sql = "select id,nome,celular,cargo from tb_funcionarios where nome like ? order by nome";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, nome);

            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                Funcionarios obj = new Funcionarios();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCelular(rs.getString("celular"));
                obj.setCargo(rs.getString("cargo"));

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
     * Método para retornar os dados de um funcionário, ao buscar pelo ID.
     *
     * @param id
     * @return
     */
    public Funcionarios consultarPorId(int id) {
        try {

            String sql = "select * from tb_funcionarios where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            Funcionarios obj = new Funcionarios();
            if (rs.next()) {
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                obj.setCpf(rs.getString("cpf"));
                obj.setRg(rs.getString("rg"));
                obj.setNascimento(rs.getString("nascimento"));
                obj.setTelefone(rs.getString("telefone"));
                obj.setCelular(rs.getString("celular"));
                obj.setCargo(rs.getString("cargo"));
                obj.setEmail(rs.getString("email"));
                obj.setSenha(rs.getString("senha"));
                obj.setNivel_acesso(rs.getString("nivel_acesso"));
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
            int idCadastro = 0;

            String sql = "select max(id)id from tb_funcionarios";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                Funcionarios obj = new Funcionarios();
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
