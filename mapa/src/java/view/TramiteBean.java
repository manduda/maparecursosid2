/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import inicio.ConfiguracionBean;
import facade.facade;
import helper.ConvertirListasHelper;
import inicio.UserBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.annotation.PostConstruct;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import javax.servlet.ServletContext;
import javax.servlet.http.HttpSession;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import utils.Correo;
import vo.AcAccionVO;
import vo.CcCodigosCortosVO;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.MunicipiosVO;
import vo.SeSenalizacionVO;
import vo.TcTramiteCcVO;
import vo.TlTramiteLdVO;
import vo.TrTramitesVO;
import vo.TsTramiteSenalizacionVO;
import vo.UsUsuariosVO;
import vo.UsersVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "TramiteBean")
@SessionScoped
public class TramiteBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @ManagedProperty(value = "#{UserBean}")
    private UserBean userSession;
    
    @ManagedProperty(value = "#{ConfiguracionBean}")
    private ConfiguracionBean configuracion;
    
    //private String rutaContexto;
    //private String operadorNinguno;
    //private String municipioNinguno;
    private List<TrTramitesVO> tramites = new ArrayList<TrTramitesVO>();
    private TrTramitesVO selectedTramite;
    private Collection<SelectItem> listaOperador;
    private String operadorOrigen;
    private String operadorDestino;
    private Collection<SelectItem> listaTramites;
    private Collection<SelectItem> listaDepartamento;
    private Collection<SelectItem> listaMunicipio;
    
    private Collection<SelectItem> listaEstadoTramite;
    private String tramiteIdBuscarTramite = "";
    private int usuarioBuscarTramite = -1;
    private int estadoBuscarTramite = -1;
    private String operadorBuscarTramite = "-1";
    private String radicadoBuscarTramite = "";
    private List<TrTramitesVO> listaBuscarTramite = new ArrayList<TrTramitesVO>();
    private int countListaBuscarTramite;
    private TrTramitesVO seleccionBuscarTramite = new TrTramitesVO();
    
    private Collection<SelectItem> listaUsuariosAplicacion;
    private List<UsUsuariosVO> usuariosAplicacion = new ArrayList<UsUsuariosVO>();

    private Collection<SelectItem> listaAsesores;
    private List<UsUsuariosVO> usuariosAsesores = new ArrayList<UsUsuariosVO>();
    
    private Collection<SelectItem> listaUsuariosNoAplicacion;
    
    private Collection<SelectItem> listaUsuariosSIUST;
    private int usuariosBuscar = 0;
    private UsUsuariosVO usuariosEncontrado = new UsUsuariosVO();
    private String mensajeAdminUsuario = "";
    
    private String seleccionDepartamento;
    private String seleccionMunicipio;
    private Integer tramiteAgregarRecurso;
    private String operadorCrearTramite;
    private UsUsuariosVO userVO;
    private String mensajeCrearTramite = "";
    private String mensajeTramite = "";
    private String mensajeRecurso = "";
    private String mensajeTransferirRecursos = "";
    private String radicadoAgregarRecurso = "";
    private String observacionesAgregarRecurso = "";
    private Integer tipoUsuario = 0;
    private Integer nuevoUsuarioTramite = 0;
    private String mensajeCambiarUsuarioTramite = "";
    
    private Integer codigoDetalleTramite = -1;
    private String tipoRecurso = "";
    private int codigoAccion = 0;
    
    private Boolean seleccionNumeracion;
    private Boolean seleccionSenalizacion;
    private Boolean seleccionIin;
    private Boolean seleccionMnc;
    private Boolean opcionNumeracion;
    private Boolean opcionSenalizacion;
    private Boolean opcionIin;
    private Boolean opcionMnc;
    private String observacionesTramite;
    private String resolucionTerminarTramite;
    private Date fechaResolucionTerminarTramite;
        
    boolean tramiteTieneDetalle;
    private TsTramiteSenalizacionVO tramiteSenalizacionVO = new TsTramiteSenalizacionVO();
    private TlTramiteLdVO tramiteCodigosLdVO = new TlTramiteLdVO();
    private TcTramiteCcVO tramiteCodigosCortosVO = new TcTramiteCcVO();

    @PostConstruct
    public void init() {
        facade fachada = new facade();
        if (userSession.getLogin()) {
            userVO = userSession.getUserVO();
            switch(userVO.getTunCodigo().getTunCodigo()) {
                case 1:
                    //tramites = fachada.cargarTramites(0,-1,-1,"","-1",4);
                    tipoUsuario = 4;
                    break;
                case 2:
                    //tramites = fachada.cargarTramites(0,-1,-1,"","-1",2);
                    tipoUsuario = 2;
                    break;
                case 3:
                    //tramites = fachada.cargarTramites(0,-1,-1,userVO.getCodigoSIUST().getLogin(),"-1",1);
                    //for (TrTramitesVO t : fachada.cargarTramites(0,-1,-1,userVO.getCodigoSIUST().getLogin(),"-1",3)){
                    //    tramites.add(t);
                    //}
                    tipoUsuario = 6;
                    break;
            }
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
        }
    }
     
    /** Creates a new instance of TramiteBean */
    public TramiteBean()    {
        facade fachada = new facade();
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.cargarOperadores(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaDepartamento = convertir.createSelectItemsList(fachada.listaDepartamentos(), "getCodigoDepartamento", null, "getNombreDepartamento", true, "");
            listaMunicipio = convertir.createSelectItemsList(fachada.listaMunicipios(seleccionDepartamento), "getCodigoMunicipio", null, "getNombreMunicipio", true, "");
            listaEstadoTramite = convertir.createSelectItemsList(fachada.listaEstadoTramites(), null, "getEtnCodigo", "getEttNombre", true, "");
            usuariosAplicacion = fachada.listaUsuariosAplicacion();
            List<UsersVO> usr = new ArrayList<UsersVO>();
            for (UsUsuariosVO u : usuariosAplicacion){
                usr.add(u.getCodigoSIUST());
            }
            listaUsuariosAplicacion = convertir.createSelectItemsList(usr, null, "getUserCode", "getLogin", true, "");

            usuariosAsesores = fachada.listaAsesores();
            List<UsersVO> usrAsesores = new ArrayList<UsersVO>();
            for (UsUsuariosVO u : usuariosAsesores){
                usrAsesores.add(u.getCodigoSIUST());
            }
            listaAsesores = convertir.createSelectItemsList(usrAsesores, null, "getUserCode", "getLogin", true, "");
            
            listaUsuariosNoAplicacion = convertir.createSelectItemsList(fachada.listaUsuariosNoAplicacion(), null, "getUserCode", "getLogin", true, "");
            
            listaUsuariosSIUST = convertir.createSelectItemsList(fachada.listaUsuariosSIUST(), null, "getUserCode", "getLogin", true, "");
            
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Tramites", e);
        }
        
    }
    
    /*public String historiaTramite() {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer codigoTramite = Integer.parseInt(context.getExternalContext().getRequestParameterValuesMap().get("codigoTramite")[0]);
        //System.out.println("Codigo: "+codigoTramite);

        for (TrTramitesVO detalleVO : tramites) {
            if (detalleVO.getTrnCodigo() == codigoTramite){
                selectedTramite = detalleVO;
                break;
            }
        }

        return null;
    }*/
    
    // ---- Funciones para administrar usuarios ---
    public String buscarUsuario() {
        facade fachada = new facade();

        final Integer id;
        final Integer rad;
        
        if (usuariosBuscar == -1) {
            usuariosEncontrado = new UsUsuariosVO();
            return null;
        }
        
        usuariosEncontrado = fachada.buscarUsuario(usuariosBuscar);

        return null;
    }
    
    public String cambiarPerfil() {
        if (usuariosEncontrado.getCodigoSIUST().getUserCode() == 0){
            mensajeAdminUsuario = "<br><b>Error al cambiar el perfil al usuario.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        
        facade fachada = new facade();
        int perfil = usuariosEncontrado.getTunCodigo().getTunCodigo();
        Integer resultado = fachada.cambiarPerfil(usuariosEncontrado,perfil);
        
        switch(resultado){
            case 0:
                mensajeAdminUsuario = "<br><b>Error al cambiar el perfil.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                try {
                    ConvertirListasHelper convertir = new ConvertirListasHelper();
                    usuariosAplicacion = fachada.listaUsuariosAplicacion();
                    List<UsersVO> usr = new ArrayList<UsersVO>();
                    for (UsUsuariosVO u : usuariosAplicacion){
                        usr.add(u.getCodigoSIUST());
                    }
                    listaUsuariosAplicacion = convertir.createSelectItemsList(usr, null, "getUserCode", "getLogin", true, "");

                    usuariosAsesores = fachada.listaAsesores();
                    List<UsersVO> usrAsesores = new ArrayList<UsersVO>();
                    for (UsUsuariosVO u : usuariosAsesores){
                        usrAsesores.add(u.getCodigoSIUST());
                    }
                    listaAsesores = convertir.createSelectItemsList(usrAsesores, null, "getUserCode", "getLogin", true, "");

                    listaUsuariosNoAplicacion = convertir.createSelectItemsList(fachada.listaUsuariosNoAplicacion(), null, "getUserCode", "getLogin", true, "");

                } catch (Exception e) {
                    Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Tramites", e);
                }
                
                usuariosEncontrado = fachada.buscarUsuario(usuariosEncontrado.getCodigoSIUST().getUserCode());
                mensajeAdminUsuario  = "<br><b>Perfil cambiado correctamente al usuario "
                        + usuariosEncontrado.getCodigoSIUST().getLogin() + ".</b><br><br>";
                break;
            case 2:
                mensajeAdminUsuario = "<br><b>El perfil a asignar no puede ser igual al que tiene actualmente del usuario.</b><br><br>";
                break;
        }

        return null;
    }
    // -------------------------------------------
    
    // ---- Funciones para crear trámite ---
    public String opcionesCrearTramite() {
        facade fachada = new facade();
        operadorCrearTramite = "-1";
        mensajeCrearTramite = "";
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.cargarOperadores(), "getEmrCodigo", null, "getEmtNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Trámites", e);
        }
        
        return configuracion.getRutaContexto()+"usuarios/crearTramite";
    }
    
    public String crearTramite() {
        if(operadorCrearTramite.equals("-1")){
            mensajeCrearTramite = "<br><b>Error al crear el trámite.</b><br><br>Debes escoger un operador<br><br>";
            return null;
        }
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeCrearTramite = "";
        
        EmOperadorVO operador = new EmOperadorVO();
        operador.setEmrCodigo(operadorCrearTramite);
        
        //EtEstadoTramiteVO estado = new EtEstadoTramiteVO();
        //estado.setEtnCodigo(1);
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setEmrCodigo(operador);
        //vo.setEtnCodigo(estado);
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
   
        boolean resultado = fachada.crearTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeCrearTramite = "<br><b>Trámite creado.</b><br><br>Código del trámite: "+tramites.get(0).getTrnCodigo()+"<br><br>";
        } else {
            mensajeCrearTramite = "<br><b>Error al crear el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    // -------------------------------------
    
    // ---- Función para ejecutar anter de acceder al trámite ---
    public String detalleTramite() {
        //FacesContext context = FacesContext.getCurrentInstance();
        //Integer codigoTramite = Integer.parseInt(context.getExternalContext().getRequestParameterValuesMap().get("codigoTramite")[0]);
        mensajeTramite = "";

        /*for (TrTramitesVO detalleVO : tramites) {
            if (detalleVO.getTrnCodigo() == codigoTramite){
                selectedTramite = detalleVO;
                break;
            }
        }*/
        /*facade fachada = new facade();
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.cargarOperadores(), "getEmrCodigo", null, "getEmtNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Trámites", e);
        }*/
        
        return configuracion.getRutaContexto()+"usuarios/tramite";
    }
    
    public String detalleTramiteSeleccionado(SelectEvent event) {
        mensajeTramite = "";
        selectedTramite = seleccionBuscarTramite;
        seleccionBuscarTramite = null;
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedTramite", event.getObject());  
        return configuracion.getRutaContexto()+"usuarios/tramite?faces-redirect=true";
    }
    // ----------------------------------------------------------
    
    public String archivarTramite() {
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeTramite = "";
        
        //EtEstadoTramiteVO estado = new EtEstadoTramiteVO();
        //estado.setEtnCodigo(6);
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setTrnCodigo(selectedTramite.getTrnCodigo());
        //vo.setEtnCodigo(estado);
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
   
        boolean resultado = fachada.archivarTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeTramite = "<br><b>Trámite borrado.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
        } else {
            mensajeTramite = "<br><b>Error al borrar el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    
    public String enviarTramite() {
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeTramite = "";
        
        //EtEstadoTramiteVO estado = new EtEstadoTramiteVO();
        //estado.setEtnCodigo(2);
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setTrnCodigo(selectedTramite.getTrnCodigo());
        //vo.setEtnCodigo(estado);
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
   
        boolean resultado = fachada.enviarTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeTramite = "<br><b>Trámite enviado al Coordinador.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
            
            List<UsUsuariosVO> coordinadores = fachada.getUsuarios(2);
            for(UsUsuariosVO u: coordinadores){    
                enviarCorreo(u.getCodigoSIUST().getEmail(), userVO.getCodigoSIUST().getLogin());
            }
            
        } else {
            mensajeTramite = "<br><b>Error al enviar el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    
    public void enviarCorreo(String usuarioTo, String usuarioRemitente){
        Correo correo = new Correo();
        correo.setServidor(configuracion.getServidorCorreo());
        correo.setPuerto(configuracion.getPuertoServidor());
        correo.setCorreoFrom(configuracion.getCorreoAplicacion());
        correo.setPassword(configuracion.getPasswordCorreo());
        correo.setAsunto("Mapa de Recursos de Identificación");
        String mensaje = "El usuario "+userVO.getCodigoSIUST().getLogin()+" te ha enviado un trámite.";
        correo.setMensaje(mensaje);
        correo.setCorreoTo(usuarioTo);
        correo.setFirma(configuracion.getFirmaCorreo());
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        correo.setRutaLogo(deploymentDirectoryPath+configuracion.getRutaContexto()+configuracion.getLogoCorreo());
        correo.send();
    }
    
    public String devolverTramite() {
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeTramite = "";
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setTrnCodigo(selectedTramite.getTrnCodigo());
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
   
        boolean resultado = fachada.devolverTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeTramite = "<br><b>Trámite devuelto al Asesor.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
            
            String email = fachada.cargarTramites(0, -1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0).getUsnCodigo().getCodigoSIUST().getEmail();
            enviarCorreo(email, userVO.getCodigoSIUST().getLogin());
            
        } else {
            mensajeTramite = "<br><b>Error al devolver el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    
    public String aprobarTramite() {
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeTramite = "";
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setTrnCodigo(selectedTramite.getTrnCodigo());
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
   
        boolean resultado = fachada.aprobarTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeTramite = "<br><b>Trámite aprobado.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
            
            List<UsUsuariosVO> administradores = fachada.getUsuarios(1);
            for(UsUsuariosVO u: administradores){    
                enviarCorreo(u.getCodigoSIUST().getEmail(), userVO.getCodigoSIUST().getLogin());
            }
            
        } else {
            mensajeTramite = "<br><b>Error al devolver el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    
    public String terminarTramite(){
        
        //inicio validaciones
        //valida si lo ingresado en el campo de resolución es un número
        boolean validaRes;
        boolean cerrarDialog = false;
        
        validaRes=validaNum(resolucionTerminarTramite);
        if(!validaRes) {
            mensajeTramite = "<br><b>Error al terminar el trámite.</b><br><br>Debes ingresar un número en el campo de resolución<br><br>";
            return null;
        }
        
                
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeTramite = "";
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setTrnCodigo(selectedTramite.getTrnCodigo());
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
        vo.setTrtObservaciones(observacionesTramite);
        vo.setTrnResolucion(resolucionTerminarTramite);
        vo.setTrfFechaResolucion(fechaResolucionTerminarTramite);
   
        boolean resultado = fachada.terminarTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
            mensajeTramite = "<br><b>Trámite terminado.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
        } else {
            mensajeTramite = "<br><b>Error al terminar el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        resolucionTerminarTramite = "";
        fechaResolucionTerminarTramite = null;
        observacionesTramite = "";
        
        return null;
    }
    
    public String cambiarUsuarioTramite() {
        if(nuevoUsuarioTramite == -1){
            mensajeCambiarUsuarioTramite = "<br><b>Error al cambiar el usuario del trámite.</b><br><br>Debes escoger un usuario<br><br>";
            return null;
        }
        
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeCambiarUsuarioTramite = "";
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setTrnCodigo(selectedTramite.getTrnCodigo());
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
        vo.setTrtObservaciones("Usuario anterior: " + selectedTramite.getUsnCodigo().getCodigoSIUST().getLogin()
                + ". Nuevo usuario: " + fachada.buscarUsuario(nuevoUsuarioTramite).getCodigoSIUST().getLogin()+".");
   
        Integer resultado = fachada.cambiarUsuarioTramite(vo, nuevoUsuarioTramite);
        
        switch(resultado){
            case 0:
                mensajeCambiarUsuarioTramite = "<br><b>Error al cambiar el usuario del trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                selectedTramite = fachada.cargarTramites(0, -1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0);
                tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
                mensajeCambiarUsuarioTramite  = "<br><b>Usuario cambiado correctamente al trámite.</b><br><br>";
                break;
            case 2:
                mensajeCambiarUsuarioTramite = "<br><b>El usuario a asignar no puede ser igual al que tiene actualmente el trámite.</b><br><br>";
                break;
        }
        
        return null;
    }
    
    // --- Funiones para buscar recursos ---
    public String opcionesBuscarTramite() {
        facade fachada = new facade();
        tramiteIdBuscarTramite = "";
        usuarioBuscarTramite = -1;
        operadorBuscarTramite = "-1";
        estadoBuscarTramite = -1;
        seleccionBuscarTramite = null;
        listaBuscarTramite = null;
        radicadoBuscarTramite = "";
        //String a = buscarTramite();
        return configuracion.getRutaContexto()+"usuarios/buscarTramite";
    }
    
    public String buscarTramite() {
        List<SeSenalizacionVO> senalizacion = new ArrayList<SeSenalizacionVO>();
        facade fachada = new facade();

        final Integer id;
        final Integer rad;
        
        if (tramiteIdBuscarTramite.equals("")) {
            id = -1;
        } else {
            id = Integer.parseInt(tramiteIdBuscarTramite);
        }
        
        if (radicadoBuscarTramite.equals("")) {
            rad = -1;
        } else {
            rad = Integer.parseInt(radicadoBuscarTramite);
        }

        listaBuscarTramite = fachada.cargarTramites(0, -1, id, usuarioBuscarTramite, operadorBuscarTramite, estadoBuscarTramite, rad);
        //countListaBuscarTramite = fachada.countCargarCodigosLd(operadorVO.getEmrCodigo(), ld, estadoVO.getEsnCodigo());

        return null;
    }
    // -------------------------------------
    
    public String agregarRecurso() {
        boolean cerrarDialog = false;
        if ((codigoAccion <= 0)||(tipoRecurso.equals(""))){
            mensajeRecurso = "<br><b>Error al agregar recurso al trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        if(radicadoAgregarRecurso.equals("")){
            mensajeRecurso = "<br><b>Error al agregar el recurso al trámite.</b><br><br>Debes ingresar un número de radicado<br><br>";
            return null;
        }
        
        if(!validaNum(radicadoAgregarRecurso)){
            mensajeRecurso = "<br><b>Error al agregar el recurso al trámite.</b><br><br>El radicado debe ser un número entero.<br><br>";
            return null;
        }
        
        if(tramiteAgregarRecurso <= 0){
            mensajeRecurso = "<br><b>Error al agregar el recurso al trámite.</b><br><br>Debes escoger un trámite<br><br>";
            return null;
        }
        
        mensajeRecurso = "";
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        /*HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        String tipoRecurso = facesContext.getExternalContext().getRequestParameterValuesMap().get("tipoRecurso")[0].toString();
        Integer codigoAccion = Integer.parseInt(facesContext.getExternalContext().getRequestParameterValuesMap().get("codigoAccion")[0]);*/
        
        TrTramitesVO tramite = new TrTramitesVO();
        tramite.setTrnCodigo(tramiteAgregarRecurso);
        
        AcAccionVO accion = new AcAccionVO();
        accion.setAcnCodigo(codigoAccion);
        
        facade fachada = new facade();
        
        Integer resultado = 0;
        
        if(tipoRecurso.equals("senalizacion")){
            TsTramiteSenalizacionVO vo = new TsTramiteSenalizacionVO();
            SenalizacionBean sen = (SenalizacionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{SenalizacionBean}", SenalizacionBean.class);//session.getAttribute("SenalizacionBean");
            
            SeSenalizacionVO senalizacion = new SeSenalizacionVO();
            senalizacion.setSenCodigo(sen.getSelectedSen().getSenCodigo());

            MunicipiosVO municipio = new MunicipiosVO();
            EmOperadorVO operador = new EmOperadorVO();
            String nombreNodo = "";
            String marcaModelo = "";
            String direccion = "";
            String observaciones = tramiteSenalizacionVO.getTstObservaciones();//this.observacionesAgregarRecurso;
            Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);

            switch(codigoAccion){
                case 2: //Preasignar
                    if(tramiteSenalizacionVO.getTstNombreNodo().equals("")){
                        mensajeRecurso = "<br><b>Error al agregar el recurso al trámite.</b><br><br>Debes ingresar el nombre del nodo<br><br>";
                        return null;
                    }
                    if(tramiteSenalizacionVO.getTstMarcaModelo().equals("")){
                        mensajeRecurso = "<br><b>Error al agregar el recurso al trámite.</b><br><br>Debes ingresar la marca/modelo del nodo<br><br>";
                        return null;
                    }
                    if(tramiteSenalizacionVO.getTstDireccion().equals("")){
                        mensajeRecurso = "<br><b>Error al agregar el recurso al trámite.</b><br><br>Debes ingresar la dirección de ubicación del nodo<br><br>";
                        return null;
                    }
                    municipio.setCodigoMunicipio(tramiteSenalizacionVO.getCodigoMunicipio().getCodigoMunicipio());
                    for (TrTramitesVO detalleVO : tramites) {
                        if (detalleVO.getTrnCodigo() == tramiteAgregarRecurso){
                            operador.setEmrCodigo(detalleVO.getEmrCodigo().getEmrCodigo());
                            break;
                        }
                    }
                    nombreNodo = tramiteSenalizacionVO.getTstNombreNodo();
                    marcaModelo = tramiteSenalizacionVO.getTstMarcaModelo();
                    direccion = tramiteSenalizacionVO.getTstDireccion();
                    break;
                case 5: //Recuperar
                    municipio.setCodigoMunicipio(sen.getSelectedSen().getCodigoMunicipio().getCodigoMunicipio());
                    operador.setEmrCodigo(configuracion.getOperadorNinguno());
                    nombreNodo = "";
                    marcaModelo = "";
                    direccion = "";
                    
                    break;
            }

            vo.setTrnCodigo(tramite);
            vo.setSenCodigo(senalizacion);
            vo.setAcnCodigo(accion);
            vo.setTsnRadicado(radicado);
            vo.setCodigoMunicipio(municipio);
            vo.setEmrCodigo(operador);
            vo.setTstNombreNodo(nombreNodo);
            vo.setTstMarcaModelo(marcaModelo);
            vo.setTstDireccion(direccion);
            vo.setTstObservaciones(observaciones);
            
            resultado = fachada.agregarRecurso(vo);
            tramiteSenalizacionVO = new TsTramiteSenalizacionVO();
        } else if (tipoRecurso.equals("codigosld")) {
            TlTramiteLdVO vo = new TlTramiteLdVO();
            CodigosLdBean recursoBean = (CodigosLdBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosLdBean}", CodigosLdBean.class);//session.getAttribute("SenalizacionBean");
            
            ClCodigosLdVO recurso = new ClCodigosLdVO();
            recurso.setClnCodigo(recursoBean.getSelectedLd().getClnCodigo());

            EmOperadorVO operador = new EmOperadorVO();
            String observaciones = tramiteCodigosLdVO.getTltObservaciones();//this.observacionesAgregarRecurso;
            Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
            
            switch(codigoAccion){
                case 2: //Preasignar
                    for (TrTramitesVO detalleVO : tramites) {
                        if (detalleVO.getTrnCodigo() == tramiteAgregarRecurso){
                            operador.setEmrCodigo(detalleVO.getEmrCodigo().getEmrCodigo());
                            break;
                        }
                    }
                    break;
                case 5: //Recuperar
                    operador.setEmrCodigo(configuracion.getOperadorNinguno());
                    break;
            }
            vo.setTrnCodigo(tramite);
            vo.setClnCodigo(recurso);
            vo.setAcnCodigo(accion);
            vo.setTlnRadicado(radicado);
            vo.setEmrCodigo(operador);
            vo.setTltObservaciones(observaciones);
            
            resultado = fachada.agregarRecurso(vo);
            tramiteCodigosLdVO = new TlTramiteLdVO();
        } else if (tipoRecurso.equals("codigosCortos")) {
            TcTramiteCcVO vo = new TcTramiteCcVO();
            CodigosCortosBean recursoBean = (CodigosCortosBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosCortosBean}", CodigosCortosBean.class);//session.getAttribute("SenalizacionBean");
            
            CcCodigosCortosVO recurso = new CcCodigosCortosVO();
            recurso.setCcnCodigo(recursoBean.getSelectedCodigoCorto().getCcnCodigo());

            EmOperadorVO operador = new EmOperadorVO();
            String observaciones = tramiteCodigosCortosVO.getTctObservaciones();//this.observacionesAgregarRecurso;
            Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
            
            switch(codigoAccion){
                case 2: //Preasignar
                    for (TrTramitesVO detalleVO : tramites) {
                        if (detalleVO.getTrnCodigo() == tramiteAgregarRecurso){
                            operador.setEmrCodigo(detalleVO.getEmrCodigo().getEmrCodigo());
                            break;
                        }
                    }
                    break;
                case 5: //Recuperar
                    operador.setEmrCodigo(configuracion.getOperadorNinguno());
                    break;
            }
            vo.setTrnCodigo(tramite);
            vo.setCcnCodigo(recurso);
            vo.setAcnCodigo(accion);
            vo.setTcnRadicado(radicado);
            vo.setEmrCodigo(operador);
            vo.setTctObservaciones(observaciones);
            
            resultado = fachada.agregarRecurso(vo);
            tramiteCodigosCortosVO = new TcTramiteCcVO();
        }
        
        switch(resultado){
            case 0:
                mensajeRecurso = "<br><b>Error al agregar recurso al trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
                mensajeRecurso = "<br><b>Recurso agregado correctamente al trámite.</b><br><br>Código del trámite: "+tramiteAgregarRecurso+"<br><br>";
                break;
            case 2:
                mensajeRecurso = "<br><b>Falta diligenciar un dato.</b><br><br>";
                break;
            case 3:
                mensajeRecurso = "<br><b>El operador del recurso debe ser el mismo del trámite.</b><br><br>";
                break;
            case 4:
                mensajeRecurso = "<br><b>Error al agregar recurso al trámite.</b><br><br>El recurso ya tiene un trámite asociado.<br><br>";
                break;
            case 5:
                mensajeRecurso = "<br><b>Error al agregar recurso al trámite.</b><br><br>El recurso debe tener estado \"ASIGNADO\".<br><br>";
                break;
            case 6:
                mensajeRecurso = "<br><b>Error al agregar recurso al trámite.</b><br><br>El recurso debe tener estado \"LIBRE\".<br><br>";
                break;
        }
        
        cerrarDialog = true;
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("cerrarDialog", cerrarDialog);
        //this.setTramiteAgregarRecurso(-1);
        //this.radicadoAgregarRecurso = "";
        //this.observacionesAgregarRecurso = "";

        return null;
    }
     
    // ---- Función para selección detalle del trámite antes de eliminarlo ---
    public String seleccionarCodigoDetalleTramite() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        Integer codigo = Integer.parseInt(facesContext.getExternalContext().getRequestParameterValuesMap().get("codigoDetalleTramite")[0]);
        
        codigoDetalleTramite = codigo;
        
        return null;
    }
    // -----------------------------------------------------------------------
    
    public String eliminarRecurso() {
        if ((codigoDetalleTramite <= 0)||(tipoRecurso.equals(""))){
            mensajeRecurso = "<br><b>Error al eliminar recurso del trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        facade fachada = new facade();
        
        /*FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        String tipoRecurso = facesContext.getExternalContext().getRequestParameterValuesMap().get("tipoRecurso")[0].toString();*/
        
        mensajeRecurso = "";
        
        boolean resultado = false;
        
        if(tipoRecurso.equals("senalizacion")){
            TsTramiteSenalizacionVO vo = new TsTramiteSenalizacionVO();
            vo.setTsnCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        } else if(tipoRecurso.equals("codigosld")) {
            TlTramiteLdVO vo = new TlTramiteLdVO();
            vo.setTlnCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        } else if(tipoRecurso.equals("codigosCortos")) {
            TcTramiteCcVO vo = new TcTramiteCcVO();
            vo.setTcnCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        }
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            for (TrTramitesVO detalleVO : tramites) {
                if (detalleVO.getTrnCodigo() == selectedTramite.getTrnCodigo()){
                    selectedTramite = detalleVO;
                    break;
                }
            }
            mensajeRecurso = "<br><b>Recurso eliminado correctamente del trámite.</b><br><br>";
        } else {
            mensajeRecurso = "<br><b>Error al eliminar recurso del trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    
    public Collection<SelectItem> cargarListaTramites() {
        ArrayList selectItemsList = new ArrayList();
        SelectItem selectItem = new SelectItem();
        selectItem.setValue("-1");
        selectItem.setLabel("Escoger un trámite");
        selectItemsList.add(selectItem);
        for (Integer i = this.tramites.size()-1 ; i >= 0 ; i--){
            selectItem = new SelectItem();
            selectItem.setValue(this.tramites.get(i).getTrnCodigo());
            selectItem.setLabel(this.tramites.get(i).getTrnCodigo()+"-"+this.tramites.get(i).getEmrCodigo().getEmtNombre());
            selectItemsList.add(selectItem);
        }
        return selectItemsList;
    }
  
    public void opcionesPreasignar() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        /*HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        String tipoRecurso = facesContext.getExternalContext().getRequestParameterValuesMap().get("tipoRecurso")[0].toString();*/
        
        if(tipoRecurso.equals("senalizacion")){
            SenalizacionBean sen = (SenalizacionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{SenalizacionBean}", SenalizacionBean.class);//session.getAttribute("SenalizacionBean");
            seleccionDepartamento = sen.getSelectedSen().getCodigoMunicipio().getCodigoDepartamento().getCodigoDepartamento();
            cambiarDepartamento();
            MunicipiosVO municipio = new MunicipiosVO();
            municipio.setCodigoMunicipio(sen.getSelectedSen().getCodigoMunicipio().getCodigoMunicipio());
            tramiteSenalizacionVO.setCodigoMunicipio(municipio);
        }

        
    }
    
    // --- Función para escoger municipio cuando se está agregando un recurso a un trámite ---
    public void cambiarDepartamento() {
        facade fachada = new facade();
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        //String codigoDepartamento = facesContext.getExternalContext().getRequestParameterValuesMap().get("departamento")[0].toString();
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaMunicipio = convertir.createSelectItemsList(fachada.listaMunicipios(seleccionDepartamento), "getCodigoMunicipio", null, "getNombreMunicipio", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Trámites", e);
        }
           
    }
    // ---------------------------------------------------------------------------------------
    
    // --- Funiones para tranferir recursos ---
    public String opcionesTransferirRecursos() {
        facade fachada = new facade();
       
        // limpia las opciones de las variables en la transferencia de recursos
        operadorOrigen = "-1";
        operadorDestino = "-1";
        seleccionNumeracion=false;
        seleccionSenalizacion=false;
        seleccionIin=false;
        seleccionMnc=false;
        opcionNumeracion=false;
        opcionSenalizacion=false;
        opcionIin=false;
        opcionMnc=false;
        
        mensajeTransferirRecursos = "";
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.cargarOperadores(), "getEmrCodigo", null, "getEmtNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Transferencia de recursos", e);
        }
        return configuracion.getRutaContexto()+"usuarios/transferenciaRecursos";
    }

    public String transferirRecursos() {
        boolean transferencia;
        if(operadorOrigen.equals("-1") || operadorDestino.equals("-1")) {
            mensajeTransferirRecursos = "<br><b>Error al transferir recursos.</b><br><br>Debes seleccionar operador origen y operador destino<br><br>";
            return null;
        }

        if(operadorOrigen.equals(operadorDestino)){
            mensajeTransferirRecursos = "<br><b>Error al transferir recursos.</b><br><br>El operador origen y el operador destino deben ser diferentes<br><br>";
            return null;
        }

        if((!seleccionNumeracion) && (!seleccionSenalizacion) && (!seleccionIin) && (!seleccionMnc)){
            mensajeTransferirRecursos = "<br><b>Error al transferir recursos.</b><br><br>Debes seleccionar por lo menos un recurso a transferir<br><br>";
            return null;
        }
            
        facade fachada = new facade();
                
        mensajeTransferirRecursos = "";
        
        transferencia = fachada.transferirRecursos(operadorOrigen, operadorDestino, seleccionNumeracion, seleccionSenalizacion, seleccionIin, seleccionMnc);
       
        if (transferencia){
            mensajeTransferirRecursos = "<br><b>Recursos transferidos exitosamente.</b><br>";
            if(seleccionNumeracion){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("NumeracionBean");
            }
            if(seleccionSenalizacion){
                //FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("SenalizacionBean");
            }
            /*if(seleccionIin){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("IinBean");
            }
            if(seleccionMnc){
                FacesContext.getCurrentInstance().getExternalContext().getSessionMap().remove("MncBean");
            }*/
        }else{
            mensajeTransferirRecursos = "<br><b>Error al transferir recrusos.</b><br>";
        } 
         return mensajeTransferirRecursos;
    }
    
    public void cambioOperadorOrigen(){
        int countRecurso=0;
        opcionNumeracion=false;
        opcionSenalizacion=false;
        opcionIin=false;
        opcionMnc=false;
        
        facade fachada = new facade();
        
        // Activa el campo de numeración en transferencia de recursos        
        countRecurso = fachada.countCargarNumeracion(operadorOrigen, -1, -1, -1, -1, "-1", "-1");
        if(countRecurso > 0){
            opcionNumeracion = true;
        }
        // Activa el campo de señalizacion en transferencia de recursos
        countRecurso = fachada.countCargarSenalizacion(operadorOrigen, -1, -1, -1, -1, "-1", "-1");
        if(countRecurso > 0){
            opcionSenalizacion = true;
        }
            
    }
    // ----------------------------------------
    
    public static boolean validaNum(String Valor) {
        try {
            Integer.parseInt(Valor);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
     
    public boolean isTramiteTieneDetalle() {
        boolean haySenalizacion = !selectedTramite.getTsTramiteSenalizacionCollection().isEmpty();
        boolean hayCodigosLd = !selectedTramite.getTlTramiteLdCollection().isEmpty();
        boolean hayCodigosCortos = !selectedTramite.getTcTramiteCcCollection().isEmpty();
        if (haySenalizacion || hayCodigosLd || hayCodigosCortos){
            return true;
        }
        return false;
    }
    
    // Funciones GET y SET
    public void setTramiteTieneDetalle(boolean tramiteTieneDetalle) {
        this.tramiteTieneDetalle = tramiteTieneDetalle;
    }

    public List<TrTramitesVO> getTramites() {
        return tramites;
    }

    public void setTramites(List<TrTramitesVO> tramites) {
        this.tramites = tramites;
    }

    public TrTramitesVO getSelectedTramite() {
        return selectedTramite;
    }

    public void setSelectedTramite(TrTramitesVO selectedTramite) {
        this.selectedTramite = selectedTramite;
    }

    public Collection<SelectItem> getListaOperador() {
        return listaOperador;
    }

    public void setListaOperador(Collection<SelectItem> listaOperador) {
        this.listaOperador = listaOperador;
    }

    public String getOperadorCrearTramite() {
        return operadorCrearTramite;
    }

    public void setOperadorCrearTramite(String operadorCrearTramite) {
        this.operadorCrearTramite = operadorCrearTramite;
    }

    public UsUsuariosVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UsUsuariosVO userVO) {
        this.userVO = userVO;
    }

    public String getMensajeCrearTramite() {
        return mensajeCrearTramite;
    }

    public void setMensajeCrearTramite(String mensajeCrearTramite) {
        this.mensajeCrearTramite = mensajeCrearTramite;
    }

    public String getMensajeTramite() {
        return mensajeTramite;
    }

    public void setMensajeTramite(String mensajeTramite) {
        this.mensajeTramite = mensajeTramite;
    }

    public Collection<SelectItem> getListaTramites() {
        return cargarListaTramites();
    }

    public void setListaTramites(Collection<SelectItem> listaTramites) {
        this.listaTramites = listaTramites;
    }

    public Integer getTramiteAgregarRecurso() {
        return tramiteAgregarRecurso;
    }

    public void setTramiteAgregarRecurso(Integer tramiteAgregarRecurso) {
        this.tramiteAgregarRecurso = tramiteAgregarRecurso;
    }

    public String getMensajeRecurso() {
        return mensajeRecurso;
    }

    public void setMensajeRecurso(String mensajeRecurso) {
        this.mensajeRecurso = mensajeRecurso;
    }

    public Integer getCodigoDetalleTramite() {
        return codigoDetalleTramite;
    }

    public void setCodigoDetalleTramite(Integer codigoDetalleTramite) {
        this.codigoDetalleTramite = codigoDetalleTramite;
    }

    public String getObservacionesAgregarRecurso() {
        return observacionesAgregarRecurso;
    }

    public void setObservacionesAgregarRecurso(String observacionesAgregarRecurso) {
        this.observacionesAgregarRecurso = observacionesAgregarRecurso;
    }

    public String getRadicadoAgregarRecurso() {
        return radicadoAgregarRecurso;
    }

    public void setRadicadoAgregarRecurso(String radicadoAgregarRecurso) {
        this.radicadoAgregarRecurso = radicadoAgregarRecurso;
    }

    public String getOperadorDestino() {
        return operadorDestino;
    }

    public void setOperadorDestino(String operadorDestino) {
        this.operadorDestino = operadorDestino;
    }

    public String getOperadorOrigen() {
        return operadorOrigen;
    }

    public void setOperadorOrigen(String operadorOrigen) {
        this.operadorOrigen = operadorOrigen;
    }

    public String getMensajeTransferirRecursos() {
        return mensajeTransferirRecursos;
    }

    public void setMensajeTransferirRecursos(String mensajeTransferirRecursos) {
        this.mensajeTransferirRecursos = mensajeTransferirRecursos;
    }

    public Boolean getSeleccionIin() {
        return seleccionIin;
    }

    public void setSeleccionIin(Boolean seleccionIin) {
        this.seleccionIin = seleccionIin;
    }

    public Boolean getSeleccionMnc() {
        return seleccionMnc;
    }

    public void setSeleccionMnc(Boolean seleccionMnc) {
        this.seleccionMnc = seleccionMnc;
    }

    public Boolean getSeleccionNumeracion() {
        return seleccionNumeracion;
    }

    public void setSeleccionNumeracion(Boolean seleccionNumeracion) {
        this.seleccionNumeracion = seleccionNumeracion;
    }

    public Boolean getSeleccionSenalizacion() {
        return seleccionSenalizacion;
    }

    public void setSeleccionSenalizacion(Boolean seleccionSenalizacion) {
        this.seleccionSenalizacion = seleccionSenalizacion;
    }

    public Boolean getOpcionIin() {
        return opcionIin;
    }

    public void setOpcionIin(Boolean opcionIin) {
        this.opcionIin = opcionIin;
    }

    public Boolean getOpcionMnc() {
        return opcionMnc;
    }

    public void setOpcionMnc(Boolean opcionMnc) {
        this.opcionMnc = opcionMnc;
    }

    public Boolean getOpcionNumeracion() {
        return opcionNumeracion;
    }

    public void setOpcionNumeracion(Boolean opcionNumeracion) {
        this.opcionNumeracion = opcionNumeracion;
    }

    public Boolean getOpcionSenalizacion() {
        return opcionSenalizacion;
    }

    public void setOpcionSenalizacion(Boolean opcionSenalizacion) {
        this.opcionSenalizacion = opcionSenalizacion;
    }

    public Collection<SelectItem> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(Collection<SelectItem> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public Collection<SelectItem> getListaMunicipio() {
        return listaMunicipio;
    }

    public void setListaMunicipio(Collection<SelectItem> listaMunicipio) {
        this.listaMunicipio = listaMunicipio;
    }

    public String getSeleccionDepartamento() {
        return seleccionDepartamento;
    }

    public void setSeleccionDepartamento(String seleccionDepartamento) {
        this.seleccionDepartamento = seleccionDepartamento;
    }

    public String getSeleccionMunicipio() {
        return seleccionMunicipio;
    }

    public void setSeleccionMunicipio(String seleccionMunicipio) {
        this.seleccionMunicipio = seleccionMunicipio;
    }

    public TsTramiteSenalizacionVO getTramiteSenalizacionVO() {
        return tramiteSenalizacionVO;
    }

    public void setTramiteSenalizacionVO(TsTramiteSenalizacionVO tramiteSenalizacionVO) {
        this.tramiteSenalizacionVO = tramiteSenalizacionVO;
    }
    
    public UserBean getUserSession() {
        return userSession;
    }

    public void setUserSession(UserBean userSession) {
        this.userSession = userSession;
    }

    public ConfiguracionBean getConfiguracion() {
        return configuracion;
    }

    public void setConfiguracion(ConfiguracionBean configuracion) {
        this.configuracion = configuracion;
    }

    public TlTramiteLdVO getTramiteCodigosLdVO() {
        return tramiteCodigosLdVO;
    }

    public void setTramiteCodigosLdVO(TlTramiteLdVO tramiteCodigosLdVO) {
        this.tramiteCodigosLdVO = tramiteCodigosLdVO;
    }

    public Collection<SelectItem> getListaEstadoTramite() {
        return listaEstadoTramite;
    }

    public void setListaEstadoTramite(Collection<SelectItem> listaEstadoTramite) {
        this.listaEstadoTramite = listaEstadoTramite;
    }

    public int getEstadoBuscarTramite() {
        return estadoBuscarTramite;
    }

    public void setEstadoBuscarTramite(int estadoBuscarTramite) {
        this.estadoBuscarTramite = estadoBuscarTramite;
    }

    public String getOperadorBuscarTramite() {
        return operadorBuscarTramite;
    }

    public void setOperadorBuscarTramite(String operadorBuscarTramite) {
        this.operadorBuscarTramite = operadorBuscarTramite;
    }

    public String getTramiteIdBuscarTramite() {
        return tramiteIdBuscarTramite;
    }

    public void setTramiteIdBuscarTramite(String tramiteIdBuscarTramite) {
        this.tramiteIdBuscarTramite = tramiteIdBuscarTramite;
    }

    public int getUsuarioBuscarTramite() {
        return usuarioBuscarTramite;
    }

    public void setUsuarioBuscarTramite(int usuarioBuscarTramite) {
        this.usuarioBuscarTramite = usuarioBuscarTramite;
    }

    public int getCountListaBuscarTramite() {
        return countListaBuscarTramite;
    }

    public void setCountListaBuscarTramite(int countListaBuscarTramite) {
        this.countListaBuscarTramite = countListaBuscarTramite;
    }

    public List<TrTramitesVO> getListaBuscarTramite() {
        return listaBuscarTramite;
    }

    public void setListaBuscarTramite(List<TrTramitesVO> listaBuscarTramite) {
        this.listaBuscarTramite = listaBuscarTramite;
    }

    public TrTramitesVO getSeleccionBuscarTramite() {
        return seleccionBuscarTramite;
    }

    public void setSeleccionBuscarTramite(TrTramitesVO seleccionBuscarTramite) {
        this.seleccionBuscarTramite = seleccionBuscarTramite;
    }

    public Collection<SelectItem> getListaUsuariosAplicacion() {
        return listaUsuariosAplicacion;
    }

    public void setListaUsuariosAplicacion(Collection<SelectItem> listaUsuariosAplicacion) {
        this.listaUsuariosAplicacion = listaUsuariosAplicacion;
    }

    public List<UsUsuariosVO> getUsuariosAplicacion() {
        return usuariosAplicacion;
    }

    public void setUsuariosAplicacion(List<UsUsuariosVO> usuariosAplicacion) {
        this.usuariosAplicacion = usuariosAplicacion;
    }

    public String getTipoRecurso() {
        return tipoRecurso;
    }

    public void setTipoRecurso(String tipoRecurso) {
        this.tipoRecurso = tipoRecurso;
    }

    public int getCodigoAccion() {
        return codigoAccion;
    }

    public void setCodigoAccion(int codigoAccion) {
        this.codigoAccion = codigoAccion;
    }

    public String getRadicadoBuscarTramite() {
        return radicadoBuscarTramite;
    }

    public void setRadicadoBuscarTramite(String radicadoBuscarTramite) {
        this.radicadoBuscarTramite = radicadoBuscarTramite;
    }

    public Collection<SelectItem> getListaUsuariosNoAplicacion() {
        return listaUsuariosNoAplicacion;
    }

    public void setListaUsuariosNoAplicacion(Collection<SelectItem> listaUsuariosNoAplicacion) {
        this.listaUsuariosNoAplicacion = listaUsuariosNoAplicacion;
    }

    public Collection<SelectItem> getListaUsuariosSIUST() {
        return listaUsuariosSIUST;
    }

    public void setListaUsuariosSIUST(Collection<SelectItem> listaUsuariosSIUST) {
        this.listaUsuariosSIUST = listaUsuariosSIUST;
    }

    public int getUsuariosBuscar() {
        return usuariosBuscar;
    }

    public void setUsuariosBuscar(int usuariosBuscar) {
        this.usuariosBuscar = usuariosBuscar;
    }

    public UsUsuariosVO getUsuariosEncontrado() {
        return usuariosEncontrado;
    }

    public void setUsuariosEncontrado(UsUsuariosVO usuariosEncontrado) {
        this.usuariosEncontrado = usuariosEncontrado;
    }

    public String getMensajeAdminUsuario() {
        return mensajeAdminUsuario;
    }

    public void setMensajeAdminUsuario(String mensajeAdminUsuario) {
        this.mensajeAdminUsuario = mensajeAdminUsuario;
    }

    public Integer getNuevoUsuarioTramite() {
        return nuevoUsuarioTramite;
    }

    public void setNuevoUsuarioTramite(Integer nuevoUsuarioTramite) {
        this.nuevoUsuarioTramite = nuevoUsuarioTramite;
    }

    public String getMensajeCambiarUsuarioTramite() {
        return mensajeCambiarUsuarioTramite;
    }

    public void setMensajeCambiarUsuarioTramite(String mensajeCambiarUsuarioTramite) {
        this.mensajeCambiarUsuarioTramite = mensajeCambiarUsuarioTramite;
    }

    public Date getFechaResolucionTerminarTramite() {
        return fechaResolucionTerminarTramite;
    }

    public void setFechaResolucionTerminarTramite(Date fechaResolucionTerminarTramite) {
        this.fechaResolucionTerminarTramite = fechaResolucionTerminarTramite;
    }

    public String getObservacionesTramite() {
        return observacionesTramite;
    }

    public void setObservacionesTramite(String observacionesTramite) {
        this.observacionesTramite = observacionesTramite;
    }

    public String getResolucionTerminarTramite() {
        return resolucionTerminarTramite;
    }

    public void setResolucionTerminarTramite(String resolucionTerminarTramite) {
        this.resolucionTerminarTramite = resolucionTerminarTramite;
    }

    public Collection<SelectItem> getListaAsesores() {
        return listaAsesores;
    }

    public void setListaAsesores(Collection<SelectItem> listaAsesores) {
        this.listaAsesores = listaAsesores;
    }

    public List<UsUsuariosVO> getUsuariosAsesores() {
        return usuariosAsesores;
    }

    public void setUsuariosAsesores(List<UsUsuariosVO> usuariosAsesores) {
        this.usuariosAsesores = usuariosAsesores;
    }

    public TcTramiteCcVO getTramiteCodigosCortosVO() {
        return tramiteCodigosCortosVO;
    }

    public void setTramiteCodigosCortosVO(TcTramiteCcVO tramiteCodigosCortosVO) {
        this.tramiteCodigosCortosVO = tramiteCodigosCortosVO;
    }
    
}
