/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Date;

/**
 *
 * @author juan.loaiza
 */
public class RsReservasTemporalesVO {
    
    private int rsnCodigo;
    private int rsnCodigoRecurso;
    private String rstTipoRecurso;
    private TrTramitesVO trnCodigo;
    private int rsnEstado;
    private Date rsfFechaLiberacion;
        
    public RsReservasTemporalesVO() {
    }

    public Date getRsfFechaLiberacion() {
        return rsfFechaLiberacion;
    }

    public void setRsfFechaLiberacion(Date rsfFechaLiberacion) {
        this.rsfFechaLiberacion = rsfFechaLiberacion;
    }

    public int getRsnCodigo() {
        return rsnCodigo;
    }

    public void setRsnCodigo(int rsnCodigo) {
        this.rsnCodigo = rsnCodigo;
    }

    public int getRsnCodigoRecurso() {
        return rsnCodigoRecurso;
    }

    public void setRsnCodigoRecurso(int rsnCodigoRecurso) {
        this.rsnCodigoRecurso = rsnCodigoRecurso;
    }

    public int getRsnEstado() {
        return rsnEstado;
    }

    public void setRsnEstado(int rsnEstado) {
        this.rsnEstado = rsnEstado;
    }

    public String getRstTipoRecurso() {
        return rstTipoRecurso;
    }

    public void setRstTipoRecurso(String rstTipoRecurso) {
        this.rstTipoRecurso = rstTipoRecurso;
    }

    public TrTramitesVO getTrnCodigo() {
        return trnCodigo;
    }

    public void setTrnCodigo(TrTramitesVO trnCodigo) {
        this.trnCodigo = trnCodigo;
    }

}
