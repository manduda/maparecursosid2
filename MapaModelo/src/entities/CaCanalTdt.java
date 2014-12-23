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
import vo.CaCanalTdtVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "CA_CANAL_TDT")
public class CaCanalTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "CAN_CODIGO")
    private int canCodigo;
    
    @Basic(optional = false)
    @Column(name = "CAT_CANAL")
    private String catCanal;
    
    @Basic(optional = false)
    @Column(name = "CAN_FRECUENCIA")
    private int canFrecuencia;
    
    public CaCanalTdt() {
    }
    
    public CaCanalTdtVO toVO(){
        CaCanalTdtVO vo = new CaCanalTdtVO();
        vo.setCanCodigo(this.getCanCodigo());
        vo.setCatCanal(this.getCatCanal());
        vo.setCanFrecuencia(this.getCanFrecuencia());
        return vo;
    }

    public String getCatCanal() {
        return catCanal;
    }

    public void setCatCanal(String catCanal) {
        this.catCanal = catCanal;
    }

    public int getCanCodigo() {
        return canCodigo;
    }

    public void setCanCodigo(int canCodigo) {
        this.canCodigo = canCodigo;
    }

    public int getCanFrecuencia() {
        return canFrecuencia;
    }

    public void setCanFrecuencia(int canFrecuencia) {
        this.canFrecuencia = canFrecuencia;
    }

}
