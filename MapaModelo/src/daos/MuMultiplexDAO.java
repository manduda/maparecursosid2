/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.MuMultiplex;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class MuMultiplexDAO {
    public static MuMultiplex findbyId(int munCodigo, EntityManager em){
        return em.find(MuMultiplex.class, munCodigo);
    }
    
    public static List<MuMultiplex> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM MuMultiplex e ORDER BY e.mutNombreMultiplex ASC");
        return query.getResultList();
    }
}
