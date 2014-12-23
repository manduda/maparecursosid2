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
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import vo.MuMultiplexVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "MU_MULTIPLEX")
public class MuMultiplex implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "MUN_CODIGO")
    private int munCodigo;
    
    @JoinColumn(name = "SK_EMPRESA_CODE", referencedColumnName = "SK_EMPRESA_CODE")
    @ManyToOne(optional = false)
    private EmOperador emrCodigo;
    
    @Basic(optional = false)
    @Column(name = "MUT_NOMBRE_MULTIPLEX")
    private String mutNombreMultiplex;
    
    public MuMultiplex() {
    }
    
    public MuMultiplexVO toVO(){
        MuMultiplexVO vo = new MuMultiplexVO();
        vo.setMunCodigo(this.getMunCodigo());
        vo.setEmrCodigo(this.getEmrCodigo().toVO());
        vo.setMutNombreMultiplex(this.getMutNombreMultiplex());
        return vo;
    }

    public EmOperador getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperador emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public int getMunCodigo() {
        return munCodigo;
    }

    public void setMunCodigo(int munCodigo) {
        this.munCodigo = munCodigo;
    }

    public String getMutNombreMultiplex() {
        return mutNombreMultiplex;
    }

    public void setMutNombreMultiplex(String mutNombreMultiplex) {
        this.mutNombreMultiplex = mutNombreMultiplex;
    }

}
