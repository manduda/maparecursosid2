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
public class TcTramiteCcVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tcnCodigo;
    private TrTramitesVO trnCodigo;
    private CcCodigosCortosVO ccnCodigo;
    private AcAccionVO acnCodigo;
    private int tcnRadicado;
    private EmOperadorVO emrCodigo;
    private String tctObservaciones;
    private char tctReservaTemporal;
    private int tcnMesesLiberacion;
    
    public TcTramiteCcVO() {
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CcCodigosCortosVO getCcnCodigo() {
        return ccnCodigo;
    }

    public void setCcnCodigo(CcCodigosCortosVO ccnCodigo) {
        this.ccnCodigo = ccnCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getTcnCodigo() {
        return tcnCodigo;
    }

    public void setTcnCodigo(int tcnCodigo) {
        this.tcnCodigo = tcnCodigo;
    }

    public int getTcnRadicado() {
        return tcnRadicado;
    }

    public void setTcnRadicado(int tcnRadicado) {
        this.tcnRadicado = tcnRadicado;
    }

    public String getTctObservaciones() {
        return tctObservaciones;
    }

    public void setTctObservaciones(String tctObservaciones) {
        this.tctObservaciones = tctObservaciones;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public int getTcnMesesLiberacion() {
        return tcnMesesLiberacion;
    }

    public void setTcnMesesLiberacion(int tcnMesesLiberacion) {
        this.tcnMesesLiberacion = tcnMesesLiberacion;
    }

    public char getTctReservaTemporal() {
        return tctReservaTemporal;
    }

    public void setTctReservaTemporal(char tctReservaTemporal) {
        this.tctReservaTemporal = tctReservaTemporal;
    }
    
}
