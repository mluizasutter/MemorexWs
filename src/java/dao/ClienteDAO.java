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
import modelo.Cliente;

/**
 *
 * @author marcelosiedler
 */
public class ClienteDAO {
    /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
    
    public ClienteDAO()
    {
    
    }
    
    public boolean inserir(Cliente cliente)
    {
        String sql = "INSERT INTO cliente(age,cc,nome,numcelular,email) VALUES(?,?,?,?,?)";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
            //Passa os dados
            pst.setString(1, cliente.getAge());
            pst.setString(2, cliente.getCc());
            pst.setString(3, cliente.getNome());
            pst.setString(4, cliente.getNumcelular());
            pst.setString(5, cliente.getEmail());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    public boolean atualizar(Cliente cliente)
    {
        String sql = "UPDATE cliente set age=?,cc=?,nomw=?,numcelular=?,email=? where age=? and cc=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
            //Passa os dados
            pst.setString(1, cliente.getAge());
            pst.setString(2, cliente.getCc());
            pst.setString(3, cliente.getNome());
            pst.setString(4, cliente.getNumcelular());
            pst.setString(5, cliente.getEmail());
            
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    public boolean excluir(Cliente cliente)
    {
        String sql = "DELETE FROM cliente where age=? and cc=?";
        Boolean retorno = false;
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
          
           
            pst.setString(1, cliente.getAge());
            pst.setString(2, cliente.getCc());
            if(pst.executeUpdate()>0)
            {
                retorno = true;
            }
                
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            retorno = false;
        }
        
        return retorno;
    
    }
    
    public List<Cliente> listar()
    {
         String sql = "SELECT * FROM cliente";
        List<Cliente> retorno = new ArrayList<Cliente>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
           
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Cliente item = new Cliente();
                item.setAge(res.getString("age"));
                item.setCc(res.getString("cc"));
                item.setNome(res.getString("nome"));
                item.setNumcelular(res.getString("numcelular"));
                item.setEmail(res.getString("email"));
                
                retorno.add(item);
            }
               
            
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }
    public Cliente buscar(Cliente cliente)
    {
        String age = cliente.getAge();
        String conta = cliente.getCc();
        
        // sql = sql.replace("$where", filtro);
        String sql = "SELECT age, cc, nome, numcelular, email  FROM cliente where age = " + age + " and cc = " + conta;
        System.out.println(sql);
        Cliente retorno = null;
        
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
                retorno = new Cliente();
                retorno.setAge(res.getString("age"));
                retorno.setCc(res.getString("cc"));
                retorno.setNome(res.getString("nome"));
                retorno.setNumcelular(res.getString("numcelular"));
                retorno.setEmail(res.getString("email"));
            
            }
            
        } catch (SQLException ex) {
            Logger.getLogger(ClienteDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }


}
