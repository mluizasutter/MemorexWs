/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;


public class Conexao {
    
 public static Connection con = null;
 
 public static Connection getConexao() {
      System.out.println("Conectando ao banco...");
      try {
          Class.forName("com.mysql.jdbc.Driver");
          con =  DriverManager.getConnection("jdbc:mysql://127.0.0.1/memorex","memorex","memorex");
          System.out.println("Conectado.");
      } catch (ClassNotFoundException ex) {
      System.out.println("Classe não encontrada, adicione o driver nas bibliotecas.");
      Logger.getLogger(Conexao.class.getName()).log(Level.SEVERE, null, ex);
    } catch(SQLException e) {
        System.out.println(e);
        throw new RuntimeException(e);    
    } 
    return con;
 }
 
 public static PreparedStatement getPreparedStatement(String sql){
        // testo se a conexão já foi criada
        if (con == null){
            // cria a conexão
            con = getConexao();
        }
        try {
            // retorna um objeto java.sql.PreparedStatement
            return con.prepareStatement(sql);
        } catch (SQLException e){
            System.out.println("Erro de sql: "+
                    e.getMessage());
        }
        return null;
    }
}