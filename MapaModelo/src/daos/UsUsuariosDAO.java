/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.TuTipoUsuario;
import entities.UsUsuarios;
import entities.Users;
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
    
    public static UsUsuarios cargar(String usuario, String contrasena, EntityManager em) {
        
        Query query = em.createQuery("SELECT u FROM UsUsuarios u "
                + "WHERE u.codigoSIUST.login = :usuario AND u.codigoSIUST.password = :contrasena "
                + "ORDER BY u.usnCodigo DESC");
        
        query.setParameter("usuario", usuario);
        query.setParameter("contrasena", contrasena);
        query.setMaxResults(1);
        
        UsUsuarios u = null;
        u = (UsUsuarios)query.getSingleResult();
        
        return u;
    }
    
    public static List<UsUsuarios> getList(EntityManager em){
        Query query = em.createQuery("SELECT u FROM UsUsuarios u WHERE u.usnEstado = 1");
        return query.getResultList();
    }
    
    public static List<Users> getUsuariosNoAplicacion(EntityManager em) {
        
        Query query = em.createQuery("SELECT us FROM Users us "
                + "WHERE us.userCode NOT IN (SELECT u.codigoSIUST.userCode FROM UsUsuarios u WHERE u.usnEstado = 1)");
        
        return query.getResultList();
    }
    
    public static List<Users> getUsuariosSIUST(EntityManager em) {
        
        Query query = em.createQuery("SELECT us FROM Users us ORDER BY us.login");
        
        return query.getResultList();
    }
    
    public static UsUsuarios buscarUsuario(int userCode, EntityManager em) {
        Query query = em.createNativeQuery("SELECT US.USER_CODE, US.NAME, US.LAST_NAME, US.EMAIL, US.LOGIN, U.TUN_CODIGO, U.TUT_NOMBRE, U.USN_ESTADO, U.USN_CODIGO, U.USF_FECHA "
                + "FROM (SELECT U.*, TU.TUT_NOMBRE FROM US_USUARIOS U, TU_TIPO_USUARIO TU WHERE U.USN_ESTADO = 1 AND U.TUN_CODIGO = TU.TUN_CODIGO) U "
                + "RIGHT JOIN SA.USERS US "
                + "ON (U.CODIGO_SIUST = US.USER_CODE) "
                + "WHERE US.USER_CODE = ?1");
        
        query.setParameter(1, userCode);
        
        Object[] results = (Object [])query.getSingleResult();
        
        UsUsuarios user = new UsUsuarios();
        
        Users usuario = new Users();
        usuario.setUserCode( Integer.parseInt(results[0].toString()) );
        usuario.setName((String)results[1]);
        usuario.setLastName((String)results[2]);
        usuario.setEmail((String)results[3]);
        usuario.setLogin((String)results[4]);
        
        TuTipoUsuario tipousuario = new TuTipoUsuario();
        if (results[8] != null) {
            tipousuario.setTunCodigo( Integer.parseInt(results[5].toString()) );
            tipousuario.setTutNombre((String)results[6]);
            user.setUsnEstado( Integer.parseInt(results[7].toString()) );
            user.setUsnCodigo( Integer.parseInt(results[8].toString()) );
        }
        
        user.setTunCodigo(tipousuario);
        user.setCodigoSIUST(usuario);
        
        return user;

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
    
}
