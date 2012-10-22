/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.CdCodigosMnc;
import entities.EmOperador;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class CdCodigosMncDAO {
    public static void persist(CdCodigosMnc entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(CdCodigosMnc entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(CdCodigosMnc entity, EntityManager em){
        em.merge(entity);
    }

    public static CdCodigosMnc findbyId(int cdnCodigo, EntityManager em){
        return em.find(CdCodigosMnc.class, cdnCodigo);
    }
    
    public static List<CdCodigosMnc> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM CdCodigosMnc e");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM CD_CODIGOS_MNC e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.cdCodigosMncCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<CdCodigosMnc> cargarCodigosMnc(int first, int max, String operador, int codigoMnc, int estado, EntityManager em){
        List<CdCodigosMnc> codigosMnc = new ArrayList<CdCodigosMnc>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM CdCodigosMnc c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoMnc != -1) {
            searchQuery.append("AND c.cdnMnc LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        searchQuery.append("ORDER BY c.cdnMnc ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoMnc != -1) {
            query.setParameter(2, codigoMnc + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        codigosMnc = query.getResultList();        
        return codigosMnc;
    }
    
    public static int countCargarCodigosMnc(String operador, int codigoMnc, int estado, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM CdCodigosMnc c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoMnc != -1) {
            searchQuery.append("AND c.cdnMnc LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoMnc != -1) {
            query.setParameter(2, codigoMnc + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static void transferirCodigosMnc(String operadorOrigen, String operadorDestino, EntityManager em){

        String searchQuery = "UPDATE CD_CODIGOS_MNC SET SK_EMPRESA_CODE = ?1 WHERE SK_EMPRESA_CODE = ?2";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, operadorDestino);
        query.setParameter(2, operadorOrigen);
        
        query.executeUpdate();
        
    }
}
