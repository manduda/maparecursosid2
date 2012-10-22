/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EmOperador;
import entities.SeSenalizacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class SeSenalizacionDAO {
    public static void persist(SeSenalizacion entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(SeSenalizacion entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(SeSenalizacion entity, EntityManager em){
        em.merge(entity);
    }

    public static SeSenalizacion findbyId(int senCodigo, EntityManager em){
        return em.find(SeSenalizacion.class, senCodigo);
    }

    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.seSenalizacionCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<Integer> getListZona(int tipoRegion, EntityManager em){
        StringBuilder searchQuery = new StringBuilder(
                "SELECT DISTINCT s.senZona FROM SeSenalizacion s " +
                "WHERE 1=1 ");

        if(tipoRegion != -1) {
            searchQuery.append("AND s.renCodigo.rtnCodigo.rtnCodigo = ?1 ");
        }
        
        searchQuery.append("ORDER BY s.senZona ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(tipoRegion != -1) {
            query.setParameter(1, tipoRegion);
        }
        
        return query.getResultList();
    }

    public static List<SeSenalizacion> cargarSenalizacion(int first, int max, String operador, int region, int zona, int ps, int estado, String municipio, String departamento, int tipoSenalizacion, int tipoRegion, EntityManager em){
        List<SeSenalizacion> senalizacion = new ArrayList<SeSenalizacion>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT s FROM SeSenalizacion s " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND s.emrCodigo.emrCodigo = ?1 ");
        }
        if(region != -1) {
            searchQuery.append("AND s.renCodigo.renCodigo = ?2 ");
        }
        if(zona != -1) {
            searchQuery.append("AND s.senZona LIKE ?3 ");
        }
        if(ps != -1) {
            searchQuery.append("AND s.senPs LIKE ?4 ");
        }
        if(estado != -1) {
            searchQuery.append("AND s.esnCodigo.esnCodigo = ?5 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery.append("AND s.codigoMunicipio.codigoMunicipio = ?6 ");
        }
        if(!departamento.equals("-1")) {
            searchQuery.append("AND s.codigoMunicipio.codigoDepartamento.codigoDepartamento = ?7 ");
        }
        if(tipoSenalizacion != -1) {
            searchQuery.append("AND s.tenCodigo.tenCodigo = ?8 ");
        }
        if(tipoRegion != -1) {
            searchQuery.append("AND s.renCodigo.rtnCodigo.rtnCodigo = ?9 ");
        }
        
        searchQuery.append("ORDER BY s.renCodigo.renCodigo,s.senZona,s.senPs ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(region != -1) {
            query.setParameter(2, region);
        }
        if(zona != -1) {
            query.setParameter(3, zona + "%");
        }
        if(ps != -1) {
            query.setParameter(4, ps + "%");
        }
        if(estado != -1) {
            query.setParameter(5, estado);
        }
        if(!municipio.equals("-1")) {
            query.setParameter(6, municipio);
        }
        if(!departamento.equals("-1")) {
            query.setParameter(7, departamento);
        }
        if(tipoSenalizacion != -1) {
            query.setParameter(8, tipoSenalizacion);
        }
        if(tipoRegion != -1) {
            query.setParameter(9, tipoRegion);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        senalizacion = query.getResultList();        
        return senalizacion;
    }

    public static int countCargarSenalizacion(String operador, int region, int zona, int ps, int estado, String municipio, String departamento, int tipoSenalizacion, int tipoRegion, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(s) FROM SeSenalizacion s " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND s.emrCodigo.emrCodigo = ?1 ");
        }
        if(region != -1) {
            searchQuery.append("AND s.renCodigo.renCodigo = ?2 ");
        }
        if(zona != -1) {
            searchQuery.append("AND s.senZona LIKE ?3 ");
        }
        if(ps != -1) {
            searchQuery.append("AND s.senPs LIKE ?4 ");
        }
        if(estado != -1) {
            searchQuery.append("AND s.esnCodigo.esnCodigo = ?5 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery.append("AND s.codigoMunicipio.codigoMunicipio = ?6 ");
        }
        if(!departamento.equals("-1")) {
            searchQuery.append("AND s.codigoMunicipio.codigoDepartamento.codigoDepartamento = ?7 ");
        }
        if(tipoSenalizacion != -1) {
            searchQuery.append("AND s.tenCodigo.tenCodigo = ?8 ");
        }
        if(tipoRegion != -1) {
            searchQuery.append("AND s.renCodigo.rtnCodigo.rtnCodigo = ?9 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(region != -1) {
            query.setParameter(2, region);
        }
        if(zona != -1) {
            query.setParameter(3, zona + "%");
        }
        if(ps != -1) {
            query.setParameter(4, ps + "%");
        }
        if(estado != -1) {
            query.setParameter(5, estado);
        }
        if(!municipio.equals("-1")) {
            query.setParameter(6, municipio);
        }
        if(!departamento.equals("-1")) {
            query.setParameter(7, departamento);
        }
        if(tipoSenalizacion != -1) {
            query.setParameter(8, tipoSenalizacion);
        }
        if(tipoRegion != -1) {
            query.setParameter(9, tipoRegion);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static void transferirSenalizacion(String operadorOrigen, String operadorDestino, EntityManager em){

        String searchQuery = "UPDATE SE_SENALIZACION SET SK_EMPRESA_CODE = ?1 WHERE SK_EMPRESA_CODE = ?2";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, operadorDestino);
        query.setParameter(2, operadorOrigen);
        
        query.executeUpdate();
        
    }
}
