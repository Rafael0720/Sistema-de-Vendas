/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package br.com.projeto.view;

import br.com.projeto.dao.ComprasDao;
import br.com.projeto.dao.ContasPagDao;
import br.com.projeto.dao.ItensComprasDao;
import br.com.projeto.model.Compras;
import br.com.projeto.model.ContasPag;
import br.com.projeto.model.ItensCompras;
import br.com.projeto.model.Utilitarios;
import com.sun.glass.events.KeyEvent;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

/**
 *
 * @author Rafael Godoy
 */
public class FrmComprasFiltro extends javax.swing.JFrame {

    String origem = null; // Variável para determinar qual form chamou esse, assim determina quais ações serão executadas
    // de acordo com a origem em questão.

    DecimalFormat decimal = new DecimalFormat("#,##0.00"); // considera apenas as duas casas decimais.
    FrmComprasFiltroReq compFilReq = new FrmComprasFiltroReq();

    /**
     * O método mostrarTela especifica qual form chamou esse, esse form pode
     * trocar informações (buscar e levar) com o form que o chamou.
     */
    private FrmCompras compras;

    public void mostrarTela(FrmCompras comp) {
        this.compras = comp;
        this.origem = "requisicao";
        setVisible(true);
    }

    public FrmComprasFiltro() {
        initComponents();

        Utilitarios util = new Utilitarios();
        util.inserirIcone(this);
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel3 = new javax.swing.JPanel();
        jPanel4 = new javax.swing.JPanel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        btnOk = new javax.swing.JButton();
        jScrollPane1 = new javax.swing.JScrollPane();
        tb_Compras = new javax.swing.JTable();
        btnPesquisar = new javax.swing.JButton();
        btnFiltrar = new javax.swing.JButton();
        jLabel2 = new javax.swing.JLabel();
        txtrequisicao = new javax.swing.JTextField();

        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        setUndecorated(true);

        jPanel3.setBackground(new java.awt.Color(173, 198, 242));
        jPanel3.setBorder(new javax.swing.border.SoftBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel1.setBackground(new java.awt.Color(0, 0, 255));

        jLabel1.setFont(new java.awt.Font("Tahoma", 1, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("Compras");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(233, 233, 233)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel1)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        jPanel2.setBorder(javax.swing.BorderFactory.createTitledBorder(""));

        btnOk.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/ok.png"))); // NOI18N
        btnOk.setToolTipText("OK");
        btnOk.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnOkActionPerformed(evt);
            }
        });

        tb_Compras.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        tb_Compras.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {

            },
            new String [] {
                "Requisição", "Fornecedor", "Emissão", "Valor"
            }
        ) {
            boolean[] canEdit = new boolean [] {
                false, false, false, false
            };

            public boolean isCellEditable(int rowIndex, int columnIndex) {
                return canEdit [columnIndex];
            }
        });
        tb_Compras.setRowHeight(20);
        jScrollPane1.setViewportView(tb_Compras);
        if (tb_Compras.getColumnModel().getColumnCount() > 0) {
            tb_Compras.getColumnModel().getColumn(0).setPreferredWidth(40);
            tb_Compras.getColumnModel().getColumn(1).setPreferredWidth(175);
            tb_Compras.getColumnModel().getColumn(2).setPreferredWidth(40);
            tb_Compras.getColumnModel().getColumn(3).setPreferredWidth(40);
        }

        btnPesquisar.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        btnPesquisar.setText("Pesquisar");
        btnPesquisar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnPesquisarActionPerformed(evt);
            }
        });

        btnFiltrar.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Imagens/filtro.png"))); // NOI18N
        btnFiltrar.setToolTipText("Filtrar");
        btnFiltrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFiltrarActionPerformed(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N
        jLabel2.setText("Nº Requisição:");

        txtrequisicao.setFont(new java.awt.Font("Tahoma", 0, 14)); // NOI18N

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 567, Short.MAX_VALUE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btnFiltrar, javax.swing.GroupLayout.PREFERRED_SIZE, 49, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(61, 61, 61)
                        .addComponent(jLabel2)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(txtrequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 123, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(btnPesquisar)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 65, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addGap(0, 0, 0))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnOk, javax.swing.GroupLayout.PREFERRED_SIZE, 57, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addContainerGap()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                .addComponent(txtrequisicao, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addComponent(jLabel2))
                            .addComponent(btnPesquisar, javax.swing.GroupLayout.PREFERRED_SIZE, 36, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(btnFiltrar))
                .addGap(0, 0, 0)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 319, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, 0))
        );

        javax.swing.GroupLayout jPanel4Layout = new javax.swing.GroupLayout(jPanel4);
        jPanel4.setLayout(jPanel4Layout);
        jPanel4Layout.setHorizontalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel4Layout.setVerticalGroup(
            jPanel4Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel4Layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(0, 0, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(5, 5, 5)
                .addComponent(jPanel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(5, 5, 5))
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(25, 25, 25)
                .addComponent(jPanel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(5, 5, 5))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addGap(0, 0, 0)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
        );

        pack();
        setLocationRelativeTo(null);
    }// </editor-fold>//GEN-END:initComponents

    private void btnOkActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnOkActionPerformed

        int validacao = tb_Compras.getSelectedRowCount(); // verifica se tem uma ou mais linhas selecionadas.

        if (validacao == 0) {
            this.dispose();
        } else if (validacao > 1) {
            JOptionPane.showMessageDialog(null, "Há mais de uma linha selecionada, selecione apenas uma linha!");
        } else {

            int id = Integer.parseInt(tb_Compras.getValueAt(tb_Compras.getSelectedRow(), 0).toString());
            ComprasDao dao = new ComprasDao();
            Compras obj = new Compras();
            obj = dao.consultarPorId(id);

            compras.txtId.setText(String.valueOf(obj.getId()));

            compras.situacao = obj.getSituacao().getNome();
            compras.situacaoAnterior = obj.getSituacao().getNome();
            compras.txtIdSituacao.setText(String.valueOf(obj.getSituacao().getId()));
            compras.txtSituacao.setText(obj.getSituacao().getNome());

            SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
            Date emissao;
            try {
                emissao = formato.parse(obj.getEmissao());
                compras.txtEmissao.setDate(emissao);
            } catch (Exception e) {
            }

            int parcelas = obj.getParcelas();
            compras.txtIdParcelas.setText(String.valueOf(parcelas));

            //esse if/else é pra preenche o campo do número de parcelas.
            if (parcelas == 1) {
                compras.txtParcelas.setText("Não foi parcelado!");
            } else {
                compras.txtParcelas.setText("Parcelado em " + parcelas + "X");
            }
            compras.txtIdFornecedor.setText(String.valueOf(obj.getFornecedor().getId()));
            compras.txtNomeFornecedor.setText(obj.getFornecedor().getNome());
            compras.txtObservacoes.setText(obj.getObservacoes());
            compras.txtTotal.setText(String.valueOf(decimal.format(obj.getTotal_compras())));
            compras.total = obj.getTotal_compras();

            //essa parte preenche a tabela de itens.
            ItensComprasDao daoItens = new ItensComprasDao();
            List<ItensCompras> lista = daoItens.consultarItens(id);

            DefaultTableModel dados = (DefaultTableModel) compras.tb_Itens.getModel();
            dados.setNumRows(0);

            for (ItensCompras i : lista) {

                String subtotal = String.valueOf(decimal.format(i.getSubtotal()));
                String preco = String.valueOf(decimal.format(i.getPreco()));
                dados.addRow(new Object[]{
                    "N",
                    i.getProduto().getId(),
                    i.getProduto().getNome(),
                    preco,
                    i.getQuantidade(),
                    subtotal
                });
            }

            //essa é a parte q preenche a tabela de contas.
            ContasPagDao daoContas = new ContasPagDao();
            List<ContasPag> listaContas = daoContas.listarPorRequisicao(id);

            DefaultTableModel tabela_conta = (DefaultTableModel) compras.tb_contas.getModel();
            tabela_conta.setNumRows(0);

            for (ContasPag c : listaContas) {

                String temBaixa = c.getBaixa();

                if (temBaixa == null) {
                    String emissaoC = c.getEmissao();
                    emissaoC = emissaoC.substring(8, 10) + "/" + emissaoC.substring(5, 7) + "/" + emissaoC.substring(0, 4);
                    String vencimentoC = c.getVencimento();
                    vencimentoC = vencimentoC.substring(8, 10) + "/" + vencimentoC.substring(5, 7) + "/" + vencimentoC.substring(0, 4);
                    String valor_compra = String.valueOf(decimal.format(c.getValor_compra()));
                    tabela_conta.addRow(new Object[]{
                        c.getId(),
                        c.getSequencia(),
                        emissaoC,
                        vencimentoC,
                        "",
                        valor_compra,
                        c.getSituacao()
                    });
                } else {
                    String emissaoC = c.getEmissao();
                    emissaoC = emissaoC.substring(8, 10) + "/" + emissaoC.substring(5, 7) + "/" + emissaoC.substring(0, 4);
                    String vencimentoC = c.getVencimento();
                    vencimentoC = vencimentoC.substring(8, 10) + "/" + vencimentoC.substring(5, 7) + "/" + vencimentoC.substring(0, 4);
                    String baixaC = c.getBaixa();
                    baixaC = baixaC.substring(8, 10) + "/" + baixaC.substring(5, 7) + "/" + baixaC.substring(0, 4);
                    String valor_compra = String.valueOf(decimal.format(c.getValor_compra()));
                    tabela_conta.addRow(new Object[]{
                        c.getId(),
                        c.getSequencia(),
                        emissaoC,
                        vencimentoC,
                        baixaC,
                        valor_compra,
                        c.getSituacao()
                    });
                }

            }
            this.dispose();
        }


    }//GEN-LAST:event_btnOkActionPerformed

    private void btnFiltrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFiltrarActionPerformed

        compFilReq.mostrarTela(this);
    }//GEN-LAST:event_btnFiltrarActionPerformed

    private void btnPesquisarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnPesquisarActionPerformed

        try {
            // caso deseje pesquisar pelo número da requisição.
            int id_requisicao = Integer.parseInt(txtrequisicao.getText());

            Compras obj = new Compras();
            ComprasDao dao = new ComprasDao();

            obj = dao.consultarPorId(id_requisicao);

            DefaultTableModel dados = (DefaultTableModel) tb_Compras.getModel();
            dados.setNumRows(0);

            String emissao = obj.getEmissao();
            emissao = emissao.substring(8, 10) + "/" + emissao.substring(5, 7) + "/" + emissao.substring(0, 4);
            String total = String.valueOf(decimal.format(obj.getTotal_compras()));

            dados.addRow(new Object[]{
                obj.getId(),
                obj.getFornecedor().getNome(),
                emissao,
                total
            });
        } catch (Exception e) {
        }


    }//GEN-LAST:event_btnPesquisarActionPerformed

    /**
     * @param args the command line arguments
     */
    public static void main(String args[]) {
        /* Set the Nimbus look and feel */
        //<editor-fold defaultstate="collapsed" desc=" Look and feel setting code (optional) ">
        /* If Nimbus (introduced in Java SE 6) is not available, stay with the default look and feel.
         * For details see http://download.oracle.com/javase/tutorial/uiswing/lookandfeel/plaf.html 
         */
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if ("Windows".equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException ex) {
            java.util.logging.Logger.getLogger(FrmComprasFiltro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (InstantiationException ex) {
            java.util.logging.Logger.getLogger(FrmComprasFiltro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (IllegalAccessException ex) {
            java.util.logging.Logger.getLogger(FrmComprasFiltro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        } catch (javax.swing.UnsupportedLookAndFeelException ex) {
            java.util.logging.Logger.getLogger(FrmComprasFiltro.class.getName()).log(java.util.logging.Level.SEVERE, null, ex);
        }
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>
        //</editor-fold>

        /* Create and display the form */
        java.awt.EventQueue.invokeLater(new Runnable() {
            public void run() {
                new FrmComprasFiltro().setVisible(true);
            }
        });
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnFiltrar;
    private javax.swing.JButton btnOk;
    private javax.swing.JButton btnPesquisar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JPanel jPanel4;
    private javax.swing.JScrollPane jScrollPane1;
    public javax.swing.JTable tb_Compras;
    private javax.swing.JTextField txtrequisicao;
    // End of variables declaration//GEN-END:variables
}
