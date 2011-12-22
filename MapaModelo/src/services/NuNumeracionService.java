/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.NuNumeracionDAO;
import entities.EmOperador;
import entities.EsEstado;
import entities.Municipios;
import entities.NdNdc;
import entities.NuNumeracion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.NdNdcVO;
import vo.NuNumeracionVO;
import vo.MunicipiosVO;
import vo.NtTipoNdcVO;

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
        // Departamento
        DepartamentosVO departamento = new DepartamentosVO();
        departamento.setCodigoDepartamento(entity.getCodigoMunicipio().getCodigoDepartamento().getCodigoDepartamento());
        departamento.setNombreDepartamento(entity.getCodigoMunicipio().getCodigoDepartamento().getNombreDepartamento());
        //------------------------------------
        // Municipio
        MunicipiosVO municipio = new MunicipiosVO();
        municipio.setCodigoMunicipio(entity.getCodigoMunicipio().getCodigoMunicipio());
        municipio.setNombreMunicipio(entity.getCodigoMunicipio().getNombreMunicipio());
        municipio.setCodigoDepartamento(departamento);
        vo.setCodigoMunicipio(municipio);
        //------------------------------------
        // NDC
        NdNdcVO ndc = new NdNdcVO();
        ndc.setNdnCodigo(entity.getNdnCodigo().getNdnCodigo());
        ndc.setNdtNombre(entity.getNdnCodigo().getNdtNombre());
        
    
        NtTipoNdcVO tipoNDC = new NtTipoNdcVO();
        tipoNDC.setNtnCodigo(entity.getNdnCodigo().getNtnCodigo().getNtnCodigo());
        tipoNDC.setNttNombre(entity.getNdnCodigo().getNtnCodigo().getNttNombre());
        ndc.setNtnCodigo(tipoNDC);
        
        vo.setNdnCodigo(ndc);
        //------------------------------------
        vo.setNunCodigo(entity.getNunCodigo());
        vo.setNunInicio(entity.getNunInicio());
        vo.setNunFin(entity.getNunFin());
        vo.setNutObservaciones(entity.getNutObservaciones());

        return vo;
    }
    
    public NuNumeracionVO getById(int id, EntityManager em){
        NuNumeracion entity = NuNumeracionDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }

    public List<NuNumeracionVO> cargarNumeracion(int first, int max, String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento, EntityManager em){
        List<NuNumeracion> numeracion = NuNumeracionDAO.cargarNumeracion(first, max, operador, ndc, tipoNdc, inicio, fin, estado, municipio, departamento, em);
        List<NuNumeracionVO> numeracionVO = new ArrayList<NuNumeracionVO>();        
        NuNumeracionVO vo = new NuNumeracionVO();
        int size = numeracion.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(numeracion.get(i));
            numeracionVO.add(vo);
        }
        return numeracionVO;
    }

    public int countCargarNumeracion(String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento, EntityManager em){
        int numeracion = NuNumeracionDAO.countCargarNumeracion(operador, ndc, tipoNdc, inicio, fin, estado, municipio, departamento, em);
        return numeracion;
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
   
    public void transferirNumeracion (String operadorOrigen, String operadorDestino, EntityManager em){
        NuNumeracionDAO.transferirNumeracionDAO(operadorOrigen, operadorDestino, em);
    }

    public Integer reservarLiberarNumeracion(NuNumeracionVO vo, EntityManager em, int accion) {
        NuNumeracion entity = new NuNumeracion();
        
        
        NdNdc ndc = new NdNdc();
        ndc.setNdnCodigo(vo.getNdnCodigo().getNdnCodigo());
        entity.setNdnCodigo(ndc);
        entity.setNunInicio(vo.getNunInicio());
        entity.setNunFin(vo.getNunFin());
        entity.setNutObservaciones(vo.getNutObservaciones());
        Municipios municipio = new Municipios();
        municipio.setCodigoMunicipio(vo.getCodigoMunicipio().getCodigoMunicipio());
        entity.setCodigoMunicipio(municipio);
        EmOperador operador = new EmOperador();
        operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
        entity.setEmrCodigo(operador);
        EsEstado estado = new EsEstado();
        estado.setEsnCodigo(vo.getEsnCodigo().getEsnCodigo());
        entity.setEsnCodigo(estado);
        
        if (accion == 1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            NuNumeracionDAO.updateNumeracion(entity, em);
            return 1;
        } else if (accion == 0 && entity.getEsnCodigo().getEsnCodigo() == 4){
            estado.setEsnCodigo(1);
            entity.setEsnCodigo(estado);
            NuNumeracionDAO.updateNumeracion(entity, em); 
            return 1;
        } else {
            return 2;
        }
    }
}
