// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   oidApoyo.java

package OID;

//import basedatos.ConnCacheBean;
//import basedatos.ConnCacheBeanOID;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.ResourceBundle;

public class oidApoyo {

    public oidApoyo() {
    }

    public static String getDN(String login, String params, String nomParams) {
        String respuesta = "cn=" + login;
        try {
            //Properties propOID = loadParams("properties/ConnectionOID");
            Properties propOID = new Properties();
            InputStream input = oidApoyo.class.getResourceAsStream("/properties/ConnectionOID.properties");
            propOID.load(input);
            input.close();
            
            String paramtempo = params;
            String paramNomtempo = nomParams;
            int pos = 0;
            int posNom = 0;
            boolean leer = true;
            String cat = "";
            String nomCat = "";
            while(leer) {
                pos = paramtempo.indexOf(',');
                posNom = paramNomtempo.indexOf(',');
                if(pos > -1) {
                    cat = paramtempo.substring(0, pos);
                    if(posNom > -1)
                        nomCat = paramNomtempo.substring(0, posNom);
                    respuesta = respuesta + "," + nomCat + "=" + propOID.get(cat);
                    paramtempo = paramtempo.substring(pos + 1, paramtempo.length());
                    paramNomtempo = paramNomtempo.substring(posNom + 1, paramNomtempo.length());
                } else {
                    cat = paramtempo.substring(0, paramtempo.length());
                    posNom = paramtempo.indexOf(',');
                    if(posNom < 0)
                        nomCat = paramNomtempo.substring(0, paramNomtempo.length());
                    else
                        nomCat = paramNomtempo.substring(0, posNom);
                    respuesta = respuesta + "," + nomCat + "=" + propOID.get(cat);
                    leer = false;
                }
            }
        }
        catch(Exception ex) {
            System.out.println(ex);
        }
        return respuesta;
    }
    
}
