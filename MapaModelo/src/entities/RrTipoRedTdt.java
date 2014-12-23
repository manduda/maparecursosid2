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
import vo.RrTipoRedTdtVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "RR_TIPO_RED_TDT")
public class RrTipoRedTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "RRN_CODIGO")
    private int rrnCodigo;
    
    @Basic(optional = false)
    @Column(name = "RRT_NOMBRE")
    private String rrtNombre;
    
    public RrTipoRedTdt() {
    }
    
    public RrTipoRedTdtVO toVO(){
        RrTipoRedTdtVO vo = new RrTipoRedTdtVO();
        vo.setRrnCodigo(this.getRrnCodigo());
        vo.setRrtNombre(this.getRrtNombre());
        return vo;
    }

    public int getRrnCodigo() {
        return rrnCodigo;
    }

    public void setRrnCodigo(int rrnCodigo) {
        this.rrnCodigo = rrnCodigo;
    }

    public String getRrtNombre() {
        return rrtNombre;
    }

    public void setRrtNombre(String rrtNombre) {
        this.rrtNombre = rrtNombre;
    }
    
}
