/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.NdNdcDAO;
import entities.NdNdc;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.NdNdcVO;
import vo.NtTipondcVO;

/**
 *
 * @author miguel.duran
 */
public class NdNdcService {
    public NdNdcVO getVOFromEntity(NdNdc entity){
        NdNdcVO vo = new NdNdcVO();
        // Tipo NDC      
        NtTipondcVO tipoNDC = new NtTipondcVO();
        tipoNDC.setNtnCodigo(entity.getNtnCodigo().getNtnCodigo());
        tipoNDC.setNtnNombre(entity.getNtnCodigo().getNtnNombre());
        vo.setNtnCodigo(tipoNDC);
        //------------------------------------
        vo.setNdnCodigo(entity.getNdnCodigo());
        vo.setNdtNombre(entity.getNdtNombre());
        return vo;
    }
    
    public NdNdcVO getById(BigDecimal id, EntityManager em){
        NdNdc entity = NdNdcDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }

    public List<NdNdcVO> getList(EntityManager em){
        List<NdNdc> ndc = NdNdcDAO.getList(em);
        List<NdNdcVO> ndcVO = new ArrayList<NdNdcVO>();        
        NdNdcVO vo = new NdNdcVO();
        int size = ndc.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(ndc.get(i));
            ndcVO.add(vo);
        }
        return ndcVO;
    }
}
