/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import vo.ClCodigosLdVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "CL_CODIGOS_LD")
public class ClCodigosLd implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Id
    @Basic(optional = false)
    @Column(name = "CLN_CODIGO")
    private int clnCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "CLN_CODIGO_LD")
    @Basic(optional = false)
    private int clnCodigoLd;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "CLT_OBSERVACIONES")
    private String cltObservaciones;

    public ClCodigosLd() {
    }
    
    public ClCodigosLdVO toVO() {
        ClCodigosLdVO vo = new ClCodigosLdVO();
        vo.setClnCodigo(this.getClnCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setClnCodigoLd(this.getClnCodigoLd());
        vo.setCltObservaciones(this.getCltObservaciones());
        return vo;
    }

    public int getClnCodigo() {
        return clnCodigo;
    }

    public void setClnCodigo(int clnCodigo) {
        this.clnCodigo = clnCodigo;
    }

    public int getClnCodigoLd() {
        return clnCodigoLd;
    }

    public void setClnCodigoLd(int clnCodigoLd) {
        this.clnCodigoLd = clnCodigoLd;
    }

    public String getCltObservaciones() {
        return cltObservaciones;
    }

    public void setCltObservaciones(String cltObservaciones) {
        this.cltObservaciones = cltObservaciones;
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

}
