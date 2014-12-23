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
public class TtTipoServicioTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int ttnCodigo;
    private String tttNombre;
    
    public TtTipoServicioTdtVO() {
    }

    public int getTtnCodigo() {
        return ttnCodigo;
    }

    public void setTtnCodigo(int ttnCodigo) {
        this.ttnCodigo = ttnCodigo;
    }

    public String getTttNombre() {
        return tttNombre;
    }

    public void setTttNombre(String tttNombre) {
        this.tttNombre = tttNombre;
    }
    
}
