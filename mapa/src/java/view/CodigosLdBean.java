/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import vo.ClCodigosLdVO;

/**
 *
 * @author juan.loaiza
 */
@ManagedBean(name = "codigosLdBean")
@RequestScoped
public class CodigosLdBean {
    
                
    private List<ClCodigosLdVO> CoLD = new ArrayList<ClCodigosLdVO>();

    public CodigosLdBean(){
        facade fachada = new facade();

        CoLD = fachada.ListaCodigosLd();
    }

    public List<ClCodigosLdVO> getCoLD() {
        return CoLD;
    }

    public void setCoLD(List<ClCodigosLdVO> CoLD) {
        this.CoLD = CoLD;
    }
    
        /** Creates a new instance of CodigosLdBean */

}
