/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Collection;
import java.util.Date;

/**
 *
 * @author miguel.duran
 */
public class TrTramitesVO {
    private int trnCodigo;
    private UsUsuariosVO usnCodigo;
    private EmOperadorVO emrCodigo;
    private EtEstadoTramiteVO etnCodigo;
    private Date trfFecha;
    private String trnResolucion;
    private Date trfFechaResolucion;
    private String trtObservaciones;
    private Collection<GtGestionTramiteVO> gtGetionTramiteCollection;
    private Collection<TsTramiteSenalizacionVO> tsTramiteSenalizacionCollection;
    private Collection<TlTramiteLdVO> tlTramiteLdCollection;
    
    public TrTramitesVO() {
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public EtEstadoTramiteVO getEtnCodigo() {
        return etnCodigo;
    }

    public void setEtnCodigo(EtEstadoTramiteVO etnCodigo) {
        this.etnCodigo = etnCodigo;
    }

    public Date getTrfFecha() {
        return trfFecha;
    }

    public void setTrfFecha(Date trfFecha) {
        this.trfFecha = trfFecha;
    }

    public Date getTrfFechaResolucion() {
        return trfFechaResolucion;
    }

    public void setTrfFechaResolucion(Date trfFechaResolucion) {
        this.trfFechaResolucion = trfFechaResolucion;
    }

    public int getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(int trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public String getTrnResolucion() {
        return trnResolucion;
    }

    public void setTrnResolucion(String trnResolucion) {
        this.trnResolucion = trnResolucion;
    }

    public String getTrtObservaciones() {
        return trtObservaciones;
    }

    public void setTrtObservaciones(String trtObservaciones) {
        this.trtObservaciones = trtObservaciones;
    }

    public UsUsuariosVO getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(UsUsuariosVO usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

    public Collection<GtGestionTramiteVO> getGtGetionTramiteCollection() {
        return gtGetionTramiteCollection;
    }

    public void setGtGetionTramiteCollection(Collection<GtGestionTramiteVO> gtGetionTramiteCollection) {
        this.gtGetionTramiteCollection = gtGetionTramiteCollection;
    }

    public Collection<TsTramiteSenalizacionVO> getTsTramiteSenalizacionCollection() {
        return tsTramiteSenalizacionCollection;
    }

    public void setTsTramiteSenalizacionCollection(Collection<TsTramiteSenalizacionVO> tsTramiteSenalizacionCollection) {
        this.tsTramiteSenalizacionCollection = tsTramiteSenalizacionCollection;
    }

    public Collection<TlTramiteLdVO> getTlTramiteLdCollection() {
        return tlTramiteLdCollection;
    }

    public void setTlTramiteLdCollection(Collection<TlTramiteLdVO> tlTramiteLdCollection) {
        this.tlTramiteLdCollection = tlTramiteLdCollection;
    }

}
