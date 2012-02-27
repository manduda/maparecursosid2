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
import vo.CiCodigosIinVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "CI_CODIGOS_IIN")
public class CiCodigosIin implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "CIN_CODIGO")
    private int cinCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "CIN_CODIGO_IIN")
    @Basic(optional = false)
    private int cinCodigoIin;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "CIT_OBSERVACIONES")
    private String citObservaciones;
    
    public CiCodigosIin(){
        
    }
    
    public CiCodigosIinVO toVO() {
        CiCodigosIinVO vo = new CiCodigosIinVO();
        vo.setCinCodigo(this.getCinCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setCinCodigoIin(this.getCinCodigoIin());
        vo.setCitObservaciones(this.getCitObservaciones());
        return vo;
    }

    public int getCinCodigo() {
        return cinCodigo;
    }

    public void setCinCodigo(int cinCodigo) {
        this.cinCodigo = cinCodigo;
    }

    public String getCitObservaciones() {
        return citObservaciones;
    }

    public void setCitObservaciones(String citObservaciones) {
        this.citObservaciones = citObservaciones;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public EsEstado getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstado esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public int getCinCodigoIin() {
        return cinCodigoIin;
    }

    public void setCinCodigoIin(int cinCodigoIin) {
        this.cinCodigoIin = cinCodigoIin;
    }

}