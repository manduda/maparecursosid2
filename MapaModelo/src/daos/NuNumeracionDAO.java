/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.NuNumeracion;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class NuNumeracionDAO {
    public static void persist(NuNumeracion entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(NuNumeracion entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(NuNumeracion entity, EntityManager em){
        em.merge(entity);
    }

    public static NuNumeracion findbyId(BigDecimal clnCodigo, EntityManager em){
        return em.find(NuNumeracion.class, clnCodigo);
    }
    
    public static List<NuNumeracion> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM NuNumeracion e ORDER BY e.nunInicio ASC");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM NuNumeracion e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
}
