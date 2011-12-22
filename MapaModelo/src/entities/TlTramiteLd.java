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
import vo.TlTramiteLdVO;

/**
 *
 * @author MADD
 */
@Entity
@Table(name = "TL_TRAMITE_LD")
public class TlTramiteLd implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TLN_CODIGO")
    private int tlnCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "CLN_CODIGO", referencedColumnName = "CLN_CODIGO")
    @ManyToOne(optional = false)
    private ClCodigosLd clnCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TLN_RADICADO")
    private int tlnRadicado;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "TLT_OBSERVACIONES")
    private String tltObservaciones;
    
    @Column(name = "TLT_RESERVA_TEMPORAL")
    private char tltReservaTemporal;
    
    @Column(name = "TLN_MESES_LIBERACION")
    private int tlnMesesLiberacion;

    public TlTramiteLd(){
    }
    
    public TlTramiteLdVO toVO(){
        TlTramiteLdVO vo = new TlTramiteLdVO();
        vo.setTlnCodigo(this.getTlnCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setClnCodigo(this.getClnCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTlnRadicado(this.getTlnRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setTltObservaciones(this.getTltObservaciones());
        vo.setTlnMesesLiberacion(this.getTlnMesesLiberacion());
        vo.setTltReservaTemporal(this.getTltReservaTemporal());
        return vo;
    }

    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public ClCodigosLd getClnCodigo() {
        return clnCodigo;
    }

    public void setClnCodigo(ClCodigosLd clnCodigo) {
        this.clnCodigo = clnCodigo;
    }

    public int getTlnCodigo() {
        return tlnCodigo;
    }

    public void setTlnCodigo(int tlnCodigo) {
        this.tlnCodigo = tlnCodigo;
    }

    public int getTlnRadicado() {
        return tlnRadicado;
    }

    public void setTlnRadicado(int tlnRadicado) {
        this.tlnRadicado = tlnRadicado;
    }

    public String getTltObservaciones() {
        return tltObservaciones;
    }

    public void setTltObservaciones(String tltObservaciones) {
        this.tltObservaciones = tltObservaciones;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getTlnMesesLiberacion() {
        return tlnMesesLiberacion;
    }

    public void setTlnMesesLiberacion(int tlnMesesLiberacion) {
        this.tlnMesesLiberacion = tlnMesesLiberacion;
    }

    public char getTltReservaTemporal() {
        return tltReservaTemporal;
    }

    public void setTltReservaTemporal(char tltReservaTemporal) {
        this.tltReservaTemporal = tltReservaTemporal;
    }
    
}
