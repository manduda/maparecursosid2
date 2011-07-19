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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "ND_NDC")
public class NdNdc implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NDN_CODIGO")
    private int ndnCodigo;
    
    @Basic(optional = false)
    @Column(name = "NDT_NOMBRE")
    private String ndtNombre;
    
    @JoinColumn(name = "NTN_CODIGO", referencedColumnName = "NTN_CODIGO")
    @ManyToOne(optional = false)
    private NtTipondc ntnCodigo;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ndnCodigo")
    private Collection<NuNumeracion> nuNumeracionCollection;

    public NdNdc() {
    }

    public int getNdnCodigo() {
        return ndnCodigo;
    }

    public void setNdnCodigo(int ndnCodigo) {
        this.ndnCodigo = ndnCodigo;
    }

    public String getNdtNombre() {
        return ndtNombre;
    }

    public void setNdtNombre(String ndtNombre) {
        this.ndtNombre = ndtNombre;
    }

    public NtTipondc getNtnCodigo() {
        return ntnCodigo;
    }

    public void setNtnCodigo(NtTipondc ntnCodigo) {
        this.ntnCodigo = ntnCodigo;
    }

    public Collection<NuNumeracion> getNuNumeracionCollection() {
        return nuNumeracionCollection;
    }

    public void setNuNumeracionCollection(Collection<NuNumeracion> nuNumeracionCollection) {
        this.nuNumeracionCollection = nuNumeracionCollection;
    }

}
