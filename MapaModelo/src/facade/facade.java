/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import services.CcCodigosCortosService;
import services.ClCodigosLdService;
import services.EsEstadoService;
import services.MunicipiosService;
import services.NdNdcService;
import services.NuNumeracionService;
import services.ReRegionService;
import services.RsReservasTemporalesService;
import services.SeSenalizacionService;
import services.TrTramitesService;
import services.UsUsuariosService;
import vo.CcCodigosCortosVO;
import vo.ClCodigosLdVO;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.EtEstadoTramiteVO;
import vo.MdModalidadCcVO;
import vo.MunicipiosVO;
import vo.NdNdcVO;
import vo.NtTipoNdcVO;
import vo.NuNumeracionVO;
import vo.ReRegionVO;
import vo.RsReservasTemporalesVO;
import vo.SeSenalizacionVO;
import vo.TcTramiteCcVO;
import vo.TlTramiteLdVO;
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
    private NdNdcService ndc;
    private MunicipiosService municipios;
    private ReRegionService regionSenalizacion;
    private EsEstadoService estado;
    private UsUsuariosService usuario;
    private TrTramitesService tramites;
    private RsReservasTemporalesService reservasTemporales;

    public facade(){
        codigosld = new ClCodigosLdService();
        numeracion = new NuNumeracionService();
        senalizacion = new SeSenalizacionService();
        codigosCortos = new CcCodigosCortosService();
        ndc = new NdNdcService();
        municipios = new MunicipiosService();
        regionSenalizacion = new ReRegionService();
        estado = new EsEstadoService();
        usuario = new UsUsuariosService();
        tramites = new TrTramitesService();
        reservasTemporales = new RsReservasTemporalesService();
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
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = usuario.cargarUsuario(user, contrasena, em);
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
    
    public Integer cambiarUsuarioTramite(TrTramitesVO vo, int codigoNuevoUsuario){
        /*
         * 0: Error al cambiar el usuario
         * 1: Usuario cambiado correctamente
         * 2: El ususario actual y el nuevo son iguales
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
                } else {
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
            } else {
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
                } else {
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
        }


        if(em != null){
            em.clear();
            em.close();
        }

        
        return recurso;
    } 
}
