/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import helper.ConvertirListasHelper;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;

/**
 *
 * @author juan.loaiza
 */
@ManagedBean(name = "CodigosLdBean")
@ViewScoped
public class CodigosLdBean implements Serializable {
    private List<ClCodigosLdVO> CoLD = new ArrayList<ClCodigosLdVO>();
    private int countCoLD;
    //private SelectItem[] listaEstado;
    private ClCodigosLdVO selectedLd;
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String codigoLd;
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;

    public CodigosLdBean(){
        facade fachada = new facade();

        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorCodigosLd(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de CodigosLd", e);
        }

        /*List<EsEstadoVO> estados = fachada.listaEstado();
        SelectItem[] options = new SelectItem[estados.size() + 1];
        listaEstado = new SelectItem[estados.size() + 1];
        listaEstado[0] = new SelectItem("", "");
        Integer i = 1;
        for (EsEstadoVO d : estados) {
            listaEstado[i] = new SelectItem(d.getEstNombre(), d.getEstNombre());
            i++;
        }*/
        //listaEstado = options;

        
        CoLD = fachada.cargarCodigosLd(0, -1, "-1", -1, -1);
        countCoLD = fachada.countCargarCodigosLd("-1", -1, -1);
    }
    
    public String buscar() {
        List<ClCodigosLdVO> codigosld = new ArrayList<ClCodigosLdVO>();
        facade fachada = new facade();

        final Integer ld;
        
        if (codigoLd.equals("")) {
            ld = -1;
        } else {
            ld = Integer.parseInt(codigoLd);
        }
        
        CoLD = fachada.cargarCodigosLd(0, -1, operadorVO.getEmrCodigo(), ld, estadoVO.getEsnCodigo());
        countCoLD = fachada.countCargarCodigosLd(operadorVO.getEmrCodigo(), ld, estadoVO.getEsnCodigo());
        
        return null;
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

    public Collection<SelectItem> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(Collection<SelectItem> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public Collection<SelectItem> getListaOperador() {
        return listaOperador;
    }

    public void setListaOperador(Collection<SelectItem> listaOperador) {
        this.listaOperador = listaOperador;
    }

    public ClCodigosLdVO getSelectedLd() {
        return selectedLd;
    }

    public void setSelectedLd(ClCodigosLdVO selectedLd) {
        this.selectedLd = selectedLd;
    }

    public String getCodigoLd() {
        return codigoLd;
    }

    public void setCodigoLd(String codigoLd) {
        this.codigoLd = codigoLd;
    }

    public EsEstadoVO getEstadoVO() {
        return estadoVO;
    }

    public void setEstadoVO(EsEstadoVO estadoVO) {
        this.estadoVO = estadoVO;
    }

    public EmOperadorVO getOperadorVO() {
        return operadorVO;
    }

    public void setOperadorVO(EmOperadorVO operadorVO) {
        this.operadorVO = operadorVO;
    }

    public int getCountCoLD() {
        return countCoLD;
    }

    public void setCountCoLD(int countCoLD) {
        this.countCoLD = countCoLD;
    }

}
