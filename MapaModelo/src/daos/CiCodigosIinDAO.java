/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.CiCodigosIin;
import entities.EmOperador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class CiCodigosIinDAO {
    public static void persist(CiCodigosIin entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(CiCodigosIin entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(CiCodigosIin entity, EntityManager em){
        em.merge(entity);
    }

    public static CiCodigosIin findbyId(int cinCodigo, EntityManager em){
        return em.find(CiCodigosIin.class, cinCodigo);
    }
    
    public static List<CiCodigosIin> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM CiCodigosIin e");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM CI_CODIGOS_IIN e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.ciCodigosIinCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<CiCodigosIin> cargarCodigosIin(int first, int max, String operador, int codigoIin, int estado, EntityManager em){
        List<CiCodigosIin> codigosIin = new ArrayList<CiCodigosIin>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM CiCodigosIin c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoIin != -1) {
            searchQuery.append("AND c.cinCodigoIin LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        searchQuery.append("ORDER BY c.cinCodigoIin ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoIin != -1) {
            query.setParameter(2, codigoIin + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        codigosIin = query.getResultList();        
        return codigosIin;
    }
    
    public static int countCargarCodigosIin(String operador, int codigoIin, int estado, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM CiCodigosIin c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoIin != -1) {
            searchQuery.append("AND c.cinCodigoIin LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoIin != -1) {
            query.setParameter(2, codigoIin + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
}
