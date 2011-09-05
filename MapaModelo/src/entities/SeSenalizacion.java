/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.GtGestionTramiteVO;
import vo.ReRegionVO;
import vo.SeSenalizacionVO;
import vo.TeTipoSenalizacionVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "SE_SENALIZACION")
public class SeSenalizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "SEN_CODIGO")
    private int senCodigo;

    @JoinColumn(name = "SK_REGION_CODE", referencedColumnName = "CODIGO_MUNICIPIO")
    @ManyToOne(optional = false)
    private Municipios codigoMunicipio;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @JoinColumn(name = "ESN_CODIGO", referencedColumnName = "ESN_CODIGO")
    @ManyToOne(optional = false)
    private EsEstado esnCodigo;
    
    @JoinColumn(name = "TEN_CODIGO", referencedColumnName = "TEN_CODIGO")
    @ManyToOne(optional = false)
    private TeTipoSenalizacion tenCodigo;
    
    @JoinColumn(name = "REN_CODIGO", referencedColumnName = "REN_CODIGO")
    @ManyToOne(optional = false)
    private ReRegion renCodigo;
    
    @Basic(optional = false)
    @Column(name = "SEN_ZONA")
    private int senZona;
    
    @Basic(optional = false)
    @Column(name = "SEN_PS")
    private int senPs;
    
    @Column(name = "SET_NOMBRE_NODO")
    private String setNombreNodo;
    
    @Column(name = "SET_MARCA_MODELO")
    private String setMarcaModelo;
    
    @Column(name = "SET_DIRECCION")
    private String setDireccion;
    
    @Column(name = "SET_OBSERVACIONES")
    private String setObservaciones;
    
    public SeSenalizacion() {
    }
    
    public SeSenalizacionVO toVO() {
        SeSenalizacionVO vo = new SeSenalizacionVO();
        vo.setSenCodigo(this.getSenCodigo());
        vo.setCodigoMunicipio(this.getCodigoMunicipio().toVO());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setEsnCodigo(this.getEsnCodigo().toVO());
        vo.setTenCodigo(this.getTenCodigo().toVO());
        vo.setRenCodigo(this.getRenCodigo().toVO());
        vo.setSenZona(this.getSenZona());
        vo.setSenPs(this.getSenPs());
        vo.setSetNombreNodo(this.getSetNombreNodo());
        vo.setSetMarcaModelo(this.getSetMarcaModelo());
        vo.setSetDireccion(this.getSetDireccion());
        vo.setSetObservaciones(this.getSetObservaciones());
        
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

    public ReRegion getRenCodigo() {
        return renCodigo;
    }

    public void setRenCodigo(ReRegion renCodigo) {
        this.renCodigo = renCodigo;
    }

    public int getSenCodigo() {
        return senCodigo;
    }

    public void setSenCodigo(int senCodigo) {
        this.senCodigo = senCodigo;
    }

    public int getSenPs() {
        return senPs;
    }

    public void setSenPs(int senPs) {
        this.senPs = senPs;
    }

    public int getSenZona() {
        return senZona;
    }

    public void setSenZona(int senZona) {
        this.senZona = senZona;
    }

    public String getSetDireccion() {
        return setDireccion;
    }

    public void setSetDireccion(String setDireccion) {
        this.setDireccion = setDireccion;
    }

    public String getSetMarcaModelo() {
        return setMarcaModelo;
    }

    public void setSetMarcaModelo(String setMarcaModelo) {
        this.setMarcaModelo = setMarcaModelo;
    }

    public String getSetNombreNodo() {
        return setNombreNodo;
    }

    public void setSetNombreNodo(String setNombreNodo) {
        this.setNombreNodo = setNombreNodo;
    }

    public String getSetObservaciones() {
        return setObservaciones;
    }

    public void setSetObservaciones(String setObservaciones) {
        this.setObservaciones = setObservaciones;
    }

    public Municipios getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(Municipios codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public TeTipoSenalizacion getTenCodigo() {
        return tenCodigo;
    }

    public void setTenCodigo(TeTipoSenalizacion tenCodigo) {
        this.tenCodigo = tenCodigo;
    }

}
