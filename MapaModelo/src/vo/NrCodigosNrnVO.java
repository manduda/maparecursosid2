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
public class NrCodigosNrnVO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int nrnCodigo;
    private EmOperadorVO emrCodigo;
    private int nrnCodigoNrn;
    private EsEstadoVO esnCodigo;
    private String nrtObservaciones;
    
    public NrCodigosNrnVO(){
        
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

    public int getNrnCodigo() {
        return nrnCodigo;
    }

    public void setNrnCodigo(int nrnCodigo) {
        this.nrnCodigo = nrnCodigo;
    }

    public int getNrnCodigoNrn() {
        return nrnCodigoNrn;
    }

    public void setNrnCodigoNrn(int nrnCodigoNrn) {
        this.nrnCodigoNrn = nrnCodigoNrn;
    }

    public String getNrtObservaciones() {
        return nrtObservaciones;
    }

    public void setNrtObservaciones(String nrtObservaciones) {
        this.nrtObservaciones = nrtObservaciones;
    }
}
