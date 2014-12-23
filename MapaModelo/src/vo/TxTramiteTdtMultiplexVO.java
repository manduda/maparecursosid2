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
public class TxTramiteTdtMultiplexVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int txnCodigo;
    private TdTramiteTdtVO tdnCodigo;
    private MuMultiplexVO munCodigo;
    
    public TxTramiteTdtMultiplexVO() {
    }

    public MuMultiplexVO getMunCodigo() {
        return munCodigo;
    }

    public void setMunCodigo(MuMultiplexVO munCodigo) {
        this.munCodigo = munCodigo;
    }

    public TdTramiteTdtVO getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(TdTramiteTdtVO tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public int getTxnCodigo() {
        return txnCodigo;
    }

    public void setTxnCodigo(int txnCodigo) {
        this.txnCodigo = txnCodigo;
    }

}
