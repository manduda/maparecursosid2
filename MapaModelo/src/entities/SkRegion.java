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
import javax.persistence.Lob;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "SA.SK_REGION")
public class SkRegion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Lob
    @Column(name = "SK_REGION_CODE")
    private byte[] skRegionCode;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String skRegionNombre;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "skRegionCode")
    private Collection<NuNumeracion> nuNumeracionCollection;

    public SkRegion() {
    }

    public Collection<NuNumeracion> getNuNumeracionCollection() {
        return nuNumeracionCollection;
    }

    public void setNuNumeracionCollection(Collection<NuNumeracion> nuNumeracionCollection) {
        this.nuNumeracionCollection = nuNumeracionCollection;
    }

    public byte[] getSkRegionCode() {
        return skRegionCode;
    }

    public void setSkRegionCode(byte[] skRegionCode) {
        this.skRegionCode = skRegionCode;
    }

    public String getSkRegionNombre() {
        return skRegionNombre;
    }

    public void setSkRegionNombre(String skRegionNombre) {
        this.skRegionNombre = skRegionNombre;
    }
        
}
