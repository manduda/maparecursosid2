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
import vo.TeTipoSenalizacionVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TE_TIPO_SENALIZACION")
public class TeTipoSenalizacion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TEN_CODIGO")
    private int tenCodigo;
    
    @Basic(optional = false)
    @Column(name = "TET_NOMBRE")
    private String tetNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tenCodigo")
    private Collection<SeSenalizacion> seSenalizacionCollection;

    public TeTipoSenalizacion() {
    }
    
    public TeTipoSenalizacionVO toVO() {
        TeTipoSenalizacionVO vo = new TeTipoSenalizacionVO();
        vo.setTenCodigo(this.getTenCodigo());
        vo.setTetNombre(this.getTetNombre());
        return vo;
    }
    
    public Collection<SeSenalizacion> getSeSenalizacionCollection() {
        return seSenalizacionCollection;
    }

    public void setSeSenalizacionCollection(Collection<SeSenalizacion> seSenalizacionCollection) {
        this.seSenalizacionCollection = seSenalizacionCollection;
    }

    public int getTenCodigo() {
        return tenCodigo;
    }

    public void setTenCodigo(int tenCodigo) {
        this.tenCodigo = tenCodigo;
    }

    public String getTetNombre() {
        return tetNombre;
    }

    public void setTetNombre(String tetNombre) {
        this.tetNombre = tetNombre;
    }

}
