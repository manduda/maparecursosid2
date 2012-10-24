/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ApplicationScoped;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "Validador",eager=true)
@ApplicationScoped
public class Validador {

    /** Creates a new instance of Validador */
    public Validador() {
    }
    
    public void enteroPositivo(FacesContext context, UIComponent uIComponent, Object object) throws ValidatorException {
        Integer numero = (Integer)object;
        if (numero <= 0) {
            FacesMessage Mensaje = new FacesMessage();
            Mensaje.setDetail("Debes ingresar un número entero mayor a cero");
            Mensaje.setSummary("Error");
            Mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(Mensaje);
        }
    }
    
    public void enteroMayorIgualCero(FacesContext context, UIComponent uIComponent, Object object) throws ValidatorException {
        FacesMessage Mensaje = new FacesMessage();
        try {
            String cadena = (String)object;
            Integer numero = Integer.parseInt(cadena);
            if (numero < 0) {
                Mensaje.setDetail("Debes ingresar un número entero mayor o igual a cero");
                Mensaje.setSummary("Error");
                Mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
                throw new ValidatorException(Mensaje);
            }
        } catch (NumberFormatException e) {
            Mensaje.setDetail("Debes ingresar un número entero");
            Mensaje.setSummary("Error");
            Mensaje.setSeverity(FacesMessage.SEVERITY_ERROR);
            throw new ValidatorException(Mensaje);
        }
        
    }
}
