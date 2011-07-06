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
@Table(name = "NT_TIPONDC")
public class NtTipondc implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NTN_CODIGO")
    private BigDecimal ntnCodigo;
    
    @Basic(optional = false)
    @Column(name = "NTN_NOMBRE")
    private String ntnNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ntnCodigo")
    private Collection<NdNdc> ndNdcCollection;
    
    @OneToMany(mappedBy = "ntnCodigoPadre")
    private Collection<NtTipondc> ntTipondcCollection;
    
    @JoinColumn(name = "NTN_CODIGO_PADRE", referencedColumnName = "NTN_CODIGO")
    @ManyToOne
    private NtTipondc ntnCodigoPadre;

    public NtTipondc() {
    }

    public BigDecimal getNtnCodigo() {
        return ntnCodigo;
    }

    public void setNtnCodigo(BigDecimal ntnCodigo) {
        this.ntnCodigo = ntnCodigo;
    }

    public String getNtnNombre() {
        return ntnNombre;
    }

    public void setNtnNombre(String ntnNombre) {
        this.ntnNombre = ntnNombre;
    }

    public Collection<NdNdc> getNdNdcCollection() {
        return ndNdcCollection;
    }

    public void setNdNdcCollection(Collection<NdNdc> ndNdcCollection) {
        this.ndNdcCollection = ndNdcCollection;
    }

    public Collection<NtTipondc> getNtTipondcCollection() {
        return ntTipondcCollection;
    }

    public void setNtTipondcCollection(Collection<NtTipondc> ntTipondcCollection) {
        this.ntTipondcCollection = ntTipondcCollection;
    }

    public NtTipondc getNtnCodigoPadre() {
        return ntnCodigoPadre;
    }

    public void setNtnCodigoPadre(NtTipondc ntnCodigoPadre) {
        this.ntnCodigoPadre = ntnCodigoPadre;
    }
  
}
