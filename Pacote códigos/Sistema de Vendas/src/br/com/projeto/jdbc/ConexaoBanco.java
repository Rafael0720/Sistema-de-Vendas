/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.jdbc;

import java.sql.Connection;
import java.sql.DriverManager;

/**
 *
 * @author Rafael Godoy
 */
public class ConexaoBanco {

    /**
     * Método de conexão com o banco de dados.
     *
     * @return
     */
    public Connection getConnection() {
        try {
            return DriverManager.getConnection("jdbc:mysql://localhost:3306/bd_sistemavendas", "root", "");
        } catch (Exception e) {
            throw new RuntimeException(e);
        }
    }

}
