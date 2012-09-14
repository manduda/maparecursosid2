/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CiCodigosIinDAO;
import entities.CiCodigosIin;
import entities.EmOperador;
import entities.EsEstado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CiCodigosIinVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;

/**
 *
 * @author miguel.duran
 */
public class CiCodigosIinService {
    /*public CiCodigosIinVO getVOFromEntity(CiCodigosIin entity){
        CiCodigosIinVO vo = new CiCodigosIinVO();
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
        vo.setCinCodigo(entity.getCinCodigo());
        vo.setCinCodigoIin(entity.getCinCodigoIin());
        vo.setCitObservaciones(entity.getCitObservaciones());

        return vo;
    }*/
    
    public CiCodigosIinVO getById(int id, EntityManager em){
        CiCodigosIin entity = CiCodigosIinDAO.findbyId(id, em);
        return entity.toVO();
    }

    
    public List<CiCodigosIinVO> cargarCodigosIin(int first, int max, String operador, int codigoIin, int estado, EntityManager em){
        List<CiCodigosIin> codigosIin = CiCodigosIinDAO.cargarCodigosIin(first, max, operador, codigoIin, estado, em);
        List<CiCodigosIinVO> codigosIinVO = new ArrayList<CiCodigosIinVO>();        
        for (CiCodigosIin c : codigosIin) {
            codigosIinVO.add(c.toVO());
        }
        return codigosIinVO;
    }
    
    public int countCargarCodigosIin(String operador, int codigoIin, int estado, EntityManager em){
        int codigosIin = CiCodigosIinDAO.countCargarCodigosIin(operador, codigoIin, estado, em);
        return codigosIin;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = CiCodigosIinDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }

    public int reservarLiberarCodigosIin (CiCodigosIinVO vo, EntityManager em,int accion){
        //si la accion es 0 se libera
        //si la accion es 1 se reserva
        
        RsReservasTemporalesService reservasTemporales = new RsReservasTemporalesService();
        Boolean reservado = false;
        reservado = reservasTemporales.consultaReservaTemporal(vo.getCinCodigo(), "CodigosIin", em);
        if (accion == 0 && reservado) {
            return 3;
        }
        
        CiCodigosIin entity = new CiCodigosIin();
        
        entity = CiCodigosIinDAO.findbyId(vo.getCinCodigo(), em);
        if (accion == 1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            CiCodigosIinDAO.merge(entity, em); 
            return 1;
        } else if (accion==0 && entity.getEsnCodigo().getEsnCodigo() == 4) {
            
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(1);
            entity.setEsnCodigo(estado);
            CiCodigosIinDAO.merge(entity, em); 
            return 1;
        } else {
            return 2;
        }
    }
}
