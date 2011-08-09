/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import vo.EtEstadoTramiteVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "ET_ESTADO_TRAMITE")
public class EtEstadoTramite implements Serializable {
    private static final long serialVersionUID = 1L;
    
    public static final int CREADO = 1;
    public static final int ENVIADO = 2;
    public static final int DEVUELTO = 3;
    public static final int APROBADO = 4;
    public static final int TERMINADO = 5;
       
    @Id
    @Basic(optional = false)
    @Column(name = "ETN_CODIGO")
    private int etnCodigo;
    
    @Basic(optional = false)
    @Column(name = "ETT_NOMBRE")
    private String ettNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etnCodigo")
    private Collection<TrTramites> trTramitesCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "etnCodigo")
    private Collection<GtGestionTramite> gtGestionTramiteCollection;
    
    public EtEstadoTramite() {
    }
    
    public EtEstadoTramiteVO toVO(){
        EtEstadoTramiteVO vo = new EtEstadoTramiteVO();
        vo.setEtnCodigo(this.getEtnCodigo());
        vo.setEttNombre(this.getEttNombre());
        return vo;
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

    public Collection<TrTramites> getTrTramitesCollection() {
        return trTramitesCollection;
    }

    public void setTrTramitesCollection(Collection<TrTramites> trTramitesCollection) {
        this.trTramitesCollection = trTramitesCollection;
    }

    public Collection<GtGestionTramite> getGtGestionTramiteCollection() {
        return gtGestionTramiteCollection;
    }

    public void setGtGestionTramiteCollection(Collection<GtGestionTramite> gtGestionTramiteCollection) {
        this.gtGestionTramiteCollection = gtGestionTramiteCollection;
    }


}
