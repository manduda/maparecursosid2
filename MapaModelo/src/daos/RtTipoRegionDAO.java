/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.RtTipoRegion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class RtTipoRegionDAO {
    public static RtTipoRegion findbyId(int rtnCodigo, EntityManager em){
        return em.find(RtTipoRegion.class, rtnCodigo);
    }
    
    public static List<RtTipoRegion> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM RtTipoRegion e ORDER BY e.rtnCodigo ASC");
        return query.getResultList();
    }
}
