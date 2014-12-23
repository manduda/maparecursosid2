/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.RrTipoRedTdt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class RrTipoRedTdtDAO {
    public static RrTipoRedTdt findbyId(int rrnCodigo, EntityManager em){
        return em.find(RrTipoRedTdt.class, rrnCodigo);
    }
    
    public static List<RrTipoRedTdt> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM RrTipoRedTdt e");
        return query.getResultList();
    }
}
