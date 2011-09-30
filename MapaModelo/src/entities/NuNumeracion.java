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
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "NU_NUMERACION")
public class NuNumeracion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NUN_CODIGO")
    private int nunCodigo;
    
    @JoinColumn(name = "SK_REGION_CODE", referencedColumnName = "CODIGO_MUNICIPIO")
    @ManyToOne(optional = false)
    private Municipios codigoMunicipio;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Basic(optional = false)
    @Column(name = "NUN_INICIO")
    private int nunInicio;
    
    @Basic(optional = false)
    @Column(name = "NUN_FIN")
    private int nunFin;
    
    @Column(name = "NUT_OBSERVACIONES")
    private String nutObservaciones;
    
    @JoinColumn(name = "NDN_CODIGO", referencedColumnName = "NDN_CODIGO")
    @ManyToOne(optional = false)
    private NdNdc ndnCodigo;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;

    public NuNumeracion() {
    }

    public int getNunCodigo() {
        return nunCodigo;
    }

    public void setNunCodigo(int nunCodigo) {
        this.nunCodigo = nunCodigo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public Municipios getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipios codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public int getNunInicio() {
        return nunInicio;
    }

    public void setNunInicio(int nunInicio) {
        this.nunInicio = nunInicio;
    }

    public int getNunFin() {
        return nunFin;
    }

    public void setNunFin(int nunFin) {
        this.nunFin = nunFin;
    }

    public String getNutObservaciones() {
        return nutObservaciones;
    }

    public void setNutObservaciones(String nutObservaciones) {
        this.nutObservaciones = nutObservaciones;
    }

    public NdNdc getNdnCodigo() {
        return ndnCodigo;
    }

    public void setNdnCodigo(NdNdc ndnCodigo) {
        this.ndnCodigo = ndnCodigo;
    }

    public EsEstado getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstado esnCodigo) {
        this.esnCodigo = esnCodigo;
    }
   
}
