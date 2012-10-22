/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EmOperador;
import entities.NrCodigosNrn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class NrCodigosNrnDAO {
    
    public static void persist(NrCodigosNrn entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(NrCodigosNrn entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(NrCodigosNrn entity, EntityManager em){
        em.merge(entity);
    }

    public static NrCodigosNrn findbyId(int nrnCodigo, EntityManager em){
        return em.find(NrCodigosNrn.class, nrnCodigo);
    }
    
    public static List<NrCodigosNrn> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM NrCodigosNrn e");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM NR_CODIGOS_NRN e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.nrCodigosNrnCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<NrCodigosNrn> cargarCodigosNrn(int first, int max, String operador, int codigoNrn, int estado, EntityManager em){
        List<NrCodigosNrn> codigosNrn = new ArrayList<NrCodigosNrn>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM NrCodigosNrn c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoNrn != -1) {
            searchQuery.append("AND c.nrnCodigoNrn LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        searchQuery.append("ORDER BY c.nrnCodigoNrn ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoNrn != -1) {
            query.setParameter(2, codigoNrn + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        codigosNrn = query.getResultList();        
        return codigosNrn;
    }
    
    public static int countCargarCodigosNrn(String operador, int codigoNrn, int estado, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM NrCodigosNrn c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoNrn != -1) {
            searchQuery.append("AND c.nrnCodigoNrn LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoNrn != -1) {
            query.setParameter(2, codigoNrn + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static void transferirCodigosNrn(String operadorOrigen, String operadorDestino, EntityManager em){

        String searchQuery = "UPDATE NR_CODIGOS_NRN SET SK_EMPRESA_CODE = ?1 WHERE SK_EMPRESA_CODE = ?2";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, operadorDestino);
        query.setParameter(2, operadorOrigen);
        
        query.executeUpdate();
        
    }
}
