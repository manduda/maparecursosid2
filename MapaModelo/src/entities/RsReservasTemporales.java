/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
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
import vo.EsEstadoVO;
import vo.GtGestionTramiteVO;
import vo.ReRegionVO;
import vo.RsReservasTemporalesVO;
import vo.SeSenalizacionVO;
import vo.TeTipoSenalizacionVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "RS_RESERVAS_TEMPORALES")
public class RsReservasTemporales implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "RSN_CODIGO")
    private int rsnCodigo;
    
    @Basic(optional = false)
    @Column(name = "RSN_CODIGO_RECURSO")
    private int rsnCodigoRecurso;

    @Basic(optional = false)
    @Column(name = "RST_TIPO_RECURSO")
    private String rstTipoRecurso;

    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
      
    @Basic(optional = false)
    @Column(name = "RSN_ESTADO")
    private char rsnEstado;
    
    @Basic(optional = false)
    @Column(name = "RSF_FECHA_LIBERACION")
    @Temporal(javax.persistence.TemporalType.TIMESTAMP)
    private Date rsfFechaLiberacion;
    
        
    public RsReservasTemporales() {
    }
    
    public RsReservasTemporalesVO toVO() {
        RsReservasTemporalesVO vo = new RsReservasTemporalesVO();
        
        vo.setRsfFechaLiberacion(this.getRsfFechaLiberacion());
        vo.setRsnCodigo(this.getRsnCodigo());
        vo.setRsnCodigoRecurso(this.getRsnCodigoRecurso());
        vo.setRsnEstado(this.getRsnEstado());
        vo.setTrnCodigo(this.getTrnCodigo().toVO());
        vo.setRstTipoRecurso(this.getRstTipoRecurso());

        return vo;
    }

    public Date getRsfFechaLiberacion() {
        return rsfFechaLiberacion;
    }

    public void setRsfFechaLiberacion(Date rsfFechaLiberacion) {
        this.rsfFechaLiberacion = rsfFechaLiberacion;
    }

    public int getRsnCodigo() {
        return rsnCodigo;
    }

    public void setRsnCodigo(int rsnCodigo) {
        this.rsnCodigo = rsnCodigo;
    }

    public int getRsnCodigoRecurso() {
        return rsnCodigoRecurso;
    }

    public void setRsnCodigoRecurso(int rsnCodigoRecurso) {
        this.rsnCodigoRecurso = rsnCodigoRecurso;
    }

    public char getRsnEstado() {
        return rsnEstado;
    }

    public void setRsnEstado(char rsnEstado) {
        this.rsnEstado = rsnEstado;
    }

    public String getRstTipoRecurso() {
        return rstTipoRecurso;
    }

    public void setRstTipoRecurso(String rstTipoRecurso) {
        this.rstTipoRecurso = rstTipoRecurso;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

}
