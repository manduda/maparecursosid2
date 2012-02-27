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
import vo.TiTramiteIinVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TI_TRAMITE_IIN")
public class TiTramiteIin implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "TIN_CODIGO")
    private int tinCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "CIN_CODIGO", referencedColumnName = "CIN_CODIGO")
    @ManyToOne(optional = false)
    private CiCodigosIin cinCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TIN_RADICADO")
    private int tinRadicado;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "TIT_OBSERVACIONES")
    private String titObservaciones;
    
    @Column(name = "TIT_RESERVA_TEMPORAL")
    private char titReservaTemporal;
    
    @Column(name = "TIN_MESES_LIBERACION")
    private int tinMesesLiberacion;
    
    public TiTramiteIin(){
        
    }
    
     public TiTramiteIinVO toVO(){
        TiTramiteIinVO vo = new TiTramiteIinVO();
        vo.setTinCodigo(this.getTinCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setCinCodigo(this.getCinCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTinRadicado(this.getTinRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setTitObservaciones(this.getTitObservaciones());
        vo.setTinMesesLiberacion(this.getTinMesesLiberacion());
        vo.setTitReservaTemporal(this.getTitReservaTemporal());
        return vo;
    }

    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CiCodigosIin getCinCodigo() {
        return cinCodigo;
    }

    public void setCinCodigo(CiCodigosIin cinCodigo) {
        this.cinCodigo = cinCodigo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getTinCodigo() {
        return tinCodigo;
    }

    public void setTinCodigo(int tinCodigo) {
        this.tinCodigo = tinCodigo;
    }

    public int getTinMesesLiberacion() {
        return tinMesesLiberacion;
    }

    public void setTinMesesLiberacion(int tinMesesLiberacion) {
        this.tinMesesLiberacion = tinMesesLiberacion;
    }

    public int getTinRadicado() {
        return tinRadicado;
    }

    public void setTinRadicado(int tinRadicado) {
        this.tinRadicado = tinRadicado;
    }

    public String getTitObservaciones() {
        return titObservaciones;
    }

    public void setTitObservaciones(String titObservaciones) {
        this.titObservaciones = titObservaciones;
    }

    public char getTitReservaTemporal() {
        return titReservaTemporal;
    }

    public void setTitReservaTemporal(char titReservaTemporal) {
        this.titReservaTemporal = titReservaTemporal;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

}
