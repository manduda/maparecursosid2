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
public class EmOperadorVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private String emrCodigo;
    private String emtNombre;

    public EmOperadorVO() {
    }
    
    public String getEmtNombre() {
        return emtNombre;
    }

    public void setEmtNombre(String emtNombre) {
        this.emtNombre = emtNombre;
    }

    public String getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(String emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

   
}
