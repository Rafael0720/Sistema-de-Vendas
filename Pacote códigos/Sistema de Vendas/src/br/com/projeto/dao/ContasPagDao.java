/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.ContasPag;
import br.com.projeto.model.Fornecedores;
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
public class ContasPagDao {

    private Connection con;

    public ContasPagDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir uma nova conta a pagar.
     *
     * @param obj
     */
    public void inserir(ContasPag obj) {
        try {

            String sql = "insert into tb_contaspagar (id,contador,requisicao,sequencia,situacao,emissao,vencimento,baixa,id_fornecedor,"
                    + "id_plano,origem,valor_compra,juros_porc,juros,desconto_porc,desconto,valor_final,observacoes) "
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
            stmt.setInt(9, obj.getFornecedor().getId());
            stmt.setInt(10, obj.getPlano().getId());
            stmt.setString(11, obj.getOrigem());
            stmt.setDouble(12, obj.getValor_compra());
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
     * Método para editar uma conta a pagar já cadastrada.
     *
     * @param obj
     */
    public void editar(ContasPag obj) {
        try {

            String sql = "update tb_contaspagar set contador=?,requisicao=?,sequencia=?,situacao=?,emissao=?,"
                    + "vencimento=?,baixa=?,id_fornecedor=?,id_plano=?,origem=?,valor_compra=?,"
                    + "juros_porc=?,juros=?,desconto_porc=?,desconto=?,valor_final=?,observacoes=? where id=?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getContador());
            stmt.setInt(2, obj.getRequisicao());
            stmt.setString(3, obj.getSequencia());
            stmt.setString(4, obj.getSituacao());
            stmt.setString(5, obj.getEmissao());
            stmt.setString(6, obj.getVencimento());
            stmt.setString(7, obj.getBaixa());
            stmt.setInt(8, obj.getFornecedor().getId());
            stmt.setInt(9, obj.getPlano().getId());
            stmt.setString(10, obj.getOrigem());
            stmt.setDouble(11, obj.getValor_compra());
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
     * Método para excluir uma conta a pagar já cadastrada.
     *
     * @param id
     */
    public void excluir(String id) {
        try {

            String sql = "delete from tb_contaspagar where id = ?";

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
     * compra. usando como base o id da compra.
     *
     * @param requisicao
     */
    public void excluirDaRequisicao(int requisicao) {
        try {

            String sql = "delete from tb_contaspagar where requisicao = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, requisicao);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

    /**
     * Método para filtrar todas as contas a pagar.
     *
     * @param situacao
     * @return
     */
    public List<ContasPag> filtrarContasSemFornecedorTudo(String situacao) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_fornecedor,fornec.nome,con.emissao,con.vencimento,con.baixa,"
                    + "con.valor_final from tb_contaspagar as con inner join tb_fornecedores as fornec on (con.id_fornecedor = fornec.id) "
                    + "where situacao = ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("con.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));
                obj.setFornecedor(objFor);

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
     * Método para filtrar todas as contas a pagar, com base no id do
     * fornecedor.
     *
     * @param id_fornecedor
     * @param situacao
     * @return
     */
    public List<ContasPag> filtrarContasComFornecedorTudo(int id_fornecedor, String situacao) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_fornecedor,fornec.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contaspagar as con inner join tb_fornecedores as fornec on (con.id_fornecedor = fornec.id) "
                    + "where id_fornecedor =? and situacao = ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_fornecedor);
            stmt.setString(2, situacao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("con.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));
                obj.setFornecedor(objFor);

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
     * Método para filtrar todas as contas a pagar, com base apenas na data de
     * emissão.
     *
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasPag> filtrarContasSemFornecedorEmissao(String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_fornecedor,fornec.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contaspagar as con inner join tb_fornecedores as fornec on (con.id_fornecedor = fornec.id) "
                    + "where situacao = ? and emissao between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setString(2, dataInicio);
            stmt.setString(3, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("con.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));
                obj.setFornecedor(objFor);

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
     * Método para filtrar todas as contas a pagar, com base no id do fornecedor
     * e na data de emissão.
     *
     * @param id_fornecedor
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasPag> filtrarContasComFornecedorEmissao(int id_fornecedor, String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_fornecedor,fornec.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contaspagar as con inner join tb_fornecedores as fornec on (con.id_fornecedor = fornec.id) "
                    + "where con.id_fornecedor = ? and situacao = ? and emissao between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_fornecedor);
            stmt.setString(2, situacao);
            stmt.setString(3, dataInicio);
            stmt.setString(4, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("con.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));
                obj.setFornecedor(objFor);

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
     * Método para filtrar todas as contas a pagar, com base apenas na data de
     * vencimento.
     *
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasPag> filtrarContasSemFornecedorVencimento(String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_fornecedor,fornec.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contaspagar as con inner join tb_fornecedores as fornec on (con.id_fornecedor = fornec.id) "
                    + "where situacao = ? and vencimento between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, situacao);
            stmt.setString(2, dataInicio);
            stmt.setString(3, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("con.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));
                obj.setFornecedor(objFor);

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
     * Método para filtrar todas as contas a pagar, com base no id do fornecedor
     * e na data de vencimento.
     *
     * @param id_fornecedor
     * @param situacao
     * @param dataInicio
     * @param dataFim
     * @return
     */
    public List<ContasPag> filtrarContasComFornecedorVencimento(int id_fornecedor, String situacao, String dataInicio, String dataFim) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select con.id,con.requisicao,con.id_fornecedor,fornec.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contaspagar as con inner join tb_fornecedores as fornec on (con.id_fornecedor = fornec.id) "
                    + "where con.id_fornecedor = ? and situacao = ? and vencimento between ? and ? order by con.emissao";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_fornecedor);
            stmt.setString(2, situacao);
            stmt.setString(3, dataInicio);
            stmt.setString(4, dataFim);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("con.id"));
                obj.setRequisicao(rs.getInt("con.requisicao"));

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("con.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));
                obj.setFornecedor(objFor);

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
    public List<ContasPag> listarPorRequisicao(int requisicao) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select id, sequencia, emissao, vencimento, baixa, valor_compra, situacao "
                    + "from tb_contaspagar where requisicao = ? order by vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, requisicao);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("id"));
                obj.setSequencia(rs.getString("sequencia"));
                obj.setEmissao(rs.getString("emissao"));
                obj.setVencimento(rs.getString("vencimento"));
                obj.setBaixa(rs.getString("baixa"));
                obj.setValor_compra(rs.getDouble("valor_compra"));
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
    public ContasPag consultarPorId(String id) {
        try {

            String sql = "select c.contador, c.requisicao, c.sequencia, c.situacao, c.emissao, c.vencimento, c.baixa, c.id_fornecedor, "
                    + "fornec.nome, c.id_plano, p.nome, c.origem, c.valor_compra, c.juros_porc, c.juros, "
                    + "c.desconto_porc, c.desconto, c.valor_final, c.observacoes from tb_contaspagar as c "
                    + "inner join tb_fornecedores as fornec inner join tb_planos as p on "
                    + "(c.id_fornecedor = fornec.id and c.id_plano = p.id) where c.id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, id);
            ResultSet rs = stmt.executeQuery();

            ContasPag obj = new ContasPag();
            if (rs.next()) {

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("c.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));

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
                obj.setFornecedor(objFor);
                obj.setPlano(objPla);
                obj.setOrigem(rs.getString("c.origem"));
                obj.setValor_compra(rs.getDouble("c.valor_compra"));
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
     * Método para buscar as contas pagas e apresentar no relatório do dia.
     *
     * @param dataBusca
     * @return
     */
    public List<ContasPag> filtrarContasRelatorioDia(String dataBusca) {
        try {

            List<ContasPag> lista = new ArrayList<>();

            String sql = "select con.id,con.id_plano,pla.tipo,con.id_fornecedor,fornec.nome,con.emissao,con.vencimento,con.baixa,con.valor_final "
                    + "from tb_contaspagar as con inner join tb_fornecedores as fornec inner join tb_planos as pla "
                    + "on (con.id_fornecedor = fornec.id) and (con.id_plano = pla.id) "
                    + "where baixa = ? order by con.vencimento";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setString(1, dataBusca);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ContasPag obj = new ContasPag();

                obj.setId(rs.getString("con.id"));

                Planos objPla = new Planos();
                objPla.setId(rs.getInt("con.id_plano"));
                objPla.setTipo(rs.getString("pla.tipo"));
                obj.setPlano(objPla);

                Fornecedores objFor = new Fornecedores();
                objFor.setId(rs.getInt("con.id_fornecedor"));
                objFor.setNome(rs.getString("fornec.nome"));
                obj.setFornecedor(objFor);

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
    public void baixarEstornar(ContasPag obj) {
        try {

            String sql = "update tb_contaspagar set situacao=?, id_plano=?, baixa=? where id=?";

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
     * Método para buscar o último contador. As contas a pagar podem ser feitas
     * de duas formas, através da requisição de compra e ser lançada diretamente
     * no sistema. Para as contas lançadas diretamente, usa-se o último contador
     * para determinar o número da nova conta.
     *
     * @return
     */
    public int ultimoContador() {
        int cont = 0;
        try {
            String sql = "select max(contador)cont from tb_contaspagar";

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
