/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.NdNdc;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class NdNdcDAO {
    public static void persist(NdNdc entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(NdNdc entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(NdNdc entity, EntityManager em){
        em.merge(entity);
    }

    public static NdNdc findbyId(BigDecimal ndnCodigo, EntityManager em){
        return em.find(NdNdc.class, ndnCodigo);
    }
    
    public static List<NdNdc> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM NdNdc e ORDER BY e.ndtNombre ASC");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM NdNdc e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
}
