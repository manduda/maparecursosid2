/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.Nc1xyDAO;
import entities.EsEstado;
import entities.MoModalidad1xy;
import entities.Nc1xy;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.MoModalidad1xyVO;
import vo.Nc1xyVO;

/**
 *
 * @author miguel.duran
 */
public class Nc1xyService {
    public Nc1xyVO getById(int id, EntityManager em){
        Nc1xy entity = Nc1xyDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public List<Nc1xyVO> cargarCodigos1xy(int first, int max, int codigo1xy, int codigoModalidad, int estado, String servicio, EntityManager em){
        List<Nc1xy> codigos1xy = Nc1xyDAO.cargarCodigos1xy(first, max, codigo1xy, codigoModalidad, estado, servicio, em);
        List<Nc1xyVO> codigos1xyVO = new ArrayList<Nc1xyVO>();        
        for (Nc1xy c : codigos1xy) {
            codigos1xyVO.add(c.toVO());
        }
        return codigos1xyVO;
    }
    
    public int countCargarCodigos1xy(int codigo1xy, int codigoModalidad, int estado, String servicio, EntityManager em){
        int codigos1xy = Nc1xyDAO.countCargarCodigos1xy(codigo1xy, codigoModalidad, estado, servicio, em);
        return codigos1xy;
    }

    public List<MoModalidad1xyVO> getListModalidad1xy(EntityManager em){
        List<MoModalidad1xy> modalidad1xy = Nc1xyDAO.getListModalidad1xy(em);
        List<MoModalidad1xyVO> modalidad1xyVO = new ArrayList<MoModalidad1xyVO>();        
        for (MoModalidad1xy o : modalidad1xy) {
            modalidad1xyVO.add(o.toVO());
        }
        return modalidad1xyVO;
    }
    
    public List<String> getListServicios(EntityManager em){
        List<String> servicios = Nc1xyDAO.getListServicios(em);
        return servicios;
    }
    
    public void editarCodigo1xy (Nc1xyVO vo, EntityManager em){
        Nc1xy entity = new Nc1xy();
        entity = Nc1xyDAO.findbyId(vo.getNcnCodigo(), em);
        
        //Estado
        EsEstado estado = new EsEstado();
        estado.setEsnCodigo(vo.getEsnCodigo().getEsnCodigo());
        
        //Modalidad
        MoModalidad1xy modalidad = new MoModalidad1xy();
        modalidad.setMonCodigo(vo.getMonCodigo().getMonCodigo());
        
        entity.setEsnCodigo(estado);
        entity.setMonCodigo(modalidad);
        entity.setNctServicio(vo.getNctServicio());
        entity.setNctObservaciones(vo.getNctObservaciones());
        
        Nc1xyDAO.merge(entity, em);
        
    }
}
