/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import entities.TeTipoSenalizacion;
import facade.helpers.CloseEntityManager;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import services.ServiceFactory;
import vo.CcCodigosCortosVO;
import vo.CdCodigosMncVO;
import vo.CiCodigosIinVO;
import vo.ClCodigosLdVO;
import vo.CmConfiguracionModulosVO;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.EtEstadoTramiteVO;
import vo.MaMarcacionAbreviadaVO;
import vo.MdModalidadCcVO;
import vo.MoModalidad1xyVO;
import vo.MunicipiosVO;
import vo.Nc1xyVO;
import vo.NdNdcVO;
import vo.NrCodigosNrnVO;
import vo.NtTipoNdcVO;
import vo.NuNumeracionVO;
import vo.PaPermisosAsesorVO;
import vo.PtTipoPermisoVO;
import vo.ReRegionVO;
import vo.RsReservasTemporalesVO;
import vo.RtTipoRegionVO;
import vo.SeSenalizacionVO;
import vo.TaTramiteMaVO;
import vo.TcTramiteCcVO;
import vo.TeTipoSenalizacionVO;
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
    /*private ClCodigosLdService codigosld;
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
    private CmConfiguracionModulosService configuracionModulos;*/

    public facade(){
        /*codigosld = new ClCodigosLdService();
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
        configuracionModulos = new CmConfiguracionModulosService();*/
    }

    public List<ClCodigosLdVO> ListaCodigosLd(){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<ClCodigosLdVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createClCodigosLdService().getList(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }

    public List<NuNumeracionVO> cargarNumeracion(int first, int max, String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<NuNumeracionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNuNumeracionService().cargarNumeracion(first, max, operador, ndc, tipoNdc, inicio, fin, estado, municipio, departamento, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarNumeracion(String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createNuNumeracionService().countCargarNumeracion(operador, ndc, tipoNdc, inicio, fin, estado, municipio, departamento, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<NuNumeracionVO> cargarNumeracionBloque(String ndc, int inicio, int fin){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<NuNumeracionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNuNumeracionService().cargarNumeracionBloque(ndc, inicio, fin, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<NdNdcVO> listaNDC() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<NdNdcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNdNdcService().getList(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<NtTipoNdcVO> listaTipoNdc(String nombreNdc) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<NtTipoNdcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNdNdcService().getListTipoNdc(nombreNdc, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
        
    public List<EmOperadorVO> listaOperadorNumeracion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNuNumeracionService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<EsEstadoVO> listaEstado() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EsEstadoVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createEsEstadoService().getList(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<EsEstadoVO> listaEstado1xy() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EsEstadoVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createEsEstadoService().getListEstados1xy(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<String> listaServicios1xy() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<String> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNc1xyService().getListServicios(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<EtEstadoTramiteVO> listaEstadoTramites() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EtEstadoTramiteVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().getListaEstadoTramites(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<SeSenalizacionVO> cargarSenalizacion(int first, int max, String operador, int region, int zona, int ps, int estado, String municipio, String departamento, int tipoSenalizacion, int tipoRegion){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<SeSenalizacionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo =ServiceFactory.createSeSenalizacionService().cargarSenalizacion(first, max, operador, region, zona, ps, estado, municipio, departamento, tipoSenalizacion, tipoRegion, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarSenalizacion(String operador, int region, int zona, int ps, int estado, String municipio, String departamento, int tipoSenalizacion, int tipoRegion){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad =ServiceFactory.createSeSenalizacionService().countCargarSenalizacion(operador, region, zona, ps, estado, municipio, departamento, tipoSenalizacion, tipoRegion, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<ReRegionVO> listaRegionSenalizacion(int tipoRegion) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<ReRegionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createReRegionService().getList(tipoRegion, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<Integer> listaZonaSenalizacion(int tipoRegion) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<Integer> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createSeSenalizacionService().getListZona(tipoRegion, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TeTipoSenalizacionVO> listaTipoSenalizacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TeTipoSenalizacionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createSeSenalizacionService().getListTipoSenalizacion(em); 
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<RtTipoRegionVO> listaTipoRegionSenalizacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<RtTipoRegionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createSeSenalizacionService().getListTipoRegionSenalizacion(em); 
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<EmOperadorVO> listaOperadorSenalizacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo =ServiceFactory.createSeSenalizacionService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<DepartamentosVO> listaDepartamentos() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<DepartamentosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createMunicipiosService().cargarDepartamentos(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<MunicipiosVO> listaMunicipios(String departamento) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<MunicipiosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createMunicipiosService().cargarMunicipios(departamento, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public UsUsuariosVO iniciarSesion(String user, String contrasena) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        UsUsuariosVO vo = null;
        boolean login = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            login = ServiceFactory.createUsUsuariosService().autenticar(user, contrasena);
            if (login){
                vo = ServiceFactory.createUsUsuariosService().cargarUsuario(user.toUpperCase(), em);
            }
            //tx.commit();
            
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
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
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
            resultado = ServiceFactory.createUsUsuariosService().cambiarPerfil(user, perfil, em);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    }
    
    public List<PtTipoPermisoVO> cargarPermisos(int usuario){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<PtTipoPermisoVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            vo = ServiceFactory.createPaPermisosAsesorService().cargarPermisos(usuario, em);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<PtTipoPermisoVO> cargarPermisosTotales(int usuario){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<PtTipoPermisoVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            vo = ServiceFactory.createPaPermisosAsesorService().cargarPermisosTotales(usuario, em);
        } catch (Exception e) {
            System.out.println(e);
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public boolean tienePermiso(int usuario, int tipo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        List<PtTipoPermisoVO> vo = null;
        boolean resultado = false;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            resultado = ServiceFactory.createPaPermisosAsesorService().tienePermiso(usuario, tipo, em);
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    }
    
    public Integer cambiarPermisoAsesor(int usuario, int tipo, boolean accion){
        /*
         * 0: Error al crear/borrar el permiso
         * 1: Permiso creado/borrado correctamente
         * 2: El usuario tiene/no tiene el permiso
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
            boolean permiso = false; 
            if (accion) {
                permiso = ServiceFactory.createPaPermisosAsesorService().crearPermiso(usuario, tipo, em);
            } else {
                permiso = ServiceFactory.createPaPermisosAsesorService().borrarPermiso(usuario, tipo, em);
            }
            if (permiso) { //Se pudo crear/borrar
                tx.commit();
                resultado = 1;
            } else { //El usuario tiene/no tiene el permiso
                resultado = 2;
            }
        } catch (Exception e) {
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    }
    
    public List<ClCodigosLdVO> cargarCodigosLd(int first, int max, String operador, int codigoLd, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<ClCodigosLdVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createClCodigosLdService().cargarCodigosLd(first, max, operador, codigoLd, estado, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarCodigosLd(String operador, int codigoLd, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createClCodigosLdService().countCargarCodigosLd(operador, codigoLd, estado,  em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosLd() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createClCodigosLdService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    //-------- CODIGOS CORTOS --------
    
    public List<CcCodigosCortosVO> cargarCodigosCortos(int first, int max, String operador, int modalidad, int codigo, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<CcCodigosCortosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCcCodigosCortosService().cargarCodigosCortos(first, max, operador, modalidad, codigo, estado, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarCodigosCortos(String operador, int modalidad, int codigo, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createCcCodigosCortosService().countCargarCodigosCortos(operador, modalidad, codigo, estado,  em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosCortos() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCcCodigosCortosService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<MdModalidadCcVO> listaModalidadCc() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<MdModalidadCcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCcCodigosCortosService().getListaModalidadCc(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- MARCACIÓN ABREVIADA --------
    
    public List<MaMarcacionAbreviadaVO> cargarMarcacionAbreviada(int first, int max, String operador, int codigoMarcacion, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<MaMarcacionAbreviadaVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createMaMarcacionAbreviadaService().cargarMarcacionAbreviada(first, max, operador, codigoMarcacion, estado, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarMarcacionAbreviada(String operador, int codigoMarcacion, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createMaMarcacionAbreviadaService().countCargarMarcacionAbreviada(operador, codigoMarcacion, estado,  em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorMarcacionAbreviada() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createMaMarcacionAbreviadaService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS MNC --------
    
    public List<CdCodigosMncVO> cargarCodigosMnc(int first, int max, String operador, int codigoMnc, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<CdCodigosMncVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCdCodigosMncService().cargarCodigosMnc(first, max, operador, codigoMnc, estado, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarCodigosMnc(String operador, int codigoMnc, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createCdCodigosMncService().countCargarCodigosMnc(operador, codigoMnc, estado,  em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosMnc() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCdCodigosMncService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS NRN --------
    
    public List<NrCodigosNrnVO> cargarCodigosNrn(int first, int max, String operador, int codigoNrn, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<NrCodigosNrnVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNrCodigosNrnService().cargarCodigosNrn(first, max, operador, codigoNrn, estado, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarCodigosNrn(String operador, int codigoNrn, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createNrCodigosNrnService().countCargarCodigosNrn(operador, codigoNrn, estado,  em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosNrn() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNrCodigosNrnService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS IIN --------
    
    public List<CiCodigosIinVO> cargarCodigosIin(int first, int max, String operador, int codigoIin, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<CiCodigosIinVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCiCodigosIinService().cargarCodigosIin(first, max, operador, codigoIin, estado, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarCodigosIin(String operador, int codigoIin, int estado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createCiCodigosIinService().countCargarCodigosIin(operador, codigoIin, estado,  em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<EmOperadorVO> listaOperadorCodigosIin() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCiCodigosIinService().getListOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    //--------------------------------
    
    //-------- CÓDIGOS 1XY --------
    
    public List<Nc1xyVO> cargarCodigos1xy(int first, int max, int codigo1xy, int codigoModalidad, int estado, String servicio){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<Nc1xyVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNc1xyService().cargarCodigos1xy(first, max, codigo1xy, codigoModalidad, estado, servicio, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public int countCargarCodigos1xy(int codigo1xy, int codigoModalidad, int estado, String servicio){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            cantidad = ServiceFactory.createNc1xyService().countCargarCodigos1xy(codigo1xy, codigoModalidad, estado, servicio, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return cantidad;
    }
    
    public List<MoModalidad1xyVO> listaModalidad1xy() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<MoModalidad1xyVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createNc1xyService().getListModalidad1xy(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createNc1xyService().editarCodigo1xy(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            if(em != null && tx != null){
                resultado = false;
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    }
    
    //--------------------------------
    
    public List<UsUsuariosVO> listaUsuariosAplicacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<UsUsuariosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createUsUsuariosService().getList(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<UsersVO> listaUsuariosNoAplicacion() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<UsersVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createUsUsuariosService().getUsuariosNoAplicacion(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<UsUsuariosVO> listaAsesores() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<UsUsuariosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createUsUsuariosService().getAsesores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<UsUsuariosVO> getUsuarios(int tipoUsuario) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<UsUsuariosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createUsUsuariosService().getUsuarios(tipoUsuario, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<UsersVO> listaUsuariosSIUST() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<UsersVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createUsUsuariosService().getUsuariosSIUST(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public UsUsuariosVO buscarUsuario(int userCode) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        UsUsuariosVO vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createUsUsuariosService().buscarUsuario(userCode, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public EmOperadorVO buscarOperador(String emrCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        EmOperadorVO vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarOperador(emrCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createTrTramitesService().crearTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createTrTramitesService().archivarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createTrTramitesService().enviarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createTrTramitesService().devolverTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createTrTramitesService().aprobarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createTrTramitesService().terminarTramite(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
                ServiceFactory.createTrTramitesService().archivarTramite(t, em);
            }
            ServiceFactory.createTrTramitesService().crearTramite(tramite, em);
            tx.commit();
            resultado = 1;
        } catch (Exception e) {
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
            resultado = ServiceFactory.createTrTramitesService().cambiarUsuarioTramite(vo, codigoNuevoUsuario, em);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
            resultado = ServiceFactory.createTrTramitesService().cambiarOperadorTramite(vo, codigoNuevoOperador, em);
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
                resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TsTramiteSenalizacionVO) recurso, em);
            } else if (nombre.equals("TnTramiteNumeracionVO")){
                resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TnTramiteNumeracionVO) recurso, em);
            } else if (nombre.equals("TlTramiteLdVO")){
                resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TlTramiteLdVO) recurso, em);
            } else if (nombre.equals("TcTramiteCcVO")){
                resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TcTramiteCcVO) recurso, em);
            } else {
                resultado = 0;
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TsTramiteSenalizacionVO) r, em);
                } else if (nombre.equals("TnTramiteNumeracionVO")){
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TnTramiteNumeracionVO) r, em);
                } else if (nombre.equals("TlTramiteLdVO")){
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TlTramiteLdVO) r, em);
                } else if (nombre.equals("TcTramiteCcVO")){
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TcTramiteCcVO) r, em);
                } else if (nombre.equals("TaTramiteMaVO")){
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TaTramiteMaVO) r, em);
                } else if (nombre.equals("TmTramiteMncVO")){
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TmTramiteMncVO) r, em);
                } else if (nombre.equals("TkTramiteNrnVO")){
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TkTramiteNrnVO) r, em);
                } else if (nombre.equals("TiTramiteIinVO")){
                    resultado = ServiceFactory.createTrTramitesService().agregarRecurso((TiTramiteIinVO) r, em);
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
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TsTramiteSenalizacionVO) recurso, em);
            } else if(nombre.equals("TnTramiteNumeracionVO")) {
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TnTramiteNumeracionVO) recurso, em);
            } else if(nombre.equals("TlTramiteLdVO")) {
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TlTramiteLdVO) recurso, em);
            } else if(nombre.equals("TcTramiteCcVO")) {
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TcTramiteCcVO) recurso, em);
            } else if(nombre.equals("TaTramiteMaVO")) {
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TaTramiteMaVO) recurso, em);
            } else if(nombre.equals("TmTramiteMncVO")) {
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TmTramiteMncVO) recurso, em);
            } else if(nombre.equals("TkTramiteNrnVO")) {
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TkTramiteNrnVO) recurso, em);
            } else if(nombre.equals("TiTramiteIinVO")) {
                resultado = ServiceFactory.createTrTramitesService().eliminarRecurso((TiTramiteIinVO) recurso, em);
            }else {
                resultado = false;
            }

            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    }
    
    public List<TrTramitesVO> cargarTramites(int tipo, int userCode){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TrTramitesVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            switch(tipo) {
                case 1:
                    vo = ServiceFactory.createTrTramitesService().getTramitesCreados(userCode, em);
                    break;
                case 2:
                    vo = ServiceFactory.createTrTramitesService().getTramitesEnviados(em);
                    break;
                case 3:
                    vo = ServiceFactory.createTrTramitesService().getTramitesDevueltos(userCode, em);
                    break;
                case 4:
                    vo = ServiceFactory.createTrTramitesService().getTramitesAprobados(em);
                    break;
                case 5:
                    vo = ServiceFactory.createTrTramitesService().getTramitesTerminados(em);
                    break;
                case 6:
                    vo = ServiceFactory.createTrTramitesService().getTramitesAsesor(userCode, em);
                    break;
            }
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TrTramitesVO> cargarTramites(int first, int max, int tramiteId, int usuario, String operador, int estado, int radicado){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TrTramitesVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().cargarTramites(first, max, tramiteId, usuario, operador, estado, radicado, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TsTramiteSenalizacionVO> buscarTramitePorSenalizacion(int senCodigo, int acnCodigo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TsTramiteSenalizacionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarTramiteSenalizacion(senCodigo, acnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TcTramiteCcVO> buscarTramitePorCodigoCorto(int ccnCodigo, int acnCodigo){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TcTramiteCcVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarTramiteCodigoCorto(ccnCodigo, acnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TlTramiteLdVO> buscarTramitePorCodigoLd(int clnCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TlTramiteLdVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarTramiteCodigoLd(clnCodigo, acnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TaTramiteMaVO> buscarTramitePorMarcacionAbreviada(int manCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TaTramiteMaVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarTramiteMarcacionAbreviada(manCodigo, acnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TmTramiteMncVO> buscarTramitePorCodigosMnc(int cdnCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TmTramiteMncVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarTramiteCodigosMnc(cdnCodigo, acnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TkTramiteNrnVO> buscarTramitePorCodigosNrn(int nrnCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TkTramiteNrnVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarTramiteCodigosNrn(nrnCodigo, acnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<TiTramiteIinVO> buscarTramitePorCodigosIin(int cinCodigo, int acnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<TiTramiteIinVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().buscarTramiteCodigosIin(cinCodigo, acnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<EmOperadorVO> cargarOperadores() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<EmOperadorVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createTrTramitesService().cargarOperadores(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
    
    public List<RsReservasTemporalesVO> consultaReservasTemporales() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<RsReservasTemporalesVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createRsReservasTemporalesService().getList(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
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
                ServiceFactory.createNuNumeracionService().transferirNumeracion(operadorOrigen, operadorDestino, em);
            }
            if (sen){
               ServiceFactory.createSeSenalizacionService().transferirSenalizacion(operadorOrigen, operadorDestino, em);
            }              
            
            /*if (iin){
               ServiceFactory.createSeSenalizacionService().transferirIIN(operadorOrigen, operadorDestino, em);
            }              
            if (mnc){
               ServiceFactory.createSeSenalizacionService().transferirMNC(operadorOrigen, operadorDestino, em);
            }              
             */
            
            resultado=true;
            tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
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
                    resultado =ServiceFactory.createSeSenalizacionService().reservarLiberarSenalizacion((SeSenalizacionVO) r, em, accion);
                } else if (nombre.equals("NuNumeracionVO")){
                    resultado = ServiceFactory.createNuNumeracionService().reservarLiberarNumeracion((NuNumeracionVO) r, em, accion);
                } else if (nombre.equals("ClCodigosLdVO")){
                    resultado = ServiceFactory.createClCodigosLdService().reservarLiberarCodigoLd((ClCodigosLdVO) r, em, accion);
                } else if (nombre.equals("CcCodigosCortosVO")){
                    resultado = ServiceFactory.createCcCodigosCortosService().reservarLiberarCodigoCorto((CcCodigosCortosVO) r, em, accion);
                } else if (nombre.equals("MaMarcacionAbreviadaVO")){
                    resultado = ServiceFactory.createMaMarcacionAbreviadaService().reservarLiberarMarcacionAbreviada((MaMarcacionAbreviadaVO) r, em, accion);
                } else if (nombre.equals("CdCodigosMncVO")){
                    resultado = ServiceFactory.createCdCodigosMncService().reservarLiberarCodigosMnc((CdCodigosMncVO) r, em, accion);
                } else if (nombre.equals("NrCodigosNrnVO")){
                    resultado = ServiceFactory.createNrCodigosNrnService().reservarLiberarCodigosNrn((NrCodigosNrnVO) r, em, accion);
                } else if (nombre.equals("CiCodigosIinVO")){
                    resultado = ServiceFactory.createCiCodigosIinService().reservarLiberarCodigosIin((CiCodigosIinVO) r, em, accion);
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
            System.out.println(e);
            resultado = 0;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    }
    
    public Object consultaRecurso(String tipoRecurso, int codigoRecurso){
        Object recurso=null;
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();

            if(tipoRecurso.equals("Senalizacion")){
                recurso = ServiceFactory.createSeSenalizacionService().getById(codigoRecurso, em);
            } else if(tipoRecurso.equals("Numeracion")){
                recurso = ServiceFactory.createNuNumeracionService().getById(codigoRecurso, em);
            } else if(tipoRecurso.equals("CodigosLd")){
                recurso = ServiceFactory.createClCodigosLdService().getById(codigoRecurso, em);
            } else if(tipoRecurso.equals("CodigosCortos")){
                recurso = ServiceFactory.createCcCodigosCortosService().getById(codigoRecurso, em);
            } else if(tipoRecurso.equals("MarcacionAbreviada")){
                recurso = ServiceFactory.createMaMarcacionAbreviadaService().getById(codigoRecurso, em);
            } else if(tipoRecurso.equals("CodigosMnc")){
                recurso = ServiceFactory.createCdCodigosMncService().getById(codigoRecurso, em);
            } else if(tipoRecurso.equals("CodigosNrn")){
                recurso = ServiceFactory.createNrCodigosNrnService().getById(codigoRecurso, em);
            } else if(tipoRecurso.equals("CodigosIin")){
                recurso = ServiceFactory.createCiCodigosIinService().getById(codigoRecurso, em);
            }
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return recurso;
    }
    
    public Boolean consultaReservaTemporal(int codigoRecurso, String tipoRecurso){
        Boolean resultado = false;
        
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            resultado = ServiceFactory.createRsReservasTemporalesService().consultaReservaTemporal(codigoRecurso, tipoRecurso, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    } 

    //-------- CONFIGURACIÓN MÓDULOS --------

    public List<CmConfiguracionModulosVO> listaModulos() {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        List<CmConfiguracionModulosVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCmConfiguracionModulosService().getList(em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
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
            ServiceFactory.createCmConfiguracionModulosService().editarConfiguracionModulo(vo, em);
            tx.commit();
            resultado = true;
        } catch (Exception e) {
            System.out.println(e);
            resultado = false;
            if(em != null && tx != null){
                tx.rollback();
            }
        } finally {
            CloseEntityManager.close(em);
        }
        return resultado;
    }
    
    public CmConfiguracionModulosVO buscarConfiguracionModulo(Integer cmnCodigo) {
        EntityManagerFactory emf = null;
        EntityManager em = null;
        //EntityTransaction tx = null;
        CmConfiguracionModulosVO vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            //tx = em.getTransaction();
            //tx.begin();
            vo = ServiceFactory.createCmConfiguracionModulosService().getById(cmnCodigo, em);
            //tx.commit();
        } catch (Exception e) {
            System.out.println(e);
            //if(em != null && tx != null){
            //    tx.rollback();
            //}
        } finally {
            CloseEntityManager.close(em);
        }
        return vo;
    }
}
