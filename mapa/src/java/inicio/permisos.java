/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package inicio;

/**
 *
 * @author miguel.duran
 */
public class permisos {
    
    // --- OPCIONES AGREGAR RECURSOS DE UN TRAMITE
    private boolean recuperar = false;
    private boolean preasignar = false;
    private boolean reservar = false;
    private boolean liberar = false;
    
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
        
    private boolean transferirRecursos = false;
    
    private boolean administrarModulos = false;
        
    public permisos() {
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

    public boolean isAdministrarModulos() {
        return administrarModulos;
    }

    public void setAdministrarModulos(boolean administrarModulos) {
        this.administrarModulos = administrarModulos;
    }
    
}
