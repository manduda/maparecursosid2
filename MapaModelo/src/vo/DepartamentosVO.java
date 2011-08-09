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
public class DepartamentosVO {
    private String codigoDepartamento;
    private String nombreDepartamento;
    private Collection<MunicipiosVO> municipiosCollection;
    
    public DepartamentosVO() {
    }

    public String getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(String codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public Collection<MunicipiosVO> getMunicipiosCollection() {
        return municipiosCollection;
    }

    public void setMunicipiosCollection(Collection<MunicipiosVO> municipiosCollection) {
        this.municipiosCollection = municipiosCollection;
    }

    public String getNombreDepartamento() {
        return nombreDepartamento;
    }

    public void setNombreDepartamento(String nombreDepartamento) {
        this.nombreDepartamento = nombreDepartamento;
    }


}
