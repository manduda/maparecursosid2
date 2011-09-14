/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.SeSenalizacionDAO;
import entities.EmOperador;
import entities.SeSenalizacion;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.ReRegionVO;
import vo.RtTipoRegionVO;
import vo.SeSenalizacionVO;
import vo.MunicipiosVO;
import vo.TeTipoSenalizacionVO;

/**
 *
 * @author miguel.duran
 */
public class SeSenalizacionService {
    public SeSenalizacionVO getVOFromEntity(SeSenalizacion entity){
        SeSenalizacionVO vo = new SeSenalizacionVO();
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
        // Tipo Region Señalizacion
        RtTipoRegionVO tipoRegionSenalizacion = new RtTipoRegionVO();
        tipoRegionSenalizacion.setRtnCodigo(entity.getRenCodigo().getRtnCodigo().getRtnCodigo());
        tipoRegionSenalizacion.setRttNombre(entity.getRenCodigo().getRtnCodigo().getRttNombre());
        //------------------------------------
        // Region Señalizacion
        ReRegionVO regionSenalizacion = new ReRegionVO();
        regionSenalizacion.setRenCodigo(entity.getRenCodigo().getRenCodigo());
        regionSenalizacion.setRetNombre(entity.getRenCodigo().getRetNombre());
        regionSenalizacion.setRtnCodigo(tipoRegionSenalizacion);
        vo.setRenCodigo(regionSenalizacion);
        //------------------------------------
        // Tipo Señalizacion
        TeTipoSenalizacionVO tipoSenalizacion = new TeTipoSenalizacionVO();
        tipoSenalizacion.setTenCodigo(entity.getTenCodigo().getTenCodigo());
        tipoSenalizacion.setTetNombre(entity.getTenCodigo().getTetNombre());
        vo.setTenCodigo(tipoSenalizacion);
        //------------------------------------
        vo.setSenCodigo(entity.getSenCodigo());
        vo.setSenZona(entity.getSenZona());
        vo.setSenPs(entity.getSenPs());
        vo.setSetNombreNodo(entity.getSetNombreNodo());
        vo.setSetMarcaModelo(entity.getSetMarcaModelo());
        vo.setSetDireccion(entity.getSetDireccion());
        vo.setSetObservaciones(entity.getSetObservaciones());

        return vo;
    }
    
    public SeSenalizacionVO getById(int id, EntityManager em){
        SeSenalizacion entity = SeSenalizacionDAO.findbyId(id, em);
        return getVOFromEntity(entity);
    }
    
    public List<SeSenalizacionVO> cargarSenalizacion(int first, int max, String operador, int region, int zona, int ps, int estado, String municipio, String departamento, EntityManager em){
        List<SeSenalizacion> senalizacion = SeSenalizacionDAO.cargarSenalizacion(first, max, operador, region, zona, ps, estado, municipio, departamento, em);
        List<SeSenalizacionVO> senalizacionVO = new ArrayList<SeSenalizacionVO>();        
        SeSenalizacionVO vo = new SeSenalizacionVO();
        int size = senalizacion.size();
        for (int i = 0; i < size; i++) {
            vo = getVOFromEntity(senalizacion.get(i));
            senalizacionVO.add(vo);
        }
        return senalizacionVO;
    }
    
    public int countCargarSenalizacion(String operador, int region, int zona, int ps, int estado, String municipio, String departamento, EntityManager em){
        int senalizacion = SeSenalizacionDAO.countCargarSenalizacion(operador, region, zona, ps, estado, municipio, departamento, em);
        return senalizacion;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = SeSenalizacionDAO.getListOperadores(em);
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
    
    public void transferirSenalizacion (String operadorOrigen, String operadorDestino, EntityManager em){
        SeSenalizacionDAO.transferirSenalizacionDAO(operadorOrigen, operadorDestino, em);
    }
}
