/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.google.api.client.auth.oauth2.Credential;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeFlow;
import com.google.api.client.googleapis.auth.oauth2.GoogleAuthorizationCodeRequestUrl;
import com.google.api.client.googleapis.auth.oauth2.GoogleTokenResponse;
import com.google.api.client.http.GenericUrl;
import com.google.api.client.http.HttpRequest;
import com.google.api.client.http.HttpRequestFactory;
import com.google.api.client.http.HttpTransport;
import com.google.api.client.http.javanet.NetHttpTransport;
import com.google.api.client.json.JsonFactory;
import com.google.api.client.json.jackson.JacksonFactory;
import com.sun.faces.context.SessionMap;
import facade.facade;
import java.io.IOException;
import java.security.SecureRandom;
import java.util.Arrays;
import java.util.Collection;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.application.Application;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ApplicationScoped;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
//import org.primefaces.event.IdleEvent;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "inicioBean")
@RequestScoped
public class InicioBean {
    
    //@ManagedProperty(value = "#{UserBean}")
    private UserBean userBean = new UserBean();
    
    //@ManagedProperty(value = "#{ConfiguracionBean}")
    private ConfiguracionBean configuracion;

    private String user;
    private String password;
    private String email;
    private String name;
    private String Mensaje = "";
    
    private String code;
    private String codeAdmin;
    
    private GoogleAuthorizationCodeFlow flow;
    private String CALLBACK_URI;
    private String CLIENT_ID;
    private String CLIENT_SECRET;
    
    /*private String CALLBACK_URI = configuracion.getCallbackUri();
    private String CLIENT_ID = configuracion.getClientId();
    private String CLIENT_SECRET = configuracion.getClientSecret();*/

    private String stateToken;

        // start google authentication constants
    private static final Collection<String> SCOPE = Arrays.asList("https://www.googleapis.com/auth/userinfo.profile;https://www.googleapis.com/auth/userinfo.email".split(";"));
    private static final String USER_INFO_URL = "https://www.googleapis.com/oauth2/v2/userinfo";
    private static final JsonFactory JSON_FACTORY = new JacksonFactory();
    private static final HttpTransport HTTP_TRANSPORT = new NetHttpTransport();
    // end google authentication constants    //private String rutaContexto;
    
    /*@PostConstruct
    public void init() {
        rutaContexto = configuracion.getRutaContexto();
        
    }*/
    /** Creates a new instance of InicioBean */
    public InicioBean() {
        FacesContext context = FacesContext.getCurrentInstance();    
        Application app = context.getApplication();    
        this.configuracion = (ConfiguracionBean) app.evaluateExpressionGet(context, "#{ConfiguracionBean}", ConfiguracionBean.class);
        //facade fachada = new facade();
        CALLBACK_URI = configuracion.getCallbackUri();//fachada.buscarConfiguracionNombre("callbackUri").getCotValor();
        CLIENT_ID = configuracion.getClientId();//fachada.buscarConfiguracionNombre("clientId").getCotValor();
        CLIENT_SECRET = configuracion.getClientSecret();//fachada.buscarConfiguracionNombre("clientSecret").getCotValor();
        flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE).build();
    }
    
    public void cargar () {
        // ----- CARGAR CONFIGURACION -----
        //ConfiguracionBean configuracion = (ConfiguracionBean) FacesContext.getCurrentInstance().getExternalContext().getApplicationMap().get("ConfiguracionBean");
        //rutaContexto = configuracion.getRutaContexto();
        // --------------------------------
        user = "";
        password = "";
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpServletRequest requested = (HttpServletRequest) facesContext.getExternalContext().getRequest();
        
        SessionMap sessionMap = (SessionMap) facesContext.getExternalContext().getSessionMap();
        String stateSesion = (String) sessionMap.get("state");
        
        //userSession = (UserBean) sessionMap.get("UserBean");
        
        if (stateSesion == null){
            generateStateToken();
        }
        
        //flow = new GoogleAuthorizationCodeFlow.Builder(HTTP_TRANSPORT, JSON_FACTORY, CLIENT_ID, CLIENT_SECRET, SCOPE).build();
        
        String error = requested.getParameter("error");
        
        if (error != null && error.equals("access_denied")){
            userBean.setLogin(false);
            Mensaje = "Acceso denegado";
        } else {
            String codigo = requested.getParameter("code");
            String state = requested.getParameter("state");

            if (codigo != null ) {
                validarUsuario(codigo);
                
                sessionMap.remove("state");
            }
        }
        
        
    }
    
    private void getGoogleUser (String authCode) {
        try {
            final GoogleTokenResponse response = flow.newTokenRequest(authCode).setRedirectUri(CALLBACK_URI).execute();
            final Credential credential = flow.createAndStoreCredential(response, null);
            final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
            // Make an authenticated request
            final GenericUrl url = new GenericUrl(USER_INFO_URL);
            final HttpRequest request = requestFactory.buildGetRequest(url);
            request.getHeaders().setContentType("application/json");
            final String jsonIdentity = request.execute().parseAsString();
            
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData = mapper.readValue(jsonIdentity, Map.class);
            
            email = (String) userData.get("email");
            String name = (String) userData.get("name");
            String picture = (String) userData.get("picture");
            userBean.setLogin(true);
            userBean.setEmail(email);
            userBean.setName(name);
            Mensaje = "Acceso exitoso";

        } catch (IOException ex) {
            Logger.getLogger(InicioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private void generateStateToken(){

        SecureRandom sr1 = new SecureRandom();

        stateToken = "google;"+sr1.nextInt();
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        SessionMap sessionMap = (SessionMap) facesContext.getExternalContext().getSessionMap();
        sessionMap.put("state", stateToken);

    }

    private void validarUsuario(String codigo) {
        try {
            final GoogleTokenResponse response = flow.newTokenRequest(codigo).setRedirectUri(CALLBACK_URI).execute();
            final Credential credential = flow.createAndStoreCredential(response, null);
            final HttpRequestFactory requestFactory = HTTP_TRANSPORT.createRequestFactory(credential);
            // Make an authenticated request
            final GenericUrl url = new GenericUrl(USER_INFO_URL);
            final HttpRequest request = requestFactory.buildGetRequest(url);
            request.getHeaders().setContentType("application/json");
            final String jsonIdentity = request.execute().parseAsString();
            
            ObjectMapper mapper = new ObjectMapper();
            Map<String,Object> userData = mapper.readValue(jsonIdentity, Map.class);
            
            email = (String) userData.get("email");
            name = (String) userData.get("name");
            String picture = (String) userData.get("picture");
            
            /*userBean.setLogin(true);
            userBean.setEmail(email);
            userBean.setName(name);
            Mensaje = "Acceso exitoso";*/

        } catch (IOException ex) {
            //Logger.getLogger(InicioBean.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        if (email == null){
            Mensaje = "<br>Error al iniciar sesión. Inténtalo nuevamente<br><br>";
            return;
        }

        facade fachada = new facade();
        UsUsuariosVO userVO = null;
        userVO = fachada.iniciarSesion(email);
        user = "";
        password = "";

        if (userVO != null){
            //userVO.getCodigoSIUST().setPassword("");//se quita la contraseña para que no quede en sesion
            if (userVO.getUsnEstado() == 1){
                //UserBean userBean = new UserBean();

                userBean.setUserVO(userVO);
                userBean.setLogin(true);
                userBean.setEmail(email);
                userBean.setName(name);
                
                int tipoUsuario = userBean.getUserVO().getTunCodigo().getTunCodigo();
                switch(tipoUsuario) {
                    case 1: // ADMINISTADOR
                        //userBean.setRecuperar(false);
                        //userBean.setPreasignar(false);
                        userBean.setReservar(true);
                        userBean.setLiberar(true);
                        userBean.setEditarCodigos1xy(true);
                        userBean.setEliminarRecurso(false);
                        userBean.setCrearTramite(false);
                        userBean.setEnviarTramite(false);
                        userBean.setArchivarTramite(true);
                        userBean.setDevolverTramite(false);
                        userBean.setAprobarTramite(false);
                        userBean.setTerminarTramite(true);
                        userBean.setCambiarOperadorTramite(false);
                        userBean.setCambiarUsuarioTramite(true);
                        userBean.setConsultarTramite(true);
                        userBean.setTransferirRecursos(true);
                        userBean.setAdministrarUsuarios(true);
                        userBean.setAdministrarModulos(true);
                        userBean.setAsignarTramites(false);
                        userBean.setReasignarTramite(false);
                        userBean.setConsultarReservas(true);
                        break;
                    case 2: // COORDINADOR
                        //userBean.setRecuperar(false);
                        //userBean.setPreasignar(false);
                        userBean.setReservar(false);
                        userBean.setLiberar(false);
                        userBean.setEditarCodigos1xy(false);
                        userBean.setEliminarRecurso(false);
                        userBean.setCrearTramite(false);
                        userBean.setEnviarTramite(false);
                        userBean.setArchivarTramite(true);
                        userBean.setDevolverTramite(true);
                        userBean.setAprobarTramite(true);
                        userBean.setTerminarTramite(false);
                        userBean.setCambiarOperadorTramite(false);
                        userBean.setCambiarUsuarioTramite(true);
                        userBean.setConsultarTramite(true);
                        userBean.setTransferirRecursos(false);
                        userBean.setAdministrarUsuarios(false);
                        userBean.setAdministrarModulos(false);
                        userBean.setAsignarTramites(false);
                        userBean.setReasignarTramite(false);
                        userBean.setConsultarReservas(true);
                        break;
                    case 3: // ASESOR
                        //userBean.setRecuperar(true);
                        //userBean.setPreasignar(true);
                        userBean.setReservar(false);
                        userBean.setLiberar(false);
                        userBean.setEditarCodigos1xy(false);
                        userBean.setEliminarRecurso(true);
                        userBean.setCrearTramite(true);
                        userBean.setEnviarTramite(true);
                        userBean.setArchivarTramite(false);
                        userBean.setDevolverTramite(false);
                        userBean.setAprobarTramite(false);
                        userBean.setTerminarTramite(false);
                        userBean.setCambiarOperadorTramite(true);
                        userBean.setCambiarUsuarioTramite(false);
                        userBean.setConsultarTramite(true);
                        userBean.setTransferirRecursos(false);
                        userBean.setAdministrarUsuarios(false);
                        userBean.setAdministrarModulos(false);
                        userBean.setAsignarTramites(false);
                        userBean.setReasignarTramite(true);
                        userBean.setConsultarReservas(true);
                        
                        Permisos permisos = new Permisos();
                        permisos.setNumeracion(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),1));
                        permisos.setSenalizacion(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),2));
                        permisos.setCodigosLd(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),3));
                        permisos.setCodigosCortos(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),4));
                        permisos.setCodigos1xy(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),5));
                        permisos.setCodigosIin(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),6));
                        permisos.setCodigosMnc(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),7));
                        permisos.setMarcacionAbreviada(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),8));
                        permisos.setCodigosNrn(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),9));
                        permisos.setRecursosTdt(fachada.tienePermiso(userBean.getUserVO().getUsnCodigo(),10));
                        
                        userBean.setPermisos(permisos);
                        
                        break;
                    case 4: // ASIGNADOR
                        //userBean.setRecuperar(false);
                        //userBean.setPreasignar(false);
                        userBean.setReservar(false);
                        userBean.setLiberar(false);
                        userBean.setEditarCodigos1xy(false);
                        userBean.setEliminarRecurso(false);
                        userBean.setCrearTramite(false);
                        userBean.setEnviarTramite(false);
                        userBean.setArchivarTramite(false);
                        userBean.setDevolverTramite(false);
                        userBean.setAprobarTramite(false);
                        userBean.setTerminarTramite(false);
                        userBean.setCambiarOperadorTramite(false);
                        userBean.setCambiarUsuarioTramite(false);
                        userBean.setConsultarTramite(true);
                        userBean.setTransferirRecursos(false);
                        userBean.setAdministrarUsuarios(false);
                        userBean.setAdministrarModulos(false);
                        userBean.setAsignarTramites(true);
                        userBean.setReasignarTramite(false);
                        userBean.setConsultarReservas(false);
                        break;
                }
                
                FacesContext facesContext = FacesContext.getCurrentInstance();
                SessionMap sessionMap = (SessionMap) facesContext.getExternalContext().getSessionMap();
                sessionMap.put("UserBean", userBean);

                
                Mensaje = "<br><font size=\"5\">Sesión iniciada</font><br><br>"
                        + "Bienvenid@ " + userBean.getName() + "<br><br>"
                        + "Tipo de usuario: <b>" + userVO.getTunCodigo().getTutNombre() + "</b><br><br>"
                        + userBean.getEmail() +"<br><br>";
                //return configuracion.getRutaContexto()+"resultadoLogin";
            } else {
                //Usuario está deshabilitado
                Mensaje = "<br>Usuario " + userVO.getCodigoSIUST().getEmail() + " está deshabilitado<br><br>";
                //return null;
            }
        } else {
            //Usuario o contraseña incorrectos
            Mensaje = "<br>Nombre de usuario no autorizado<br><br>";
            //return null;
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
        userVO = fachada.iniciarSesion(email);
        user = "";
        password = "";

        if (userVO != null){
            //userVO.getCodigoSIUST().setPassword("");//se quita la contraseña para que no quede en sesion
            if (userVO.getUsnEstado() == 1){
                userBean.setUserVO(userVO);
                userBean.setLogin(true);
                loggedIn = true;
            } else {
                loggedIn = false;
                msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "El usuario "+userVO.getCodigoSIUST().getEmail()+"está deshabilitado");
                FacesContext.getCurrentInstance().addMessage(null, msg);
            }
        } else {
            loggedIn = false;
            msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Login Error", "Nombre de usuario no autorizado");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        
        context.addCallbackParam("loggedIn", loggedIn);
        
    } 
    

    public String cerrarSesion() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        SessionMap sessionMap = (SessionMap) facesContext.getExternalContext().getSessionMap();

        sessionMap.remove("UserBean");

        //session.removeAttribute("UserBean");
        session.invalidate(); 
        Mensaje = "<br><font size=\"5\">Sesión del sistema cerrada correctamente</font><br><br>";
        Mensaje = Mensaje + "<font color=\"red\"><b>Si estás en un equipo público, no olvides cerrar<br>";
        Mensaje = Mensaje + "también tu sesión de Google.<br>";
        Mensaje = Mensaje + "Para cerrala pulsa en el siguiente botón.</b></font><br><br>";
        return configuracion.getRutaContexto()+"resultadoLogin";
    }
    
    /*public void idleListener(IdleEvent event) throws IOException {  
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);

        session.removeAttribute("UserBean");
        session.invalidate();
        
        ServletContext servletContext = (ServletContext) facesContext.getExternalContext().getContext();
        String path = servletContext.getContextPath()+configuracion.getRutaContexto();
        facesContext.getExternalContext().redirect(path+"resultadoLogin.xhtml");
        Mensaje = "<br>Sesión cerrada automáticamente por inactividad<br><br>";
    }*/  
    
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

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
    
    public String getCode() {
        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        
        url.setRedirectUri(CALLBACK_URI);
        url.setState(stateToken);
        url.set("max_auth_age", "0");
        url.set("hd", "crcom.gov.co");
        
        return url.build();
    }

    public void setCode(String Code) {
        this.code = code;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCodeAdmin() {
        final GoogleAuthorizationCodeRequestUrl url = flow.newAuthorizationUrl();
        
        url.setRedirectUri(CALLBACK_URI);
        url.setState(stateToken);
        url.set("max_auth_age", "0");
        
        return url.build();
    }

    public void setCodeAdmin(String codeAdmin) {
        this.codeAdmin = codeAdmin;
    }

   
    
}
