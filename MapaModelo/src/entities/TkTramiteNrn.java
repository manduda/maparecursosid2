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
import vo.TkTramiteNrnVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TK_TRAMITE_NRN")
public class TkTramiteNrn implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "TKN_CODIGO")
    private int tknCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "NRN_CODIGO", referencedColumnName = "NRN_CODIGO")
    @ManyToOne(optional = false)
    private NrCodigosNrn nrnCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TKN_RADICADO")
    private int tknRadicado;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "TKT_OBSERVACIONES")
    private String tktObservaciones;
    
    @Column(name = "TKT_RESERVA_TEMPORAL")
    private char tktReservaTemporal;
    
    @Column(name = "TKN_MESES_LIBERACION")
    private int tknMesesLiberacion;
    
    public TkTramiteNrn(){
        
    }
    
     public TkTramiteNrnVO toVO(){
        TkTramiteNrnVO vo = new TkTramiteNrnVO();
        vo.setTknCodigo(this.getTknCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setNrnCodigo(this.getNrnCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTknRadicado(this.getTknRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setTktObservaciones(this.getTktObservaciones());
        vo.setTknMesesLiberacion(this.getTknMesesLiberacion());
        vo.setTktReservaTemporal(this.getTktReservaTemporal());
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

    public NrCodigosNrn getNrnCodigo() {
        return nrnCodigo;
    }

    public void setNrnCodigo(NrCodigosNrn nrnCodigo) {
        this.nrnCodigo = nrnCodigo;
    }

    public int getTknCodigo() {
        return tknCodigo;
    }

    public void setTknCodigo(int tknCodigo) {
        this.tknCodigo = tknCodigo;
    }

    public int getTknMesesLiberacion() {
        return tknMesesLiberacion;
    }

    public void setTknMesesLiberacion(int tknMesesLiberacion) {
        this.tknMesesLiberacion = tknMesesLiberacion;
    }

    public int getTknRadicado() {
        return tknRadicado;
    }

    public void setTknRadicado(int tknRadicado) {
        this.tknRadicado = tknRadicado;
    }

    public String getTktObservaciones() {
        return tktObservaciones;
    }

    public void setTktObservaciones(String tktObservaciones) {
        this.tktObservaciones = tktObservaciones;
    }

    public char getTktReservaTemporal() {
        return tktReservaTemporal;
    }

    public void setTktReservaTemporal(char tktReservaTemporal) {
        this.tktReservaTemporal = tktReservaTemporal;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
     
}
