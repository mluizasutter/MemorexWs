/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ws;

import modelo.ManipuladorJson;
import javax.ws.rs.core.Context;
import javax.ws.rs.core.UriInfo;
import javax.ws.rs.Produces;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PUT;
import javax.ws.rs.PathParam;
import javax.ws.rs.core.MediaType;

/**
 * REST Web Service
 *
 * @author Sutter
 */
@Path("memorex")
public class MemorexWebServ {
    

    @Context
    private UriInfo context;
    
    /**
     * Creates a new instance of MemorexWebServ
     */
    public MemorexWebServ() { }

    /**
     * Retrieves representation of an instance of ws.MemorexWebServ
     * @return an instance of java.lang.String
     */
 //@notation para listar os dados da tabela
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("memorex/dadositemMeses/{age}/{cc}")
   public String listMesesItem(@PathParam("age") String age, 
                               @PathParam("cc") String cc) {
        ManipuladorJson itemJson = new ManipuladorJson();
        
        String retorno = "";
        
        retorno = itemJson.jsonMountMeses(age,cc);
                 
        //itemJson.loadDia(retorno);
        return retorno;
   }
   
   //@notation para listar os dados da tabela
   @GET
   @Produces(MediaType.APPLICATION_JSON)
   @Path("memorex/dadositemMes/{age}/{cc}")
   public String listMesItem(@PathParam("age") String age, 
                             @PathParam("cc") String cc) {
        ManipuladorJson itemJson = new ManipuladorJson();
        
        String retorno = "";
        
        retorno = itemJson.jsonMountMes(age,cc);
                 
        //itemJson.loadDia(retorno);
        return retorno;
    }
 
    //@notation para listar os dados da tabela
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("memorex/dadositemBusca/{age}/{cc}/{data}")
    public String listItems(@PathParam("age") String age, 
                            @PathParam("cc") String cc,
                            @PathParam("data") String data) {
        
        
        ManipuladorJson itemJson = new ManipuladorJson();
        
        String retorno = "";
        
        retorno = itemJson.jsonMountMesBusca(age,cc,data);
                 
        //itemJson.loadDia(retorno);
        return retorno;
    }

         
    // MEMOREX/DOCUMENTO/TÍTULO
    @GET
    @Produces(MediaType.APPLICATION_JSON)
    @Path("memorex/documento/{idDoc}")
    public String getDocumento(@PathParam("idDoc") String idDoc) {
        ManipuladorJson itemJson = new ManipuladorJson();

        String retornoDoc = "";
        
        retornoDoc = itemJson.jsonMountDoc(Integer.parseInt(idDoc));
              
        return retornoDoc;
    }

           
   
    /**
     * PUT method for updating or creating an instance of MemorexWebServ
     * @param content representation for the resource
     */
    @PUT
    @Consumes(MediaType.APPLICATION_JSON)
    public void putJson(String content) {
    }
}
