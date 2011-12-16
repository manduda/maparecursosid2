/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.RsReservasTemporales;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author juan.loaiza
 */
public class RsReservasTemporalesDAO {
   
    public static void persist(RsReservasTemporales entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(RsReservasTemporales entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(RsReservasTemporales entity, EntityManager em){
        em.merge(entity);
    }

    public static RsReservasTemporales findbyId(int tlnCodigo, EntityManager em){
        return em.find(RsReservasTemporales.class, tlnCodigo);
    }
    
    public static int getMaxId(EntityManager em){
    Query query = em.createQuery("SELECT MAX(t.rsnCodigo) FROM RsReservasTemporales t");
    Integer n = (Integer)query.getSingleResult();
    if (n == null){
        n = 0;
    }
    return n;
    }
    
    public static List<RsReservasTemporales> getList(EntityManager em){
    Query query = em.createQuery("SELECT e FROM RsReservasTemporales e");
    return query.getResultList();
    }
}
