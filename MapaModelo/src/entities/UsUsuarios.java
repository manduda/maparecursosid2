/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import vo.TuTipoUsuarioVO;
import vo.UsUsuariosVO;
import vo.UsersVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "US_USUARIOS")
public class UsUsuarios implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @JoinColumn(name = "USN_CODIGO", referencedColumnName = "USER_CODE")
    @ManyToOne(optional = false)
    private Users usnCodigo;
    
    @JoinColumn(name = "TUN_CODIGO", referencedColumnName = "TUN_CODIGO")
    @ManyToOne(optional = false)
    private TuTipoUsuario tunCodigo;
        
    @Basic(optional = false)
    @Column(name = "USN_ESTADO")
    private int usnEstado;

    public UsUsuarios(){
    }

    public UsUsuariosVO toVO(){
        UsUsuariosVO vo = new UsUsuariosVO();
        // Datos Usuario
        UsersVO datosusuario = new UsersVO();
        datosusuario.setUserCode(this.getUsnCodigo().getUserCode());
        datosusuario.setName(this.getUsnCodigo().getName());
        datosusuario.setLastName(this.getUsnCodigo().getLastName());
        datosusuario.setEmail(this.getUsnCodigo().getEmail());
        datosusuario.setLogin(this.getUsnCodigo().getLogin());
        datosusuario.setPassword(this.getUsnCodigo().getPassword());
        vo.setUsnCodigo(datosusuario);
        //------------------------------------
        // Tipo Usuario
        TuTipoUsuarioVO tipousuario = new TuTipoUsuarioVO();
        tipousuario.setTunCodigo(this.getTunCodigo().getTunCodigo());
        tipousuario.setTutNombre(this.getTunCodigo().getTutNombre());
        vo.setTunCodigo(tipousuario);
        //------------------------------------
        vo.setUsnEstado(this.getUsnEstado());
        
        return vo;
    }
    
    
    public TuTipoUsuario getTunCodigo() {
        return tunCodigo;
    }

    public void setTunCodigo(TuTipoUsuario tunCodigo) {
        this.tunCodigo = tunCodigo;
    }

    public Users getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(Users usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

    public int getUsnEstado() {
        return usnEstado;
    }

    public void setUsnEstado(int usnEstado) {
        this.usnEstado = usnEstado;
    }

}
