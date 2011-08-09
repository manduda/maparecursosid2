/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.ReRegionDAO;
import entities.ReRegion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.ReRegionVO;
import vo.RtTipoRegionVO;

/**
 *
 * @author miguel.duran
 */
public class ReRegionService {
    public ReRegionVO getVOFromEntity(ReRegion entity){
        ReRegionVO vo = new ReRegionVO();
        // Tipo Region Señalización
        RtTipoRegionVO tipoRegion = new RtTipoRegionVO();
        tipoRegion.setRtnCodigo(entity.getRtnCodigo().getRtnCodigo());
        tipoRegion.setRttNombre(entity.getRtnCodigo().getRttNombre());
        vo.setRtnCodigo(tipoRegion);
        //------------------------------------
        vo.setRenCodigo(entity.getRenCodigo());
        vo.setRetNombre(entity.getRetNombre());
        return vo;
    }
    
    public ReRegionVO getById(int id, EntityManager em){
        ReRegion entity = ReRegionDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }

    public List<ReRegionVO> getList(EntityManager em){
        List<ReRegion> region = ReRegionDAO.getList(em);
        List<ReRegionVO> regionVO = new ArrayList<ReRegionVO>();        
        ReRegionVO vo = new ReRegionVO();
        int size = region.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(region.get(i));
            regionVO.add(vo);
        }
        return regionVO;
    }
}
