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
import vo.Nc1xyVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "NC_1XY")
public class Nc1xy implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "NCN_CODIGO")
    private int ncnCodigo;
    
    @JoinColumn(name = "MON_CODIGO", referencedColumnName = "MON_CODIGO")
    @ManyToOne(optional = false)
    private MoModalidad1xy monCodigo;
    
    @Column(name = "NCN_1XY")
    @Basic(optional = false)
    private int ncn1xy;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "NCT_SERVICIO")
    private String nctServicio;
    
    @Column(name = "NCT_OBSERVACIONES")
    private String nctObservaciones;
    
    public Nc1xy(){
        
    }
    
    public Nc1xyVO toVO() {
        Nc1xyVO vo = new Nc1xyVO();
        vo.setNcnCodigo(this.getNcnCodigo());
        vo.setMonCodigo(this.getMonCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setNcn1xy(this.getNcn1xy());
        vo.setNctServicio(this.getNctServicio());
        vo.setNctObservaciones(this.getNctObservaciones());
        return vo;
    }

    public EsEstado getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstado esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public MoModalidad1xy getMonCodigo() {
        return monCodigo;
    }

    public void setMonCodigo(MoModalidad1xy monCodigo) {
        this.monCodigo = monCodigo;
    }

    public int getNcn1xy() {
        return ncn1xy;
    }

    public void setNcn1xy(int ncn1xy) {
        this.ncn1xy = ncn1xy;
    }

    public int getNcnCodigo() {
        return ncnCodigo;
    }

    public void setNcnCodigo(int ncnCodigo) {
        this.ncnCodigo = ncnCodigo;
    }

    public String getNctServicio() {
        return nctServicio;
    }

    public void setNctServicio(String nctServicio) {
        this.nctServicio = nctServicio;
    }

    public String getNctObservaciones() {
        return nctObservaciones;
    }

    public void setNctObservaciones(String nctObservaciones) {
        this.nctObservaciones = nctObservaciones;
    }
    
}
