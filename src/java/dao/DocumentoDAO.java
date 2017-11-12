
package dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import modelo.Cliente;
import modelo.Documento;

public class DocumentoDAO {

    public DocumentoDAO() {
    }

    //MÉTODO SELECT DOCUMENTO  
    public Documento selectDocumento(int idDocumento) {
        
        Documento retorno = null;
   
        String sql = "select d.iddoc as 'id', d.idcliente, d.numero as 'nome',d.emissor as 'emissor', d.valor as 'valor', date_format(d.datavencto,'%d/%m/%Y') as 'data', d.codigobarra as 'codigobarra'  from documento d where iddoc = ?;";    
        
        try {
            PreparedStatement preparador = Conexao.getPreparedStatement(sql);
            preparador.setInt(1, idDocumento);           
            ResultSet resultado = preparador.executeQuery();
            
            resultado.next();
                    
            retorno = new Documento();
                                   
            retorno.setIdDoc(resultado.getInt("id"));
            retorno.setIdCliente(resultado.getInt("idcliente"));
            retorno.setNossoNumero(resultado.getLong("nome"));
            retorno.setEmissor(resultado.getString("emissor"));
            retorno.setValor(resultado.getFloat("valor"));
            retorno.setDtVcto(resultado.getString("data"));             
            retorno.setCodigoBarras(resultado.getString("codigobarra"));
            //retorno.setMotivo(resultado.getInt("codmotivo"));
            //retorno.setDtAgenda(resultado.getDate("dataagenda"));            


            sql = "select age, cc, nome from cliente where idCliente = ?";              
        
            preparador = Conexao.getPreparedStatement(sql);
            preparador.setString(1, Integer.toString(resultado.getInt("idcliente")));
            resultado = preparador.executeQuery();
            
            resultado.next();                               
            retorno.setAge(resultado.getString("age"));
            retorno.setCc(resultado.getString("cc"));
            retorno.setNomeCli(resultado.getString("nome"));
 
            preparador.close();
            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o documento!! Erro: " + e.getMessage());
        }        
        return retorno;
                
    }
    
    /*
    // BUSCAR DADOS CLIENTE POR ID PARA JASON DOCUMENTO
    public Documento selectClienteId(String idCliente){
        
        Documento retorno = null;   
        String sql = "select * from cliente where idCliente = ?";    
        
        try {
            PreparedStatement preparador = Conexao.getPreparedStatement(sql);
            preparador.setString(1, idCliente);
            ResultSet resultado = preparador.executeQuery();
            
            resultado.next();
                    
            retorno = new Documento();
                               
            retorno.setAge(resultado.getString("age"));
            retorno.setCc(resultado.getString("cc"));
            retorno.setNomeCli(resultado.getString("nome"));
             
            preparador.close();
            
        } catch (SQLException e) {
            System.out.println("Erro ao consultar o documento!! Erro: " + e.getMessage());
        }
        
        return retorno;
    } */
}
