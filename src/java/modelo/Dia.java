/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package modelo;

/**
 *
 * @author Sutter
 */
  
    
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

/**
 * Created by progrid on 19/10/17.
 */
public class Dia implements Serializable {

    private int dia;
    private int mes;
    private int ano;

    private List<Item> itens;


    public int getDia() {
        return dia;
    }

    public void setDia(int dia) {
        this.dia = dia;
    }

    public List<Item> getItens() {
        if (this.itens == null)
            this.itens = new ArrayList<Item>();
        return this.itens;
    }

    public void setItens(List<Item> itens) {
        this.itens = itens;
    }

    public int getMes() {
        return mes;
    }

    public void setMes(int mes) {
        this.mes = mes;
    }

    public int getAno() {
        return ano;
    }

    public void setAno(int ano) {
        this.ano = ano;
    }
}

    

