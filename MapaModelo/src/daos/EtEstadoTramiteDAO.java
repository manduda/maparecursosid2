/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EtEstadoTramite;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class EtEstadoTramiteDAO {
    public static EtEstadoTramite findbyId(int etnCodigo, EntityManager em){
        return em.find(EtEstadoTramite.class, etnCodigo);
    }
    
    public static List<EtEstadoTramite> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM EtEstadoTramite e WHERE e.etnCodigo != 7");
        return query.getResultList();
    }

}
