/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import vo.AcAccionVO;
import vo.TsTramiteSenalizacionVO;

/**
 *
 * @author MADD
 */
@Entity
@Table(name = "TS_TRAMITE_SENALIZACION")
public class TsTramiteSenalizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TSN_CODIGO")
    private int tsnCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    @JoinColumn(name = "SEN_CODIGO", referencedColumnName = "SEN_CODIGO")
    @ManyToOne(optional = false)
    private SeSenalizacion senCodigo;
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TSN_RADICADO")
    private int tsnRadicado;
    
    @JoinColumn(name = "SK_REGION_CODE", referencedColumnName = "CODIGO_MUNICIPIO")
    @ManyToOne(optional = false)
    private Municipios codigoMunicipio;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Column(name = "TST_NOMBRE_NODO")
    private String tstNombreNodo;
    
    @Column(name = "TST_MARCA_MODELO")
    private String tstMarcaModelo;
    
    @Column(name = "TST_DIRECCION")
    private String tstDireccion;
    
    @Column(name = "TST_OBSERVACIONES")
    private String tstObservaciones;

    public TsTramiteSenalizacion() {
    }
    
    public TsTramiteSenalizacionVO toVO(){
        TsTramiteSenalizacionVO vo = new TsTramiteSenalizacionVO();
        vo.setTsnCodigo(this.getTsnCodigo());
        //vo.setTrnCodigo(this.getTrnCodigo().toVO());
        vo.setSenCodigo(this.getSenCodigo().toVO());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTsnRadicado(this.getTsnRadicado());
        vo.setCodigoMunicipio(this.getCodigoMunicipio().toVO());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setTstNombreNodo(this.getTstNombreNodo());
        vo.setTstMarcaModelo(this.getTstMarcaModelo());
        vo.setTstDireccion(this.getTstDireccion());
        vo.setTstObservaciones(this.getTstObservaciones());
        
        
        return vo;
    }

    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public SeSenalizacion getSenCodigo() {
        return senCodigo;
    }

    public void setSenCodigo(SeSenalizacion senCodigo) {
        this.senCodigo = senCodigo;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public int getTsnCodigo() {
        return tsnCodigo;
    }

    public void setTsnCodigo(int tsnCodigo) {
        this.tsnCodigo = tsnCodigo;
    }

    public int getTsnRadicado() {
        return tsnRadicado;
    }

    public void setTsnRadicado(int tsnRadicado) {
        this.tsnRadicado = tsnRadicado;
    }

    public String getTstDireccion() {
        return tstDireccion;
    }

    public void setTstDireccion(String tstDireccion) {
        this.tstDireccion = tstDireccion;
    }

    public String getTstMarcaModelo() {
        return tstMarcaModelo;
    }

    public void setTstMarcaModelo(String tstMarcaModelo) {
        this.tstMarcaModelo = tstMarcaModelo;
    }

    public String getTstNombreNodo() {
        return tstNombreNodo;
    }

    public void setTstNombreNodo(String tstNombreNodo) {
        this.tstNombreNodo = tstNombreNodo;
    }

    public String getTstObservaciones() {
        return tstObservaciones;
    }

    public void setTstObservaciones(String tstObservaciones) {
        this.tstObservaciones = tstObservaciones;
    }

    public Municipios getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipios codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

}
