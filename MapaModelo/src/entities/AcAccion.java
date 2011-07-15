/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "AC_ACCION")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "AcAccion.findAll", query = "SELECT a FROM AcAccion a"),
    @NamedQuery(name = "AcAccion.findByAcnCodigo", query = "SELECT a FROM AcAccion a WHERE a.acnCodigo = :acnCodigo"),
    @NamedQuery(name = "AcAccion.findByActNombre", query = "SELECT a FROM AcAccion a WHERE a.actNombre = :actNombre")})
public class AcAccion implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "ACN_CODIGO")
    private BigDecimal acnCodigo;
    @Basic(optional = false)
    @Column(name = "ACT_NOMBRE")
    private String actNombre;

    public AcAccion() {
    }

    public AcAccion(BigDecimal acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public AcAccion(BigDecimal acnCodigo, String actNombre) {
        this.acnCodigo = acnCodigo;
        this.actNombre = actNombre;
    }

    public BigDecimal getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(BigDecimal acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public String getActNombre() {
        return actNombre;
    }

    public void setActNombre(String actNombre) {
        this.actNombre = actNombre;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (acnCodigo != null ? acnCodigo.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof AcAccion)) {
            return false;
        }
        AcAccion other = (AcAccion) object;
        if ((this.acnCodigo == null && other.acnCodigo != null) || (this.acnCodigo != null && !this.acnCodigo.equals(other.acnCodigo))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.AcAccion[ acnCodigo=" + acnCodigo + " ]";
    }
    
}
