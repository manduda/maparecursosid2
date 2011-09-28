/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 *
 * @author miguel.duran
 */
public class NtTipondcVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private BigDecimal ntnCodigo;
    private String ntnNombre;
    private NtTipondcVO ntnCodigoPadre;

    public NtTipondcVO() {
    }

    public BigDecimal getNtnCodigo() {
        return ntnCodigo;
    }

    public void setNtnCodigo(BigDecimal ntnCodigo) {
        this.ntnCodigo = ntnCodigo;
    }

    public NtTipondcVO getNtnCodigoPadre() {
        return ntnCodigoPadre;
    }

    public void setNtnCodigoPadre(NtTipondcVO ntnCodigoPadre) {
        this.ntnCodigoPadre = ntnCodigoPadre;
    }

    public String getNtnNombre() {
        return ntnNombre;
    }

    public void setNtnNombre(String ntnNombre) {
        this.ntnNombre = ntnNombre;
    }

}
