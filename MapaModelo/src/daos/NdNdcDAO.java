/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.NdNdc;
import java.math.BigDecimal;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class NdNdcDAO {
    public static void persist(NdNdc entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(NdNdc entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(NdNdc entity, EntityManager em){
        em.merge(entity);
    }

    public static NdNdc findbyId(int ndnCodigo, EntityManager em){
        return em.find(NdNdc.class, ndnCodigo);
    }
    
    public static List<String> getList(String departamento, EntityManager em){
        //Query query = em.createQuery("SELECT DISTINCT e.ndtNombre FROM NdNdc e ORDER BY e.ndtNombre ASC");
        StringBuilder searchQuery = new StringBuilder(
                "SELECT DISTINCT C.NDT_NOMBRE FROM ND_NDC C "
                + "WHERE C.NDN_CODIGO IN "
                + "( "
                + "SELECT DISTINCT A.NDN_CODIGO "
                + "FROM MAPA.NU_NUMERACION A "
                + "WHERE 1=1 ");
        
        if(!departamento.equals("-1")) {
            searchQuery.append("AND A.SK_REGION_CODE IN (SELECT B.CODIGO_MUNICIPIO FROM SA.MUNICIPIOS B WHERE B.CODIGO_DEPARTAMENTO = ?1) ");
        }
        searchQuery.append(") ORDER BY C.NDT_NOMBRE");
                
        Query query = em.createNativeQuery(searchQuery.toString());
        
        if(!departamento.equals("-1")) {
            query.setParameter(1, departamento);
        }
        
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM NdNdc e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
}
