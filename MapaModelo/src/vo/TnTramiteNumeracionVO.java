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
public class TnTramiteNumeracionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tnnCodigo;
    private TrTramitesVO trnCodigo;
    private AcAccionVO acnCodigo;
    private int tnnRadicado;
    private MunicipiosVO codigoMunicipio;
    private EmOperadorVO emrCodigo;
    private NdNdcVO ndnCodigo;
    private int tnnInicio;
    private int tnnFin;
    private String tntObservaciones;
    private char tntReservaTemporal;
    private int tnnMesesLiberacion;
    
    public TnTramiteNumeracionVO() {
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public MunicipiosVO getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(MunicipiosVO codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public NdNdcVO getNdnCodigo() {
        return ndnCodigo;
    }

    public void setNdnCodigo(NdNdcVO ndnCodigo) {
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

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
    
}
