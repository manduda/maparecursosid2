/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author miguel.duran
 */
public class UsUsuariosVO {
    
    public static final int ADMINISTRADOR = 1;
    public static final int COORDINADOR = 2;
    public static final int ASESOR = 3;
    
    private UsersVO usnCodigo;
    private TuTipoUsuarioVO tunCodigo;
    private int usnEstado;

    public UsUsuariosVO() {
    }
        
    public TuTipoUsuarioVO getTunCodigo() {
        return tunCodigo;
    }

    public void setTunCodigo(TuTipoUsuarioVO tunCodigo) {
        this.tunCodigo = tunCodigo;
    }

    public UsersVO getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(UsersVO usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

    public int getUsnEstado() {
        return usnEstado;
    }

    public void setUsnEstado(int usnEstado) {
        this.usnEstado = usnEstado;
    }

}
