/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package vo;

/**
 *
 * @author miguel.duran
 */
public class SkRegionVO {
    private byte[] skRegionCode;
    private String skRegionNombre;

    public SkRegionVO() {
    }

    public byte[] getSkRegionCode() {
        return skRegionCode;
    }

    public void setSkRegionCode(byte[] skRegionCode) {
        this.skRegionCode = skRegionCode;
    }

    public String getSkRegionNombre() {
        return skRegionNombre;
    }

    public void setSkRegionNombre(String skRegionNombre) {
        this.skRegionNombre = skRegionNombre;
    }
    
}
