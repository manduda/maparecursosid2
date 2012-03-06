/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.EsEstadoDAO;
import entities.EsEstado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.EsEstadoVO;

/**
 *
 * @author miguel.duran
 */
public class EsEstadoService {
    public EsEstadoVO getVOFromEntity(EsEstado entity){
        EsEstadoVO vo = new EsEstadoVO();
        vo.setEsnCodigo(entity.getEsnCodigo());
        vo.setEstNombre(entity.getEstNombre());

        return vo;
    }
    
    public EsEstadoVO getById(int id, EntityManager em){
        EsEstado entity = EsEstadoDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }

    public List<EsEstadoVO> getList(EntityManager em){
        List<EsEstado> estado = EsEstadoDAO.getList(em);
        List<EsEstadoVO> estadoVO = new ArrayList<EsEstadoVO>();        
        for (EsEstado o : estado) {
            estadoVO.add(o.toVO());
        }
        return estadoVO;
    }
    
    public List<EsEstadoVO> getListEstados1xy(EntityManager em){
        List<EsEstado> estado = EsEstadoDAO.getListEstados1xy(em);
        List<EsEstadoVO> estadoVO = new ArrayList<EsEstadoVO>();        
        for (EsEstado o : estado) {
            estadoVO.add(o.toVO());
        }
        return estadoVO;
    }
    
}
