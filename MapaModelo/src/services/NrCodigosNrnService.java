/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.NrCodigosNrnDAO;
import entities.EmOperador;
import entities.EsEstado;
import entities.NrCodigosNrn;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.NrCodigosNrnVO;

/**
 *
 * @author miguel.duran
 */
public class NrCodigosNrnService {
    /*public NrCodigosNrnVO getVOFromEntity(NrCodigosNrn entity){
        NrCodigosNrnVO vo = new NrCodigosNrnVO();
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
        vo.setNrnCodigo(entity.getNrnCodigo());
        vo.setNrnCodigoNrn(entity.getNrnCodigoNrn());
        vo.setNrtObservaciones(entity.getNrtObservaciones());

        return vo;
    }*/
    
    public NrCodigosNrnVO getById(int id, EntityManager em){
        NrCodigosNrn entity = NrCodigosNrnDAO.findbyId(id, em);
        return entity.toVO();
    }

    
    public List<NrCodigosNrnVO> cargarCodigosNrn(int first, int max, String operador, int codigoNrn, int estado, EntityManager em){
        List<NrCodigosNrn> codigosNrn = NrCodigosNrnDAO.cargarCodigosNrn(first, max, operador, codigoNrn, estado, em);
        List<NrCodigosNrnVO> codigosNrnVO = new ArrayList<NrCodigosNrnVO>();        
        for (NrCodigosNrn c : codigosNrn) {
            codigosNrnVO.add(c.toVO());
        }
        return codigosNrnVO;
    }
    
    public int countCargarCodigosNrn(String operador, int codigoNrn, int estado, EntityManager em){
        int codigosNrn = NrCodigosNrnDAO.countCargarCodigosNrn(operador, codigoNrn, estado, em);
        return codigosNrn;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = NrCodigosNrnDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }
    
    public void transferirCodigosNrn (String operadorOrigen, String operadorDestino, EntityManager em){
        NrCodigosNrnDAO.transferirCodigosNrn(operadorOrigen, operadorDestino, em);
    }

    public int reservarLiberarCodigosNrn (NrCodigosNrnVO vo, EntityManager em,int accion){
        //si la accion es 0 se libera
        //si la accion es 1 se reserva
        
        RsReservasTemporalesService reservasTemporales = new RsReservasTemporalesService();
        Boolean reservado = false;
        reservado = reservasTemporales.consultaReservaTemporal(vo.getNrnCodigo(), "CodigosNrn", em);
        if (accion == 0 && reservado) {
            return 3;
        }
        
        NrCodigosNrn entity = new NrCodigosNrn();
        
        entity = NrCodigosNrnDAO.findbyId(vo.getNrnCodigo(), em);
        if (accion == 1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            NrCodigosNrnDAO.merge(entity, em); 
            return 1;
        } else if (accion==0 && entity.getEsnCodigo().getEsnCodigo() == 4) {
            
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(1);
            entity.setEsnCodigo(estado);
            NrCodigosNrnDAO.merge(entity, em); 
            return 1;
        } else {
            return 2;
        }
    }
    
}
