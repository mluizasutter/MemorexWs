/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import java.text.SimpleDateFormat;
import modelo.Dia;
import modelo.Item;
import modelo.ManipuladorJson;
import modelo.Mes;
import org.json.JSONObject;


/**
 *
 * @author Sutter
 */
public class ItemDAO {
    
    public List<Item> listarJson(String age, String conta, int mes, int ano, int dia)
    {
                        
        String sql = "SELECT " +
                     "d.iddoc as 'id'," + 
                     "day(d.datavencto) as 'dia'," +
                     "date_format(d.datavencto,\"%d/%m/%Y\") as 'datavencto'," +
                     "d.numero," +       
                     "d.emissor," +
                     "d.valor," +
                     "d.codigobarra," +
                     "e.codmotivo," +
                     "e.dscmotivo," +
                     "g.idgrupo," +
                     "g.nomegrupo," +
                     "a.idsinal," +
                     "a.nome as 'nomesinal'" +
                     " FROM documento d" +
                     " left join cliente c on (c.idcliente = d.idcliente) " +
                     " left join motivo e on (e.codmotivo = d.codmotivo)" +
                     " left join notificacao n on (n.idnotificacao = d.idnotificacao)" +
                     " left join grupo g on (g.idgrupo = n.idgrupo)" +
                     " left join aparencia a on (a.idsinal = d.idsinal)"
                     + " where c.age = ? and c.cc = ? and d.mes = ? and d.ano = ? and day(d.datavencto) = ? order by d.datavencto;";
        
        List<Item> retorno = new ArrayList<Item>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
              
            pst.setString(1, age);
            pst.setString(2, conta);
            pst.setInt(3, mes);
            pst.setInt(4, ano);
            pst.setInt(5, dia);
            //System.out.println(sql);
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Item items = new Item();
                items.setIddoc(res.getInt("id"));     
                items.setDia(res.getString("dia"));
                items.setDatavenc(res.getString("datavencto"));
                items.setNumero(res.getInt("numero"));
                items.setEmissor(res.getString("emissor"));
                items.setValor(res.getDouble("valor"));
                items.setCodigoBarra(res.getString("codigoBarra"));
                items.setCodmotivo(res.getInt("codmotivo"));
                items.setDscmotivo(res.getString("dscmotivo"));
                items.setIdgrupo(res.getInt("idgrupo"));
                items.setNomegrupo(res.getString("nomegrupo"));
                items.setIdsinal(res.getInt("idsinal"));                
                               
                retorno.add(items);
            }
                          
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }
    
    public List<Item> listar(String age, String conta, int mes, int ano)
    {
                        
        String sql = "SELECT " +
                     "d.mes," +
                     "d.ano," +
                     "c.age," +
                     "c.cc as 'conta'," +
                     "d.iddoc as 'id'," + 
                     "day(d.datavencto) as 'dia'," +
                     "date_format(d.datavencto,\"%d/%m/%Y\") as 'datavencto'," +
                     "d.numero," +       
                     "d.emissor," +
                     "d.valor," +
                     "d.codigobarra," +
                     "e.codmotivo," +
                     "e.dscmotivo," +
                     "g.idgrupo," +
                     "g.nomegrupo," +
                     "a.idsinal," +
                     "a.nome as 'nomesinal'" +
                     " FROM documento d" +
                     " left join cliente c on (c.idcliente = d.idcliente) " +
                     " left join motivo e on (e.codmotivo = d.codmotivo)" +
                     " left join notificacao n on (n.idnotificacao = d.idnotificacao)" +
                     " left join grupo g on (g.idgrupo = n.idgrupo)" +
                     " left join aparencia a on (a.idsinal = d.idsinal)"
                     + " where c.age = ? and c.cc = ? and d.mes = ? and d.ano = ?;";
        //System.out.println(sql);
        List<Item> retorno = new ArrayList<Item>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
              
            pst.setString(1, age);
            pst.setString(2, conta);
            pst.setInt(3, mes);
            pst.setInt(4, ano);
            ResultSet res = pst.executeQuery();
            
            while(res.next())
            {
                Item items = new Item();
                items.setMes(res.getString("mes"));
                items.setAno(res.getString("ano"));
                items.setAge(res.getString("age"));
                items.setConta(res.getString("conta"));
                items.setIddoc(res.getInt("id"));     
                items.setDia(res.getString("dia"));
                items.setDatavenc(res.getString("datavencto"));
                items.setNumero(res.getInt("numero"));
                items.setEmissor(res.getString("emissor"));
                items.setValor(res.getDouble("valor"));
                items.setCodigoBarra(res.getString("codigoBarra"));
                items.setCodmotivo(res.getInt("codmotivo"));
                items.setDscmotivo(res.getString("dscmotivo"));
                items.setIdgrupo(res.getInt("idgrupo"));
                items.setNomegrupo(res.getString("nomegrupo"));
                items.setIdsinal(res.getInt("idsinal"));
                items.setNomeSinal(res.getString("nomesinal"));
                               
                retorno.add(items);
            }
                          
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;
    
    
    }
    
    public List<Item> buscar(String age, String conta, String dataParam) {
        
        String data = dataParam.substring(4, 8) + dataParam.substring(2, 4) + dataParam.substring(0, 2);
       
        String sql = "SELECT " +
                     "d.mes," +
                     "d.ano," +
                     "c.age," +
                     "c.cc as 'conta'," +
                     "d.iddoc as 'id'," + 
                     "day(d.datavencto) as 'dia'," +
                     "date_format(d.datavencto,\"%d/%m/%Y\") as 'datavencto'," +
                     "d.numero," +       
                     "d.emissor," +
                     "d.valor," +
                     "d.codigobarra," +
                     "e.codmotivo," +
                     "e.dscmotivo," +
                     "g.idgrupo," +
                     "g.nomegrupo," +
                     "a.idsinal," +
                     "a.nome as 'nomesinal'" +
                     " FROM documento d" +
                     " left join cliente c on (c.idcliente = d.idcliente) " +
                     " left join motivo e on (e.codmotivo = d.codmotivo)" +
                     " left join notificacao n on (n.idnotificacao = d.idnotificacao)" +
                     " left join grupo g on (g.idgrupo = n.idgrupo)" +
                     " left join aparencia a on (a.idsinal = d.idsinal)"
                     + " where c.age = ? and c.cc = ? and d.datavencto = date(?);";
        
        List<Item> retorno = new ArrayList<Item>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
                      
            pst.setString(1, age);
            pst.setString(2, conta);
            pst.setString(3, data);
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Item items = new Item();
                items.setMes(res.getString("mes"));
                items.setAno(res.getString("ano"));
                items.setAge(res.getString("age"));
                items.setConta(res.getString("conta"));
                items.setIddoc(res.getInt("id")); 
                items.setDia(res.getString("dia"));
                items.setDatavenc(res.getString("datavencto"));
                items.setNumero(res.getInt("numero"));
                items.setEmissor(res.getString("emissor"));
                items.setValor(res.getDouble("valor"));
                items.setCodigoBarra(res.getString("codigoBarra"));
                items.setCodmotivo(res.getInt("codmotivo"));
                items.setDscmotivo(res.getString("dscmotivo"));
                items.setIdgrupo(res.getInt("idgrupo"));
                items.setNomegrupo(res.getString("nomegrupo"));
                items.setIdsinal(res.getInt("idsinal"));
                items.setNomeSinal(res.getString("nomesinal"));
                               
                retorno.add(items);
            }
          } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
       }
       
       return retorno;  
    
    }
    
    public List<Mes> buscaMesesAno(String age, String conta)  { 
        String sql = "SELECT d.mes, d.ano from documento d left join cliente c on (c.idcliente = d.idcliente) where c.age = ? and c.cc = ? group by mes, ano;";
               
        List<Mes> retorno = new ArrayList<Mes>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
                      
            pst.setString(1, age);
            pst.setString(2, conta);
           
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Mes items = new Mes();
                items.setMes(res.getInt("mes"));
                items.setAno(res.getInt("ano"));              
                               
                retorno.add(items);
            }             
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        
        return retorno;        
            
    }
    
    public Mes buscaMesAno(String age, String conta)  { 
        
        String sql = "SELECT d.mes, d.ano from documento d left join cliente c on (c.idcliente = d.idcliente) where c.age = ? and c.cc = ? and d.mes = month(curdate()) group by mes, ano limit 1;";
        Mes retorno = new Mes();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
                      
            pst.setString(1, age);
            pst.setString(2, conta);
           
            ResultSet res = pst.executeQuery();
            while(res.next())            {
                
                retorno.setMes(res.getInt("mes"));
                retorno.setAno(res.getInt("ano"));        
                 
            }          
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return retorno;        
            
    }
    
    
    public List<Dia> buscaDias(String age, String conta, int mes, int ano)  { 
        String sql = "SELECT d.mes, d.ano, extract(day from d.datavencto) as 'dia' from documento d left join cliente c on (c.idcliente = d.idcliente) " +
                     " where c.age = ? and c.cc = ? and d.mes = ? and d.ano = ? group by d.mes, d.ano, dia;";
        
        List<Dia> retorno = new ArrayList<Dia>();
        
        PreparedStatement pst = Conexao.getPreparedStatement(sql);
        try {
                      
            pst.setString(1, age);
            pst.setString(2, conta);
            pst.setInt(3, mes);
            pst.setInt(4, ano);  
            //System.out.println(sql);
            
            ResultSet res = pst.executeQuery();
            while(res.next())
            {
                Dia items = new Dia();
                items.setMes(res.getInt("mes"));
                items.setAno(res.getInt("ano")); 
                items.setDia(res.getInt("dia")); 
                               
                retorno.add(items);
            }             
            
            
        } catch (SQLException ex) {
            Logger.getLogger(NotificacaoDAO.class.getName()).log(Level.SEVERE, null, ex);
            
        }
        return retorno;        
            
    }  
    
   
       
}
