/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Date;

/**
 *
 * @author miguel.duran
 */
public class GtGestionTramiteVO {
    private int gtnCodigo;
    private UsUsuariosVO usnCodigo;
    private TrTramitesVO trnCodigo;
    private EtEstadoTramiteVO etnCodigo;
    private Date gtfFecha;
        
    public GtGestionTramiteVO() {
    }

    public EtEstadoTramiteVO getEtnCodigo() {
        return etnCodigo;
    }

    public void setEtnCodigo(EtEstadoTramiteVO etnCodigo) {
        this.etnCodigo = etnCodigo;
    }

    public Date getGtfFecha() {
        return gtfFecha;
    }

    public void setGtfFecha(Date gtfFecha) {
        this.gtfFecha = gtfFecha;
    }

    public int getGtnCodigo() {
        return gtnCodigo;
    }

    public void setGtnCodigo(int gtnCodigo) {
        this.gtnCodigo = gtnCodigo;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public UsUsuariosVO getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(UsUsuariosVO usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

}