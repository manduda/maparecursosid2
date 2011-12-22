/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.NdNdcDAO;
import daos.NtTipoNdcDAO;
import entities.NdNdc;
import entities.NtTipoNdc;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.NdNdcVO;
import vo.NtTipoNdcVO;

/**
 *
 * @author miguel.duran
 */
public class NdNdcService {
    public NdNdcVO getVOFromEntity(NdNdc entity){
        NdNdcVO vo = new NdNdcVO();
        // Tipo NDC      
        NtTipoNdcVO tipoNDC = new NtTipoNdcVO();
        tipoNDC.setNtnCodigo(entity.getNtnCodigo().getNtnCodigo());
        tipoNDC.setNttNombre(entity.getNtnCodigo().getNttNombre());
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
        List<String> ndc = NdNdcDAO.getList(em);
        List<NdNdcVO> ndcVO = new ArrayList<NdNdcVO>();        
        NdNdcVO vo = new NdNdcVO();
        int size = ndc.size();
        for (int i = 0; i < size; i++) {
            vo = new NdNdcVO();
            vo.setNdtNombre(ndc.get(i));
            vo.setNdnCodigo(Integer.parseInt(ndc.get(i)));
            //vo = getVOFromEntity(ndc.get(i));
            ndcVO.add(vo);
        }
        return ndcVO;
    }
    
    public List<NtTipoNdcVO> getListTipoNdc(String ndc, EntityManager em){
        List<NtTipoNdc> tipoNdc = NtTipoNdcDAO.getList(ndc, em);
        List<NtTipoNdcVO> tipoNdcVO = new ArrayList<NtTipoNdcVO>();
        for (NtTipoNdc nt : tipoNdc) {
            tipoNdcVO.add(nt.toVO());
        }
        return tipoNdcVO;
    }
}
