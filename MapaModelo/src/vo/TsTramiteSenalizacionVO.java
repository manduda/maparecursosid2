/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.io.Serializable;

/**
 *
 * @author MADD
 */
public class TsTramiteSenalizacionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int tsnCodigo;
    private TrTramitesVO trnCodigo;
    private SeSenalizacionVO senCodigo;
    private AcAccionVO acnCodigo;
    private int tsnRadicado;
    private MunicipiosVO codigoMunicipio;
    private EmOperadorVO emrCodigo;
    private String tstNombreNodo;
    private String tstMarcaModelo;
    private String tstDireccion;
    private String tstObservaciones;
    
    public TsTramiteSenalizacionVO(){
    }

    public AcAccionVO getAcnCodigo() {
        return acnCodigo;
    }

    public void setAcnCodigo(AcAccionVO acnCodigo) {
        this.acnCodigo = acnCodigo;
    }

    public SeSenalizacionVO getSenCodigo() {
        return senCodigo;
    }

    public void setSenCodigo(SeSenalizacionVO senCodigo) {
        this.senCodigo = senCodigo;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

    public int getTsnCodigo() {
        return tsnCodigo;
    }

    public void setTsnCodigo(int tsnCodigo) {
        this.tsnCodigo = tsnCodigo;
    }

    public int getTsnRadicado() {
        return tsnRadicado;
    }

    public void setTsnRadicado(int tsnRadicado) {
        this.tsnRadicado = tsnRadicado;
    }

    public String getTstDireccion() {
        return tstDireccion;
    }

    public void setTstDireccion(String tstDireccion) {
        this.tstDireccion = tstDireccion;
    }

    public String getTstMarcaModelo() {
        return tstMarcaModelo;
    }

    public void setTstMarcaModelo(String tstMarcaModelo) {
        this.tstMarcaModelo = tstMarcaModelo;
    }

    public String getTstNombreNodo() {
        return tstNombreNodo;
    }

    public void setTstNombreNodo(String tstNombreNodo) {
        this.tstNombreNodo = tstNombreNodo;
    }

    public String getTstObservaciones() {
        return tstObservaciones;
    }

    public void setTstObservaciones(String tstObservaciones) {
        this.tstObservaciones = tstObservaciones;
    }

    public MunicipiosVO getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(MunicipiosVO codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public EmOperadorVO getEmrCodigo() {
        return emrCodigo;
    }

    public void setEmrCodigo(EmOperadorVO emrCodigo) {
        this.emrCodigo = emrCodigo;
    }
    
}
