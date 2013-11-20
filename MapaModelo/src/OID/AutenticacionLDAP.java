/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package OID;

import com.novell.ldap.LDAPConnection;
import daos.CoConfiguracionDAO;
import java.io.IOException;
import java.io.InputStream;
import java.util.Enumeration;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

/**
 *
 * @author miguel.duran
 */
public class AutenticacionLDAP {
    public static boolean autenticar (String usuario, String contrasena, int tipo, EntityManager em) {
        /*
         * 1 - JDNI a través de Resource Reference
         * 2 - JDNI a través de Archivo Properties
         * 3 - Archivo properties con librería LDAP
         * 4 - Configuración guardad en BD. Tabla CO_CONFIGURACION
         */
        boolean resultado = false;
        switch (tipo) {
            case 1:
                resultado = autenticarJndi(usuario, contrasena);
                break;
            case 2:
                resultado = autenticarProp(usuario, contrasena);
                break;
            case 3:
                autenticarLibreriaLDAP(usuario, contrasena);
                break;
            case 4:
                resultado = autenticarBD(usuario, contrasena, em);
                break;
        }
        
        return resultado;
    }
    
    public static boolean autenticarJndi(String usuario, String contrasena){
        //Autenticación con Java Naming and Directory Interface
        boolean resultado = false;
        try {
            Context newCtx = new InitialContext();
            Context envCtx = (Context) newCtx.lookup("java:/comp/env");
            NamingEnumeration bindings = envCtx.listBindings("ldap");
            Binding bd = null;
            while (bindings.hasMoreElements()) {
                Binding bds = (Binding) bindings.next();
                if (bds.getName().equals("OracleOID")){
                    bd = bds;
                }
            }
            
            if (bd == null){
                System.out.println("no se encuentra el Resource");
                return false;
            }
            
            Reference ref = (Reference) bd.getObject();
            Enumeration parametros = ref.getAll();
            Properties env = new Properties();

            while (parametros.hasMoreElements()) {
                RefAddr addr = (RefAddr) parametros.nextElement();
                if (addr.getType().equals("java.naming.security.principal")) {
                    env.put(addr.getType(), "cn="+usuario+","+addr.getContent().toString());
                } else if (addr.getType().equals("java.naming.security.credentials")) {
                    env.put("java.naming.security.credentials", contrasena);
                } else {
                    env.put(addr.getType(), addr.getContent().toString());
                }
            }
            
            DirContext authContext = new InitialDirContext(env);
            authContext.close();
            envCtx.close();
            newCtx.close();
            //System.out.println("Atenticación exitosa");
            resultado = true;
        } catch (AuthenticationException authEx) {
            //System.out.println("Atenticación fallida");
            resultado = false;
        } catch (NamingException namEx) {
            System.out.println("Error en la autenticación con LDAP a través de JNDI");
            resultado = false;
        }
        return resultado;
    }
    
    public static boolean autenticarProp(String usuario, String contrasena){
        //Autenticación con archivo Properties
        boolean resultado = false;
        Properties propOID = new Properties();
        try {
            InputStream input = AutenticacionLDAP.class.getResourceAsStream("/properties/ConnectionOID.properties");
            propOID.load(input);
            input.close();
            
            String factory = (String)propOID.getProperty("java.naming.factory.initial");
            String pool = (String)propOID.getProperty("com.sun.jndi.ldap.connect.pool");
            String url = (String)propOID.getProperty("java.naming.provider.url");
            String authentication = (String)propOID.getProperty("java.naming.security.authentication");
            String principal = "cn="+usuario+","+(String)propOID.getProperty("java.naming.security.principal");
            
            Properties env = new Properties();
            env.put("java.naming.factory.initial", factory);
            env.put("com.sun.jndi.ldap.connect.pool", pool);
            env.put("java.naming.provider.url", url);
            env.put("java.naming.security.authentication", authentication);
            env.put("java.naming.security.principal", principal);
            env.put("java.naming.security.credentials", contrasena);
            
            try {
                DirContext authContext = new InitialDirContext(env);
                authContext.close();
                //System.out.println("Atenticación exitosa");
                resultado = true;
            } catch (AuthenticationException authEx) {
                //System.out.println("Atenticación fallida");
                resultado = false;
            } catch (NamingException namEx) {
                System.out.println("Error en la autenticación con LDAP a través del archivo properties");
                resultado = false;
            }

        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo ConnectionOID.properties no encontrado", e);
            resultado = false;
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros", e);
            resultado = false;
        }
        
        
        
        return resultado;
    }
    
    public static boolean autenticarLibreriaLDAP(String usuario, String contrasena){
        //Autenticación con librería LDAP
        boolean resultado = false;
        Properties propOID = new Properties();
        try {
            InputStream input = AutenticacionLDAP.class.getResourceAsStream("/properties/ConnectionOID.properties");
            propOID.load(input);
            input.close();
            
            String url = (String)propOID.getProperty("java.naming.provider.url");
            
            LDAPConnection conn = new LDAPConnection();
            int ldapPort = Integer.parseInt(url.substring(url.indexOf(":") + 1, url.length() - 1));
            String ldapHost = url.substring(url.indexOf("//") + 2, url.indexOf(":") - 1);
            String loginDN = "cn="+usuario+","+(String)propOID.getProperty("java.naming.security.principal");

            String respuesta = GetAuthenticated.simpleBind1(conn, ldapHost, ldapPort, loginDN, contrasena);

            if(respuesta.equals("1")){
                resultado = true;
            } else {
                resultado = false;
            }

        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo ConnectionOID.properties no encontrado", e);
            resultado = false;
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros", e);
            resultado = false;
        }
        
        
        
        return resultado;
    }
    
    public static boolean autenticarBD(String usuario, String contrasena, EntityManager em){
        //Autenticación con BD
        boolean resultado = false;
        String factory = CoConfiguracionDAO.findbyName("ldap.factory.initial", em).getCotValor();
        String pool = CoConfiguracionDAO.findbyName("com.sun.jndi.ldap.connect.pool", em).getCotValor();
        String url = CoConfiguracionDAO.findbyName("ldap.provider.url", em).getCotValor();
        String authentication = CoConfiguracionDAO.findbyName("ldap.security.authentication", em).getCotValor();
        String principal = "cn="+usuario+","+CoConfiguracionDAO.findbyName("ldap.security.principal", em).getCotValor();

        Properties env = new Properties();
        env.put("java.naming.factory.initial", factory);
        env.put("com.sun.jndi.ldap.connect.pool", pool);
        env.put("java.naming.provider.url", url);
        env.put("java.naming.security.authentication", authentication);
        env.put("java.naming.security.principal", principal);
        env.put("java.naming.security.credentials", contrasena);

        try {
            DirContext authContext = new InitialDirContext(env);
            authContext.close();
            //System.out.println("Atenticación exitosa");
            resultado = true;
        } catch (AuthenticationException authEx) {
            //System.out.println("Atenticación fallida");
            resultado = false;
        } catch (NamingException namEx) {
            System.out.println("Error en la autenticación con LDAP a través del archivo properties");
            resultado = false;
        }
        
        return resultado;
    }
    
}
