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
public class TkTramiteNrnVO implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private int tknCodigo;
    private TrTramitesVO trnCodigo;
    private NrCodigosNrnVO nrnCodigo;
    private AcAccionVO acnCodigo;
    private int tknRadicado;
    private EmOperadorVO emrCodigo;
    private String tktObservaciones;
    private char tktReservaTemporal;
    private int tknMesesLiberacion;

    public TkTramiteNrnVO(){
        
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public NrCodigosNrnVO getNrnCodigo() {
        return nrnCodigo;
    }

    public void setNrnCodigo(NrCodigosNrnVO nrnCodigo) {
        this.nrnCodigo = nrnCodigo;
    }

    public int getTknCodigo() {
        return tknCodigo;
    }

    public void setTknCodigo(int tknCodigo) {
        this.tknCodigo = tknCodigo;
    }

    public int getTknMesesLiberacion() {
        return tknMesesLiberacion;
    }

    public void setTknMesesLiberacion(int tknMesesLiberacion) {
        this.tknMesesLiberacion = tknMesesLiberacion;
    }

    public int getTknRadicado() {
        return tknRadicado;
    }

    public void setTknRadicado(int tknRadicado) {
        this.tknRadicado = tknRadicado;
    }

    public String getTktObservaciones() {
        return tktObservaciones;
    }

    public void setTktObservaciones(String tktObservaciones) {
        this.tktObservaciones = tktObservaciones;
    }

    public char getTktReservaTemporal() {
        return tktReservaTemporal;
    }

    public void setTktReservaTemporal(char tktReservaTemporal) {
        this.tktReservaTemporal = tktReservaTemporal;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
    
}
