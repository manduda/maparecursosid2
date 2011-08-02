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
