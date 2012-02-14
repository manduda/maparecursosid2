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
import vo.CdCodigosMncVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "CD_CODIGOS_MNC")
public class CdCodigosMnc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "CDN_CODIGO")
    private int cdnCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @JoinColumn(name = "MCN_CODIGO", referencedColumnName = "MCN_CODIGO")
    @ManyToOne(optional = false)
    private McMcc mcnCodigo;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "CDN_MNC")
    @Basic(optional = false)
    private int cdnMnc;
    
    @Column(name = "CDT_OBSERVACIONES")
    private String cdtObservaciones;
    
    public CdCodigosMnc() {
    }
    
    public CdCodigosMncVO toVO() {
        CdCodigosMncVO vo = new CdCodigosMncVO();
        vo.setCdnCodigo(this.getCdnCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setMcnCodigo(this.getMcnCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setCdnMnc(this.getCdnMnc());
        vo.setCdtObservaciones(this.getCdtObservaciones());
        
        return vo;
    }

    public int getCdnCodigo() {
        return cdnCodigo;
    }

    public void setCdnCodigo(int cdnCodigo) {
        this.cdnCodigo = cdnCodigo;
    }

    public String getCdtObservaciones() {
        return cdtObservaciones;
    }

    public void setCdtObservaciones(String cdtObservaciones) {
        this.cdtObservaciones = cdtObservaciones;
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

    public McMcc getMcnCodigo() {
        return mcnCodigo;
    }

    public void setMcnCodigo(McMcc mcnCodigo) {
        this.mcnCodigo = mcnCodigo;
    }

    public int getCdnMnc() {
        return cdnMnc;
    }

    public void setCdnMnc(int cdnMnc) {
        this.cdnMnc = cdnMnc;
    }
    
}
