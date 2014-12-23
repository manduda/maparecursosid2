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
import vo.TxTramiteTdtMultiplexVO;

/**
 *
 * @author miguel.duran
 */
@Entity
@Table(name = "TX_TRAMITE_TDT_MULTIPLEX")
public class TxTramiteTdtMultiplex implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @Basic(optional = false)
    @Column(name = "TXN_CODIGO")
    private int txnCodigo;
    
    @JoinColumn(name = "TDN_CODIGO", referencedColumnName = "TDN_CODIGO")
    @ManyToOne(optional = false)
    private TdTramiteTdt tdnCodigo;
    
    @JoinColumn(name = "MUN_CODIGO", referencedColumnName = "MUN_CODIGO")
    @ManyToOne(optional = false)
    private MuMultiplex munCodigo;
    
    public TxTramiteTdtMultiplex() {
    }
    
    public TxTramiteTdtMultiplexVO toVO(){
        TxTramiteTdtMultiplexVO vo = new TxTramiteTdtMultiplexVO();
        vo.setTxnCodigo(this.getTxnCodigo());
        vo.setTdnCodigo(this.getTdnCodigo().toVOsinDetalles());
        vo.setMunCodigo(this.getMunCodigo().toVO());
        return vo;
    }

    public MuMultiplex getMunCodigo() {
        return munCodigo;
    }

    public void setMunCodigo(MuMultiplex munCodigo) {
        this.munCodigo = munCodigo;
    }

    public TdTramiteTdt getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(TdTramiteTdt tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public int getTxnCodigo() {
        return txnCodigo;
    }

    public void setTxnCodigo(int txnCodigo) {
        this.txnCodigo = txnCodigo;
    }

}
