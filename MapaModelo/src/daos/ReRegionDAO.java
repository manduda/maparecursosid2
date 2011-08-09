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
    
    public static List<ReRegion> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM ReRegion e ORDER BY e.retNombre ASC");
        return query.getResultList();
    }
}
