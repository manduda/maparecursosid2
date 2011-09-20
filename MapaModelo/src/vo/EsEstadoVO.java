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
public class EsEstadoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int esnCodigo;
    private String estNombre;

    public EsEstadoVO() {
    }

    public int getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(int esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

}
