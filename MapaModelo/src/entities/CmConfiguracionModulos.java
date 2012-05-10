/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package entities;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import vo.CmConfiguracionModulosVO;


/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "CM_CONFIGURACION_MODULOS")
public class CmConfiguracionModulos implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "CMN_CODIGO")
    private int cmnCodigo;

    @Basic(optional = false)
    @Column(name = "CMT_MODULO")
    private String cmtModulo;
      
    @Basic(optional = false)
    @Column(name = "CMT_ACTIVO")
    private char cmtActivo;
        
    public CmConfiguracionModulos() {
    }
    
    public CmConfiguracionModulosVO toVO() {
        CmConfiguracionModulosVO vo = new CmConfiguracionModulosVO();
        
        vo.setCmnCodigo(this.getCmnCodigo());
        vo.setCmtModulo(this.getCmtModulo());
        vo.setCmtActivo(this.getCmtActivo());
        return vo;
    }

    public int getCmnCodigo() {
        return cmnCodigo;
    }

    public void setCmnCodigo(int cmnCodigo) {
        this.cmnCodigo = cmnCodigo;
    }

    public char getCmtActivo() {
        return cmtActivo;
    }

    public void setCmtActivo(char cmtActivo) {
        this.cmtActivo = cmtActivo;
    }

    public String getCmtModulo() {
        return cmtModulo;
    }

    public void setCmtModulo(String cmtModulo) {
        this.cmtModulo = cmtModulo;
    }

}
