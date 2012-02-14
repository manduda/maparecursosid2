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
public class TmTramiteMncVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tmnCodigo;
    private TrTramitesVO trnCodigo;
    private CdCodigosMncVO cdnCodigo;
    private AcAccionVO acnCodigo;
    private int tmnRadicado;
    private EmOperadorVO emrCodigo;
    private String tmtObservaciones;
    private char tmtReservaTemporal;
    private int tmnMesesLiberacion;

    public TmTramiteMncVO(){
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CdCodigosMncVO getCdnCodigo() {
        return cdnCodigo;
    }

    public void setCdnCodigo(CdCodigosMncVO cdnCodigo) {
        this.cdnCodigo = cdnCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getTmnCodigo() {
        return tmnCodigo;
    }

    public void setTmnCodigo(int tmnCodigo) {
        this.tmnCodigo = tmnCodigo;
    }

    public int getTmnMesesLiberacion() {
        return tmnMesesLiberacion;
    }

    public void setTmnMesesLiberacion(int tmnMesesLiberacion) {
        this.tmnMesesLiberacion = tmnMesesLiberacion;
    }

    public int getTmnRadicado() {
        return tmnRadicado;
    }

    public void setTmnRadicado(int tmnRadicado) {
        this.tmnRadicado = tmnRadicado;
    }

    public String getTmtObservaciones() {
        return tmtObservaciones;
    }

    public void setTmtObservaciones(String tmtObservaciones) {
        this.tmtObservaciones = tmtObservaciones;
    }

    public char getTmtReservaTemporal() {
        return tmtReservaTemporal;
    }

    public void setTmtReservaTemporal(char tmtReservaTemporal) {
        this.tmtReservaTemporal = tmtReservaTemporal;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }
    
}
