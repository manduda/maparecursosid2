/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author miguel.duran
 */
public class MunicipiosVO {
    private String codigoMunicipio;
    private String nombreMunicipio;
    private DepartamentosVO codigoDepartamento;

    public MunicipiosVO() {
    }

    public DepartamentosVO getCodigoDepartamento() {
        return codigoDepartamento;
    }

    public void setCodigoDepartamento(DepartamentosVO codigoDepartamento) {
        this.codigoDepartamento = codigoDepartamento;
    }

    public String getCodigoMunicipio() {
        return codigoMunicipio;
    }

    public void setCodigoMunicipio(String codigoMunicipio) {
        this.codigoMunicipio = codigoMunicipio;
    }

    public String getNombreMunicipio() {
        return nombreMunicipio;
    }

    public void setNombreMunicipio(String nombreMunicipio) {
        this.nombreMunicipio = nombreMunicipio;
    }

 
}
