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
import vo.StServiciosTdtVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "ST_SERVICIOS_TDT")
public class StServiciosTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "STN_CODIGO")
    private int stnCodigo;
    
    @JoinColumn(name = "RIN_CODIGO", referencedColumnName = "RIN_CODIGO")
    @OneToOne(optional = false)
    private RiRecursosTdt rinCodigo;
    
    @JoinColumn(name = "TTN_CODIGO", referencedColumnName = "TTN_CODIGO")
    @ManyToOne(optional = false)
    private TtTipoServicioTdt ttnCodigo;
    
    public StServiciosTdt() {
    }
    
    public StServiciosTdtVO toVO(){
        StServiciosTdtVO vo = new StServiciosTdtVO();
        vo.setStnCodigo(this.getStnCodigo());
        vo.setRinCodigo(this.getRinCodigo().toVOsinDetalles());
        vo.setTtnCodigo(this.getTtnCodigo().toVO());
        return vo;
    }

    public RiRecursosTdt getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdt rinCodigo) {
        this.rinCodigo = rinCodigo;
    }

    public int getStnCodigo() {
        return stnCodigo;
    }

    public void setStnCodigo(int stnCodigo) {
        this.stnCodigo = stnCodigo;
    }

    public TtTipoServicioTdt getTtnCodigo() {
        return ttnCodigo;
    }

    public void setTtnCodigo(TtTipoServicioTdt ttnCodigo) {
        this.ttnCodigo = ttnCodigo;
    }
    
}
