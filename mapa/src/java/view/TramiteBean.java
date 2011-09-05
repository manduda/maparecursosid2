/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import helper.ConvertirListasHelper;
import inicio.LoginFilter;
import inicio.UserBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.faces.model.SelectItem;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.AcAccionVO;
import vo.EmOperadorVO;
import vo.EtEstadoTramiteVO;
import vo.GtGestionTramiteVO;
import vo.MunicipiosVO;
import vo.SeSenalizacionVO;
import vo.TrTramitesVO;
import vo.TsTramiteSenalizacionVO;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "TramiteBean")
@SessionScoped
public class TramiteBean {
    private List<TrTramitesVO> tramites = new ArrayList<TrTramitesVO>();
    private TrTramitesVO selectedTramite;
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaTramites;
    private Integer tramiteAgregarRecurso;
    private String operadorCrearTramite;
    private UsUsuariosVO userVO;
    private String mensajeCrearTramite;
    private String mensajeTramite;
    private String mensajeRecurso;
    private String radicadoAgregarRecurso;
    private String observacionesAgregarRecurso;
    private Integer tipoUsuario = 0;
    private Integer codigoDetalleTramite = 0;

    /** Creates a new instance of TramiteBean */
    public TramiteBean() {
        facade fachada = new facade();
        userVO = new UsUsuariosVO();
        UserBean userSession = null;

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        userSession = (UserBean) session.getAttribute("UserBean");
        //System.out.println("user: "+userSession);
        if (userSession.isIsLoggedIn()) {
            userVO = userSession.getUserVO();
            switch(userVO.getTunCodigo().getTunCodigo()) {
                case 1:
                    tipoUsuario = 4;
                    break;
                case 2:
                    tipoUsuario = 2;
                    break;
                case 3:
                    tipoUsuario = 6;
                    break;
            }
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
            //System.out.print("user: "+userVO.getUsnCodigo());
        }
        
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        //userVO = (UsUsuariosVO)facesContext.getExternalContext().getSessionMap().get("UserBean");
        //System.out.print("user: "+userVO);
        //tramites = fachada.cargarTramites(1, userVO.getUsnCodigo());
        //tramites = fachada.cargarTramites(6, 1);
    }
    
    public String historiaTramite() {
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
    }
    
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
        
        return "/usuarios/crearTramite";
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
        
        System.out.println("resultado: "+resultado);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
            mensajeCrearTramite = "<br><b>Trámite creado.</b><br><br>Código del trámite: "+tramites.get(0).getTrnCodigo()+"<br><br>";
        } else {
            mensajeCrearTramite = "<br><b>Error al crear el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    
    public String detalleTramite() {
        FacesContext context = FacesContext.getCurrentInstance();
        Integer codigoTramite = Integer.parseInt(context.getExternalContext().getRequestParameterValuesMap().get("codigoTramite")[0]);
        mensajeTramite = "";

        for (TrTramitesVO detalleVO : tramites) {
            if (detalleVO.getTrnCodigo() == codigoTramite){
                selectedTramite = detalleVO;
                break;
            }
        }

        return "/usuarios/tramite";
    }
    
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
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
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
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
            mensajeTramite = "<br><b>Trámite enviado al Coordinador.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
        } else {
            mensajeTramite = "<br><b>Error al enviar el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
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
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
            mensajeTramite = "<br><b>Trámite devuelto al Asesor.</b><br><br>Código del trámite: "+selectedTramite.getTrnCodigo()+"<br><br>";
        } else {
            mensajeTramite = "<br><b>Error al devolver el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        return null;
    }
    
    public String agregarRecurso() {
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
        facade fachada = new facade();
        TsTramiteSenalizacionVO vo = new TsTramiteSenalizacionVO();
        
        mensajeRecurso = "";

        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        SenalizacionBean sen = (SenalizacionBean) session.getAttribute("SenalizacionBean");
        Integer codigoAccion = Integer.parseInt(facesContext.getExternalContext().getRequestParameterValuesMap().get("codigoAccion")[0]);
        
        TrTramitesVO tramite = new TrTramitesVO();
        tramite.setTrnCodigo(tramiteAgregarRecurso);
        
        SeSenalizacionVO senalizacion = new SeSenalizacionVO();
        senalizacion.setSenCodigo(sen.getSelectedSen().getSenCodigo());
        
        AcAccionVO accion = new AcAccionVO();
        accion.setAcnCodigo(codigoAccion);
        
        MunicipiosVO municipio = new MunicipiosVO();
        EmOperadorVO operador = new EmOperadorVO();
        String nombreNodo = "";
        String marcaModelo = "";
        String direccion = "";
        String observaciones = "";
        Integer radicado = Integer.parseInt(this.radicadoAgregarRecurso);
        
        switch(codigoAccion){
            case 5: //Recuperar
                municipio.setCodigoMunicipio("06053D2E");
                operador.setEmrCodigo("C0159C");
                nombreNodo = "";
                marcaModelo = "";
                direccion = "";
                observaciones = this.observacionesAgregarRecurso;
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
   
        boolean resultado = fachada.agregarRecurso(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
            mensajeRecurso = "<br><b>Recurso agregado correctamente al trámite.</b><br><br>Código del trámite: "+tramiteAgregarRecurso+"<br><br>";
        } else {
            mensajeRecurso = "<br><b>Error al agregar recurso al trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        this.setTramiteAgregarRecurso(-1);
        this.radicadoAgregarRecurso = "";
        this.observacionesAgregarRecurso = "";
        
        return null;
    }
    
    public String seleccionarCodigoDetalleTramite() {
        FacesContext facesContext = FacesContext.getCurrentInstance();
        HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(false);
        
        Integer codigo = Integer.parseInt(facesContext.getExternalContext().getRequestParameterValuesMap().get("codigoDetalleTramite")[0]);
        
        codigoDetalleTramite = codigo;
        
        return null;
    }
    
    public String eliminarRecurso() {
        facade fachada = new facade();
        TsTramiteSenalizacionVO vo = new TsTramiteSenalizacionVO();
        
        mensajeRecurso = "";
        
        System.out.println("Codigo: "+codigoDetalleTramite);
        vo.setTsnCodigo(codigoDetalleTramite);
   
        boolean resultado = fachada.eliminarRecurso(vo);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(tipoUsuario, userVO.getUsnCodigo());
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
    
    public static boolean validaNum(String Valor) {
        try {
            Integer.parseInt(Valor);
            return true;
        } catch (Exception e) {
            return false;
        }
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
    
}
