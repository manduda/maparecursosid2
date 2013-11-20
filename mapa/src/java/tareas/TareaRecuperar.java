/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package tareas;

import facade.facade;
import inicio.ConfiguracionBean;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.OutputStreamWriter;
import java.io.UnsupportedEncodingException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;
import java.util.Properties;
import java.util.Timer;
import java.util.TimerTask;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import utils.Correo;
import utils.Functions;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
public class TareaRecuperar extends TimerTask implements ServletContextListener {
    private Timer timer;
    private String realPath;
    
    @Override
    public void contextInitialized(ServletContextEvent evt) {
        realPath = evt.getServletContext().getRealPath("/"); 
        // Iniciamos el timer
        timer = new Timer();
        Date fecha = getTime();
        timer.scheduleAtFixedRate(this, fecha, ONCE_PER_DAY);
    }
 
    @Override
    public void contextDestroyed(ServletContextEvent evt) {
        timer.cancel();
    }
    
    @Override
    public void run() {
        facade fachada = new facade();
        Boolean resultado = fachada.recuperarReservaTemporal();
        
        if (!resultado) {
            List<UsUsuariosVO> administradores = fachada.getUsuarios(1);
            for(UsUsuariosVO u: administradores){
                String mensaje = "Error al realizar la tarea de recuperación."+ "<br/><br/>";
                helperTareas.enviarCorreo(u.getCodigoSIUST().getEmail(), mensaje, "Error tarea recuperación");
            }
        }
        
    }
    
    private final static long ONCE_PER_DAY = 1000*60*60*24;
    private final static int HOUR = 0;
    private final static int MINUTES = 30;
    
    private static Date getTime(){
        Calendar fecha = new GregorianCalendar();
        //fecha.add(Calendar.DATE, 1);
        Calendar result = new GregorianCalendar(
            fecha.get(Calendar.YEAR),
            fecha.get(Calendar.MONTH),
            fecha.get(Calendar.DATE),
            HOUR,
            MINUTES
        );
        return result.getTime();
    }
    
}
