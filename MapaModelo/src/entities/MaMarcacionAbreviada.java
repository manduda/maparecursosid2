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
import vo.MaMarcacionAbreviadaVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "MA_MARCACION_ABREVIADA")
public class MaMarcacionAbreviada implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "MAN_CODIGO")
    private int manCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "MAN_CODIGO_MARCACION")
    @Basic(optional = false)
    private int manCodigoMarcacion;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @Column(name = "MAT_OBSERVACIONES")
    private String matObservaciones;
    
    public MaMarcacionAbreviada() {
    }
    
    public MaMarcacionAbreviadaVO toVO() {
        MaMarcacionAbreviadaVO vo = new MaMarcacionAbreviadaVO();
        vo.setManCodigo(this.getManCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setManCodigoMarcacion(this.getManCodigoMarcacion());
        vo.setMatObservaciones(this.getMatObservaciones());
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

    public int getManCodigo() {
        return manCodigo;
    }

    public void setManCodigo(int manCodigo) {
        this.manCodigo = manCodigo;
    }

    public int getManCodigoMarcacion() {
        return manCodigoMarcacion;
    }

    public void setManCodigoMarcacion(int manCodigoMarcacion) {
        this.manCodigoMarcacion = manCodigoMarcacion;
    }

    public String getMatObservaciones() {
        return matObservaciones;
    }

    public void setMatObservaciones(String matObservaciones) {
        this.matObservaciones = matObservaciones;
    }
    
}
