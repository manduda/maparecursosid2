/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.ClCodigosLdDAO;
import daos.CoConfiguracionDAO;
import daos.EmOperadorDAO;
import daos.GtGestionTramiteDAO;
import daos.SeSenalizacionDAO;
import daos.TlTramiteLdDAO;
import daos.TrTramitesDAO;
import daos.TsTramiteSenalizacionDAO;
import entities.AcAccion;
import entities.ClCodigosLd;
import entities.EmOperador;
import entities.EsEstado;
import entities.EtEstadoTramite;
import entities.GtGestionTramite;
import entities.Municipios;
import entities.SeSenalizacion;
import entities.TlTramiteLd;
import entities.TrTramites;
import entities.TsTramiteSenalizacion;
import entities.UsUsuarios;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import vo.EmOperadorVO;
import vo.TlTramiteLdVO;
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
        gestionTramite.setGttObservaciones(vo.getTrtObservaciones());
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
        gestionTramite.setGttObservaciones(vo.getTrtObservaciones());
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
        gestionTramite.setGttObservaciones(vo.getTrtObservaciones());
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
        gestionTramite.setGttObservaciones(vo.getTrtObservaciones());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);
        gestionTramite.setUsnCodigo(usuario);
        gestionTramite.setTrnCodigo(entity);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
    }
    
    public void aprobarTramite(TrTramitesVO vo, EntityManager em){
        TrTramites entity = new TrTramites();
        
        entity = TrTramitesDAO.findbyId(vo.getTrnCodigo(), em);
        
        EtEstadoTramite estado = new EtEstadoTramite();
        estado.setEtnCodigo(4);
        
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
        gestionTramite.setGttObservaciones(vo.getTrtObservaciones());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);
        gestionTramite.setUsnCodigo(usuario);
        gestionTramite.setTrnCodigo(entity);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
        
        if (!entity.getTsTramiteSenalizacionCollection().isEmpty()) {
            Collection<TsTramiteSenalizacion> recursos = entity.getTsTramiteSenalizacionCollection();
            for (TsTramiteSenalizacion t : recursos) {
                if (t.getAcnCodigo().getAcnCodigo() == 2) {
                    SeSenalizacion senalizacion = SeSenalizacionDAO.findbyId(t.getSenCodigo().getSenCodigo(), em);
                    senalizacion.setCodigoMunicipio(t.getCodigoMunicipio());
                    senalizacion.setEmrCodigo(t.getEmrCodigo());
                    EsEstado estadoRecurso = new EsEstado();
                    estadoRecurso.setEsnCodigo(2);
                    senalizacion.setEsnCodigo(estadoRecurso);
                    senalizacion.setSetNombreNodo(t.getTstNombreNodo());
                    senalizacion.setSetMarcaModelo(t.getTstMarcaModelo());
                    senalizacion.setSetDireccion(t.getTstDireccion());
                    senalizacion.setSetObservaciones(t.getTstObservaciones());
                    SeSenalizacionDAO.merge(senalizacion, em);
                }
            }
        }
        
        if (!entity.getTlTramiteldCollection().isEmpty()) {
            Collection<TlTramiteLd> recursos = entity.getTlTramiteldCollection();
            for (TlTramiteLd t : recursos) {
                if (t.getAcnCodigo().getAcnCodigo() == 2) {
                    ClCodigosLd codigoLd = ClCodigosLdDAO.findbyId(t.getClnCodigo().getClnCodigo(), em);
                    codigoLd.setEmrCodigo(t.getEmrCodigo());
                    EsEstado estadoRecurso = new EsEstado();
                    estadoRecurso.setEsnCodigo(2);
                    codigoLd.setEsnCodigo(estadoRecurso);
                    codigoLd.setCltObservaciones(t.getTltObservaciones());
                    ClCodigosLdDAO.merge(codigoLd, em);
                }
            }
        }

        
        
        
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
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        
        if((vo.getTrnCodigo().getTrnCodigo()==0)||(vo.getSenCodigo().getSenCodigo()==0)||(vo.getAcnCodigo().getAcnCodigo()==0)||(vo.getCodigoMunicipio().getCodigoMunicipio().equals(""))||(vo.getEmrCodigo().getEmrCodigo().equals(""))){
            return 2;
        }
        
        if (TsTramiteSenalizacionDAO.findIdSenalizacion(vo.getSenCodigo().getSenCodigo(), em)) {
            return 4;
        }
        
        TsTramiteSenalizacion entity = new TsTramiteSenalizacion();
        
        TrTramites tramite = TrTramitesDAO.findbyId(vo.getTrnCodigo().getTrnCodigo(), em);
        String operadorTramite = tramite.getEmrCodigo().getEmrCodigo();
        
        SeSenalizacion senalizacion = SeSenalizacionDAO.findbyId(vo.getSenCodigo().getSenCodigo(), em);
        String operadorRecurso = senalizacion.getEmrCodigo().getEmrCodigo();
        
        Municipios municipio = new Municipios();
        EmOperador operador = new EmOperador();
        AcAccion accion = new AcAccion();
        String nombreNodo = "";
        String marcaModelo = "";
        String direccion = "";
        
        switch(vo.getAcnCodigo().getAcnCodigo()){
            case 1: //LIBERAR
                break;
            case 2: //PREASIGNAR
                if(senalizacion.getEsnCodigo().getEsnCodigo()!=1){ // El estado de la señalización no es libre
                    return 6;
                }
                municipio.setCodigoMunicipio(vo.getCodigoMunicipio().getCodigoMunicipio());
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                nombreNodo = vo.getTstNombreNodo();
                marcaModelo = vo.getTstMarcaModelo();
                direccion = vo.getTstDireccion();
                
                break;
            case 3: //ASIGNAR
                break;
            case 4: //RESERVAR
                break;
            case 5: //RECUPERAR
                if(senalizacion.getEsnCodigo().getEsnCodigo()!=3){ // El estado de la señalización no es asignado
                    return 5;
                }
                
                if(!operadorTramite.equals(operadorRecurso)){ // El operador del trámite es diferente al del recurso
                    return 3;
                }
                municipio.setCodigoMunicipio(senalizacion.getCodigoMunicipio().getCodigoMunicipio());
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                nombreNodo = "";
                marcaModelo = "";
                direccion = "";

                break;
        }
        
        accion.setAcnCodigo(vo.getAcnCodigo().getAcnCodigo());
        
        entity.setTsnCodigo(TsTramiteSenalizacionDAO.getMaxId(em)+1);
        entity.setTrnCodigo(tramite);
        entity.setSenCodigo(senalizacion);
        entity.setAcnCodigo(accion);
        entity.setTsnRadicado(vo.getTsnRadicado());
        entity.setCodigoMunicipio(municipio);
        entity.setEmrCodigo(operador);
        entity.setTstNombreNodo(nombreNodo);
        entity.setTstMarcaModelo(marcaModelo);
        entity.setTstDireccion(direccion);
        entity.setTstObservaciones(vo.getTstObservaciones());
        
        TsTramiteSenalizacionDAO.persist(entity, em);
        
        return 1;
        
    }
    
    public Integer agregarRecurso(TlTramiteLdVO vo, EntityManager em){
        /*
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        
        if((vo.getTrnCodigo().getTrnCodigo()==0)||(vo.getClnCodigo().getClnCodigo()==0)||(vo.getAcnCodigo().getAcnCodigo()==0)||(vo.getEmrCodigo().getEmrCodigo().equals(""))){
            return 2;
        }
        
        if (TlTramiteLdDAO.findIdCodigosLd(vo.getClnCodigo().getClnCodigo(), em)) {
            return 4;
        }
        
        TlTramiteLd entity = new TlTramiteLd();
        
        TrTramites tramite = TrTramitesDAO.findbyId(vo.getTrnCodigo().getTrnCodigo(), em);
        String operadorTramite = tramite.getEmrCodigo().getEmrCodigo();
        
        ClCodigosLd codigosld = ClCodigosLdDAO.findbyId(vo.getClnCodigo().getClnCodigo(), em);
        String operadorRecurso = codigosld.getEmrCodigo().getEmrCodigo();
        
        EmOperador operador = new EmOperador();
        AcAccion accion = new AcAccion();
        
        switch(vo.getAcnCodigo().getAcnCodigo()){
            case 1: //LIBERAR
                break;
            case 2: //PREASIGNAR
                if(codigosld.getEsnCodigo().getEsnCodigo()!=1){ // El estado del recurso no es libre
                    return 6;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                break;
            case 3: //ASIGNAR
                break;
            case 4: //RESERVAR
                break;
            case 5: //RECUPERAR
                if(codigosld.getEsnCodigo().getEsnCodigo()!=3){ // El estado del recurso no es asignado
                    return 5;
                }
                
                if(!operadorTramite.equals(operadorRecurso)){ // El operador del trámite es diferente al del recurso
                    return 3;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                break;
        }
        
        accion.setAcnCodigo(vo.getAcnCodigo().getAcnCodigo());
        
        entity.setTlnCodigo(TlTramiteLdDAO.getMaxId(em)+1);
        entity.setTrnCodigo(tramite);
        entity.setClnCodigo(codigosld);
        entity.setAcnCodigo(accion);
        entity.setTlnRadicado(vo.getTlnRadicado());
        entity.setEmrCodigo(operador);
        entity.setTltObservaciones(vo.getTltObservaciones());
        
        TlTramiteLdDAO.persist(entity, em);
        
        return 1;
        
    }
    
    public boolean eliminarRecurso(TsTramiteSenalizacionVO vo, EntityManager em){
        TsTramiteSenalizacion entity = new TsTramiteSenalizacion();
        
        entity = TsTramiteSenalizacionDAO.findbyId(vo.getTsnCodigo(), em);

        TsTramiteSenalizacionDAO.delete(entity, em);
        
        return true;
    }
    
    public boolean eliminarRecurso(TlTramiteLdVO vo, EntityManager em){
        TlTramiteLd entity = new TlTramiteLd();
        
        entity = TlTramiteLdDAO.findbyId(vo.getTlnCodigo(), em);

        TlTramiteLdDAO.delete(entity, em);
        
        return true;
    }
    
}
