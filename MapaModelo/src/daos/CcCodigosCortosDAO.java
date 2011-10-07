/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.CcCodigosCortos;
import entities.EmOperador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class CcCodigosCortosDAO {
    public static void persist(CcCodigosCortos entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(CcCodigosCortos entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(CcCodigosCortos entity, EntityManager em){
        em.merge(entity);
    }

    public static CcCodigosCortos findbyId(int ccnCodigo, EntityManager em){
        return em.find(CcCodigosCortos.class, ccnCodigo);
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.ccCodigosCortosCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<CcCodigosCortos> cargarCodigosCortos(int first, int max, String operador, int modalidad, int codigo, int estado, EntityManager em){
        List<CcCodigosCortos> codigosCortos = new ArrayList<CcCodigosCortos>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM CcCodigosCortos c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(modalidad != -1) {
            searchQuery.append("AND c.mdnCodigo.mdnCodigo = ?2 ");
        }
        if(codigo != -1) {
            searchQuery.append("AND c.ccnCodigoCorto LIKE ?3 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?4 ");
        }
        
        searchQuery.append("ORDER BY c.ccnCodigoCorto ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(modalidad != -1) {
            query.setParameter(2, modalidad);
        }
        if(codigo != -1) {
            query.setParameter(3, codigo + "%");
        }
        if(estado != -1) {
            query.setParameter(4, estado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        codigosCortos = query.getResultList();        
        return codigosCortos;
    }
    
    public static int countCargarCodigosCortos(String operador, int modalidad, int codigo, int estado, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM CcCodigosCortos c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(modalidad != -1) {
            searchQuery.append("AND c.mdnCodigo.mdnCodigo = ?2 ");
        }
        if(codigo != -1) {
            searchQuery.append("AND c.ccnCodigoCorto LIKE ?3 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?4 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(modalidad != -1) {
            query.setParameter(2, modalidad);
        }
        if(codigo != -1) {
            query.setParameter(3, codigo + "%");
        }
        if(estado != -1) {
            query.setParameter(4, estado);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
}
