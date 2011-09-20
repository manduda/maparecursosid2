/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;

/**
 *
 * @author MADD
 */
public class TlTramiteLdVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tlnCodigo;
    private TrTramitesVO trnCodigo;
    private ClCodigosLdVO clnCodigo;
    private AcAccionVO acnCodigo;
    private int tlnRadicado;
    private EmOperadorVO emrCodigo;
    private String tltObservaciones;

    public TlTramiteLdVO(){
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public ClCodigosLdVO getClnCodigo() {
        return clnCodigo;
    }

    public void setClnCodigo(ClCodigosLdVO clnCodigo) {
        this.clnCodigo = clnCodigo;
    }

    public int getTlnCodigo() {
        return tlnCodigo;
    }

    public void setTlnCodigo(int tlnCodigo) {
        this.tlnCodigo = tlnCodigo;
    }

    public int getTlnRadicado() {
        return tlnRadicado;
    }

    public void setTlnRadicado(int tlnRadicado) {
        this.tlnRadicado = tlnRadicado;
    }

    public String getTltObservaciones() {
        return tltObservaciones;
    }

    public void setTltObservaciones(String tltObservaciones) {
        this.tltObservaciones = tltObservaciones;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }
    
}
