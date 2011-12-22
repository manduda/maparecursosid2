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
public class NtTipoNdcVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Integer ntnCodigo;
    private String nttNombre;
    private NtTipoNdcVO ntnCodigoPadre;

    public NtTipoNdcVO() {
    }

    public Integer getNtnCodigo() {
        return ntnCodigo;
    }

    public void setNtnCodigo(Integer ntnCodigo) {
        this.ntnCodigo = ntnCodigo;
    }

    public NtTipoNdcVO getNtnCodigoPadre() {
        return ntnCodigoPadre;
    }

    public void setNtnCodigoPadre(NtTipoNdcVO ntnCodigoPadre) {
        this.ntnCodigoPadre = ntnCodigoPadre;
    }

    public String getNttNombre() {
        return nttNombre;
    }

    public void setNttNombre(String nttNombre) {
        this.nttNombre = nttNombre;
    }

}
