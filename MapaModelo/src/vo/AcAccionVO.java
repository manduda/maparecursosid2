/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;

/**
 *
 * @author MADD
 */
public class AcAccionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int acnCodigo;
    private String actNombre;
    
    public AcAccionVO(){
    }

    public int getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(int acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public String getActNombre() {
        return actNombre;
    }

    public void setActNombre(String actNombre) {
        this.actNombre = actNombre;
    }
    
}
