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
import vo.UsuariosVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "MAPA.USERS")
public class Usuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "USER_CODE")
    private int userCode;
    
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSIUST")
    private Collection<UsUsuarios> usUsuariosCollection;
    
    public Usuarios(){
    }

    public UsuariosVO toVO(){
        UsuariosVO vo = new UsuariosVO();
        // Datos Usuario
        vo.setUserCode(this.getUserCode());
        vo.setEmail(this.getEmail());
        return vo;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public Collection<UsUsuarios> getUsUsuariosCollection() {
        return usUsuariosCollection;
    }

    public void setUsUsuariosCollection(Collection<UsUsuarios> usUsuariosCollection) {
        this.usUsuariosCollection = usUsuariosCollection;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }
    
}
