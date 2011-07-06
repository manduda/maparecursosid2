/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.ClCodigosLd;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class ClCodigosLdDAO {
    public static void persist(ClCodigosLd entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(ClCodigosLd entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(ClCodigosLd entity, EntityManager em){
        em.merge(entity);
    }

    public static ClCodigosLd findbyId(BigDecimal clnCodigo, EntityManager em){
        return em.find(ClCodigosLd.class, clnCodigo);
    }
    
    public static List<ClCodigosLd> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM ClCodigosLd e");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM CL_CODIGOS_LD e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
}
