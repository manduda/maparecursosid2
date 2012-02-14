/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TaTramiteMa;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TaTramiteMaDAO {
    public static void persist(TaTramiteMa entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(TaTramiteMa entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(TaTramiteMa entity, EntityManager em){
        em.merge(entity);
    }

    public static TaTramiteMa findbyId(int tanCodigo, EntityManager em){
        return em.find(TaTramiteMa.class, tanCodigo);
    }
    
    public static boolean findIdMarcacionAbreviada(int manCodigo, EntityManager em){
        List<TaTramiteMa> tramiteMarcacionAbreviada;
        Query query = em.createQuery("SELECT t FROM TaTramiteMa t WHERE t.manCodigo.manCodigo = :marcacionAbreviada "
                + "AND t.trnCodigo.etnCodigo.etnCodigo NOT IN (5,6)");
        query.setParameter("marcacionAbreviada", manCodigo);
        tramiteMarcacionAbreviada = query.getResultList();
        if (tramiteMarcacionAbreviada.isEmpty()) {
            return false;
        } else {
            return true;
        }
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.tanCodigo) FROM TaTramiteMa t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }

    public static List<TaTramiteMa> findTramiteMarcacionAbreviada(int manCodigo, int etnCodigo, EntityManager em) {
        String searchQuery = "SELECT t FROM TaTramiteMa t where t.manCodigo.manCodigo = ?1 AND t.trnCodigo.etnCodigo.etnCodigo = ?2 "
                + "ORDER BY t.trnCodigo.trfFechaResolucion DESC";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, manCodigo);
        query.setParameter(2, etnCodigo);
        
        return query.getResultList();
    }
}
