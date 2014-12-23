/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EmOperador;
import entities.RiRecursosTdt;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class RiRecursosTdtDAO {
    public static void persist(RiRecursosTdt entity, EntityManager em){
        em.persist(entity);
    }
    
    public static void delete(RiRecursosTdt entity, EntityManager em){
        em.remove(em.merge(entity));
    }
    
    public static void merge(RiRecursosTdt entity, EntityManager em){
        em.merge(entity);
    }
    
    public static RiRecursosTdt findbyId(int rinCodigo, EntityManager em){
        return em.find(RiRecursosTdt.class, rinCodigo);
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.riRecursosTdtCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<RiRecursosTdt> cargarRecursosTdt(int first, int max, String operador, int codigo, int tipoRecurso, int tipoRed, int canal, String municipio, String departamento, int tipoServicio, int multiplex, int estado, EntityManager em){
        List<RiRecursosTdt> recursosTdt = new ArrayList<RiRecursosTdt>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT c FROM RiRecursosTdt c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigo != -1) {
            searchQuery.append("AND c.rinCodigoRecurso = ?2 ");
        }
        if(tipoRecurso != -1) {
            searchQuery.append("AND c.rnnCodigo.rnnCodigo = ?3 ");
        }
        if(tipoRed != -1) {
            searchQuery.append("AND c.rrnCodigo.rrnCodigo = ?4 ");
        }
        if(canal != -1) {
            searchQuery.append("AND c.canCodigo.canCodigo = ?5 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery.append("AND c.rinCodigo IN (SELECT DISTINCT mun.rinCodigo.rinCodigo FROM RaRegionesTdt mun where mun.codigoMunicipio.codigoMunicipio = ?6) ");
        }
        if(!departamento.equals("-1")) {
            searchQuery.append("AND c.rinCodigo IN (SELECT DISTINCT dep.rinCodigo.rinCodigo FROM RaRegionesTdt dep where dep.codigoMunicipio.codigoDepartamento.codigoDepartamento = ?7) ");
        }
        if(tipoServicio != -1) {
            searchQuery.append("AND c.rinCodigo IN (SELECT s.rinCodigo.rinCodigo FROM StServiciosTdt s where s.ttnCodigo.ttnCodigo = ?8) ");
        }
        if(multiplex != -1) {
            searchQuery.append("AND c.rinCodigo IN (SELECT mu.rinCodigo.rinCodigo FROM MrMultiplexRecurso mu where mu.munCodigo.munCodigo = ?9) ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?10 ");
        }
        
        searchQuery.append("ORDER BY c.rinCodigoRecurso ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigo != -1) {
            query.setParameter(2, codigo);
        }
        if(tipoRecurso != -1) {
            query.setParameter(3, tipoRecurso);
        }
        if(tipoRed != -1) {
            query.setParameter(4, tipoRed);
        }
        if(canal != -1) {
            query.setParameter(5, canal);
        }
        if(!municipio.equals("-1")) {
            query.setParameter(6, municipio);
        }
        if(!departamento.equals("-1")) {
            query.setParameter(7, departamento);
        }
        if(tipoServicio != -1) {
            query.setParameter(8, tipoServicio);
        }
        if(multiplex != -1) {
            query.setParameter(9, multiplex);
        }
        if(estado != -1) {
            query.setParameter(10, estado);
        }
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        recursosTdt = query.getResultList();        
        return recursosTdt;
    }
    
    public static int countCargarRecursosTdt(String operador, int codigo, int tipoRecurso, int tipoRed, int canal, String municipio, String departamento, int tipoServicio, int multiplex, int estado, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(c) FROM RiRecursosTdt c " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND c.emrCodigo.emrCodigo = ?1 ");
        }
        if(codigo != -1) {
            searchQuery.append("AND c.rinCodigoRecurso = ?2 ");
        }
        if(tipoRecurso != -1) {
            searchQuery.append("AND c.rnnCodigo.rnnCodigo = ?3 ");
        }
        if(tipoRed != -1) {
            searchQuery.append("AND c.rrnCodigo.rrnCodigo = ?4 ");
        }
        if(canal != -1) {
            searchQuery.append("AND c.canCodigo.canCodigo = ?5 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery.append("AND c.rinCodigo IN (SELECT DISTINCT mun.rinCodigo.rinCodigo FROM RaRegionesTdt mun where mun.codigoMunicipio.codigoMunicipio = ?6) ");
        }
        if(!departamento.equals("-1")) {
            searchQuery.append("AND c.rinCodigo IN (SELECT DISTINCT dep.rinCodigo.rinCodigo FROM RaRegionesTdt dep where dep.codigoMunicipio.codigoDepartamento.codigoDepartamento = ?7) ");
        }
        if(tipoServicio != -1) {
            searchQuery.append("AND c.rinCodigo IN (SELECT s.rinCodigo.rinCodigo FROM StServiciosTdt s where s.ttnCodigo.ttnCodigo = ?8) ");
        }
        if(multiplex != -1) {
            searchQuery.append("AND c.rinCodigo IN (SELECT mu.rinCodigo.rinCodigo FROM MrMultiplexRecurso mu where mu.munCodigo.munCodigo = ?9) ");
        }
        if(estado != -1) {
            searchQuery.append("AND c.esnCodigo.esnCodigo = ?10 ");
        }
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(codigo != -1) {
            query.setParameter(2, codigo);
        }
        if(tipoRecurso != -1) {
            query.setParameter(3, tipoRecurso);
        }
        if(tipoRed != -1) {
            query.setParameter(4, tipoRed);
        }
        if(canal != -1) {
            query.setParameter(5, canal);
        }
        if(!municipio.equals("-1")) {
            query.setParameter(6, municipio);
        }
        if(!departamento.equals("-1")) {
            query.setParameter(7, departamento);
        }
        if(tipoServicio != -1) {
            query.setParameter(8, tipoServicio);
        }
        if(multiplex != -1) {
            query.setParameter(9, multiplex);
        }
        if(estado != -1) {
            query.setParameter(10, estado);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static void transferirRecursosTdt(String operadorOrigen, String operadorDestino, EntityManager em){

        String searchQuery = "UPDATE RI_RECURSOS_TDT SET SK_EMPRESA_CODE = ?1 WHERE SK_EMPRESA_CODE = ?2";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, operadorDestino);
        query.setParameter(2, operadorOrigen);
        
        query.executeUpdate();
        
    }
}
