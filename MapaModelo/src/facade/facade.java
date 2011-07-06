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
import services.NuNumeracionService;
import vo.ClCodigosLdVO;
import vo.NuNumeracionVO;

/**
 *
 * @author miguel.duran
 */
public class facade {
    private ClCodigosLdService codigosld;
    private NuNumeracionService numeracion;

    public facade(){
        codigosld = new ClCodigosLdService();
        numeracion = new NuNumeracionService();
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

    public List<NuNumeracionVO> ListaNumeracion(){
        EntityManagerFactory emf = null;
        EntityManager em = null;
        EntityTransaction tx = null;
        List<NuNumeracionVO> vo = null;
        try {
            emf = Persistence.createEntityManagerFactory("MapaModeloPU");
            em = emf.createEntityManager();
            tx = em.getTransaction();
            tx.begin();
            vo = numeracion.getList(em);
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
