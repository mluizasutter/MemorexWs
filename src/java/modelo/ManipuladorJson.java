/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

import com.google.gson.JsonObject;
import dao.DocumentoDAO;
import dao.ItemDAO;
import java.io.StringWriter;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;
import java.util.NoSuchElementException;
import modelo.Mes;
import modelo.Dia;
import modelo.Item;
import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;
import org.json.JSONWriter;
import modelo.Common;

/**
 *
 * @author Sutter
 */
public class ManipuladorJson {
    protected Common common;

         
    public static String jsonMountMeses(String age, String cc) {
       JSONObject obj;  
       JSONArray  objtitu    = new JSONArray();
       JSONObject objtitulos;
       JSONArray  objmain    = new JSONArray(); 
       JSONObject objdia;
       JSONArray  objdias    = new JSONArray();       
              
       ItemDAO buscas = new ItemDAO();
                    
       List<Mes>  listames  = new ArrayList<Mes>();
       List<Item> listaitem = new ArrayList<Item>();
       List<Dia>  listadias = new ArrayList<Dia>();
        
       listames = buscas.buscaMesesAno(age, cc);       
		 
       for (Iterator iterator = listames.iterator(); iterator.hasNext();) {
               Mes Lista1 = (Mes) iterator.next();
               int mesjson, anojson, dia;
               
               mesjson = Lista1.getMes();
               anojson = Lista1.getAno();
               obj = new JSONObject();               
                              
               listadias = buscas.buscaDias(age, cc, mesjson, anojson); 
               objdias = new JSONArray();
               
               for (Iterator itemdias = listadias.iterator(); itemdias.hasNext();) {
                   Dia Lista3 = (Dia) itemdias.next();
                   dia = Lista3.getDia();
                   
                   //System.out.println(mesjson + " / " + anojson + " / " + dia );
                   
                   listaitem = buscas.listarJson(age, cc, mesjson, anojson, dia); 
                   
                   objdia = new JSONObject();
                   objdia.put("dia",dia);
                   
                   objtitu = new JSONArray();
                   
                   for (Iterator itemlista = listaitem.iterator(); itemlista.hasNext();) {
                        Item Lista2 = (Item) itemlista.next();
                        objtitulos = new JSONObject();
                        //System.out.println(Lista2.getIddoc());                                   

                        objtitulos.put("id"      , Lista2.getIddoc()); 
                        objtitulos.put("sinal"   , Lista2.getIdsinal());
                        objtitulos.put("grupo"   , Lista2.getIdgrupo());
                        objtitulos.put("motivo"  , Lista2.getDscmotivo());
                        objtitulos.put("nome" , Lista2.getEmissor());
                        objtitulos.put("valor"   , Lista2.getValor()); 

                        objtitu.put(objtitulos);
                  
                    } 
                   objdia.put("titulos",objtitu);
                   objdias.put(objdia);                     
               }
               obj = new JSONObject();               
               //*obj.put("titulos",objtitu); 
               obj.put("mes",mesjson);
               obj.put("ano",anojson);
               obj.put("dias",objdias); 
                        
               
               objmain.put(obj);
                          
       }          
      
      //System.out.println(objmain.toString());      
      return objmain.toString();
        
    }
    
    
    public static String jsonMountMes(String age, String cc) {
       JSONObject obj;  
       JSONArray  objtitu    = new JSONArray();
       JSONObject objtitulos;      
       JSONObject objdia;
       JSONArray  objdias    = new JSONArray();       
              
       ItemDAO buscas = new ItemDAO();
                    
       Mes listames  = new Mes();
       List<Item> listaitem = new ArrayList<Item>();
       List<Dia>  listadias = new ArrayList<Dia>();
        
       listames = buscas.buscaMesAno(age, cc);   
       
       int mesjson, anojson, dia;
               
       mesjson = listames.getMes();
       anojson = listames.getAno();
       obj = new JSONObject();               
                              
       listadias = buscas.buscaDias(age, cc, mesjson, anojson);
       objdias = new JSONArray();

       for (Iterator itemdias = listadias.iterator(); itemdias.hasNext();) {
            Dia Lista3 = (Dia) itemdias.next();
            dia = Lista3.getDia();

            //System.out.println(mesjson + " / " + anojson + " / " + dia );
            listaitem = buscas.listarJson(age, cc, mesjson, anojson, dia);

            objdia = new JSONObject();
            objdia.put("dia", dia);

            objtitu = new JSONArray();

            for (Iterator itemlista = listaitem.iterator(); itemlista.hasNext();) {
                Item Lista2 = (Item) itemlista.next();
                objtitulos = new JSONObject();
                //System.out.println(Lista2.getIddoc());

                objtitulos.put("id", Lista2.getIddoc());
                objtitulos.put("sinal", Lista2.getIdsinal());
                objtitulos.put("grupo", Lista2.getIdgrupo());
                objtitulos.put("motivo", Lista2.getDscmotivo());
                objtitulos.put("nome", Lista2.getEmissor());
                objtitulos.put("valor", Lista2.getValor());

                objtitu.put(objtitulos);

            }
            objdia.put("titulos", objtitu);
            objdias.put(objdia);
      }
      obj = new JSONObject();
       
      obj.put("mes", mesjson);
      obj.put("ano", anojson);
      obj.put("dias", objdias);

      //objmain.put(obj);
      //System.out.println(obj.toString());      
      return obj.toString();
        
    }
    
     public static String jsonMountMesBusca(String age, String cc, String busca) {
       JSONObject obj;  
       JSONArray  objtitu    = new JSONArray();
       JSONObject objtitulos;      
       JSONObject objdia;
       JSONArray  objdias    = new JSONArray(); 
       ItemDAO buscas = new ItemDAO();
       int mesjson, anojson, dia;
       
       String mesData = busca.substring(2, 4);
       String diaData = busca.substring(0, 2);
       String anoData = busca.substring(4, 8);
       
       Mes listames  = new Mes();
       List<Item> listaitem = new ArrayList<Item>();
                 
       mesjson = Integer.parseInt(mesData);
       anojson = Integer.parseInt(anoData);
       
       objdias = new JSONArray();
       objdia = new JSONObject();
       
       listaitem = buscas.listarJson(age, cc, mesjson, anojson, Integer.parseInt(diaData));
       
       objdia.put("dia", diaData);

       objtitu = new JSONArray();

            for (Iterator itemlista = listaitem.iterator(); itemlista.hasNext();) {
            Item Lista2 = (Item) itemlista.next();
            objtitulos = new JSONObject();
            //System.out.println(Lista2.getIddoc());

            objtitulos.put("id", Lista2.getIddoc());
            objtitulos.put("sinal", Lista2.getIdsinal());
            objtitulos.put("grupo", Lista2.getIdgrupo());
            objtitulos.put("motivo", Lista2.getDscmotivo());
            objtitulos.put("nome", Lista2.getEmissor());
            objtitulos.put("valor", Lista2.getValor());

            objtitu.put(objtitulos);

      }
      objdia.put("titulos", objtitu);
      objdias.put(objdia);
    
      obj = new JSONObject();
       
      obj.put("mes", mesjson);
      obj.put("ano", anojson);
      obj.put("dias", objdias);

      return obj.toString();
        
    }
    
    
    public static String jsonMountDoc(int idDoc) {
       JSONObject obj = null;                  
              
       Documento doc = new Documento();
       DocumentoDAO docDAO = new DocumentoDAO();
       
       doc = docDAO.selectDocumento(idDoc);
       
       obj = new JSONObject();               
               
       obj.put("id", doc.getIdDoc()); 
       obj.put("nome", doc.getNossoNumero());
       obj.put("emissor", doc.getEmissor());
       obj.put("valor", doc.getValor());
       obj.put("data", doc.getDtVcto());
       obj.put("codigobarra", doc.getCodigoBarras()); 
        
       return obj.toString();
        
    }
   
    
    
    /****
     * Classes de teste para o simular classes Android que consomem o retorno do JSON
     * @param jSonParam
     * @throws JSONException 
     */
    public void loadDia(String jSonParam) throws JSONException {
        String json = jSonParam;
        if (common.mes == null)
            common.mes = ManipuladorJson.jsonMesToBase(json);
        //System.out.println("Classe json" + common.mes);
    }
    
    public void loadDiaMeses(String jSonParam) throws JSONException {
        String json = jSonParam;
        if (common.mes == null)
            common.mes = ManipuladorJson.jsonMesesToBase(json);
        //System.out.println("Classe json" + common.mes);
    }
    
     public static Mes jsonMesesToBase(String jsonText) throws JSONException{
        Mes mes = null;

        JSONArray json_lstmes = new JSONArray(jsonText);
        
          
        for (int i1 = 0; i1 < json_lstmes.length(); i1++) {
            JSONObject json_mes = (JSONObject) json_lstmes.get(i1);
            
            if (json_mes != null) {

                mes = new Mes();
                mes.setMes(json_mes.getInt("mes"));
                mes.setAno(json_mes.getInt("ano"));

                JSONArray json_dias = json_mes.getJSONArray("dias");

                for (int i = 0; i < json_dias.length(); i++) {
                    JSONObject json_dia = (JSONObject) json_dias.get(i);

                    Dia dia = new Dia();
                    dia.setDia(json_dia.getInt("dia"));
                    dia.setMes(json_mes.getInt("mes"));
                    dia.setAno(json_mes.getInt("ano"));

                    mes.getDias().add(dia);

                    JSONArray json_titulos = json_dia.getJSONArray("titulos");

                    for (int j = 0; j < json_titulos.length(); j++) {
                        JSONObject json_titulo = (JSONObject) json_titulos.get(j);

                        Item item = new Item();
                        item.setIddoc(json_titulo.getInt("id"));
                        item.setIdsinal(json_titulo.getInt("sinal"));
                        item.setIdgrupo(json_titulo.getInt("grupo"));
                        item.setEmissor(json_titulo.getString("nome"));
                        item.setValor(json_titulo.getDouble("valor"));

                        dia.getItens().add(item);

                    }
                }
            }
        }
        return mes;
    }
     
    public static Mes jsonMesToBase(String jsonText) throws JSONException{
        Mes mes = null;

        JSONObject json_mes = new JSONObject(jsonText);

        if (json_mes != null) {

            mes = new Mes();
            mes.setMes(json_mes.getInt("mes"));
            mes.setAno(json_mes.getInt("ano"));

            JSONArray json_dias = json_mes.getJSONArray("dias");

            for (int i = 0; i < json_dias.length(); i++) {
                JSONObject json_dia = (JSONObject) json_dias.get(i);

                Dia dia = new Dia();
                dia.setDia(json_dia.getInt("dia"));
                dia.setMes(json_mes.getInt("mes"));
                dia.setAno(json_mes.getInt("ano"));

                mes.getDias().add(dia);

                JSONArray json_titulos = json_dia.getJSONArray("titulos");

                for (int j = 0; j < json_titulos.length(); j++) {
                    JSONObject json_titulo = (JSONObject) json_titulos.get(j);

                    Item item = new Item();
                    item.setIddoc(json_titulo.getInt("id"));
                    item.setIdsinal(json_titulo.getInt("sinal"));
                    item.setIdgrupo(json_titulo.getInt("grupo"));
                    item.setEmissor(json_titulo.getString("nome"));
                    item.setValor(json_titulo.getDouble("valor"));

                    dia.getItens().add(item);

                }
            }
        }

        return mes;
    }

    public static Item jsonItemToBase(String json){
        Item item = null;
        return item;
    }     
    
}
    

