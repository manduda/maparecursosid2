/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Municipios;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class MunicipiosDAO {
    public static Municipios findbyId(String codigoMunicipio, EntityManager em){
        return em.find(Municipios.class, codigoMunicipio);
    }
    
    public static List<Municipios> getList(EntityManager em){
        Query query = em.createQuery("SELECT m FROM Municipios m");
        return query.getResultList();
    }
    
    public static List<Municipios> cargarMunicipios(String departamento, EntityManager em){
        Query query = em.createQuery("SELECT m FROM Municipios m WHERE m.codigoDepartamento.codigoDepartamento = :departamento ORDER BY m.nombreMunicipio");
        query.setParameter("departamento", departamento);
        return query.getResultList();
    }

}
