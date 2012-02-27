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
import vo.NrCodigosNrnVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "NR_CODIGOS_NRN")
public class NrCodigosNrn implements Serializable {
    private static final long serialVersionUID = 1L;
   
    @Id
    @Basic(optional = false)
    @Column(name = "NRN_CODIGO")
    private int nrnCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "NRN_CODIGO_NRN")
    @Basic(optional = false)
    private int nrnCodigoNrn;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "NRT_OBSERVACIONES")
    private String nrtObservaciones;
    
    public NrCodigosNrn(){
        
    }
    
    public NrCodigosNrnVO toVO() {
        NrCodigosNrnVO vo = new NrCodigosNrnVO();
        vo.setNrnCodigo(this.getNrnCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setNrnCodigoNrn(this.getNrnCodigoNrn());
        vo.setNrtObservaciones(this.getNrtObservaciones());
        return vo;
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

    public int getNrnCodigo() {
        return nrnCodigo;
    }

    public void setNrnCodigo(int nrnCodigo) {
        this.nrnCodigo = nrnCodigo;
    }

    public int getNrnCodigoNrn() {
        return nrnCodigoNrn;
    }

    public void setNrnCodigoNrn(int nrnCodigoNrn) {
        this.nrnCodigoNrn = nrnCodigoNrn;
    }

    public String getNrtObservaciones() {
        return nrtObservaciones;
    }

    public void setNrtObservaciones(String nrtObservaciones) {
        this.nrtObservaciones = nrtObservaciones;
    }
}
