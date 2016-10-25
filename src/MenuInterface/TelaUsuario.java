/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package MenuInterface;

/**
 *
 * @author Renan
 */
import java.sql.*;
import Conexao.ModuloConexao;
import javax.swing.JOptionPane;

public class TelaUsuario extends javax.swing.JInternalFrame {

    Connection conexao = null;
    PreparedStatement pst = null;
    ResultSet rs = null;

    /**
     * Creates new form TelaUsuario
     */
    //o codigo abaixo é um construtor
    public TelaUsuario() {
        initComponents();
        conexao = ModuloConexao.conector();
    }

    //Metodo abaixo consulta os usuarios existentes
    private void consultar() {
        //A estrutura abaixo verifica campos necessarios para pesquisa de usuarios
        if (txtUsuID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o Campo 'ID User' para Localizar os Usuários");
        } else {

            String sql = "select * from usuarios where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuID.getText());
                rs = pst.executeQuery();
                if (rs.next()) {
                    txtUsuNome.setText(rs.getString(2));
                    txtUsuFone.setText(rs.getString(3));
                    txtUsuLogin.setText(rs.getString(4));
                    txtUsuSenha.setText(rs.getString(5));
                    //a linha abaixo é o comando do combobox/rs.getString(6) 
                    //é referente ao campo perfil por isso a formatação da letra
                    //deve ser identica á escrita no banco de dados.
                    cbPerfil.setSelectedItem(rs.getString(6));

                } else {
                    JOptionPane.showMessageDialog(null, "Usuário não Cadastrado");

                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    cbPerfil.setSelectedItem("------");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //Metodo abaixo adiciona usuarios
    private void adicionar() {
        //A estrutura abaixo verifica os campos necessarios para a adição de novo usuario
        if (txtUsuID.getText().isEmpty() || txtUsuNome.getText().isEmpty() || txtUsuFone.getText().isEmpty()
                || txtUsuLogin.getText().isEmpty() || txtUsuSenha.getText().isEmpty()
                || cbPerfil.getSelectedItem().equals(null)) {
            JOptionPane.showMessageDialog(null, "Preencha Todos os Campos");
        } else {
            //Esta instrução serve para inserir dados ao banco de dados
            // no caso de values (?) corresponde aos campos que sera preenchido 
            String sql = "insert into usuarios(iduser,usuario,fone,login,senha,perfil) values(?,?,?,?,?,?)";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuID.getText());
                pst.setString(2, txtUsuNome.getText());
                pst.setString(3, txtUsuFone.getText());
                pst.setString(4, txtUsuLogin.getText());
                pst.setString(5, txtUsuSenha.getText());
                //O combobox deve ser convertido para String por isso é usado
                //.toString();
                pst.setString(6, cbPerfil.getSelectedItem().toString());
                //o comando abaixo atualiza a tabela de usuarios com os dados do formulario
                //pst.executeUpdate();
                //A estrutura abaixo informa ao usuario a confirmação de cadastro
                int adicionado = pst.executeUpdate();
                System.out.println(adicionado);
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Usuário Cadastrado Com Sucesso!");
                    txtUsuID.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuSenha.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuLogin.setText(null);
                    cbPerfil.setSelectedItem("------");
                }
            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //Metodo para alterar dados do usuario
    private void alterar() {
        //A estrutura abaixo localiza o campo necessario para a alteração
        if (txtUsuID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o Campo'ID User' para Alteração");
        } else {

            String sql = "update usuarios set usuario=?,fone=?,login=?,senha=?,perfil=? where iduser=?";
            try {
                pst = conexao.prepareStatement(sql);
                pst.setString(1, txtUsuNome.getText());
                pst.setString(2, txtUsuFone.getText());
                pst.setString(3, txtUsuLogin.getText());
                pst.setString(4, txtUsuSenha.getText());
                pst.setString(5, cbPerfil.getSelectedItem().toString());
                pst.setString(6, txtUsuID.getText());

                //A estrutura abaixo informa ao usuario a confirmação de cadastro
                int adicionado = pst.executeUpdate();
                if (adicionado > 0) {
                    JOptionPane.showMessageDialog(null, "Dados Alterados com Sucesso!");
                    txtUsuID.setText(null);
                    txtUsuNome.setText(null);
                    txtUsuFone.setText(null);
                    txtUsuLogin.setText(null);
                    txtUsuSenha.setText(null);
                    cbPerfil.setSelectedItem("------");
                } else {
                    JOptionPane.showMessageDialog(null, "Usuário Invalido para Alteração");
                }

            } catch (Exception e) {
                JOptionPane.showMessageDialog(null, e);
            }
        }
    }

    //Metodo para remoção de Usuários
    private void remover() {
        //A estrutura abaixo verifica o campo necessario para remoção de usuarios
        if (txtUsuID.getText().isEmpty()) {
            JOptionPane.showMessageDialog(null, "Preencha o Campo'ID User'para Remoção");
        } else {

            //a estrutura abaixo confirma a remoção do usuario
            int confirma = JOptionPane.showConfirmDialog(null, "Tem Certeza que Deseja Remover o Usuário", "Atenção", JOptionPane.YES_NO_OPTION);
            if (confirma == JOptionPane.YES_OPTION) {
                String Sql = "delete from usuarios where iduser=?";
                try {
                    pst = conexao.prepareStatement(Sql);
                    pst.setString(1, txtUsuID.getText());
                    int apagado = pst.executeUpdate();
                    if (apagado > 0) {
                        JOptionPane.showMessageDialog(null, "Usuário '" +rs.getString(2)+ "' Removido com Sucesso");
                        txtUsuID.setText(null);
                        txtUsuNome.setText(null);
                        txtUsuFone.setText(null);
                        txtUsuLogin.setText(null);
                        txtUsuSenha.setText(null);
                        cbPerfil.setSelectedItem("------");
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
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        txtUsuID = new javax.swing.JTextField();
        txtUsuNome = new javax.swing.JTextField();
        txtUsuLogin = new javax.swing.JTextField();
        cbPerfil = new javax.swing.JComboBox<>();
        jLabel7 = new javax.swing.JLabel();
        txtUsuFone = new javax.swing.JTextField();
        jButton1 = new javax.swing.JButton();
        jButton2 = new javax.swing.JButton();
        jButton3 = new javax.swing.JButton();
        jButton4 = new javax.swing.JButton();
        txtUsuSenha = new javax.swing.JTextField();

        setClosable(true);
        setIconifiable(true);
        setMaximizable(true);

        jPanel1.setBorder(javax.swing.BorderFactory.createEtchedBorder());

        jLabel1.setText("ID User:");

        jLabel2.setText("Nome:");

        jLabel3.setText("Login:");

        jLabel4.setText("Senha:");

        jLabel5.setText("Perfil:");

        txtUsuID.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtUsuIDActionPerformed(evt);
            }
        });

        cbPerfil.setModel(new javax.swing.DefaultComboBoxModel<>(new String[] { "------", "admin", "user", "Tecnico" }));

        jLabel7.setText("Fone:");

        jButton1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/edit.png"))); // NOI18N
        jButton1.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton1ActionPerformed(evt);
            }
        });

        jButton2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/delete.png"))); // NOI18N
        jButton2.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton2ActionPerformed(evt);
            }
        });

        jButton3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/add.png"))); // NOI18N
        jButton3.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton3ActionPerformed(evt);
            }
        });

        jButton4.setIcon(new javax.swing.ImageIcon(getClass().getResource("/Icones/finder.png"))); // NOI18N
        jButton4.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                jButton4ActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(6, 6, 6)
                        .addComponent(jButton3, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton1, javax.swing.GroupLayout.PREFERRED_SIZE, 135, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton4, javax.swing.GroupLayout.PREFERRED_SIZE, 137, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jButton2, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGap(19, 19, 19)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jLabel1)
                            .addComponent(jLabel2)
                            .addComponent(jLabel3)
                            .addComponent(jLabel7))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(txtUsuID, javax.swing.GroupLayout.PREFERRED_SIZE, 45, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(0, 0, Short.MAX_VALUE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                                    .addComponent(txtUsuFone, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 186, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, 185, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 75, Short.MAX_VALUE)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel4)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                                        .addComponent(jLabel5)
                                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                        .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, 196, javax.swing.GroupLayout.PREFERRED_SIZE))))
                            .addComponent(txtUsuNome))))
                .addContainerGap(31, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jLabel1)
                    .addComponent(txtUsuID, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuNome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel2))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtUsuLogin, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4)
                    .addComponent(txtUsuSenha, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtUsuFone, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addComponent(jLabel5)
                    .addComponent(cbPerfil, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(36, 36, 36)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jButton1)
                    .addComponent(jButton3)
                    .addComponent(jButton4)
                    .addComponent(jButton2))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(191, Short.MAX_VALUE))
        );

        setBounds(0, 0, 641, 480);
    }// </editor-fold>//GEN-END:initComponents

    private void txtUsuIDActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtUsuIDActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_txtUsuIDActionPerformed

    private void jButton4ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton4ActionPerformed
        // acão chama o metodo para consultar usuários
        consultar();
    }//GEN-LAST:event_jButton4ActionPerformed

    private void jButton3ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton3ActionPerformed
        // ação chama o metodo para criação de usuários
        adicionar();
    }//GEN-LAST:event_jButton3ActionPerformed

    private void jButton1ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton1ActionPerformed
        // ação chama o metodo para altarar dados do usuário
        alterar();
    }//GEN-LAST:event_jButton1ActionPerformed

    private void jButton2ActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_jButton2ActionPerformed
        // ação chama o metodo para remoção de usuários
        remover();
    }//GEN-LAST:event_jButton2ActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JComboBox<String> cbPerfil;
    private javax.swing.JButton jButton1;
    private javax.swing.JButton jButton2;
    private javax.swing.JButton jButton3;
    private javax.swing.JButton jButton4;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JTextField txtUsuFone;
    private javax.swing.JTextField txtUsuID;
    private javax.swing.JTextField txtUsuLogin;
    private javax.swing.JTextField txtUsuNome;
    private javax.swing.JTextField txtUsuSenha;
    // End of variables declaration//GEN-END:variables
}
