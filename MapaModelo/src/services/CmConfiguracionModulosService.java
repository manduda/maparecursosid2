/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CmConfiguracionModulosDAO;
import entities.CmConfiguracionModulos;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CmConfiguracionModulosVO;

/**
 *
 * @author miguel.duran
 */
public class CmConfiguracionModulosService {
    public CmConfiguracionModulosVO getById(int id, EntityManager em){
        CmConfiguracionModulos entity = CmConfiguracionModulosDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public void editarConfiguracionModulo (CmConfiguracionModulosVO vo, EntityManager em){
        CmConfiguracionModulos entity = new CmConfiguracionModulos();
        entity = CmConfiguracionModulosDAO.findbyId(vo.getCmnCodigo(), em);
        entity.setCmtActivo(vo.getCmtActivo());
        CmConfiguracionModulosDAO.merge(entity, em);
        
    }
    
    public List<CmConfiguracionModulosVO> getList(EntityManager em){
        List<CmConfiguracionModulos> modulos = CmConfiguracionModulosDAO.getList(em);
        List<CmConfiguracionModulosVO> modulosVO = new ArrayList<CmConfiguracionModulosVO>();
        for (CmConfiguracionModulos u : modulos) {
            modulosVO.add(u.toVO());
        }
        return modulosVO;
    }
}
