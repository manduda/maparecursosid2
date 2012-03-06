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
public class Nc1xyVO implements Serializable {
    private static final long serialVersionUID = 1L;
   
    private int ncnCodigo;
    private MoModalidad1xyVO monCodigo;
    private int ncn1xy;
    private EsEstadoVO esnCodigo;
    private String nctServicio;
    private String nctObservaciones;
    
    public Nc1xyVO(){
        
    }

    public EsEstadoVO getEsnCodigo() {
        return esnCodigo;
    }

    public void setEsnCodigo(EsEstadoVO esnCodigo) {
        this.esnCodigo = esnCodigo;
    }

    public MoModalidad1xyVO getMonCodigo() {
        return monCodigo;
    }

    public void setMonCodigo(MoModalidad1xyVO monCodigo) {
        this.monCodigo = monCodigo;
    }

    public int getNcn1xy() {
        return ncn1xy;
    }

    public void setNcn1xy(int ncn1xy) {
        this.ncn1xy = ncn1xy;
    }

    public int getNcnCodigo() {
        return ncnCodigo;
    }

    public void setNcnCodigo(int ncnCodigo) {
        this.ncnCodigo = ncnCodigo;
    }

    public String getNctServicio() {
        return nctServicio;
    }

    public void setNctServicio(String nctServicio) {
        this.nctServicio = nctServicio;
    }

    public String getNctObservaciones() {
        return nctObservaciones;
    }

    public void setNctObservaciones(String nctObservaciones) {
        this.nctObservaciones = nctObservaciones;
    }

}
