/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.MaMarcacionAbreviadaDAO;
import entities.EmOperador;
import entities.EsEstado;
import entities.MaMarcacionAbreviada;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.MaMarcacionAbreviadaVO;

/**
 *
 * @author miguel.duran
 */
public class MaMarcacionAbreviadaService {
    /*public MaMarcacionAbreviadaVO getVOFromEntity(MaMarcacionAbreviada entity){
        MaMarcacionAbreviadaVO vo = new MaMarcacionAbreviadaVO();
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
        vo.setManCodigo(entity.getManCodigo());
        vo.setManCodigoMarcacion(entity.getManCodigoMarcacion());
        vo.setMatObservaciones(entity.getMatObservaciones());

        return vo;
    }*/
    
    public MaMarcacionAbreviadaVO getById(int id, EntityManager em){
        MaMarcacionAbreviada entity = MaMarcacionAbreviadaDAO.findbyId(id, em);
        return entity.toVO();
    }

    public List<MaMarcacionAbreviadaVO> getList(EntityManager em){
        List<MaMarcacionAbreviada> codigosMarcacion = MaMarcacionAbreviadaDAO.getList(em);
        List<MaMarcacionAbreviadaVO> codigosMarcacionVO = new ArrayList<MaMarcacionAbreviadaVO>();        
        for (MaMarcacionAbreviada ma : codigosMarcacion) {
            codigosMarcacionVO.add(ma.toVO());
        }
        return codigosMarcacionVO;
    }
    
    public List<MaMarcacionAbreviadaVO> cargarMarcacionAbreviada(int first, int max, String operador, int codigoMarcacion, int estado, EntityManager em){
        List<MaMarcacionAbreviada> codigosMarcacionAbreviada = MaMarcacionAbreviadaDAO.cargarMarcacionAbreviada(first, max, operador, codigoMarcacion, estado, em);
        List<MaMarcacionAbreviadaVO> codigosMarcacionAbreviadaVO = new ArrayList<MaMarcacionAbreviadaVO>();        
        for (MaMarcacionAbreviada ma : codigosMarcacionAbreviada) {
            codigosMarcacionAbreviadaVO.add(ma.toVO());
        }
        return codigosMarcacionAbreviadaVO;
    }
    
    public int countCargarMarcacionAbreviada(String operador, int codigoMarcacion, int estado, EntityManager em){
        int codigosMarcacionAbreviada = MaMarcacionAbreviadaDAO.countCargarMarcacionAbreviada(operador, codigoMarcacion, estado, em);
        return codigosMarcacionAbreviada;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = MaMarcacionAbreviadaDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }

    public int reservarLiberarMarcacionAbreviada (MaMarcacionAbreviadaVO vo, EntityManager em,int accion){
        //si la accion es 0 se libera
        //si la accion es 1 se reserva
        
        RsReservasTemporalesService reservasTemporales = new RsReservasTemporalesService();
        Boolean reservado = false;
        reservado = reservasTemporales.consultaReservaTemporal(vo.getManCodigo(), "MarcacionAbreviada", em);
        if (accion == 0 && reservado) {
            return 3;
        }
        
        MaMarcacionAbreviada entity = new MaMarcacionAbreviada();
        
        entity = MaMarcacionAbreviadaDAO.findbyId(vo.getManCodigo(), em);
        if (accion == 1 && entity.getEsnCodigo().getEsnCodigo() == 1){
            EsEstado estado = new EsEstado();
            estado.setEsnCodigo(4);
            entity.setEsnCodigo(estado);
            MaMarcacionAbreviadaDAO.merge(entity, em); 
            return 1;
        }
        else if(accion==0 && entity.getEsnCodigo().getEsnCodigo() == 4){
            
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                entity.setEsnCodigo(estado);
                MaMarcacionAbreviadaDAO.merge(entity, em); 
                return 1;
        }
            else{
                return 2;
            }
        }
}
