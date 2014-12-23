/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;
import java.util.Collection;

/**
 *
 * @author miguel.duran
 */
public class TdTramiteTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tdnCodigo;
    private TrTramitesVO trnCodigo;
    private RiRecursosTdtVO rinCodigo;
    private AcAccionVO acnCodigo;
    private int tdnRadicado;
    private EmOperadorVO emrCodigo;
    private RrTipoRedTdtVO rrnCodigo;
    private CaCanalTdtVO canCodigo;
    private String tdtNombreRecurso;
    private String tdtObservaciones;
    private char tdtReservaTemporal;
    private int tdnMesesLiberacion;
    private Collection<TxTramiteTdtMultiplexVO> txTramiteTdtMultiplexCollection;
    private Collection<TvTramiteTdtServiciosVO> tvTramiteTdtServiciosCollection;
    private Collection<TgTramiteTdtRegionesVO> tgTramiteTdtRegionesCollection;
    
    public TdTramiteTdtVO() {
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public CaCanalTdtVO getCanCodigo() {
        return canCodigo;
    }

    public void setCanCodigo(CaCanalTdtVO canCodigo) {
        this.canCodigo = canCodigo;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }

    public RiRecursosTdtVO getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(RiRecursosTdtVO rinCodigo) {
        this.rinCodigo = rinCodigo;
    }

    public RrTipoRedTdtVO getRrnCodigo() {
        return rrnCodigo;
    }

    public void setRrnCodigo(RrTipoRedTdtVO rrnCodigo) {
        this.rrnCodigo = rrnCodigo;
    }

    public int getTdnCodigo() {
        return tdnCodigo;
    }

    public void setTdnCodigo(int tdnCodigo) {
        this.tdnCodigo = tdnCodigo;
    }

    public int getTdnMesesLiberacion() {
        return tdnMesesLiberacion;
    }

    public void setTdnMesesLiberacion(int tdnMesesLiberacion) {
        this.tdnMesesLiberacion = tdnMesesLiberacion;
    }

    public int getTdnRadicado() {
        return tdnRadicado;
    }

    public void setTdnRadicado(int tdnRadicado) {
        this.tdnRadicado = tdnRadicado;
    }

    public String getTdtNombreRecurso() {
        return tdtNombreRecurso;
    }

    public void setTdtNombreRecurso(String tdtNombreRecurso) {
        this.tdtNombreRecurso = tdtNombreRecurso;
    }

    public String getTdtObservaciones() {
        return tdtObservaciones;
    }

    public void setTdtObservaciones(String tdtObservaciones) {
        this.tdtObservaciones = tdtObservaciones;
    }

    public char getTdtReservaTemporal() {
        return tdtReservaTemporal;
    }

    public void setTdtReservaTemporal(char tdtReservaTemporal) {
        this.tdtReservaTemporal = tdtReservaTemporal;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public Collection<TxTramiteTdtMultiplexVO> getTxTramiteTdtMultiplexCollection() {
        return txTramiteTdtMultiplexCollection;
    }

    public void setTxTramiteTdtMultiplexCollection(Collection<TxTramiteTdtMultiplexVO> txTramiteTdtMultiplexCollection) {
        this.txTramiteTdtMultiplexCollection = txTramiteTdtMultiplexCollection;
    }

    public Collection<TvTramiteTdtServiciosVO> getTvTramiteTdtServiciosCollection() {
        return tvTramiteTdtServiciosCollection;
    }

    public void setTvTramiteTdtServiciosCollection(Collection<TvTramiteTdtServiciosVO> tvTramiteTdtServiciosCollection) {
        this.tvTramiteTdtServiciosCollection = tvTramiteTdtServiciosCollection;
    }

    public Collection<TgTramiteTdtRegionesVO> getTgTramiteTdtRegionesCollection() {
        return tgTramiteTdtRegionesCollection;
    }

    public void setTgTramiteTdtRegionesCollection(Collection<TgTramiteTdtRegionesVO> tgTramiteTdtRegionesCollection) {
        this.tgTramiteTdtRegionesCollection = tgTramiteTdtRegionesCollection;
    }
    
}
