/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import services.CcCodigosCortosService;
import services.CdCodigosMncService;
import services.CiCodigosIinService;
import services.ClCodigosLdService;
import services.CmConfiguracionModulosService;
import services.EsEstadoService;
import services.MaMarcacionAbreviadaService;
import services.MunicipiosService;
import services.Nc1xyService;
import services.NdNdcService;
import services.NrCodigosNrnService;
import services.NuNumeracionService;
import services.ReRegionService;
import services.RsReservasTemporalesService;
import services.SeSenalizacionService;
import services.TrTramitesService;
import services.UsUsuariosService;
import vo.CcCodigosCortosVO;
import vo.CdCodigosMncVO;
import vo.CiCodigosIinVO;
import vo.ClCodigosLdVO;
import vo.CmConfiguracionModulosVO;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.EtEstadoTramiteVO;
import vo.GtGestionTramiteVO;
import vo.MaMarcacionAbreviadaVO;
import vo.MdModalidadCcVO;
import vo.MoModalidad1xyVO;
import vo.MunicipiosVO;
import vo.Nc1xyVO;
import vo.NdNdcVO;
import vo.NrCodigosNrnVO;
import vo.NtTipoNdcVO;
import vo.NuNumeracionVO;
import vo.ReRegionVO;
import vo.RsReservasTemporalesVO;
import vo.SeSenalizacionVO;
import vo.TaTramiteMaVO;
import vo.TcTramiteCcVO;
import vo.TiTramiteIinVO;
import vo.TkTramiteNrnVO;
import vo.TlTramiteLdVO;
import vo.TmTramiteMncVO;
import vo.TnTramiteNumeracionVO;
import vo.TrTramitesVO;
import vo.TsTramiteSenalizacionVO;
import vo.UsUsuariosVO;
import vo.UsersVO;

/**
 *
 * @author miguel.duran
 */
public class facade {
    private ClCodigosLdService codigosld;
    private NuNumeracionService numeracion;
    private SeSenalizacionService senalizacion;
    private CcCodigosCortosService codigosCortos;
    private MaMarcacionAbreviadaService marcacionAbreviada;
    private CdCodigosMncService codigosMnc;
    private NrCodigosNrnService codigosNrn;
    private CiCodigosIinService codigosIin;
    private Nc1xyService codigos1xy;
    private NdNdcService ndc;
    private MunicipiosService municipios;
    private ReRegionService regionSenalizacion;
    private EsEstadoService estado;
    private UsUsuariosService usuario;
    private TrTramitesService tramites;
    private RsReservasTemporalesService reservasTemporales;
    private CmConfiguracionModulosService configuracionModulos;

    public facade(){
        codigosld = new ClCodigosLdService();
        numeracion = new NuNumeracionService();
        senalizacion = new SeSenalizacionService();
        codigosCortos = new CcCodigosCortosService();
        marcacionAbreviada = new MaMarcacionAbreviadaService();
        codigosMnc = new CdCodigosMncService();
        codigosNrn = new NrCodigosNrnService();
        codigosIin = new CiCodigosIinService();
        codigos1xy = new Nc1xyService();
        ndc = new NdNdcService();
        municipios = new MunicipiosService();
        regionSenalizacion = new ReRegionService();
        estado = new EsEstadoService();
        usuario = new UsUsuariosService();
        tramites = new TrTramitesService();
        reservasTemporales = new RsReservasTemporalesService();
        configuracionModulos = new CmConfiguracionModulosService();
    }

    public List<ClCodigosLdVO> ListaCodigosLd(){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<ClCodigosLdVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosld.getList(em);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }

    public List<NuNumeracionVO> cargarNumeracion(int first, int max, String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<NuNumeracionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = numeracion.cargarNumeracion(first, max, operador, ndc, tipoNdc, inicio, fin, estado, municipio, departamento, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarNumeracion(String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = numeracion.countCargarNumeracion(operador, ndc, tipoNdc, inicio, fin, estado, municipio, departamento, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<NdNdcVO> listaNDC() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<NdNdcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = ndc.getList(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<NtTipoNdcVO> listaTipoNdc(String nombreNdc) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<NtTipoNdcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = ndc.getListTipoNdc(nombreNdc, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
        
    public List<EmOperadorVO> listaOperadorNumeracion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = numeracion.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<EsEstadoVO> listaEstado() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EsEstadoVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = estado.getList(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<EsEstadoVO> listaEstado1xy() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EsEstadoVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = estado.getListEstados1xy(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<String> listaServicios1xy() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<String> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigos1xy.getListServicios(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<EtEstadoTramiteVO> listaEstadoTramites() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EtEstadoTramiteVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.getListaEstadoTramites(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<SeSenalizacionVO> cargarSenalizacion(int first, int max, String operador, int region, int zona, int ps, int estado, String municipio, String departamento){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<SeSenalizacionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = senalizacion.cargarSenalizacion(first, max, operador, region, zona, ps, estado, municipio, departamento, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarSenalizacion(String operador, int region, int zona, int ps, int estado, String municipio, String departamento){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = senalizacion.countCargarSenalizacion(operador, region, zona, ps, estado, municipio, departamento,  em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<ReRegionVO> listaRegionSenalizacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<ReRegionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = regionSenalizacion.getList(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<EmOperadorVO> listaOperadorSenalizacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = senalizacion.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<DepartamentosVO> listaDepartamentos() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<DepartamentosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = municipios.cargarDepartamentos(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<MunicipiosVO> listaMunicipios(String departamento) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<MunicipiosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = municipios.cargarMunicipios(departamento, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public UsUsuariosVO iniciarSesion(String user, String contrasena) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        UsUsuariosVO vo = null;
        boolean login = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            login = usuario.autenticar(user, contrasena);
            if (login){
                vo = usuario.cargarUsuario(user.toUpperCase(), em);
            }
            tx.commit();
            
/*            if(vo!=null){
            //Se valida la vigencia del usuario
                if(vo.getUsnEstado() == 1 ){
                    //return vo;
                } else {
                    Logger.getAnonymousLogger().log(Level.INFO, "Usuario " + vo.getUsnCodigo().getLogin() + " no está vigente.");
                    //return null;
                }
            } else {
                Logger.getAnonymousLogger().log(Level.INFO, "Nombre de usuario y contraseña inválidos.");
            }
            //return null;*/
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public Integer cambiarPerfil(UsUsuariosVO user, int perfil){
        /*
         * 0: Error al cambiar el perfil
         * 1: Perfil cambiado correctamente
         * 2: El perfil actual y el nuevo son iguales
        */
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Integer resultado = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            resultado = usuario.cambiarPerfil(user, perfil, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = 0;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public List<ClCodigosLdVO> cargarCodigosLd(int first, int max, String operador, int codigoLd, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<ClCodigosLdVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosld.cargarCodigosLd(first, max, operador, codigoLd, estado, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarCodigosLd(String operador, int codigoLd, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = codigosld.countCargarCodigosLd(operador, codigoLd, estado,  em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosLd() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosld.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    //-------- CODIGOS CORTOS --------
    
    public List<CcCodigosCortosVO> cargarCodigosCortos(int first, int max, String operador, int modalidad, int codigo, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<CcCodigosCortosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosCortos.cargarCodigosCortos(first, max, operador, modalidad, codigo, estado, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarCodigosCortos(String operador, int modalidad, int codigo, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = codigosCortos.countCargarCodigosCortos(operador, modalidad, codigo, estado,  em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosCortos() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosCortos.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<MdModalidadCcVO> listaModalidadCc() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<MdModalidadCcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosCortos.getListaModalidadCc(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- MARCACIÓN ABREVIADA --------
    
    public List<MaMarcacionAbreviadaVO> cargarMarcacionAbreviada(int first, int max, String operador, int codigoMarcacion, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<MaMarcacionAbreviadaVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = marcacionAbreviada.cargarMarcacionAbreviada(first, max, operador, codigoMarcacion, estado, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarMarcacionAbreviada(String operador, int codigoMarcacion, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = marcacionAbreviada.countCargarMarcacionAbreviada(operador, codigoMarcacion, estado,  em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorMarcacionAbreviada() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = marcacionAbreviada.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS MNC --------
    
    public List<CdCodigosMncVO> cargarCodigosMnc(int first, int max, String operador, int codigoMnc, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<CdCodigosMncVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosMnc.cargarCodigosMnc(first, max, operador, codigoMnc, estado, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarCodigosMnc(String operador, int codigoMnc, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = codigosMnc.countCargarCodigosMnc(operador, codigoMnc, estado,  em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosMnc() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosMnc.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS NRN --------
    
    public List<NrCodigosNrnVO> cargarCodigosNrn(int first, int max, String operador, int codigoNrn, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<NrCodigosNrnVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosNrn.cargarCodigosNrn(first, max, operador, codigoNrn, estado, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarCodigosNrn(String operador, int codigoNrn, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = codigosNrn.countCargarCodigosNrn(operador, codigoNrn, estado,  em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosNrn() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosNrn.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS IIN --------
    
    public List<CiCodigosIinVO> cargarCodigosIin(int first, int max, String operador, int codigoIin, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<CiCodigosIinVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosIin.cargarCodigosIin(first, max, operador, codigoIin, estado, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarCodigosIin(String operador, int codigoIin, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = codigosIin.countCargarCodigosIin(operador, codigoIin, estado,  em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosIin() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigosIin.getListOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS 1XY --------
    
    public List<Nc1xyVO> cargarCodigos1xy(int first, int max, int codigo1xy, int codigoModalidad, int estado, String servicio){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<Nc1xyVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigos1xy.cargarCodigos1xy(first, max, codigo1xy, codigoModalidad, estado, servicio, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public int countCargarCodigos1xy(int codigo1xy, int codigoModalidad, int estado, String servicio){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = codigos1xy.countCargarCodigos1xy(codigo1xy, codigoModalidad, estado, servicio, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return cantidad;
    }
    
    public List<MoModalidad1xyVO> listaModalidad1xy() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<MoModalidad1xyVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = codigos1xy.getListModalidad1xy(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public boolean editarCodigo1xy(Nc1xyVO vo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            codigos1xy.editarCodigo1xy(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    //--------------------------------
    
    public List<UsUsuariosVO> listaUsuariosAplicacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<UsUsuariosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = usuario.getList(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<UsersVO> listaUsuariosNoAplicacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<UsersVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = usuario.getUsuariosNoAplicacion(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<UsUsuariosVO> listaAsesores() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<UsUsuariosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = usuario.getAsesores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<UsUsuariosVO> getUsuarios(int tipoUsuario) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<UsUsuariosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = usuario.getUsuarios(tipoUsuario, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<UsersVO> listaUsuariosSIUST() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<UsersVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = usuario.getUsuariosSIUST(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public UsUsuariosVO buscarUsuario(int userCode) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        UsUsuariosVO vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = usuario.buscarUsuario(userCode, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public EmOperadorVO buscarOperador(String emrCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        EmOperadorVO vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarOperador(emrCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    //-------- TRAMITES --------
    public boolean crearTramite(TrTramitesVO vo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tramites.crearTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public boolean archivarTramite(TrTramitesVO vo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tramites.archivarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public boolean enviarTramite(TrTramitesVO vo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tramites.enviarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public boolean devolverTramite(TrTramitesVO vo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tramites.devolverTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public boolean aprobarTramite(TrTramitesVO vo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tramites.aprobarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public boolean terminarTramite(TrTramitesVO vo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            tramites.terminarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public Integer unirTramites(TrTramitesVO[] vo){
        /*
         * 0: Error al unir los trámites
         * 1: Trámites unidos correctamente
         * 2: Las empresas de los trámites deben ser las mismas
        */
        
        String empresa = vo[0].getEmrCodigo().getEmrCodigo();
        String observaciones = "";
        for(TrTramitesVO t : vo){
            if (!empresa.equals(t.getEmrCodigo().getEmrCodigo())) {
                return 2;
            }
        }
                
        TrTramitesVO tramite = new TrTramitesVO();
        Collection<TsTramiteSenalizacionVO> tramiteSen = new ArrayList<TsTramiteSenalizacionVO>();
        Collection<TnTramiteNumeracionVO> tramiteNum = new ArrayList<TnTramiteNumeracionVO>();
        Collection<TlTramiteLdVO> tramiteLd = new ArrayList<TlTramiteLdVO>();
        Collection<TcTramiteCcVO> tramiteCC = new ArrayList<TcTramiteCcVO>();
        Collection<TaTramiteMaVO> tramiteMa = new ArrayList<TaTramiteMaVO>();
        Collection<TmTramiteMncVO> tramiteMnc = new ArrayList<TmTramiteMncVO>();
        Collection<TkTramiteNrnVO> tramiteNrn = new ArrayList<TkTramiteNrnVO>();
        Collection<TiTramiteIinVO> tramiteIin = new ArrayList<TiTramiteIinVO>();
        
        observaciones = "Se unieron los trámites: ";
        
        for(TrTramitesVO t : vo){
            for (TsTramiteSenalizacionVO ts : t.getTsTramiteSenalizacionCollection()) {
                tramiteSen.add(ts);
            }
            for (TnTramiteNumeracionVO tn : t.getTnTramiteNumeracionCollection()) {
                tramiteNum.add(tn);
            }
            for (TlTramiteLdVO tld : t.getTlTramiteLdCollection()) {
                tramiteLd.add(tld);
            }
            for (TcTramiteCcVO tc : t.getTcTramiteCcCollection()) {
                tramiteCC.add(tc);
            }
            for (TaTramiteMaVO tma : t.getTaTramiteMaCollection()) {
                tramiteMa.add(tma);
            }
            for (TmTramiteMncVO tmnc : t.getTmTramiteMncCollection()) {
                tramiteMnc.add(tmnc);
            }
            for (TkTramiteNrnVO tnrn : t.getTkTramiteNrnCollection()) {
                tramiteNrn.add(tnrn);
            }
            for (TiTramiteIinVO tiin : t.getTiTramiteIinCollection()) {
                tramiteIin.add(tiin);
            }
            observaciones = observaciones + t.getTrnCodigo() + "-";            
        }
        
        tramite.setTsTramiteSenalizacionCollection(tramiteSen);
        tramite.setTnTramiteNumeracionCollection(tramiteNum);
        tramite.setTlTramiteLdCollection(tramiteLd);
        tramite.setTcTramiteCcCollection(tramiteCC);
        tramite.setTaTramiteMaCollection(tramiteMa);
        tramite.setTmTramiteMncCollection(tramiteMnc);
        tramite.setTkTramiteNrnCollection(tramiteNrn);
        tramite.setTiTramiteIinCollection(tramiteIin);
        tramite.setEmrCodigo(vo[0].getEmrCodigo());
        tramite.setEtnCodigo(vo[0].getEtnCodigo());
        tramite.setTrfFecha(vo[0].getTrfFecha());
        tramite.setTrtObservaciones(observaciones);
        tramite.setUsnCodigo(vo[0].getUsnCodigo());
        
        for (int i = 0; i < vo.length; i++) {
            vo[i].setTrtObservaciones(observaciones);
        }
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Integer resultado = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            for(TrTramitesVO t : vo){
                tramites.archivarTramite(t, em);
            }
            tramites.crearTramite(tramite, em);
            tx.commit();
            resultado = 1;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = 0;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    
    public Integer cambiarUsuarioTramite(TrTramitesVO vo, int codigoNuevoUsuario){
        /*
         * 0: Error al cambiar el usuario
         * 1: Usuario cambiado correctamente
         * 2: El usuario actual y el nuevo son iguales
        */
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Integer resultado = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            resultado = tramites.cambiarUsuarioTramite(vo, codigoNuevoUsuario, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = 0;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public Integer cambiarOperadorTramite(TrTramitesVO vo, String codigoNuevoOperador){
        /*
         * 0: Error al cambiar el operador
         * 1: Operador cambiado correctamente
         * 2: El operador actual y el nuevo son iguales
        */
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Integer resultado = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            resultado = tramites.cambiarOperadorTramite(vo, codigoNuevoOperador, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = 0;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public Integer agregarRecurso(Object recurso){
        /*
         * 0: Error al agregar el recurso
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Integer resultado = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            String nombre = recurso.getClass().getSimpleName();
            if (nombre.equals("TsTramiteSenalizacionVO")){
                resultado = tramites.agregarRecurso((TsTramiteSenalizacionVO) recurso, em);
            } else if (nombre.equals("TnTramiteNumeracionVO")){
                resultado = tramites.agregarRecurso((TnTramiteNumeracionVO) recurso, em);
            } else if (nombre.equals("TlTramiteLdVO")){
                resultado = tramites.agregarRecurso((TlTramiteLdVO) recurso, em);
            } else if (nombre.equals("TcTramiteCcVO")){
                resultado = tramites.agregarRecurso((TcTramiteCcVO) recurso, em);
            } else {
                resultado = 0;
            }

            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = 0;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public Integer agregarRecursos(ArrayList recursos){
        /*
         * 0: Error al agregar el recurso
         * 1: Recurso agregado correctamente
         * 2: Falta un dato del VO
         * 3: El operador del recurso es diferente al del trámite
         * 4: El recurso ya tiene un tramite
         * 5: El estado del recurso debe ser "ASIGNADO" (para el trámite de recuperación)
         * 6: El estado del recurso debe ser "LIBRE" (para el trámite de preasignación)
        */
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Integer resultado = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            for(Object r : recursos){
                String nombre = r.getClass().getSimpleName();
                if (nombre.equals("TsTramiteSenalizacionVO")){
                    resultado = tramites.agregarRecurso((TsTramiteSenalizacionVO) r, em);
                } else if (nombre.equals("TnTramiteNumeracionVO")){
                    resultado = tramites.agregarRecurso((TnTramiteNumeracionVO) r, em);
                } else if (nombre.equals("TlTramiteLdVO")){
                    resultado = tramites.agregarRecurso((TlTramiteLdVO) r, em);
                } else if (nombre.equals("TcTramiteCcVO")){
                    resultado = tramites.agregarRecurso((TcTramiteCcVO) r, em);
                } else if (nombre.equals("TaTramiteMaVO")){
                    resultado = tramites.agregarRecurso((TaTramiteMaVO) r, em);
                } else if (nombre.equals("TmTramiteMncVO")){
                    resultado = tramites.agregarRecurso((TmTramiteMncVO) r, em);
                } else if (nombre.equals("TkTramiteNrnVO")){
                    resultado = tramites.agregarRecurso((TkTramiteNrnVO) r, em);
                } else if (nombre.equals("TiTramiteIinVO")){
                    resultado = tramites.agregarRecurso((TiTramiteIinVO) r, em);
                }else {
                    resultado = 0;
                }
                if(resultado != 1){
                    break;
                }
                
            }
            
            if(resultado == 1){
                tx.commit();
            } else {
                tx.rollback();
            }

        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = 0;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public boolean eliminarRecurso(Object recurso){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            String nombre = recurso.getClass().getSimpleName();
            if (nombre.equals("TsTramiteSenalizacionVO")){
                resultado = tramites.eliminarRecurso((TsTramiteSenalizacionVO) recurso, em);
            } else if(nombre.equals("TnTramiteNumeracionVO")) {
                resultado = tramites.eliminarRecurso((TnTramiteNumeracionVO) recurso, em);
            } else if(nombre.equals("TlTramiteLdVO")) {
                resultado = tramites.eliminarRecurso((TlTramiteLdVO) recurso, em);
            } else if(nombre.equals("TcTramiteCcVO")) {
                resultado = tramites.eliminarRecurso((TcTramiteCcVO) recurso, em);
            } else if(nombre.equals("TaTramiteMaVO")) {
                resultado = tramites.eliminarRecurso((TaTramiteMaVO) recurso, em);
            } else if(nombre.equals("TmTramiteMncVO")) {
                resultado = tramites.eliminarRecurso((TmTramiteMncVO) recurso, em);
            } else if(nombre.equals("TkTramiteNrnVO")) {
                resultado = tramites.eliminarRecurso((TkTramiteNrnVO) recurso, em);
            } else if(nombre.equals("TiTramiteIinVO")) {
                resultado = tramites.eliminarRecurso((TiTramiteIinVO) recurso, em);
            }else {
                resultado = false;
            }

            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public List<TrTramitesVO> cargarTramites(int tipo, int userCode){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TrTramitesVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            switch(tipo) {
                case 1:
                    vo = tramites.getTramitesCreados(userCode, em);
                    break;
                case 2:
                    vo = tramites.getTramitesEnviados(em);
                    break;
                case 3:
                    vo = tramites.getTramitesDevueltos(userCode, em);
                    break;
                case 4:
                    vo = tramites.getTramitesAprobados(em);
                    break;
                case 5:
                    vo = tramites.getTramitesTerminados(em);
                    break;
                case 6:
                    vo = tramites.getTramitesAsesor(userCode, em);
                    break;
            }
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TrTramitesVO> cargarTramites(int first, int max, int tramiteId, int usuario, String operador, int estado, int radicado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TrTramitesVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.cargarTramites(first, max, tramiteId, usuario, operador, estado, radicado, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TsTramiteSenalizacionVO> buscarTramitePorSenalizacion(int senCodigo, int acnCodigo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TsTramiteSenalizacionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarTramiteSenalizacion(senCodigo, acnCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TcTramiteCcVO> buscarTramitePorCodigoCorto(int ccnCodigo, int acnCodigo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TcTramiteCcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarTramiteCodigoCorto(ccnCodigo, acnCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TlTramiteLdVO> buscarTramitePorCodigoLd(int clnCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TlTramiteLdVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarTramiteCodigoLd(clnCodigo, acnCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TaTramiteMaVO> buscarTramitePorMarcacionAbreviada(int manCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TaTramiteMaVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarTramiteMarcacionAbreviada(manCodigo, acnCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TmTramiteMncVO> buscarTramitePorCodigosMnc(int cdnCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TmTramiteMncVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarTramiteCodigosMnc(cdnCodigo, acnCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TkTramiteNrnVO> buscarTramitePorCodigosNrn(int nrnCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TkTramiteNrnVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarTramiteCodigosNrn(nrnCodigo, acnCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<TiTramiteIinVO> buscarTramitePorCodigosIin(int cinCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<TiTramiteIinVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.buscarTramiteCodigosIin(cinCodigo, acnCodigo, em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<EmOperadorVO> cargarOperadores() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = tramites.cargarOperadores(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public List<RsReservasTemporalesVO> consultaReservasTemporales() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<RsReservasTemporalesVO> vo = null;
        
        emf = Persistence.createEntityManagerFactory("MapaModeloPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
        vo = reservasTemporales.getList(em);

        if(em != null){
            em.clear();
            em.close();
        }
        return vo;
    }
    
    public boolean transferirRecursos(String operadorOrigen, String operadorDestino, boolean num, boolean sen, boolean iin, boolean mnc){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            if (num){
                numeracion.transferirNumeracion(operadorOrigen, operadorDestino, em);
            }
            if (sen){
                senalizacion.transferirSenalizacion(operadorOrigen, operadorDestino, em);
            }              
            
            /*if (iin){
                senalizacion.transferirIIN(operadorOrigen, operadorDestino, em);
            }              
            if (mnc){
                senalizacion.transferirMNC(operadorOrigen, operadorDestino, em);
            }              
             */
            
            resultado=true;
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public int reservarLiberarRecurso(ArrayList recursos, int accion){
        /* la acción 0 es liberación y la accion 1 es reserva
         * 
         * 0: Error al reservar/liberar el recurso
         * 1: Recurso reservado/liberado correctamente
         * 2: Falta un dato del VO
         * 3: El recurso está reservado temporalmente y no se puede liberar
        */
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        Integer resultado = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            
            for(Object r : recursos){
                String nombre = r.getClass().getSimpleName();
                if (nombre.equals("SeSenalizacionVO")){
                    resultado = senalizacion.reservarLiberarSenalizacion((SeSenalizacionVO) r, em, accion);
                } else if (nombre.equals("NuNumeracionVO")){
                    resultado = numeracion.reservarLiberarNumeracion((NuNumeracionVO) r, em, accion);
                } else if (nombre.equals("ClCodigosLdVO")){
                    resultado = codigosld.reservarLiberarCodigoLd((ClCodigosLdVO) r, em, accion);
                } else if (nombre.equals("CcCodigosCortosVO")){
                    resultado = codigosCortos.reservarLiberarCodigoCorto((CcCodigosCortosVO) r, em, accion);
                } else if (nombre.equals("MaMarcacionAbreviadaVO")){
                    resultado = marcacionAbreviada.reservarLiberarMarcacionAbreviada((MaMarcacionAbreviadaVO) r, em, accion);
                } else if (nombre.equals("CdCodigosMncVO")){
                    resultado = codigosMnc.reservarLiberarCodigosMnc((CdCodigosMncVO) r, em, accion);
                } else if (nombre.equals("NrCodigosNrnVO")){
                    resultado = codigosNrn.reservarLiberarCodigosNrn((NrCodigosNrnVO) r, em, accion);
                } else if (nombre.equals("CiCodigosIinVO")){
                    resultado = codigosIin.reservarLiberarCodigosIin((CiCodigosIinVO) r, em, accion);
                }else {
                    resultado = 0;
                }
                if(resultado != 1){
                    break;
                }
                
            }
            
            if(resultado == 1){
                tx.commit();
            } else {
                tx.rollback();
            }
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = 0;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public Object consultaRecurso(String tipoRecurso, int codigoRecurso){
        Object recurso=null;
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
                
        emf = Persistence.createEntityManagerFactory("MapaModeloPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
        
        if(tipoRecurso.equals("Senalizacion")){
            recurso=senalizacion.getById(codigoRecurso, em);
        } else if(tipoRecurso.equals("Numeracion")){
            recurso=numeracion.getById(codigoRecurso, em);
        } else if(tipoRecurso.equals("CodigosLd")){
            recurso=codigosld.getById(codigoRecurso, em);
        } else if(tipoRecurso.equals("CodigosCortos")){
            recurso=codigosCortos.getById(codigoRecurso, em);
        } else if(tipoRecurso.equals("MarcacionAbreviada")){
            recurso=marcacionAbreviada.getById(codigoRecurso, em);
        } else if(tipoRecurso.equals("CodigosMnc")){
            recurso=codigosMnc.getById(codigoRecurso, em);
        } else if(tipoRecurso.equals("CodigosNrn")){
            recurso=codigosNrn.getById(codigoRecurso, em);
        } else if(tipoRecurso.equals("CodigosIin")){
            recurso=codigosIin.getById(codigoRecurso, em);
        }


        if(em != null){
            em.clear();
            em.close();
        }

        
        return recurso;
    }
    
    public Boolean consultaReservaTemporal(int codigoRecurso, String tipoRecurso){
        Boolean resultado = false;
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
                
        emf = Persistence.createEntityManagerFactory("MapaModeloPU");
        em = emf.createEntityManager();
        tx = em.getTransaction();
        tx.begin();
        
        resultado = reservasTemporales.consultaReservaTemporal(codigoRecurso, tipoRecurso, em);

        if(em != null){
            resultado = false;
            em.clear();
            em.close();
        }
        
        return resultado;
    } 

    //-------- CONFIGURACIÓN MÓDULOS --------

    public List<CmConfiguracionModulosVO> listaModulos() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<CmConfiguracionModulosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = configuracionModulos.getList(em);
            tx.commit();
        } catch (Exception e) {
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
    
    public boolean editarConfiguracionModulo(CmConfiguracionModulosVO vo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            configuracionModulos.editarConfiguracionModulo(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return resultado;
    }
    
    public CmConfiguracionModulosVO buscarConfiguracionModulo(Integer cmnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        CmConfiguracionModulosVO vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = configuracionModulos.getById(cmnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            if(em != null){
                em.clear();
                em.close();
            }
        }
        return vo;
    }
}
