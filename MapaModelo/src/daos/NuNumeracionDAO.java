/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EmOperador;
import entities.NuNumeracion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class NuNumeracionDAO {
    public static void persist(NuNumeracion entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(NuNumeracion entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(NuNumeracion entity, EntityManager em){
        em.merge(entity);
    }

    public static NuNumeracion findbyId(BigDecimal nunCodigo, EntityManager em){
        return em.find(NuNumeracion.class, nunCodigo);
    }
    
    public static List<NuNumeracion> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM NuNumeracion e ORDER BY e.nunInicio ASC");
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM NuNumeracion e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.nuNumeracionCollection n ORDER BY e.emtNombre ASC");
        return query.getResultList();
    }
    
    public static List<NuNumeracion> cargarNumeracion(int first, int max, String operador, int ndc, int inicio, int fin, int estado, String municipio, String departamento, EntityManager em){
        List<NuNumeracion> numeracion = new ArrayList<NuNumeracion>();

        StringBuilder searchQuery1 = new StringBuilder(
                "SELECT RPAD(MIN(NUM),7,0),RPAD(MAX(NUM),7,9) FROM (SELECT DISTINCT "
                + "SUBSTR(n.NUN_INICIO,1,4) NUM, "
                + "n.SK_REGION_CODE, "
                + "n.SK_EMPRESA_CODE, "
                + "n.ESN_CODIGO, "
                + "n.NDN_CODIGO, "
                + "n.SK_REGION_CODE "
                + "FROM NU_NUMERACION n, SA.DEPARTAMENTOS d, SA.MUNICIPIOS m "
                + "WHERE 1=1 "
                + "AND n.SK_REGION_CODE = m.CODIGO_MUNICIPIO "
                + "AND m.CODIGO_DEPARTAMENTO = d.CODIGO_DEPARTAMENTO ");
        
        StringBuilder searchQuery = new StringBuilder(
                "SELECT n FROM NuNumeracion n " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery1.append("AND n.SK_EMPRESA_CODE = ?1 ");
            searchQuery.append("AND n.emrCodigo.emrCodigo = ?1 ");
        }
        if(ndc != -1) {
            searchQuery1.append("AND n.NDN_CODIGO = ?2 ");
            searchQuery.append("AND n.ndnCodigo.ndnCodigo = ?2 ");
        }
        if(inicio != -1) {
            searchQuery1.append("AND n.NUN_INICIO LIKE ?3 ");
            searchQuery.append("AND n.nunInicio LIKE ?3 ");
        }
        if(fin != -1) {
            searchQuery1.append("AND n.NUN_FIN LIKE ?4 ");
            searchQuery.append("AND n.nunFin LIKE ?4 ");
        }
        if(estado != -1) {
            searchQuery1.append("AND n.ESN_CODIGO = ?5 ");
            searchQuery.append("AND n.esnCodigo.esnCodigo = ?5 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery1.append("AND n.SK_REGION_CODE = ?6 ");
            searchQuery.append("AND n.codigoMunicipio.codigoMunicipio = ?6 ");
        }
        if(!departamento.equals("-1")) {
            searchQuery1.append("AND d.CODIGO_DEPARTAMENTO = ?7 ");
            searchQuery.append("AND n.codigoMunicipio.codigoDepartamento.codigoDepartamento = ?7 ");
        }
        
        searchQuery1.append(" ) a");
        
        Query query1 = em.createNativeQuery(searchQuery1.toString());

        if(!operador.equals("-1")) {
            query1.setParameter(1, operador);
        }
        if(ndc != -1) {
            query1.setParameter(2, ndc);
        }
        if(inicio != -1) {
            query1.setParameter(3, inicio + "%");
        }
        if(fin != -1) {
            query1.setParameter(4, fin + "%");
        }
        if(estado != -1) {
            query1.setParameter(5, estado);
        }
        if(!municipio.equals("-1")) {
            query1.setParameter(6, municipio);
        }
        if(!departamento.equals("-1")) {
            query1.setParameter(7, departamento);
        }
        
        Object[] results = (Object [])query1.getSingleResult();
//                getResultList();
        
        if (results[0] != null){
            searchQuery.append("AND n.nunInicio >= ?10 AND n.nunFin <= ?11 ");
        }        
        
        searchQuery.append("ORDER BY n.nunInicio ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(ndc != -1) {
            query.setParameter(2, ndc);
        }
        if(inicio != -1) {
            query.setParameter(3, inicio + "%");
        }
        if(fin != -1) {
            query.setParameter(4, fin + "%");
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
        
        if (results[0] != null){
            Integer a = Integer.valueOf(results[0].toString());
            Integer b = Integer.valueOf(results[1].toString());
            query.setParameter(10, a);
            query.setParameter(11, b);
        }
        
        
        query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }
        numeracion = query.getResultList();        
        return numeracion;
    }
    
    public static int countCargarNumeracion(String operador, int ndc, int inicio, int fin, int estado, String municipio, String departamento, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(*) FROM (SELECT DISTINCT "
                + "SUBSTR(n.NUN_INICIO,1,4), "
                + "n.SK_REGION_CODE, "
                + "n.SK_EMPRESA_CODE, "
                + "n.ESN_CODIGO, "
                + "n.NDN_CODIGO, "
                + "n.SK_REGION_CODE "
                + "FROM NU_NUMERACION n, SA.DEPARTAMENTOS d, SA.MUNICIPIOS m "
                + "WHERE 1=1 "
                + "AND n.SK_REGION_CODE = m.CODIGO_MUNICIPIO "
                + "AND m.CODIGO_DEPARTAMENTO = d.CODIGO_DEPARTAMENTO ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND n.SK_EMPRESA_CODE = ?1 ");
        }
        if(ndc != -1) {
            searchQuery.append("AND n.NDN_CODIGO = ?2 ");
        }
        if(inicio != -1) {
            searchQuery.append("AND n.NUN_INICIO LIKE ?3 ");
        }
        if(fin != -1) {
            searchQuery.append("AND n.NUN_FIN LIKE ?4 ");
        }
        if(estado != -1) {
            searchQuery.append("AND n.ESN_CODIGO = ?5 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery.append("AND n.SK_REGION_CODE = ?6 ");
        }
        if(!departamento.equals("-1")) {
            searchQuery.append("AND d.CODIGO_DEPARTAMENTO = ?7 ");
        }
        
        searchQuery.append(" ) a");
        
        Query query = em.createNativeQuery(searchQuery.toString());

        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(ndc != -1) {
            query.setParameter(2, ndc);
        }
        if(inicio != -1) {
            query.setParameter(3, inicio + "%");
        }
        if(fin != -1) {
            query.setParameter(4, fin + "%");
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
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static void transferirNumeracionDAO(String operadorOrigen, String operadorDestino, EntityManager em){

        String searchQuery = "UPDATE NU_NUMERACION SET SK_EMPRESA_CODE = ?1 WHERE SK_EMPRESA_CODE = ?2";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, operadorDestino);
        query.setParameter(2, operadorOrigen);
        
        query.executeUpdate();
        
    }
    
}
