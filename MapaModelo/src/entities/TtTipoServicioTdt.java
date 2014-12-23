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
import vo.TtTipoServicioTdtVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TT_TIPO_SERVICIO_TDT")
public class TtTipoServicioTdt implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TTN_CODIGO")
    private int ttnCodigo;
    
    @Basic(optional = false)
    @Column(name = "TTT_NOMBRE")
    private String tttNombre;
    
    public TtTipoServicioTdt() {
    }
    
    public TtTipoServicioTdtVO toVO(){
        TtTipoServicioTdtVO vo = new TtTipoServicioTdtVO();
        vo.setTtnCodigo(this.getTtnCodigo());
        vo.setTttNombre(this.getTttNombre());
        return vo;
    }

    public int getTtnCodigo() {
        return ttnCodigo;
    }

    public void setTtnCodigo(int ttnCodigo) {
        this.ttnCodigo = ttnCodigo;
    }

    public String getTttNombre() {
        return tttNombre;
    }

    public void setTttNombre(String tttNombre) {
        this.tttNombre = tttNombre;
    }
    
}
