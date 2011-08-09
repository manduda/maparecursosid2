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

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "RT_TIPO_REGION")
public class RtTipoRegion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "RTN_CODIGO")
    private int rtnCodigo;
    
    @Basic(optional = false)
    @Column(name = "RTT_NOMBRE")
    private String rttNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "rtnCodigo")
    private Collection<ReRegion> reRegionCollection;

    public RtTipoRegion() {
    }

    public Collection<ReRegion> getReRegionCollection() {
        return reRegionCollection;
    }

    public void setReRegionCollection(Collection<ReRegion> reRegionCollection) {
        this.reRegionCollection = reRegionCollection;
    }

    public int getRtnCodigo() {
        return rtnCodigo;
    }

    public void setRtnCodigo(int rtnCodigo) {
        this.rtnCodigo = rtnCodigo;
    }

    public String getRttNombre() {
        return rttNombre;
    }

    public void setRttNombre(String rttNombre) {
        this.rttNombre = rttNombre;
    }


}
