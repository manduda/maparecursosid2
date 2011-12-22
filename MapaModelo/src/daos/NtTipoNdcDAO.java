/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.NtTipoNdc;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class NtTipoNdcDAO {
    
    public static List<NtTipoNdc> getList(String ndc, EntityManager em){
        Query query = em.createQuery("SELECT nt FROM NtTipoNdc nt "
                + "WHERE nt.ntnCodigo IN (SELECT DISTINCT nd.ntnCodigo.ntnCodigo FROM NdNdc nd WHERE nd.ndtNombre = :ndc)");
        query.setParameter("ndc", ndc);
        return query.getResultList();
    }    
}
