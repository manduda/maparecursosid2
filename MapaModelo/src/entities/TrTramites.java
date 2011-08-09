/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.persistence.Temporal;
import vo.EmOperadorVO;
import vo.EtEstadoTramiteVO;
import vo.GtGestionTramiteVO;
import vo.TrTramitesVO;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TR_TRAMITES")
public class TrTramites implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TRN_CODIGO")
    private int trnCodigo;
    
    @JoinColumn(name = "USN_CODIGO", referencedColumnName = "USN_CODIGO")
    @ManyToOne(optional = false)
    private UsUsuarios usnCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @JoinColumn(name = "ETN_CODIGO", referencedColumnName = "ETN_CODIGO")
    @ManyToOne(optional = false)
    private EtEstadoTramite etnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TRF_FECHA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date trfFecha;
    
    @Column(name = "TRN_RESOLUCION")
    private int trnResolucion;
    
    @Column(name = "TRF_FECHA_RESOLUCION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date trfFechaResolucion;
    
    @Column(name = "TRT_OBSERVACIONES")
    private String trtObservaciones;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "trnCodigo")
    private Collection<GtGestionTramite> gtGetionTramiteCollection;
        
    public TrTramites() {
    }

    public TrTramitesVO toVO(){
        TrTramitesVO vo = new TrTramitesVO();
        // Datos Usuario
        UsUsuariosVO userVO = new UsUsuariosVO();
        userVO = this.getUsnCodigo().toVO();
        // Operador
        EmOperadorVO operadorVO = new EmOperadorVO();
        operadorVO = this.getEmrCodigo().toVO();
        // Estado trámite
        EtEstadoTramiteVO estadoTramiteVO = new EtEstadoTramiteVO();
        estadoTramiteVO = this.getEtnCodigo().toVO();
        // Gestion trámite
        Collection<GtGestionTramiteVO> getionTramitesVO = new ArrayList<GtGestionTramiteVO>();        
        for (GtGestionTramite gt : this.gtGetionTramiteCollection) {
            getionTramitesVO.add(gt.toVO());
        }
        
        vo.setTrnCodigo(this.getTrnCodigo());
        vo.setUsnCodigo(userVO);
        vo.setEmrCodigo(operadorVO);
        vo.setEtnCodigo(estadoTramiteVO);
        vo.setTrfFecha(this.getTrfFecha());
        vo.setTrnResolucion(this.getTrnResolucion());
        vo.setTrfFechaResolucion(this.getTrfFechaResolucion());
        vo.setTrtObservaciones(this.getTrtObservaciones());
        vo.setGtGetionTramiteCollection(getionTramitesVO);
        
        return vo;
    }
    
    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public EtEstadoTramite getEtnCodigo() {
        return etnCodigo;
    }

    public void setEtnCodigo(EtEstadoTramite etnCodigo) {
        this.etnCodigo = etnCodigo;
    }

    public Date getTrfFecha() {
        return trfFecha;
    }

    public void setTrfFecha(Date trfFecha) {
        this.trfFecha = trfFecha;
    }

    public Date getTrfFechaResolucion() {
        return trfFechaResolucion;
    }

    public void setTrfFechaResolucion(Date trfFechaResolucion) {
        this.trfFechaResolucion = trfFechaResolucion;
    }

    public int getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(int trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public int getTrnResolucion() {
        return trnResolucion;
    }

    public void setTrnResolucion(int trnResolucion) {
        this.trnResolucion = trnResolucion;
    }

    public String getTrtObservaciones() {
        return trtObservaciones;
    }

    public void setTrtObservaciones(String trtObservaciones) {
        this.trtObservaciones = trtObservaciones;
    }

    public UsUsuarios getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(UsUsuarios usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

    public Collection<GtGestionTramite> getGtGetionTramiteCollection() {
        return gtGetionTramiteCollection;
    }

    public void setGtGetionTramiteCollection(Collection<GtGestionTramite> gtGetionTramiteCollection) {
        this.gtGetionTramiteCollection = gtGetionTramiteCollection;
    }

}
