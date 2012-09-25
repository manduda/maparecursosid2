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
import org.primefaces.event.UnselectEvent;
import utils.Correo;
import utils.Functions;
import vo.AcAccionVO;
import vo.CcCodigosCortosVO;
import vo.CdCodigosMncVO;
import vo.CiCodigosIinVO;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.MaMarcacionAbreviadaVO;
import vo.MunicipiosVO;
import vo.NrCodigosNrnVO;
import vo.NuNumeracionVO;
import vo.PtTipoPermisoVO;
import vo.RsReservasTemporalesVO;
import vo.SeSenalizacionVO;
import vo.TaTramiteMaVO;
import vo.TcTramiteCcVO;
import vo.TiTramiteIinVO;
import vo.TkTramiteNrnVO;
import vo.TlTramiteLdVO;
import vo.TmTramiteMncVO;
import vo.TnTramiteNumeracionVO;
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
    private ArrayList reservas = new ArrayList();
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
    private TrTramitesVO[] selectedUnirTramites;
    private Boolean selectedUnir;
    
    private Collection<SelectItem> listaUsuariosAplicacion;
    private List<UsUsuariosVO> usuariosAplicacion = new ArrayList<UsUsuariosVO>();

    private Collection<SelectItem> listaAsesores;
    private List<UsUsuariosVO> usuariosAsesores = new ArrayList<UsUsuariosVO>();
    
    //private Collection<SelectItem> listaUsuariosNoAplicacion;
    
    private Collection<SelectItem> listaUsuariosSIUST;
    private int usuariosBuscar = 0;
    private UsUsuariosVO usuariosEncontrado = new UsUsuariosVO();
    private String mensajeAdminUsuario = "";
    private UsUsuariosVO asesorEditar = new UsUsuariosVO();
    private List<PtTipoPermisoVO> permisosAsesor = new ArrayList<PtTipoPermisoVO>();
    private int tipoPermiso = 0;
    //3002026010
    
    private String seleccionDepartamento;
    private String seleccionMunicipio;
    private Integer tramiteAgregarRecurso=-1;
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
    private String nuevoOperadorTramite = "-1";
    private String mensajeCambiarOperadorTramite = "";
    
    
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
    private TnTramiteNumeracionVO tramiteNumeracionVO = new TnTramiteNumeracionVO();
    private TlTramiteLdVO tramiteCodigosLdVO = new TlTramiteLdVO();
    private TcTramiteCcVO tramiteCodigosCortosVO = new TcTramiteCcVO();
    private TaTramiteMaVO tramiteMarcacionAbreviadaVO = new TaTramiteMaVO();
    private TmTramiteMncVO tramiteMncVO = new TmTramiteMncVO();
    private TkTramiteNrnVO tramiteNrnVO = new TkTramiteNrnVO();
    private TiTramiteIinVO tramiteIinVO = new TiTramiteIinVO();
    private Boolean reservaTemporal = false;
    private int mesesReserva = 0;
    
    String rutaImagenError;
    String rutaImagenOk;

    
    

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
                case 4:
                    tipoUsuario = 6;
                    break;
            }
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
        }
        
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        rutaImagenError = ctx.getContextPath() + configuracion.getRutaContexto() + "resources/images/error.png";
        rutaImagenOk = ctx.getContextPath() + configuracion.getRutaContexto() + "resources/images/ok.png";

        
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
            
            //listaUsuariosNoAplicacion = convertir.createSelectItemsList(fachada.listaUsuariosNoAplicacion(), null, "getUserCode", "getLogin", true, "");
            
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

                    //listaUsuariosNoAplicacion = convertir.createSelectItemsList(fachada.listaUsuariosNoAplicacion(), null, "getUserCode", "getLogin", true, "");

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
    
    public String mostrarPermisosAsesor() {
        if (asesorEditar.getCodigoSIUST().getUserCode() == 0){
            mensajeAdminUsuario = "<br><b>Error al cambiar el perfil al usuario.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        
        if (asesorEditar.getTunCodigo().getTunCodigo() != 3){
            mensajeAdminUsuario = "<br><b>El usuario no es Asesor<br><br>";
            return null;
        }
        
        facade fachada = new facade();
        
        permisosAsesor = fachada.cargarPermisosTotales(asesorEditar.getUsnCodigo());

        return configuracion.getRutaContexto()+"usuarios/adminPermisosAsesor";
    }
    
    public String borrarPermiso() {
        if (asesorEditar.getCodigoSIUST().getUserCode() == 0){
            mensajeAdminUsuario = "<br><b>Error al borrar el permiso al usuario.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        
        if (asesorEditar.getTunCodigo().getTunCodigo() != 3){
            mensajeAdminUsuario = "<br><b>El usuario no es Asesor<br><br>";
            return null;
        }
        
        facade fachada = new facade();
        int resultado = 0;
        resultado = fachada.cambiarPermisoAsesor(asesorEditar.getUsnCodigo(), tipoPermiso, false);
        
        switch(resultado) {
            case 0:
                mensajeAdminUsuario = "<br><b>Error al borrar el permiso.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                mensajeAdminUsuario  = "<br><b>Permiso borrado correctamente.</b><br><br>";
                permisosAsesor = fachada.cargarPermisosTotales(asesorEditar.getUsnCodigo());
                break;
            case 2:
                mensajeAdminUsuario  = "<br><b>El permiso no puede borrarse porque el usuario no lo tiene asignado.</b><br><br>";
                break;
        }
        
        if (resultado != 1) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.addCallbackParam("abrirDialog", true);
        }
        
        return null;
    }
    
    public String crearPermiso() {
        if (asesorEditar.getCodigoSIUST().getUserCode() == 0){
            mensajeAdminUsuario = "<br><b>Error al crear el permiso al usuario.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        
        if (asesorEditar.getTunCodigo().getTunCodigo() != 3){
            mensajeAdminUsuario = "<br><b>El usuario no es Asesor<br><br>";
            return null;
        }
        
        facade fachada = new facade();
        int resultado = 0;
        resultado = fachada.cambiarPermisoAsesor(asesorEditar.getUsnCodigo(), tipoPermiso, true);
        
        switch(resultado) {
            case 0:
                mensajeAdminUsuario = "<br><b>Error al crear el permiso.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                mensajeAdminUsuario  = "<br><b>Permiso creado correctamente.</b><br><br>";
                permisosAsesor = fachada.cargarPermisosTotales(asesorEditar.getUsnCodigo());
                break;
            case 2:
                mensajeAdminUsuario  = "<br><b>El permiso no puede crearse porque el usuario ya lo tiene asignado.</b><br><br>";
                break;
        }
        
        if (resultado != 1) {
            RequestContext context = RequestContext.getCurrentInstance();
            context.addCallbackParam("abrirDialog", true);
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
    
    /*public String detalleTramiteSeleccionado(SelectEvent event) {
        mensajeTramite = "";
        selectedTramite = seleccionBuscarTramite;
        seleccionBuscarTramite = null;
        
        FacesContext.getCurrentInstance().getExternalContext().getFlash().put("selectedTramite", event.getObject());  
        return configuracion.getRutaContexto()+"usuarios/tramite?faces-redirect=true";
    }*/
    
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
        vo.setTrtObservaciones(observacionesTramite);
   
        boolean resultado = fachada.archivarTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeTramite = "<br><b>Trámite archivado.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
        } else {
            mensajeTramite = "<br><b>Error al archivar el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        observacionesTramite = "";
        
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
        vo.setTrtObservaciones(observacionesTramite);
   
        boolean resultado = fachada.enviarTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeTramite = "<br><b>Trámite enviado al Coordinador.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
            
            vo = fachada.cargarTramites(0, 1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0);
            
            List<UsUsuariosVO> coordinadores = fachada.getUsuarios(2);
            for(UsUsuariosVO u: coordinadores){
                String mensaje = "El usuario "+userVO.getCodigoSIUST().getLogin() + " (" + userVO.getTunCodigo().getTutNombre() + ") te ha enviado un trámite.<br/><br/>"
                        + "Código del trámite: "+vo.getTrnCodigo()+"<br/><br/>"
                        + "Empresa: "+vo.getEmrCodigo().getEmtNombre()+"<br/><br/>";
                enviarCorreo(u.getCodigoSIUST().getEmail(), mensaje, selectedTramite.getTrnCodigo());
            }
            
        } else {
            mensajeTramite = "<br><b>Error al enviar el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        observacionesTramite = "";
        
        return null;
    }
    
    public void enviarCorreo(String usuarioTo, String mensaje, Integer TramiteId){
        Correo correo = new Correo();
        correo.setServidor(configuracion.getServidorCorreo());
        correo.setPuerto(configuracion.getPuertoServidor());
        correo.setCorreoFrom(configuracion.getCorreoAplicacion());
        correo.setPassword(configuracion.getPasswordCorreo());
        correo.setAsunto("Sistema de Información y Gestión de Recursos de Identificación - Trámite " + TramiteId);
        //String mensaje = "El usuario "+userVO.getCodigoSIUST().getLogin()+" te ha enviado un trámite.";
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
        vo.setTrtObservaciones(observacionesTramite);
   
        boolean resultado = fachada.devolverTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            if (userVO.getTunCodigo().getTunCodigo() == 3) {
                mensajeTramite = "<br><b>Trámite enviado al Asignador para reasignar.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
            } else {
                mensajeTramite = "<br><b>Trámite devuelto al Asesor.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
            }
            vo = fachada.cargarTramites(0, 1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0);
            
            String email = vo.getUsnCodigo().getCodigoSIUST().getEmail();
            String mensaje = "El usuario "+userVO.getCodigoSIUST().getLogin() + " (" + userVO.getTunCodigo().getTutNombre() + ") te ha devuelto un trámite.<br/><br/>"
                        + "Código del trámite: "+vo.getTrnCodigo()+"<br/><br/>"
                        + "Empresa: "+vo.getEmrCodigo().getEmtNombre()+"<br/><br/>";
            enviarCorreo(email, mensaje, selectedTramite.getTrnCodigo());
            
        } else {
            mensajeTramite = "<br><b>Error al devolver el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        observacionesTramite = "";
        
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
        vo.setTrtObservaciones(observacionesTramite);
   
        boolean resultado = fachada.aprobarTramite(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
            mensajeTramite = "<br><b>Trámite aprobado.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
            
            vo = fachada.cargarTramites(0, 1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0);

            List<UsUsuariosVO> administradores = fachada.getUsuarios(1);
            for(UsUsuariosVO u: administradores){
                String mensaje = "El usuario "+userVO.getCodigoSIUST().getLogin() + " (" + userVO.getTunCodigo().getTutNombre() + ") ha aprobado un trámite.<br/><br/>"
                        + "Código del trámite: "+vo.getTrnCodigo()+"<br/><br/>"
                        + "Empresa: "+vo.getEmrCodigo().getEmtNombre()+"<br/><br/>";
                enviarCorreo(u.getCodigoSIUST().getEmail(), mensaje, selectedTramite.getTrnCodigo());
            }
            
        } else {
            mensajeTramite = "<br><b>Error al aprobar el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        observacionesTramite = "";
        
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
    
    public String asignarTramite() {
        if(nuevoUsuarioTramite == -1){
            mensajeTramite = "<br><b>Error al asignar el trámite.</b><br><br>Debes escoger un usuario<br><br>";
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
        vo.setTrtObservaciones("Trámite asignado por el usuario: " + userVO.getCodigoSIUST().getLogin() + " (" + userVO.getTunCodigo().getTutNombre() + ").");
   
        Integer resultado = fachada.cambiarUsuarioTramite(vo, nuevoUsuarioTramite);
        
        switch(resultado){
            case 0:
                mensajeTramite = "<br><b>Error al asignar el usuario del trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                selectedTramite = fachada.cargarTramites(0, -1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0);
                tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
                
                vo = fachada.cargarTramites(0, 1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0);
                mensajeTramite  = "<br><b>Trámite asignado correctamente al usuario " + vo.getUsnCodigo().getCodigoSIUST().getLogin() + ".</b><br><br>";
                
                String email = vo.getUsnCodigo().getCodigoSIUST().getEmail();
                String mensaje = "El usuario "+userVO.getCodigoSIUST().getLogin() + " (" + userVO.getTunCodigo().getTutNombre() + ") te ha asignado un trámite.<br/><br/>"
                            + "Código del trámite: "+vo.getTrnCodigo()+"<br/><br/>"
                            + "Empresa: "+vo.getEmrCodigo().getEmtNombre()+"<br/><br/>";
                enviarCorreo(email, mensaje, selectedTramite.getTrnCodigo());
                break;
            case 2:
                mensajeTramite = "<br><b>El usuario a asignar no puede ser igual al que tiene actualmente el trámite.</b><br><br>";
                break;
        }
        
        observacionesTramite = "";
        
        return null;
    }
    
    public String detalleMostrarTramites() {
        mensajeTramite = "";
        return configuracion.getRutaContexto()+"usuarios/mostrarTramites";
    }
    
    public String unirTramites() {
        if ((selectedUnirTramites == null)||(selectedUnirTramites.length < 2)){
            mensajeTramite = "<br><b>Error al asignar el trámite.</b><br><br>Debes escoger al menos 2 trámites<br><br>";
        }
        
        facade fachada = new facade();
        Integer resultado = fachada.unirTramites(selectedUnirTramites);
        
        switch(resultado){
            case 0:
                mensajeTramite = "<br><b>Error al unir los trámites.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
                mensajeTramite = "<br><b>Trámites unidos correctamente.</b><br><br>";
                break;
            case 2:
                mensajeTramite = "<br><b>Las empresas de los trámites deben ser las mismas.</b><br><br>";
                break;
        }
        
        
        
        return configuracion.getRutaContexto()+"usuarios/mostrarTramites";
    }
        
    public void onRowSelectUnir(SelectEvent event) {
        if (selectedUnirTramites == null){
            selectedUnir = false;
        } else if (selectedUnirTramites.length > 1) {
            selectedUnir = true;
        } else {
            selectedUnir = false;
        }
    }
    
    public void onRowUnselectUnir(UnselectEvent event) {
        if (selectedUnirTramites == null){
            selectedUnir = false;
        } else if (selectedUnirTramites.length > 1) {
            selectedUnir = true;
        } else {
            selectedUnir = false;
        }
    }
    
    public String cambiarOperadorTramite() {
        if(nuevoOperadorTramite.equals("-1")){
            mensajeCambiarOperadorTramite = "<br><b>Error al cambiar el operador del trámite.</b><br><br>Debes escoger un operador<br><br>";
            return null;
        }
        
        facade fachada = new facade();
        TrTramitesVO vo = new TrTramitesVO();
        
        mensajeCambiarOperadorTramite = "";
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setTrnCodigo(selectedTramite.getTrnCodigo());
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
        vo.setTrtObservaciones("Operador anterior: " + selectedTramite.getEmrCodigo().getEmtNombre()
                + ". Nuevo operador: " + fachada.buscarOperador(nuevoOperadorTramite).getEmtNombre()+".");
   
        Integer resultado = fachada.cambiarOperadorTramite(vo, nuevoOperadorTramite);
        
        switch(resultado){
            case 0:
                mensajeCambiarOperadorTramite = "<br><b>Error al cambiar el operador del trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                selectedTramite = fachada.cargarTramites(0, -1, selectedTramite.getTrnCodigo(), -1, "-1", -1, -1).get(0);
                tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
                mensajeCambiarOperadorTramite  = "<br><b>Operador cambiado correctamente al trámite.</b><br><br>";
                break;
            case 2:
                mensajeCambiarOperadorTramite = "<br><b>El operador a asignar no puede ser igual al que tiene actualmente el trámite.</b><br><br>";
                break;
        }
        
        nuevoOperadorTramite = "-1";
        
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
        String mensaje = "";
        boolean error = false;
        if ((codigoAccion <= 0)||(tipoRecurso.equals("")) && !error){
            mensaje = "Si el error persiste, por favor contacte al Aministrador.";
            error = true;
            cerrarDialog = true;
        } else 
        if(radicadoAgregarRecurso.equals("") && !error){
            mensaje = "Debes ingresar un número de radicado.";
            error = true;
            cerrarDialog = false;
        }
        
        if(!validaNum(radicadoAgregarRecurso) && !error){
            mensaje = "El radicado debe ser un número entero.";
            error = true;
            cerrarDialog = false;
        }
        
        if((tramiteAgregarRecurso <= 0) && !error){
            mensaje = "Debes escoger un trámite.";
            error = true;
            cerrarDialog = false;
        }
        
        if(!error) {
        
            mensajeRecurso = "";

            FacesContext facesContext = FacesContext.getCurrentInstance();

            TrTramitesVO tramite = new TrTramitesVO();
            tramite.setTrnCodigo(tramiteAgregarRecurso);

            AcAccionVO accion = new AcAccionVO();
            accion.setAcnCodigo(codigoAccion);

            facade fachada = new facade();

            Integer resultado = 0;

            agregar:
            if(tipoRecurso.equals("senalizacion")){
                if(!userSession.getPermisos().isSenalizacion()){
                    mensaje = "No tienes permisos asignados para el módulo de señalización.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                //TsTramiteSenalizacionVO vo = new TsTramiteSenalizacionVO();
                SenalizacionBean sen = (SenalizacionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{SenalizacionBean}", SenalizacionBean.class);//session.getAttribute("SenalizacionBean");

                List<SeSenalizacionVO> senalizacion = new ArrayList<SeSenalizacionVO>();

                int size = sen.getSelectedSens().length;
                for (int i = 0; i < size; i++) {
                    senalizacion.add(sen.getSelectedSens()[i]);
                }

                //SeSenalizacionVO senalizacion = new SeSenalizacionVO();
                //senalizacion.setSenCodigo(sen.getSelectedSen().getSenCodigo());

                forSenalizacion:
                for(SeSenalizacionVO s : senalizacion){
                    //numeracion.setSenCodigo(num.getSelectedSen().getSenCodigo());
                    TsTramiteSenalizacionVO tsVO = new TsTramiteSenalizacionVO();

                    MunicipiosVO municipio = new MunicipiosVO();
                    EmOperadorVO operador = new EmOperadorVO();
                    String nombreNodo = "";
                    String marcaModelo = "";
                    String direccion = "";
                    String observaciones = tramiteSenalizacionVO.getTstObservaciones();//this.observacionesAgregarRecurso;
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;

                    switch(codigoAccion){
                        case 2: //Preasignar
                            
                            if(tramiteSenalizacionVO.getTstNombreNodo().equals("")){
                                mensaje = "Debes ingresar el nombre del nodo.";
                                error = true;
                                break forSenalizacion;
                            }
                            if(tramiteSenalizacionVO.getTstMarcaModelo().equals("")){
                                mensaje = "Debes ingresar la marca/modelo del nodo.";
                                error = true;
                                break forSenalizacion;
                            }
                            if(tramiteSenalizacionVO.getTstDireccion().equals("")){
                                mensaje = "Debes ingresar la dirección de ubicación del nodo.";
                                error = true;
                                break forSenalizacion;
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
                            if (reservaTemporal == true){
                                resTemp = 'S';                    
                                mesesResTemp = mesesReserva;
                            }
                            municipio.setCodigoMunicipio(s.getCodigoMunicipio().getCodigoMunicipio());
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            nombreNodo = "";
                            marcaModelo = "";
                            direccion = "";

                            break;
                    }

                    tsVO.setTrnCodigo(tramite);
                    tsVO.setSenCodigo(s);
                    tsVO.setAcnCodigo(accion);
                    tsVO.setTsnRadicado(radicado);
                    tsVO.setCodigoMunicipio(municipio);
                    tsVO.setEmrCodigo(operador);
                    tsVO.setTstNombreNodo(nombreNodo);
                    tsVO.setTstMarcaModelo(marcaModelo);
                    tsVO.setTstDireccion(direccion);
                    tsVO.setTstObservaciones(observaciones);
                    tsVO.setTstReservaTemporal(resTemp);
                    tsVO.setTsnMesesLiberacion(mesesResTemp);

                    vo.add(tsVO); 
                }
                if (!error) {
                    resultado = fachada.agregarRecursos(vo);
                    tramiteSenalizacionVO = new TsTramiteSenalizacionVO();
                    sen.setSelectedSensAccion(false);
                }
                
            } else if (tipoRecurso.equals("numeracion")){
                if(!userSession.getPermisos().isNumeracion()){
                    mensaje = "No tienes permisos asignados para el módulo de numeración.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                NumeracionBean num = (NumeracionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{NumeracionBean}", NumeracionBean.class);//session.getAttribute("SenalizacionBean");

                List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();

                int size = num.getSelectedNums().length;
                for (int i = 0; i < size; i++) {
                    numeracion.add(num.getSelectedNums()[i]);
                }

                for(NuNumeracionVO n : numeracion){
                    //numeracion.setSenCodigo(num.getSelectedSen().getSenCodigo());
                    TnTramiteNumeracionVO tnVO = new TnTramiteNumeracionVO();

                    MunicipiosVO municipio = new MunicipiosVO();
                    EmOperadorVO operador = new EmOperadorVO();
                    String observaciones = tramiteNumeracionVO.getTntObservaciones();//this.observacionesAgregarRecurso;
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;

                    switch(codigoAccion){
                        case 2: //Preasignar
                            municipio.setCodigoMunicipio(tramiteNumeracionVO.getCodigoMunicipio().getCodigoMunicipio());
                            for (TrTramitesVO detalleVO : tramites) {
                                if (detalleVO.getTrnCodigo() == tramiteAgregarRecurso){
                                    operador.setEmrCodigo(detalleVO.getEmrCodigo().getEmrCodigo());
                                    break;
                                }
                            }
                            break;
                        case 5: //Recuperar
                            if (reservaTemporal=true){
                                resTemp='S';                    
                                mesesResTemp=mesesReserva;
                            }
                            municipio.setCodigoMunicipio(n.getCodigoMunicipio().getCodigoMunicipio());
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            break;
                    }

                    tnVO.setTrnCodigo(tramite);
                    tnVO.setAcnCodigo(accion);
                    tnVO.setTnnRadicado(radicado);
                    tnVO.setCodigoMunicipio(municipio);
                    tnVO.setEmrCodigo(operador);
                    tnVO.setNdnCodigo(n.getNdnCodigo());
                    tnVO.setTnnInicio(n.getNunInicio());
                    tnVO.setTnnFin(n.getNunFin());
                    tnVO.setTntObservaciones(observaciones);
                    tnVO.setTntReservaTemporal(resTemp);
                    tnVO.setTnnMesesLiberacion(mesesResTemp);

                    vo.add(tnVO);

                }

                resultado = fachada.agregarRecursos(vo);
                tramiteNumeracionVO = new TnTramiteNumeracionVO();
                num.setSelectedNumsAccion(false);
            } else if (tipoRecurso.equals("codigosld")) {
                if(!userSession.getPermisos().isCodigosLd()){
                    mensaje = "No tienes permisos asignados para el módulo de códigos LD.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                //TlTramiteLdVO vo = new TlTramiteLdVO();
                CodigosLdBean ld = (CodigosLdBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosLdBean}", CodigosLdBean.class);//session.getAttribute("SenalizacionBean");

                List<ClCodigosLdVO> codigosLD = new ArrayList<ClCodigosLdVO>();

                int size = ld.getSelectedLds().length;
                for (int i = 0; i < size; i++) {
                    codigosLD.add(ld.getSelectedLds()[i]);
                }

                //ClCodigosLdVO recurso = new ClCodigosLdVO();
                //recurso.setClnCodigo(recursoBean.getSelectedLd().getClnCodigo());

                for(ClCodigosLdVO l : codigosLD){
                    TlTramiteLdVO tlVO = new TlTramiteLdVO();

                    EmOperadorVO operador = new EmOperadorVO();
                    String observaciones = tramiteCodigosLdVO.getTltObservaciones();//this.observacionesAgregarRecurso;
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;
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
                            if (reservaTemporal=true){
                                resTemp='S';                    
                                mesesResTemp=mesesReserva;
                            }
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            break;
                    }
                    tlVO.setTrnCodigo(tramite);
                    tlVO.setClnCodigo(l);
                    tlVO.setAcnCodigo(accion);
                    tlVO.setTlnRadicado(radicado);
                    tlVO.setEmrCodigo(operador);
                    tlVO.setTltObservaciones(observaciones);
                    tlVO.setTltReservaTemporal(resTemp);
                    tlVO.setTlnMesesLiberacion(mesesResTemp);

                    vo.add(tlVO);
                }

                resultado = fachada.agregarRecursos(vo);
                tramiteCodigosLdVO = new TlTramiteLdVO();
                ld.setSelectedLdsAccion(false);
            } else if (tipoRecurso.equals("codigosCortos")) {
                if(!userSession.getPermisos().isCodigosCortos()){
                    mensaje = "No tienes permisos asignados para el módulo de codigos cortos.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                //TcTramiteCcVO vo = new TcTramiteCcVO();
                CodigosCortosBean cc = (CodigosCortosBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosCortosBean}", CodigosCortosBean.class);//session.getAttribute("SenalizacionBean");

                List<CcCodigosCortosVO> codigoCorto = new ArrayList<CcCodigosCortosVO>();

                int size = cc.getSelectedCCs().length;
                for (int i = 0; i < size; i++) {
                    codigoCorto.add(cc.getSelectedCCs()[i]);
                }

                //CcCodigosCortosVO recurso = new CcCodigosCortosVO();
                //recurso.setCcnCodigo(recursoBean.getSelectedCodigoCorto().getCcnCodigo());

                for(CcCodigosCortosVO c : codigoCorto){
                    TcTramiteCcVO tcVO = new TcTramiteCcVO();
                    EmOperadorVO operador = new EmOperadorVO();
                    String observaciones = tramiteCodigosCortosVO.getTctObservaciones();//this.observacionesAgregarRecurso;
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;
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
                            if (reservaTemporal=true){
                                resTemp='S';                    
                                mesesResTemp=mesesReserva;
                            }
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            break;
                    }
                    tcVO.setTrnCodigo(tramite);
                    tcVO.setCcnCodigo(c);
                    tcVO.setAcnCodigo(accion);
                    tcVO.setTcnRadicado(radicado);
                    tcVO.setEmrCodigo(operador);
                    tcVO.setTctObservaciones(observaciones);
                    tcVO.setTctReservaTemporal(resTemp);
                    tcVO.setTcnMesesLiberacion(mesesResTemp);

                    vo.add(tcVO);

                }
                resultado = fachada.agregarRecursos(vo);
                tramiteCodigosCortosVO = new TcTramiteCcVO();
                cc.setSelectedCCsAccion(false);
            } else if (tipoRecurso.equals("marcacionAbreviada")) {
                if(!userSession.getPermisos().isMarcacionAbreviada()){
                    mensaje = "No tienes permisos asignados para el módulo de marcación abreviada.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                //TcTramiteCcVO vo = new TcTramiteCcVO();
                MarcacionAbreviadaBean ma = (MarcacionAbreviadaBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{MarcacionAbreviadaBean}", MarcacionAbreviadaBean.class);//session.getAttribute("SenalizacionBean");

                List<MaMarcacionAbreviadaVO> marcacionAbrevidada = new ArrayList<MaMarcacionAbreviadaVO>();

                int size = ma.getSelecteds().length;
                for (int i = 0; i < size; i++) {
                    marcacionAbrevidada.add(ma.getSelecteds()[i]);
                }

                //CcCodigosCortosVO recurso = new CcCodigosCortosVO();
                //recurso.setCcnCodigo(recursoBean.getSelectedCodigoCorto().getCcnCodigo());

                for(MaMarcacionAbreviadaVO c : marcacionAbrevidada){
                    TaTramiteMaVO taVO = new TaTramiteMaVO();
                    EmOperadorVO operador = new EmOperadorVO();
                    String observaciones = tramiteMarcacionAbreviadaVO.getTatObservaciones();//this.observacionesAgregarRecurso;
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;
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
                            if (reservaTemporal=true){
                                resTemp='S';                    
                                mesesResTemp=mesesReserva;
                            }
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            break;
                    }
                    taVO.setTrnCodigo(tramite);
                    taVO.setManCodigo(c);
                    taVO.setAcnCodigo(accion);
                    taVO.setTanRadicado(radicado);
                    taVO.setEmrCodigo(operador);
                    taVO.setTatObservaciones(observaciones);
                    taVO.setTatReservaTemporal(resTemp);
                    taVO.setTanMesesLiberacion(mesesResTemp);

                    vo.add(taVO);

                }
                resultado = fachada.agregarRecursos(vo);
                tramiteMarcacionAbreviadaVO = new TaTramiteMaVO();
                ma.setSelectedsAccion(false);
            } else if (tipoRecurso.equals("codigosMnc")) {
                if(!userSession.getPermisos().isCodigosMnc()){
                    mensaje = "No tienes permisos asignados para el módulo de códigos MNC.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                CodigosMncBean bean = (CodigosMncBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosMncBean}", CodigosMncBean.class);//session.getAttribute("SenalizacionBean");

                List<CdCodigosMncVO> codigosMnc = new ArrayList<CdCodigosMncVO>();

                int size = bean.getSelecteds().length;
                for (int i = 0; i < size; i++) {
                    codigosMnc.add(bean.getSelecteds()[i]);
                }

                for(CdCodigosMncVO c : codigosMnc){
                    TmTramiteMncVO tmVO = new TmTramiteMncVO();
                    EmOperadorVO operador = new EmOperadorVO();
                    String observaciones = tramiteMncVO.getTmtObservaciones();
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;
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
                            if (reservaTemporal=true){
                                resTemp='S';                    
                                mesesResTemp=mesesReserva;
                            }
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            break;
                    }
                    tmVO.setTrnCodigo(tramite);
                    tmVO.setCdnCodigo(c);
                    tmVO.setAcnCodigo(accion);
                    tmVO.setTmnRadicado(radicado);
                    tmVO.setEmrCodigo(operador);
                    tmVO.setTmtObservaciones(observaciones);
                    tmVO.setTmtReservaTemporal(resTemp);
                    tmVO.setTmnMesesLiberacion(mesesResTemp);

                    vo.add(tmVO);

                }
                resultado = fachada.agregarRecursos(vo);
                tramiteMncVO = new TmTramiteMncVO();
                bean.setSelectedsAccion(false);
            } else if (tipoRecurso.equals("codigosNrn")) {
                if(!userSession.getPermisos().isCodigosNrn()){
                    mensaje = "No tienes permisos asignados para el módulo de códigos NRN.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                CodigosNrnBean bean = (CodigosNrnBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosNrnBean}", CodigosNrnBean.class);//session.getAttribute("SenalizacionBean");

                List<NrCodigosNrnVO> codigosNrn = new ArrayList<NrCodigosNrnVO>();

                int size = bean.getSelecteds().length;
                for (int i = 0; i < size; i++) {
                    codigosNrn.add(bean.getSelecteds()[i]);
                }

                for(NrCodigosNrnVO c : codigosNrn){
                    TkTramiteNrnVO tkVO = new TkTramiteNrnVO();
                    EmOperadorVO operador = new EmOperadorVO();
                    String observaciones = tramiteNrnVO.getTktObservaciones();
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;
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
                            if (reservaTemporal=true){
                                resTemp='S';                    
                                mesesResTemp=mesesReserva;
                            }
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            break;
                    }
                    tkVO.setTrnCodigo(tramite);
                    tkVO.setNrnCodigo(c);
                    tkVO.setAcnCodigo(accion);
                    tkVO.setTknRadicado(radicado);
                    tkVO.setEmrCodigo(operador);
                    tkVO.setTktObservaciones(observaciones);
                    tkVO.setTktReservaTemporal(resTemp);
                    tkVO.setTknMesesLiberacion(mesesResTemp);

                    vo.add(tkVO);

                }
                resultado = fachada.agregarRecursos(vo);
                tramiteNrnVO = new TkTramiteNrnVO();
                bean.setSelectedsAccion(false);
            } else if (tipoRecurso.equals("codigosIin")) {
                if(!userSession.getPermisos().isCodigosIin()){
                    mensaje = "No tienes permisos asignados para el módulo de códigos IIN.";
                    error = true;
                    cerrarDialog = true;
                    break agregar;
                }
                
                ArrayList vo = new ArrayList();
                CodigosIinBean bean = (CodigosIinBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosIinBean}", CodigosIinBean.class);//session.getAttribute("SenalizacionBean");

                List<CiCodigosIinVO> codigosIin = new ArrayList<CiCodigosIinVO>();

                int size = bean.getSelecteds().length;
                for (int i = 0; i < size; i++) {
                    codigosIin.add(bean.getSelecteds()[i]);
                }

                for(CiCodigosIinVO c : codigosIin){
                    TiTramiteIinVO tiVO = new TiTramiteIinVO();
                    EmOperadorVO operador = new EmOperadorVO();
                    String observaciones = tramiteIinVO.getTitObservaciones();
                    Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
                    char resTemp = 'N';
                    int mesesResTemp = 0;
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
                            if (reservaTemporal=true){
                                resTemp='S';                    
                                mesesResTemp=mesesReserva;
                            }
                            operador.setEmrCodigo(configuracion.getOperadorNinguno());
                            break;
                    }
                    tiVO.setTrnCodigo(tramite);
                    tiVO.setCinCodigo(c);
                    tiVO.setAcnCodigo(accion);
                    tiVO.setTinRadicado(radicado);
                    tiVO.setEmrCodigo(operador);
                    tiVO.setTitObservaciones(observaciones);
                    tiVO.setTitReservaTemporal(resTemp);
                    tiVO.setTinMesesLiberacion(mesesResTemp);

                    vo.add(tiVO);

                }
                resultado = fachada.agregarRecursos(vo);
                tramiteIinVO = new TiTramiteIinVO();
                bean.setSelectedsAccion(false);
            }
            
            if (!error) {
                switch(resultado){
                    case 0:
                        mensaje = "Si el error persiste, por favor contacte al Aministrador";
                        error = true;
                        cerrarDialog = true;
                        break;
                    case 1:
                        tramites = fachada.cargarTramites(tipoUsuario, userVO.getCodigoSIUST().getUserCode());
                        mensaje = "Código del trámite: "+tramiteAgregarRecurso;
                        error = false;
                        cerrarDialog = true;
                        break;
                    case 2:
                        mensaje = "Falta diligenciar un dato.";
                        error = true;
                        cerrarDialog = false;
                        break;
                    case 3:
                        mensaje = "El operador del recurso debe ser el mismo del trámite.";
                        error = true;
                        cerrarDialog = false;
                        break;
                    case 4:
                        mensaje = "El recurso ya tiene un trámite asociado.";
                        error = true;
                        cerrarDialog = true;
                        break;
                    case 5:
                        mensaje = "El recurso debe tener estado \"ASIGNADO\"";
                        error = true;
                        cerrarDialog = true;
                        break;
                    case 6:
                        mensaje = "El recurso debe tener estado \"LIBRE\".";
                        error = true;
                        cerrarDialog = true;
                        break;
                }
            }
            
        }
        
        if (error){
            mensajeRecurso = "<table>"
                        + "<tr>"
                        + "<td><img src=\"" + rutaImagenError + "\" width=\"50\" height=\"50\" /></td>"
                        + "<td><b><font color=\"red\">Error al agregar recurso al trámite.</font></b></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td colspan=\"2\"><br>" + mensaje + "<td>"
                        + "</table>"
                        + "<br><br>";
        } else {
            mensajeRecurso = "<table>"
                        + "<tr>"
                        + "<td><img src=\"" + rutaImagenOk + "\" width=\"50\" height=\"50\" /></td>"
                        + "<td><b>Recurso agregado correctamente al trámite.</b></td>"
                        + "</tr>"
                        + "<tr>"
                        + "<td colspan=\"2\"><br>" + mensaje + "<td>"
                        + "</table>"
                        + "<br><br>";
        }
        
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("cerrarDialog", cerrarDialog);
        //this.setTramiteAgregarRecurso(-1);
        //this.radicadoAgregarRecurso = "";
        //this.observacionesAgregarRecurso = "";

        return null;
    }
    
    public String liberarReservarRecurso() {
        if ((codigoAccion <= 0)||(tipoRecurso.equals(""))){
            mensajeRecurso = "<br><b>Error al liberar/reservar el recurso.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        
        mensajeRecurso = "";
        
        FacesContext facesContext = FacesContext.getCurrentInstance();
        
        facade fachada = new facade();
        
        Integer resultado = 0;
        
        ArrayList vo = new ArrayList();
        if (tipoRecurso.equals("senalizacion")) {
            SenalizacionBean bean = (SenalizacionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{SenalizacionBean}", SenalizacionBean.class);
            
            int size = bean.getSelectedSens().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelectedSens()[i]);
            }
            bean.setSelectedSensAccion(false);
        } else if (tipoRecurso.equals("numeracion")) {
            NumeracionBean bean = (NumeracionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{NumeracionBean}", NumeracionBean.class);
            
            int size = bean.getSelectedNums().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelectedNums()[i]);
            }
            bean.setSelectedNumsAccion(false);
        } else if (tipoRecurso.equals("codigosld")) {
            CodigosLdBean bean = (CodigosLdBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosLdBean}", CodigosLdBean.class);
            
            int size = bean.getSelectedLds().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelectedLds()[i]);
            }
            bean.setSelectedLdsAccion(false);
        } else if (tipoRecurso.equals("codigosCortos")) {
            CodigosCortosBean bean = (CodigosCortosBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosCortosBean}", CodigosCortosBean.class);
            
            int size = bean.getSelectedCCs().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelectedCCs()[i]);
            }
            bean.setSelectedCCsAccion(false);
            bean.buscar();
        } else if (tipoRecurso.equals("marcacionAbreviada")) {
            MarcacionAbreviadaBean bean = (MarcacionAbreviadaBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{MarcacionAbreviadaBean}", MarcacionAbreviadaBean.class);
            
            int size = bean.getSelecteds().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelecteds()[i]);
            }
            bean.setSelectedsAccion(false);
        } else if (tipoRecurso.equals("codigosMnc")) {
            CodigosMncBean bean = (CodigosMncBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosMncBean}", CodigosMncBean.class);
            
            int size = bean.getSelecteds().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelecteds()[i]);
            }
            bean.setSelectedsAccion(false);
        } else if (tipoRecurso.equals("codigosNrn")) {
            CodigosNrnBean bean = (CodigosNrnBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosNrnBean}", CodigosNrnBean.class);
            
            int size = bean.getSelecteds().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelecteds()[i]);
            }
            bean.setSelectedsAccion(false);
        } else if (tipoRecurso.equals("codigosIin")) {
            CodigosIinBean bean = (CodigosIinBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{CodigosIinBean}", CodigosIinBean.class);
            
            int size = bean.getSelecteds().length;
            for (int i = 0; i < size; i++) {
                vo.add(bean.getSelecteds()[i]);
            }
            bean.setSelectedsAccion(false);
        }
        
        String accion = "";
        if (codigoAccion == 1) {
            accion = "Liberación";
            resultado = fachada.reservarLiberarRecurso(vo,0);
        } else if (codigoAccion == 4) {
            accion = "Reserva";
            resultado = fachada.reservarLiberarRecurso(vo,1);
        }
        
        switch(resultado){
            case 0:
                mensajeRecurso = "<br><b>Error de "+ accion + " del recurso.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
                break;
            case 1:
                mensajeRecurso = "<br><b>" + accion + " exitosa de recursos</b><br><br>";
                break;
            case 2:
                mensajeRecurso = "<br><b>Falta diligenciar un dato.</b><br><br>";
                break;
            case 3:
                mensajeRecurso = "<br><b>El recurso no se puede liberar porque está reservado temporalmente</b><br><br>";
                break;
        }

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
        } else if(tipoRecurso.equals("numeracion")){
            TnTramiteNumeracionVO vo = new TnTramiteNumeracionVO();
            vo.setTnnCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        } else if(tipoRecurso.equals("codigosld")) {
            TlTramiteLdVO vo = new TlTramiteLdVO();
            vo.setTlnCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        } else if(tipoRecurso.equals("codigosCortos")) {
            TcTramiteCcVO vo = new TcTramiteCcVO();
            vo.setTcnCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        } else if(tipoRecurso.equals("marcacionAbreviada")) {
            TaTramiteMaVO vo = new TaTramiteMaVO();
            vo.setTanCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        } else if(tipoRecurso.equals("codigosMnc")) {
            TmTramiteMncVO vo = new TmTramiteMncVO();
            vo.setTmnCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        } else if(tipoRecurso.equals("codigosNrn")) {
            TkTramiteNrnVO vo = new TkTramiteNrnVO();
            vo.setTknCodigo(codigoDetalleTramite);
            resultado = fachada.eliminarRecurso(vo);
        }  else if(tipoRecurso.equals("codigosIin")) {
            TiTramiteIinVO vo = new TiTramiteIinVO();
            vo.setTinCodigo(codigoDetalleTramite);
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
            /*SenalizacionBean sen = (SenalizacionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{SenalizacionBean}", SenalizacionBean.class);//session.getAttribute("SenalizacionBean");
            seleccionDepartamento = sen.getSelectedSen().getCodigoMunicipio().getCodigoDepartamento().getCodigoDepartamento();
            cambiarDepartamento();
            MunicipiosVO municipio = new MunicipiosVO();
            municipio.setCodigoMunicipio(sen.getSelectedSen().getCodigoMunicipio().getCodigoMunicipio());
            tramiteSenalizacionVO.setCodigoMunicipio(municipio);*/
            SenalizacionBean sen = (SenalizacionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{SenalizacionBean}", SenalizacionBean.class);
            SeSenalizacionVO[] senalizacion = sen.getSelectedSens();
            
            int size = sen.getSelectedSens().length;
            String departamento = senalizacion[0].getCodigoMunicipio().getCodigoMunicipio();
            Boolean valida = true;
            for (int i = 1; i < size; i++) {
                if (!departamento.equals(senalizacion[i].getCodigoMunicipio().getCodigoMunicipio())) {
                    valida = false;
                    break;
                }
            }
            
            MunicipiosVO municipio = new MunicipiosVO();
            
            if (valida) {
                seleccionDepartamento = senalizacion[0].getCodigoMunicipio().getCodigoDepartamento().getCodigoDepartamento();
                municipio.setCodigoMunicipio(senalizacion[0].getCodigoMunicipio().getCodigoMunicipio());
            } else {
                seleccionDepartamento = "-1";
                municipio.setCodigoMunicipio("-1");
            }
            
            cambiarDepartamento();
            tramiteSenalizacionVO.setCodigoMunicipio(municipio);
            
        }else if(tipoRecurso.equals("numeracion")){
            NumeracionBean num = (NumeracionBean) facesContext.getApplication().evaluateExpressionGet(facesContext, "#{NumeracionBean}", NumeracionBean.class);
            NuNumeracionVO[] numeracion = num.getSelectedNums();
            
            int size = num.getSelectedNums().length;
            String departamento = numeracion[0].getCodigoMunicipio().getCodigoMunicipio();
            Boolean valida = true;
            for (int i = 1; i < size; i++) {
                if (!departamento.equals(numeracion[i].getCodigoMunicipio().getCodigoMunicipio())) {
                    valida = false;
                    break;
                }
            }
            
            MunicipiosVO municipio = new MunicipiosVO();
            
            if (valida) {
                seleccionDepartamento = numeracion[0].getCodigoMunicipio().getCodigoDepartamento().getCodigoDepartamento();
                municipio.setCodigoMunicipio(numeracion[0].getCodigoMunicipio().getCodigoMunicipio());
            } else {
                seleccionDepartamento = "-1";
                municipio.setCodigoMunicipio("-1");
            }
            
            cambiarDepartamento();
            tramiteNumeracionVO.setCodigoMunicipio(municipio);
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
        countRecurso = fachada.countCargarNumeracion(operadorOrigen, "-1", -1, -1, -1, -1, "-1", "-1");
        if(countRecurso > 0){
            opcionNumeracion = true;
        }
        // Activa el campo de señalizacion en transferencia de recursos
        countRecurso = fachada.countCargarSenalizacion(operadorOrigen, -1, -1, -1, -1, "-1", "-1", -1, -1);
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
        boolean haySenalizacion = false;
        boolean hayNumeracion = false;
        boolean hayCodigosLd = false;
        boolean hayCodigosCortos = false;
        boolean hayMarcacionAbreviada = false;
        boolean hayCodigosMnc = false;
        boolean hayCodigosNrn = false;
        boolean hayCodigosIin = false;
        haySenalizacion = !selectedTramite.getTsTramiteSenalizacionCollection().isEmpty();
        hayNumeracion = !selectedTramite.getTnTramiteNumeracionCollection().isEmpty();
        hayCodigosLd = !selectedTramite.getTlTramiteLdCollection().isEmpty();
        hayCodigosCortos = !selectedTramite.getTcTramiteCcCollection().isEmpty();
        hayMarcacionAbreviada = !selectedTramite.getTaTramiteMaCollection().isEmpty();
        hayCodigosMnc = !selectedTramite.getTmTramiteMncCollection().isEmpty();
        hayCodigosNrn = !selectedTramite.getTkTramiteNrnCollection().isEmpty();
        hayCodigosIin = !selectedTramite.getTiTramiteIinCollection().isEmpty();
        if (haySenalizacion || hayNumeracion || hayCodigosLd || hayCodigosCortos || hayMarcacionAbreviada || hayCodigosMnc || hayCodigosNrn || hayCodigosIin){
            return true;
        }
        return false;
    }
    
    public String consultaReservasTemporales() {

        facade fachada = new facade();
        reservas=new ArrayList();
        
        List<RsReservasTemporalesVO> reservasDetalle = new ArrayList<RsReservasTemporalesVO>();

        reservasDetalle = fachada.consultaReservasTemporales();
        int i=0;
        for (RsReservasTemporalesVO r : reservasDetalle) {
            
            Object recurso =null;
            
            reservas.add(new ArrayList());
              
            recurso = fachada.consultaRecurso(r.getRstTipoRecurso(), r.getRsnCodigoRecurso());
            
            if(r.getRstTipoRecurso().equals("Senalizacion")){
                SeSenalizacionVO rec = (SeSenalizacionVO)recurso;
                r.setRstTipoRecurso("Senalización");
                
                Functions funciones = new Functions();
                String recDetalle = rec.getRenCodigo().getRetNombre() +" - "+ Functions.rellenarCerosIzquierda(String.valueOf(rec.getSenZona()), 2)  +" - "+ Functions.rellenarCerosIzquierda(String.valueOf(rec.getSenPs()), 2);
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            } else if(r.getRstTipoRecurso().equals("Numeracion")){
                NuNumeracionVO rec = (NuNumeracionVO)recurso;
                r.setRstTipoRecurso("Numeración");
                String recDetalle = "(" + rec.getNdnCodigo().getNdtNombre() + ") " + rec.getNunInicio() + " - " + rec.getNunFin();
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            } else if(r.getRstTipoRecurso().equals("CodigosLd")){
                ClCodigosLdVO rec = (ClCodigosLdVO)recurso;
                r.setRstTipoRecurso("Codigo LD");
                int recDetalle = rec.getClnCodigoLd();
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            } else if(r.getRstTipoRecurso().equals("CodigosCortos")){
                CcCodigosCortosVO rec = (CcCodigosCortosVO)recurso;
                r.setRstTipoRecurso("Codigo Corto");
                int recDetalle = rec.getCcnCodigoCorto();
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            } else if(r.getRstTipoRecurso().equals("MarcacionAbreviada")){
                MaMarcacionAbreviadaVO rec = (MaMarcacionAbreviadaVO)recurso;
                r.setRstTipoRecurso("Marcación Abreviada");
                int recDetalle = rec.getManCodigoMarcacion();
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            } else if(r.getRstTipoRecurso().equals("CodigosMnc")){
                CdCodigosMncVO rec = (CdCodigosMncVO)recurso;
                r.setRstTipoRecurso("Códigos MNC");
                int recDetalle = rec.getCdnMnc();
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            } else if(r.getRstTipoRecurso().equals("CodigosNrn")){
                NrCodigosNrnVO rec = (NrCodigosNrnVO)recurso;
                r.setRstTipoRecurso("Códigos NRN");
                int recDetalle = rec.getNrnCodigoNrn();
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            } else if(r.getRstTipoRecurso().equals("CodigosIin")){
                CiCodigosIinVO rec = (CiCodigosIinVO)recurso;
                r.setRstTipoRecurso("Códigos IIN");
                int recDetalle = rec.getCinCodigoIin();
                ((ArrayList)reservas.get(i)).add(r);
                ((ArrayList)reservas.get(i)).add(recDetalle);
            }
            i++;
        }

        return configuracion.getRutaContexto()+"usuarios/consultaReservasTemporales";
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

    public TnTramiteNumeracionVO getTramiteNumeracionVO() {
        return tramiteNumeracionVO;
    }

    public void setTramiteNumeracionVO(TnTramiteNumeracionVO tramiteNumeracionVO) {
        this.tramiteNumeracionVO = tramiteNumeracionVO;
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

    /*public Collection<SelectItem> getListaUsuariosNoAplicacion() {
        return listaUsuariosNoAplicacion;
    }

    public void setListaUsuariosNoAplicacion(Collection<SelectItem> listaUsuariosNoAplicacion) {
        this.listaUsuariosNoAplicacion = listaUsuariosNoAplicacion;
    }*/

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

    public UsUsuariosVO getAsesorEditar() {
        return asesorEditar;
    }

    public void setAsesorEditar(UsUsuariosVO asesorEditar) {
        this.asesorEditar = asesorEditar;
    }

    public List<PtTipoPermisoVO> getPermisosAsesor() {
        return permisosAsesor;
    }

    public void setPermisosAsesor(List<PtTipoPermisoVO> permisosAsesor) {
        this.permisosAsesor = permisosAsesor;
    }

    public int getTipoPermiso() {
        return tipoPermiso;
    }

    public void setTipoPermiso(int tipoPermiso) {
        this.tipoPermiso = tipoPermiso;
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

    public int getMesesReserva() {
        return mesesReserva;
    }

    public void setMesesReserva(int mesesReserva) {
        this.mesesReserva = mesesReserva;
    }

    public Boolean getReservaTemporal() {
        return reservaTemporal;
    }

    public void setReservaTemporal(Boolean reservaTemporal) {
        this.reservaTemporal = reservaTemporal;
    }

    public TcTramiteCcVO getTramiteCodigosCortosVO() {
        return tramiteCodigosCortosVO;
    }

    public void setTramiteCodigosCortosVO(TcTramiteCcVO tramiteCodigosCortosVO) {
        this.tramiteCodigosCortosVO = tramiteCodigosCortosVO;
    }

    public ArrayList getReservas() {
        return reservas;
    }

    public void setReservas(ArrayList reservas) {
        this.reservas = reservas;
    }

    public TaTramiteMaVO getTramiteMarcacionAbreviadaVO() {
        return tramiteMarcacionAbreviadaVO;
    }

    public void setTramiteMarcacionAbreviadaVO(TaTramiteMaVO tramiteMarcacionAbreviadaVO) {
        this.tramiteMarcacionAbreviadaVO = tramiteMarcacionAbreviadaVO;
    }

    public TmTramiteMncVO getTramiteMncVO() {
        return tramiteMncVO;
    }

    public void setTramiteMncVO(TmTramiteMncVO tramiteMncVO) {
        this.tramiteMncVO = tramiteMncVO;
    }

    public TkTramiteNrnVO getTramiteNrnVO() {
        return tramiteNrnVO;
    }

    public void setTramiteNrnVO(TkTramiteNrnVO tramiteNrnVO) {
        this.tramiteNrnVO = tramiteNrnVO;
    }

    public TiTramiteIinVO getTramiteIinVO() {
        return tramiteIinVO;
    }

    public void setTramiteIinVO(TiTramiteIinVO tramiteIinVO) {
        this.tramiteIinVO = tramiteIinVO;
    }

    public String getMensajeCambiarOperadorTramite() {
        return mensajeCambiarOperadorTramite;
    }

    public void setMensajeCambiarOperadorTramite(String mensajeCambiarOperadorTramite) {
        this.mensajeCambiarOperadorTramite = mensajeCambiarOperadorTramite;
    }

    public String getNuevoOperadorTramite() {
        return nuevoOperadorTramite;
    }

    public void setNuevoOperadorTramite(String nuevoOperadorTramite) {
        this.nuevoOperadorTramite = nuevoOperadorTramite;
    }

    public TrTramitesVO[] getSelectedUnirTramites() {
        return selectedUnirTramites;
    }

    public void setSelectedUnirTramites(TrTramitesVO[] selectedUnirTramites) {
        this.selectedUnirTramites = selectedUnirTramites;
    }

    public Boolean getSelectedUnir() {
        return selectedUnir;
    }

    public void setSelectedUnir(Boolean selectedUnir) {
        this.selectedUnir = selectedUnir;
    }

}
