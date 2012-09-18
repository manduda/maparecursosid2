/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import vo.PaPermisosAsesorVO;
import vo.PtTipoPermisoVO;

/**
 *
 * @author MADD
 */
@Entity
@Table(name = "PT_TIPO_PERMISO")
public class PtTipoPermiso implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "PTN_CODIGO")
    private int ptnCodigo;
    
    @Basic(optional = false)
    @Column(name = "PTT_NOMBRE")
    private String pttNombre;
    
    public PtTipoPermiso() {
    }
    
    public PtTipoPermisoVO toVO(){
        PtTipoPermisoVO vo = new PtTipoPermisoVO();
        vo.setPtnCodigo(this.ptnCodigo);
        vo.setPttNombre(this.pttNombre);
        return vo;
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
    
}
