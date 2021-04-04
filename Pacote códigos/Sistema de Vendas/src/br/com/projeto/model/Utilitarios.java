/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.model;

import java.awt.Component;
import java.awt.Toolkit;
import java.text.SimpleDateFormat;
import java.util.Date;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.text.JTextComponent;

/**
 *
 * @author Rafael Godoy
 */
public class Utilitarios {

    /**
     * Método para limpar e habilitar todos os textBox que estiverem dentro do
     * container.
     *
     * @param container
     */
    public void limpaTela_Habilita(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextComponent) component).setText(null);
                ((JTextComponent) component).setEditable(true);

            }
        }
    }

    /**
     * Método para limpar e desabilitar todos os textBox que estiverem dentro do
     * container.
     *
     * @param container
     */
    public void limpaTela_Desabilita(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextComponent) component).setText(null);
                ((JTextComponent) component).setEditable(false);

            }
        }
    }

    /**
     * Método para limpar todos os textBox que estiverem dentro do container.
     *
     * @param container
     */
    public void limpaTela_Apenas(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextComponent) component).setText(null);

            }
        }
    }

    /**
     * Método para habilitar todos os textBox que estiverem dentro do container.
     *
     * @param container
     */
    public void Habilita_Apenas(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEditable(true);
            }
        }
    }

    /**
     * Método para desabilitar todos os textBox que estiverem dentro do
     * container.
     *
     * @param container
     */
    public void Desabilita_Apenas(JPanel container) {
        Component components[] = container.getComponents();
        for (Component component : components) {
            if (component instanceof JTextField) {
                ((JTextField) component).setEditable(false);
            }
        }
    }

    /**
     * Método que retorna a data do dia atual
     *
     * @return
     */
    public String atualizaData() {
        Date data = new Date();
        SimpleDateFormat formatar = new SimpleDateFormat("dd/MM/yyyy");
        String dataAtual = formatar.format(data);
        return dataAtual;
    }

    /**
     * Método para inserir ícone nos formulários.
     *
     * @param frm
     */
    public void inserirIcone(JFrame frm) {

        try {
            frm.setIconImage(Toolkit.getDefaultToolkit().getImage("src/imagens/icone_principal.png"));
        } catch (Exception e) {

        }

    }
}
