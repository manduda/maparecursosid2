/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.ReRegion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class ReRegionDAO {
    public static ReRegion findbyId(int renCodigo, EntityManager em){
        return em.find(ReRegion.class, renCodigo);
    }
    
    public static List<ReRegion> getList(int tipoRegion, EntityManager em){
        StringBuilder searchQuery = new StringBuilder(
                "SELECT DISTINCT s.renCodigo FROM SeSenalizacion s " +
                "WHERE 1=1 ");

        if(tipoRegion != -1) {
            searchQuery.append("AND s.renCodigo.rtnCodigo.rtnCodigo = ?1 ");
        }
        
        searchQuery.append("ORDER BY s.renCodigo.retNombre ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(tipoRegion != -1) {
            query.setParameter(1, tipoRegion);
        }
        
        return query.getResultList();
    }
}
