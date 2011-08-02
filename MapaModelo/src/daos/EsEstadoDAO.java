/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EsEstado;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class EsEstadoDAO {
    public static EsEstado findbyId(int esnCodigo, EntityManager em){
        return em.find(EsEstado.class, esnCodigo);
    }
    
    public static List<EsEstado> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM EsEstado e");
        return query.getResultList();
    }
    
}
