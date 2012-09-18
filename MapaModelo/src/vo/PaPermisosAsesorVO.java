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
public class PaPermisosAsesorVO implements Serializable {
    private static final long serialVersionUID = 1L;
    private int panCodigo;
    private UsUsuariosVO usnCodigo;
    private PtTipoPermisoVO ptnCodigo;
    
    public PaPermisosAsesorVO() {
    }

    public int getPanCodigo() {
        return panCodigo;
    }

    public void setPanCodigo(int panCodigo) {
        this.panCodigo = panCodigo;
    }

    public PtTipoPermisoVO getPtnCodigo() {
        return ptnCodigo;
    }

    public void setPtnCodigo(PtTipoPermisoVO ptnCodigo) {
        this.ptnCodigo = ptnCodigo;
    }

    public UsUsuariosVO getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(UsUsuariosVO usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

}
