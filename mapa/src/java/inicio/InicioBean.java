/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import facade.facade;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "inicioBean")
@RequestScoped
public class InicioBean {
    private String user;
    private String password;
    private String Mensaje = "";
    /** Creates a new instance of InicioBean */
    public InicioBean() {
        
    }

    public String validarUsuario() {
        facade fachada = new facade();
        UsUsuariosVO userVO = null;
        userVO = fachada.iniciarSesion(user, password);

        if (userVO != null){
            userVO.getUsnCodigo().setPassword("");//se quita la contraseña para que no quede en sesion
            if (userVO.getUsnEstado() == 1){
                //Usuario logueado
                UserBean usuario = new UserBean();
                usuario.setUserVO(userVO);
                usuario.setLogin(true);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                facesContext.getExternalContext().getSessionMap().put("UserBean", usuario);
                Mensaje = "";
                return null;
            } else {
                //Usuario está deshabilitado
                Mensaje = "Usuario " + userVO.getUsnCodigo().getLogin() + " está deshabilitado";
                return null;
            }
        } else {
            //Usuario o contraseña incorrectos
            Mensaje = "Nombre de usuario o contraseña incorrectos";
            return null;
        }
        
    }

    public String cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.removeAttribute("UserBean");
        session.invalidate(); 
        
        return null;
    }
    
    public String getMensaje() {
        return Mensaje;
    }

    public void setMensaje(String Mensaje) {
        this.Mensaje = Mensaje;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getUser() {
        return user;
    }

    public void setUser(String user) {
        this.user = user;
    }
    
}
