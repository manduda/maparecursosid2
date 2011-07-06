/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author miguel.duran
 */
public class ClCodigosLdVO {
    private BigDecimal clnCodigo;
    private EmOperadorVO emrCodigo;
    private BigInteger clnCodigoLd;
    private EsEstadoVO esnCodigo;

    public ClCodigosLdVO() {
    }

    public BigDecimal getClnCodigo() {
        return clnCodigo;
    }

    public void setClnCodigo(BigDecimal clnCodigo) {
        this.clnCodigo = clnCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public BigInteger getClnCodigoLd() {
        return clnCodigoLd;
    }

    public void setClnCodigoLd(BigInteger clnCodigoLd) {
        this.clnCodigoLd = clnCodigoLd;
    }

    public EsEstadoVO getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstadoVO esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

}
