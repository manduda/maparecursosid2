/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package facade;

import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.Persistence;
import services.ClCodigosLdService;
import services.NdNdcService;
import services.NuNumeracionService;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.NdNdcVO;
import vo.NuNumeracionVO;

/**
 *
 * @author miguel.duran
 */
public class facade {
    private ClCodigosLdService codigosld;
    private NuNumeracionService numeracion;
    private NdNdcService ndc;

    public facade(){
        codigosld = new ClCodigosLdService();
        numeracion = new NuNumeracionService();
        ndc = new NdNdcService();
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

    public List<NuNumeracionVO> cargarNumeracion(int first, int max, String operador, int ndc, int inicio, int fin){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<NuNumeracionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = numeracion.cargarNumeracion(first, max, operador, ndc, inicio, fin, em);
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
    
    public int countCargarNumeracion(String operador, int ndc, int inicio, int fin){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        int cantidad = 0;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            cantidad = numeracion.countCargarNumeracion(operador, ndc, inicio, fin, em);
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
    
    public List<EmOperadorVO> listaOperador() {
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
    
}
