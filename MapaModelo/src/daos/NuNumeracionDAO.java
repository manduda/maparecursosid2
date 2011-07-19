/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EmOperador;
import entities.NuNumeracion;
import java.math.BigDecimal;
import java.math.BigInteger;
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

    public static NuNumeracion findbyId(BigDecimal clnCodigo, EntityManager em){
        return em.find(NuNumeracion.class, clnCodigo);
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
    
    public static List<NuNumeracion> cargarNumeracion(String operador, int ndc, int inicio, int fin, EntityManager em){
        List<NuNumeracion> numeracion = new ArrayList<NuNumeracion>();

        StringBuilder searchQuery = new StringBuilder(
                "SELECT n FROM NuNumeracion n " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND n.emrCodigo.emrCodigo = :operador ");
        }
        if(ndc != -1) {
            searchQuery.append("AND n.ndnCodigo.ndnCodigo = :ndc ");
        }
        if(inicio != -1) {
            searchQuery.append("AND n.nunInicio LIKE :inicio ");
        }
        if(fin != -1) {
            searchQuery.append("AND n.nunFin LIKE :fin ");
        }
        searchQuery.append("ORDER BY n.nunInicio ASC");

        Query query = em.createQuery(searchQuery.toString());

        if(!operador.equals("-1")) {
            query.setParameter("operador", operador);
        }
        if(ndc != -1) {
            query.setParameter("ndc", ndc);
        }
        if(inicio != -1) {
            query.setParameter("inicio", inicio + "%");
        }
        if(fin != -1) {
            query.setParameter("fin", fin + "%");
        }
        numeracion = query.getResultList();        
        
        return numeracion;
    }
    
}
