/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.ClCodigosLd;
import entities.EmOperador;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class ClCodigosLdDAO {
    public static void persist(ClCodigosLd entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(ClCodigosLd entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(ClCodigosLd entity, EntityManager em){
        em.merge(entity);
    }

    public static ClCodigosLd findbyId(int clnCodigo, EntityManager em){
        return em.find(ClCodigosLd.class, clnCodigo);
    }
    
    public static List<ClCodigosLd> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM ClCodigosLd e");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM CL_CODIGOS_LD e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.clCodigosLdCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<ClCodigosLd> cargarCodigosLd(int first, int max, String operador, int codigoLd, int estado, EntityManager em){
        List<ClCodigosLd> codigosld = new ArrayList<ClCodigosLd>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM ClCodigosLd c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoLd != -1) {
            searchQuery.append("AND c.clnCodigoLd LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        searchQuery.append("ORDER BY c.clnCodigoLd ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoLd != -1) {
            query.setParameter(2, codigoLd + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        codigosld = query.getResultList();        
        return codigosld;
    }
    
    public static int countCargarCodigosLd(String operador, int codigoLd, int estado, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM ClCodigosLd c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoLd != -1) {
            searchQuery.append("AND c.clnCodigoLd LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoLd != -1) {
            query.setParameter(2, codigoLd + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static void transferirCodigosLd(String operadorOrigen, String operadorDestino, EntityManager em){

        String searchQuery = "UPDATE CL_CODIGOS_LD SET SK_EMPRESA_CODE = ?1 WHERE SK_EMPRESA_CODE = ?2";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, operadorDestino);
        query.setParameter(2, operadorOrigen);
        
        query.executeUpdate();
        
    }
    
}
