/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

import java.util.Collection;

/**
 *
 * @author miguel.duran
 */
public class TeTipoSenalizacionVO {
    private int tenCodigo;
    private String tetNombre;
    private Collection<SeSenalizacionVO> seSenalizacionCollection;
    
    public TeTipoSenalizacionVO() {
    }

    public Collection<SeSenalizacionVO> getSeSenalizacionCollection() {
        return seSenalizacionCollection;
    }

    public void setSeSenalizacionCollection(Collection<SeSenalizacionVO> seSenalizacionCollection) {
        this.seSenalizacionCollection = seSenalizacionCollection;
    }

    public int getTenCodigo() {
        return tenCodigo;
    }

    public void setTenCodigo(int tenCodigo) {
        this.tenCodigo = tenCodigo;
    }

    public String getTetNombre() {
        return tetNombre;
    }

    public void setTetNombre(String tetNombre) {
        this.tetNombre = tetNombre;
    }
    
}
