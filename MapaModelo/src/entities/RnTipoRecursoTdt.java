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
import vo.RnTipoRecursoTdtVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "RN_TIPO_RECURSO_TDT")
public class RnTipoRecursoTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "RNN_CODIGO")
    private int rnnCodigo;
    
    @Basic(optional = false)
    @Column(name = "RNT_NOMBRE")
    private String rntNombre;
    
    public RnTipoRecursoTdt() {
    }
    
    public RnTipoRecursoTdtVO toVO(){
        RnTipoRecursoTdtVO vo = new RnTipoRecursoTdtVO();
        vo.setRnnCodigo(this.getRnnCodigo());
        vo.setRntNombre(this.getRntNombre());
        return vo;
    }

    public int getRnnCodigo() {
        return rnnCodigo;
    }

    public void setRnnCodigo(int rnnCodigo) {
        this.rnnCodigo = rnnCodigo;
    }

    public String getRntNombre() {
        return rntNombre;
    }

    public void setRntNombre(String rntNombre) {
        this.rntNombre = rntNombre;
    }

}
