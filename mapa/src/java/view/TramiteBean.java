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
import vo.EmOperadorVO;
import vo.EtEstadoTramiteVO;
import vo.GtGestionTramiteVO;
import vo.TrTramitesVO;
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
    private String operadorCrearTramite;
    private UsUsuariosVO userVO;
    private String mensajeCrearTramite;

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
            tramites = fachada.cargarTramites(6, userVO.getUsnCodigo());
            //System.out.print("user: "+userVO.getUsnCodigo());
        }
        
        //FacesContext facesContext = FacesContext.getCurrentInstance();
        //HttpSession session = (HttpSession) facesContext.getExternalContext().getSession(true);
        //userVO = (UsUsuariosVO)facesContext.getExternalContext().getSessionMap().get("UserBean");
        //System.out.print("user: "+userVO);
        //tramites = fachada.cargarTramites(1, userVO.getUsnCodigo());
        //tramites = fachada.cargarTramites(6, 1);
    }
    
    public String detalleTramite() {
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
        
        return "usuarios/crearTramite";
    }
    
    public String crearTramite() {
        facade fachada = new facade();

        TrTramitesVO vo = new TrTramitesVO();
        
        EmOperadorVO operador = new EmOperadorVO();
        operador.setEmrCodigo(operadorCrearTramite);
        
        EtEstadoTramiteVO estado = new EtEstadoTramiteVO();
        estado.setEtnCodigo(1);
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(userVO.getUsnCodigo());
        
        Date fecha = new Date();
        
        vo.setEmrCodigo(operador);
        vo.setEtnCodigo(estado);
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
   
        boolean resultado = fachada.crearTramite(vo);
        
        System.out.println("resultado: "+resultado);
        
        if (resultado == true){
            tramites = fachada.cargarTramites(6, userVO.getUsnCodigo());
            mensajeCrearTramite = "Trámite creado.<br><br>Código del trámite: "+tramites.get(0).getTrnCodigo();
        } else {
            mensajeCrearTramite = "<b>Error al crear el trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador";
        }
        
        return null;
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
    
}
