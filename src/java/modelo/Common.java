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


import java.util.Date;

/**
 * Created by progrid on 19/10/17.
 */
public class Common  {

    public static Mes mes = null;
    public static Date diaCorrente;


    public Dia getDate(int intDia, int intMes, int intANo){
        Dia dia = null;

        for (int i = 0; i < mes.getDias().size(); i++) {
            Dia aux_dia = mes.getDias().get(i);
            if (aux_dia.getDia() == intDia && aux_dia.getMes() == intMes && aux_dia.getAno() == intANo)
                dia = aux_dia;
        }

        return dia;
    }

}

    
