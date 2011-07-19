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
public class NdNdcVO {
    private int ndnCodigo;
    private String ndtNombre;
    private NtTipondcVO ntnCodigo;

    public NdNdcVO() {
    }

    public int getNdnCodigo() {
        return ndnCodigo;
    }

    public void setNdnCodigo(int ndnCodigo) {
        this.ndnCodigo = ndnCodigo;
    }

    public String getNdtNombre() {
        return ndtNombre;
    }

    public void setNdtNombre(String ndtNombre) {
        this.ndtNombre = ndtNombre;
    }

    public NtTipondcVO getNtnCodigo() {
        return ntnCodigo;
    }

    public void setNtnCodigo(NtTipondcVO ntnCodigo) {
        this.ntnCodigo = ntnCodigo;
    }

}
