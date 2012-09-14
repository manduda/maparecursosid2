/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CdCodigosMncDAO;
import entities.CdCodigosMnc;
import entities.EmOperador;
import entities.EsEstado;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CdCodigosMncVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;

/**
 *
 * @author miguel.duran
 */
public class CdCodigosMncService {
    /*public CdCodigosMncVO getVOFromEntity(CdCodigosMnc entity){
        CdCodigosMncVO vo = new CdCodigosMncVO();
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
        vo.setCdnCodigo(entity.getCdnCodigo());
        vo.setCdnMnc(entity.getCdnMnc());
        vo.setCdtObservaciones(entity.getCdtObservaciones());

        return vo;
    }*/
    
    public CdCodigosMncVO getById(int id, EntityManager em){
        CdCodigosMnc entity = CdCodigosMncDAO.findbyId(id, em);
        return entity.toVO();
    }

    public List<CdCodigosMncVO> cargarCodigosMnc(int first, int max, String operador, int codigoMnc, int estado, EntityManager em){
        List<CdCodigosMnc> codigosMnc = CdCodigosMncDAO.cargarCodigosMnc(first, max, operador, codigoMnc, estado, em);
        List<CdCodigosMncVO> codigosMncVO = new ArrayList<CdCodigosMncVO>();        
        for (CdCodigosMnc c : codigosMnc) {
            codigosMncVO.add(c.toVO());
        }
        return codigosMncVO;
    }
    
    public int countCargarCodigosMnc(String operador, int codigoMnc, int estado, EntityManager em){
        int codigosMnc = CdCodigosMncDAO.countCargarCodigosMnc(operador, codigoMnc, estado, em);
        return codigosMnc;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = CdCodigosMncDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }

    public int reservarLiberarCodigosMnc (CdCodigosMncVO vo, EntityManager em,int accion){
        //si la accion es 0 se libera
        //si la accion es 1 se reserva
        
        RsReservasTemporalesService reservasTemporales = new RsReservasTemporalesService();
        Boolean reservado = false;
        reservado = reservasTemporales.consultaReservaTemporal(vo.getCdnCodigo(), "CodigosMnc", em);
        if (accion == 0 && reservado) {
            return 3;
        }
        
        CdCodigosMnc entity = new CdCodigosMnc();
        
        entity = CdCodigosMncDAO.findbyId(vo.getCdnCodigo(), em);
        if (accion == 1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            CdCodigosMncDAO.merge(entity, em); 
            return 1;
        }
        else if(accion==0 && entity.getEsnCodigo().getEsnCodigo() == 4){
            
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                entity.setEsnCodigo(estado);
                CdCodigosMncDAO.merge(entity, em); 
                return 1;
        }
            else{
                return 2;
            }
        }
}
