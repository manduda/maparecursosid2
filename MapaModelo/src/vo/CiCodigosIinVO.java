/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;

/**
 *
 * @author miguel.duran
 */
public class CiCodigosIinVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cinCodigo;
    private EmOperadorVO emrCodigo;
    private int cinCodigoIin;
    private EsEstadoVO esnCodigo;
    private String citObservaciones;
    
    public CiCodigosIinVO(){
        
    }

    public int getCinCodigo() {
        return cinCodigo;
    }

    public void setCinCodigo(int cinCodigo) {
        this.cinCodigo = cinCodigo;
    }

    public String getCitObservaciones() {
        return citObservaciones;
    }

    public void setCitObservaciones(String citObservaciones) {
        this.citObservaciones = citObservaciones;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public EsEstadoVO getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstadoVO esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public int getCinCodigoIin() {
        return cinCodigoIin;
    }

    public void setCinCodigoIin(int cinCodigoIin) {
        this.cinCodigoIin = cinCodigoIin;
    }
    
}
