/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.PaPermisosAsesorDAO;
import entities.PaPermisosAsesor;
import entities.PtTipoPermiso;
import entities.UsUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.PaPermisosAsesorVO;
import vo.PtTipoPermisoVO;

/**
 *
 * @author MADD
 */
public class PaPermisosAsesorService {
    public boolean crearPermiso(int user, int type, EntityManager em){
        PaPermisosAsesor entity = new PaPermisosAsesor();
        
        entity = PaPermisosAsesorDAO.consultarPermiso(user, type, em);
        
        if (entity == null) {
            UsUsuarios usuario = new UsUsuarios();
            usuario.setUsnCodigo(user);

            PtTipoPermiso tipo = new PtTipoPermiso(); 
            tipo.setPtnCodigo(type);

            entity.setPanCodigo(PaPermisosAsesorDAO.getMaxId(em)+1);
            entity.setUsnCodigo(usuario);
            entity.setPtnCodigo(tipo);

            PaPermisosAsesorDAO.persist(entity, em);
            
            return true;
        } else {
            return false;
        }
    }
    
    public boolean borrarPermiso(int user, int type, EntityManager em){
        
        PaPermisosAsesor entity = new PaPermisosAsesor();
        
        entity = PaPermisosAsesorDAO.consultarPermiso(user, type, em);
        
        if (entity == null) {
            return false;
        } else {
            PaPermisosAsesorDAO.delete(entity, em);
            return true;
        }
        
    }
    
    public List<PtTipoPermisoVO> cargarPermisos(int usuario, EntityManager em) {
        List<PtTipoPermiso> permisos = PaPermisosAsesorDAO.cargarPermisos(usuario, em);
        List<PtTipoPermisoVO> permisosVO = new ArrayList<PtTipoPermisoVO>();
        for (PtTipoPermiso t: permisos){
            permisosVO.add(t.toVO());
        }
        return permisosVO;
    }
    
    public List<PtTipoPermisoVO> cargarPermisosTotales(int usuario, EntityManager em) {
        List<PtTipoPermiso> permisos = PaPermisosAsesorDAO.cargarPermisosTotales(usuario, em);
        List<PtTipoPermisoVO> permisosVO = new ArrayList<PtTipoPermisoVO>();
        for (PtTipoPermiso t: permisos){
            permisosVO.add(t.toVO());
        }
        return permisosVO;
    }
}
