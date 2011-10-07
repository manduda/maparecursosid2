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
public class MdModalidadCcVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int mdnCodigo;
    private String mdtNombre;
    
    public MdModalidadCcVO() {
    }

    public int getMdnCodigo() {
        return mdnCodigo;
    }

    public void setMdnCodigo(int mdnCodigo) {
        this.mdnCodigo = mdnCodigo;
    }

    public String getMdtNombre() {
        return mdtNombre;
    }

    public void setMdtNombre(String mdtNombre) {
        this.mdtNombre = mdtNombre;
    }
    
}
