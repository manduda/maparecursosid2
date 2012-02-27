/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import java.util.Properties;
import javax.activation.DataHandler;
import javax.activation.DataSource;
import javax.activation.FileDataSource;
import javax.faces.context.FacesContext;
import javax.mail.BodyPart;
import javax.mail.Message;
import javax.mail.Multipart;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;
import javax.servlet.ServletContext;

/**
 *
 * @author miguel.duran
 */
public class Correo {
    private String mensaje;
    private String correoFrom;
    private String correoTo;
    private String servidor;
    private String puerto;
    private String asunto;
    private String password;
    private String firma;
    private String rutaLogo; 
    
    public Correo() {
    }

    public void send() {
        // Propiedades de la conexión
        Properties props = new Properties();
        props.setProperty("mail.smtp.host", this.servidor);
        props.setProperty("mail.smtp.starttls.enable", "true");
        props.setProperty("mail.smtp.port", this.puerto);
        props.setProperty("mail.smtp.user", this.correoFrom);
        props.setProperty("mail.smtp.auth", "true");
        
        // Preparamos la sesion
        Session session = Session.getDefaultInstance(props);
        //session.setDebug(true);
        
        try {
            // Construimos el mensaje
            MimeMessage message = new MimeMessage(session);
            message.setFrom(new InternetAddress(this.correoFrom));
            message.addRecipient(
                Message.RecipientType.TO,
                new InternetAddress(this.correoTo));
            message.setSubject(this.asunto);
            //message.setContent(this.mensaje, "text/html");
            //message.setText(this.mensaje);
            
            Multipart multipart = new MimeMultipart("related");
            
            // The HTML
            BodyPart htmlPart = new MimeBodyPart();
            /*String firma = "<p><strong><b>MAPA DE RECURSOS DE IDENTIFICACIÓN</b></strong><br/>"
                    + "Comisión de Regulación de Comunicaciones<br/>"
                    + "Cra 7 No 77-07 Piso 9 Bogotá, Colombia<br/>"
                    + "Teléfono: +57(1)3198300<br/>"
                    + "Bogotá, Colombia<br/>"
                    + "Página web: <a href=\"http://www.crcom.gov.co\" title=\"http://www.crom.gov.co\" target=\"_blank\">http://www.crcom.gov.co</a></p>";
             */

            htmlPart.setContent("<html><body><p>" + this.mensaje + "</p>" + this.getFirma()
                    + "<img src=\"cid:imgPart\"/><br/></body></html>", "text/html");
            multipart.addBodyPart(htmlPart);
            
            // The Images
            BodyPart imgPart=new MimeBodyPart();

            // Loading the image
            DataSource ds=new FileDataSource(this.rutaLogo);
            imgPart.setDataHandler(new DataHandler(ds));

            //Setting the header
            imgPart.setHeader("Content-ID","<imgPart>");

            multipart.addBodyPart(imgPart);

            // attaching the multi-part to the message
            message.setContent(multipart);
            

            // Lo enviamos.
            Transport t = session.getTransport("smtp");
            t.connect(this.correoFrom, this.password);
            t.sendMessage(message, message.getAllRecipients());
            
            // Cierre.
            t.close();
            
        }catch (Exception e){
            System.out.println(e);
        }
    }

    public String getAsunto() {
        return asunto;
    }

    public void setAsunto(String asunto) {
        this.asunto = asunto;
    }

    public String getCorreoFrom() {
        return correoFrom;
    }

    public void setCorreoFrom(String correoFrom) {
        this.correoFrom = correoFrom;
    }

    public String getCorreoTo() {
        return correoTo;
    }

    public void setCorreoTo(String correoTo) {
        this.correoTo = correoTo;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPuerto() {
        return puerto;
    }

    public void setPuerto(String puerto) {
        this.puerto = puerto;
    }

    public String getServidor() {
        return servidor;
    }

    public void setServidor(String servidor) {
        this.servidor = servidor;
    }

    public String getRutaLogo() {
        return rutaLogo;
    }

    public void setRutaLogo(String rutaLogo) {
        this.rutaLogo = rutaLogo;
    }

    public String getFirma() {
        return firma;
    }

    public void setFirma(String firma) {
        this.firma = firma;
    }

}
