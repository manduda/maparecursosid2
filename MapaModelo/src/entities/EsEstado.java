/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
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
@Table(name = "ES_ESTADO")
public class EsEstado implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    
    @Id
    @Basic(optional = false)
    @Column(name = "ESN_CODIGO")
    private int esnCodigo;
    
    @Basic(optional = false)
    @Column(name = "EST_NOMBRE")
    private String estNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esnCodigo")
    private Collection<ClCodigosLd> clCodigosLdCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "esnCodigo")
    private Collection<NuNumeracion> nuNumeracionCollection;
    
    public EsEstado() {
    }

    public int getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(int esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public String getEstNombre() {
        return estNombre;
    }

    public void setEstNombre(String estNombre) {
        this.estNombre = estNombre;
    }

    public Collection<ClCodigosLd> getClCodigosLdCollection() {
        return clCodigosLdCollection;
    }

    public void setClCodigosLdCollection(Collection<ClCodigosLd> clCodigosLdCollection) {
        this.clCodigosLdCollection = clCodigosLdCollection;
    }

    public Collection<NuNumeracion> getNuNumeracionCollection() {
        return nuNumeracionCollection;
    }

    public void setNuNumeracionCollection(Collection<NuNumeracion> nuNumeracionCollection) {
        this.nuNumeracionCollection = nuNumeracionCollection;
    }
   
}
