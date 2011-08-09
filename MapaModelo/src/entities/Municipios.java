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
import javax.persistence.JoinColumn;
import javax.persistence.Lob;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "SA.MUNICIPIOS")
public class Municipios implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "CODIGO_MUNICIPIO")
    private String codigoMunicipio;
    
    @Basic(optional = false)
    @Column(name = "NOMBRE_MUNICIPIO")
    private String nombreMunicipio;
    
    @JoinColumn(name = "CODIGO_DEPARTAMENTO", referencedColumnName = "CODIGO_DEPARTAMENTO")
    @ManyToOne(optional = false)
    private Departamentos codigoDepartamento;

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMunicipio")
    private Collection<NuNumeracion> nuNumeracionCollection;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoMunicipio")
    private Collection<SeSenalizacion> seSenalizacionCollection;

    public Municipios() {
    }

    public Departamentos getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(Departamentos codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

    public Collection<NuNumeracion> getNuNumeracionCollection() {
        return nuNumeracionCollection;
    }

    public void setNuNumeracionCollection(Collection<NuNumeracion> nuNumeracionCollection) {
        this.nuNumeracionCollection = nuNumeracionCollection;
    }

    public Collection<SeSenalizacion> getSeSenalizacionCollection() {
        return seSenalizacionCollection;
    }

    public void setSeSenalizacionCollection(Collection<SeSenalizacion> seSenalizacionCollection) {
        this.seSenalizacionCollection = seSenalizacionCollection;
    }

}
