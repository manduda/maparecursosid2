/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;

/**
 *
 * @author miguel.duran
 */
public class SeSenalizacionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int senCodigo;
    private MunicipiosVO codigoMunicipio;
    private EmOperadorVO emrCodigo;
    private EsEstadoVO esnCodigo;
    private TeTipoSenalizacionVO tenCodigo;
    private ReRegionVO renCodigo;
    private int senZona;
    private int senPs;
    private String setNombreNodo;
    private String setMarcaModelo;
    private String setDireccion;
    private String setObservaciones;

    public SeSenalizacionVO() {
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public EsEstadoVO getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstadoVO esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public ReRegionVO getRenCodigo() {
        return renCodigo;
    }

    public void setRenCodigo(ReRegionVO renCodigo) {
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

    public MunicipiosVO getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(MunicipiosVO codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }


    public TeTipoSenalizacionVO getTenCodigo() {
        return tenCodigo;
    }

    public void setTenCodigo(TeTipoSenalizacionVO tenCodigo) {
        this.tenCodigo = tenCodigo;
    }
    
}
