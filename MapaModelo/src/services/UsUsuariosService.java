/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.UsUsuariosDAO;
import entities.UsUsuarios;
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
        datosusuario.setUserCode(entity.getUsnCodigo().getUserCode());
        datosusuario.setName(entity.getUsnCodigo().getName());
        datosusuario.setLastName(entity.getUsnCodigo().getLastName());
        datosusuario.setEmail(entity.getUsnCodigo().getEmail());
        datosusuario.setLogin(entity.getUsnCodigo().getLogin());
        datosusuario.setPassword(entity.getUsnCodigo().getPassword());
        vo.setUsnCodigo(datosusuario);
        //------------------------------------
        // Tipo Usuario
        TuTipoUsuarioVO tipousuario = new TuTipoUsuarioVO();
        tipousuario.setTunCodigo(entity.getTunCodigo().getTunCodigo());
        tipousuario.setTutNombre(entity.getTunCodigo().getTutNombre());
        vo.setTunCodigo(tipousuario);
        //------------------------------------
        vo.setUsnEstado(entity.getUsnEstado());

        return vo;
    }
    
    public UsUsuariosVO cargarUsuario(String usuario, String contrasena, EntityManager em) {
        UsUsuarios u = UsUsuariosDAO.cargar(usuario, contrasena, em);
        
        if(u!=null){
            UsUsuariosVO uVO = new UsUsuariosVO();
            uVO = getVOFromEntity(u);
            return uVO;
        }
        return null;         
    }
}
