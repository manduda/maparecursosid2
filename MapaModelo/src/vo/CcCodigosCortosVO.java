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
public class CcCodigosCortosVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int ccnCodigo;
    private EmOperadorVO emrCodigo;
    private EsEstadoVO esnCodigo;
    private int ccnCodigoCorto;
    private MdModalidadCcVO mdnCodigo;
    private String cctObservaciones;

    public CcCodigosCortosVO() {
    }

    public int getCcnCodigo() {
        return ccnCodigo;
    }

    public void setCcnCodigo(int ccnCodigo) {
        this.ccnCodigo = ccnCodigo;
    }

    public int getCcnCodigoCorto() {
        return ccnCodigoCorto;
    }

    public void setCcnCodigoCorto(int ccnCodigoCorto) {
        this.ccnCodigoCorto = ccnCodigoCorto;
    }

    public String getCctObservaciones() {
        return cctObservaciones;
    }

    public void setCctObservaciones(String cctObservaciones) {
        this.cctObservaciones = cctObservaciones;
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

    public MdModalidadCcVO getMdnCodigo() {
        return mdnCodigo;
    }

    public void setMdnCodigo(MdModalidadCcVO mdnCodigo) {
        this.mdnCodigo = mdnCodigo;
    }
    
}
