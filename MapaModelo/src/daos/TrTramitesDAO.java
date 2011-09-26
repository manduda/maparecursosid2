/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TrTramites;
import java.util.ArrayList;
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
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.trnCodigo) FROM TrTramites t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
    
    public static List<TrTramites> getTramitesAsesor(int userCode, EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.usnCodigo.codigoSIUST.userCode = :usuario "
                + "AND e.etnCodigo.etnCodigo IN (1,3) ORDER BY e.trfFecha DESC");
        query.setParameter("usuario", userCode);
        return query.getResultList();
    }
    
    public static List<TrTramites> getTramitesCreados(int usnCodigo, EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.usnCodigo.usnCodigo = :usuario "
                + "AND e.etnCodigo.etnCodigo = 1");
        query.setParameter("usuario", usnCodigo);
        return query.getResultList();
    }
    
    public static List<TrTramites> getTramitesDevueltos(int usnCodigo, EntityManager em){
        Query query = em.createQuery("SELECT e FROM TrTramites e WHERE e.usnCodigo.usnCodigo = :ususario "
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
    
    public static List<TrTramites> cargarTramites(int first, int max, int tramiteId, int usuario, String operador, int estado, int radicado, EntityManager em){
        List<TrTramites> tramites = new ArrayList<TrTramites>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT t FROM TrTramites t ");
        /*if(radicado != -1) {
            searchQuery.append(", IN(t.tsTramiteSenalizacionCollection) ts, "
                    + "IN(t.tlTramiteldCollection) tld ");
        }*/
        searchQuery.append("WHERE 1=1 ");
        
        if(!operador.equals("-1")) {
            searchQuery.append("AND t.emrCodigo.emrCodigo = ?1 ");
        }
        if(tramiteId != -1) {
            searchQuery.append("AND t.trnCodigo = ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND t.etnCodigo.etnCodigo = ?3 ");
        }
        if(usuario != -1) {
            searchQuery.append("AND t.usnCodigo.codigoSIUST.userCode = ?4 ");
        }
        if(radicado != -1) {
            searchQuery.append("AND ("
                    + "(t.trnCodigo IN (SELECT DISTINCT ts.trnCodigo.trnCodigo FROM TsTramiteSenalizacion ts where ts.tsnRadicado = ?5)) "
                    + "OR "
                    + "(t.trnCodigo IN (SELECT DISTINCT tld.trnCodigo.trnCodigo FROM TlTramiteLd tld where tld.tlnRadicado = ?5))"
                    + ") ");
        }
        
        searchQuery.append("ORDER BY t.trnCodigo ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(tramiteId != -1) {
            query.setParameter(2, tramiteId);
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        if(usuario != -1) {
            query.setParameter(4, usuario);
        }
        if(radicado != -1) {
            query.setParameter(5, radicado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        tramites = query.getResultList();        
        return tramites;
    }
}
