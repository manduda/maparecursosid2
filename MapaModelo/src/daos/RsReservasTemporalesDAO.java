/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.MaMarcacionAbreviada;
import entities.RsReservasTemporales;
import entities.TrTramites;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author juan.loaiza
 */
public class RsReservasTemporalesDAO {
   
    public static void persist(RsReservasTemporales entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(RsReservasTemporales entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(RsReservasTemporales entity, EntityManager em){
        em.merge(entity);
    }

    public static RsReservasTemporales findbyId(int rsnCodigo, EntityManager em){
        return em.find(RsReservasTemporales.class, rsnCodigo);
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.rsnCodigo) FROM RsReservasTemporales t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
    
    public static List<RsReservasTemporales> getList(EntityManager em){
        Query query = em.createQuery("SELECT e FROM RsReservasTemporales e");
        return query.getResultList();
    }
    
    public static List<RsReservasTemporales> getListRecuperar(EntityManager em){
        List<RsReservasTemporales> reservasTemporales = new ArrayList<RsReservasTemporales>();
        StringBuilder searchQuery = new StringBuilder(
                "SELECT A.*"
                + "  FROM MAPA.RS_RESERVAS_TEMPORALES A"
                + "  WHERE A.RSF_FECHA_LIBERACION <= TO_DATE(TO_CHAR(sysdate,'DD/MM/YYYY'),'DD/MM/YYYY')"
                + "  AND   A.RST_ESTADO = 'S'");
        
        Query query = em.createNativeQuery(searchQuery.toString());

        List<Object[]> results = new ArrayList<Object[]>();
        results = query.getResultList();
        
        for(int i=0; i < results.size(); i++){
            RsReservasTemporales reserva = new RsReservasTemporales();
            reserva.setRsnCodigo(Integer.valueOf(results.get(i)[0].toString()));
            reserva.setRsnCodigoRecurso(Integer.valueOf(results.get(i)[1].toString()));
            reserva.setRstTipoRecurso((String)results.get(i)[2]);
            
            TrTramites tramite = new TrTramites();
            tramite.setTrnCodigo(Integer.valueOf(results.get(i)[3].toString()));
            reserva.setTrnCodigo(tramite);
            reserva.setRstEstado(((String)results.get(i)[4]).charAt(0));
            reserva.setRsfFechaLiberacion((Date)results.get(i)[5]);
            reservasTemporales.add(reserva);
        }
        
        return reservasTemporales;
    }
    
    
    public static Boolean consultaReservaTemporal(int codigoRecurso, String tipoRecurso, EntityManager em){
        Boolean respuesta = false;
        
        String searchQuery = "SELECT e FROM RsReservasTemporales e "
                + "WHERE e.rsnCodigoRecurso = ?1 AND e.rstTipoRecurso = ?2 AND e.rstEstado = 'S'";
        
        Query query = em.createQuery(searchQuery);
        query.setParameter(1, codigoRecurso);
        query.setParameter(2, tipoRecurso);
        
        List<RsReservasTemporales> reservasTemporales = null;
        reservasTemporales = query.getResultList();
        
        if (reservasTemporales.isEmpty()) {
            respuesta = false; 
        } else {
            respuesta = true;
        }
        
        return respuesta;
    }
    
}
