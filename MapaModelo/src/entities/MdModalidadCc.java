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
import vo.MdModalidadCcVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "MD_MODALIDAD_CC")
public class MdModalidadCc implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "MDN_CODIGO")
    private int mdnCodigo;
    
    @Basic(optional = false)
    @Column(name = "MDT_NOMBRE")
    private String mdtNombre;
    
    public MdModalidadCc() {
    }
    
    public MdModalidadCcVO toVO() {
        MdModalidadCcVO vo = new MdModalidadCcVO();
        vo.setMdnCodigo(this.getMdnCodigo());
        vo.setMdtNombre(this.getMdtNombre());
        return vo;
    }

    public int getMdnCodigo() {
        return mdnCodigo;
    }
    
    public void setMdnCodigo(int mdnCodigo) {
        this.mdnCodigo = mdnCodigo;
    }

    public String getMdtNombre() {
        return mdtNombre;
    }

    public void setMdtNombre(String mdtNombre) {
        this.mdtNombre = mdtNombre;
    }

}
