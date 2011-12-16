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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.persistence.Temporal;
import vo.RsReservasTemporalesVO;

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
    @Column(name = "RST_ESTADO")
    private char rstEstado;
    
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
        vo.setRstEstado(this.getRstEstado());
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

    public char getRstEstado() {
        return rstEstado;
    }

    public void setRstEstado(char rstEstado) {
        this.rstEstado = rstEstado;
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
