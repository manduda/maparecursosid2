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
import vo.UsersVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "SA.USERS")
public class Users implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "USER_CODE")
    private int userCode;
    
    @Basic(optional = false)
    @Column(name = "NAME")
    private String name;
    
    @Basic(optional = false)
    @Column(name = "LAST_NAME")
    private String lastName;
    
    @Basic(optional = false)
    @Column(name = "EMAIL")
    private String email;
    
    @Basic(optional = false)
    @Column(name = "LOGIN")
    private String login;
    
    @Basic(optional = false)
    @Column(name = "PASSWORD")
    private String password;

    //@OneToMany(cascade = CascadeType.ALL, mappedBy = "codigoSIUST")
    //private Collection<UsUsuarios> usUsuariosCollection;

    public Users(){
    }

    public UsersVO toVO(){
        UsersVO vo = new UsersVO();
        // Datos Usuario
        vo.setUserCode(this.getUserCode());
        vo.setName(this.getName());
        vo.setLastName(this.getLastName());
        vo.setEmail(this.getEmail());
        vo.setLogin(this.getLogin());
        //vo.setPassword(this.getPassword());
        
        return vo;
    }
    
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    /*public Collection<UsUsuarios> getUsUsuariosCollection() {
        return usUsuariosCollection;
    }

    public void setUsUsuariosCollection(Collection<UsUsuarios> usUsuariosCollection) {
        this.usUsuariosCollection = usUsuariosCollection;
    }*/

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }

}
