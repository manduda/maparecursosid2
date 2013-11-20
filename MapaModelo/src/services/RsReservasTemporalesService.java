/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CcCodigosCortosDAO;
import daos.CdCodigosMncDAO;
import daos.CiCodigosIinDAO;
import daos.ClCodigosLdDAO;
import daos.MaMarcacionAbreviadaDAO;
import daos.NuNumeracionDAO;
import daos.NrCodigosNrnDAO;
import daos.RsReservasTemporalesDAO;
import daos.SeSenalizacionDAO;
import entities.CcCodigosCortos;
import entities.CdCodigosMnc;
import entities.CiCodigosIin;
import entities.ClCodigosLd;
import entities.EsEstado;
import entities.MaMarcacionAbreviada;
import entities.NrCodigosNrn;
import entities.NuNumeracion;
import entities.RsReservasTemporales;
import entities.SeSenalizacion;
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
    
    public void recuperarReservasTemporales(EntityManager em){
        List<RsReservasTemporales> reservasTemporales = RsReservasTemporalesDAO.getListRecuperar(em);
        //List<RsReservasTemporalesVO> reservasTemporalesVO = new ArrayList<RsReservasTemporalesVO>();        
        for (RsReservasTemporales r : reservasTemporales) {
            //reservasTemporalesVO.add(r.toVO());
            RsReservasTemporales reserva = r;
            
            if(r.getRstTipoRecurso().equals("Numeracion")){
                NuNumeracion numeracion = new NuNumeracion();
                numeracion = NuNumeracionDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                numeracion.setEsnCodigo(estado);
                NuNumeracionDAO.merge(numeracion, em);
            }
            else if (r.getRstTipoRecurso().equals("Senalizacion")){
                SeSenalizacion senalizacion = new SeSenalizacion();
                senalizacion = SeSenalizacionDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                senalizacion.setEsnCodigo(estado);
                SeSenalizacionDAO.merge(senalizacion, em);
            }
            else if (r.getRstTipoRecurso().equals("CodigosLd")){
                ClCodigosLd codigosLd = new ClCodigosLd();
                codigosLd = ClCodigosLdDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                codigosLd.setEsnCodigo(estado);
                ClCodigosLdDAO.merge(codigosLd, em);
            }
            else if (r.getRstTipoRecurso().equals("CodigosCortos")){
                CcCodigosCortos codigosCortos = new CcCodigosCortos();
                codigosCortos = CcCodigosCortosDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                codigosCortos.setEsnCodigo(estado);
                CcCodigosCortosDAO.merge(codigosCortos, em);
            }
            else if (r.getRstTipoRecurso().equals("MarcacionAbreviada")){
                MaMarcacionAbreviada marcacionAbreviada = new MaMarcacionAbreviada();
                marcacionAbreviada = MaMarcacionAbreviadaDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                marcacionAbreviada.setEsnCodigo(estado);
                MaMarcacionAbreviadaDAO.merge(marcacionAbreviada, em);
            }
            else if (r.getRstTipoRecurso().equals("CodigosMnc")){
                CdCodigosMnc codigosMnc = new CdCodigosMnc();
                codigosMnc = CdCodigosMncDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                codigosMnc.setEsnCodigo(estado);
                CdCodigosMncDAO.merge(codigosMnc, em);
            }
            else if (r.getRstTipoRecurso().equals("CodigosNrn")){
                NrCodigosNrn codigosNrn = new NrCodigosNrn();
                codigosNrn = NrCodigosNrnDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                codigosNrn.setEsnCodigo(estado);
                NrCodigosNrnDAO.merge(codigosNrn, em);
            }
            else if (r.getRstTipoRecurso().equals("CodigosIin")){
                CiCodigosIin codigosIin = new CiCodigosIin();
                codigosIin = CiCodigosIinDAO.findbyId(r.getRsnCodigoRecurso(), em);
                EsEstado estado = new EsEstado();
                estado.setEsnCodigo(1);
                codigosIin.setEsnCodigo(estado);
                CiCodigosIinDAO.merge(codigosIin, em);
            }
            
            reserva.setRstEstado('N');
            RsReservasTemporalesDAO.merge(reserva, em);

        }
        //return reservasTemporalesVO;
    }
    
    
    
}
