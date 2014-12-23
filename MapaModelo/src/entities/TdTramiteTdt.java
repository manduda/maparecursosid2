/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import vo.TdTramiteTdtVO;
import vo.TgTramiteTdtRegionesVO;
import vo.TvTramiteTdtServiciosVO;
import vo.TxTramiteTdtMultiplexVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TR_TRAMITE_TDT")
public class TdTramiteTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TDN_CODIGO")
    private int tdnCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "RIN_CODIGO", referencedColumnName = "RIN_CODIGO")
    @ManyToOne(optional = false)
    private RiRecursosTdt rinCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TDN_RADICADO")
    private int tdnRadicado;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @JoinColumn(name = "RRN_CODIGO", referencedColumnName = "RRN_CODIGO")
    @ManyToOne(optional = false)
    private RrTipoRedTdt rrnCodigo;
    
    @JoinColumn(name = "CAN_CODIGO", referencedColumnName = "CAN_CODIGO")
    @ManyToOne(optional = false)
    private CaCanalTdt canCodigo;
    
    @Column(name = "TDT_NOMBRE_RECURSO")
    private String tdtNombreRecurso;
    
    @Column(name = "TDT_OBSERVACIONES")
    private String tdtObservaciones;
    
    @Column(name = "TDT_RESERVA_TEMPORAL")
    private char tdtReservaTemporal;
    
    @Column(name = "TDN_MESES_LIBERACION")
    private int tdnMesesLiberacion;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tdnCodigo")
    private Collection<TxTramiteTdtMultiplex> txTramiteTdtMultiplexCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tdnCodigo")
    private Collection<TvTramiteTdtServicios> tvTramiteTdtServiciosCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tdnCodigo")
    private Collection<TgTramiteTdtRegiones> tgTramiteTdtRegionesCollection;
    
    public TdTramiteTdt() {
    }
    
    public TdTramiteTdtVO toVO(){
        TdTramiteTdtVO vo = new TdTramiteTdtVO();
        vo.setTdnCodigo(this.getTdnCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setRinCodigo(this.getRinCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTdnRadicado(this.getTdnRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setRrnCodigo(this.getRrnCodigo().toVO());
        vo.setCanCodigo(this.getCanCodigo().toVO());
        vo.setTdtNombreRecurso(this.getTdtNombreRecurso());
        vo.setTdtObservaciones(this.getTdtObservaciones());
        vo.setTdnMesesLiberacion(this.getTdnMesesLiberacion());
        vo.setTdtReservaTemporal(this.getTdtReservaTemporal());
        Collection<TxTramiteTdtMultiplexVO> tramiteTdtMultiplexVO = new ArrayList<TxTramiteTdtMultiplexVO>();        
        for (TxTramiteTdtMultiplex tx : this.txTramiteTdtMultiplexCollection) {
            tramiteTdtMultiplexVO.add(tx.toVO());
        }
        Collection<TvTramiteTdtServiciosVO> tramiteTdtServiciosVO = new ArrayList<TvTramiteTdtServiciosVO>();        
        for (TvTramiteTdtServicios tv : this.tvTramiteTdtServiciosCollection) {
            tramiteTdtServiciosVO.add(tv.toVO());
        }
        Collection<TgTramiteTdtRegionesVO> tramiteTdtRegionesVO = new ArrayList<TgTramiteTdtRegionesVO>();        
        for (TgTramiteTdtRegiones tg : this.tgTramiteTdtRegionesCollection) {
            tramiteTdtRegionesVO.add(tg.toVO());
        }
        return vo;
    }
    
    public TdTramiteTdtVO toVOsinDetalles(){
        TdTramiteTdtVO vo = new TdTramiteTdtVO();
        vo.setTdnCodigo(this.getTdnCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setRinCodigo(this.getRinCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTdnRadicado(this.getTdnRadicado());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setRrnCodigo(this.getRrnCodigo().toVO());
        vo.setCanCodigo(this.getCanCodigo().toVO());
        vo.setTdtNombreRecurso(this.getTdtNombreRecurso());
        vo.setTdtObservaciones(this.getTdtObservaciones());
        vo.setTdnMesesLiberacion(this.getTdnMesesLiberacion());
        vo.setTdtReservaTemporal(this.getTdtReservaTemporal());
        return vo;
    }
    

    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CaCanalTdt getCanCodigo() {
        return canCodigo;
    }

    public void setCanCodigo(CaCanalTdt canCodigo) {
        this.canCodigo = canCodigo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public RiRecursosTdt getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdt rinCodigo) {
        this.rinCodigo = rinCodigo;
    }

    public RrTipoRedTdt getRrnCodigo() {
        return rrnCodigo;
    }

    public void setRrnCodigo(RrTipoRedTdt rrnCodigo) {
        this.rrnCodigo = rrnCodigo;
    }

    public int getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(int tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public int getTdnMesesLiberacion() {
        return tdnMesesLiberacion;
    }

    public void setTdnMesesLiberacion(int tdnMesesLiberacion) {
        this.tdnMesesLiberacion = tdnMesesLiberacion;
    }

    public int getTdnRadicado() {
        return tdnRadicado;
    }

    public void setTdnRadicado(int tdnRadicado) {
        this.tdnRadicado = tdnRadicado;
    }

    public String getTdtNombreRecurso() {
        return tdtNombreRecurso;
    }

    public void setTdtNombreRecurso(String tdtNombreRecurso) {
        this.tdtNombreRecurso = tdtNombreRecurso;
    }

    public String getTdtObservaciones() {
        return tdtObservaciones;
    }

    public void setTdtObservaciones(String tdtObservaciones) {
        this.tdtObservaciones = tdtObservaciones;
    }

    public char getTdtReservaTemporal() {
        return tdtReservaTemporal;
    }

    public void setTdtReservaTemporal(char tdtReservaTemporal) {
        this.tdtReservaTemporal = tdtReservaTemporal;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public Collection<TxTramiteTdtMultiplex> getTxTramiteTdtMultiplexCollection() {
        return txTramiteTdtMultiplexCollection;
    }

    public void setTxTramiteTdtMultiplexCollection(Collection<TxTramiteTdtMultiplex> txTramiteTdtMultiplexCollection) {
        this.txTramiteTdtMultiplexCollection = txTramiteTdtMultiplexCollection;
    }

    public Collection<TvTramiteTdtServicios> getTvTramiteTdtServiciosCollection() {
        return tvTramiteTdtServiciosCollection;
    }

    public void setTvTramiteTdtServiciosCollection(Collection<TvTramiteTdtServicios> tvTramiteTdtServiciosCollection) {
        this.tvTramiteTdtServiciosCollection = tvTramiteTdtServiciosCollection;
    }

    public Collection<TgTramiteTdtRegiones> getTgTramiteTdtRegionesCollection() {
        return tgTramiteTdtRegionesCollection;
    }

    public void setTgTramiteTdtRegionesCollection(Collection<TgTramiteTdtRegiones> tgTramiteTdtRegionesCollection) {
        this.tgTramiteTdtRegionesCollection = tgTramiteTdtRegionesCollection;
    }
    
}
