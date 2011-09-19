/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TlTramiteLd;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TlTramiteLdDAO {
    public static void persist(TlTramiteLd entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TlTramiteLd entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TlTramiteLd entity, EntityManager em){
        em.merge(entity);
    }

    public static TlTramiteLd findbyId(int tlnCodigo, EntityManager em){
        return em.find(TlTramiteLd.class, tlnCodigo);
    }
    
    public static boolean findIdCodigosLd(int clnCodigo, EntityManager em){
        List<TlTramiteLd> tramiteCodigosLd;
        Query query = em.createQuery("SELECT t FROM TlTramiteLd t WHERE t.clnCodigo.clnCodigo = :codigosld "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter("codigosld", clnCodigo);
        tramiteCodigosLd = query.getResultList();
        if (tramiteCodigosLd.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tlnCodigo) FROM TlTramiteLd t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
}
