/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author MADD
 */
public class PtTipoPermisoVO implements Serializable {
    private static final long serialVersionUID = 1L;

    private int ptnCodigo;
    private String pttNombre;
    private Collection<PaPermisosAsesorVO> PaPermisosAsesorCollection;
    
    public PtTipoPermisoVO() {
    }

    public int getPtnCodigo() {
        return ptnCodigo;
    }

    public void setPtnCodigo(int ptnCodigo) {
        this.ptnCodigo = ptnCodigo;
    }

    public String getPttNombre() {
        return pttNombre;
    }

    public void setPttNombre(String pttNombre) {
        this.pttNombre = pttNombre;
    }

    public Collection<PaPermisosAsesorVO> getPaPermisosAsesorCollection() {
        return PaPermisosAsesorCollection;
    }

    public void setPaPermisosAsesorCollection(Collection<PaPermisosAsesorVO> PaPermisosAsesorCollection) {
        this.PaPermisosAsesorCollection = PaPermisosAsesorCollection;
    }
    
}
