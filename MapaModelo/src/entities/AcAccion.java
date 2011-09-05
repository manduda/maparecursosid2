/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.math.BigDecimal;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;
import vo.AcAccionVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "AC_ACCION")
public class AcAccion implements Serializable {
    private static final long serialVersionUID = 1L;

    @Id
    @Basic(optional = false)
    @Column(name = "ACN_CODIGO")
    private int acnCodigo;
    
    @Basic(optional = false)
    @Column(name = "ACT_NOMBRE")
    private String actNombre;

    public AcAccion() {
    }
    
    public AcAccionVO toVO(){
        AcAccionVO vo = new AcAccionVO();
        vo.setAcnCodigo(this.getAcnCodigo());
        vo.setActNombre(this.getActNombre());
        return vo;
    }

    public int getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(int acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public String getActNombre() {
        return actNombre;
    }

    public void setActNombre(String actNombre) {
        this.actNombre = actNombre;
    }    
}
