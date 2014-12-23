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
import vo.TgTramiteTdtRegionesVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TG_TRAMITE_TDT_REGIONES")
public class TgTramiteTdtRegiones implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TGN_CODIGO")
    private int tgnCodigo;
    
    @JoinColumn(name = "TDN_CODIGO", referencedColumnName = "TDN_CODIGO")
    @ManyToOne(optional = false)
    private TdTramiteTdt tdnCodigo;
    
    @JoinColumn(name = "SK_REGION_CODE", referencedColumnName = "CODIGO_MUNICIPIO")
    @ManyToOne(optional = false)
    private Municipios codigoMunicipio;
    
    public TgTramiteTdtRegiones() {
    }
    
    public TgTramiteTdtRegionesVO toVO(){
        TgTramiteTdtRegionesVO vo = new TgTramiteTdtRegionesVO();
        vo.setTgnCodigo(this.getTgnCodigo());
        vo.setTdnCodigo(this.getTdnCodigo().toVOsinDetalles());
        vo.setCodigoMunicipio(this.getCodigoMunicipio().toVO());
        return vo;
    }

    public Municipios getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipios codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public TdTramiteTdt getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(TdTramiteTdt tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public int getTgnCodigo() {
        return tgnCodigo;
    }

    public void setTgnCodigo(int tgnCodigo) {
        this.tgnCodigo = tgnCodigo;
    }
    
}
