/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tareas;

import facade.facade;
import javax.faces.context.FacesContext;
import javax.servlet.ServletContext;
import utils.Correo;

/**
 *
 * @author miguel.duran
 */
public class helperTareas {
    
    public static void enviarCorreo(String usuarioTo, String mensaje, String Asunto){
        facade fachada = new facade();
        
        String servidorCorreo = fachada.buscarConfiguracionNombre("servidorCorreo").getCotValor();
        String correoAplicacion = fachada.buscarConfiguracionNombre("correoAplicacion").getCotValor();
        String passwordCorreo = fachada.buscarConfiguracionNombre("passwordCorreo").getCotValor();
        String puertoServidor = fachada.buscarConfiguracionNombre("puertoServidor").getCotValor();
        String firmaCorreo = fachada.buscarConfiguracionNombre("firmaCorreo").getCotValor();
        String logoCorreo = fachada.buscarConfiguracionNombre("logoCorreo").getCotValor();
        
        ServletContext ctx = (ServletContext) FacesContext.getCurrentInstance().getExternalContext().getContext();
        String deploymentDirectoryPath = ctx.getRealPath("/");
        String ruta = deploymentDirectoryPath + fachada.buscarConfiguracionNombre("rutaContexto").getCotValor();
        
        Correo correo = new Correo();
        correo.setServidor(servidorCorreo);
        correo.setPuerto(puertoServidor);
        correo.setCorreoFrom(correoAplicacion);
        correo.setPassword(passwordCorreo);
        correo.setAsunto(Asunto);
        correo.setMensaje(mensaje);
        correo.setCorreoTo(usuarioTo);
        correo.setFirma(firmaCorreo);
        correo.setRutaLogo(ruta+logoCorreo);
        correo.send();
    }
    
    
}
