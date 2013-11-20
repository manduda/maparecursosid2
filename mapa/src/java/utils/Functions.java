/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.UnsupportedEncodingException;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.zip.ZipEntry;
import java.util.zip.ZipOutputStream;

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
    
    public static Integer longitud(String texto) {
        return texto.length();
    }
    
    public static String toColorCode(String cadena) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String resultado = cadena;
        
        while (resultado.length() < 6){
            resultado = '0' + resultado;
        }
        
        byte[] digest = null;
        byte[] buffer = resultado.getBytes();
        try {
            MessageDigest messageDigest = MessageDigest.getInstance("MD5");
            messageDigest.reset();
            messageDigest.update(buffer);
            digest = messageDigest.digest();
        } catch (NoSuchAlgorithmException ex) {
            System.out.println("Error creando Digest");
        }
        
        String hash = "";
        for(byte aux : digest) {
            int b = aux & 0xff;
            if (Integer.toHexString(b).length() == 1) hash += "0";
            hash += Integer.toHexString(b);
        }
        
        String R = hash.substring(2, 4);
        String G = hash.substring(6, 8);
        String B = hash.substring(10, 12);
        
        return '#'+R+B+G;
    }
    
    public static String toColorCodeFont(String cadena) throws UnsupportedEncodingException, NoSuchAlgorithmException {
        String resultado = toColorCode(cadena).substring(1, 7);
        int R = Integer.parseInt(resultado.substring(0, 2), 16);
        int G = Integer.parseInt(resultado.substring(2, 4), 16);
        int B = Integer.parseInt(resultado.substring(4, 6), 16);
        
        double a = 1 - ( 0.299 * R + 0.587 * G + 0.114 * B)/255;
        
        if (a < 0.5) {
            resultado = "000000"; // bright colors - black font
        } else {
            resultado = "FFFFFF"; // dark colors - white font
        }
      
        return '#'+resultado;
    }
    
    public static void zippear(String pFile, String pZipFile, String fileName) throws Exception {
        // objetos en memoria
        FileInputStream fis = null;
        FileOutputStream fos = null;
        ZipOutputStream zipos = null;

        // buffer
        byte[] buffer = new byte[1024];
        try {
                // fichero a comprimir
                fis = new FileInputStream(pFile);
                // fichero contenedor del zip
                fos = new FileOutputStream(pZipFile);
                // fichero comprimido
                zipos = new ZipOutputStream(fos);
                ZipEntry zipEntry = new ZipEntry(fileName);
                zipos.putNextEntry(zipEntry);
                int len = 0;
                // zippear
                while ((len = fis.read(buffer, 0, 1024)) != -1)
                        zipos.write(buffer, 0, len);
                // volcar la memoria al disco
                zipos.flush();
        } catch (Exception e) {
                throw e;
        } finally {
                // cerramos los files
                zipos.close();
                fis.close();
                fos.close();
        } // end try
    }
    
    public static String userEmail(String email) {
        return email.split("@crcom.gov.co")[0];
    }
}
