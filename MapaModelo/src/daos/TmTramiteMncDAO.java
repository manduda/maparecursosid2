/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TmTramiteMnc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TmTramiteMncDAO {
    public static void persist(TmTramiteMnc entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TmTramiteMnc entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TmTramiteMnc entity, EntityManager em){
        em.merge(entity);
    }

    public static TmTramiteMnc findbyId(int tmnCodigo, EntityManager em){
        return em.find(TmTramiteMnc.class, tmnCodigo);
    }
    
    public static boolean findIdMnc(int cdnCodigo, EntityManager em){
        List<TmTramiteMnc> tramiteMnc;
        Query query = em.createQuery("SELECT t FROM TmTramiteMnc t WHERE t.cdnCodigo.cdnCodigo = :mnc "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter("mnc", cdnCodigo);
        tramiteMnc = query.getResultList();
        if (tramiteMnc.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tmnCodigo) FROM TmTramiteMnc t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }

    public static List<TmTramiteMnc> findTramiteCodigosMnc(int cdnCodigo, int etnCodigo, EntityManager em) {
        String searchQuery = "SELECT t FROM TmTramiteMnc t where t.cdnCodigo.cdnCodigo = ?1 AND t.trnCodigo.etnCodigo.etnCodigo = ?2 "
                + "ORDER BY t.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, cdnCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }
}
