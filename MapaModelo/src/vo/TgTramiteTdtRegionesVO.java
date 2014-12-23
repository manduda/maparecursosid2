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
public class TgTramiteTdtRegionesVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tgnCodigo;
    private TdTramiteTdtVO tdnCodigo;
    private MunicipiosVO codigoMunicipio;
    
    public TgTramiteTdtRegionesVO() {
    }

    public MunicipiosVO getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(MunicipiosVO codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public TdTramiteTdtVO getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(TdTramiteTdtVO tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public int getTgnCodigo() {
        return tgnCodigo;
    }

    public void setTgnCodigo(int tgnCodigo) {
        this.tgnCodigo = tgnCodigo;
    }
    
}
