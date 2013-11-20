/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TuTipoUsuario;
import entities.UsUsuarios;
import entities.Users;
import entities.Usuarios;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;


/**
 *
 * @author miguel.duran
 */
public class UsUsuariosDAO {
    public static void persist(UsUsuarios entity, EntityManager em){
        em.persist(entity);
    }

    public static void merge(UsUsuarios entity, EntityManager em){
        em.merge(entity);
    }
    
    public static UsUsuarios findbyId(int usnCodigo, EntityManager em){
        return em.find(UsUsuarios.class, usnCodigo);
    }
    
    public static int getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(t.usnCodigo) FROM UsUsuarios t");
        Integer n = (Integer)query.getSingleResult();
        if (n == null){
            n = 0;
        }
        return n;
    }
    
    public static UsUsuarios cargar(String email, EntityManager em) {
        
        Query query = em.createQuery("SELECT u FROM UsUsuarios u "
                + "WHERE u.codigoSIUST.email = :email "
                + "AND u.usnEstado = 1 "
                + "ORDER BY u.usnCodigo DESC");
        
        query.setParameter("email", email);
        //query.setMaxResults(1);
        
        UsUsuarios u = null;
        u = (UsUsuarios)query.getSingleResult();
        
        return u;
    }
    
    public static List<UsUsuarios> getList(EntityManager em){
        Query query = em.createQuery("SELECT u FROM UsUsuarios u WHERE u.usnEstado = 1 ORDER BY u.codigoSIUST.email");
        return query.getResultList();
    }
    
    public static List<Users> getUsuariosNoAplicacion(EntityManager em) {
        
        Query query = em.createQuery("SELECT us FROM Usuarios us "
                + "WHERE us.userCode NOT IN (SELECT u.codigoSIUST.userCode FROM UsUsuarios u WHERE u.usnEstado = 1) us.email");
        
        return query.getResultList();
    }
    
    public static List<Usuarios> getUsuariosSIUST(EntityManager em) {
        Query query = em.createQuery("SELECT u FROM Usuarios u ORDER BY u.email");
        return query.getResultList();

    }
    
    public static UsUsuarios buscarUsuario(int userCode, EntityManager em) {
        Query query = em.createNativeQuery("SELECT US.USER_CODE, US.EMAIL, U.TUN_CODIGO, U.TUT_NOMBRE, U.USN_ESTADO, U.USN_CODIGO, U.USF_FECHA "
                + "FROM (SELECT U.*, TU.TUT_NOMBRE FROM US_USUARIOS U, TU_TIPO_USUARIO TU WHERE U.USN_ESTADO = 1 AND U.TUN_CODIGO = TU.TUN_CODIGO) U "
                + "RIGHT JOIN MAPA.USERS US "
                + "ON (U.CODIGO_SIUST = US.USER_CODE) "
                + "WHERE US.USER_CODE = ?1");
        
        query.setParameter(1, userCode);
        
        Object[] results = (Object [])query.getSingleResult();
        
        UsUsuarios user = new UsUsuarios();
        
        Usuarios usuario = new Usuarios();
        usuario.setUserCode( Integer.parseInt(results[0].toString()) );
        usuario.setEmail((String)results[1]);
        
        TuTipoUsuario tipousuario = new TuTipoUsuario();
        if (results[5] != null) {
            tipousuario.setTunCodigo( Integer.parseInt(results[2].toString()) );
            tipousuario.setTutNombre((String)results[3]);
            user.setUsnEstado( Integer.parseInt(results[4].toString()) );
            user.setUsnCodigo( Integer.parseInt(results[5].toString()) );
        }
        
        user.setTunCodigo(tipousuario);
        user.setCodigoSIUST(usuario);
        
        return user;

    }
    
    public static boolean buscarEmail(String email, EntityManager em) {
        boolean resultado = false;
        Query query = em.createNativeQuery("SELECT COUNT(*) "
                + "FROM (SELECT U.*, TU.TUT_NOMBRE FROM US_USUARIOS U, TU_TIPO_USUARIO TU WHERE U.USN_ESTADO = 1 AND U.TUN_CODIGO = TU.TUN_CODIGO) U "
                + "RIGHT JOIN MAPA.USERS US "
                + "ON (U.CODIGO_SIUST = US.USER_CODE) "
                + "WHERE US.EMAIL = ?1");
        
        query.setParameter(1, email);
        
        Number result = (Number) query.getSingleResult();
        if (result.intValue() > 0) {
            resultado = true;
        }
        
        return resultado;
        
    }
    
    public static List<UsUsuarios> getAsesores(EntityManager em){
        Query query = em.createQuery("SELECT u FROM UsUsuarios u WHERE u.usnEstado = 1 AND u.tunCodigo.tunCodigo = 3");
        return query.getResultList();
    }
    
    public static List<UsUsuarios> getUsuarios(int tipousuario, EntityManager em){
        Query query = em.createQuery("SELECT u FROM UsUsuarios u WHERE u.usnEstado = 1 AND u.tunCodigo.tunCodigo = :usuario");
        query.setParameter("usuario", tipousuario);
        return query.getResultList();
    }
    
    public static UsUsuarios getAsignador(EntityManager em){
        Query query = em.createQuery("SELECT u FROM UsUsuarios u WHERE u.usnEstado = 1 AND u.tunCodigo.tunCodigo = 4");
        UsUsuarios u = (UsUsuarios)query.getSingleResult();
        return u;
    }
    
}
