/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.RnTipoRecursoTdt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class RnTipoRecursoTdtDAO {
    public static RnTipoRecursoTdt findbyId(int rnnCodigo, EntityManager em){
        return em.find(RnTipoRecursoTdt.class, rnnCodigo);
    }
    
    public static List<RnTipoRecursoTdt> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM RnTipoRecursoTdt e");
        return query.getResultList();
    }
}
