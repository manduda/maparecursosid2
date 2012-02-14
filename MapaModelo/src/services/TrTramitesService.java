/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.CcCodigosCortosDAO;
import daos.CdCodigosMncDAO;
import daos.ClCodigosLdDAO;
import daos.EmOperadorDAO;
import daos.EtEstadoTramiteDAO;
import daos.GtGestionTramiteDAO;
import daos.MaMarcacionAbreviadaDAO;
import daos.NuNumeracionDAO;
import daos.RsReservasTemporalesDAO;
import daos.SeSenalizacionDAO;
import daos.TaTramiteMaDAO;
import daos.TcTramiteCcDAO;
import daos.TlTramiteLdDAO;
import daos.TmTramiteMncDAO;
import daos.TnTramiteNumeracionDAO;
import daos.TrTramitesDAO;
import daos.TsTramiteSenalizacionDAO;
import daos.UsUsuariosDAO;
import entities.AcAccion;
import entities.CcCodigosCortos;
import entities.CdCodigosMnc;
import entities.ClCodigosLd;
import entities.EmOperador;
import entities.EsEstado;
import entities.EtEstadoTramite;
import entities.GtGestionTramite;
import entities.MaMarcacionAbreviada;
import entities.Municipios;
import entities.NdNdc;
import entities.NuNumeracion;
import entities.RsReservasTemporales;
import entities.SeSenalizacion;
import entities.TaTramiteMa;
import entities.TcTramiteCc;
import entities.TlTramiteLd;
import entities.TmTramiteMnc;
import entities.TnTramiteNumeracion;
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
import vo.TaTramiteMaVO;
import vo.TcTramiteCcVO;
import vo.TlTramiteLdVO;
import vo.TmTramiteMncVO;
import vo.TnTramiteNumeracionVO;
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
        
        if (!entity.getTnTramiteNumeracionCollection().isEmpty()) {
            Collection<TnTramiteNumeracion> recursos = entity.getTnTramiteNumeracionCollection();
            for (TnTramiteNumeracion t : recursos) {
                if (t.getAcnCodigo().getAcnCodigo() == 2) {
                    AcAccion accion = new AcAccion();
                    accion.setAcnCodigo(3);
                    t.setAcnCodigo(accion);
                    TnTramiteNumeracionDAO.merge(t, em);
                    
                    NuNumeracion numeracion = new NuNumeracion();//NuNumeracionDAO.findbyId(t.getSenCodigo().getSenCodigo(), em);
                    numeracion.setCodigoMunicipio(t.getCodigoMunicipio());
                    numeracion.setEmrCodigo(t.getEmrCodigo());
                    EsEstado estadoRecurso = new EsEstado();
                    estadoRecurso.setEsnCodigo(2);
                    numeracion.setEsnCodigo(estadoRecurso);
                    numeracion.setNdnCodigo(t.getNdnCodigo());
                    numeracion.setNunInicio(t.getTnnInicio());
                    numeracion.setNunFin(t.getTnnFin());
                    numeracion.setNutObservaciones(t.getTntObservaciones());
                    NuNumeracionDAO.updateNumeracion(numeracion, em);
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
        
        if (!entity.getTaTramiteMaCollection().isEmpty()) {
            Collection<TaTramiteMa> recursos = entity.getTaTramiteMaCollection();
            for (TaTramiteMa t : recursos) {
                if (t.getAcnCodigo().getAcnCodigo() == 2) {
                    AcAccion accion = new AcAccion();
                    accion.setAcnCodigo(3);
                    t.setAcnCodigo(accion);
                    TaTramiteMaDAO.merge(t, em);
                    
                    MaMarcacionAbreviada codigoMarcacionAbreviada = MaMarcacionAbreviadaDAO.findbyId(t.getManCodigo().getManCodigo(), em);
                    codigoMarcacionAbreviada.setEmrCodigo(t.getEmrCodigo());
                    EsEstado estadoRecurso = new EsEstado();
                    estadoRecurso.setEsnCodigo(2);
                    codigoMarcacionAbreviada.setEsnCodigo(estadoRecurso);
                    codigoMarcacionAbreviada.setMatObservaciones(t.getTatObservaciones());
                    MaMarcacionAbreviadaDAO.merge(codigoMarcacionAbreviada, em);
                }
            }
        }
        
        if (!entity.getTmTramiteMncCollection().isEmpty()) {
            Collection<TmTramiteMnc> recursos = entity.getTmTramiteMncCollection();
            for (TmTramiteMnc t : recursos) {
                if (t.getAcnCodigo().getAcnCodigo() == 2) {
                    AcAccion accion = new AcAccion();
                    accion.setAcnCodigo(3);
                    t.setAcnCodigo(accion);
                    TmTramiteMncDAO.merge(t, em);
                    
                    CdCodigosMnc codigoMnc = CdCodigosMncDAO.findbyId(t.getCdnCodigo().getCdnCodigo(), em);
                    codigoMnc.setEmrCodigo(t.getEmrCodigo());
                    EsEstado estadoRecurso = new EsEstado();
                    estadoRecurso.setEsnCodigo(2);
                    codigoMnc.setEsnCodigo(estadoRecurso);
                    codigoMnc.setCdtObservaciones(t.getTmtObservaciones());
                    CdCodigosMncDAO.merge(codigoMnc, em);
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
        
        //actualizamos la información de los registros de numeración
        if (!entity.getTnTramiteNumeracionCollection().isEmpty()) {
            Collection<TnTramiteNumeracion> recursos = entity.getTnTramiteNumeracionCollection();
            for (TnTramiteNumeracion t : recursos) {
                
                NuNumeracion numeracion = new NuNumeracion();//SeSenalizacionDAO.findbyId(t.getSenCodigo().getSenCodigo(), em);
                
                numeracion.setCodigoMunicipio(t.getCodigoMunicipio());
                numeracion.setEmrCodigo(t.getEmrCodigo());
                numeracion.setNdnCodigo(t.getNdnCodigo());
                numeracion.setNunInicio(t.getTnnInicio());
                numeracion.setNunFin(t.getTnnFin());
                numeracion.setNutObservaciones(t.getTntObservaciones());
                
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
                        if(t.getTntReservaTemporal()=='S'){
                           Date fecha; 
                           //Cálculo de la fecha de liberación
                            Calendar ahoraCal = Calendar.getInstance();//instancia una clase de calendario
                            ahoraCal.setTime(vo.getTrfFechaResolucion());//se le asigna la fecha de la resolución
                            ahoraCal.add(Calendar.MONTH, t.getTnnMesesLiberacion());//se le suman los meses escogidos a la fecha de la resolución
                            fecha=ahoraCal.getTime();
                            
                            List<NuNumeracion> num = NuNumeracionDAO.getListRango(t.getNdnCodigo().getNdnCodigo(), t.getTnnInicio(), t.getTnnFin(), em);
                            
                            for (NuNumeracion n : num) {
                                //instanciamiento del entity
                                RsReservasTemporales resTemp = new RsReservasTemporales();

                                //cargue del entity
                                resTemp.setTrnCodigo(entity);
                                resTemp.setRsnCodigo(RsReservasTemporalesDAO.getMaxId(em)+1);
                                resTemp.setRsnCodigoRecurso(n.getNunCodigo());
                                resTemp.setRstEstado('S');
                                resTemp.setRstTipoRecurso("Numeracion");
                                resTemp.setRsfFechaLiberacion(fecha);

                                //Guardado del entity
                                RsReservasTemporalesDAO.persist(resTemp, em);
                            }
                            
                            //Modificación del estado del recurso a reserva
                            estadoRecurso.setEsnCodigo(4);
                            
                        }else{
                            estadoRecurso.setEsnCodigo(1);   
                        }
                         break;
                } 
                numeracion.setEsnCodigo(estadoRecurso);
                NuNumeracionDAO.updateNumeracion(numeracion, em);
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
                        if(t.getTltReservaTemporal()=='S'){
                           Date fecha; 
                           //Cálculo de la fecha de liberación
                            Calendar ahoraCal = Calendar.getInstance();//instancia una clase de calendario
                            ahoraCal.setTime(vo.getTrfFechaResolucion());//se le asigna la fecha de la resolución
                            ahoraCal.add(Calendar.MONTH, t.getTlnMesesLiberacion());//se le suman los meses escogidos a la fecha de la resolución
                            fecha=ahoraCal.getTime();
                            //instanciamiento del entity
                            RsReservasTemporales resTemp = new RsReservasTemporales();
                            
                            //cargue del entity
                            resTemp.setTrnCodigo(entity);
                            resTemp.setRsnCodigo(RsReservasTemporalesDAO.getMaxId(em)+1);
                            resTemp.setRsnCodigoRecurso(t.getClnCodigo().getClnCodigo());
                            resTemp.setRstEstado('S');
                            resTemp.setRstTipoRecurso("CodigosLd");
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
                        if(t.getTctReservaTemporal()=='S'){
                           Date fecha; 
                           //Cálculo de la fecha de liberación
                            Calendar ahoraCal = Calendar.getInstance();//instancia una clase de calendario
                            ahoraCal.setTime(vo.getTrfFechaResolucion());//se le asigna la fecha de la resolución
                            ahoraCal.add(Calendar.MONTH, t.getTcnMesesLiberacion());//se le suman los meses escogidos a la fecha de la resolución
                            fecha=ahoraCal.getTime();
                            //instanciamiento del entity
                            RsReservasTemporales resTemp = new RsReservasTemporales();
                            
                            //cargue del entity
                            resTemp.setTrnCodigo(entity);
                            resTemp.setRsnCodigo(RsReservasTemporalesDAO.getMaxId(em)+1);
                            resTemp.setRsnCodigoRecurso(t.getCcnCodigo().getCcnCodigo());
                            resTemp.setRstEstado('S');
                            resTemp.setRstTipoRecurso("CodigosCortos");
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
                codigoCortos.setEsnCodigo(estadoRecurso);
                CcCodigosCortosDAO.merge(codigoCortos, em);
            }
        }
        
        //actualizamos la información de los registros de marcación abreviada
        if (!entity.getTaTramiteMaCollection().isEmpty()) {
            Collection<TaTramiteMa> recursos = entity.getTaTramiteMaCollection();
            for (TaTramiteMa t : recursos) {
               
                MaMarcacionAbreviada marcacionAbreviada = MaMarcacionAbreviadaDAO.findbyId(t.getManCodigo().getManCodigo(), em);
                
                marcacionAbreviada.setEmrCodigo(t.getEmrCodigo());
                marcacionAbreviada.setMatObservaciones(t.getTatObservaciones());

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
                        if(t.getTatReservaTemporal()=='S'){
                           Date fecha; 
                           //Cálculo de la fecha de liberación
                            Calendar ahoraCal = Calendar.getInstance();//instancia una clase de calendario
                            ahoraCal.setTime(vo.getTrfFechaResolucion());//se le asigna la fecha de la resolución
                            ahoraCal.add(Calendar.MONTH, t.getTanMesesLiberacion());//se le suman los meses escogidos a la fecha de la resolución
                            fecha=ahoraCal.getTime();
                            //instanciamiento del entity
                            RsReservasTemporales resTemp = new RsReservasTemporales();
                            
                            //cargue del entity
                            resTemp.setTrnCodigo(entity);
                            resTemp.setRsnCodigo(RsReservasTemporalesDAO.getMaxId(em)+1);
                            resTemp.setRsnCodigoRecurso(t.getManCodigo().getManCodigo());
                            resTemp.setRstEstado('S');
                            resTemp.setRstTipoRecurso("MarcacionAbreviada");
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
                marcacionAbreviada.setEsnCodigo(estadoRecurso);
                MaMarcacionAbreviadaDAO.merge(marcacionAbreviada, em);
            }
        }
        
        //actualizamos la información de los registros de codigos MNC
        if (!entity.getTmTramiteMncCollection().isEmpty()) {
            Collection<TmTramiteMnc> recursos = entity.getTmTramiteMncCollection();
            for (TmTramiteMnc t : recursos) {
               
                CdCodigosMnc codigosMnc = CdCodigosMncDAO.findbyId(t.getCdnCodigo().getCdnCodigo(), em);
                
                codigosMnc.setEmrCodigo(t.getEmrCodigo());
                codigosMnc.setCdtObservaciones(t.getTmtObservaciones());

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
                        if(t.getTmtReservaTemporal()=='S'){
                           Date fecha; 
                           //Cálculo de la fecha de liberación
                            Calendar ahoraCal = Calendar.getInstance();//instancia una clase de calendario
                            ahoraCal.setTime(vo.getTrfFechaResolucion());//se le asigna la fecha de la resolución
                            ahoraCal.add(Calendar.MONTH, t.getTmnMesesLiberacion());//se le suman los meses escogidos a la fecha de la resolución
                            fecha=ahoraCal.getTime();
                            //instanciamiento del entity
                            RsReservasTemporales resTemp = new RsReservasTemporales();
                            
                            //cargue del entity
                            resTemp.setTrnCodigo(entity);
                            resTemp.setRsnCodigo(RsReservasTemporalesDAO.getMaxId(em)+1);
                            resTemp.setRsnCodigoRecurso(t.getCdnCodigo().getCdnCodigo());
                            resTemp.setRstEstado('S');
                            resTemp.setRstTipoRecurso("CodigosMnc");
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
                codigosMnc.setEsnCodigo(estadoRecurso);
                CdCodigosMncDAO.merge(codigosMnc, em);
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
    
    public Integer agregarRecurso(TnTramiteNumeracionVO vo, EntityManager em){
        /*
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        
        // Verificar si falta un dato en el VO
        if((vo.getTrnCodigo().getTrnCodigo()==0)||(vo.getAcnCodigo().getAcnCodigo()==0)||(vo.getCodigoMunicipio().getCodigoMunicipio().equals(""))||(vo.getEmrCodigo().getEmrCodigo().equals(""))){
            return 2;
        }
        
        // Verificar si el resurso ya está asociado a un trámite
        if (TnTramiteNumeracionDAO.findNumeracion(vo.getNdnCodigo().getNdnCodigo(), vo.getTnnInicio(), vo.getTnnFin(), em)) {
            return 4;
        }
        
        TnTramiteNumeracion entity = new TnTramiteNumeracion();
        
        TrTramites tramite = TrTramitesDAO.findbyId(vo.getTrnCodigo().getTrnCodigo(), em);
        String operadorTramite = tramite.getEmrCodigo().getEmrCodigo();
        
        Municipios municipio = new Municipios();
        EmOperador operador = new EmOperador();
        AcAccion accion = new AcAccion();
        char reservaTemporal='N';
        int mesesLiberacion =0;
        
        List<NuNumeracion> numeracion = NuNumeracionDAO.getListRango(vo.getNdnCodigo().getNdnCodigo(), vo.getTnnInicio(), vo.getTnnFin(), em);
        
        switch(vo.getAcnCodigo().getAcnCodigo()){
            case 1: //LIBERAR
                break;
            case 2: //PREASIGNAR
                for (NuNumeracion n : numeracion) {
                    int i = n.getEsnCodigo().getEsnCodigo();
                    if(n.getEsnCodigo().getEsnCodigo() != 1) { // El estado de la numeracion no es libre
                        return 6;
                    }
                }

                municipio.setCodigoMunicipio(vo.getCodigoMunicipio().getCodigoMunicipio());
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                
                break;
            case 3: //ASIGNAR
                break;
            case 4: //RESERVAR
                break;
            case 5: //RECUPERAR
                for (NuNumeracion n : numeracion) {
                    if(n.getEsnCodigo().getEsnCodigo() != 3) { // El estado de la numeracion no es asignado
                        return 5;
                    }
                }
                
                for (NuNumeracion n : numeracion) {
                    if(!operadorTramite.equals(n.getEmrCodigo().getEmrCodigo())) { // El operador del trámite es diferente al del recurso
                        return 3;
                    }
                }
                
                municipio.setCodigoMunicipio(vo.getCodigoMunicipio().getCodigoMunicipio());
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                
                if(vo.getTntReservaTemporal()== 'S'){
                    reservaTemporal='S';
                    mesesLiberacion=vo.getTnnMesesLiberacion();
                }

                break;
        }
        
        accion.setAcnCodigo(vo.getAcnCodigo().getAcnCodigo());
        entity.setTnnCodigo(TnTramiteNumeracionDAO.getMaxId(em)+1);
        entity.setTrnCodigo(tramite);
        entity.setAcnCodigo(accion);
        entity.setTnnRadicado(vo.getTnnRadicado());
        entity.setCodigoMunicipio(municipio);
        entity.setEmrCodigo(operador);
        NdNdc ndc = new NdNdc();
        ndc.setNdnCodigo(vo.getNdnCodigo().getNdnCodigo());
        entity.setNdnCodigo(ndc);
        entity.setTnnInicio(vo.getTnnInicio());
        entity.setTnnFin(vo.getTnnFin());
        entity.setTntObservaciones(vo.getTntObservaciones());
        entity.setTntReservaTemporal(reservaTemporal);
        entity.setTnnMesesLiberacion(mesesLiberacion);
        
        TnTramiteNumeracionDAO.persist(entity, em);
        
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
        char reservaTemporal='N';
        int mesesLiberacion =0;
        
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
                
                if(vo.getTltReservaTemporal()== 'S'){
                    reservaTemporal='S';
                    mesesLiberacion=vo.getTlnMesesLiberacion();
                }
                
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
        entity.setTltReservaTemporal(reservaTemporal);
        entity.setTlnMesesLiberacion(mesesLiberacion);
        
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
        char reservaTemporal='N';
        int mesesLiberacion =0;
        
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
                
                if(vo.getTctReservaTemporal()== 'S'){
                    reservaTemporal='S';
                    mesesLiberacion=vo.getTcnMesesLiberacion();
                }
                
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
        entity.setTctReservaTemporal(reservaTemporal);
        entity.setTcnMesesLiberacion(mesesLiberacion);
        
        TcTramiteCcDAO.persist(entity, em);
        
        return 1;
        
    }
    
    public Integer agregarRecurso(TaTramiteMaVO vo, EntityManager em){
        /*
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        
        if((vo.getTrnCodigo().getTrnCodigo()==0)||(vo.getManCodigo().getManCodigo()==0)||(vo.getAcnCodigo().getAcnCodigo()==0)||(vo.getEmrCodigo().getEmrCodigo().equals(""))){
            return 2;
        }
        
        if (TaTramiteMaDAO.findIdMarcacionAbreviada(vo.getManCodigo().getManCodigo(), em)) {
            return 4;
        }
        
        TaTramiteMa entity = new TaTramiteMa();
        
        TrTramites tramite = TrTramitesDAO.findbyId(vo.getTrnCodigo().getTrnCodigo(), em);
        String operadorTramite = tramite.getEmrCodigo().getEmrCodigo();
        
        MaMarcacionAbreviada marcacionAbreviada = MaMarcacionAbreviadaDAO.findbyId(vo.getManCodigo().getManCodigo(), em);
        String operadorRecurso = marcacionAbreviada.getEmrCodigo().getEmrCodigo();
        
        EmOperador operador = new EmOperador();
        AcAccion accion = new AcAccion();
        char reservaTemporal='N';
        int mesesLiberacion =0;
        
        switch(vo.getAcnCodigo().getAcnCodigo()){
            case 1: //LIBERAR
                break;
            case 2: //PREASIGNAR
                if(marcacionAbreviada.getEsnCodigo().getEsnCodigo()!=1){ // El estado del recurso no es libre
                    return 6;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                break;
            case 3: //ASIGNAR
                break;
            case 4: //RESERVAR
                break;
            case 5: //RECUPERAR
                if(marcacionAbreviada.getEsnCodigo().getEsnCodigo()!=3){ // El estado del recurso no es asignado
                    return 5;
                }
                
                if(!operadorTramite.equals(operadorRecurso)){ // El operador del trámite es diferente al del recurso
                    return 3;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                
                if(vo.getTatReservaTemporal()== 'S'){
                    reservaTemporal='S';
                    mesesLiberacion=vo.getTanMesesLiberacion();
                }
                
                break;
        }
        
        accion.setAcnCodigo(vo.getAcnCodigo().getAcnCodigo());
        
        entity.setTanCodigo(TaTramiteMaDAO.getMaxId(em)+1);
        entity.setTrnCodigo(tramite);
        entity.setManCodigo(marcacionAbreviada);
        entity.setAcnCodigo(accion);
        entity.setTanRadicado(vo.getTanRadicado());
        entity.setEmrCodigo(operador);
        entity.setTatObservaciones(vo.getTatObservaciones());
        entity.setTatReservaTemporal(reservaTemporal);
        entity.setTanMesesLiberacion(mesesLiberacion);
        
        TaTramiteMaDAO.persist(entity, em);
        
        return 1;
        
    }
    
    public Integer agregarRecurso(TmTramiteMncVO vo, EntityManager em){
        /*
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        
        if((vo.getTrnCodigo().getTrnCodigo()==0)||(vo.getCdnCodigo().getCdnCodigo()==0)||(vo.getAcnCodigo().getAcnCodigo()==0)||(vo.getEmrCodigo().getEmrCodigo().equals(""))){
            return 2;
        }
        
        if (TaTramiteMaDAO.findIdMarcacionAbreviada(vo.getCdnCodigo().getCdnCodigo(), em)) {
            return 4;
        }
        
        TmTramiteMnc entity = new TmTramiteMnc();
        
        TrTramites tramite = TrTramitesDAO.findbyId(vo.getTrnCodigo().getTrnCodigo(), em);
        String operadorTramite = tramite.getEmrCodigo().getEmrCodigo();
        
        CdCodigosMnc codigosMnc = CdCodigosMncDAO.findbyId(vo.getCdnCodigo().getCdnCodigo(), em);
        String operadorRecurso = codigosMnc.getEmrCodigo().getEmrCodigo();
        
        EmOperador operador = new EmOperador();
        AcAccion accion = new AcAccion();
        char reservaTemporal='N';
        int mesesLiberacion =0;
        
        switch(vo.getAcnCodigo().getAcnCodigo()){
            case 1: //LIBERAR
                break;
            case 2: //PREASIGNAR
                if(codigosMnc.getEsnCodigo().getEsnCodigo()!=1){ // El estado del recurso no es libre
                    return 6;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                break;
            case 3: //ASIGNAR
                break;
            case 4: //RESERVAR
                break;
            case 5: //RECUPERAR
                if(codigosMnc.getEsnCodigo().getEsnCodigo()!=3){ // El estado del recurso no es asignado
                    return 5;
                }
                
                if(!operadorTramite.equals(operadorRecurso)){ // El operador del trámite es diferente al del recurso
                    return 3;
                }
                operador.setEmrCodigo(vo.getEmrCodigo().getEmrCodigo());
                
                if(vo.getTmtReservaTemporal()== 'S'){
                    reservaTemporal='S';
                    mesesLiberacion=vo.getTmnMesesLiberacion();
                }
                
                break;
        }
        
        accion.setAcnCodigo(vo.getAcnCodigo().getAcnCodigo());
        
        entity.setTmnCodigo(TmTramiteMncDAO.getMaxId(em)+1);
        entity.setTrnCodigo(tramite);
        entity.setCdnCodigo(codigosMnc);
        entity.setAcnCodigo(accion);
        entity.setTmnRadicado(vo.getTmnRadicado());
        entity.setEmrCodigo(operador);
        entity.setTmtObservaciones(vo.getTmtObservaciones());
        entity.setTmtReservaTemporal(reservaTemporal);
        entity.setTmnMesesLiberacion(mesesLiberacion);
        
        TmTramiteMncDAO.persist(entity, em);
        
        return 1;
        
    }
    
    public boolean eliminarRecurso(TsTramiteSenalizacionVO vo, EntityManager em){
        TsTramiteSenalizacion entity = new TsTramiteSenalizacion();
        
        entity = TsTramiteSenalizacionDAO.findbyId(vo.getTsnCodigo(), em);

        TsTramiteSenalizacionDAO.delete(entity, em);
        
        return true;
    }
    
    public boolean eliminarRecurso(TnTramiteNumeracionVO vo, EntityManager em){
        TnTramiteNumeracion entity = new TnTramiteNumeracion();
        
        entity = TnTramiteNumeracionDAO.findbyId(vo.getTnnCodigo(), em);

        TnTramiteNumeracionDAO.delete(entity, em);
        
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
    
    public boolean eliminarRecurso(TaTramiteMaVO vo, EntityManager em){
        TaTramiteMa entity = new TaTramiteMa();
        
        entity = TaTramiteMaDAO.findbyId(vo.getTanCodigo(), em);

        TaTramiteMaDAO.delete(entity, em);
        
        return true;
    }
    
    public boolean eliminarRecurso(TmTramiteMncVO vo, EntityManager em){
        TmTramiteMnc entity = new TmTramiteMnc();
        
        entity = TmTramiteMncDAO.findbyId(vo.getTmnCodigo(), em);

        TmTramiteMncDAO.delete(entity, em);
        
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
    
    public List<TcTramiteCcVO> buscarTramiteCodigoCorto(int ccnCodigo, int acnCodigo, EntityManager em){
        List<TcTramiteCc> tramiteCodigoCorto = TcTramiteCcDAO.findTramiteCodigoCorto(ccnCodigo, acnCodigo, em);
        List<TcTramiteCcVO> tramiteCodigoCortoVO = new ArrayList<TcTramiteCcVO>();        
        for (TcTramiteCc t : tramiteCodigoCorto) {
            tramiteCodigoCortoVO.add(t.toVO());
        }
        return tramiteCodigoCortoVO;
    }

    public List<TlTramiteLdVO> buscarTramiteCodigoLd(int clnCodigo, int acnCodigo, EntityManager em) {
        List<TlTramiteLd> tramiteLd = TlTramiteLdDAO.findTramiteLd(clnCodigo, acnCodigo, em);
        List<TlTramiteLdVO> tramiteLdVO = new ArrayList<TlTramiteLdVO>();        
        for (TlTramiteLd t : tramiteLd) {
            tramiteLdVO.add(t.toVO());
        }
        return tramiteLdVO;
    }
    
    public List<TaTramiteMaVO> buscarTramiteMarcacionAbreviada(int manCodigo, int acnCodigo, EntityManager em) {
        List<TaTramiteMa> tramiteMa = TaTramiteMaDAO.findTramiteMarcacionAbreviada(manCodigo, acnCodigo, em);
        List<TaTramiteMaVO> tramiteMaVO = new ArrayList<TaTramiteMaVO>();        
        for (TaTramiteMa t : tramiteMa) {
            tramiteMaVO.add(t.toVO());
        }
        return tramiteMaVO;
    }
    
    public List<TmTramiteMncVO> buscarTramiteCodigosMnc(int cdnCodigo, int acnCodigo, EntityManager em) {
        List<TmTramiteMnc> tramiteMnc = TmTramiteMncDAO.findTramiteCodigosMnc(cdnCodigo, acnCodigo, em);
        List<TmTramiteMncVO> tramiteMncVO = new ArrayList<TmTramiteMncVO>();        
        for (TmTramiteMnc t : tramiteMnc) {
            tramiteMncVO.add(t.toVO());
        }
        return tramiteMncVO;
    }
    
}
