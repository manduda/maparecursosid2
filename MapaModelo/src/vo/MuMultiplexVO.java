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
public class MuMultiplexVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int munCodigo;
    private EmOperadorVO emrCodigo;
    private String mutNombreMultiplex;
    
    public MuMultiplexVO() {
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
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
