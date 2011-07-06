/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.math.BigDecimal;
import java.math.BigInteger;

/**
 *
 * @author miguel.duran
 */
public class NuNumeracionVO {
    private BigDecimal nunCodigo;
    private SkRegionVO skRegionCode;
    private EmOperadorVO emrCodigo;
    private BigInteger nunInicio;
    private BigInteger nunFin;
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

    public BigDecimal getNunCodigo() {
        return nunCodigo;
    }

    public void setNunCodigo(BigDecimal nunCodigo) {
        this.nunCodigo = nunCodigo;
    }

    public BigInteger getNunFin() {
        return nunFin;
    }

    public void setNunFin(BigInteger nunFin) {
        this.nunFin = nunFin;
    }

    public BigInteger getNunInicio() {
        return nunInicio;
    }

    public void setNunInicio(BigInteger nunInicio) {
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

    public SkRegionVO getSkRegionCode() {
        return skRegionCode;
    }

    public void setSkRegionCode(SkRegionVO skRegionCode) {
        this.skRegionCode = skRegionCode;
    }

}
