/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuInterface;

import java.sql.*;
import Conexao.ModuloConexao;
import java.awt.Color;
import javax.swing.JOptionPane;

/**
 *
 * @author Renan
 */
public class TelaClientes extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaClientes
     */
    public TelaClientes() {
        initComponents();
        conexao = ModuloConexao.conector();
        lblObrigatorio1.setVisible(false);
        lblObrigatorio2.setVisible(false);
        lblObrigatorio3.setVisible(false);

    }

    private void adicionar() {
        //A estrutura abaixo verifica os campos necessarios para a adição de novo Cliente
        if (txtNomeCli.getText().isEmpty() || txtRgCli.getText().isEmpty() || txtCpfCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os Campos Obrigatorios *");
            lblObrigatorio1.setVisible(true);
            lblObrigatorio1.setForeground(Color.red);
            lblObrigatorio2.setVisible(true);
            lblObrigatorio2.setForeground(Color.red);
            lblObrigatorio3.setVisible(true);
            lblObrigatorio3.setForeground(Color.red);
        } else {
            lblObrigatorio1.setVisible(false);
            lblObrigatorio2.setVisible(false);
            lblObrigatorio3.setVisible(false);
            //Esta instrução serve para inserir dados ao banco de dados
            // no caso de values (?) corresponde aos campos que sera preenchido 
            String sql = "insert into tbclientes(nomecli,endereco,rg,cpf,rua) values(?,?,?,?,?)";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtNomeCli.getText());
                pst.setString(2, txtEndCli.getText());
                pst.setString(3, txtRgCli.getText());
                pst.setString(4, txtCpfCli.getText());
                pst.setString(5, txtRuaCli.getText());

                //o comando abaixo atualiza a tabela de usuarios com os dados do formulario
                //pst.executeUpdate();
                //A estrutura abaixo informa ao usuario a confirmação de cadastro
                int adicionado = pst.executeUpdate();
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Cliente Cadastrado Com Sucesso!");
                    txtNomeCli.setText(null);
                    txtEndCli.setText(null);
                    txtRgCli.setText(null);
                    txtCpfCli.setText(null);
                    txtRuaCli.setText(null);

                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void alterar() {
        //A estrutura abaixo localiza o campo necessario para a alteração
        if (txtNomeCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o Campo'ID User' para Alteração");
        } else {

            String sql = "update tbclientes set nomecli=?,endereco=?,rg=?,cpf=?,rua=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtNomeCli.getText());
                pst.setString(2, txtEndCli.getText());
                pst.setString(3, txtRgCli.getText());
                pst.setString(4, txtCpfCli.getText());
                pst.setString(6, txtRuaCli.getText());

                //A estrutura abaixo informa ao usuario a confirmação de cadastro
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
                    txtNomeCli.setText(null);
                    txtEndCli.setText(null);
                    txtRgCli.setText(null);
                    txtCpfCli.setText(null);
                    txtRuaCli.setText(null);
                } else {
                    JOptionPane.showMessageDialog(null, "Cliente Invalido para Alteração");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    private void remover() {
        //A estrutura abaixo verifica o campo necessario para remoção de usuarios
        if (txtPesqCli.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o Campo'ID User'para Remoção");
        } else {

            //a estrutura abaixo confirma a remoção do usuario
            int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Remover o Cliente", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                String Sql = "delete from tbclientes where idcli=?";
                try {
                    pst = conexao.prepareStatement(Sql);
                    pst.setString(1, txtPesqCli.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Cliente '" + rs.getString(2) + "' Removido com Sucesso");
                        txtNomeCli.setText(null);
                        txtEndCli.setText(null);
                        txtRgCli.setText(null);
                        txtCpfCli.setText(null);
                        txtRuaCli.setText(null);
                    }
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, e);
                }
            }
        }
    }

    /**
     * This method is called from within the constructor to initialize the form.
     * WARNING: Do NOT modify this code. The content of this method is always
     * regenerated by the Form Editor.
     */
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jPanel2 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        txtNomeCli = new javax.swing.JTextField();
        txtEndCli = new javax.swing.JTextField();
        jLabel3 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtRgCli = new javax.swing.JTextField();
        txtCpfCli = new javax.swing.JTextField();
        jLabel6 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        txtRuaCli = new javax.swing.JTextField();
        btAddCli = new javax.swing.JButton();
        btEditCli = new javax.swing.JButton();
        btDelCli = new javax.swing.JButton();
        lblObrigatorio1 = new javax.swing.JLabel();
        lblObrigatorio2 = new javax.swing.JLabel();
        lblObrigatorio3 = new javax.swing.JLabel();
        txtPesqCli = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);
        setPreferredSize(new java.awt.Dimension(640, 480));

        jPanel1.setBorder(javax.swing.BorderFactory.createBevelBorder(javax.swing.border.BevelBorder.RAISED));

        jPanel2.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel2.setFont(new java.awt.Font("Tahoma", 0, 12)); // NOI18N
        jLabel2.setText("Nome:");

        jLabel3.setText("Endereço:");

        jLabel5.setText("RG:");

        jLabel6.setText("CPF:");

        jLabel4.setText("Rua:");

        btAddCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/add.png"))); // NOI18N
        btAddCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btAddCliActionPerformed(evt);
            }
        });

        btEditCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/edit.png"))); // NOI18N
        btEditCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btEditCliActionPerformed(evt);
            }
        });

        btDelCli.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        btDelCli.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btDelCliActionPerformed(evt);
            }
        });

        lblObrigatorio1.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblObrigatorio1.setText("*");

        lblObrigatorio2.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblObrigatorio2.setText("*");

        lblObrigatorio3.setFont(new java.awt.Font("Tahoma", 1, 14)); // NOI18N
        lblObrigatorio3.setText("*");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(80, 80, 80)
                        .addComponent(btAddCli)
                        .addGap(72, 72, 72)
                        .addComponent(btEditCli)
                        .addGap(72, 72, 72)
                        .addComponent(btDelCli))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGap(33, 33, 33)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel5)
                            .addComponent(jLabel3)
                            .addComponent(jLabel2))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtRgCli, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(lblObrigatorio2)
                                        .addGap(23, 23, 23)
                                        .addComponent(jLabel6)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtCpfCli))
                                    .addGroup(jPanel2Layout.createSequentialGroup()
                                        .addComponent(txtEndCli, javax.swing.GroupLayout.PREFERRED_SIZE, 375, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                        .addComponent(txtRuaCli, javax.swing.GroupLayout.PREFERRED_SIZE, 74, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObrigatorio3))
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, 480, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lblObrigatorio1)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(txtNomeCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObrigatorio1))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtEndCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtRuaCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5)
                    .addComponent(txtRgCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6)
                    .addComponent(txtCpfCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lblObrigatorio2)
                    .addComponent(lblObrigatorio3))
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btEditCli)
                    .addComponent(btAddCli)
                    .addComponent(btDelCli))
                .addContainerGap())
        );

        jLabel1.setText("Pesquisar");

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "Title 1", "Title 2", "Title 3", "Title 4"
            }
        ));
        jScrollPane1.setViewportView(jTable1);

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(txtPesqCli, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel1)
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(jScrollPane1))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtPesqCli, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 167, Short.MAX_VALUE)
                .addGap(13, 13, 13)
                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        setBounds(0, 0, 640, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void btAddCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btAddCliActionPerformed
        // TODO add your handling code here:
        adicionar();
    }//GEN-LAST:event_btAddCliActionPerformed

    private void btEditCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btEditCliActionPerformed
        // TODO add your handling code here:
        alterar();
    }//GEN-LAST:event_btEditCliActionPerformed

    private void btDelCliActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btDelCliActionPerformed
        // TODO add your handling code here:
        remover();
    }//GEN-LAST:event_btDelCliActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btAddCli;
    private javax.swing.JButton btDelCli;
    private javax.swing.JButton btEditCli;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTable1;
    private javax.swing.JLabel lblObrigatorio1;
    private javax.swing.JLabel lblObrigatorio2;
    private javax.swing.JLabel lblObrigatorio3;
    private javax.swing.JTextField txtCpfCli;
    private javax.swing.JTextField txtEndCli;
    private javax.swing.JTextField txtNomeCli;
    private javax.swing.JTextField txtPesqCli;
    private javax.swing.JTextField txtRgCli;
    private javax.swing.JTextField txtRuaCli;
    // End of variables declaration//GEN-END:variables
}
