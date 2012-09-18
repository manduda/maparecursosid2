/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.PaPermisosAsesor;
import entities.PtTipoPermiso;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.NoResultException;
import javax.persistence.Query;

/**
 *
 * @author MADD
 */
public class PaPermisosAsesorDAO {
    public static void persist(PaPermisosAsesor entity, EntityManager em){
        em.persist(entity);
    }

    public static void merge(PaPermisosAsesor entity, EntityManager em){
        em.merge(entity);
    }
    
    public static void delete(PaPermisosAsesor entity, EntityManager em){
        em.remove(em.merge(entity));
    }
    
    public static PaPermisosAsesor findbyId(int panCodigo, EntityManager em){
        return em.find(PaPermisosAsesor.class, panCodigo);
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.panCodigo) FROM PaPermisosAsesor t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
    
    public static List<PtTipoPermiso> cargarPermisos(int usuario, EntityManager em) {
        
        Query query = em.createQuery("SELECT p.ptnCodigo FROM PaPermisosAsesor p "
                + "WHERE p.usnCodigo.usnCodigo = :usuario "
                + "ORDER BY p.ptnCodigo.ptnCodigo");
        
        query.setParameter("usuario", usuario);
        
        return query.getResultList();
    }
    
    public static List<PtTipoPermiso> cargarPermisosTotales(int usuario, EntityManager em) {
        
        Query query = em.createQuery("SELECT pt FROM PtTipoPermiso pt "
                + "ORDER BY pt.ptnCodigo");
        
        List<PtTipoPermiso> permisos = query.getResultList();
        List<PtTipoPermiso> permisosFinales = new ArrayList<PtTipoPermiso>();
        
        for (PtTipoPermiso p : permisos){
            PaPermisosAsesor permisosAsesor = consultarPermiso(usuario, p.getPtnCodigo(), em);
            Collection<PaPermisosAsesor> coleccionPermisosAsesor = new ArrayList<PaPermisosAsesor>();
            coleccionPermisosAsesor.add(permisosAsesor);
            PtTipoPermiso permiso = p;
            permiso.setPaPermisosAsesorCollection(coleccionPermisosAsesor);
            permisosFinales.add(permiso);
        }
        
        return permisosFinales;
    }
    
    public static PaPermisosAsesor consultarPermiso(int usuario, int tipo, EntityManager em) {
        
        Query query = em.createQuery("SELECT p FROM PaPermisosAsesor p "
                + "WHERE p.usnCodigo.usnCodigo = :usuario "
                + "AND p.ptnCodigo.ptnCodigo = :tipo");
        
        query.setParameter("usuario", usuario);
        query.setParameter("tipo", tipo);
        
        try{
            return (PaPermisosAsesor)query.getSingleResult();
        } catch(NoResultException e) {
            return null;
        }
        
    }
    
}
