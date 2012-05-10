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
public class CmConfiguracionModulosVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int cmnCodigo;
    private String cmtModulo;
    private char cmtActivo;
        
    public CmConfiguracionModulosVO() {
    }

    public int getCmnCodigo() {
        return cmnCodigo;
    }

    public void setCmnCodigo(int cmnCodigo) {
        this.cmnCodigo = cmnCodigo;
    }

    public char getCmtActivo() {
        return cmtActivo;
    }

    public void setCmtActivo(char cmtActivo) {
        this.cmtActivo = cmtActivo;
    }

    public String getCmtModulo() {
        return cmtModulo;
    }

    public void setCmtModulo(String cmtModulo) {
        this.cmtModulo = cmtModulo;
    }
    
}
