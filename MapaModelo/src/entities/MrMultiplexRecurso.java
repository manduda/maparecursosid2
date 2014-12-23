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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import vo.MrMultiplexRecursoVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "MR_MULTIPLEX_RECURSO")
public class MrMultiplexRecurso implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "MRN_CODIGO")
    private int mrnCodigo;
    
    @JoinColumn(name = "RIN_CODIGO", referencedColumnName = "RIN_CODIGO")
    @OneToOne(optional = false)
    private RiRecursosTdt rinCodigo;
    
    @JoinColumn(name = "MUN_CODIGO", referencedColumnName = "MUN_CODIGO")
    @ManyToOne(optional = false)
    private MuMultiplex munCodigo;
    
    public MrMultiplexRecurso() {
    }
    
    public MrMultiplexRecursoVO toVO(){
        MrMultiplexRecursoVO vo = new MrMultiplexRecursoVO();
        vo.setMrnCodigo(this.getMrnCodigo());
        vo.setRinCodigo(this.getRinCodigo().toVOsinDetalles());
        vo.setMunCodigo(this.getMunCodigo().toVO());
        return vo;
    }

    public int getMrnCodigo() {
        return mrnCodigo;
    }

    public void setMrnCodigo(int mrnCodigo) {
        this.mrnCodigo = mrnCodigo;
    }

    public MuMultiplex getMunCodigo() {
        return munCodigo;
    }

    public void setMunCodigo(MuMultiplex munCodigo) {
        this.munCodigo = munCodigo;
    }

    public RiRecursosTdt getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdt rinCodigo) {
        this.rinCodigo = rinCodigo;
    }
    
}
