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
public class RiRecursosTdtVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int rinCodigo;
    private EmOperadorVO emrCodigo;
    private EsEstadoVO esnCodigo;
    private int rinCodigoRecurso;
    private String ritNombreRecurso;
    private RnTipoRecursoTdtVO rnnCodigo;
    private RrTipoRedTdtVO rrnCodigo;
    private CaCanalTdtVO canCodigo;
    private String ritObservaciones;
    private Collection<RaRegionesTdtVO> raRegionesTdtCollection;
    private StServiciosTdtVO servicioCodigo;
    private MrMultiplexRecursoVO multiplexCodigo;
    
    public RiRecursosTdtVO() {
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

    public EsEstadoVO getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstadoVO esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public int getRinCodigo() {
        return rinCodigo;
    }

    public void setRinCodigo(int rinCodigo) {
        this.rinCodigo = rinCodigo;
    }

    public int getRinCodigoRecurso() {
        return rinCodigoRecurso;
    }

    public void setRinCodigoRecurso(int rinCodigoRecurso) {
        this.rinCodigoRecurso = rinCodigoRecurso;
    }

    public String getRitNombreRecurso() {
        return ritNombreRecurso;
    }

    public void setRitNombreRecurso(String ritNombreRecurso) {
        this.ritNombreRecurso = ritNombreRecurso;
    }

    public String getRitObservaciones() {
        return ritObservaciones;
    }

    public void setRitObservaciones(String ritObservaciones) {
        this.ritObservaciones = ritObservaciones;
    }

    public RnTipoRecursoTdtVO getRnnCodigo() {
        return rnnCodigo;
    }

    public void setRnnCodigo(RnTipoRecursoTdtVO rnnCodigo) {
        this.rnnCodigo = rnnCodigo;
    }

    public RrTipoRedTdtVO getRrnCodigo() {
        return rrnCodigo;
    }

    public void setRrnCodigo(RrTipoRedTdtVO rrnCodigo) {
        this.rrnCodigo = rrnCodigo;
    }

    public Collection<RaRegionesTdtVO> getRaRegionesTdtCollection() {
        return raRegionesTdtCollection;
    }

    public void setRaRegionesTdtCollection(Collection<RaRegionesTdtVO> raRegionesTdtCollection) {
        this.raRegionesTdtCollection = raRegionesTdtCollection;
    }

    public MrMultiplexRecursoVO getMultiplexCodigo() {
        return multiplexCodigo;
    }

    public void setMultiplexCodigo(MrMultiplexRecursoVO multiplexCodigo) {
        this.multiplexCodigo = multiplexCodigo;
    }

    public StServiciosTdtVO getServicioCodigo() {
        return servicioCodigo;
    }

    public void setServicioCodigo(StServiciosTdtVO servicioCodigo) {
        this.servicioCodigo = servicioCodigo;
    }

}
