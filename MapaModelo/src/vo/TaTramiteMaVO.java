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
public class TaTramiteMaVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tanCodigo;
    private TrTramitesVO trnCodigo;
    private MaMarcacionAbreviadaVO manCodigo;
    private AcAccionVO acnCodigo;
    private int tanRadicado;
    private EmOperadorVO emrCodigo;
    private String tatObservaciones;
    private char tatReservaTemporal;
    private int tanMesesLiberacion;
    
    public TaTramiteMaVO(){
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public MaMarcacionAbreviadaVO getManCodigo() {
        return manCodigo;
    }

    public void setManCodigo(MaMarcacionAbreviadaVO manCodigo) {
        this.manCodigo = manCodigo;
    }

    public int getTanCodigo() {
        return tanCodigo;
    }

    public void setTanCodigo(int tanCodigo) {
        this.tanCodigo = tanCodigo;
    }

    public int getTanMesesLiberacion() {
        return tanMesesLiberacion;
    }

    public void setTanMesesLiberacion(int tanMesesLiberacion) {
        this.tanMesesLiberacion = tanMesesLiberacion;
    }

    public int getTanRadicado() {
        return tanRadicado;
    }

    public void setTanRadicado(int tanRadicado) {
        this.tanRadicado = tanRadicado;
    }

    public String getTatObservaciones() {
        return tatObservaciones;
    }

    public void setTatObservaciones(String tatObservaciones) {
        this.tatObservaciones = tatObservaciones;
    }

    public char getTatReservaTemporal() {
        return tatReservaTemporal;
    }

    public void setTatReservaTemporal(char tatReservaTemporal) {
        this.tatReservaTemporal = tatReservaTemporal;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
    
}
