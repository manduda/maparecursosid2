/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.GtGestionTramite;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class GtGestionTramiteDAO {
    public static void persist(GtGestionTramite entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(GtGestionTramite entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(GtGestionTramite entity, EntityManager em){
        em.merge(entity);
    }

    public static GtGestionTramite findbyId(int gtnCodigo, EntityManager em){
        return em.find(GtGestionTramite.class, gtnCodigo);
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.gtnCodigo) FROM GtGestionTramite t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
}
