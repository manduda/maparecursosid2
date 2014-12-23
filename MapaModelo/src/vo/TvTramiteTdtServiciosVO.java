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
public class TvTramiteTdtServiciosVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tvnCodigo;
    private TdTramiteTdtVO tdnCodigo;
    private TtTipoServicioTdtVO ttnCodigo;
    
    public TvTramiteTdtServiciosVO() {
    }

    public TdTramiteTdtVO getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(TdTramiteTdtVO tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public TtTipoServicioTdtVO getTtnCodigo() {
        return ttnCodigo;
    }

    public void setTtnCodigo(TtTipoServicioTdtVO ttnCodigo) {
        this.ttnCodigo = ttnCodigo;
    }

    public int getTvnCodigo() {
        return tvnCodigo;
    }

    public void setTvnCodigo(int tvnCodigo) {
        this.tvnCodigo = tvnCodigo;
    }
    
}
