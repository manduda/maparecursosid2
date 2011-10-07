/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.MdModalidadCc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class MdModalidadCcDAO {
    public static MdModalidadCc findbyId(int mdnCodigo, EntityManager em){
        return em.find(MdModalidadCc.class, mdnCodigo);
    }
    
    public static List<MdModalidadCc> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM MdModalidadCc e");
        return query.getResultList();
    }
}
