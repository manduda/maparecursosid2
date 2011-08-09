/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Collection;

/**
 *
 * @author miguel.duran
 */
public class EtEstadoTramiteVO {
    private int etnCodigo;
    private String ettNombre;
    private Collection<TrTramitesVO> trTramitesCollection;
    private Collection<GtGestionTramiteVO> gtGestionTramiteCollection;
    
    public EtEstadoTramiteVO() {
    }

    public int getEtnCodigo() {
        return etnCodigo;
    }

    public void setEtnCodigo(int etnCodigo) {
        this.etnCodigo = etnCodigo;
    }

    public String getEttNombre() {
        return ettNombre;
    }

    public void setEttNombre(String ettNombre) {
        this.ettNombre = ettNombre;
    }

    public Collection<GtGestionTramiteVO> getGtGestionTramiteCollection() {
        return gtGestionTramiteCollection;
    }

    public void setGtGestionTramiteCollection(Collection<GtGestionTramiteVO> gtGestionTramiteCollection) {
        this.gtGestionTramiteCollection = gtGestionTramiteCollection;
    }

    public Collection<TrTramitesVO> getTrTramitesCollection() {
        return trTramitesCollection;
    }

    public void setTrTramitesCollection(Collection<TrTramitesVO> trTramitesCollection) {
        this.trTramitesCollection = trTramitesCollection;
    }
    
}
