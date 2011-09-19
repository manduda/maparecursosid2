/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author miguel.duran
 */
public class UsersVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int userCode;
    private String name;
    private String lastName;
    private String email;
    private String login;
    private String password;
    private Collection<UsUsuariosVO> usUsuariosCollection;

    public UsersVO() {
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

    public Collection<UsUsuariosVO> getUsUsuariosCollection() {
        return usUsuariosCollection;
    }

    public void setUsUsuariosCollection(Collection<UsUsuariosVO> usUsuariosCollection) {
        this.usUsuariosCollection = usUsuariosCollection;
    }

    public int getUserCode() {
        return userCode;
    }

    public void setUserCode(int userCode) {
        this.userCode = userCode;
    }
}
