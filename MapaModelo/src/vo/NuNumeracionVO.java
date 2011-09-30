/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author miguel.duran
 */
public class NuNumeracionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int nunCodigo;
    private MunicipiosVO codigoMunicipio;
    private EmOperadorVO emrCodigo;
    private int nunInicio;
    private int nunFin;
    private String nutObservaciones;
    private NdNdcVO ndnCodigo;
    private EsEstadoVO esnCodigo;

    public NuNumeracionVO() {
    }

    public EsEstadoVO getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstadoVO esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public NdNdcVO getNdnCodigo() {
        return ndnCodigo;
    }

    public void setNdnCodigo(NdNdcVO ndnCodigo) {
        this.ndnCodigo = ndnCodigo;
    }

    public int getNunCodigo() {
        return nunCodigo;
    }

    public void setNunCodigo(int nunCodigo) {
        this.nunCodigo = nunCodigo;
    }

    public int getNunFin() {
        return nunFin;
    }

    public void setNunFin(int nunFin) {
        this.nunFin = nunFin;
    }

    public int getNunInicio() {
        return nunInicio;
    }

    public void setNunInicio(int nunInicio) {
        this.nunInicio = nunInicio;
    }

    public String getNutObservaciones() {
        return nutObservaciones;
    }

    public void setNutObservaciones(String nutObservaciones) {
        this.nutObservaciones = nutObservaciones;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public MunicipiosVO getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(MunicipiosVO codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

}
