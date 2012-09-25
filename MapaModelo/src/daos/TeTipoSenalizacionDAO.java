/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TeTipoSenalizacion;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class TeTipoSenalizacionDAO {
    public static TeTipoSenalizacion findbyId(int tenCodigo, EntityManager em){
        return em.find(TeTipoSenalizacion.class, tenCodigo);
    }
    
    public static List<TeTipoSenalizacion> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM TeTipoSenalizacion e ORDER BY e.tetNombre ASC");
        return query.getResultList();
    }
}
