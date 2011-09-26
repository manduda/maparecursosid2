/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.ArrayList;

/**
 *
 * @author MADD
 */
public class Functions {
    public static String concat(String string1, String string2) {
        return string1.concat(string2);
    }
    
    public static String rellenarCerosIzquierda(String cadena, Integer longitud) {
        Integer longitudCadena = cadena.length();
        String resultado = cadena;
        
        if (longitudCadena >= longitud) {
            return cadena;
        }
        
        while (longitudCadena < longitud) {
            resultado = "0" + resultado;
            longitudCadena = resultado.length();
        }
        
        return resultado;
    }
    
    public static int countLista(ArrayList lista) {
        if(lista == null){
            return 0;
        }
        if (lista.isEmpty()){
            return 0;
        } else {
            return lista.size();
        }
        
    }
}
