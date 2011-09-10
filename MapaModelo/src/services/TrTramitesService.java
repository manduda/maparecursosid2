/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CoConfiguracionDAO;
import daos.EmOperadorDAO;
import daos.GtGestionTramiteDAO;
import daos.SeSenalizacionDAO;
import daos.TrTramitesDAO;
import daos.TsTramiteSenalizacionDAO;
import entities.AcAccion;
import entities.EmOperador;
import entities.EtEstadoTramite;
import entities.GtGestionTramite;
import entities.Municipios;
import entities.SeSenalizacion;
import entities.TrTramites;
import entities.TsTramiteSenalizacion;
import entities.UsUsuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import vo.EmOperadorVO;
import vo.TrTramitesVO;
import vo.TsTramiteSenalizacionVO;

/**
 *
 * @author miguel.duran
 */
public class TrTramitesService {
    public void crearTramite(TrTramitesVO vo, EntityManager em){
        TrTramites entity = new TrTramites();
        
        EmOperador operador = new EmOperador();
        operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
        
        EtEstadoTramite estado = new EtEstadoTramite();
        estado.setEtnCodigo(1);
        
        UsUsuarios usuario = new UsUsuarios();
        usuario.setUsnCodigo(vo.getUsnCodigo().getUsnCodigo());
        
        entity.setTrnCodigo(TrTramitesDAO.getMaxId(em)+1);
        entity.setEmrCodigo(operador);
        entity.setEtnCodigo(estado);
        entity.setUsnCodigo(usuario);
        entity.setTrfFecha(vo.getTrfFecha());
        
        TrTramitesDAO.persist(entity, em);
        
        GtGestionTramite gestionTramite = new GtGestionTramite();
        gestionTramite.setEtnCodigo(estado);
        gestionTramite.setGtfFecha(vo.getTrfFecha());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);
        gestionTramite.setUsnCodigo(usuario);
        gestionTramite.setTrnCodigo(entity);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
    }
    
    public void archivarTramite(TrTramitesVO vo, EntityManager em){
        TrTramites entity = new TrTramites();
        
        entity = TrTramitesDAO.findbyId(vo.getTrnCodigo(), em);
        
        EtEstadoTramite estado = new EtEstadoTramite();
        estado.setEtnCodigo(6);
        
        UsUsuarios usuario = new UsUsuarios();
        usuario.setUsnCodigo(vo.getUsnCodigo().getUsnCodigo());
        
        //entity.setTrnCodigo(vo.getTrnCodigo());
        entity.setEtnCodigo(estado);
        //entity.setUsnCodigo(usuario);
        entity.setTrfFecha(vo.getTrfFecha());
        
        TrTramitesDAO.merge(entity, em);
        
        GtGestionTramite gestionTramite = new GtGestionTramite();
        gestionTramite.setEtnCodigo(estado);
        gestionTramite.setGtfFecha(vo.getTrfFecha());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);
        gestionTramite.setUsnCodigo(usuario);
        gestionTramite.setTrnCodigo(entity);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
    }
    
    public void enviarTramite(TrTramitesVO vo, EntityManager em){
        TrTramites entity = new TrTramites();
        
        entity = TrTramitesDAO.findbyId(vo.getTrnCodigo(), em);
        
        EtEstadoTramite estado = new EtEstadoTramite();
        estado.setEtnCodigo(2);
        
        UsUsuarios usuario = new UsUsuarios();
        usuario.setUsnCodigo(vo.getUsnCodigo().getUsnCodigo());
        
        //entity.setTrnCodigo(vo.getTrnCodigo());
        entity.setEtnCodigo(estado);
        //entity.setUsnCodigo(usuario);
        entity.setTrfFecha(vo.getTrfFecha());
        
        TrTramitesDAO.merge(entity, em);
        
        GtGestionTramite gestionTramite = new GtGestionTramite();
        gestionTramite.setEtnCodigo(estado);
        gestionTramite.setGtfFecha(vo.getTrfFecha());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);
        gestionTramite.setUsnCodigo(usuario);
        gestionTramite.setTrnCodigo(entity);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
    }
    
    public void devolverTramite(TrTramitesVO vo, EntityManager em){
        TrTramites entity = new TrTramites();
        
        entity = TrTramitesDAO.findbyId(vo.getTrnCodigo(), em);
        
        EtEstadoTramite estado = new EtEstadoTramite();
        estado.setEtnCodigo(3);
        
        UsUsuarios usuario = new UsUsuarios();
        usuario.setUsnCodigo(vo.getUsnCodigo().getUsnCodigo());
        
        //entity.setTrnCodigo(vo.getTrnCodigo());
        entity.setEtnCodigo(estado);
        //entity.setUsnCodigo(usuario);
        entity.setTrfFecha(vo.getTrfFecha());
        
        TrTramitesDAO.merge(entity, em);
        
        GtGestionTramite gestionTramite = new GtGestionTramite();
        gestionTramite.setEtnCodigo(estado);
        gestionTramite.setGtfFecha(vo.getTrfFecha());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);
        gestionTramite.setUsnCodigo(usuario);
        gestionTramite.setTrnCodigo(entity);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
    }
    
    public TrTramitesVO getById(int id, EntityManager em){
        TrTramites entity = TrTramitesDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public List<TrTramitesVO> getTramitesAsesor(int usnCodigo, EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.getTramitesAsesor(usnCodigo, em);
        List<TrTramitesVO> tramitesVO = new ArrayList<TrTramitesVO>();        
        for (TrTramites t : tramites) {
            tramitesVO.add(t.toVO());
        }
        return tramitesVO;
    }
    
    public List<TrTramitesVO> getTramitesCreados(int usnCodigo, EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.getTramitesCreados(usnCodigo,em);
        List<TrTramitesVO> tramitesVO = new ArrayList<TrTramitesVO>();        
        for (TrTramites t : tramites) {
            tramitesVO.add(t.toVO());
        }
        return tramitesVO;
    }
    
    public List<TrTramitesVO> getTramitesDevueltos(int usnCodigo, EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.getTramitesDevueltos(usnCodigo,em);
        List<TrTramitesVO> tramitesVO = new ArrayList<TrTramitesVO>();        
        for (TrTramites t : tramites) {
            tramitesVO.add(t.toVO());
        }
        return tramitesVO;
    }
    
    public List<TrTramitesVO> getTramitesEnviados(EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.getTramitesEnviados(em);
        List<TrTramitesVO> tramitesVO = new ArrayList<TrTramitesVO>();        
        for (TrTramites t : tramites) {
            tramitesVO.add(t.toVO());
        }
        return tramitesVO;
    }
    
    public List<TrTramitesVO> getTramitesAprobados(EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.getTramitesAprobados(em);
        List<TrTramitesVO> tramitesVO = new ArrayList<TrTramitesVO>();        
        for (TrTramites t : tramites) {
            tramitesVO.add(t.toVO());
        }
        return tramitesVO;
    }
    
    public List<TrTramitesVO> getTramitesTerminados(EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.getTramitesTerminados(em);
        List<TrTramitesVO> tramitesVO = new ArrayList<TrTramitesVO>();        
        for (TrTramites t : tramites) {
            tramitesVO.add(t.toVO());
        }
        return tramitesVO;
    }
    
    public List<EmOperadorVO> cargarOperadores(EntityManager em){
        List<EmOperador> operadores = EmOperadorDAO.cargarOperadores(em);
        List<EmOperadorVO> operadoresVO = new ArrayList<EmOperadorVO>();        
        for (EmOperador t : operadores) {
            operadoresVO.add(t.toVO());
        }
        return operadoresVO;
    }
    
    public Integer agregarRecurso(TsTramiteSenalizacionVO vo, EntityManager em){
        /*
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
        */
        
        if((vo.getTrnCodigo().getTrnCodigo()==0)||(vo.getSenCodigo().getSenCodigo()==0)||(vo.getAcnCodigo().getAcnCodigo()==0)||(vo.getCodigoMunicipio().getCodigoMunicipio().equals(""))){
            return 2;
        }
        
        TrTramites tramite = TrTramitesDAO.findbyId(vo.getTrnCodigo().getTrnCodigo(), em);
        String operadorTramite = tramite.getEmrCodigo().getEmrCodigo();
        
        SeSenalizacion senalizacion = SeSenalizacionDAO.findbyId(vo.getSenCodigo().getSenCodigo(), em);
        String operadorRecurso = senalizacion.getEmrCodigo().getEmrCodigo();
        
        if(!operadorTramite.equals(operadorRecurso)){
            return 3;
        }
        
        if (TsTramiteSenalizacionDAO.findIdSenalizacion(vo.getSenCodigo().getSenCodigo(), em)) {
            return 4;
        }
         
        TsTramiteSenalizacion entity = new TsTramiteSenalizacion();
        
        AcAccion accion = new AcAccion();
        accion.setAcnCodigo(vo.getAcnCodigo().getAcnCodigo());
        
        Municipios municipio = new Municipios();
        municipio.setCodigoMunicipio(senalizacion.getCodigoMunicipio().getCodigoMunicipio());
        
        EmOperador operador = new EmOperador();
        operador.setEmrCodigo(CoConfiguracionDAO.findbyId(1, em).getCotValor());
        
        entity.setTsnCodigo(TsTramiteSenalizacionDAO.getMaxId(em)+1);
        entity.setTrnCodigo(tramite);
        entity.setSenCodigo(senalizacion);
        entity.setAcnCodigo(accion);
        entity.setTsnRadicado(vo.getTsnRadicado());
        entity.setCodigoMunicipio(municipio);
        entity.setEmrCodigo(operador);
        entity.setTstNombreNodo(vo.getTstNombreNodo());
        entity.setTstMarcaModelo(vo.getTstMarcaModelo());
        entity.setTstDireccion(vo.getTstDireccion());
        entity.setTstObservaciones(vo.getTstObservaciones());
        
        TsTramiteSenalizacionDAO.persist(entity, em);
        
        return 1;
        
    }
    
    public boolean eliminarRecurso(TsTramiteSenalizacionVO vo, EntityManager em){
        TsTramiteSenalizacion entity = new TsTramiteSenalizacion();
        
        entity = TsTramiteSenalizacionDAO.findbyId(vo.getTsnCodigo(), em);

        TsTramiteSenalizacionDAO.delete(entity, em);
        
        return true;
    }
    
}
