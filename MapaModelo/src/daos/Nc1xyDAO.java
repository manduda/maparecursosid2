/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.MoModalidad1xy;
import entities.Nc1xy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class Nc1xyDAO {
    public static void persist(Nc1xy entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(Nc1xy entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(Nc1xy entity, EntityManager em){
        em.merge(entity);
    }

    public static Nc1xy findbyId(int ncnCodigo, EntityManager em){
        return em.find(Nc1xy.class, ncnCodigo);
    }
    
    public static List<Nc1xy> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM Nc1xy e");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM NC_1XY e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<Nc1xy> cargarCodigos1xy(int first, int max, int codigo1xy, int codigoModalidad, int estado, String servicio, EntityManager em){
        List<Nc1xy> codigos1xy = new ArrayList<Nc1xy>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM Nc1xy c " +
                "WHERE 1=1 ");

        if(codigo1xy != -1) {
            searchQuery.append("AND c.ncn1xy LIKE ?1 ");
        }
        if(codigoModalidad != -1) {
            searchQuery.append("AND c.monCodigo.monCodigo = ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        if(!servicio.equals("-1")) {
            searchQuery.append("AND c.nctServicio = ?4 ");
        }
        
        searchQuery.append("ORDER BY c.ncn1xy ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(codigo1xy != -1) {
            query.setParameter(1, codigo1xy + "%");
        }
        if(codigoModalidad != -1) {
            query.setParameter(2, codigoModalidad);
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        if(!servicio.equals("-1")) {
            query.setParameter(4, servicio);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        codigos1xy = query.getResultList();        
        return codigos1xy;
    }
    
    public static int countCargarCodigos1xy(int codigo1xy, int codigoModalidad, int estado, String servicio, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM Nc1xy c " +
                "WHERE 1=1 ");

        if(codigo1xy != -1) {
            searchQuery.append("AND c.ncn1xy LIKE ?1 ");
        }
        if(codigoModalidad != -1) {
            searchQuery.append("AND c.monCodigo.monCodigo = ?2 ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?3 ");
        }
        if(!servicio.equals("-1")) {
            searchQuery.append("AND c.nctServicio = ?4 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(codigo1xy != -1) {
            query.setParameter(1, codigo1xy + "%");
        }
        if(codigoModalidad != -1) {
            query.setParameter(2, codigoModalidad);
        }
        if(estado != -1) {
            query.setParameter(3, estado);
        }
        if(!servicio.equals("-1")) {
            query.setParameter(4, servicio);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static List<MoModalidad1xy> getListModalidad1xy(EntityManager em){
        Query query = em.createQuery("SELECT e FROM MoModalidad1xy e ORDER BY e.motNombre ASC");
        return query.getResultList();
    }
    
    public static List<String> getListServicios(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e.nctServicio FROM Nc1xy e WHERE (e.nctServicio != '') OR (e.nctServicio IS NOT NULL) ORDER BY e.nctServicio ASC");
        return query.getResultList();
    }
}
