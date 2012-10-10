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
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.TlTramiteLdVO;

/**
 *
 * @author juan.loaiza
 */
@ManagedBean(name = "CodigosLdBean")
@ViewScoped
public class CodigosLdBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<ClCodigosLdVO> CoLD = new ArrayList<ClCodigosLdVO>();
    private int countCoLD;
    //private SelectItem[] listaEstado;
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String codigoLd="";
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    
    private ClCodigosLdVO selectedLd;
    private ClCodigosLdVO[] selectedLds;
    private Boolean selectedLdsPreasignar;
    private Boolean selectedLdsRecuperar;
    private Boolean selectedLdsReservar;
    private Boolean selectedLdsLiberar;
    private Boolean selectedLdsAccion;
    private ClCodigosLdVO[] selectedCCsDetalle;
    private List<TlTramiteLdVO> tramiteLd = null;

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
        operadorVO.setEmrCodigo("-1");
        estadoVO.setEsnCodigo(-1);
        codigoLd = "";
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
    
    public void detalleLd(){
        selectedLds = new ClCodigosLdVO[1];
        selectedLds[0] = selectedLd;
    }
    
    public void onRowSelect() {
        if (selectedLds == null){
            selectedLdsAccion = false;
        } else if (selectedLds.length > 0) {
            selectedLdsAccion = true;
        } else {
            selectedLdsAccion = false;
        }
    }
    
    public void onRowUnselect() {
        if (selectedLds == null){
            selectedLdsAccion = false;
        } else if (selectedLds.length > 0) {
            selectedLdsAccion = true;
        } else {
            selectedLdsAccion = false;
        }
    }
    
    public void detalleAccionLd(){
        if (selectedLds != null){
            selectedLdsPreasignar = true;
            selectedLdsRecuperar = true;
            selectedLdsReservar = true;
            selectedLdsLiberar = true;
            String operador = selectedLds[0].getEmrCodigo().getEmrCodigo();
            
            for (ClCodigosLdVO n : selectedLds) {
                //selectedNumsCantidad = selectedNumsCantidad + (n.getNunFin()-n.getNunInicio()+1);
                
                //--- Activar / desactivar botón Pre-Asignar
                if (selectedLdsPreasignar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 1) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedLdsPreasignar = true;
                    } else {
                        selectedLdsPreasignar = false;
                    }
                }
                //--- Activar / desactivar botón Recuperar
                if (selectedLdsRecuperar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 3) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedLdsRecuperar = true;
                    } else {
                        selectedLdsRecuperar = false;
                    }
                }
                //--- Activar / desactivar botón Reservar
                if (selectedLdsReservar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 1) {
                        selectedLdsReservar = true;
                    } else {
                        selectedLdsReservar = false;
                    }
                }
                //--- Activar / desactivar botón Liberar
                if (selectedLdsLiberar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 4) {
                        selectedLdsLiberar = true;
                    } else {
                        selectedLdsLiberar = false;
                    }
                }
                
            }
        } else {
            //selectedNumsCantidad = 0;
            selectedLdsPreasignar = false;
            selectedLdsRecuperar = false;
            selectedLdsReservar = false;
            selectedLdsLiberar = false;
        }
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
        if (selectedLd != null){
            facade fachada = new facade();
            this.tramiteLd = fachada.buscarTramitePorCodigoLd(selectedLd.getClnCodigo(), 5);
        }
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

    public ClCodigosLdVO[] getSelectedCCsDetalle() {
        return selectedCCsDetalle;
    }

    public void setSelectedCCsDetalle(ClCodigosLdVO[] selectedCCsDetalle) {
        this.selectedCCsDetalle = selectedCCsDetalle;
    }

    public ClCodigosLdVO[] getSelectedLds() {
        return selectedLds;
    }

    public void setSelectedLds(ClCodigosLdVO[] selectedLds) {
        this.selectedLds = selectedLds;
    }

    public Boolean getSelectedLdsAccion() {
        return selectedLdsAccion;
    }

    public void setSelectedLdsAccion(Boolean selectedLdsAccion) {
        this.selectedLdsAccion = selectedLdsAccion;
    }

    public Boolean getSelectedLdsLiberar() {
        return selectedLdsLiberar;
    }

    public void setSelectedLdsLiberar(Boolean selectedLdsLiberar) {
        this.selectedLdsLiberar = selectedLdsLiberar;
    }

    public Boolean getSelectedLdsPreasignar() {
        return selectedLdsPreasignar;
    }

    public void setSelectedLdsPreasignar(Boolean selectedLdsPreasignar) {
        this.selectedLdsPreasignar = selectedLdsPreasignar;
    }

    public Boolean getSelectedLdsRecuperar() {
        return selectedLdsRecuperar;
    }

    public void setSelectedLdsRecuperar(Boolean selectedLdsRecuperar) {
        this.selectedLdsRecuperar = selectedLdsRecuperar;
    }

    public Boolean getSelectedLdsReservar() {
        return selectedLdsReservar;
    }

    public void setSelectedLdsReservar(Boolean selectedLdsReservar) {
        this.selectedLdsReservar = selectedLdsReservar;
    }

    public List<TlTramiteLdVO> getTramiteLd() {
        return tramiteLd;
    }

    public void setTramiteLd(List<TlTramiteLdVO> tramiteLd) {
        this.tramiteLd = tramiteLd;
    }
    
}
