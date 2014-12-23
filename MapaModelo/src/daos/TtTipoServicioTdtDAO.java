/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TtTipoServicioTdt;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TtTipoServicioTdtDAO {
    public static TtTipoServicioTdt findbyId(int ttnCodigo, EntityManager em){
        return em.find(TtTipoServicioTdt.class, ttnCodigo);
    }
    
    public static List<TtTipoServicioTdt> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM TtTipoServicioTdt e");
        return query.getResultList();
    }
}
