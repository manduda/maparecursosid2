/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.UsUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author miguel.duran
 */
public class UsUsuariosDAO {
    public static UsUsuarios cargar(String usuario, String contrasena, EntityManager em) {
        
        Query query = em.createQuery("SELECT u FROM UsUsuarios u "
                + "WHERE u.codigoSIUST.login = :usuario AND u.codigoSIUST.password = :contrasena "
                + "ORDER BY u.usnCodigo DESC");
        
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", contrasena);
        query.setMaxResults(1);
        
        UsUsuarios u = null;
        u = (UsUsuarios)query.getSingleResult();
        
        return u;
    }
    
    public static List<UsUsuarios> getList(EntityManager em){
        Query query = em.createQuery("SELECT u FROM UsUsuarios u WHERE u.usnEstado = 1");
        return query.getResultList();
    }
    
}
