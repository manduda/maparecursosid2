/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import inicio.LoginFilter;
import inicio.UserBean;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import vo.GtGestionTramiteVO;
import vo.TrTramitesVO;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "TramiteBean")
@RequestScoped
public class TramiteBean {
    private List<TrTramitesVO> tramites = new ArrayList<TrTramitesVO>();
    private TrTramitesVO selectedTramite;

    /** Creates a new instance of TramiteBean */
    public TramiteBean() {
        facade fachada = new facade();
        UsUsuariosVO userVO = new UsUsuariosVO();
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
    
}
