/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.ClCodigosLdDAO;
import entities.ClCodigosLd;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;

/**
 *
 * @author miguel.duran
 */
public class ClCodigosLdService {
    public ClCodigosLdVO getVOFromEntity(ClCodigosLd entity){
        ClCodigosLdVO vo = new ClCodigosLdVO();
        // Estado
        EsEstadoVO estado = new EsEstadoVO();
        estado.setEsnCodigo(entity.getEsnCodigo().getEsnCodigo());
        estado.setEstNombre(entity.getEsnCodigo().getEstNombre());
        vo.setEsnCodigo(estado);
        //------------------------------------
        // Operador
        EmOperadorVO operador = new EmOperadorVO();
        operador.setEmrCodigo(entity.getEmrCodigo().getEmrCodigo());
        operador.setEmtNombre(entity.getEmrCodigo().getEmtNombre());
        vo.setEmrCodigo(operador);
        //------------------------------------
        vo.setClnCodigo(entity.getClnCodigo());
        vo.setClnCodigoLd(entity.getClnCodigoLd());

        return vo;
    }
    
    public ClCodigosLdVO getById(BigDecimal id, EntityManager em){
        ClCodigosLd entity = ClCodigosLdDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }

    public List<ClCodigosLdVO> getList(EntityManager em){
        List<ClCodigosLd> codigosld = ClCodigosLdDAO.getList(em);
        List<ClCodigosLdVO> codigosldVO = new ArrayList<ClCodigosLdVO>();        
        ClCodigosLdVO vo = new ClCodigosLdVO();
        int size = codigosld.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(codigosld.get(i));
            codigosldVO.add(vo);
        }
        return codigosldVO;
    }
}