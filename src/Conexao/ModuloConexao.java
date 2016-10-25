/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Conexao;

import java.sql.*;
import javax.swing.JOptionPane;

/**
 *
 * @author Renan
 */
public class ModuloConexao {

    //Metodo responsavel por estabelecer a conexão com o banco de dados
    public static Connection conector() {
        java.sql.Connection conexao = null;
        //A linha abaixo chama o driver importado da biblioteca
        String driver = "com.mysql.jdbc.Driver";
        // A linha abaixo armazena informaçoes sobre o banco de dados
        String url = "jdbc:mysql://localhost:3306/Banco";
        String user = "root";
        String password = "";
        //Estabelecendo a conexao com o banco
        try {
            Class.forName(driver);
            conexao = DriverManager.getConnection(url, user, password);
            return conexao;
        } catch (Exception e) {
            //Linha abaixo serve de apoio caso haja erro
            //JOptionPane.showMessageDialog(null,e);
            return null;
        }
    }

}
