/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import vo.CoConfiguracionVO;

/**
 *
 * @author MADD
 */
@Entity
@Table(name = "CO_CONFIGURACION")
public class CoConfiguracion implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "CON_CODIGO")
    private int conCodigo;

    @Basic(optional = false)
    @Column(name = "COT_DESCRIPCION")
    private String cotDescripcion;
    
    @Basic(optional = false)
    @Column(name = "COT_VALOR")
    private String cotValor;
    
    public CoConfiguracion() {
    }
    
    public CoConfiguracionVO toVO() {
        CoConfiguracionVO vo = new CoConfiguracionVO();
        
        vo.setConCodigo(this.getConCodigo());
        vo.setCotDescripcion(this.getCotDescripcion());
        vo.setCotValor(this.getCotValor());
        return vo;
    }

    public int getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(int conCodigo) {
        this.conCodigo = conCodigo;
    }

    public String getCotDescripcion() {
        return cotDescripcion;
    }

    public void setCotDescripcion(String cotDescripcion) {
        this.cotDescripcion = cotDescripcion;
    }

    public String getCotValor() {
        return cotValor;
    }

    public void setCotValor(String cotValor) {
        this.cotValor = cotValor;
    }
    
}
