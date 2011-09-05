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
import vo.ReRegionVO;
import vo.RtTipoRegionVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "RE_REGION")
public class ReRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "REN_CODIGO")
    private int renCodigo;
    
    @Basic(optional = false)
    @Column(name = "RET_NOMBRE")
    private String retNombre;
    
    @JoinColumn(name = "RTN_CODIGO", referencedColumnName = "RTN_CODIGO")
    @ManyToOne(optional = false)
    private RtTipoRegion rtnCodigo;
    
    public ReRegion() {
    }
    
    public ReRegionVO toVO() {
        ReRegionVO vo = new ReRegionVO();
        
        // Tipo región señalización
        RtTipoRegionVO tipoRegionSenalizacionVO = new RtTipoRegionVO();
        tipoRegionSenalizacionVO = this.getRtnCodigo().toVO();
        
        vo.setRenCodigo(this.getRenCodigo());
        vo.setRetNombre(this.getRetNombre());
        vo.setRtnCodigo(tipoRegionSenalizacionVO);
        return vo;
    }
    
    public int getRenCodigo() {
        return renCodigo;
    }

    public void setRenCodigo(int renCodigo) {
        this.renCodigo = renCodigo;
    }

    public String getRetNombre() {
        return retNombre;
    }

    public void setRetNombre(String retNombre) {
        this.retNombre = retNombre;
    }

    public RtTipoRegion getRtnCodigo() {
        return rtnCodigo;
    }

    public void setRtnCodigo(RtTipoRegion rtnCodigo) {
        this.rtnCodigo = rtnCodigo;
    }

}
