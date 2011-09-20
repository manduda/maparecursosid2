/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author miguel.duran
 */
public class ClCodigosLdVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int clnCodigo;
    private EmOperadorVO emrCodigo;
    private int clnCodigoLd;
    private EsEstadoVO esnCodigo;
    private String cltObservaciones;

    public ClCodigosLdVO() {
    }

    public int getClnCodigo() {
        return clnCodigo;
    }

    public void setClnCodigo(int clnCodigo) {
        this.clnCodigo = clnCodigo;
    }

    public int getClnCodigoLd() {
        return clnCodigoLd;
    }

    public void setClnCodigoLd(int clnCodigoLd) {
        this.clnCodigoLd = clnCodigoLd;
    }

    public String getCltObservaciones() {
        return cltObservaciones;
    }

    public void setCltObservaciones(String cltObservaciones) {
        this.cltObservaciones = cltObservaciones;
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

}
