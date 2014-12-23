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
public class CaCanalTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int canCodigo;
    private String catCanal;
    private int canFrecuencia;
    
    public CaCanalTdtVO() {
    }

    public String getCatCanal() {
        return catCanal;
    }

    public void setCatCanal(String catCanal) {
        this.catCanal = catCanal;
    }

    public int getCanCodigo() {
        return canCodigo;
    }

    public void setCanCodigo(int canCodigo) {
        this.canCodigo = canCodigo;
    }

    public int getCanFrecuencia() {
        return canFrecuencia;
    }

    public void setCanFrecuencia(int canFrecuencia) {
        this.canFrecuencia = canFrecuencia;
    }
    
}
