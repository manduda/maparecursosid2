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
import vo.RaRegionesTdtVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "RA_REGIONES_TDT")
public class RaRegionesTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "RAN_CODIGO")
    private int ranCodigo;
    
    @JoinColumn(name = "RIN_CODIGO", referencedColumnName = "RIN_CODIGO")
    @ManyToOne(optional = false)
    private RiRecursosTdt rinCodigo;
    
    @JoinColumn(name = "SK_REGION_CODE", referencedColumnName = "CODIGO_MUNICIPIO")
    @ManyToOne(optional = false)
    private Municipios codigoMunicipio;
    
    public RaRegionesTdt() {
    }
    
    public RaRegionesTdtVO toVO(){
        RaRegionesTdtVO vo = new RaRegionesTdtVO();
        vo.setRanCodigo(this.getRanCodigo());
        vo.setRinCodigo(this.getRinCodigo().toVOsinDetalles());
        vo.setCodigoMunicipio(this.getCodigoMunicipio().toVO());
        return vo;
    }

    public Municipios getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipios codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public int getRanCodigo() {
        return ranCodigo;
    }

    public void setRanCodigo(int ranCodigo) {
        this.ranCodigo = ranCodigo;
    }

    public RiRecursosTdt getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdt rinCodigo) {
        this.rinCodigo = rinCodigo;
    }
    
}
