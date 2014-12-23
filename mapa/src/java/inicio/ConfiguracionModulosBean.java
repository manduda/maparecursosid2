/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import facade.facade;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "ConfiguracionModulosBean")
@RequestScoped
public class ConfiguracionModulosBean {
    
    private boolean moduloNumeracion = false;
    private boolean moduloSenalizacion = false;
    private boolean moduloCodigosLd = false;
    private boolean moduloCodigosCortos = false;
    private boolean moduloCodigos1xy = false;
    private boolean moduloCodigosIin = false;
    private boolean moduloCodigosMnc = false;
    private boolean moduloMarcacionAbreviada = false;
    private boolean moduloCodigosNrn = false;
    private boolean moduloRecursosTdt = false;
    
    private String mensajeConfiguracionModulos = "";
    
    public ConfiguracionModulosBean() {
        try {
            facade fachada = new facade();

            if (fachada.buscarConfiguracionModulo(1).getCmtActivo() == 'S') {
                moduloNumeracion = true;
            }

            if (fachada.buscarConfiguracionModulo(2).getCmtActivo() == 'S') {
                moduloSenalizacion = true;
            }

            if (fachada.buscarConfiguracionModulo(3).getCmtActivo() == 'S') {
                moduloCodigosLd = true;
            }

            if (fachada.buscarConfiguracionModulo(4).getCmtActivo() == 'S') {
                moduloCodigosCortos = true;
            }

            if (fachada.buscarConfiguracionModulo(5).getCmtActivo() == 'S') {
                moduloCodigos1xy = true;
            }

            if (fachada.buscarConfiguracionModulo(6).getCmtActivo() == 'S') {
                moduloCodigosIin = true;
            }

            if (fachada.buscarConfiguracionModulo(7).getCmtActivo() == 'S') {
                moduloCodigosMnc = true;
            }

            if (fachada.buscarConfiguracionModulo(8).getCmtActivo() == 'S') {
                moduloMarcacionAbreviada = true;
            }

            if (fachada.buscarConfiguracionModulo(9).getCmtActivo() == 'S') {
                moduloCodigosNrn = true;
            }
            
            if (fachada.buscarConfiguracionModulo(10).getCmtActivo() == 'S') {
                moduloRecursosTdt = true;
            }
            mensajeConfiguracionModulos = "";
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando ConfiguracionModulosBean", e);
            mensajeConfiguracionModulos = "Estamos experimentado problemas de conexión con la base de datos del sistema. Por favor intente más tarde.";
        }
    }

    public boolean isModuloCodigos1xy() {
        return moduloCodigos1xy;
    }

    public void setModuloCodigos1xy(boolean moduloCodigos1xy) {
        this.moduloCodigos1xy = moduloCodigos1xy;
    }

    public boolean isModuloCodigosCortos() {
        return moduloCodigosCortos;
    }

    public void setModuloCodigosCortos(boolean moduloCodigosCortos) {
        this.moduloCodigosCortos = moduloCodigosCortos;
    }

    public boolean isModuloCodigosIin() {
        return moduloCodigosIin;
    }

    public void setModuloCodigosIin(boolean moduloCodigosIin) {
        this.moduloCodigosIin = moduloCodigosIin;
    }

    public boolean isModuloCodigosLd() {
        return moduloCodigosLd;
    }

    public void setModuloCodigosLd(boolean moduloCodigosLd) {
        this.moduloCodigosLd = moduloCodigosLd;
    }

    public boolean isModuloCodigosMnc() {
        return moduloCodigosMnc;
    }

    public void setModuloCodigosMnc(boolean moduloCodigosMnc) {
        this.moduloCodigosMnc = moduloCodigosMnc;
    }

    public boolean isModuloCodigosNrn() {
        return moduloCodigosNrn;
    }

    public void setModuloCodigosNrn(boolean moduloCodigosNrn) {
        this.moduloCodigosNrn = moduloCodigosNrn;
    }

    public boolean isModuloMarcacionAbreviada() {
        return moduloMarcacionAbreviada;
    }

    public void setModuloMarcacionAbreviada(boolean moduloMarcacionAbreviada) {
        this.moduloMarcacionAbreviada = moduloMarcacionAbreviada;
    }

    public boolean isModuloNumeracion() {
        return moduloNumeracion;
    }

    public void setModuloNumeracion(boolean moduloNumeracion) {
        this.moduloNumeracion = moduloNumeracion;
    }

    public boolean isModuloSenalizacion() {
        return moduloSenalizacion;
    }

    public void setModuloSenalizacion(boolean moduloSenalizacion) {
        this.moduloSenalizacion = moduloSenalizacion;
    }

    public boolean isModuloRecursosTdt() {
        return moduloRecursosTdt;
    }

    public void setModuloRecursosTdt(boolean moduloRecursosTdt) {
        this.moduloRecursosTdt = moduloRecursosTdt;
    }
    
    public String getMensajeConfiguracionModulos() {
        return mensajeConfiguracionModulos;
    }

    public void setMensajeConfiguracionModulos(String mensajeConfiguracionModulos) {
        this.mensajeConfiguracionModulos = mensajeConfiguracionModulos;
    }

}
