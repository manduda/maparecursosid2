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
public class MrMultiplexRecursoVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int mrnCodigo;
    private RiRecursosTdtVO rinCodigo;
    private MuMultiplexVO munCodigo;
    
    public MrMultiplexRecursoVO() {
    }

    public int getMrnCodigo() {
        return mrnCodigo;
    }

    public void setMrnCodigo(int mrnCodigo) {
        this.mrnCodigo = mrnCodigo;
    }

    public MuMultiplexVO getMunCodigo() {
        return munCodigo;
    }

    public void setMunCodigo(MuMultiplexVO munCodigo) {
        this.munCodigo = munCodigo;
    }

    public RiRecursosTdtVO getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdtVO rinCodigo) {
        this.rinCodigo = rinCodigo;
    }
    
}
