/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import vo.TcTramiteCcVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TC_TRAMITE_CC")
public class TcTramiteCc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TCN_CODIGO")
    private int tcnCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "CCN_CODIGO", referencedColumnName = "CCN_CODIGO")
    @ManyToOne(optional = false)
    private CcCodigosCortos ccnCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TCN_RADICADO")
    private int tcnRadicado;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "TCT_OBSERVACIONES")
    private String tctObservaciones;
    
    @Column(name = "TCT_RESERVA_TEMPORAL")
    private char tctReservaTemporal;
    
    @Column(name = "TCN_MESES_LIBERACION")
    private int tcnMesesLiberacion;
    
    public TcTramiteCc() {
    }
    
    public TcTramiteCcVO toVO(){
        TcTramiteCcVO vo = new TcTramiteCcVO();
        vo.setTcnCodigo(this.getTcnCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setCcnCodigo(this.getCcnCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTcnRadicado(this.getTcnRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setTctObservaciones(this.getTctObservaciones());
        vo.setTcnMesesLiberacion(this.getTcnMesesLiberacion());
        vo.setTctReservaTemporal(this.getTctReservaTemporal());
        return vo;
    }

    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CcCodigosCortos getCcnCodigo() {
        return ccnCodigo;
    }

    public void setCcnCodigo(CcCodigosCortos ccnCodigo) {
        this.ccnCodigo = ccnCodigo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getTcnCodigo() {
        return tcnCodigo;
    }

    public void setTcnCodigo(int tcnCodigo) {
        this.tcnCodigo = tcnCodigo;
    }

    public int getTcnRadicado() {
        return tcnRadicado;
    }

    public void setTcnRadicado(int tcnRadicado) {
        this.tcnRadicado = tcnRadicado;
    }

    public String getTctObservaciones() {
        return tctObservaciones;
    }

    public void setTctObservaciones(String tctObservaciones) {
        this.tctObservaciones = tctObservaciones;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public int getTcnMesesLiberacion() {
        return tcnMesesLiberacion;
    }

    public void setTcnMesesLiberacion(int tcnMesesLiberacion) {
        this.tcnMesesLiberacion = tcnMesesLiberacion;
    }

    public char getTctReservaTemporal() {
        return tctReservaTemporal;
    }

    public void setTctReservaTemporal(char tctReservaTemporal) {
        this.tctReservaTemporal = tctReservaTemporal;
    }
    
}
