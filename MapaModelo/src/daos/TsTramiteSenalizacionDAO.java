/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TsTramiteSenalizacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author MADD
 */
public class TsTramiteSenalizacionDAO {
    public static void persist(TsTramiteSenalizacion entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TsTramiteSenalizacion entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TsTramiteSenalizacion entity, EntityManager em){
        em.merge(entity);
    }

    public static TsTramiteSenalizacion findbyId(int tsnCodigo, EntityManager em){
        return em.find(TsTramiteSenalizacion.class, tsnCodigo);
    }
    
    public static boolean findIdSenalizacion(int senCodigo, EntityManager em){
        List<TsTramiteSenalizacion> tramiteSenalizacion;
        Query query = em.createQuery("SELECT t FROM TsTramiteSenalizacion t WHERE t.senCodigo.senCodigo = :senalizacion "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter("senalizacion", senCodigo);
        tramiteSenalizacion = query.getResultList();
        if (tramiteSenalizacion.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tsnCodigo) FROM TsTramiteSenalizacion t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
    
    public static List<TsTramiteSenalizacion> findTramiteSenalizacion(int senCodigo, int etnCodigo, EntityManager em){
        String searchQuery = "SELECT ts FROM TsTramiteSenalizacion ts where ts.senCodigo.senCodigo = ?1 AND ts.acnCodigo.acnCodigo = ?2 "
                + "ORDER BY ts.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, senCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }
    
}
