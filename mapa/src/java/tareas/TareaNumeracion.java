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
public class TareaNumeracion extends TimerTask implements ServletContextListener {
    private Timer timer;
    private String contextPath;
    private String realPath;
    
    private String servidorCorreo;
    private String correoAplicacion;
    private String passwordCorreo;
    private String puertoServidor;
    private String firmaCorreo;
    private String logoCorreo;
 
    @Override
    public void contextInitialized(ServletContextEvent evt) {
        contextPath = evt.getServletContext().getContextPath();
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
        boolean error = false;
        try {
            boolean cargarDatos = false;
            String pFile = realPath + "exports\\numeracion.csv";
            String pZipFile = realPath + "exports\\numeracion.zip";
            
            //Comprobar archivo existe
            File archivo = new File(pZipFile);
            if (!archivo.exists()){
                cargarDatos = true;
            } else {
                long ms = archivo.lastModified();
                
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                Date fechaArchivo = sdf.parse(sdf.format(new Date(ms)));
                Date ahora = sdf.parse(sdf.format(new Date()));
                
                if (fechaArchivo.before(ahora)) {
                    cargarDatos = true;
                } else {
                    cargarDatos = false;
                }
                
            }
            
            //Generar archivo csv
            if (cargarDatos) {
                facade fachada = new facade();
                
                List<String> numeracion = fachada.exportarNumeracionCSV();
                
                BufferedWriter bw = new BufferedWriter(new OutputStreamWriter(new FileOutputStream(pFile), "ISO-8859-1"));

                for(String s : numeracion){
                    bw.write(s);
                    bw.newLine();
                }

                bw.flush();
                bw.close();
                
                //Generar archivo zip
                Functions.zippear(pFile,pZipFile,"numeracion.csv");

                //Borrar archivo csv
                archivo = new File(pFile);
                archivo.delete();
            }
            
        } catch (UnsupportedEncodingException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "1 - Error al descargar archivo - mapa completo", e);
            error = true;
        } catch (FileNotFoundException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "2 - Error al descargar archivo - mapa completo", e);
            error = true;
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "3 - Error al descargar archivo - mapa completo", e);
            error = true;
        } catch (java.lang.Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "4 - Error al descargar archivo - mapa completo", e);
            error = true;
        } finally {
            if (error) {
                facade fachada = new facade();
                List<UsUsuariosVO> administradores = fachada.getUsuarios(1);
                for(UsUsuariosVO u: administradores){
                    String mensaje = "Error al generar el archivo del mapa completo " + new Date() + "<br/><br/>";
                   enviarCorreo(u.getCodigoSIUST().getEmail(), mensaje, "Error");
                }
            }
        }
    }
    
    private final static long ONCE_PER_DAY = 1000*60*60*24;
    private final static int HOUR = 0;
    private final static int MINUTES = 0;
    
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
    
    public void enviarCorreo(String usuarioTo, String mensaje, String Asunto){
        confguracionCorreo();
        Correo correo = new Correo();
        correo.setServidor(servidorCorreo);
        correo.setPuerto(puertoServidor);
        correo.setCorreoFrom(correoAplicacion);
        correo.setPassword(passwordCorreo);
        correo.setAsunto(Asunto);
        correo.setMensaje(mensaje);
        correo.setCorreoTo(usuarioTo);
        correo.setFirma(firmaCorreo);
        correo.setRutaLogo(realPath+logoCorreo);
        correo.send();
    }
    
    private void confguracionCorreo() {
        Properties properties = new Properties();
        try {
            InputStream input = ConfiguracionBean.class.getResourceAsStream("../properties/Correo.properties");
            properties.load(input);
            input.close();
            
            servidorCorreo = properties.getProperty("servidorCorreo");
            correoAplicacion = properties.getProperty("correoAplicacion");
            passwordCorreo = properties.getProperty("passwordCorreo");
            puertoServidor = properties.getProperty("puertoServidor");
            firmaCorreo = properties.getProperty("firmaCorreo");
            logoCorreo = properties.getProperty("logoCorreo");
            
        } catch (IOException e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Archivo Correo.properties no encontrado", e);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error inicializando el builder de parámetros del correo", e);
        }
    }
            
            
            
}
