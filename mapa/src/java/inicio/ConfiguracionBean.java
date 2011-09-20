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
    private String tiempoSesion;
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

        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo parametros.properties no encontrado", e);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros", e);
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

}
