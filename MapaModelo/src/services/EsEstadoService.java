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
        EsEstadoVO vo = new EsEstadoVO();
        int size = estado.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(estado.get(i));
            estadoVO.add(vo);
        }
        return estadoVO;
    }
}
