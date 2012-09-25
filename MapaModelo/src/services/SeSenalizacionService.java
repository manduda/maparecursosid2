/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.RtTipoRegionDAO;
import daos.SeSenalizacionDAO;
import daos.TeTipoSenalizacionDAO;
import entities.EmOperador;
import entities.EsEstado;
import entities.RtTipoRegion;
import entities.SeSenalizacion;
import entities.TeTipoSenalizacion;
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
    /*public SeSenalizacionVO getVOFromEntity(SeSenalizacion entity){
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
    }*/
    
    public SeSenalizacionVO getById(int id, EntityManager em){
        SeSenalizacion entity = SeSenalizacionDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public List<SeSenalizacionVO> cargarSenalizacion(int first, int max, String operador, int region, int zona, int ps, int estado, String municipio, String departamento, int tipoSenalizacion, int tipoRegion, EntityManager em){
        List<SeSenalizacion> senalizacion = SeSenalizacionDAO.cargarSenalizacion(first, max, operador, region, zona, ps, estado, municipio, departamento, tipoSenalizacion, tipoRegion, em);
        List<SeSenalizacionVO> senalizacionVO = new ArrayList<SeSenalizacionVO>();        
        for (SeSenalizacion s : senalizacion) {
            senalizacionVO.add(s.toVO());
        }
        return senalizacionVO;
    }
    
    public int countCargarSenalizacion(String operador, int region, int zona, int ps, int estado, String municipio, String departamento, int tipoSenalizacion, int tipoRegion, EntityManager em){
        int senalizacion = SeSenalizacionDAO.countCargarSenalizacion(operador, region, zona, ps, estado, municipio, departamento, tipoSenalizacion, tipoRegion, em);
        return senalizacion;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = SeSenalizacionDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }
    
    public List<Integer> getListZona(int tipoRegion, EntityManager em){
        List<Integer> zona = SeSenalizacionDAO.getListZona(tipoRegion, em);
        return zona;
    }
    
    public List<TeTipoSenalizacionVO> getListTipoSenalizacion(EntityManager em){
        List<TeTipoSenalizacion> tipoSenalizacion = TeTipoSenalizacionDAO.getList(em);
        List<TeTipoSenalizacionVO> tipoSenalizacionVO = new ArrayList<TeTipoSenalizacionVO>();        
        for (TeTipoSenalizacion r : tipoSenalizacion) {
            tipoSenalizacionVO.add(r.toVO());
        }
        return tipoSenalizacionVO;
    }
    
    public List<RtTipoRegionVO> getListTipoRegionSenalizacion(EntityManager em){
        List<RtTipoRegion> tipoRegionSenalizacion = RtTipoRegionDAO.getList(em);
        List<RtTipoRegionVO> tipoRegionSenalizacionVO = new ArrayList<RtTipoRegionVO>();        
        for (RtTipoRegion r : tipoRegionSenalizacion) {
            tipoRegionSenalizacionVO.add(r.toVO());
        }
        return tipoRegionSenalizacionVO;
    }
    
    /*public EmOperadorVO getVOFromEntityOperador(EmOperador entity){
        EmOperadorVO vo = new EmOperadorVO();
        vo.setEmrCodigo(entity.getEmrCodigo());
        vo.setEmtNombre(entity.getEmtNombre());
        return vo;
    }*/
    
    public void transferirSenalizacion (String operadorOrigen, String operadorDestino, EntityManager em){
        SeSenalizacionDAO.transferirSenalizacionDAO(operadorOrigen, operadorDestino, em);
    }
    
    public int reservarLiberarSenalizacion (SeSenalizacionVO vo, EntityManager em, int accion){
        //si la accion es 0 se libera
        //si la accion es 1 se reserva
        
        RsReservasTemporalesService reservasTemporales = new RsReservasTemporalesService();
        Boolean reservado = false;
        reservado = reservasTemporales.consultaReservaTemporal(vo.getSenCodigo(), "Senalizacion", em);
        if (accion == 0 && reservado) {
            return 3;
        }
        
        SeSenalizacion entity = new SeSenalizacion();
        
        entity = SeSenalizacionDAO.findbyId(vo.getSenCodigo(), em);
        
        if (accion==1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            SeSenalizacionDAO.merge(entity, em); 
            return 1;
        }else if (accion==0 && entity.getEsnCodigo().getEsnCodigo() == 4){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(1);
            entity.setEsnCodigo(estado);
            SeSenalizacionDAO.merge(entity, em); 
            return 1;
        }
        else{
            return 2;
        }
        
    }
}
