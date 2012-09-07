/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import OID.GetAuthenticated;
import OID.AutenticacionLDAP;
import OID.oidApoyo;
import com.novell.ldap.LDAPConnection;
import daos.UsUsuariosDAO;
import entities.TuTipoUsuario;
import entities.UsUsuarios;
import entities.Users;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Date;
import java.util.Enumeration;
import java.util.List;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.naming.AuthenticationException;
import javax.naming.Binding;
import javax.naming.Context;
import javax.naming.InitialContext;
import javax.naming.NamingEnumeration;
import javax.naming.NamingException;
import javax.naming.RefAddr;
import javax.naming.Reference;
import javax.naming.directory.DirContext;
import javax.naming.directory.InitialDirContext;
import javax.persistence.EntityManager;
import vo.TuTipoUsuarioVO;
import vo.UsUsuariosVO;
import vo.UsersVO;

/**
 *
 * @author miguel.duran
 */
public class UsUsuariosService {
    public UsUsuariosVO getVOFromEntity(UsUsuarios entity){
        UsUsuariosVO vo = new UsUsuariosVO();
        // Datos Usuario
        UsersVO datosusuario = new UsersVO();
        datosusuario.setUserCode(entity.getCodigoSIUST().getUserCode());
        datosusuario.setName(entity.getCodigoSIUST().getName());
        datosusuario.setLastName(entity.getCodigoSIUST().getLastName());
        datosusuario.setEmail(entity.getCodigoSIUST().getEmail());
        datosusuario.setLogin(entity.getCodigoSIUST().getLogin());
        datosusuario.setPassword(entity.getCodigoSIUST().getPassword());
        vo.setCodigoSIUST(datosusuario);
        //------------------------------------
        // Tipo Usuario
        TuTipoUsuarioVO tipousuario = new TuTipoUsuarioVO();
        tipousuario.setTunCodigo(entity.getTunCodigo().getTunCodigo());
        tipousuario.setTutNombre(entity.getTunCodigo().getTutNombre());
        vo.setTunCodigo(tipousuario);
        //------------------------------------
        vo.setUsnCodigo(entity.getUsnCodigo());
        vo.setUsnEstado(entity.getUsnEstado());
        vo.setUsfFecha(entity.getUsfFecha());

        return vo;
    }
    
    public boolean autenticar(String usuario, String contrasena) {
        /*
         * 1 - JDNI a través de Resource Reference
         * 2 - JDNI a través de Archivo Properties
         * 3 - Archivo properties con librería LDAP
         */
        boolean resultado = false;
        resultado = AutenticacionLDAP.autenticar(usuario, contrasena, 2);
        return resultado;
    }
    
    public UsUsuariosVO cargarUsuario(String usuario, EntityManager em) {
        UsUsuarios u = UsUsuariosDAO.cargar(usuario, em);
        
        if(u!=null){
            UsUsuariosVO uVO = new UsUsuariosVO();
            uVO = getVOFromEntity(u);
            return uVO;
        }
        return null;         
    }
    
    public List<UsUsuariosVO> getList(EntityManager em){
        List<UsUsuarios> usuarios = UsUsuariosDAO.getList(em);
        List<UsUsuariosVO> usuariosVO = new ArrayList<UsUsuariosVO>();
        for (UsUsuarios u : usuarios) {
            usuariosVO.add(u.toVO());
        }
        return usuariosVO;
    }
    
    public List<UsUsuariosVO> getAsesores(EntityManager em){
        List<UsUsuarios> usuarios = UsUsuariosDAO.getAsesores(em);
        List<UsUsuariosVO> usuariosVO = new ArrayList<UsUsuariosVO>();
        for (UsUsuarios u : usuarios) {
            usuariosVO.add(u.toVO());
        }
        return usuariosVO;
    }
    
    public List<UsUsuariosVO> getUsuarios(int tipoUsuario, EntityManager em){
        List<UsUsuarios> usuarios = UsUsuariosDAO.getUsuarios(tipoUsuario, em);
        List<UsUsuariosVO> usuariosVO = new ArrayList<UsUsuariosVO>();
        for (UsUsuarios u : usuarios) {
            usuariosVO.add(u.toVO());
        }
        return usuariosVO;
    }
        
    public List<UsersVO> getUsuariosNoAplicacion(EntityManager em){
        List<Users> usuarios = UsUsuariosDAO.getUsuariosNoAplicacion(em);
        List<UsersVO> usuariosVO = new ArrayList<UsersVO>();
        for (Users u : usuarios) {
            usuariosVO.add(u.toVO());
        }
        return usuariosVO;
    }
    
    public List<UsersVO> getUsuariosSIUST(EntityManager em){
        List<Users> usuarios = UsUsuariosDAO.getUsuariosSIUST(em);
        List<UsersVO> usuariosVO = new ArrayList<UsersVO>();
        for (Users u : usuarios) {
            usuariosVO.add(u.toVO());
        }
        return usuariosVO;
    }
    
    public UsUsuariosVO buscarUsuario(int userCode, EntityManager em){
        UsUsuarios usuario = UsUsuariosDAO.buscarUsuario(userCode, em);
        return usuario.toVO();
    }
    
    public Integer cambiarPerfil(UsUsuariosVO user, int perfil, EntityManager em){
        /*
         * 1: Perfil cambiado correctamente
         * 2: El perfil actual y el nuevo son iguales
        */
        UsUsuarios entity = new UsUsuarios();
        
        entity = UsUsuariosDAO.findbyId(user.getUsnCodigo(), em);
        if(entity != null){
            if(entity.getTunCodigo().getTunCodigo() == perfil){
                return 2;
            }
            entity.setUsnEstado(0);
            UsUsuariosDAO.merge(entity, em);
        }
        
        if(perfil != 0){
            UsUsuarios newEntity = new UsUsuarios();
            newEntity.setUsnEstado(1);
            Users userSIUST = new Users();
            userSIUST.setUserCode(user.getCodigoSIUST().getUserCode());
            newEntity.setCodigoSIUST(userSIUST);
            TuTipoUsuario tipousuario = new TuTipoUsuario();
            tipousuario.setTunCodigo(perfil);
            newEntity.setTunCodigo(tipousuario);
            newEntity.setUsnCodigo(UsUsuariosDAO.getMaxId(em)+1);
            newEntity.setUsfFecha(new Date());
            UsUsuariosDAO.persist(newEntity, em);
        }
        return 1;
    }
    
}
