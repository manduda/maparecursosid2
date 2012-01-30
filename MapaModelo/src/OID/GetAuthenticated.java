// Decompiled by Jad v1.5.8g. Copyright 2001 Pavel Kouznetsov.
// Jad home page: http://www.kpdus.com/jad.html
// Decompiler options: packimports(3) 
// Source File Name:   GetAuthenticated.java

package OID;

import com.novell.ldap.LDAPConnection;
import com.novell.ldap.LDAPException;
import com.novell.ldap.LDAPJSSESecureSocketFactory;
import com.sun.net.ssl.internal.ssl.Provider;
import java.io.UnsupportedEncodingException;
import java.security.Security;
import java.util.Properties;

public class GetAuthenticated {

    public GetAuthenticated() {
    }

    private static void usage() {
        System.err.println("Usage:   java GetAuthenticated <host Name> <login dn> <password>");
        System.err.println("Example: java GetAuthenticated Acme.com \"cn=admin,o=Acme\" secret");
        System.err.println("To set the keystore for JSSE: java -Djavax.net.ssl.trustStore=/path/keystoreName.keystore ...");
    }

    private static void anonymousBind(LDAPConnection conn, String host, int port) {
        try {
            System.out.println("\nanonymous bind...");
            conn.connect(host, port);
            System.out.println(conn.isBound() ? "\n\tAuthenticated to the server\n" : "\n\tAnonymous bind to the server\n");
            conn.disconnect();
        }
        catch(LDAPException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    public static String simpleBind1(LDAPConnection conn, String host, int port, String dn, String passwd) {
        String respuesta = "";
        try {
            conn.connect(host, port);
            try {
                conn.bind(3, dn, passwd.getBytes("UTF8"));
            }
            catch(UnsupportedEncodingException u) {
                throw new LDAPException("UTF8 Invalid Encoding", 82, (String)null, u);
            }
            respuesta = "1";
            if(conn.isBound()) {
                respuesta = "1";
            } else {
                respuesta = "0";
            }
            conn.disconnect();
        }
        catch(LDAPException e) {
            //System.out.println("Error: " + e.toString());
            respuesta = e.toString();
        }
        return respuesta;
    }

    private static void simpleBind2(int version, LDAPConnection conn, String host, int port, String dn, String passwd) {
        try {
            System.out.println("Simple bind with connection method...");
            conn.connect(host, port);
            try {
                conn.bind(version, dn, passwd.getBytes("UTF8"));
            }
            catch(UnsupportedEncodingException u) {
                throw new LDAPException("UTF8 Invalid Encoding", 82, (String)null, u);
            }
            System.out.println(conn.isBound() ? "\n\tAuthenticated to the server ( simple )\n" : "\n\tNot authenticated to the server\n");
            conn.disconnect();
        }
        catch(LDAPException e) {
            System.out.println("Error: " + e.toString());
        }
    }

    private static void SSLBind(int version, String host, int SSLPort, String dn, String passwd) {
        LDAPJSSESecureSocketFactory ssf = new LDAPJSSESecureSocketFactory();
        LDAPConnection conn = new LDAPConnection(ssf);
        try {
            System.out.println("SSL bind...");
            conn.connect(host, SSLPort);
            try {
                conn.bind(version, dn, passwd.getBytes("UTF8"));
            }
            catch(UnsupportedEncodingException u) {
                throw new LDAPException("UTF8 Invalid Encoding", 82, (String)null, u);
            }
            System.out.println(conn.isBound() ? "\n\tAuthenticated to the server ( ssl )\n" : "\n\tNot authenticated to the server\n");
            conn.disconnect();
        }
        catch(LDAPException e) {
            System.out.println("Error: " + e.toString());
        }
    }
}
