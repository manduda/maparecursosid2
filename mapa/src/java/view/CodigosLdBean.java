/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import helper.ConvertirListasHelper;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import vo.ClCodigosLdVO;
import vo.EsEstadoVO;

/**
 *
 * @author juan.loaiza
 */
@ManagedBean(name = "codigosLdBean")
@RequestScoped
public class CodigosLdBean {
    private List<ClCodigosLdVO> CoLD = new ArrayList<ClCodigosLdVO>();
    private SelectItem[] listaEstado;

    public CodigosLdBean(){
        facade fachada = new facade();


        List<EsEstadoVO> estados = fachada.listaEstado();
        SelectItem[] options = new SelectItem[estados.size() + 1];
        listaEstado = new SelectItem[estados.size() + 1];
        listaEstado[0] = new SelectItem("", "");
        Integer i = 1;
        for (EsEstadoVO d : estados) {
            listaEstado[i] = new SelectItem(d.getEstNombre(), d.getEstNombre());
            i++;
        }
        //listaEstado = options;

        
        CoLD = fachada.ListaCodigosLd();
    }
    
    private SelectItem[] createFilterOptions(String[] data)  {  
        SelectItem[] options = new SelectItem[data.length + 1];  
  
        options[0] = new SelectItem("", "");  
        for(int i = 0; i < data.length; i++) {  
            options[i + 1] = new SelectItem(data[i], data[i]);  
        }  
  
        return options;  
    }
    

    public List<ClCodigosLdVO> getCoLD() {
        return CoLD;
    }

    public void setCoLD(List<ClCodigosLdVO> CoLD) {
        this.CoLD = CoLD;
    }

    public SelectItem[] getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(SelectItem[] listaEstado) {
        this.listaEstado = listaEstado;
    }



}
