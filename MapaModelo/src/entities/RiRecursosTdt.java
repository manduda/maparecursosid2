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
import javax.persistence.OneToOne;
import javax.persistence.Table;
import vo.RaRegionesTdtVO;
import vo.RiRecursosTdtVO;


/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "RI_RECURSOS_TDT")
public class RiRecursosTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "RIN_CODIGO")
    private int rinCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "RIN_CODIGO_RECURSO")
    @Basic(optional = false)
    private int rinCodigoRecurso;
    
    @Column(name = "RIT_NOMBRE_RECURSO")
    @Basic(optional = false)
    private String ritNombreRecurso;
    
    @JoinColumn(name = "RNN_CODIGO", referencedColumnName = "RNN_CODIGO")
    @ManyToOne(optional = false)
    private RnTipoRecursoTdt rnnCodigo;
    
    @JoinColumn(name = "RRN_CODIGO", referencedColumnName = "RRN_CODIGO")
    @ManyToOne(optional = false)
    private RrTipoRedTdt rrnCodigo;
    
    @JoinColumn(name = "CAN_CODIGO", referencedColumnName = "CAN_CODIGO")
    @ManyToOne(optional = false)
    private CaCanalTdt canCodigo;
    
    @Column(name = "RIT_OBSERVACIONES")
    private String ritObservaciones;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rinCodigo")
    private Collection<RaRegionesTdt> raRegionesTdtCollection;
    
    @OneToOne(optional = true, mappedBy = "rinCodigo")
    //@JoinColumn(name="RIN_CODIGO", referencedColumnName="RIN_CODIGO")
    private StServiciosTdt servicioCodigo;
    
    @OneToOne(optional = true, mappedBy = "rinCodigo")
    //@JoinColumn(name="RIN_CODIGO", referencedColumnName="RIN_CODIGO")
    private MrMultiplexRecurso multiplexCodigo;
    
    public RiRecursosTdt() {
    }
    
    public RiRecursosTdtVO toVO(){
        RiRecursosTdtVO vo = new RiRecursosTdtVO();
        vo.setRinCodigo(this.getRinCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.esnCodigo.toVO());
        vo.setRinCodigoRecurso(this.getRinCodigoRecurso());
        vo.setRitNombreRecurso(this.getRitNombreRecurso());
        vo.setRnnCodigo(this.getRnnCodigo().toVO());
        vo.setRrnCodigo(this.getRrnCodigo().toVO());
        vo.setCanCodigo(this.getCanCodigo().toVO());
        vo.setRitObservaciones(this.getRitObservaciones());
        Collection<RaRegionesTdtVO> regionesTdtVO = new ArrayList<RaRegionesTdtVO>();        
        for (RaRegionesTdt ra : this.getRaRegionesTdtCollection()) {
            regionesTdtVO.add(ra.toVO());
        }
        if (this.getServicioCodigo() != null){
            vo.setServicioCodigo(this.getServicioCodigo().toVO());
        }
        if (this.getMultiplexCodigo() != null){
            vo.setMultiplexCodigo(this.getMultiplexCodigo().toVO());
        }
        return vo;
    }
    
    public RiRecursosTdtVO toVOsinDetalles(){
        RiRecursosTdtVO vo = new RiRecursosTdtVO();
        vo.setRinCodigo(this.getRinCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.esnCodigo.toVO());
        vo.setRinCodigoRecurso(this.getRinCodigoRecurso());
        vo.setRitNombreRecurso(this.getRitNombreRecurso());
        vo.setRnnCodigo(this.getRnnCodigo().toVO());
        vo.setRrnCodigo(this.getRrnCodigo().toVO());
        vo.setCanCodigo(this.getCanCodigo().toVO());
        vo.setRitObservaciones(this.getRitObservaciones());
        return vo;
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

    public EsEstado getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstado esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public int getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(int rinCodigo) {
        this.rinCodigo = rinCodigo;
    }

    public int getRinCodigoRecurso() {
        return rinCodigoRecurso;
    }

    public void setRinCodigoRecurso(int rinCodigoRecurso) {
        this.rinCodigoRecurso = rinCodigoRecurso;
    }

    public String getRitNombreRecurso() {
        return ritNombreRecurso;
    }

    public void setRitNombreRecurso(String ritNombreRecurso) {
        this.ritNombreRecurso = ritNombreRecurso;
    }

    public String getRitObservaciones() {
        return ritObservaciones;
    }

    public void setRitObservaciones(String ritObservaciones) {
        this.ritObservaciones = ritObservaciones;
    }

    public RnTipoRecursoTdt getRnnCodigo() {
        return rnnCodigo;
    }

    public void setRnnCodigo(RnTipoRecursoTdt rnnCodigo) {
        this.rnnCodigo = rnnCodigo;
    }

    public RrTipoRedTdt getRrnCodigo() {
        return rrnCodigo;
    }

    public void setRrnCodigo(RrTipoRedTdt rrnCodigo) {
        this.rrnCodigo = rrnCodigo;
    }

    public Collection<RaRegionesTdt> getRaRegionesTdtCollection() {
        return raRegionesTdtCollection;
    }

    public void setRaRegionesTdtCollection(Collection<RaRegionesTdt> raRegionesTdtCollection) {
        this.raRegionesTdtCollection = raRegionesTdtCollection;
    }

    public MrMultiplexRecurso getMultiplexCodigo() {
        return multiplexCodigo;
    }

    public void setMultiplexCodigo(MrMultiplexRecurso multiplexCodigo) {
        this.multiplexCodigo = multiplexCodigo;
    }

    public StServiciosTdt getServicioCodigo() {
        return servicioCodigo;
    }

    public void setServicioCodigo(StServiciosTdt servicioCodigo) {
        this.servicioCodigo = servicioCodigo;
    }

}
