/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.NuNumeracionDAO;
import entities.EmOperador;
import entities.NuNumeracion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.NdNdcVO;
import vo.NuNumeracionVO;
import vo.SkRegionVO;

/**
 *
 * @author miguel.duran
 */
public class NuNumeracionService {
    public NuNumeracionVO getVOFromEntity(NuNumeracion entity){
        NuNumeracionVO vo = new NuNumeracionVO();
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
        // Region
        SkRegionVO region = new SkRegionVO();
        region.setSkRegionCode(entity.getSkRegionCode().getSkRegionCode());
        region.setSkRegionNombre(entity.getSkRegionCode().getSkRegionNombre());
        vo.setSkRegionCode(region);
        //------------------------------------
        // NDC
        NdNdcVO ndc = new NdNdcVO();
        ndc.setNdnCodigo(entity.getNdnCodigo().getNdnCodigo());
        ndc.setNdtNombre(entity.getNdnCodigo().getNdtNombre());
        vo.setNdnCodigo(ndc);
        //------------------------------------
        vo.setNunCodigo(entity.getNunCodigo());
        vo.setNunInicio(entity.getNunInicio());
        vo.setNunFin(entity.getNunFin());
        vo.setNutObservaciones(entity.getNutObservaciones());

        return vo;
    }
    
    public NuNumeracionVO getById(BigDecimal id, EntityManager em){
        NuNumeracion entity = NuNumeracionDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }

    public List<NuNumeracionVO> getList(EntityManager em){
        List<NuNumeracion> numeracion = NuNumeracionDAO.getList(em);
        List<NuNumeracionVO> numeracionVO = new ArrayList<NuNumeracionVO>();        
        NuNumeracionVO vo = new NuNumeracionVO();
        int size = numeracion.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(numeracion.get(i));
            numeracionVO.add(vo);
        }
        return numeracionVO;
    }

    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = NuNumeracionDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        EmOperadorVO vo = new EmOperadorVO();
        int size = operador.size();
        
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntityOperador(operador.get(i));
            operadorVO.add(vo);
        }
        return operadorVO;
    }

    public EmOperadorVO getVOFromEntityOperador(EmOperador entity){
        EmOperadorVO vo = new EmOperadorVO();
        vo.setEmrCodigo(entity.getEmrCodigo());
        vo.setEmtNombre(entity.getEmtNombre());
        return vo;
    }
}
