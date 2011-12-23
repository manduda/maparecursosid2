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
import vo.NtTipoNdcVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "NT_TIPONDC")
public class NtTipoNdc implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "NTN_CODIGO")
    private Integer ntnCodigo;
    
    @Basic(optional = false)
    @Column(name = "NTT_NOMBRE")
    private String nttNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "ntnCodigo")
    private Collection<NdNdc> ndNdcCollection;
    
    @OneToMany(mappedBy = "ntnCodigoPadre")
    private Collection<NtTipoNdc> ntTipoNdcCollection;
    
    @JoinColumn(name = "NTN_CODIGO_PADRE", referencedColumnName = "NTN_CODIGO")
    @ManyToOne
    private NtTipoNdc ntnCodigoPadre;

    public NtTipoNdc() {
    }
    
    public NtTipoNdcVO toVO() {
        NtTipoNdcVO vo = new NtTipoNdcVO();
        vo.setNtnCodigo(this.getNtnCodigo());
        vo.setNttNombre(this.getNttNombre());
        
        NtTipoNdcVO voPadre = new NtTipoNdcVO();
        voPadre.setNtnCodigo(this.getNtnCodigoPadre().ntnCodigo);
        vo.setNtnCodigoPadre(voPadre);
        return vo;
    }

    public Collection<NdNdc> getNdNdcCollection() {
        return ndNdcCollection;
    }

    public void setNdNdcCollection(Collection<NdNdc> ndNdcCollection) {
        this.ndNdcCollection = ndNdcCollection;
    }

    public Collection<NtTipoNdc> getNtTipoNdcCollection() {
        return ntTipoNdcCollection;
    }

    public void setNtTipoNdcCollection(Collection<NtTipoNdc> ntTipoNdcCollection) {
        this.ntTipoNdcCollection = ntTipoNdcCollection;
    }

    public Integer getNtnCodigo() {
        return ntnCodigo;
    }

    public void setNtnCodigo(Integer ntnCodigo) {
        this.ntnCodigo = ntnCodigo;
    }

    public NtTipoNdc getNtnCodigoPadre() {
        return ntnCodigoPadre;
    }

    public void setNtnCodigoPadre(NtTipoNdc ntnCodigoPadre) {
        this.ntnCodigoPadre = ntnCodigoPadre;
    }

    public String getNttNombre() {
        return nttNombre;
    }

    public void setNttNombre(String nttNombre) {
        this.nttNombre = nttNombre;
    }
  
}
