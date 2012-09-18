/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import vo.PaPermisosAsesorVO;
import vo.PtTipoPermisoVO;

/**
 *
 * @author MADD
 */
@Entity
@Table(name = "PA_PERMISOS_ASESOR")
public class PaPermisosAsesor implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "PAN_CODIGO")
    private int panCodigo;
    
    @JoinColumn(name = "USN_CODIGO", referencedColumnName = "USN_CODIGO")
    @ManyToOne(optional = false)
    private UsUsuarios usnCodigo;
    
    @JoinColumn(name = "PTN_CODIGO", referencedColumnName = "PTN_CODIGO")
    @ManyToOne(optional = false)
    private PtTipoPermiso ptnCodigo;
    
    public PaPermisosAsesor() {
    }
    
    public PaPermisosAsesorVO toVO(){
        PaPermisosAsesorVO vo = new PaPermisosAsesorVO();
        vo.setPanCodigo(this.panCodigo);
        vo.setUsnCodigo(this.usnCodigo.toVO());
        vo.setPtnCodigo(this.ptnCodigo.toVO());
        return vo;
    }

    public int getPanCodigo() {
        return panCodigo;
    }

    public void setPanCodigo(int panCodigo) {
        this.panCodigo = panCodigo;
    }

    public PtTipoPermiso getPtnCodigo() {
        return ptnCodigo;
    }

    public void setPtnCodigo(PtTipoPermiso ptnCodigo) {
        this.ptnCodigo = ptnCodigo;
    }

    public UsUsuarios getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(UsUsuarios usnCodigo) {
        this.usnCodigo = usnCodigo;
    }
    
}
