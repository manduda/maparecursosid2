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
public class RaRegionesTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int ranCodigo;
    private RiRecursosTdtVO rinCodigo;
    private MunicipiosVO codigoMunicipio;
    
    public RaRegionesTdtVO() {
    }

    public MunicipiosVO getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(MunicipiosVO codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public int getRanCodigo() {
        return ranCodigo;
    }

    public void setRanCodigo(int ranCodigo) {
        this.ranCodigo = ranCodigo;
    }

    public RiRecursosTdtVO getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdtVO rinCodigo) {
        this.rinCodigo = rinCodigo;
    }
    
}
