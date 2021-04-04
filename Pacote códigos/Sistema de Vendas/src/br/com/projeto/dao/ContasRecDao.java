/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Clientes;
import br.com.projeto.model.ContasRec;
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
public class ContasRecDao {

    private Connection con;

    public ContasRecDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir uma nova conta a receber.
     *
     * @param obj
     */
    public void inserir(ContasRec obj) {
        try {

            String sql = "insert into tb_contasreceber (id,contador,requisicao,sequencia,situacao,emissao,vencimento,baixa,id_cliente,"
                    + "id_plano,origem,valor_venda,juros_porc,juros,desconto_porc,desconto,valor_final,observacoes) "
                    + "values (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getId());
            stmt.setInt(2, obj.getContador());
            stmt.setInt(3, obj.getRequisicao());
            stmt.setString(4, obj.getSequencia());
            stmt.setString(5, obj.getSituacao());
            stmt.setString(6, obj.getEmissao());
            stmt.setString(7, obj.getVencimento());
            stmt.setString(8, obj.getBaixa());
            stmt.setInt(9, obj.getCliente().getId());
            stmt.setInt(10, obj.getPlano().getId());
            stmt.setString(11, obj.getOrigem());
            stmt.setDouble(12, obj.getValor_venda());
            stmt.setDouble(13, obj.getJuros_porc());
            stmt.setDouble(14, obj.getJuros());
            stmt.setDouble(15, obj.getDesconto_porc());
            stmt.setDouble(16, obj.getDesconto());
            stmt.setDouble(17, obj.getValor_final());
            stmt.setString(18, obj.getObservacoes());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para editar uma conta a receber já cadastrada.
     *
     * @param obj
     */
    public void editar(ContasRec obj) {
        try {

            String sql = "update tb_contasreceber set contador=?,requisicao=?,sequencia=?,situacao=?,emissao=?,"
                    + "vencimento=?,baixa=?,id_cliente=?,id_plano=?,origem=?,valor_venda=?,"
                    + "juros_porc=?,juros=?,desconto_porc=?,desconto=?,valor_final=?,observacoes=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getContador());
            stmt.setInt(2, obj.getRequisicao());
            stmt.setString(3, obj.getSequencia());
            stmt.setString(4, obj.getSituacao());
            stmt.setString(5, obj.getEmissao());
            stmt.setString(6, obj.getVencimento());
            stmt.setString(7, obj.getBaixa());
            stmt.setInt(8, obj.getCliente().getId());
            stmt.setInt(9, obj.getPlano().getId());
            stmt.setString(10, obj.getOrigem());
            stmt.setDouble(11, obj.getValor_venda());
            stmt.setDouble(12, obj.getJuros_porc());
            stmt.setDouble(13, obj.getJuros());
            stmt.setDouble(14, obj.getDesconto_porc());
            stmt.setDouble(15, obj.getDesconto());
            stmt.setDouble(16, obj.getValor_final());
            stmt.setString(17, obj.getObservacoes());
            stmt.setString(18, obj.getId());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);

        }
    }

    /**
     * Método para excluir uma conta a receber já cadastrada.
     *
     * @param id
     */
    public void excluir(String id) {
        try {

            String sql = "delete from tb_contasreceber where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            stmt.execute();
            stmt.close();

            JOptionPane.showMessageDialog(null, "Arquivo excluído com sucesso!");

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para excluir todas as contas que foram geradas a partir de uma
     * venda, usando como base o id da venda.
     *
     * @param requisicao
     */
    public void excluirDaRequisicao(int requisicao) {
        try {

            String sql = "delete from tb_contasreceber where requisicao = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, requisicao);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para filtrar todas as contas a receber.
     *
     * @param situacao
     * @return
     */
    public List<ContasRec> filtrarContasSemClienteTudo(String situacao) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_cliente,cli.nome,con.emissao,con.vencimento,con.baixa,"
                    + "con.valor_final from tb_contasreceber as con inner join tb_clientes as cli on (con.id_cliente = cli.id) "
                    + "where situacao = ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("con.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("con.emissao"));
                obj.setVencimento(rs.getString("con.vencimento"));
                obj.setBaixa(rs.getString("con.baixa"));
                obj.setValor_final(rs.getDouble("con.valor_final"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para filtrar todas as contas a receber, com base no id do cliente.
     *
     * @param id_cliente
     * @param situacao
     * @return
     */
    public List<ContasRec> filtrarContasComClienteTudo(int id_cliente, String situacao) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_cliente,cli.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contasreceber as con inner join tb_clientes as cli on (con.id_cliente = cli.id) "
                    + "where id_cliente =? and situacao = ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            stmt.setString(2, situacao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("con.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("con.emissao"));
                obj.setVencimento(rs.getString("con.vencimento"));
                obj.setBaixa(rs.getString("con.baixa"));
                obj.setValor_final(rs.getDouble("con.valor_final"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para filtrar todas as contas a receber, com base apenas na data de
     * emissão.
     *
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasRec> filtrarContasSemClienteEmissao(String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_cliente,cli.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contasreceber as con inner join tb_clientes as cli on (con.id_cliente = cli.id) "
                    + "where situacao = ? and emissao between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setString(2, dataInicio);
            stmt.setString(3, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("con.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("con.emissao"));
                obj.setVencimento(rs.getString("con.vencimento"));
                obj.setBaixa(rs.getString("con.baixa"));
                obj.setValor_final(rs.getDouble("con.valor_final"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para filtrar todas as contas a receber, com base no id do cliente
     * e na data de emissão.
     *
     * @param id_cliente
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasRec> filtrarContasComClienteEmissao(int id_cliente, String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_cliente,cli.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contasreceber as con inner join tb_clientes as cli on (con.id_cliente = cli.id) "
                    + "where con.id_cliente = ? and situacao = ? and emissao between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            stmt.setString(2, situacao);
            stmt.setString(3, dataInicio);
            stmt.setString(4, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("con.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("con.emissao"));
                obj.setVencimento(rs.getString("con.vencimento"));
                obj.setBaixa(rs.getString("con.baixa"));
                obj.setValor_final(rs.getDouble("con.valor_final"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para filtrar todas as contas a receber, com base apenas na data de
     * vencimento.
     *
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasRec> filtrarContasSemClienteVencimento(String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_cliente,cli.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contasreceber as con inner join tb_clientes as cli on (con.id_cliente = cli.id) "
                    + "where situacao = ? and vencimento between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setString(2, dataInicio);
            stmt.setString(3, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("con.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("con.emissao"));
                obj.setVencimento(rs.getString("con.vencimento"));
                obj.setBaixa(rs.getString("con.baixa"));
                obj.setValor_final(rs.getDouble("con.valor_final"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para filtrar todas as contas a receber, com base no id do cliente
     * e na data de vencimento.
     *
     * @param id_cliente
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasRec> filtrarContasComClienteVencimento(int id_cliente, String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_cliente,cli.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contasreceber as con inner join tb_clientes as cli on (con.id_cliente = cli.id) "
                    + "where con.id_cliente = ? and situacao = ? and vencimento between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_cliente);
            stmt.setString(2, situacao);
            stmt.setString(3, dataInicio);
            stmt.setString(4, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("con.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("con.emissao"));
                obj.setVencimento(rs.getString("con.vencimento"));
                obj.setBaixa(rs.getString("con.baixa"));
                obj.setValor_final(rs.getDouble("con.valor_final"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /*
    Método para buscar as contas referente a determinada requisição. Para colocar na tabela de contas
    daquela venda.
     */
    public List<ContasRec> listarPorRequisicao(int requisicao) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select id, sequencia, emissao, vencimento, baixa, valor_venda, situacao "
                    + "from tb_contasreceber where requisicao = ? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, requisicao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("id"));
                obj.setSequencia(rs.getString("sequencia"));
                obj.setEmissao(rs.getString("emissao"));
                obj.setVencimento(rs.getString("vencimento"));
                obj.setBaixa(rs.getString("baixa"));
                obj.setValor_venda(rs.getDouble("valor_venda"));
                obj.setSituacao(rs.getString("situacao"));
                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para buscar determinada conta, com base no id dela.
     *
     * @param id
     * @return
     */
    public ContasRec consultarPorId(String id) {
        try {

            String sql = "select c.contador, c.requisicao, c.sequencia, c.situacao, c.emissao, c.vencimento, c.baixa, c.id_cliente, "
                    + "cli.nome, c.id_plano, p.nome, c.origem, c.valor_venda, c.juros_porc, c.juros, "
                    + "c.desconto_porc, c.desconto, c.valor_final, c.observacoes from tb_contasreceber as c "
                    + "inner join tb_clientes as cli inner join tb_planos as p on "
                    + "(c.id_cliente = cli.id and c.id_plano = p.id) where c.id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            ContasRec obj = new ContasRec();
            if (rs.next()) {

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("c.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("c.id_plano"));
                objPla.setNome(rs.getString("p.nome"));

                obj.setContador(rs.getInt("c.contador"));
                obj.setRequisicao(rs.getInt("c.requisicao"));
                obj.setSequencia(rs.getString("c.sequencia"));
                obj.setSituacao(rs.getString("c.situacao"));
                obj.setEmissao(rs.getString("c.emissao"));
                obj.setVencimento(rs.getString("c.vencimento"));
                obj.setBaixa(rs.getString("c.baixa"));
                obj.setCliente(objCli);
                obj.setPlano(objPla);
                obj.setOrigem(rs.getString("c.origem"));
                obj.setValor_venda(rs.getDouble("c.valor_venda"));
                obj.setJuros_porc(rs.getDouble("c.juros_porc"));
                obj.setJuros(rs.getDouble("c.juros"));
                obj.setDesconto_porc(rs.getDouble("c.desconto_porc"));
                obj.setDesconto(rs.getDouble("c.desconto"));
                obj.setValor_final(rs.getDouble("c.valor_final"));
                obj.setObservacoes(rs.getString("c.observacoes"));
            }
            stmt.close();
            return obj;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para buscar as contas recebidas e apresentar no relatório do dia.
     *
     * @param dataBusca
     * @return
     */
    public List<ContasRec> filtrarContasRelatorioDia(String dataBusca) {
        try {

            List<ContasRec> lista = new ArrayList<>();

            String sql = "select con.id,con.id_plano,pla.tipo,con.id_cliente,cli.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contasreceber as con inner join tb_clientes as cli inner join tb_planos as pla "
                    + "on (con.id_cliente = cli.id) and (con.id_plano = pla.id) "
                    + "where baixa = ? order by con.vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dataBusca);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasRec obj = new ContasRec();

                obj.setId(rs.getString("con.id"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("con.id_plano"));
                objPla.setTipo(rs.getString("pla.tipo"));
                obj.setPlano(objPla);

                Clientes objCli = new Clientes();
                objCli.setId(rs.getInt("con.id_cliente"));
                objCli.setNome(rs.getString("cli.nome"));
                obj.setCliente(objCli);

                obj.setEmissao(rs.getString("con.emissao"));
                obj.setVencimento(rs.getString("con.vencimento"));
                obj.setBaixa(rs.getString("con.baixa"));
                obj.setValor_final(rs.getDouble("con.valor_final"));

                lista.add(obj);

            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para mudar o status de uma conta, ao ser baixado ou estornada.
     *
     * @param obj
     */
    public void baixarEstornar(ContasRec obj) {
        try {

            String sql = "update tb_contasreceber set situacao=?, id_plano=?, baixa=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, obj.getSituacao());
            stmt.setInt(2, obj.getPlano().getId());
            stmt.setString(3, obj.getBaixa());
            stmt.setString(4, obj.getId());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para buscar o último contador. As contas a receber podem ser
     * feitas de duas formas, através da requisição de venda e ser lançada
     * diretamente no sistema. Para as contas lançadas diretamente, usa-se o
     * último contador para determinar o número da nova conta.
     *
     * @return
     */
    public int ultimoContador() {
        int cont = 0;
        try {
            String sql = "select max(contador)cont from tb_contasreceber";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                cont = rs.getInt("cont");
            }
            stmt.close();
            return cont;
        } catch (Exception e) {
            return cont;
        }
    }
}
