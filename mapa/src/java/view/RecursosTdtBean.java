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
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vo.CaCanalTdtVO;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.MuMultiplexVO;
import vo.MunicipiosVO;
import vo.RiRecursosTdtVO;
import vo.RnTipoRecursoTdtVO;
import vo.RrTipoRedTdtVO;
import vo.TtTipoServicioTdtVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "RecursosTdtBean")
@ViewScoped
public class RecursosTdtBean implements Serializable{
    private static final long serialVersionUID = 1L;
    
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private Collection<SelectItem> listaDepartamento;
    private Collection<SelectItem> listaMunicipio;
    private Collection<SelectItem> listaTipoRecurso;
    private Collection<SelectItem> listaTipoRed;
    private Collection<SelectItem> listaCanal;
    private Collection<SelectItem> listaServicio;
    private Collection<SelectItem> listaMultiplex;
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private DepartamentosVO departamentoVO = new DepartamentosVO();
    private MunicipiosVO municipioVO = new MunicipiosVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private RnTipoRecursoTdtVO tipoRecursoVO = new RnTipoRecursoTdtVO();
    private RrTipoRedTdtVO tipoRedVO = new RrTipoRedTdtVO();
    private CaCanalTdtVO canalVO = new CaCanalTdtVO();
    private TtTipoServicioTdtVO servicioVO = new TtTipoServicioTdtVO();
    private MuMultiplexVO multiplexVO = new MuMultiplexVO();
    private String recursoTdt;
    private LazyDataModel<RiRecursosTdtVO> lazyModel;
    private RiRecursosTdtVO selected;
    private RiRecursosTdtVO[] selecteds;
    private Boolean selectedsPreasignar;
    private Boolean selectedsRecuperar;
    private Boolean selectedsReservar;
    private Boolean selectedsLiberar;
    private Boolean selectedsAccion;
    private RiRecursosTdtVO[] selectedsDetalle;
    //private List<TiTramiteIinVO> tramiteCodigosIin = null;
    
    public RecursosTdtBean() {
        facade fachada = new facade();
        
        municipioVO.setCodigoMunicipio("-1");
        departamentoVO.setCodigoDepartamento("-1");
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorRecursosTdt(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
            listaTipoRecurso = convertir.createSelectItemsList(fachada.listaTipoRecursoTdt(), null, "getRnnCodigo", "getRntNombre", false, "");
            listaTipoRed = convertir.createSelectItemsList(fachada.listaTipoRedTdt(), null, "getRrnCodigo", "getRrtNombre", true, "");
            listaCanal = convertir.createSelectItemsList(fachada.listaCanalTdt(), null, "getCanCodigo", "getCatCanal", true, "");
            listaServicio = convertir.createSelectItemsList(fachada.listaServicioTdt(), null, "getTtnCodigo", "getTttNombre", true, "");
            listaMultiplex = convertir.createSelectItemsList(fachada.listaMultiplex(), null, "getMunCodigo", "getMutNombreMultiplex", true, "");
            listaDepartamento = convertir.createSelectItemsList(fachada.listaDepartamentos(), "getCodigoDepartamento", null, "getNombreDepartamento", true, "");
            listaMunicipio = convertir.createSelectItemsList(fachada.listaMunicipios(departamentoVO.getCodigoDepartamento()), "getCodigoMunicipio", null, "getNombreMunicipio", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de recursos TDT - RecursosTdtBean", e);
        }
        
        lazyModel = new LazyDataModel<RiRecursosTdtVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<RiRecursosTdtVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<RiRecursosTdtVO> lazyRecursosTdt = new ArrayList<RiRecursosTdtVO>();
                facade fachada = new facade();
                lazyRecursosTdt = fachada.cargarRecursosTdt(first, pageSize, "-1", -1, 1, -1, -1, "-1", "-1", -1, -1, -1); 
  
                return lazyRecursosTdt;
            }  
        };
        lazyModel.setRowCount(fachada.countCargarRecursosTdt("-1", -1, 1, -1, -1, "-1", "-1", -1, -1, -1));
        
        operadorVO.setEmrCodigo("-1");
        tipoRecursoVO.setRnnCodigo(1);
        tipoRedVO.setRrnCodigo(-1);
        canalVO.setCanCodigo(-1);
        servicioVO.setTtnCodigo(-1);
        multiplexVO.setMunCodigo(-1);
        recursoTdt = "";
        estadoVO.setEsnCodigo(-1);
    }
    
    public String buscar() {
        List<RiRecursosTdtVO> RecursosTdt = new ArrayList<RiRecursosTdtVO>();
        facade fachada = new facade();

        final Integer codigo;
        
        if (recursoTdt.equals("")) {
            codigo = -1;
        } else {
            codigo = Integer.parseInt(recursoTdt);
        }
        //System.out.println(operadorVO.getEmrCodigo() +"*"+ regionSenalizacionVO.getRenCodigo() +"*"+ zona +"*"+ ps +"*"+ estadoVO.getEsnCodigo() +"*"+ municipioVO.getCodigoMunicipio() +"*"+ departamentoVO.getCodigoDepartamento()+"*");
        //System.out.println("Esrtado:"+estadoVO.getEsnCodigo());

        lazyModel = new LazyDataModel<RiRecursosTdtVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<RiRecursosTdtVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<RiRecursosTdtVO> lazyRecursosTdt = new ArrayList<RiRecursosTdtVO>();
                facade fachada = new facade();
                lazyRecursosTdt = fachada.cargarRecursosTdt(first, pageSize, operadorVO.getEmrCodigo(), codigo, tipoRecursoVO.getRnnCodigo(), tipoRedVO.getRrnCodigo(), canalVO.getCanCodigo(), municipioVO.getCodigoMunicipio(), departamentoVO.getCodigoDepartamento(), servicioVO.getTtnCodigo(), multiplexVO.getMunCodigo(), estadoVO.getEsnCodigo()); 
                
                return lazyRecursosTdt;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarRecursosTdt(operadorVO.getEmrCodigo(), codigo, tipoRecursoVO.getRnnCodigo(), tipoRedVO.getRrnCodigo(), canalVO.getCanCodigo(), municipioVO.getCodigoMunicipio(), departamentoVO.getCodigoDepartamento(), servicioVO.getTtnCodigo(), multiplexVO.getMunCodigo(), estadoVO.getEsnCodigo())); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public void cambiarDepartamento() {
        facade fachada = new facade();
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaMunicipio = convertir.createSelectItemsList(fachada.listaMunicipios(departamentoVO.getCodigoDepartamento()), "getCodigoMunicipio", null, "getNombreMunicipio", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Recursos TDT - cambiarDepartamento", e);
        }
           
    }
    
    public void borrarServicio() {
        servicioVO.setTtnCodigo(-1);
    }
    
    public void detalle(){
        selecteds = new RiRecursosTdtVO[1];
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
            
            for (RiRecursosTdtVO n : selecteds) {
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
    
    public LazyDataModel<RiRecursosTdtVO> getLazyModel() {
        return lazyModel;
    }
    
    public void setLazyModel(LazyDataModel<RiRecursosTdtVO> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public CaCanalTdtVO getCanalVO() {
        return canalVO;
    }

    public void setCanalVO(CaCanalTdtVO canalVO) {
        this.canalVO = canalVO;
    }

    public EsEstadoVO getEstadoVO() {
        return estadoVO;
    }

    public void setEstadoVO(EsEstadoVO estadoVO) {
        this.estadoVO = estadoVO;
    }

    public Collection<SelectItem> getListaCanal() {
        return listaCanal;
    }

    public void setListaCanal(Collection<SelectItem> listaCanal) {
        this.listaCanal = listaCanal;
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

    public Collection<SelectItem> getListaTipoRecurso() {
        return listaTipoRecurso;
    }

    public void setListaTipoRecurso(Collection<SelectItem> listaTipoRecurso) {
        this.listaTipoRecurso = listaTipoRecurso;
    }

    public Collection<SelectItem> getListaTipoRed() {
        return listaTipoRed;
    }

    public void setListaTipoRed(Collection<SelectItem> listaTipoRed) {
        this.listaTipoRed = listaTipoRed;
    }

    public EmOperadorVO getOperadorVO() {
        return operadorVO;
    }

    public void setOperadorVO(EmOperadorVO operadorVO) {
        this.operadorVO = operadorVO;
    }

    public String getRecursoTdt() {
        return recursoTdt;
    }

    public void setRecursoTdt(String recursoTdt) {
        this.recursoTdt = recursoTdt;
    }

    public RiRecursosTdtVO getSelected() {
        return selected;
    }

    public void setSelected(RiRecursosTdtVO selected) {
        this.selected = selected;
        if (selected != null){
            facade fachada = new facade();
            //this.tramiteRecursosTdt = fachada.buscarTramitePorCodigosIin(selected.getCinCodigo(), 5);
        }
    }

    public RiRecursosTdtVO[] getSelecteds() {
        return selecteds;
    }

    public void setSelecteds(RiRecursosTdtVO[] selecteds) {
        this.selecteds = selecteds;
    }

    public Boolean getSelectedsAccion() {
        return selectedsAccion;
    }

    public void setSelectedsAccion(Boolean selectedsAccion) {
        this.selectedsAccion = selectedsAccion;
    }

    public RiRecursosTdtVO[] getSelectedsDetalle() {
        return selectedsDetalle;
    }

    public void setSelectedsDetalle(RiRecursosTdtVO[] selectedsDetalle) {
        this.selectedsDetalle = selectedsDetalle;
    }

    public Boolean getSelectedsLiberar() {
        return selectedsLiberar;
    }

    public void setSelectedsLiberar(Boolean selectedsLiberar) {
        this.selectedsLiberar = selectedsLiberar;
    }

    public Boolean getSelectedsPreasignar() {
        return selectedsPreasignar;
    }

    public void setSelectedsPreasignar(Boolean selectedsPreasignar) {
        this.selectedsPreasignar = selectedsPreasignar;
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

    public RnTipoRecursoTdtVO getTipoRecursoVO() {
        return tipoRecursoVO;
    }

    public void setTipoRecursoVO(RnTipoRecursoTdtVO tipoRecursoVO) {
        this.tipoRecursoVO = tipoRecursoVO;
    }

    public RrTipoRedTdtVO getTipoRedVO() {
        return tipoRedVO;
    }

    public void setTipoRedVO(RrTipoRedTdtVO tipoRedVO) {
        this.tipoRedVO = tipoRedVO;
    }

    public DepartamentosVO getDepartamentoVO() {
        return departamentoVO;
    }

    public void setDepartamentoVO(DepartamentosVO departamentoVO) {
        this.departamentoVO = departamentoVO;
    }

    public MunicipiosVO getMunicipioVO() {
        return municipioVO;
    }

    public void setMunicipioVO(MunicipiosVO municipioVO) {
        this.municipioVO = municipioVO;
    }

    public Collection<SelectItem> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(Collection<SelectItem> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public Collection<SelectItem> getListaMunicipio() {
        return listaMunicipio;
    }

    public void setListaMunicipio(Collection<SelectItem> listaMunicipio) {
        this.listaMunicipio = listaMunicipio;
    }

    public Collection<SelectItem> getListaServicio() {
        return listaServicio;
    }

    public void setListaServicio(Collection<SelectItem> listaServicio) {
        this.listaServicio = listaServicio;
    }

    public TtTipoServicioTdtVO getServicioVO() {
        return servicioVO;
    }

    public void setServicioVO(TtTipoServicioTdtVO servicioVO) {
        this.servicioVO = servicioVO;
    }

    public Collection<SelectItem> getListaMultiplex() {
        return listaMultiplex;
    }

    public void setListaMultiplex(Collection<SelectItem> listaMultiplex) {
        this.listaMultiplex = listaMultiplex;
    }

    public MuMultiplexVO getMultiplexVO() {
        return multiplexVO;
    }

    public void setMultiplexVO(MuMultiplexVO multiplexVO) {
        this.multiplexVO = multiplexVO;
    }
    
}
