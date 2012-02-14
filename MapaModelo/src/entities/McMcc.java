/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Collection;
import javax.persistence.Basic;
import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import vo.McMccVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "MC_MCC")
public class McMcc implements Serializable {
    private static final long serialVersionUID = 1L;
    // @Max(value=?)  @Min(value=?)//if you know range of your decimal fields consider using these annotations to enforce field validation
    @Id
    @Basic(optional = false)
    @Column(name = "MCN_CODIGO")
    private int mcnCodigo;
    
    @Basic(optional = false)
    @Column(name = "MCT_NOMBRE")
    private String mctNombre;

    public McMcc() {
    }
    
    public McMccVO toVO(){
        McMccVO vo = new McMccVO();
        vo.setMcnCodigo(this.getMcnCodigo());
        vo.setMctNombre(this.getMctNombre());
        
        return vo;
    }

    public int getMcnCodigo() {
        return mcnCodigo;
    }

    public void setMcnCodigo(int mcnCodigo) {
        this.mcnCodigo = mcnCodigo;
    }

    public String getMctNombre() {
        return mctNombre;
    }

    public void setMctNombre(String mctNombre) {
        this.mctNombre = mctNombre;
    }

}
