/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.UsUsuarios;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author miguel.duran
 */
public class UsUsuariosDAO {
    public static UsUsuarios cargar(String usuario, String contrasena, EntityManager em) {
        
        Query query = em.createQuery("SELECT u FROM UsUsuarios u "
                + "WHERE u.usnCodigo.login = :usuario AND u.usnCodigo.password = :contrasena");
        
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", contrasena);
        
        UsUsuarios u = null;
        u = (UsUsuarios)query.getSingleResult();
        
        return u;
    }
    
}
