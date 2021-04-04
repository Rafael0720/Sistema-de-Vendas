/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Situacao;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Rafael Godoy
 */
public class SituacaoDao {

    private Connection con;

    public SituacaoDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para retornar as duas opções de situação, A Vista e A Prazo, que
     * será usado para realizar uma compra ou venda de produtos.
     *
     * @return
     */
    public List<Situacao> listarSituacao() {
        try {

            List<Situacao> lista = new ArrayList<>();

            String sql = "select * from tb_situacao";

            PreparedStatement stmt = con.prepareStatement(sql);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {
                Situacao obj = new Situacao();
                obj.setId(rs.getInt("id"));
                obj.setNome(rs.getString("nome"));
                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
