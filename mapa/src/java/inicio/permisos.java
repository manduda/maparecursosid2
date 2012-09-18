/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import java.io.Serializable;

/**
 *
 * @author miguel.duran
 */
public class permisos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    // --- OPCIONES AGREGAR RECURSOS DE UN TRAMITE
    private boolean numeracion = false;
    private boolean senalizacion = false;
    private boolean codigosLd = false;
    private boolean codigosCortos = false;
    private boolean codigos1xy = false;
    private boolean codigosIin = false;
    private boolean codigosMnc = false;
    private boolean marcacionAbreviada = false;
    private boolean codigosNrn = false;
    
    public permisos() {
        this.numeracion = false;
        this.senalizacion = false;
        this.codigosLd = false;
        this.codigosCortos = false;
        this.codigos1xy = false;
        this.codigosIin = false;
        this.codigosMnc = false;
        this.marcacionAbreviada = false;
        this.codigosNrn = false;
    }

    public boolean isCodigos1xy() {
        return codigos1xy;
    }

    public void setCodigos1xy(boolean codigos1xy) {
        this.codigos1xy = codigos1xy;
    }

    public boolean isCodigosCortos() {
        return codigosCortos;
    }

    public void setCodigosCortos(boolean codigosCortos) {
        this.codigosCortos = codigosCortos;
    }

    public boolean isCodigosIin() {
        return codigosIin;
    }

    public void setCodigosIin(boolean codigosIin) {
        this.codigosIin = codigosIin;
    }

    public boolean isCodigosLd() {
        return codigosLd;
    }

    public void setCodigosLd(boolean codigosLd) {
        this.codigosLd = codigosLd;
    }

    public boolean isCodigosMnc() {
        return codigosMnc;
    }

    public void setCodigosMnc(boolean codigosMnc) {
        this.codigosMnc = codigosMnc;
    }

    public boolean isCodigosNrn() {
        return codigosNrn;
    }

    public void setCodigosNrn(boolean codigosNrn) {
        this.codigosNrn = codigosNrn;
    }

    public boolean isMarcacionAbreviada() {
        return marcacionAbreviada;
    }

    public void setMarcacionAbreviada(boolean marcacionAbreviada) {
        this.marcacionAbreviada = marcacionAbreviada;
    }

    public boolean isNumeracion() {
        return numeracion;
    }

    public void setNumeracion(boolean numeracion) {
        this.numeracion = numeracion;
    }

    public boolean isSenalizacion() {
        return senalizacion;
    }

    public void setSenalizacion(boolean senalizacion) {
        this.senalizacion = senalizacion;
    }
    
}
