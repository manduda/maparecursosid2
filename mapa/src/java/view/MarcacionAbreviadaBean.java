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
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.MaMarcacionAbreviadaVO;
import vo.TaTramiteMaVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "MarcacionAbreviadaBean")
@ViewScoped
public class MarcacionAbreviadaBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String codigoMarcacionAbreviada = "";
    private LazyDataModel<MaMarcacionAbreviadaVO> lazyModel;
    private MaMarcacionAbreviadaVO selected;
    private MaMarcacionAbreviadaVO[] selecteds;
    private Boolean selectedsPreasignar;
    private Boolean selectedsRecuperar;
    private Boolean selectedsReservar;
    private Boolean selectedsLiberar;
    private Boolean selectedsAccion;
    private MaMarcacionAbreviadaVO[] selectedsDetalle;
    private List<TaTramiteMaVO> tramiteMarcacionAbreviada = null;
    

    /** Creates a new instance of CodigosCortosBean */
    public MarcacionAbreviadaBean() {
        facade fachada = new facade();
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorMarcacionAbreviada(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Marcación Abreviada", e);
        }
        
        lazyModel = new LazyDataModel<MaMarcacionAbreviadaVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<MaMarcacionAbreviadaVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<MaMarcacionAbreviadaVO> lazyMarcacionAbreviada = new ArrayList<MaMarcacionAbreviadaVO>();
                facade fachada = new facade();
                lazyMarcacionAbreviada = fachada.cargarMarcacionAbreviada(first, pageSize, "-1", -1, -1); 
  
                return lazyMarcacionAbreviada;
            }  
        };
        lazyModel.setRowCount(fachada.countCargarMarcacionAbreviada("-1", -1, -1));
        
        operadorVO.setEmrCodigo("-1");
        codigoMarcacionAbreviada = "";
        estadoVO.setEsnCodigo(-1);
    }
    
    public String buscar() {
        List<MaMarcacionAbreviadaVO> marcacionAbreviada = new ArrayList<MaMarcacionAbreviadaVO>();
        facade fachada = new facade();

        final Integer codigo;
        
        if (codigoMarcacionAbreviada.equals("")) {
            codigo = -1;
        } else {
            codigo = Integer.parseInt(codigoMarcacionAbreviada);
        }
        //System.out.println(operadorVO.getEmrCodigo() +"*"+ regionSenalizacionVO.getRenCodigo() +"*"+ zona +"*"+ ps +"*"+ estadoVO.getEsnCodigo() +"*"+ municipioVO.getCodigoMunicipio() +"*"+ departamentoVO.getCodigoDepartamento()+"*");
        //System.out.println("Esrtado:"+estadoVO.getEsnCodigo());

        lazyModel = new LazyDataModel<MaMarcacionAbreviadaVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<MaMarcacionAbreviadaVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<MaMarcacionAbreviadaVO> lazyMarcacionAbreviada = new ArrayList<MaMarcacionAbreviadaVO>();
                facade fachada = new facade();
                lazyMarcacionAbreviada = fachada.cargarMarcacionAbreviada(first, pageSize, operadorVO.getEmrCodigo(), codigo, estadoVO.getEsnCodigo()); 
                
                return lazyMarcacionAbreviada;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarMarcacionAbreviada(operadorVO.getEmrCodigo(), codigo, estadoVO.getEsnCodigo())); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public void detalle(){
        selecteds = new MaMarcacionAbreviadaVO[1];
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
            
            for (MaMarcacionAbreviadaVO n : selecteds) {
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
    
    public LazyDataModel<MaMarcacionAbreviadaVO> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<MaMarcacionAbreviadaVO> lazyModel) {
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

    public MaMarcacionAbreviadaVO getSelected() {
        return selected;
    }
    
    public void setSelected(MaMarcacionAbreviadaVO selected) {
        this.selected = selected;
        if (selected != null){
            facade fachada = new facade();
            this.tramiteMarcacionAbreviada = fachada.buscarTramitePorMarcacionAbreviada(selected.getManCodigo(), 5);
        }
    }

    public MaMarcacionAbreviadaVO[] getSelecteds() {
        return selecteds;
    }

    public void setSelecteds(MaMarcacionAbreviadaVO[] selecteds) {
        this.selecteds = selecteds;
    }

    public Boolean getSelectedsAccion() {
        return selectedsAccion;
    }

    public void setSelectedsAccion(Boolean selectedsAccion) {
        this.selectedsAccion = selectedsAccion;
    }

    public MaMarcacionAbreviadaVO[] getSelectedsDetalle() {
        return selectedsDetalle;
    }

    public void setSelectedsDetalle(MaMarcacionAbreviadaVO[] selectedsDetalle) {
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

    public String getCodigoMarcacionAbreviada() {
        return codigoMarcacionAbreviada;
    }

    public void setCodigoMarcacionAbreviada(String codigoMarcacionAbreviada) {
        this.codigoMarcacionAbreviada = codigoMarcacionAbreviada;
    }

    public List<TaTramiteMaVO> getTramiteMarcacionAbreviada() {
        return tramiteMarcacionAbreviada;
    }

    public void setTramiteMarcacionAbreviada(List<TaTramiteMaVO> tramiteMarcacionAbreviada) {
        this.tramiteMarcacionAbreviada = tramiteMarcacionAbreviada;
    }
    
}
