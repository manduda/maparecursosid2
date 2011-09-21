/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import facade.facade;
import java.io.IOException;
import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.IdleEvent;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "inicioBean")
@RequestScoped
public class InicioBean {
    
    @ManagedProperty(value = "#{UserBean}")
    private UserBean userBean;
    
    @ManagedProperty(value = "#{ConfiguracionBean}")
    private ConfiguracionBean configuracion;

    private String user;
    private String password;
    private String Mensaje = "";
    //private String rutaContexto;
    
    /*@PostConstruct
    public void init() {
        rutaContexto = configuracion.getRutaContexto();
    }*/
    /** Creates a new instance of InicioBean */
    public InicioBean() {
        // ----- CARGAR CONFIGURACION -----
        //ConfiguracionBean configuracion = (ConfiguracionBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("ConfiguracionBean");
        //rutaContexto = configuracion.getRutaContexto();
        // --------------------------------
        user = "";
        password = "";
    }

    public String validarUsuario() {
        /*if((user.equals("")) || (password.equals(""))){
            Mensaje = "<br><font color=\"red\">Los campos de <b>Usuario</b> y <b>Contraseña</b> son obligatorios</font><br><br>";
            return configuracion.getRutaContexto()+"resultadoLogin"; 
        }*/
        
        facade fachada = new facade();
        UsUsuariosVO userVO = null;
        userVO = fachada.iniciarSesion(user, password);
        user = "";
        password = "";

        if (userVO != null){
            userVO.getCodigoSIUST().setPassword("");//se quita la contraseña para que no quede en sesion
            if (userVO.getUsnEstado() == 1){
                userBean.setUserVO(userVO);
                userBean.setLogin(true);
                int tipoUsuario = userBean.getUserVO().getTunCodigo().getTunCodigo();
                switch(tipoUsuario) {
                    case 1: // ADMINISTADOR
                        userBean.setRecuperar(false);
                        userBean.setPreasignar(false);
                        userBean.setReservar(true);
                        userBean.setLiberar(true);
                        userBean.setEliminarRecurso(false);
                        userBean.setCrearTramite(false);
                        userBean.setEnviarTramite(false);
                        userBean.setArchivarTramite(false);
                        userBean.setDevolverTramite(false);
                        userBean.setAprobarTramite(false);
                        userBean.setTerminarTramite(true);
                        userBean.setCambiarOperadorTramite(false);
                        userBean.setConsultarTramite(true);
                        userBean.setTransferirRecursos(true);
                        userBean.setAdministrarUsuarios(true);
                        break;
                    case 2: // CORRDINADOR
                        userBean.setRecuperar(false);
                        userBean.setPreasignar(false);
                        userBean.setReservar(false);
                        userBean.setLiberar(false);
                        userBean.setEliminarRecurso(false);
                        userBean.setCrearTramite(false);
                        userBean.setEnviarTramite(false);
                        userBean.setArchivarTramite(true);
                        userBean.setDevolverTramite(true);
                        userBean.setAprobarTramite(true);
                        userBean.setTerminarTramite(false);
                        userBean.setCambiarOperadorTramite(false);
                        userBean.setConsultarTramite(true);
                        userBean.setTransferirRecursos(false);
                        userBean.setAdministrarUsuarios(false);
                        break;
                    case 3: // ASESOR
                        userBean.setRecuperar(true);
                        userBean.setPreasignar(true);
                        userBean.setReservar(false);
                        userBean.setLiberar(false);
                        userBean.setEliminarRecurso(true);
                        userBean.setCrearTramite(true);
                        userBean.setEnviarTramite(true);
                        userBean.setArchivarTramite(false);
                        userBean.setDevolverTramite(false);
                        userBean.setAprobarTramite(false);
                        userBean.setTerminarTramite(false);
                        userBean.setCambiarOperadorTramite(true);
                        userBean.setConsultarTramite(true);
                        userBean.setTransferirRecursos(false);
                        userBean.setAdministrarUsuarios(false);
                        break;
                }
                
                Mensaje = "<br>Sesión iniciada<br><br>"
                        + "Bienvenid@ " + userVO.getCodigoSIUST().getName() + " " + userVO.getCodigoSIUST().getLastName() + "<br><br>"
                        + "Tipo de usuario: <b>" + userVO.getTunCodigo().getTutNombre() + "</b><br><br>";
                return configuracion.getRutaContexto()+"resultadoLogin";
            } else {
                //Usuario está deshabilitado
                Mensaje = "<br>Usuario " + userVO.getCodigoSIUST().getLogin() + " está deshabilitado<br><br>";
                return null;
            }
        } else {
            //Usuario o contraseña incorrectos
            Mensaje = "<br>Nombre de usuario o contraseña incorrectos<br><br>";
            return null;
        }
        //return configuracion.getRutaContexto()+"resultadoLogin";
    }
    
    public void login(ActionEvent actionEvent) {
        RequestContext context = RequestContext.getCurrentInstance();
        FacesMessage msg = null;
        boolean loggedIn = false; // Esta variable controla el cierre el dialogo de inicio de sesión si es "true"
        
        if((user.equals("")) || (password.equals(""))){
            Mensaje = "<br><font color=\"red\">Los campos de <b>Usuario</b> y <b>Contraseña</b> son obligatorios</font><br><br>";
            msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Login error", "Los campos de Usuario y Contraseña son obligatorios");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            context.addCallbackParam("loggedIn", loggedIn);
            return;
        }
          
        facade fachada = new facade();
        UsUsuariosVO userVO = null;
        userVO = fachada.iniciarSesion(user, password);
        user = "";
        password = "";

        if (userVO != null){
            userVO.getCodigoSIUST().setPassword("");//se quita la contraseña para que no quede en sesion
            if (userVO.getUsnEstado() == 1){
                userBean.setUserVO(userVO);
                userBean.setLogin(true);
                loggedIn = true;
            } else {
                loggedIn = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "El usuario "+userVO.getCodigoSIUST().getLogin()+"está deshabilitado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Nombre de usuario o contraseña incorrectos");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        context.addCallbackParam("loggedIn", loggedIn);
        
    } 
    

    public String cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.removeAttribute("UserBean");
        session.invalidate(); 
        Mensaje = "<br>Sesión cerrada correctamente<br><br>";
        return configuracion.getRutaContexto()+"resultadoLogin";
    }
    
    public void idleListener(IdleEvent event) throws IOException {  
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.removeAttribute("UserBean");
        session.invalidate();
        
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String path = servletContext.getContextPath()+configuracion.getRutaContexto();
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

    public UserBean getUserBean() {
        return userBean;
    }

    public void setUserBean(UserBean userBean) {
        this.userBean = userBean;
    }
    
    public ConfiguracionBean getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfiguracionBean configuracion) {
        this.configuracion = configuracion;
    }
}
