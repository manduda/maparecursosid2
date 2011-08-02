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
@Table(name = "TU_TIPO_USUARIO")
public class TuTipoUsuario implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TUN_CODIGO")
    private int tunCodigo;
    
    @Basic(optional = false)
    @Column(name = "TUT_NOMBRE")
    private String tutNombre;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "tunCodigo")
    private Collection<UsUsuarios> usUsuariosCollection;

    public int getTunCodigo() {
        return tunCodigo;
    }

    public void setTunCodigo(int tunCodigo) {
        this.tunCodigo = tunCodigo;
    }

    public String getTutNombre() {
        return tutNombre;
    }

    public void setTutNombre(String tutNombre) {
        this.tutNombre = tutNombre;
    }

    public Collection<UsUsuarios> getUsUsuariosCollection() {
        return usUsuariosCollection;
    }

    public void setUsUsuariosCollection(Collection<UsUsuarios> usUsuariosCollection) {
        this.usUsuariosCollection = usUsuariosCollection;
    }

}
