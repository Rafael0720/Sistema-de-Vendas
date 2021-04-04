/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Planos;
import br.com.projeto.model.VendasNoCartao;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;
import javax.swing.JOptionPane;

/**
 *
 * @author Rafael Godoy
 */
public class VendasNoCartaoDao {

    private Connection con;

    public VendasNoCartaoDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir uma venda no cartão.
     *
     * @param obj
     */
    public void inserir(VendasNoCartao obj) {

        try {

            String sql = "insert into tb_vendascartao (id_requisicao,emissao,vencimento,data_baixa,id_plano,situacao,valorVenda,"
                    + "desconto,valorFinal) values (?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId_requisicao());
            stmt.setString(2, obj.getEmissao());
            stmt.setString(3, obj.getVencimento());
            stmt.setString(4, obj.getData_baixa());
            stmt.setInt(5, obj.getPlano().getId());
            stmt.setString(6, obj.getSituacao());
            stmt.setDouble(7, obj.getValorVenda());
            stmt.setDouble(8, obj.getDesconto());
            stmt.setDouble(9, obj.getValorFinal());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para editar uma venda feita no cartão.
     *
     * @param obj
     */
    public void editar(VendasNoCartao obj) {

        try {

            String sql = "update tb_vendascartao set id_requisicao=?, emissao=?,vencimento=?,data_baixa=?,id_plano=?,situacao=?,"
                    + "valorVenda=?,desconto=?,valorFinal=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getId_requisicao());
            stmt.setString(2, obj.getEmissao());
            stmt.setString(3, obj.getVencimento());
            stmt.setString(4, obj.getData_baixa());
            stmt.setInt(5, obj.getPlano().getId());
            stmt.setString(6, obj.getSituacao());
            stmt.setDouble(7, obj.getValorVenda());
            stmt.setDouble(8, obj.getDesconto());
            stmt.setDouble(9, obj.getValorFinal());
            stmt.setInt(10, obj.getId());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para editar o status de uma venda feita no cartão.
     *
     * @param situacao
     * @param id
     */
    public void editarStatus(String situacao, int id) {

        try {

            String sql = "update tb_vendascartao set situacao=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setInt(2, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para editar o status da baixa, de uma venda feita no cartão.
     *
     * @param situacao
     * @param data_baixa
     * @param id
     */
    public void editarStatus_Baixa(String situacao, String data_baixa, int id) {

        try {

            String sql = "update tb_vendascartao set situacao=?, data_baixa=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setString(2, data_baixa);
            stmt.setInt(3, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas no cartão.
     *
     * @return
     */
    public List<VendasNoCartao> listaVendas() {

        try {

            List<VendasNoCartao> lista = new ArrayList<>();

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                VendasNoCartao obj = new VendasNoCartao();

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas no cartão, com base no status
     * escolhido.
     *
     * @param status
     * @return
     */
    public List<VendasNoCartao> listarStatus(String status) {

        try {

            List<VendasNoCartao> lista = new ArrayList<>();

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) where situacao=? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                VendasNoCartao obj = new VendasNoCartao();

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas no cartão, com base na data de
     * emissão.
     *
     * @param data_inicio
     * @param data_final
     * @return
     */
    public List<VendasNoCartao> listarEmissaoTudo(String data_inicio, String data_final) {

        try {

            List<VendasNoCartao> lista = new ArrayList<>();

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) where emissao between ? and ? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio);
            stmt.setString(2, data_final);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                VendasNoCartao obj = new VendasNoCartao();

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas no cartão, com base no status
     * escolhido e na data de emissão.
     *
     * @param data_inicio
     * @param data_final
     * @param status
     * @return
     */
    public List<VendasNoCartao> listarEmissaoStatus(String data_inicio, String data_final, String status) {

        try {

            List<VendasNoCartao> lista = new ArrayList<>();

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) where emissao between ? and ? and situacao=? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio);
            stmt.setString(2, data_final);
            stmt.setString(3, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                VendasNoCartao obj = new VendasNoCartao();

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas no cartão, com base na data de
     * vencimento.
     *
     * @param data_inicio
     * @param data_final
     * @return
     */
    public List<VendasNoCartao> listarVencimentoTudo(String data_inicio, String data_final) {

        try {

            List<VendasNoCartao> lista = new ArrayList<>();

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) where vencimento between ? and ? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio);
            stmt.setString(2, data_final);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                VendasNoCartao obj = new VendasNoCartao();

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para listar todas as vendas feitas no cartão, com base no status
     * escolhido e na data de vencimento.
     *
     * @param data_inicio
     * @param data_final
     * @param status
     * @return
     */
    public List<VendasNoCartao> listarVencimentoStatus(String data_inicio, String data_final, String status) {

        try {

            List<VendasNoCartao> lista = new ArrayList<>();

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) where vencimento between ? and ? and situacao=? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, data_inicio);
            stmt.setString(2, data_final);
            stmt.setString(3, status);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                VendasNoCartao obj = new VendasNoCartao();

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para buscar uma venda feita no cartão, com base requisição de
     * venda.
     *
     * @param requisicao
     * @return
     */
    public List<VendasNoCartao> buscarPorRequisicao(int requisicao) {
        try {

            List<VendasNoCartao> lista = new ArrayList<>();

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) where v.id_requisicao=? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, requisicao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                VendasNoCartao obj = new VendasNoCartao();

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para buscar uma venda feita no cartão, com base id dessa venda.
     *
     * @param id
     * @return
     */
    public VendasNoCartao buscarPorId(int id) {
        try {

            String sql = "select v.id, v.id_requisicao, v.emissao, v.vencimento, v.data_baixa, v.id_plano, p.nome, "
                    + "p.porcentagem, v.situacao, v.valorVenda, v.desconto, v.valorFinal from tb_vendascartao as v "
                    + "inner join tb_planos as p on (v.id_plano = p.id) where v.id=? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            ResultSet rs = stmt.executeQuery();

            VendasNoCartao obj = new VendasNoCartao();

            if (rs.next()) {

                obj.setId(rs.getInt("v.id"));
                obj.setId_requisicao(rs.getInt("v.id_requisicao"));
                obj.setEmissao(rs.getString("v.emissao"));
                obj.setVencimento(rs.getString("v.vencimento"));
                obj.setData_baixa(rs.getString("v.data_baixa"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("id_plano"));
                objPla.setNome(rs.getString("p.nome"));
                objPla.setPorcentagem(rs.getDouble("p.porcentagem"));
                obj.setPlano(objPla);

                obj.setSituacao(rs.getString("v.situacao"));
                obj.setValorVenda(rs.getDouble("v.valorVenda"));

                obj.setDesconto(rs.getDouble("v.desconto"));
                obj.setValorFinal(rs.getDouble("v.valorFinal"));

            }
            stmt.close();
            return obj;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para retornar o id da última venda feita no cartão.
     *
     * @return
     */
    public int ultimoCadastro() {
        try {
            int idCadastro = 0;

            String sql = "select max(id)id from tb_vendascartao";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            if (rs.next()) {
                VendasNoCartao obj = new VendasNoCartao();
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
