/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.CaCanalTdt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class CaCanalTdtDAO {
    public static CaCanalTdt findbyId(int canCodigo, EntityManager em){
        return em.find(CaCanalTdt.class, canCodigo);
    }
    
    public static List<CaCanalTdt> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM CaCanalTdt e");
        return query.getResultList();
    }
}
