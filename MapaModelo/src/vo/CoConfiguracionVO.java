/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author miguel.duran
 */
public class CoConfiguracionVO {
    private static final long serialVersionUID = 1L;
    
    private int conCodigo;
    private String cotDescripcion;
    private String cotValor;
    
    public CoConfiguracionVO() {
    }

    public int getConCodigo() {
        return conCodigo;
    }

    public void setConCodigo(int conCodigo) {
        this.conCodigo = conCodigo;
    }

    public String getCotDescripcion() {
        return cotDescripcion;
    }

    public void setCotDescripcion(String cotDescripcion) {
        this.cotDescripcion = cotDescripcion;
    }

    public String getCotValor() {
        return cotValor;
    }

    public void setCotValor(String cotValor) {
        this.cotValor = cotValor;
    }
}
