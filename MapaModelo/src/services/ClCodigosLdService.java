/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.ClCodigosLdDAO;
import entities.ClCodigosLd;
import entities.EmOperador;
import entities.EsEstado;
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
    /*public ClCodigosLdVO getVOFromEntity(ClCodigosLd entity){
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
        vo.setCltObservaciones(entity.getCltObservaciones());

        return vo;
    }*/
    
    public ClCodigosLdVO getById(int id, EntityManager em){
        ClCodigosLd entity = ClCodigosLdDAO.findbyId(id, em);
        return entity.toVO();
    }

    public List<ClCodigosLdVO> getList(EntityManager em){
        List<ClCodigosLd> codigosld = ClCodigosLdDAO.getList(em);
        List<ClCodigosLdVO> codigosldVO = new ArrayList<ClCodigosLdVO>();        
        for (ClCodigosLd c : codigosld) {
            codigosldVO.add(c.toVO());
        }
        return codigosldVO;
    }
    
    public List<ClCodigosLdVO> cargarCodigosLd(int first, int max, String operador, int codigoLd, int estado, EntityManager em){
        List<ClCodigosLd> codigosld = ClCodigosLdDAO.cargarCodigosLd(first, max, operador, codigoLd, estado, em);
        List<ClCodigosLdVO> codigosldVO = new ArrayList<ClCodigosLdVO>();        
        for (ClCodigosLd c : codigosld) {
            codigosldVO.add(c.toVO());
        }
        return codigosldVO;
    }
    
    public int countCargarCodigosLd(String operador, int codigoLd, int estado, EntityManager em){
        int codigosld = ClCodigosLdDAO.countCargarCodigosLd(operador, codigoLd, estado, em);
        return codigosld;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = ClCodigosLdDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }

    public int reservarLiberarCodigoLd (ClCodigosLdVO vo, EntityManager em,int accion){
        //si la accion es 0 se libera
        //si la accion es 1 se reserva
        
        RsReservasTemporalesService reservasTemporales = new RsReservasTemporalesService();
        Boolean reservado = false;
        reservado = reservasTemporales.consultaReservaTemporal(vo.getClnCodigo(), "CodigosLd", em);
        if (accion == 0 && reservado) {
            return 3;
        }
        
        ClCodigosLd entity = new ClCodigosLd();
        
        entity = ClCodigosLdDAO.findbyId(vo.getClnCodigo(), em);
        if (accion == 1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            ClCodigosLdDAO.merge(entity, em); 
            return 1;
        }
        else if(accion==0 && entity.getEsnCodigo().getEsnCodigo() == 4){
            
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                entity.setEsnCodigo(estado);
                ClCodigosLdDAO.merge(entity, em); 
                return 1;
        }
            else{
                return 2;
            }
        }

        
        
}