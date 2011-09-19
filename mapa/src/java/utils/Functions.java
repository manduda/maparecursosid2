/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

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
}
