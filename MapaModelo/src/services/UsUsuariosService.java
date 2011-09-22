/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import daos.UsUsuariosDAO;
import entities.UsUsuarios;
import java.util.ArrayList;
import java.util.List;
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
    
    public List<UsUsuariosVO> getList(EntityManager em){
        List<UsUsuarios> usuarios = UsUsuariosDAO.getList(em);
        List<UsUsuariosVO> usuariosVO = new ArrayList<UsUsuariosVO>();
        for (UsUsuarios u : usuarios) {
            usuariosVO.add(u.toVO());
        }
        return usuariosVO;
    }
}
