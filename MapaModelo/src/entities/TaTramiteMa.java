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
import vo.TaTramiteMaVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TA_TRAMITE_MA")
public class TaTramiteMa implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TAN_CODIGO")
    private int tanCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "MAN_CODIGO", referencedColumnName = "MAN_CODIGO")
    @ManyToOne(optional = false)
    private MaMarcacionAbreviada manCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TAN_RADICADO")
    private int tanRadicado;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "TAT_OBSERVACIONES")
    private String tatObservaciones;
    
    @Column(name = "TAT_RESERVA_TEMPORAL")
    private char tatReservaTemporal;
    
    @Column(name = "TAN_MESES_LIBERACION")
    private int tanMesesLiberacion;

    public TaTramiteMa(){
    }
    
    public TaTramiteMaVO toVO(){
        TaTramiteMaVO vo = new TaTramiteMaVO();
        vo.setTanCodigo(this.getTanCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setManCodigo(this.getManCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTanRadicado(this.getTanRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setTatObservaciones(this.getTatObservaciones());
        vo.setTanMesesLiberacion(this.getTanMesesLiberacion());
        vo.setTatReservaTemporal(this.getTatReservaTemporal());
        return vo;
    }

    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public MaMarcacionAbreviada getManCodigo() {
        return manCodigo;
    }

    public void setManCodigo(MaMarcacionAbreviada manCodigo) {
        this.manCodigo = manCodigo;
    }

    public int getTanCodigo() {
        return tanCodigo;
    }

    public void setTanCodigo(int tanCodigo) {
        this.tanCodigo = tanCodigo;
    }

    public int getTanMesesLiberacion() {
        return tanMesesLiberacion;
    }

    public void setTanMesesLiberacion(int tanMesesLiberacion) {
        this.tanMesesLiberacion = tanMesesLiberacion;
    }

    public int getTanRadicado() {
        return tanRadicado;
    }

    public void setTanRadicado(int tanRadicado) {
        this.tanRadicado = tanRadicado;
    }

    public String getTatObservaciones() {
        return tatObservaciones;
    }

    public void setTatObservaciones(String tatObservaciones) {
        this.tatObservaciones = tatObservaciones;
    }

    public char getTatReservaTemporal() {
        return tatReservaTemporal;
    }

    public void setTatReservaTemporal(char tatReservaTemporal) {
        this.tatReservaTemporal = tatReservaTemporal;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
    
}
