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
public class McMccVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int mcnCodigo;
    private String mctNombre;
    
    public McMccVO() {
    }

    public int getMcnCodigo() {
        return mcnCodigo;
    }

    public void setMcnCodigo(int mcnCodigo) {
        this.mcnCodigo = mcnCodigo;
    }

    public String getMctNombre() {
        return mctNombre;
    }

    public void setMctNombre(String mctNombre) {
        this.mctNombre = mctNombre;
    }
    
}
