/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import modelo.Notificacao;

/**
 *
 * @author marcelosiedler
 */
public class NotificacaoDAO {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
    public NotificacaoDAO() {  }
    
   
    public boolean atualizar(Notificacao notificacao)
    {
        String sql = "UPDATE notificacao, notificacao_envio set notificacao_envio.datahorarecebimento = curdate() where notificacao. ";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
            //Passa os dados
            pst.setString(1, notificacao.getAge());
          
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    public boolean excluir(Notificacao notificacao)
    {
        String sql = "DELETE FROM cliente where age=? and cc=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
           
            pst.setString(1, notificacao.getAge());
           
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    
    public List<Notificacao> listar()
    {
         String sql = "SELECT * FROM cliente";
        List<Notificacao> retorno = new ArrayList<Notificacao>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Notificacao item = new Notificacao();
                item.setAge(res.getString("age"));
                
                retorno.add(item);
            }
               
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }
    public Notificacao buscar(Notificacao cliente)
    {
        String age = cliente.getAge();
              
        // sql = sql.replace("$where", filtro);
        String sql = "SELECT age, cc, nome, numcelular, email  FROM cliente where age = " + age ;
        System.out.println(sql);
        Notificacao retorno = null;
        
        PreparedStatement pst;
        pst = Conexao.getPreparedStatement(sql);
        try {
           
            //pst.setString(1, cliente.getAge());
            //pst.setString(2, cliente.getCc());
            ResultSet res = pst.executeQuery();
            System.out.println(sql);
            
            if(res.next())
            {
                //Passa os dados
                retorno = new Notificacao();
                retorno.setAge(res.getString("age"));
               
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }


}
