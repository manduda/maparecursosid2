/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import facade.facade;
import java.io.IOException;
import java.io.InputStream;
import java.io.Serializable;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "ConfiguracionBean",eager=true)
@ApplicationScoped
public class ConfiguracionBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String operadorNinguno;
    private String municipioNinguno;
    private String rutaContexto;
    private String rutaInicio;
    private String rutaMapaSenalizacion;
    private String rutaMapaNumeracion;
    private String rutaCodigosLd;
    private String rutaCodigosCortos;
    private String rutaCodigos1xy;
    private String rutaCodigosIIN;
    private String rutaMarcacionAbreviada;
    private String rutaCodigosMnc;
    private String rutaNRN;
    private String rutaNormatividad;
    private String tiempoSesion;
    private boolean googleAnalytics;
    
    private String servidorCorreo;
    private String correoAplicacion;
    private String passwordCorreo;
    private String puertoServidor;
    private String firmaCorreo;
    private String logoCorreo;
    
    private String callbackUri;
    private String clientId;
    private String clientSecret;
    /** Creates a new instance of ConfiguracionBean */
    public ConfiguracionBean() {
        facade fachada = new facade();
        Properties properties = new Properties();
        try {
            InputStream input = ConfiguracionBean.class.getResourceAsStream("../properties/parametros.properties");
            properties.load(input);
            input.close();

            operadorNinguno = fachada.buscarConfiguracionNombre("operadorNinguno").getCotValor();//properties.getProperty("operadorNinguno");
            municipioNinguno = fachada.buscarConfiguracionNombre("municipioNinguno").getCotValor();//properties.getProperty("municipioNinguno");
            rutaContexto = fachada.buscarConfiguracionNombre("rutaContexto").getCotValor();//properties.getProperty("rutaContexto");
            
            String google = fachada.buscarConfiguracionNombre("googleAnalytics").getCotValor();
            
            if (google.equals("1")){
                googleAnalytics = true;
            } else {
                googleAnalytics = false;
            }
            
            tiempoSesion = properties.getProperty("tiempoSesion");
            
            rutaInicio = rutaContexto + "index.xhtml";
            rutaMapaSenalizacion = rutaContexto + "senalizacion.xhtml";
            rutaMapaNumeracion = rutaContexto + "numeracion.xhtml";
            rutaCodigosLd = rutaContexto + "codigosld.xhtml";
            rutaCodigosCortos = rutaContexto + "codigosCortos.xhtml";
            rutaCodigos1xy = rutaContexto + "codigos1xy.xhtml";
            rutaCodigosIIN = rutaContexto + "codigosIIN.xhtml";
            rutaMarcacionAbreviada = rutaContexto + "marcacionAbreviada.xhtml";
            rutaCodigosMnc = rutaContexto + "codigosMNC.xhtml";
            rutaNRN = rutaContexto + "codigosNrn.xhtml";
            rutaNormatividad = rutaContexto + "normatividad.xhtml";

        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo parametros.properties no encontrado", e);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros", e);
        }
        
        try {
            InputStream input = ConfiguracionBean.class.getResourceAsStream("../properties/Correo.properties");
            properties.load(input);
            input.close();
            
            servidorCorreo = fachada.buscarConfiguracionNombre("servidorCorreo").getCotValor();//properties.getProperty("servidorCorreo");
            correoAplicacion = fachada.buscarConfiguracionNombre("correoAplicacion").getCotValor();//properties.getProperty("correoAplicacion");
            passwordCorreo = fachada.buscarConfiguracionNombre("passwordCorreo").getCotValor();//properties.getProperty("passwordCorreo");
            puertoServidor = fachada.buscarConfiguracionNombre("puertoServidor").getCotValor();//properties.getProperty("puertoServidor");
            firmaCorreo = fachada.buscarConfiguracionNombre("firmaCorreo").getCotValor();//properties.getProperty("firmaCorreo");
            logoCorreo = fachada.buscarConfiguracionNombre("logoCorreo").getCotValor();//properties.getProperty("logoCorreo");
            
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo Correo.properties no encontrado", e);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros del correo", e);
        }
        
        callbackUri = fachada.buscarConfiguracionNombre("callbackUri").getCotValor();
        clientId = fachada.buscarConfiguracionNombre("clientId").getCotValor();
        clientSecret = fachada.buscarConfiguracionNombre("clientSecret").getCotValor();

    }

    public String getOperadorNinguno() {
        return operadorNinguno;
    }

    public String getMunicipioNinguno() {
        return municipioNinguno;
    }

    public String getRutaContexto() {
        return rutaContexto;
    }

    public String getRutaCodigosLd() {
        return rutaCodigosLd;
    }

    public String getRutaInicio() {
        return rutaInicio;
    }

    public String getRutaMapaSenalizacion() {
        return rutaMapaSenalizacion;
    }

    public String getRutaMapaNumeracion() {
        return rutaMapaNumeracion;
    }

    public String getTiempoSesion() {
        return tiempoSesion;
    }

    public void setTiempoSesion(String tiempoSesion) {
        this.tiempoSesion = tiempoSesion;
    }

    public String getCorreoAplicacion() {
        return correoAplicacion;
    }

    public void setCorreoAplicacion(String correoAplicacion) {
        this.correoAplicacion = correoAplicacion;
    }

    public String getPasswordCorreo() {
        return passwordCorreo;
    }

    public void setPasswordCorreo(String passwordCorreo) {
        this.passwordCorreo = passwordCorreo;
    }

    public String getServidorCorreo() {
        return servidorCorreo;
    }

    public void setServidorCorreo(String servidorCorreo) {
        this.servidorCorreo = servidorCorreo;
    }

    public String getPuertoServidor() {
        return puertoServidor;
    }

    public void setPuertoServidor(String puertoServidor) {
        this.puertoServidor = puertoServidor;
    }

    public String getFirmaCorreo() {
        return firmaCorreo;
    }

    public void setFirmaCorreo(String firmaCorreo) {
        this.firmaCorreo = firmaCorreo;
    }

    public String getLogoCorreo() {
        return logoCorreo;
    }

    public void setLogoCorreo(String logoCorreo) {
        this.logoCorreo = logoCorreo;
    }

    public String getRutaCodigosCortos() {
        return rutaCodigosCortos;
    }

    public void setRutaCodigosCortos(String rutaCodigosCortos) {
        this.rutaCodigosCortos = rutaCodigosCortos;
    }

    public String getRutaNormatividad() {
        return rutaNormatividad;
    }

    public void setRutaNormatividad(String rutaNormatividad) {
        this.rutaNormatividad = rutaNormatividad;
    }

    public String getRutaCodigosIIN() {
        return rutaCodigosIIN;
    }

    public void setRutaCodigosIIN(String rutaCodigosIIN) {
        this.rutaCodigosIIN = rutaCodigosIIN;
    }

    public String getRutaMarcacionAbreviada() {
        return rutaMarcacionAbreviada;
    }

    public void setRutaMarcacionAbreviada(String rutaMarcacionAbreviada) {
        this.rutaMarcacionAbreviada = rutaMarcacionAbreviada;
    }

    public String getRutaCodigos1xy() {
        return rutaCodigos1xy;
    }

    public void setRutaCodigos1xy(String rutaCodigos1xy) {
        this.rutaCodigos1xy = rutaCodigos1xy;
    }

    public String getRutaNRN() {
        return rutaNRN;
    }

    public void setRutaNRN(String rutaNRN) {
        this.rutaNRN = rutaNRN;
    }

    public String getRutaCodigosMnc() {
        return rutaCodigosMnc;
    }

    public void setRutaCodigosMnc(String rutaCodigosMnc) {
        this.rutaCodigosMnc = rutaCodigosMnc;
    }

    public boolean isGoogleAnalytics() {
        return googleAnalytics;
    }

    public void setGoogleAnalytics(boolean googleAnalytics) {
        this.googleAnalytics = googleAnalytics;
    }

    public String getCallbackUri() {
        return callbackUri;
    }

    public void setCallbackUri(String callbackUri) {
        this.callbackUri = callbackUri;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public String getClientSecret() {
        return clientSecret;
    }

    public void setClientSecret(String clientSecret) {
        this.clientSecret = clientSecret;
    }
    
}
