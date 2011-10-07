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
import vo.CcCodigosCortosVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "CC_CODIGOS_CORTOS")
public class CcCodigosCortos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "CCN_CODIGO")
    private int ccnCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "CCN_CODIGO_CORTO")
    @Basic(optional = false)
    private int ccnCodigoCorto;
    
    @JoinColumn(name = "MDN_CODIGO", referencedColumnName = "MDN_CODIGO")
    @ManyToOne(optional = false)
    private MdModalidadCc mdnCodigo;
    
    @Column(name = "CCT_OBSERVACIONES")
    private String cctObservaciones;

    public CcCodigosCortos() {
    }
    
    public CcCodigosCortosVO toVO() {
        CcCodigosCortosVO vo = new CcCodigosCortosVO();
        vo.setCcnCodigo(this.getCcnCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setCcnCodigoCorto(this.getCcnCodigoCorto());
        vo.setMdnCodigo(this.getMdnCodigo().toVO());
        vo.setCctObservaciones(this.getCctObservaciones());
        return vo;
    }

    public int getCcnCodigo() {
        return ccnCodigo;
    }

    public void setCcnCodigo(int ccnCodigo) {
        this.ccnCodigo = ccnCodigo;
    }

    public int getCcnCodigoCorto() {
        return ccnCodigoCorto;
    }

    public void setCcnCodigoCorto(int ccnCodigoCorto) {
        this.ccnCodigoCorto = ccnCodigoCorto;
    }

    public String getCctObservaciones() {
        return cctObservaciones;
    }

    public void setCctObservaciones(String cctObservaciones) {
        this.cctObservaciones = cctObservaciones;
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

    public MdModalidadCc getMdnCodigo() {
        return mdnCodigo;
    }

    public void setMdnCodigo(MdModalidadCc mdnCodigo) {
        this.mdnCodigo = mdnCodigo;
    }
    
}
