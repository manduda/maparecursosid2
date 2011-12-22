/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TnTramiteNumeracion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TnTramiteNumeracionDAO {
    public static void persist(TnTramiteNumeracion entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TnTramiteNumeracion entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TnTramiteNumeracion entity, EntityManager em){
        em.merge(entity);
    }

    public static TnTramiteNumeracion findbyId(int tnnCodigo, EntityManager em){
        return em.find(TnTramiteNumeracion.class, tnnCodigo);
    }
    
    public static boolean findNumeracion(int ndc, int inicio, int fin, EntityManager em){
        List<TnTramiteNumeracion> tramiteNumeracion;
        Query query = em.createQuery("SELECT t FROM TnTramiteNumeracion t "
                + "WHERE t.ndnCodigo.ndnCodigo = ?1 "
                + "AND t.tnnInicio >= ?2 AND t.tnnFin <= ?3 "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter(1, ndc);
        query.setParameter(2, inicio);
        query.setParameter(3, fin);
        tramiteNumeracion = query.getResultList();
        if (tramiteNumeracion.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tnnCodigo) FROM TnTramiteNumeracion t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
    
    /*public static List<TsTramiteSenalizacion> findTramiteSenalizacion(int senCodigo, int etnCodigo, EntityManager em){
        String searchQuery = "SELECT ts FROM TsTramiteSenalizacion ts where ts.senCodigo.senCodigo = ?1 AND ts.trnCodigo.etnCodigo.etnCodigo = ?2 "
                + "ORDER BY ts.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, senCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }*/
}
