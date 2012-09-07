/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade.helpers;

import javax.persistence.EntityManager;

/**
 *
 * @author miguel.duran
 */
public class CloseEntityManager {
    public static void close(EntityManager em) {
        if(em != null) {
                em.clear();
                em.close();
        }
    }
}
