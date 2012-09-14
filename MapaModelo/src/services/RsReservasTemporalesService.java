/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.RsReservasTemporalesDAO;
import entities.RsReservasTemporales;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.RsReservasTemporalesVO;
import vo.TrTramitesVO;

/**
 *
 * @author juan.loaiza
 */
public class RsReservasTemporalesService {
    /*public RsReservasTemporalesVO getVOFromEntity(RsReservasTemporales entity) {
        RsReservasTemporalesVO vo = new RsReservasTemporalesVO();
        
        vo.setRsfFechaLiberacion(entity.getRsfFechaLiberacion());
        vo.setRsnCodigo(entity.getRsnCodigo());
        vo.setRsnCodigoRecurso(entity.getRsnCodigoRecurso());
        vo.setRstEstado(entity.getRstEstado());
        vo.setRstTipoRecurso(entity.getRstTipoRecurso());
        
        // Trámite
        TrTramitesVO tramite = new TrTramitesVO();
        tramite.setTrnCodigo(entity.getTrnCodigo().getTrnCodigo());
        vo.setTrnCodigo(tramite);
        //------------------------------------        
        
        return vo;
    }*/
    
    public List<RsReservasTemporalesVO> getList(EntityManager em){
        List<RsReservasTemporales> reservasTemporales = RsReservasTemporalesDAO.getList(em);
        List<RsReservasTemporalesVO> reservasTemporalesVO = new ArrayList<RsReservasTemporalesVO>();        
        for (RsReservasTemporales r : reservasTemporales) {
            reservasTemporalesVO.add(r.toVO());
        }
        return reservasTemporalesVO;
    }
    
    public Boolean consultaReservaTemporal(int codigoRecurso, String tipoRecurso, EntityManager em){
        return RsReservasTemporalesDAO.consultaReservaTemporal(codigoRecurso, tipoRecurso, em);
    }
    
}
