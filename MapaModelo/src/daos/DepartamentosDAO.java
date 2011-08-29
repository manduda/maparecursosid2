/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Departamentos;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class DepartamentosDAO {
    public static List<Departamentos> cargarDepartamentos(EntityManager em){
        Query query = em.createQuery("SELECT d FROM Departamentos d ORDER BY d.nombreDepartamento");
        return query.getResultList();
    }
}
