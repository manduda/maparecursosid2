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
import vo.EmOperadorVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "SA.SK_EMPRESA")
public class EmOperador implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "SK_EMPRESA_CODE")
    private String emrCodigo;
    
    @Basic(optional = false)
    @Column(name = "DESCRIPCION")
    private String emtNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emrCodigo")
    private Collection<ClCodigosLd> clCodigosLdCollection;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emrCodigo")
    private Collection<NuNumeracion> nuNumeracionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emrCodigo")
    private Collection<SeSenalizacion> seSenalizacionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emrCodigo")
    private Collection<CcCodigosCortos> ccCodigosCortosCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emrCodigo")
    private Collection<MaMarcacionAbreviada> maMarcacionAbreviadaCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "emrCodigo")
    private Collection<CdCodigosMnc> cdCodigosMncCollection;

    public EmOperador() {
    }
    
    public EmOperadorVO toVO(){
        EmOperadorVO vo = new EmOperadorVO();
        vo.setEmrCodigo(this.getEmrCodigo());
        vo.setEmtNombre(this.getEmtNombre());
        return vo;
    }
    
    public Collection<ClCodigosLd> getClCodigosLdCollection() {
        return clCodigosLdCollection;
    }

    public void setClCodigosLdCollection(Collection<ClCodigosLd> clCodigosLdCollection) {
        this.setClCodigosLdCollection(clCodigosLdCollection);
    }

    public Collection<NuNumeracion> getNuNumeracionCollection() {
        return nuNumeracionCollection;
    }

    public void setNuNumeracionCollection(Collection<NuNumeracion> nuNumeracionCollection) {
        this.nuNumeracionCollection = nuNumeracionCollection;
    }

    public String getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(String emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public String getEmtNombre() {
        return emtNombre;
    }

    public void setEmtNombre(String emtNombre) {
        this.emtNombre = emtNombre;
    }

    public Collection<SeSenalizacion> getSeSenalizacionCollection() {
        return seSenalizacionCollection;
    }

    public void setSeSenalizacionCollection(Collection<SeSenalizacion> seSenalizacionCollection) {
        this.seSenalizacionCollection = seSenalizacionCollection;
    }

    public Collection<CcCodigosCortos> getCcCodigosCortosCollection() {
        return ccCodigosCortosCollection;
    }

    public void setCcCodigosCortosCollection(Collection<CcCodigosCortos> ccCodigosCortosCollection) {
        this.ccCodigosCortosCollection = ccCodigosCortosCollection;
    }

    public Collection<MaMarcacionAbreviada> getMaMarcacionAbreviadaCollection() {
        return maMarcacionAbreviadaCollection;
    }

    public void setMaMarcacionAbreviadaCollection(Collection<MaMarcacionAbreviada> maMarcacionAbreviadaCollection) {
        this.maMarcacionAbreviadaCollection = maMarcacionAbreviadaCollection;
    }

    public Collection<CdCodigosMnc> getCdCodigosMncCollection() {
        return cdCodigosMncCollection;
    }

    public void setCdCodigosMncCollection(Collection<CdCodigosMnc> cdCodigosMncCollection) {
        this.cdCodigosMncCollection = cdCodigosMncCollection;
    }
    
}
