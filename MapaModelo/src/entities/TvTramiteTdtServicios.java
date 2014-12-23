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
import vo.TvTramiteTdtServiciosVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TV_TRAMITE_TDT_SERVICIOS")
public class TvTramiteTdtServicios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TVN_CODIGO")
    private int tvnCodigo;
    
    @JoinColumn(name = "TDN_CODIGO", referencedColumnName = "TDN_CODIGO")
    @ManyToOne(optional = false)
    private TdTramiteTdt tdnCodigo;
    
    @JoinColumn(name = "TTN_CODIGO", referencedColumnName = "TTN_CODIGO")
    @ManyToOne(optional = false)
    private TtTipoServicioTdt ttnCodigo;
    
    public TvTramiteTdtServicios() {
    }
    
    public TvTramiteTdtServiciosVO toVO(){
        TvTramiteTdtServiciosVO vo = new TvTramiteTdtServiciosVO();
        vo.setTvnCodigo(this.getTvnCodigo());
        vo.setTdnCodigo(this.getTdnCodigo().toVOsinDetalles());
        vo.setTtnCodigo(this.getTtnCodigo().toVO());
        return vo;
    }

    public TdTramiteTdt getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(TdTramiteTdt tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public TtTipoServicioTdt getTtnCodigo() {
        return ttnCodigo;
    }

    public void setTtnCodigo(TtTipoServicioTdt ttnCodigo) {
        this.ttnCodigo = ttnCodigo;
    }

    public int getTvnCodigo() {
        return tvnCodigo;
    }

    public void setTvnCodigo(int tvnCodigo) {
        this.tvnCodigo = tvnCodigo;
    }
    
}
