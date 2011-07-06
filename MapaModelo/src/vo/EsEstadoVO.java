/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.math.BigDecimal;

/**
 *
 * @author miguel.duran
 */
public class EsEstadoVO {
    private BigDecimal esnCodigo;
    private String estNombre;

    public EsEstadoVO() {
    }

    public BigDecimal getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(BigDecimal esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

}
