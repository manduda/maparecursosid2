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
public class TiTramiteIinVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tinCodigo;
    private TrTramitesVO trnCodigo;
    private CiCodigosIinVO cinCodigo;
    private AcAccionVO acnCodigo;
    private int tinRadicado;
    private EmOperadorVO emrCodigo;
    private String titObservaciones;
    private char titReservaTemporal;
    private int tinMesesLiberacion;
    
    public TiTramiteIinVO(){
        
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CiCodigosIinVO getCinCodigo() {
        return cinCodigo;
    }

    public void setCinCodigo(CiCodigosIinVO cinCodigo) {
        this.cinCodigo = cinCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getTinCodigo() {
        return tinCodigo;
    }

    public void setTinCodigo(int tinCodigo) {
        this.tinCodigo = tinCodigo;
    }

    public int getTinMesesLiberacion() {
        return tinMesesLiberacion;
    }

    public void setTinMesesLiberacion(int tinMesesLiberacion) {
        this.tinMesesLiberacion = tinMesesLiberacion;
    }

    public int getTinRadicado() {
        return tinRadicado;
    }

    public void setTinRadicado(int tinRadicado) {
        this.tinRadicado = tinRadicado;
    }

    public String getTitObservaciones() {
        return titObservaciones;
    }

    public void setTitObservaciones(String titObservaciones) {
        this.titObservaciones = titObservaciones;
    }

    public char getTitReservaTemporal() {
        return titReservaTemporal;
    }

    public void setTitReservaTemporal(char titReservaTemporal) {
        this.titReservaTemporal = titReservaTemporal;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
    
}
