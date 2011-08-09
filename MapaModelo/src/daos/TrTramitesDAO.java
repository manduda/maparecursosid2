/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TrTramites;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TrTramitesDAO {
    public static void persist(TrTramites entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TrTramites entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TrTramites entity, EntityManager em){
        em.merge(entity);
    }

    public static TrTramites findbyId(int trnCodigo, EntityManager em){
        return em.find(TrTramites.class, trnCodigo);
    }
    
    public static List<TrTramites> getTramitesAsesor(int usnCodigo, EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.usnCodigo.usnCodigo.userCode = :usuario "
                + "AND e.etnCodigo.etnCodigo IN (1,3) ORDER BY e.trfFecha DESC");
        query.setParameter("usuario", usnCodigo);
        return query.getResultList();
    }
    
    public static List<TrTramites> getTramitesCreados(int usnCodigo, EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.usnCodigo.usnCodigo.userCode = :usuario "
                + "AND e.etnCodigo.etnCodigo = 1");
        query.setParameter("usuario", usnCodigo);
        return query.getResultList();
    }
    
    public static List<TrTramites> getTramitesDevueltos(int usnCodigo, EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.usnCodigo.usnCodigo.userCode = :ususario "
                + "AND e.etnCodigo.etnCodigo = 3");
        query.setParameter("ususario", usnCodigo);
        return query.getResultList();
    }
    
    public static List<TrTramites> getTramitesEnviados(EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.etnCodigo.etnCodigo = 2");
        return query.getResultList();
    }
    
    public static List<TrTramites> getTramitesAprobados(EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.etnCodigo.etnCodigo = 4");
        return query.getResultList();
    }
    
    public static List<TrTramites> getTramitesTerminados(EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.etnCodigo.etnCodigo = 5");
        return query.getResultList();
    }
    
}
