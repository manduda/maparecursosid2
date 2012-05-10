/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.CmConfiguracionModulos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class CmConfiguracionModulosDAO {
    public static void merge(CmConfiguracionModulos entity, EntityManager em){
        em.merge(entity);
    }

    public static CmConfiguracionModulos findbyId(int cmnCodigo, EntityManager em){
        return em.find(CmConfiguracionModulos.class, cmnCodigo);
    }
    
    public static List<CmConfiguracionModulos> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM CmConfiguracionModulos e ORDER BY e.cmnCodigo ASC");
        return query.getResultList();
    }
}
