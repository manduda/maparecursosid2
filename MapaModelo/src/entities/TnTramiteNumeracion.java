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
import vo.TnTramiteNumeracionVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TN_TRAMITE_NUMERACION")
public class TnTramiteNumeracion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TNN_CODIGO")
    private int tnnCodigo;
    
    @JoinColumn(name = "TRN_CODIGO", referencedColumnName = "TRN_CODIGO")
    @ManyToOne(optional = false)
    private TrTramites trnCodigo;
    
    /*@JoinColumn(name = "SEN_CODIGO", referencedColumnName = "SEN_CODIGO")
    @ManyToOne(optional = false)
    private SeSenalizacion senCodigo;*/
    
    @JoinColumn(name = "ACN_CODIGO", referencedColumnName = "ACN_CODIGO")
    @ManyToOne(optional = false)
    private AcAccion acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TNN_RADICADO")
    private int tnnRadicado;
    
    @JoinColumn(name = "SK_REGION_CODE", referencedColumnName = "CODIGO_MUNICIPIO")
    @ManyToOne(optional = false)
    private Municipios codigoMunicipio;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @JoinColumn(name = "NDN_CODIGO", referencedColumnName = "NDN_CODIGO")
    @ManyToOne(optional = false)
    private NdNdc ndnCodigo;
    
    @Column(name = "TNN_INICIO")
    private int tnnInicio;
    
    @Column(name = "TNN_FIN")
    private int tnnFin;
    
    @Column(name = "TNT_OBSERVACIONES")
    private String tntObservaciones;

    @Column(name = "TNT_RESERVA_TEMPORAL")
    private char tntReservaTemporal;
    
    @Column(name = "TNN_MESES_LIBERACION")
    private int tnnMesesLiberacion;
    
    public TnTramiteNumeracion() {
    }
    
    public TnTramiteNumeracionVO toVO(){
        TnTramiteNumeracionVO vo = new TnTramiteNumeracionVO();
        vo.setTnnCodigo(this.getTnnCodigo());
        vo.setTrnCodigo(this.getTrnCodigo().toVOsinDetalleTramites());
        vo.setAcnCodigo(this.getAcnCodigo().toVO());
        vo.setTnnRadicado(this.getTnnRadicado());
        vo.setCodigoMunicipio(this.getCodigoMunicipio().toVO());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setNdnCodigo(this.getNdnCodigo().toVO());
        vo.setTnnInicio(this.getTnnInicio());
        vo.setTnnFin(this.getTnnFin());
        vo.setTntObservaciones(this.getTntObservaciones());
        vo.setTnnMesesLiberacion(this.getTnnMesesLiberacion());
        vo.setTntReservaTemporal(this.getTntReservaTemporal());
        
        return vo;
    }
    
    
    public AcAccion getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccion acnCodigo) {
        this.acnCodigo = acnCodigo;
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

    public NdNdc getNdnCodigo() {
        return ndnCodigo;
    }

    public void setNdnCodigo(NdNdc ndnCodigo) {
        this.ndnCodigo = ndnCodigo;
    }

    public int getTnnCodigo() {
        return tnnCodigo;
    }

    public void setTnnCodigo(int tnnCodigo) {
        this.tnnCodigo = tnnCodigo;
    }

    public int getTnnFin() {
        return tnnFin;
    }

    public void setTnnFin(int tnnFin) {
        this.tnnFin = tnnFin;
    }

    public int getTnnInicio() {
        return tnnInicio;
    }

    public void setTnnInicio(int tnnInicio) {
        this.tnnInicio = tnnInicio;
    }

    public int getTnnMesesLiberacion() {
        return tnnMesesLiberacion;
    }

    public void setTnnMesesLiberacion(int tnnMesesLiberacion) {
        this.tnnMesesLiberacion = tnnMesesLiberacion;
    }

    public int getTnnRadicado() {
        return tnnRadicado;
    }

    public void setTnnRadicado(int tnnRadicado) {
        this.tnnRadicado = tnnRadicado;
    }

    public String getTntObservaciones() {
        return tntObservaciones;
    }

    public void setTntObservaciones(String tntObservaciones) {
        this.tntObservaciones = tntObservaciones;
    }

    public char getTntReservaTemporal() {
        return tntReservaTemporal;
    }

    public void setTntReservaTemporal(char tntReservaTemporal) {
        this.tntReservaTemporal = tntReservaTemporal;
    }

    public TrTramites getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramites trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

}
