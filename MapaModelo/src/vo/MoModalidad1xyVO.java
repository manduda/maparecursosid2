/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Id;

/**
 *
 * @author miguel.duran
 */
public class MoModalidad1xyVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "MON_CODIGO")
    private int monCodigo;
    
    @Basic(optional = false)
    @Column(name = "MOT_NOMBRE")
    private String motNombre;
    
    public MoModalidad1xyVO() {
    }

    public int getMonCodigo() {
        return monCodigo;
    }

    public void setMonCodigo(int monCodigo) {
        this.monCodigo = monCodigo;
    }

    public String getMotNombre() {
        return motNombre;
    }

    public void setMotNombre(String motNombre) {
        this.motNombre = motNombre;
    }
    
}
