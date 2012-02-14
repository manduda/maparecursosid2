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
import vo.TmTramiteMncVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TM_TRAMITE_MNC")
public class TmTramiteMnc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TMN_CODIGO")
    private int tmnCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "CDN_CODIGO", referencedColumnName = "CDN_CODIGO")
    @ManyToOne(optional = false)
    private CdCodigosMnc cdnCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TMN_RADICADO")
    private int tmnRadicado;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "TMT_OBSERVACIONES")
    private String tmtObservaciones;
    
    @Column(name = "TMT_RESERVA_TEMPORAL")
    private char tmtReservaTemporal;
    
    @Column(name = "TMN_MESES_LIBERACION")
    private int tmnMesesLiberacion;

    public TmTramiteMnc(){
    }
    
    public TmTramiteMncVO toVO(){
        TmTramiteMncVO vo = new TmTramiteMncVO();
        vo.setTmnCodigo(this.getTmnCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setCdnCodigo(this.getCdnCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTmnRadicado(this.getTmnRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setTmtObservaciones(this.getTmtObservaciones());
        vo.setTmnMesesLiberacion(this.getTmnMesesLiberacion());
        vo.setTmtReservaTemporal(this.getTmtReservaTemporal());
        return vo;
    }

    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CdCodigosMnc getCdnCodigo() {
        return cdnCodigo;
    }

    public void setCdnCodigo(CdCodigosMnc cdnCodigo) {
        this.cdnCodigo = cdnCodigo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getTmnCodigo() {
        return tmnCodigo;
    }

    public void setTmnCodigo(int tmnCodigo) {
        this.tmnCodigo = tmnCodigo;
    }

    public int getTmnMesesLiberacion() {
        return tmnMesesLiberacion;
    }

    public void setTmnMesesLiberacion(int tmnMesesLiberacion) {
        this.tmnMesesLiberacion = tmnMesesLiberacion;
    }

    public int getTmnRadicado() {
        return tmnRadicado;
    }

    public void setTmnRadicado(int tmnRadicado) {
        this.tmnRadicado = tmnRadicado;
    }

    public String getTmtObservaciones() {
        return tmtObservaciones;
    }

    public void setTmtObservaciones(String tmtObservaciones) {
        this.tmtObservaciones = tmtObservaciones;
    }

    public char getTmtReservaTemporal() {
        return tmtReservaTemporal;
    }

    public void setTmtReservaTemporal(char tmtReservaTemporal) {
        this.tmtReservaTemporal = tmtReservaTemporal;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
    
    
}
