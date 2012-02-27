/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TkTramiteNrn;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TkTramiteNrnDAO {
    
    public static void persist(TkTramiteNrn entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TkTramiteNrn entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TkTramiteNrn entity, EntityManager em){
        em.merge(entity);
    }

    public static TkTramiteNrn findbyId(int tknCodigo, EntityManager em){
        return em.find(TkTramiteNrn.class, tknCodigo);
    }
    
    public static boolean findIdCodigosNrn(int nrnCodigo, EntityManager em){
        List<TkTramiteNrn> tramiteCodigosNrn;
        Query query = em.createQuery("SELECT t FROM TkTramiteNrn t WHERE t.nrnCodigo.nrnCodigo = :codigosNrn "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter("codigosNrn", nrnCodigo);
        tramiteCodigosNrn = query.getResultList();
        if (tramiteCodigosNrn.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tknCodigo) FROM TkTramiteNrn t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }

    public static List<TkTramiteNrn> findTramiteCodigosNrn(int nrnCodigo, int etnCodigo, EntityManager em) {
        String searchQuery = "SELECT t FROM TkTramiteNrn t where t.nrnCodigo.nrnCodigo = ?1 AND t.trnCodigo.etnCodigo.etnCodigo = ?2 "
                + "ORDER BY t.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, nrnCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }
    
}
