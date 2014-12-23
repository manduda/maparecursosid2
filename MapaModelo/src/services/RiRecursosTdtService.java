/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CaCanalTdtDAO;
import daos.MuMultiplexDAO;
import daos.RiRecursosTdtDAO;
import daos.RnTipoRecursoTdtDAO;
import daos.RrTipoRedTdtDAO;
import daos.TtTipoServicioTdtDAO;
import entities.CaCanalTdt;
import entities.EmOperador;
import entities.EsEstado;
import entities.MuMultiplex;
import entities.RiRecursosTdt;
import entities.RnTipoRecursoTdt;
import entities.RrTipoRedTdt;
import entities.TtTipoServicioTdt;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CaCanalTdtVO;
import vo.EmOperadorVO;
import vo.MuMultiplexVO;
import vo.RiRecursosTdtVO;
import vo.RnTipoRecursoTdtVO;
import vo.RrTipoRedTdtVO;
import vo.TtTipoServicioTdtVO;

/**
 *
 * @author miguel.duran
 */
public class RiRecursosTdtService {
    public RiRecursosTdtVO getById(int id, EntityManager em){
        RiRecursosTdt entity = RiRecursosTdtDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public List<RiRecursosTdtVO> cargarRecursosTdT(int first, int max, String operador, int codigo, int tipoRecurso, int tipoRed, int canal, String municipio, String departamento, int tipoServicio, int multiplex, int estado, EntityManager em){
        List<RiRecursosTdt> recursosTdt = RiRecursosTdtDAO.cargarRecursosTdt(first, max, operador, codigo, tipoRecurso, tipoRed, canal, municipio, departamento, tipoServicio, multiplex, estado, em);
        List<RiRecursosTdtVO> recursosTdtVO = new ArrayList<RiRecursosTdtVO>();        
        for (RiRecursosTdt c : recursosTdt) {
            recursosTdtVO.add(c.toVO());
        }
        return recursosTdtVO;
    }
    
    public int countCargarRecursosTdt(String operador, int codigo, int tipoRecurso, int tipoRed, int canal, String municipio, String departamento, int tipoServicio, int multiplex, int estado, EntityManager em){
        int recursosTdt = RiRecursosTdtDAO.countCargarRecursosTdt(operador, codigo, tipoRecurso, tipoRed, canal, municipio, departamento, tipoServicio, multiplex, estado, em);
        return recursosTdt;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = RiRecursosTdtDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }
    
    public List<RnTipoRecursoTdtVO> getListaTipoRecurso(EntityManager em){
        List<RnTipoRecursoTdt> tipoRecurso = RnTipoRecursoTdtDAO.getList(em);
        List<RnTipoRecursoTdtVO> tipoRecursoVO = new ArrayList<RnTipoRecursoTdtVO>();        
        for (RnTipoRecursoTdt m : tipoRecurso) {
            tipoRecursoVO.add(m.toVO());
        }
        return tipoRecursoVO;
    }
    
    public List<RrTipoRedTdtVO> getListaTipoRed(EntityManager em){
        List<RrTipoRedTdt> tipoRed = RrTipoRedTdtDAO.getList(em);
        List<RrTipoRedTdtVO> tipoRedVO = new ArrayList<RrTipoRedTdtVO>();        
        for (RrTipoRedTdt m : tipoRed) {
            tipoRedVO.add(m.toVO());
        }
        return tipoRedVO;
    }
    
    public List<CaCanalTdtVO> getListaCanal(EntityManager em){
        List<CaCanalTdt> canal = CaCanalTdtDAO.getList(em);
        List<CaCanalTdtVO> canalVO = new ArrayList<CaCanalTdtVO>();        
        for (CaCanalTdt m : canal) {
            canalVO.add(m.toVO());
        }
        return canalVO;
    }
    
    public List<TtTipoServicioTdtVO> getListaServicioTdt(EntityManager em){
        List<TtTipoServicioTdt> servicio = TtTipoServicioTdtDAO.getList(em);
        List<TtTipoServicioTdtVO> servicioVO = new ArrayList<TtTipoServicioTdtVO>();        
        for (TtTipoServicioTdt m : servicio) {
            servicioVO.add(m.toVO());
        }
        return servicioVO;
    }
    
    public List<MuMultiplexVO> getListaMultiplex(EntityManager em){
        List<MuMultiplex> multiplex = MuMultiplexDAO.getList(em);
        List<MuMultiplexVO> multiplexVO = new ArrayList<MuMultiplexVO>();        
        for (MuMultiplex m : multiplex) {
            multiplexVO.add(m.toVO());
        }
        return multiplexVO;
    }
    
    public void transferirRecursosTdt (String operadorOrigen, String operadorDestino, EntityManager em){
        RiRecursosTdtDAO.transferirRecursosTdt(operadorOrigen, operadorDestino, em);
    }
    
    public Integer reservarLiberarRecursosTdT(RiRecursosTdtVO vo, EntityManager em, int accion) {
        //si la accion es 0 se libera
        //si la accion es 1 se reserva
        
        RsReservasTemporalesService reservasTemporales = new RsReservasTemporalesService();
        Boolean reservado = false;
        reservado = reservasTemporales.consultaReservaTemporal(vo.getRinCodigo(), "RecursosTdt", em);
        if (accion == 0 && reservado) {
            return 3;
        }
        
        RiRecursosTdt entity = new RiRecursosTdt();
        
        entity = RiRecursosTdtDAO.findbyId(vo.getRinCodigo(), em);
        
        if (accion==1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            RiRecursosTdtDAO.merge(entity, em); 
            return 1;
        }else if (accion==0 && entity.getEsnCodigo().getEsnCodigo() == 4){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(1);
            entity.setEsnCodigo(estado);
            RiRecursosTdtDAO.merge(entity, em); 
            return 1;
        }
        else{
            return 2;
        }
    }
}
