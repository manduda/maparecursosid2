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
import javax.persistence.Table;
import vo.MoModalidad1xyVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "MO_MODALIDAD_1XY")
public class MoModalidad1xy implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "MON_CODIGO")
    private int monCodigo;
    
    @Basic(optional = false)
    @Column(name = "MOT_NOMBRE")
    private String motNombre;
    
    public MoModalidad1xy() {
    }
    
    public MoModalidad1xyVO toVO() {
        MoModalidad1xyVO vo = new MoModalidad1xyVO();
        vo.setMonCodigo(this.getMonCodigo());
        vo.setMotNombre(this.getMotNombre());
        return vo;
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
