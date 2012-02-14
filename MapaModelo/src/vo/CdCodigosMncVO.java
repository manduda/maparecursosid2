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
public class CdCodigosMncVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int cdnCodigo;
    private EmOperadorVO emrCodigo;
    private McMccVO mcnCodigo;
    private EsEstadoVO esnCodigo;
    private int cdnMnc;
    private String cdtObservaciones;
    
    public CdCodigosMncVO() {
    }

    public int getCdnCodigo() {
        return cdnCodigo;
    }

    public void setCdnCodigo(int cdnCodigo) {
        this.cdnCodigo = cdnCodigo;
    }

    public String getCdtObservaciones() {
        return cdtObservaciones;
    }

    public void setCdtObservaciones(String cdtObservaciones) {
        this.cdtObservaciones = cdtObservaciones;
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

    public McMccVO getMcnCodigo() {
        return mcnCodigo;
    }

    public void setMcnCodigo(McMccVO mcnCodigo) {
        this.mcnCodigo = mcnCodigo;
    }

    public int getCdnMnc() {
        return cdnMnc;
    }

    public void setCdnMnc(int cdnMnc) {
        this.cdnMnc = cdnMnc;
    }
    
}
