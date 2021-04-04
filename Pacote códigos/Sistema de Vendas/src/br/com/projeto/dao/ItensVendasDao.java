/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.ItensVendas;
import br.com.projeto.model.Produtos;
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
public class ItensVendasDao {

    private Connection con;

    public ItensVendasDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir o item, resultado da venda de um produto já
     * cadastrado.
     *
     * @param obj
     */
    public void inserir(ItensVendas obj) {
        try {

            String sql = "insert into tb_itensvendas(id_venda,id_produto,preco,quantidade,subtotal) values (?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getVenda().getId());
            stmt.setInt(2, obj.getProduto().getId());
            stmt.setDouble(3, obj.getPreco());
            stmt.setInt(4, obj.getQuantidade());
            stmt.setDouble(5, obj.getSubtotal());
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir um item, resultado da venda de um produto já
     * cadastrado.
     *
     * @param id
     */
    public void excluirItem(int id) {

        try {

            String sql = "delete from tb_itensvendas where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir todos os itens que compõem uma venda, quando essa
     * venda é editada ou cancelada.
     *
     * @param id
     */
    public void excluir(int id) {

        try {

            String sql = "delete from tb_itensvendas where id_venda = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para filtrar todos os itens de uma venda, esses itens serão
     * apresentado na tabela de itens dessa venda.
     *
     * @param id_venda
     * @return
     */
    public List<ItensVendas> consultarItens(int id_venda) {
        try {

            List<ItensVendas> lista = new ArrayList<>();

            String sql = "select i.id,i.id_venda,i.id_produto,p.nome,i.preco,i.quantidade,i.subtotal "
                    + "from tb_itensvendas as i inner join tb_produtos as p on i.id_produto = p.id where i.id_venda = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_venda);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ItensVendas obj = new ItensVendas();
                obj.setId(rs.getInt("i.id"));

                Vendas objVen = new Vendas();
                objVen.setId(rs.getInt("i.id_venda"));
                obj.setVenda(objVen);

                Produtos objPro = new Produtos();
                objPro.setId(rs.getInt("i.id_produto"));
                objPro.setNome(rs.getString("p.nome"));
                obj.setProduto(objPro);

                obj.setPreco(rs.getDouble("i.preco"));
                obj.setQuantidade(rs.getInt("i.quantidade"));
                obj.setSubtotal(rs.getDouble("i.subtotal"));
                lista.add(obj);
            }
            stmt.close();
            return lista;

        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
