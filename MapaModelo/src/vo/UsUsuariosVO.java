/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.util.Date;

/**
 *
 * @author miguel.duran
 */
public class UsUsuariosVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    //public static final int ADMINISTRADOR = 1;
    //public static final int COORDINADOR = 2;
    //public static final int ASESOR = 3;
    
    private int usnCodigo;
    private UsersVO codigoSIUST;
    private TuTipoUsuarioVO tunCodigo;
    private int usnEstado;
    private Date usfFecha;

    public UsUsuariosVO() {
    }

    public UsersVO getCodigoSIUST() {
        return codigoSIUST;
    }

    public void setCodigoSIUST(UsersVO codigoSIUST) {
        this.codigoSIUST = codigoSIUST;
    }

    public TuTipoUsuarioVO getTunCodigo() {
        return tunCodigo;
    }

    public void setTunCodigo(TuTipoUsuarioVO tunCodigo) {
        this.tunCodigo = tunCodigo;
    }

    public int getUsnCodigo() {
        return usnCodigo;
    }

    public void setUsnCodigo(int usnCodigo) {
        this.usnCodigo = usnCodigo;
    }

    public int getUsnEstado() {
        return usnEstado;
    }

    public void setUsnEstado(int usnEstado) {
        this.usnEstado = usnEstado;
    }

    public Date getUsfFecha() {
        return usfFecha;
    }

    public void setUsfFecha(Date usfFecha) {
        this.usfFecha = usfFecha;
    }
    
}
