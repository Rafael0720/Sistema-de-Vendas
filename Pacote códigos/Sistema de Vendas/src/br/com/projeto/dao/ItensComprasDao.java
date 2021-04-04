/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.dao;

import br.com.projeto.jdbc.ConexaoBanco;
import br.com.projeto.model.Compras;
import br.com.projeto.model.ItensCompras;
import br.com.projeto.model.Produtos;
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
public class ItensComprasDao {

    private Connection con;

    public ItensComprasDao() {
        this.con = new ConexaoBanco().getConnection();
    }

    /**
     * Método para inserir o item, resultado da compra de um produto já
     * cadastrado.
     *
     * @param obj
     */
    public void inserir(ItensCompras obj) {
        try {

            String sql = "insert into tb_itenscompras(id_compra,id_produto,preco,quantidade,subtotal) values (?,?,?,?,?)";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, obj.getCompra().getId());
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
     * Método para excluir um item, resultado da compra de um produto já
     * cadastrado.
     *
     * @param id
     */
    public void excluirItem(int id) {

        try {

            String sql = "delete from tb_itenscompras where id = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para excluir todos os itens que compõem uma compra, quando essa
     * compra é editada ou cancelada.
     *
     * @param id
     */
    public void excluir(int id) {

        try {

            String sql = "delete from tb_itenscompras where id_compra = ?";

            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id);
            stmt.execute();
            stmt.close();

        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, e);
        }
    }

    /**
     * Método para filtrar todos os itens de uma compra, esses itens serão
     * apresentado na tabela de itens dessa compra.
     *
     * @param id_compra
     * @return
     */
    public List<ItensCompras> consultarItens(int id_compra) {
        try {

            List<ItensCompras> lista = new ArrayList<>();

            String sql = "select i.id,i.id_compra,i.id_produto,p.nome,i.preco,i.quantidade,i.subtotal "
                    + "from tb_itenscompras as i inner join tb_produtos as p on i.id_produto = p.id where i.id_compra = ?";
            PreparedStatement stmt = con.prepareStatement(sql);
            stmt.setInt(1, id_compra);
            ResultSet rs = stmt.executeQuery();

            while (rs.next()) {

                ItensCompras obj = new ItensCompras();
                obj.setId(rs.getInt("i.id"));

                Compras objCom = new Compras();
                objCom.setId(rs.getInt("i.id_compra"));
                obj.setCompra(objCom);

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
