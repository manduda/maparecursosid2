/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

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
    private String rutaMatriz1xy;
    private String rutaCodigosIIN;
    private String rutaMarcacionAbreviada;
    private String rutaCodigosMnc;
    private String rutaNRN;
    private String rutaNormatividad;
    private String tiempoSesion;
    
    private String servidorCorreo;
    private String correoAplicacion;
    private String passwordCorreo;
    private String puertoServidor;
    private String firmaCorreo;
    private String logoCorreo;
    /** Creates a new instance of ConfiguracionBean */
    public ConfiguracionBean() {
        
        Properties properties = new Properties();
        try {
            InputStream input = ConfiguracionBean.class.getResourceAsStream("../properties/parametros.properties");
            properties.load(input);
            input.close();
            
            operadorNinguno = properties.getProperty("operadorNinguno");
            municipioNinguno = properties.getProperty("municipioNinguno");
            rutaContexto = properties.getProperty("rutaContexto");
            tiempoSesion = properties.getProperty("tiempoSesion");
            
            rutaInicio = rutaContexto + "index.xhtml";
            rutaMapaSenalizacion = rutaContexto + "senalizacion.xhtml";
            rutaMapaNumeracion = rutaContexto + "numeracion.xhtml";
            rutaCodigosLd = rutaContexto + "codigosld.xhtml";
            rutaCodigosCortos = rutaContexto + "codigosCortos.xhtml";
            rutaMatriz1xy = rutaContexto + "matriz1xy.xhtml";
            rutaCodigosIIN = rutaContexto + "codigosIIN.xhtml";
            rutaMarcacionAbreviada = rutaContexto + "marcacionAbreviada.xhtml";
            rutaCodigosMnc = rutaContexto + "codigosMNC.xhtml";
            rutaNRN = rutaContexto + "nrn.xhtml";
            rutaNormatividad = rutaContexto + "normatividad.xhtml";

        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo parametros.properties no encontrado", e);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros", e);
        }
        
        Properties Correo = new Properties();
        try {
            InputStream input = ConfiguracionBean.class.getResourceAsStream("../properties/Correo.properties");
            properties.load(input);
            input.close();
            
            servidorCorreo = properties.getProperty("servidorCorreo");
            correoAplicacion = properties.getProperty("correoAplicacion");
            passwordCorreo = properties.getProperty("passwordCorreo");
            puertoServidor = properties.getProperty("puertoServidor");
            firmaCorreo = properties.getProperty("firmaCorreo");
            logoCorreo = properties.getProperty("logoCorreo");
            
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo Correo.properties no encontrado", e);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros del correo", e);
        }


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

    public String getRutaMatriz1xy() {
        return rutaMatriz1xy;
    }

    public void setRutaMatriz1xy(String rutaMatriz1xy) {
        this.rutaMatriz1xy = rutaMatriz1xy;
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
    
}
