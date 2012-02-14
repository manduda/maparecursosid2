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
public class MaMarcacionAbreviadaVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int manCodigo;
    private EmOperadorVO emrCodigo;
    private int manCodigoMarcacion;
    private EsEstadoVO esnCodigo;
    private String matObservaciones;
    
    public MaMarcacionAbreviadaVO() {
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

    public int getManCodigo() {
        return manCodigo;
    }

    public void setManCodigo(int manCodigo) {
        this.manCodigo = manCodigo;
    }

    public int getManCodigoMarcacion() {
        return manCodigoMarcacion;
    }

    public void setManCodigoMarcacion(int manCodigoMarcacion) {
        this.manCodigoMarcacion = manCodigoMarcacion;
    }

    public String getMatObservaciones() {
        return matObservaciones;
    }

    public void setMatObservaciones(String matObservaciones) {
        this.matObservaciones = matObservaciones;
    }
    
}
