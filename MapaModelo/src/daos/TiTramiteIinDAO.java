/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TiTramiteIin;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TiTramiteIinDAO {
    public static void persist(TiTramiteIin entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TiTramiteIin entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TiTramiteIin entity, EntityManager em){
        em.merge(entity);
    }

    public static TiTramiteIin findbyId(int tinCodigo, EntityManager em){
        return em.find(TiTramiteIin.class, tinCodigo);
    }
    
    public static boolean findIdCodigosIin(int cinCodigo, EntityManager em){
        List<TiTramiteIin> tramiteCodigosIin;
        Query query = em.createQuery("SELECT t FROM TiTramiteIin t WHERE t.cinCodigo.cinCodigo = :codigosIin "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter("codigosIin", cinCodigo);
        tramiteCodigosIin = query.getResultList();
        if (tramiteCodigosIin.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tinCodigo) FROM TiTramiteIin t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }

    public static List<TiTramiteIin> findTramiteCodigosIin(int cinCodigo, int etnCodigo, EntityManager em) {
        String searchQuery = "SELECT t FROM TiTramiteIin t where t.cinCodigo.cinCodigo = ?1 AND t.trnCodigo.etnCodigo.etnCodigo = ?2 "
                + "ORDER BY t.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, cinCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }
}
