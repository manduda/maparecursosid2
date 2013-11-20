/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.CoConfiguracion;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author MADD
 */
public class CoConfiguracionDAO {
    /* 1 = OPERADOR NINGUNO
     * 2 = MUNICIPIO NINIGUNO
     */
    public static CoConfiguracion findbyId(int conCodigo, EntityManager em){
        return em.find(CoConfiguracion.class, conCodigo);
    }
    
    public static CoConfiguracion findbyName(String cotDescripcion, EntityManager em){
        Query query = em.createQuery("SELECT c FROM CoConfiguracion c WHERE c.cotDescripcion = :descripcion");
        query.setParameter("descripcion", cotDescripcion);
        return (CoConfiguracion) query.getSingleResult();
    }
}
