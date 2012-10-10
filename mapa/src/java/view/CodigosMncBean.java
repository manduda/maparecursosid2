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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vo.CdCodigosMncVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.TmTramiteMncVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "CodigosMncBean")
@ViewScoped
public class CodigosMncBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String codigoMnc = "";
    private LazyDataModel<CdCodigosMncVO> lazyModel;
    private CdCodigosMncVO selected;
    private CdCodigosMncVO[] selecteds;
    private Boolean selectedsPreasignar;
    private Boolean selectedsRecuperar;
    private Boolean selectedsReservar;
    private Boolean selectedsLiberar;
    private Boolean selectedsAccion;
    private CdCodigosMncVO[] selectedsDetalle;
    private List<TmTramiteMncVO> tramiteMnc = null;
    

    /** Creates a new instance of CodigosCortosBean */
    public CodigosMncBean() {
        facade fachada = new facade();
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorCodigosMnc(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Codigos Mnc", e);
        }
        
        lazyModel = new LazyDataModel<CdCodigosMncVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<CdCodigosMncVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<CdCodigosMncVO> lazyCodigosMnc = new ArrayList<CdCodigosMncVO>();
                facade fachada = new facade();
                lazyCodigosMnc = fachada.cargarCodigosMnc(first, pageSize, "-1", -1, -1); 
  
                return lazyCodigosMnc;
            }  
        };
        lazyModel.setRowCount(fachada.countCargarCodigosMnc("-1", -1, -1));
        
        operadorVO.setEmrCodigo("-1");
        codigoMnc = "";
        estadoVO.setEsnCodigo(-1);
    }
    
    public String buscar() {
        facade fachada = new facade();

        final Integer codigo;
        
        if (codigoMnc.equals("")) {
            codigo = -1;
        } else {
            codigo = Integer.parseInt(codigoMnc);
        }

        lazyModel = new LazyDataModel<CdCodigosMncVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<CdCodigosMncVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<CdCodigosMncVO> lazyCodigosMnc = new ArrayList<CdCodigosMncVO>();
                facade fachada = new facade();
                lazyCodigosMnc = fachada.cargarCodigosMnc(first, pageSize, operadorVO.getEmrCodigo(), codigo, estadoVO.getEsnCodigo()); 
                
                return lazyCodigosMnc;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarCodigosMnc(operadorVO.getEmrCodigo(), codigo, estadoVO.getEsnCodigo())); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public void detalle(){
        selecteds = new CdCodigosMncVO[1];
        selecteds[0] = selected;
    }
    
    public void onRowSelect() {
        if (selecteds == null){
            selectedsAccion = false;
        } else if (selecteds.length > 0) {
            selectedsAccion = true;
        } else {
            selectedsAccion = false;
        }
    }
    
    public void onRowUnselect() {
        if (selecteds == null){
            selectedsAccion = false;
        } else if (selecteds.length > 0) {
            selectedsAccion = true;
        } else {
            selectedsAccion = false;
        }
    }
    
    public void detalleAccion(){
        if (selecteds != null){
            selectedsPreasignar = true;
            selectedsRecuperar = true;
            selectedsReservar = true;
            selectedsLiberar = true;
            String operador = selecteds[0].getEmrCodigo().getEmrCodigo();
            
            for (CdCodigosMncVO n : selecteds) {
                //selectedNumsCantidad = selectedNumsCantidad + (n.getNunFin()-n.getNunInicio()+1);
                
                //--- Activar / desactivar botón Pre-Asignar
                if (selectedsPreasignar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 1) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedsPreasignar = true;
                    } else {
                        selectedsPreasignar = false;
                    }
                }
                //--- Activar / desactivar botón Recuperar
                if (selectedsRecuperar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 3) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedsRecuperar = true;
                    } else {
                        selectedsRecuperar = false;
                    }
                }
                //--- Activar / desactivar botón Reservar
                if (selectedsReservar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 1) {
                        selectedsReservar = true;
                    } else {
                        selectedsReservar = false;
                    }
                }
                //--- Activar / desactivar botón Liberar
                if (selectedsLiberar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 4) {
                        selectedsLiberar = true;
                    } else {
                        selectedsLiberar = false;
                    }
                }
                
            }
        } else {
            //selectedNumsCantidad = 0;
            selectedsPreasignar = false;
            selectedsRecuperar = false;
            selectedsReservar = false;
            selectedsLiberar = false;
        }
    }
    
    public LazyDataModel<CdCodigosMncVO> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<CdCodigosMncVO> lazyModel) {
        this.lazyModel = lazyModel;
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

    public CdCodigosMncVO getSelected() {
        return selected;
    }
    
    public void setSelected(CdCodigosMncVO selected) {
        this.selected = selected;
        if (selected != null){
            facade fachada = new facade();
            this.tramiteMnc = fachada.buscarTramitePorCodigosMnc(selected.getCdnCodigo(), 5);
        }
    }

    public CdCodigosMncVO[] getSelecteds() {
        return selecteds;
    }

    public void setSelecteds(CdCodigosMncVO[] selecteds) {
        this.selecteds = selecteds;
    }

    public Boolean getSelectedsAccion() {
        return selectedsAccion;
    }

    public void setSelectedsAccion(Boolean selectedsAccion) {
        this.selectedsAccion = selectedsAccion;
    }

    public CdCodigosMncVO[] getSelectedsDetalle() {
        return selectedsDetalle;
    }

    public void setSelectedsDetalle(CdCodigosMncVO[] selectedsDetalle) {
        this.selectedsDetalle = selectedsDetalle;
    }

    public Boolean getSelectedsLiberar() {
        return selectedsLiberar;
    }

    public void setSelectedsLiberar(Boolean selectedsLiberar) {
        this.selectedsLiberar = selectedsLiberar;
    }

    public Boolean getSelectedsRecuperar() {
        return selectedsRecuperar;
    }

    public void setSelectedsRecuperar(Boolean selectedsRecuperar) {
        this.selectedsRecuperar = selectedsRecuperar;
    }

    public Boolean getSelectedsReservar() {
        return selectedsReservar;
    }

    public void setSelectedsReservar(Boolean selectedsReservar) {
        this.selectedsReservar = selectedsReservar;
    }

    public Boolean getSelectedsPreasignar() {
        return selectedsPreasignar;
    }

    public void setSelectedsPreasignar(Boolean selectedsPreasignar) {
        this.selectedsPreasignar = selectedsPreasignar;
    }

    public String getCodigoMnc() {
        return codigoMnc;
    }

    public void setCodigoMnc(String codigoMnc) {
        this.codigoMnc = codigoMnc;
    }

    public List<TmTramiteMncVO> getTramiteMnc() {
        return tramiteMnc;
    }

    public void setTramiteMnc(List<TmTramiteMncVO> tramiteMnc) {
        this.tramiteMnc = tramiteMnc;
    }
    
}
