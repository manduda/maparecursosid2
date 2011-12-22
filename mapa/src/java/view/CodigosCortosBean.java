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
import vo.CcCodigosCortosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.MdModalidadCcVO;
import vo.TcTramiteCcVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "CodigosCortosBean")
@ViewScoped
public class CodigosCortosBean implements Serializable {
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private Collection<SelectItem> listaModalidad;
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private MdModalidadCcVO modalidadVO = new MdModalidadCcVO();
    private String codigoCorto;
    private LazyDataModel<CcCodigosCortosVO> lazyModel;
    private CcCodigosCortosVO selectedCC;
    private CcCodigosCortosVO[] selectedCCs;
    private Boolean selectedCCsPreasignar;
    private Boolean selectedCCsRecuperar;
    private Boolean selectedCCsReservar;
    private Boolean selectedCCsLiberar;
    private Boolean selectedCCsAccion;
    private CcCodigosCortosVO[] selectedCCsDetalle;
    private List<TcTramiteCcVO> tramiteCodigoCorto = null;
    

    /** Creates a new instance of CodigosCortosBean */
    public CodigosCortosBean() {
        facade fachada = new facade();
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorCodigosCortos(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
            listaModalidad = convertir.createSelectItemsList(fachada.listaModalidadCc(), null, "getMdnCodigo", "getMdtNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Codigos Cortos", e);
        }
        
        lazyModel = new LazyDataModel<CcCodigosCortosVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<CcCodigosCortosVO> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<CcCodigosCortosVO> lazyCodigosCortos = new ArrayList<CcCodigosCortosVO>();
                facade fachada = new facade();
                lazyCodigosCortos = fachada.cargarCodigosCortos(first, pageSize, "-1", -1, -1, -1); 
  
                return lazyCodigosCortos;
            }  
        };
        lazyModel.setRowCount(fachada.countCargarCodigosCortos("-1", -1, -1, -1));
        
        operadorVO.setEmrCodigo("-1");
        modalidadVO.setMdnCodigo(-1);
        codigoCorto = "";
        estadoVO.setEsnCodigo(-1);
    }
    
    public String buscar() {
        List<CcCodigosCortosVO> codigosCortos = new ArrayList<CcCodigosCortosVO>();
        facade fachada = new facade();

        final Integer codigo;
        
        if (codigoCorto.equals("")) {
            codigo = -1;
        } else {
            codigo = Integer.parseInt(codigoCorto);
        }
        //System.out.println(operadorVO.getEmrCodigo() +"*"+ regionSenalizacionVO.getRenCodigo() +"*"+ zona +"*"+ ps +"*"+ estadoVO.getEsnCodigo() +"*"+ municipioVO.getCodigoMunicipio() +"*"+ departamentoVO.getCodigoDepartamento()+"*");
        //System.out.println("Esrtado:"+estadoVO.getEsnCodigo());

        lazyModel = new LazyDataModel<CcCodigosCortosVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<CcCodigosCortosVO> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<CcCodigosCortosVO> lazyCodigosCortos = new ArrayList<CcCodigosCortosVO>();
                facade fachada = new facade();
                lazyCodigosCortos = fachada.cargarCodigosCortos(first, pageSize, operadorVO.getEmrCodigo(), modalidadVO.getMdnCodigo(), codigo, estadoVO.getEsnCodigo()); 
                
                return lazyCodigosCortos;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarCodigosCortos(operadorVO.getEmrCodigo(), modalidadVO.getMdnCodigo(), codigo, estadoVO.getEsnCodigo())); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public void detalleCC(){
        selectedCCs = new CcCodigosCortosVO[1];
        selectedCCs[0] = selectedCC;
    }
    
    public void onRowSelect(SelectEvent event) {
        if (selectedCCs == null){
            selectedCCsAccion = false;
        } else if (selectedCCs.length > 0) {
            selectedCCsAccion = true;
        } else {
            selectedCCsAccion = false;
        }
    }
    
    public void onRowUnselect(UnselectEvent event) {
        if (selectedCCs == null){
            selectedCCsAccion = false;
        } else if (selectedCCs.length > 0) {
            selectedCCsAccion = true;
        } else {
            selectedCCsAccion = false;
        }
    }
    
    public void detalleAccionCC(){
        if (selectedCCs != null){
            selectedCCsPreasignar = true;
            selectedCCsRecuperar = true;
            selectedCCsReservar = true;
            selectedCCsLiberar = true;
            String operador = selectedCCs[0].getEmrCodigo().getEmrCodigo();
            
            for (CcCodigosCortosVO n : selectedCCs) {
                //selectedNumsCantidad = selectedNumsCantidad + (n.getNunFin()-n.getNunInicio()+1);
                
                //--- Activar / desactivar botón Pre-Asignar
                if (selectedCCsPreasignar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 1) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedCCsPreasignar = true;
                    } else {
                        selectedCCsPreasignar = false;
                    }
                }
                //--- Activar / desactivar botón Recuperar
                if (selectedCCsRecuperar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 3) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedCCsRecuperar = true;
                    } else {
                        selectedCCsRecuperar = false;
                    }
                }
                //--- Activar / desactivar botón Reservar
                if (selectedCCsReservar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 1) {
                        selectedCCsReservar = true;
                    } else {
                        selectedCCsReservar = false;
                    }
                }
                //--- Activar / desactivar botón Liberar
                if (selectedCCsLiberar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 4) {
                        selectedCCsLiberar = true;
                    } else {
                        selectedCCsLiberar = false;
                    }
                }
                
            }
        } else {
            //selectedNumsCantidad = 0;
            selectedCCsPreasignar = false;
            selectedCCsRecuperar = false;
            selectedCCsReservar = false;
            selectedCCsLiberar = false;
        }
    }
    
    public String reservar() {
        int operacion;
        facade fachada = new facade();
        
        ArrayList vo = new ArrayList();
        int size = selectedCCs.length;
        for (int i = 0; i < size; i++) {
            vo.add(selectedCCs[i]);
        }
        
        operacion = fachada.reservarLiberarRecurso(vo,1);
        
        if(operacion == 1){
            buscar();
            selectedCCsAccion = false;
            return "Reserva de códigos cortos exitosa";
        }else{
            return "Error en el bean de códigos cortos";   
        }
    }
    
    public String liberar() {
        int operacion;
        facade fachada = new facade();
        
        ArrayList vo = new ArrayList();
        int size = selectedCCs.length;
        for (int i = 0; i < size; i++) {
            vo.add(selectedCCs[i]);
        }
        
        operacion = fachada.reservarLiberarRecurso(vo,0);
        
        if(operacion == 1){
            buscar();
            selectedCCsAccion = false;
            return "Liberación de códigos cortos exitosa";
        }else{
         return "Error en el bean de códigos cortos";   
        }
    }
    
    public LazyDataModel<CcCodigosCortosVO> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<CcCodigosCortosVO> lazyModel) {
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

    public String getCodigoCorto() {
        return codigoCorto;
    }

    public void setCodigoCorto(String codigoCorto) {
        this.codigoCorto = codigoCorto;
    }

    public EsEstadoVO getEstadoVO() {
        return estadoVO;
    }

    public void setEstadoVO(EsEstadoVO estadoVO) {
        this.estadoVO = estadoVO;
    }

    public Collection<SelectItem> getListaModalidad() {
        return listaModalidad;
    }

    public void setListaModalidad(Collection<SelectItem> listaModalidad) {
        this.listaModalidad = listaModalidad;
    }

    public MdModalidadCcVO getModalidadVO() {
        return modalidadVO;
    }

    public void setModalidadVO(MdModalidadCcVO modalidadVO) {
        this.modalidadVO = modalidadVO;
    }

    public EmOperadorVO getOperadorVO() {
        return operadorVO;
    }

    public void setOperadorVO(EmOperadorVO operadorVO) {
        this.operadorVO = operadorVO;
    }

    public CcCodigosCortosVO getSelectedCC() {
        return selectedCC;
    }

    public void setSelectedCC(CcCodigosCortosVO selectedCC) {
        this.selectedCC = selectedCC;
        if (selectedCC != null){
            facade fachada = new facade();
            this.tramiteCodigoCorto = fachada.buscarTramitePorCodigoCorto(selectedCC.getCcnCodigo(), 5);
        }
    }

    public CcCodigosCortosVO[] getSelectedCCs() {
        return selectedCCs;
    }

    public void setSelectedCCs(CcCodigosCortosVO[] selectedCCs) {
        this.selectedCCs = selectedCCs;
    }

    public Boolean getSelectedCCsAccion() {
        return selectedCCsAccion;
    }

    public void setSelectedCCsAccion(Boolean selectedCCsAccion) {
        this.selectedCCsAccion = selectedCCsAccion;
    }

    public CcCodigosCortosVO[] getSelectedCCsDetalle() {
        return selectedCCsDetalle;
    }

    public void setSelectedCCsDetalle(CcCodigosCortosVO[] selectedCCsDetalle) {
        this.selectedCCsDetalle = selectedCCsDetalle;
    }

    public Boolean getSelectedCCsLiberar() {
        return selectedCCsLiberar;
    }

    public void setSelectedCCsLiberar(Boolean selectedCCsLiberar) {
        this.selectedCCsLiberar = selectedCCsLiberar;
    }

    public Boolean getSelectedCCsRecuperar() {
        return selectedCCsRecuperar;
    }

    public void setSelectedCCsRecuperar(Boolean selectedCCsRecuperar) {
        this.selectedCCsRecuperar = selectedCCsRecuperar;
    }

    public Boolean getSelectedCCsReservar() {
        return selectedCCsReservar;
    }

    public void setSelectedCCsReservar(Boolean selectedCCsReservar) {
        this.selectedCCsReservar = selectedCCsReservar;
    }

    public Boolean getSelectedCCsPreasignar() {
        return selectedCCsPreasignar;
    }

    public void setSelectedCCsPreasignar(Boolean selectedCCsPreasignar) {
        this.selectedCCsPreasignar = selectedCCsPreasignar;
    }
    
    public List<TcTramiteCcVO> getTramiteCodigoCorto() {
        return tramiteCodigoCorto;
    }

    public void setTramiteCodigoCorto(List<TcTramiteCcVO> tramiteCodigoCorto) {
        this.tramiteCodigoCorto = tramiteCodigoCorto;
    }
    
}
