/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CcCodigosCortosDAO;
import daos.MdModalidadCcDAO;
import entities.CcCodigosCortos;
import entities.EmOperador;
import entities.MdModalidadCc;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.CcCodigosCortosVO;
import vo.EmOperadorVO;
import vo.MdModalidadCcVO;

/**
 *
 * @author miguel.duran
 */
public class CcCodigosCortosService {
    public CcCodigosCortosVO getById(int id, EntityManager em){
        CcCodigosCortos entity = CcCodigosCortosDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public List<CcCodigosCortosVO> cargarCodigosCortos(int first, int max, String operador, int modalidad, int codigo, int estado, EntityManager em){
        List<CcCodigosCortos> codigosCortos = CcCodigosCortosDAO.cargarCodigosCortos(first, max, operador, modalidad, codigo, estado, em);
        List<CcCodigosCortosVO> codigosCortosVO = new ArrayList<CcCodigosCortosVO>();        
        for (CcCodigosCortos c : codigosCortos) {
            codigosCortosVO.add(c.toVO());
        }
        return codigosCortosVO;
    }
    
    public int countCargarCodigosCortos(String operador, int modalidad, int codigo, int estado, EntityManager em){
        int codigosCortos = CcCodigosCortosDAO.countCargarCodigosCortos(operador, modalidad, codigo, estado, em);
        return codigosCortos;
    }
    
    public List<EmOperadorVO> getListOperadores(EntityManager em){
        List<EmOperador> operador = CcCodigosCortosDAO.getListOperadores(em);
        List<EmOperadorVO> operadorVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador o : operador) {
            operadorVO.add(o.toVO());
        }
        return operadorVO;
    }
    
    public List<MdModalidadCcVO> getListaModalidadCc(EntityManager em){
        List<MdModalidadCc> modalidad = MdModalidadCcDAO.getList(em);
        List<MdModalidadCcVO> modalidadVO = new ArrayList<MdModalidadCcVO>();        
        for (MdModalidadCc m : modalidad) {
            modalidadVO.add(m.toVO());
        }
        return modalidadVO;
    }
}
