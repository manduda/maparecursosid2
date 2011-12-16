/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CcCodigosCortosDAO;
import daos.ClCodigosLdDAO;
import daos.EmOperadorDAO;
import daos.EtEstadoTramiteDAO;
import daos.GtGestionTramiteDAO;
import daos.RsReservasTemporalesDAO;
import daos.SeSenalizacionDAO;
import daos.TcTramiteCcDAO;
import daos.TlTramiteLdDAO;
import daos.TrTramitesDAO;
import daos.TsTramiteSenalizacionDAO;
import daos.UsUsuariosDAO;
import entities.AcAccion;
import entities.CcCodigosCortos;
import entities.ClCodigosLd;
import entities.EmOperador;
import entities.EsEstado;
import entities.EtEstadoTramite;
import entities.GtGestionTramite;
import entities.Municipios;
import entities.RsReservasTemporales;
import entities.SeSenalizacion;
import entities.TcTramiteCc;
import entities.TlTramiteLd;
import entities.TrTramites;
import entities.TsTramiteSenalizacion;
import entities.UsUsuarios;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import vo.EmOperadorVO;
import vo.EtEstadoTramiteVO;
import vo.TcTramiteCcVO;
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
                    AcAccion accion = new AcAccion();
                    accion.setAcnCodigo(3);
                    t.setAcnCodigo(accion);
                    TsTramiteSenalizacionDAO.merge(t, em);
                    
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
                    AcAccion accion = new AcAccion();
                    accion.setAcnCodigo(3);
                    t.setAcnCodigo(accion);
                    TlTramiteLdDAO.merge(t, em);
                    
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
        
        if (!entity.getTcTramiteCcCollection().isEmpty()) {
            Collection<TcTramiteCc> recursos = entity.getTcTramiteCcCollection();
            for (TcTramiteCc t : recursos) {
                if (t.getAcnCodigo().getAcnCodigo() == 2) {
                    AcAccion accion = new AcAccion();
                    accion.setAcnCodigo(3);
                    t.setAcnCodigo(accion);
                    TcTramiteCcDAO.merge(t, em);
                    
                    CcCodigosCortos codigosCortos = CcCodigosCortosDAO.findbyId(t.getCcnCodigo().getCcnCodigo(), em);
                    codigosCortos.setEmrCodigo(t.getEmrCodigo());
                    EsEstado estadoRecurso = new EsEstado();
                    estadoRecurso.setEsnCodigo(2);
                    codigosCortos.setEsnCodigo(estadoRecurso);
                    codigosCortos.setCctObservaciones(t.getTctObservaciones());
                    CcCodigosCortosDAO.merge(codigosCortos, em);
                }
            }
        }
        
    }
    
    public void terminarTramite(TrTramitesVO vo, EntityManager em){
        
        //actualizamos la información del trámite
        TrTramites entity = new TrTramites();
        
        entity = TrTramitesDAO.findbyId(vo.getTrnCodigo(), em);
        
        EtEstadoTramite estado = new EtEstadoTramite();
        estado.setEtnCodigo(5);
        entity.setEtnCodigo(estado);
        entity.setTrfFecha(vo.getTrfFecha());
        entity.setTrnResolucion(vo.getTrnResolucion());
        entity.setTrfFechaResolucion(vo.getTrfFechaResolucion());
        entity.setTrtObservaciones(vo.getTrtObservaciones());
        
        TrTramitesDAO.merge(entity, em);
        
        //insertamos registro en el historial
        GtGestionTramite gestionTramite = new GtGestionTramite();
        gestionTramite.setEtnCodigo(estado);
        gestionTramite.setGtfFecha(vo.getTrfFecha());
        gestionTramite.setGttObservaciones(vo.getTrtObservaciones());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);

        UsUsuarios usuario = new UsUsuarios();
        usuario.setUsnCodigo(vo.getUsnCodigo().getUsnCodigo());

        gestionTramite.setUsnCodigo(usuario);
        gestionTramite.setTrnCodigo(entity);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
        
        //actualizamos la información de los registros de señalización
        if (!entity.getTsTramiteSenalizacionCollection().isEmpty()) {
            Collection<TsTramiteSenalizacion> recursos = entity.getTsTramiteSenalizacionCollection();
            for (TsTramiteSenalizacion t : recursos) {
                
                SeSenalizacion senalizacion = SeSenalizacionDAO.findbyId(t.getSenCodigo().getSenCodigo(), em);
                
                senalizacion.setCodigoMunicipio(t.getCodigoMunicipio());
                senalizacion.setEmrCodigo(t.getEmrCodigo());
                senalizacion.setSetNombreNodo(t.getTstNombreNodo());
                senalizacion.setSetMarcaModelo(t.getTstMarcaModelo());
                senalizacion.setSetDireccion(t.getTstDireccion());
                senalizacion.setSetObservaciones(t.getTstObservaciones());
                
                EsEstado estadoRecurso = new EsEstado();

                switch(t.getAcnCodigo().getAcnCodigo()){
                    case 1:
                        estadoRecurso.setEsnCodigo(1);
                        break;
                    case 2:
                        estadoRecurso.setEsnCodigo(3);
                        break;
                    case 3:
                        estadoRecurso.setEsnCodigo(3);
                        break;
                    case 4:
                        estadoRecurso.setEsnCodigo(4);
                        break;
                    case 5:
                        if(t.getTstReservaTemporal()=='S'){
                           Date fecha; 
                           //Cálculo de la fecha de liberación
                            Calendar ahoraCal = Calendar.getInstance();//instancia una clase de calendario
                            ahoraCal.setTime(vo.getTrfFechaResolucion());//se le asigna la fecha de la resolución
                            ahoraCal.add(Calendar.MONTH, t.getTsnMesesLiberacion());//se le suman los meses escogidos a la fecha de la resolución
                            fecha=ahoraCal.getTime();
                            //instanciamiento del entity
                            RsReservasTemporales resTemp = new RsReservasTemporales();
                            
                            //cargue del entity
                            resTemp.setTrnCodigo(entity);
                            resTemp.setRsnCodigo(RsReservasTemporalesDAO.getMaxId(em)+1);
                            resTemp.setRsnCodigoRecurso(t.getSenCodigo().getSenCodigo());
                            resTemp.setRstEstado('S');
                            resTemp.setRstTipoRecurso("Senalizacion");
                            resTemp.setRsfFechaLiberacion(fecha);
                            
                            //Guardado del entity
                            RsReservasTemporalesDAO.persist(resTemp, em);

                            //Modificación del estado del recurso a reserva
                            estadoRecurso.setEsnCodigo(4);
                            
                        }else{
                            estadoRecurso.setEsnCodigo(1);   
                        }
                         break;
                } 
                senalizacion.setEsnCodigo(estadoRecurso);
                SeSenalizacionDAO.merge(senalizacion, em);
            }
        }
        
        //actualizamos la información de los registros de codigos LD
        if (!entity.getTlTramiteldCollection().isEmpty()) {
            Collection<TlTramiteLd> recursos = entity.getTlTramiteldCollection();
            for (TlTramiteLd t : recursos) {
               
                ClCodigosLd codigoLd = ClCodigosLdDAO.findbyId(t.getClnCodigo().getClnCodigo(), em);
                
                codigoLd.setEmrCodigo(t.getEmrCodigo());
                codigoLd.setCltObservaciones(t.getTltObservaciones());

                EsEstado estadoRecurso = new EsEstado();
                
                switch(t.getAcnCodigo().getAcnCodigo()){
                    case 1:
                        estadoRecurso.setEsnCodigo(1);
                        break;
                    case 2:
                        estadoRecurso.setEsnCodigo(3);
                        break;
                    case 3:
                        estadoRecurso.setEsnCodigo(3);
                        break;
                    case 4:
                        estadoRecurso.setEsnCodigo(4);
                        break;
                    case 5:
                        estadoRecurso.setEsnCodigo(1);
                        break;
                }
                codigoLd.setEsnCodigo(estadoRecurso);
                ClCodigosLdDAO.merge(codigoLd, em);
            }
        }
        
        //actualizamos la información de los registros de codigos cortos
        if (!entity.getTcTramiteCcCollection().isEmpty()) {
            Collection<TcTramiteCc> recursos = entity.getTcTramiteCcCollection();
            for (TcTramiteCc t : recursos) {
               
                CcCodigosCortos codigoCortos = CcCodigosCortosDAO.findbyId(t.getCcnCodigo().getCcnCodigo(), em);
                
                codigoCortos.setEmrCodigo(t.getEmrCodigo());
                codigoCortos.setCctObservaciones(t.getTctObservaciones());

                EsEstado estadoRecurso = new EsEstado();
                
                switch(t.getAcnCodigo().getAcnCodigo()){
                    case 1:
                        estadoRecurso.setEsnCodigo(1);
                        break;
                    case 2:
                        estadoRecurso.setEsnCodigo(3);
                        break;
                    case 3:
                        estadoRecurso.setEsnCodigo(3);
                        break;
                    case 4:
                        estadoRecurso.setEsnCodigo(4);
                        break;
                    case 5:
                        estadoRecurso.setEsnCodigo(1);
                        break;
                }
                codigoCortos.setEsnCodigo(estadoRecurso);
                CcCodigosCortosDAO.merge(codigoCortos, em);
            }
        }
        
    }
    
    public Integer cambiarUsuarioTramite(TrTramitesVO vo, int codigoNuevoUsuarioSIUST, EntityManager em){
        /*
         * 1: Usuario cambiado correctamente
         * 2: El ususario actual y el nuevo son iguales
        */
        
        TrTramites entity = new TrTramites();
        
        entity = TrTramitesDAO.findbyId(vo.getTrnCodigo(), em);
        
        int codigoUsuarioSIUST = entity.getUsnCodigo().getCodigoSIUST().getUserCode();
        //int codigoNuevoUsuarioSIUST = UsUsuariosDAO.findbyId(codigoNuevoUsuario, em).getCodigoSIUST().getUserCode();
        
        if(codigoUsuarioSIUST == codigoNuevoUsuarioSIUST){
            return 2;
        }
        
        UsUsuarios nuevoUsuario = UsUsuariosDAO.buscarUsuario(codigoNuevoUsuarioSIUST, em);
        entity.setUsnCodigo(nuevoUsuario);
        
        Date fecha = new Date();
        entity.setTrfFecha(fecha);
        
        TrTramitesDAO.merge(entity, em);
        
        GtGestionTramite gestionTramite = new GtGestionTramite();
        EtEstadoTramite estado = new EtEstadoTramite();
        estado.setEtnCodigo(7);
        gestionTramite.setEtnCodigo(estado);
        gestionTramite.setGtfFecha(fecha);
        gestionTramite.setGttObservaciones(vo.getTrtObservaciones());
        gestionTramite.setGtnCodigo(GtGestionTramiteDAO.getMaxId(em)+1);
        UsUsuarios usuario = new UsUsuarios();
        usuario.setUsnCodigo(vo.getUsnCodigo().getUsnCodigo());
        gestionTramite.setUsnCodigo(usuario);
        
        TrTramites tramite = new TrTramites();
        tramite.setTrnCodigo(vo.getTrnCodigo());
        gestionTramite.setTrnCodigo(tramite);
        
        GtGestionTramiteDAO.persist(gestionTramite, em);
        
        return 1;

    }
    
    public List<EtEstadoTramiteVO> getListaEstadoTramites(EntityManager em){
        List<EtEstadoTramite> estado = EtEstadoTramiteDAO.getList(em);
        List<EtEstadoTramiteVO> estadoVO = new ArrayList<EtEstadoTramiteVO>();
        for (EtEstadoTramite t: estado){
            estadoVO.add(t.toVO());
        }
        return estadoVO;
    }
    
    public TrTramitesVO getById(int id, EntityManager em){
        TrTramites entity = TrTramitesDAO.findbyId(id, em);
        return entity.toVO();
    }
    
    public List<TrTramitesVO> getTramitesAsesor(int userCode, EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.getTramitesAsesor(userCode, em);
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
    
    public List<TrTramitesVO> cargarTramites(int first, int max, int tramiteId, int usuario, String operador, int estado, int radicado, EntityManager em){
        List<TrTramites> tramites = TrTramitesDAO.cargarTramites(first, max, tramiteId, usuario, operador, estado, radicado, em);
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
        char reservaTemporal='N';
        int mesesLiberacion =0;
        
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
                
                if(vo.getTstReservaTemporal()== 'S'){
                    reservaTemporal='S';
                    mesesLiberacion=vo.getTsnMesesLiberacion();
                }

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
        entity.setTstReservaTemporal(reservaTemporal);
        entity.setTsnMesesLiberacion(mesesLiberacion);
        
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
    
    public Integer agregarRecurso(TcTramiteCcVO vo, EntityManager em){
        /*
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        
        if((vo.getTrnCodigo().getTrnCodigo()==0)||(vo.getCcnCodigo().getCcnCodigo()==0)||(vo.getAcnCodigo().getAcnCodigo()==0)||(vo.getEmrCodigo().getEmrCodigo().equals(""))){
            return 2;
        }
        
        if (TcTramiteCcDAO.findIdCodigosCortos(vo.getCcnCodigo().getCcnCodigo(), em)) {
            return 4;
        }
        
        TcTramiteCc entity = new TcTramiteCc();
        
        TrTramites tramite = TrTramitesDAO.findbyId(vo.getTrnCodigo().getTrnCodigo(), em);
        String operadorTramite = tramite.getEmrCodigo().getEmrCodigo();
        
        CcCodigosCortos codigosCortos = CcCodigosCortosDAO.findbyId(vo.getCcnCodigo().getCcnCodigo(), em);
        String operadorRecurso = codigosCortos.getEmrCodigo().getEmrCodigo();
        
        EmOperador operador = new EmOperador();
        AcAccion accion = new AcAccion();
        
        switch(vo.getAcnCodigo().getAcnCodigo()){
            case 1: //LIBERAR
                break;
            case 2: //PREASIGNAR
                if(codigosCortos.getEsnCodigo().getEsnCodigo()!=1){ // El estado del recurso no es libre
                    return 6;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                break;
            case 3: //ASIGNAR
                break;
            case 4: //RESERVAR
                break;
            case 5: //RECUPERAR
                if(codigosCortos.getEsnCodigo().getEsnCodigo()!=3){ // El estado del recurso no es asignado
                    return 5;
                }
                
                if(!operadorTramite.equals(operadorRecurso)){ // El operador del trámite es diferente al del recurso
                    return 3;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                break;
        }
        
        accion.setAcnCodigo(vo.getAcnCodigo().getAcnCodigo());
        
        entity.setTcnCodigo(TcTramiteCcDAO.getMaxId(em)+1);
        entity.setTrnCodigo(tramite);
        entity.setCcnCodigo(codigosCortos);
        entity.setAcnCodigo(accion);
        entity.setTcnRadicado(vo.getTcnRadicado());
        entity.setEmrCodigo(operador);
        entity.setTctObservaciones(vo.getTctObservaciones());
        
        TcTramiteCcDAO.persist(entity, em);
        
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
    
    public boolean eliminarRecurso(TcTramiteCcVO vo, EntityManager em){
        TcTramiteCc entity = new TcTramiteCc();
        
        entity = TcTramiteCcDAO.findbyId(vo.getTcnCodigo(), em);

        TcTramiteCcDAO.delete(entity, em);
        
        return true;
    }
    
    public List<TsTramiteSenalizacionVO> buscarTramiteSenalizacion(int senCodigo, int acnCodigo, EntityManager em){
        List<TsTramiteSenalizacion> tramiteSenalizacion = TsTramiteSenalizacionDAO.findTramiteSenalizacion(senCodigo, acnCodigo, em);
        List<TsTramiteSenalizacionVO> tramiteSenalizacionVO = new ArrayList<TsTramiteSenalizacionVO>();        
        for (TsTramiteSenalizacion t : tramiteSenalizacion) {
            tramiteSenalizacionVO.add(t.toVO());
        }
        return tramiteSenalizacionVO;
    }
    
}
