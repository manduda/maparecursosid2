/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import facade.facade;
import java.io.IOException;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.event.IdleEvent;
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
    private String rutaContexto;
    /** Creates a new instance of InicioBean */
    public InicioBean() {
        // ----- CARGAR CONFIGURACION -----
        ConfiguracionBean configuracion = (ConfiguracionBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("ConfiguracionBean");
        rutaContexto = configuracion.getRutaContexto();
        // --------------------------------
        user = "";
        password = "";
    }

    public String validarUsuario() {
        if((user.equals("")) || (password.equals(""))){
            Mensaje = "<br><font color=\"red\">Los campos de <b>Usuario</b> y <b>Contraseña</b> son obligatorios</font><br><br>";
            return rutaContexto+"resultadoLogin"; 
        }
        
        facade fachada = new facade();
        UsUsuariosVO userVO = null;
        userVO = fachada.iniciarSesion(user, password);
        user = "";
        password = "";

        if (userVO != null){
            userVO.getCodigoSIUST().setPassword("");//se quita la contraseña para que no quede en sesion
            if (userVO.getUsnEstado() == 1){
                //Usuario logueado
                UserBean usuario = new UserBean();
                usuario.setUserVO(userVO);
                usuario.setLogin(true);
                FacesContext facesContext = FacesContext.getCurrentInstance();
                HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
                facesContext.getExternalContext().getSessionMap().put("UserBean", usuario);
                Mensaje = "<br>Sesión iniciada<br><br>"
                        + "Bienvenid@ " + userVO.getCodigoSIUST().getName() + " " + userVO.getCodigoSIUST().getLastName() + "<br><br>"
                        + "Tipo de usuario: <b>" + userVO.getTunCodigo().getTutNombre() + "</b><br><br>";
                //return "cerrar";
            } else {
                //Usuario está deshabilitado
                Mensaje = "<br>Usuario " + userVO.getCodigoSIUST().getLogin() + " está deshabilitado<br><br>";
                //return null;
            }
        } else {
            //Usuario o contraseña incorrectos
            Mensaje = "<br>Nombre de usuario o contraseña incorrectos<br><br>";
            //return null;
        }
        return rutaContexto+"resultadoLogin";
    }

    public String cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.removeAttribute("UserBean");
        session.invalidate(); 
        Mensaje = "<br>Sesión cerrada correctamente<br><br>";
        return rutaContexto+"resultadoLogin";
    }
    
    public void idleListener(IdleEvent event) throws IOException {  
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.removeAttribute("UserBean");
        session.invalidate();
        
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String path = servletContext.getContextPath()+rutaContexto;
        facesContext.getExternalContext().redirect(path+"resultadoLogin.xhtml");
        Mensaje = "<br>Sesión cerrada automáticamente por inactividad<br><br>";
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
