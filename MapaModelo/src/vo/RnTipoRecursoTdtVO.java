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
public class RnTipoRecursoTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int rnnCodigo;
    private String rntNombre;
    
    public RnTipoRecursoTdtVO() {
    }

    public int getRnnCodigo() {
        return rnnCodigo;
    }

    public void setRnnCodigo(int rnnCodigo) {
        this.rnnCodigo = rnnCodigo;
    }

    public String getRntNombre() {
        return rntNombre;
    }

    public void setRntNombre(String rntNombre) {
        this.rntNombre = rntNombre;
    }

}
