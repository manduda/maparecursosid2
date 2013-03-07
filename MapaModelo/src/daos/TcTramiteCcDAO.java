/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TcTramiteCc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TcTramiteCcDAO {
    public static void persist(TcTramiteCc entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TcTramiteCc entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TcTramiteCc entity, EntityManager em){
        em.merge(entity);
    }

    public static TcTramiteCc findbyId(int tcnCodigo, EntityManager em){
        return em.find(TcTramiteCc.class, tcnCodigo);
    }
    
    public static boolean findIdCodigosCortos(int ccnCodigo, EntityManager em){
        List<TcTramiteCc> tramiteCodigosCortos;
        Query query = em.createQuery("SELECT t FROM TcTramiteCc t WHERE t.ccnCodigo.ccnCodigo = :codigo "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter("codigo", ccnCodigo);
        tramiteCodigosCortos = query.getResultList();
        if (tramiteCodigosCortos.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static boolean findIdCodigosCortosSinActual(int ccnCodigo, int trnCodigo, EntityManager em){
        List<TcTramiteCc> tramiteCodigosCortos;
        Query query = em.createQuery("SELECT t FROM TcTramiteCc t WHERE t.ccnCodigo.ccnCodigo = :codigo "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)"
                + "AND t.trnCodigo.trnCodigo != :tramite");
        query.setParameter("codigo", ccnCodigo);
        query.setParameter("tramite", trnCodigo);
        tramiteCodigosCortos = query.getResultList();
        if (tramiteCodigosCortos.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tcnCodigo) FROM TcTramiteCc t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
    
    public static List<TcTramiteCc> findTramiteCodigosCortos(int ccnCodigo, int etnCodigo, EntityManager em){
        String searchQuery = "SELECT tc FROM TcTramiteCc tc where tc.ccnCodigo.ccnCodigo = ?1 AND tc.trnCodigo.etnCodigo.etnCodigo = ?2 "
                + "ORDER BY tc.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, ccnCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }
    
    public static List<TcTramiteCc> findTramiteCodigoCorto(int ccnCodigo, int etnCodigo, EntityManager em){
        String searchQuery = "SELECT tc FROM TcTramiteCc tc where tc.ccnCodigo.ccnCodigo = ?1 AND tc.trnCodigo.etnCodigo.etnCodigo = ?2 "
                + "ORDER BY tc.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, ccnCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }
    
}
