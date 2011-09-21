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
public class RtTipoRegionVO implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private int rtnCodigo;
    private String rttNombre;
    private Collection<ReRegionVO> reRegionCollection;
        
    public RtTipoRegionVO() {
    }

    public Collection<ReRegionVO> getReRegionCollection() {
        return reRegionCollection;
    }

    public void setReRegionCollection(Collection<ReRegionVO> reRegionCollection) {
        this.reRegionCollection = reRegionCollection;
    }

    public int getRtnCodigo() {
        return rtnCodigo;
    }

    public void setRtnCodigo(int rtnCodigo) {
        this.rtnCodigo = rtnCodigo;
    }

    public String getRttNombre() {
        return rttNombre;
    }

    public void setRttNombre(String rttNombre) {
        this.rttNombre = rttNombre;
    }

}
