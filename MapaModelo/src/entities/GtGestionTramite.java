/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import vo.EtEstadoTramiteVO;
import vo.GtGestionTramiteVO;
import vo.TrTramitesVO;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "GT_GESTION_TRAMITE")
public class GtGestionTramite implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "GTN_CODIGO")
    private int gtnCodigo;
    
    @JoinColumn(name = "USN_CODIGO", referencedColumnName = "USN_CODIGO")
    @ManyToOne(optional = false)
    private UsUsuarios usnCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;

    @JoinColumn(name = "ETN_CODIGO", referencedColumnName = "ETN_CODIGO")
    @ManyToOne(optional = false)
    private EtEstadoTramite etnCodigo;
    
    @Basic(optional = false)
    @Column(name = "GTF_FECHA")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date gtfFecha;
    
    @Column(name = "GTT_OBSERVACIONES")
    @Basic(optional = true)
    private String gttObservaciones;
        
    public GtGestionTramite() {
    }
    
    public GtGestionTramiteVO toVO(){
        GtGestionTramiteVO vo = new GtGestionTramiteVO();
        // Usuario
        UsUsuariosVO userVO = new UsUsuariosVO();
        userVO = this.getUsnCodigo().toVO();
        // Trámite
        //TrTramitesVO tramiteVO = new TrTramitesVO();
        //tramiteVO = this.getTrnCodigo().toVO();
        // Estado trámite
        EtEstadoTramiteVO estadoTramiteVO = new EtEstadoTramiteVO();
        estadoTramiteVO = this.getEtnCodigo().toVO();
        
        vo.setGtnCodigo(this.getGtnCodigo());
        vo.setUsnCodigo(userVO);
        //vo.setTrnCodigo(tramiteVO);
        vo.setEtnCodigo(estadoTramiteVO);
        vo.setGtfFecha(this.getGtfFecha());
        vo.setGttObservaciones(this.getGttObservaciones());
        
        return vo;
    }

    
    public EtEstadoTramite getEtnCodigo() {
        return etnCodigo;
    }

    public void setEtnCodigo(EtEstadoTramite etnCodigo) {
        this.etnCodigo = etnCodigo;
    }

    public Date getGtfFecha() {
        return gtfFecha;
    }

    public void setGtfFecha(Date gtfFecha) {
        this.gtfFecha = gtfFecha;
    }

    public int getGtnCodigo() {
        return gtnCodigo;
    }

    public void setGtnCodigo(int gtnCodigo) {
        this.gtnCodigo = gtnCodigo;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public UsUsuarios getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(UsUsuarios usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

    public String getGttObservaciones() {
        return gttObservaciones;
    }

    public void setGttObservaciones(String gttObservaciones) {
        this.gttObservaciones = gttObservaciones;
    }

}
