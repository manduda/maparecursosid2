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
public class ReRegionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int renCodigo;
    private String retNombre;
    private RtTipoRegionVO rtnCodigo;
    
    public ReRegionVO() {
    }

    public int getRenCodigo() {
        return renCodigo;
    }

    public void setRenCodigo(int renCodigo) {
        this.renCodigo = renCodigo;
    }

    public String getRetNombre() {
        return retNombre;
    }

    public void setRetNombre(String retNombre) {
        this.retNombre = retNombre;
    }

    public RtTipoRegionVO getRtnCodigo() {
        return rtnCodigo;
    }

    public void setRtnCodigo(RtTipoRegionVO rtnCodigo) {
        this.rtnCodigo = rtnCodigo;
    }
    
}
