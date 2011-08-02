/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Collection;

/**
 *
 * @author miguel.duran
 */
public class TuTipoUsuarioVO {
    private int tunCodigo;
    private String tutNombre;
    private Collection<UsUsuariosVO> usUsuariosCollection;

    public int getTunCodigo() {
        return tunCodigo;
    }

    public void setTunCodigo(int tunCodigo) {
        this.tunCodigo = tunCodigo;
    }

    public String getTutNombre() {
        return tutNombre;
    }

    public void setTutNombre(String tutNombre) {
        this.tutNombre = tutNombre;
    }

    public Collection<UsUsuariosVO> getUsUsuariosCollection() {
        return usUsuariosCollection;
    }

    public void setUsUsuariosCollection(Collection<UsUsuariosVO> usUsuariosCollection) {
        this.usUsuariosCollection = usUsuariosCollection;
    }

}
