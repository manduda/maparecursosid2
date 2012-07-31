/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

//import javax.faces.bean.ManagedBean;

import java.io.IOException;
import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.servlet.http.HttpSessionBindingEvent;
import javax.servlet.http.HttpSessionBindingListener;
import vo.UsUsuariosVO;

//import javax.faces.bean.SessionScoped;

/**
 *
 * @author MADD
 */
//@ManagedBean(name = "UserBean")
//@SessionScoped
public class UserBean implements Serializable, HttpSessionBindingListener {
    private static final long serialVersionUID = 1L;
    
    // All logins.
    private static Map<UserBean, HttpSession> logins = new HashMap<UserBean, HttpSession>();
    
    private UsUsuariosVO userVO = new UsUsuariosVO();
    private Boolean login = false;
    
    // --- OPCIONES AGREGAR RECURSOS DE UN TRAMITE
    private boolean recuperar = false;
    private boolean preasignar = false;
    private boolean reservar = false;
    private boolean liberar = false;
    private boolean editarCodigos1xy = false;
    
    // --- OPCIONES ELIMINAR RECURSOS DE UN TRAMITE
    private boolean eliminarRecurso = false;
    
    // --- OPCIONES GESTIÓN TRÁMITE
    private boolean crearTramite = false;
    private boolean enviarTramite = false;
    private boolean archivarTramite = false;
    private boolean devolverTramite = false;
    private boolean aprobarTramite = false;
    private boolean terminarTramite = false;
    private boolean cambiarOperadorTramite = false;
    private boolean cambiarUsuarioTramite = false;
    
    private boolean consultarTramite = false;
        
    private boolean transferirRecursos = false;
    private boolean administrarUsuarios = false;
    private boolean administrarModulos = false;
    
    private boolean asignarTramites = false;
    

    public UserBean() {
    }

    @Override
    public void valueBound(HttpSessionBindingEvent event) {
        HttpSession session = logins.remove(this);
        if (session != null) {
            session.invalidate();
        }
        logins.put(this, event.getSession());
    }

    @Override
    public void valueUnbound(HttpSessionBindingEvent event) {
        logins.remove(this);
    }
    
    @Override
    public boolean equals(Object other) {
        return (other instanceof UserBean) && (userVO.getCodigoSIUST() != null) ? userVO.getCodigoSIUST().getLogin().equals(((UserBean) other).getUserVO().getCodigoSIUST().getLogin()) : (other == this);
    }

    @Override
    public int hashCode() {
        return (userVO.getCodigoSIUST() != null) ? (this.getClass().hashCode() + userVO.getCodigoSIUST().getLogin().hashCode()) : super.hashCode();
    }

    public UsUsuariosVO getUserVO() {
        return userVO;
    }

    public void setUserVO(UsUsuariosVO userVO) {
        this.userVO = userVO;
    }

    public Boolean getLogin() {
        return login;
    }

    public void setLogin(Boolean login) {
        this.login = login;
    }

    public boolean isAprobarTramite() {
        return aprobarTramite;
    }

    public void setAprobarTramite(boolean aprobarTramite) {
        this.aprobarTramite = aprobarTramite;
    }

    public boolean isArchivarTramite() {
        return archivarTramite;
    }

    public void setArchivarTramite(boolean archivarTramite) {
        this.archivarTramite = archivarTramite;
    }

    public boolean isCambiarOperadorTramite() {
        return cambiarOperadorTramite;
    }

    public void setCambiarOperadorTramite(boolean cambiarOperadorTramite) {
        this.cambiarOperadorTramite = cambiarOperadorTramite;
    }

    public boolean isCrearTramite() {
        return crearTramite;
    }

    public void setCrearTramite(boolean crearTramite) {
        this.crearTramite = crearTramite;
    }

    public boolean isDevolverTramite() {
        return devolverTramite;
    }

    public void setDevolverTramite(boolean devolverTramite) {
        this.devolverTramite = devolverTramite;
    }

    public boolean isEliminarRecurso() {
        return eliminarRecurso;
    }

    public void setEliminarRecurso(boolean eliminarRecurso) {
        this.eliminarRecurso = eliminarRecurso;
    }

    public boolean isEnviarTramite() {
        return enviarTramite;
    }

    public void setEnviarTramite(boolean enviarTramite) {
        this.enviarTramite = enviarTramite;
    }

    public boolean isLiberar() {
        return liberar;
    }

    public void setLiberar(boolean liberar) {
        this.liberar = liberar;
    }

    public boolean isPreasignar() {
        return preasignar;
    }

    public void setPreasignar(boolean preasignar) {
        this.preasignar = preasignar;
    }

    public boolean isRecuperar() {
        return recuperar;
    }

    public void setRecuperar(boolean recuperar) {
        this.recuperar = recuperar;
    }

    public boolean isReservar() {
        return reservar;
    }

    public void setReservar(boolean reservar) {
        this.reservar = reservar;
    }

    public boolean isTerminarTramite() {
        return terminarTramite;
    }

    public void setTerminarTramite(boolean terminarTramite) {
        this.terminarTramite = terminarTramite;
    }

    public boolean isTransferirRecursos() {
        return transferirRecursos;
    }

    public void setTransferirRecursos(boolean transferirRecursos) {
        this.transferirRecursos = transferirRecursos;
    }

    public boolean isAdministrarUsuarios() {
        return administrarUsuarios;
    }

    public void setAdministrarUsuarios(boolean administrarUsuarios) {
        this.administrarUsuarios = administrarUsuarios;
    }

    public boolean isConsultarTramite() {
        return consultarTramite;
    }

    public void setConsultarTramite(boolean consultarTramite) {
        this.consultarTramite = consultarTramite;
    }

    public boolean isCambiarUsuarioTramite() {
        return cambiarUsuarioTramite;
    }

    public void setCambiarUsuarioTramite(boolean cambiarUsuarioTramite) {
        this.cambiarUsuarioTramite = cambiarUsuarioTramite;
    }

    public static Map<UserBean, HttpSession> getLogins() {
        return logins;
    }

    public static void setLogins(Map<UserBean, HttpSession> logins) {
        UserBean.logins = logins;
    }

    public boolean isEditarCodigos1xy() {
        return editarCodigos1xy;
    }

    public void setEditarCodigos1xy(boolean editarCodigos1xy) {
        this.editarCodigos1xy = editarCodigos1xy;
    }

    public boolean isAdministrarModulos() {
        return administrarModulos;
    }

    public void setAdministrarModulos(boolean administrarModulos) {
        this.administrarModulos = administrarModulos;
    }

    public boolean isAsignarTramites() {
        return asignarTramites;
    }

    public void setAsignarTramites(boolean asignarTramites) {
        this.asignarTramites = asignarTramites;
    }

}
