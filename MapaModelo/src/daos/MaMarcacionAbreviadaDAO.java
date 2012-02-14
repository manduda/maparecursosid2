/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EmOperador;
import entities.MaMarcacionAbreviada;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class MaMarcacionAbreviadaDAO {
    public static void persist(MaMarcacionAbreviada entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(MaMarcacionAbreviada entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(MaMarcacionAbreviada entity, EntityManager em){
        em.merge(entity);
    }

    public static MaMarcacionAbreviada findbyId(int manCodigo, EntityManager em){
        return em.find(MaMarcacionAbreviada.class, manCodigo);
    }
    
    public static List<MaMarcacionAbreviada> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM MaMarcacionAbreviada e");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM MA_MARCACION_ABREVIADA e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.maMarcacionAbreviadaCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<MaMarcacionAbreviada> cargarMarcacionAbreviada(int first, int max, String operador, int codigoMarcacion, int estado, EntityManager em){
        List<MaMarcacionAbreviada> codigosMarcacion = new ArrayList<MaMarcacionAbreviada>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM MaMarcacionAbreviada c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoMarcacion != -1) {
            searchQuery.append("AND c.manCodigoMarcacion LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        searchQuery.append("ORDER BY c.manCodigoMarcacion ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoMarcacion != -1) {
            query.setParameter(2, codigoMarcacion + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        codigosMarcacion = query.getResultList();        
        return codigosMarcacion;
    }
    
    public static int countCargarMarcacionAbreviada(String operador, int codigoMarcacion, int estado, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM MaMarcacionAbreviada c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigoMarcacion != -1) {
            searchQuery.append("AND c.manCodigoMarcacion LIKE ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigoMarcacion != -1) {
            query.setParameter(2, codigoMarcacion + "%");
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
}
