/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;

/**
 *
 * @author miguel.duran
 */
public class StServiciosTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int stnCodigo;
    private RiRecursosTdtVO rinCodigo;
    private TtTipoServicioTdtVO ttnCodigo;
    
    public StServiciosTdtVO() {
    }

    public RiRecursosTdtVO getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdtVO rinCodigo) {
        this.rinCodigo = rinCodigo;
    }

    public int getStnCodigo() {
        return stnCodigo;
    }

    public void setStnCodigo(int stnCodigo) {
        this.stnCodigo = stnCodigo;
    }

    public TtTipoServicioTdtVO getTtnCodigo() {
        return ttnCodigo;
    }

    public void setTtnCodigo(TtTipoServicioTdtVO ttnCodigo) {
        this.ttnCodigo = ttnCodigo;
    }
    
}
