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
public class RrTipoRedTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int rrnCodigo;
    private String rrtNombre;
    
    public RrTipoRedTdtVO() {
    }

    public int getRrnCodigo() {
        return rrnCodigo;
    }

    public void setRrnCodigo(int rrnCodigo) {
        this.rrnCodigo = rrnCodigo;
    }

    public String getRrtNombre() {
        return rrtNombre;
    }

    public void setRrtNombre(String rrtNombre) {
        this.rrtNombre = rrtNombre;
    }
    
}
