/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import helper.ConvertirListasHelper;
import java.io.Serializable;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.bean.ViewScoped;
import javax.faces.model.SelectItem;
import org.apache.poi.hssf.usermodel.HSSFCell;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.hssf.util.HSSFColor;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.MunicipiosVO;
import vo.ReRegionVO;
import vo.RtTipoRegionVO;
import vo.SeSenalizacionVO;
import vo.TeTipoSenalizacionVO;
import vo.TsTramiteSenalizacionVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "SenalizacionBean")
@ViewScoped
public class SenalizacionBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<SeSenalizacionVO> sen = null;//new ArrayList<NuNumeracionVO>();
    private Collection<SelectItem> listaRegionSenalizacion;
    private Collection<SelectItem> listaTipoSenalizacion;
    private Collection<SelectItem> listaTipoRegionSenalizacion;
    private Collection<SelectItem> listaZona;
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private Collection<SelectItem> listaDepartamento;
    private Collection<SelectItem> listaMunicipio;
    private ReRegionVO regionSenalizacionVO = new ReRegionVO();
    private TeTipoSenalizacionVO tipoSenalizacionVO = new TeTipoSenalizacionVO();
    private RtTipoRegionVO tipoRegionSenalizacionVO = new RtTipoRegionVO();
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private DepartamentosVO departamentoVO = new DepartamentosVO();
    private MunicipiosVO municipioVO = new MunicipiosVO();
    private String zonaSenalizacion;
    private String psSenalizacion;
    private LazyDataModel<SeSenalizacionVO> lazyModel;
    private SeSenalizacionVO selectedSen;
    private SeSenalizacionVO[] selectedSens;
    private Boolean selectedSensPreasignar;
    private Boolean selectedSensRecuperar;
    private Boolean selectedSensReservar;
    private Boolean selectedSensLiberar;
    private Boolean selectedSensAccion;
    private SeSenalizacionVO[] selectedSensDetalle;
    private List<TsTramiteSenalizacionVO> tramiteSenalizacion = null;
    
    
    /** Creates a new instance of SenalizacionBean */
    public SenalizacionBean() {
        facade fachada = new facade();
        
        /*ArrayList selectItemsList = new ArrayList();
        SelectItem selectItem = new SelectItem();
        selectItem.setValue("-1");
        selectItem.setLabel("");
        selectItemsList.add(selectItem);
        DecimalFormat format = new DecimalFormat("00");
        for (Integer i = 0; i < 16; i++){
            selectItem = new SelectItem();
            selectItem.setValue(i.toString());
            selectItem.setLabel(format.format(i));
            selectItemsList.add(selectItem);
        }
        listaZona = selectItemsList;*/
        
        operadorVO.setEmrCodigo("-1");
        municipioVO.setCodigoMunicipio("-1");
        departamentoVO.setCodigoDepartamento("-1");
        estadoVO.setEsnCodigo(-1);
        regionSenalizacionVO.setRenCodigo(-1);
        tipoSenalizacionVO.setTenCodigo(-1);
        tipoRegionSenalizacionVO.setRtnCodigo(1);
        psSenalizacion="";
        zonaSenalizacion="-1";
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaRegionSenalizacion = convertir.createSelectItemsList(fachada.listaRegionSenalizacion(tipoRegionSenalizacionVO.getRtnCodigo()), null, "getRenCodigo", "getRetNombre", true, "");
            listaZona = convertir.createSelectItemsList(fachada.listaZonaSenalizacion(tipoRegionSenalizacionVO.getRtnCodigo()),-1);
            listaTipoSenalizacion = convertir.createSelectItemsList(fachada.listaTipoSenalizacion(), null, "getTenCodigo", "getTetNombre", true, "");
            listaTipoRegionSenalizacion = convertir.createSelectItemsList(fachada.listaTipoRegionSenalizacion(), null, "getRtnCodigo", "getRttNombre", false, "");
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorSenalizacion(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
            listaDepartamento = convertir.createSelectItemsList(fachada.listaDepartamentos(), "getCodigoDepartamento", null, "getNombreDepartamento", true, "");
            listaMunicipio = convertir.createSelectItemsList(fachada.listaMunicipios(departamentoVO.getCodigoDepartamento()), "getCodigoMunicipio", null, "getNombreMunicipio", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Señalización", e);
        }
          
        lazyModel = new LazyDataModel<SeSenalizacionVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<SeSenalizacionVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<SeSenalizacionVO> lazySenalizacion = new ArrayList<SeSenalizacionVO>();
                facade fachada = new facade();
                lazySenalizacion = fachada.cargarSenalizacion(first, pageSize, "-1", -1, -1, -1, -1, "-1", "-1", -1, tipoRegionSenalizacionVO.getRtnCodigo()); 
  
                return lazySenalizacion;
            }  
        };
        lazyModel.setRowCount(fachada.countCargarSenalizacion("-1", -1, -1, -1, -1, "-1", "-1", -1, tipoRegionSenalizacionVO.getRtnCodigo()));
        
    }
    
    public LazyDataModel<SeSenalizacionVO> getLazyModel() {  
        return lazyModel;  
    }
    
    public String buscar() {
        List<SeSenalizacionVO> senalizacion = new ArrayList<SeSenalizacionVO>();
        facade fachada = new facade();

        final Integer zona;
        final Integer ps;
        
        if (zonaSenalizacion.equals("")) {
            zona = -1;
        } else {
            zona = Integer.parseInt(zonaSenalizacion);
        }
        
        if (psSenalizacion.equals("")) {
            ps = -1;
        } else {
            ps = Integer.parseInt(psSenalizacion);
        }
        //System.out.println(operadorVO.getEmrCodigo() +"*"+ regionSenalizacionVO.getRenCodigo() +"*"+ zona +"*"+ ps +"*"+ estadoVO.getEsnCodigo() +"*"+ municipioVO.getCodigoMunicipio() +"*"+ departamentoVO.getCodigoDepartamento()+"*");
        //System.out.println("Esrtado:"+estadoVO.getEsnCodigo());

        lazyModel = new LazyDataModel<SeSenalizacionVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<SeSenalizacionVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<SeSenalizacionVO> lazySenalizacion = new ArrayList<SeSenalizacionVO>();
                facade fachada = new facade();
                lazySenalizacion = fachada.cargarSenalizacion(first, pageSize, operadorVO.getEmrCodigo(), regionSenalizacionVO.getRenCodigo(), zona, ps, estadoVO.getEsnCodigo(), municipioVO.getCodigoMunicipio(), departamentoVO.getCodigoDepartamento(), tipoSenalizacionVO.getTenCodigo(), tipoRegionSenalizacionVO.getRtnCodigo()); 
                
                return lazySenalizacion;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarSenalizacion(operadorVO.getEmrCodigo(), regionSenalizacionVO.getRenCodigo(), zona, ps, estadoVO.getEsnCodigo(), municipioVO.getCodigoMunicipio(), departamentoVO.getCodigoDepartamento(), tipoSenalizacionVO.getTenCodigo(), tipoRegionSenalizacionVO.getRtnCodigo())); 
        
        
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
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Señalización", e);
        }
           
    }
    
    public void cambiarTipoRegionSenalizacion() {
        facade fachada = new facade();
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            regionSenalizacionVO.setRenCodigo(-1);
            listaRegionSenalizacion = convertir.createSelectItemsList(fachada.listaRegionSenalizacion(tipoRegionSenalizacionVO.getRtnCodigo()), null, "getRenCodigo", "getRetNombre", true, "");
            zonaSenalizacion="-1";
            listaZona = convertir.createSelectItemsList(fachada.listaZonaSenalizacion(tipoRegionSenalizacionVO.getRtnCodigo()),-1);
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Señalización", e);
        }
           
    }
    
    public void detalleSen(){
        selectedSens = new SeSenalizacionVO[1];
        selectedSens[0] = selectedSen;
    }
    
    public void onRowSelect() {
        if (selectedSens == null){
            selectedSensAccion = false;
        } else if (selectedSens.length > 0) {
            selectedSensAccion = true;
        } else {
            selectedSensAccion = false;
        }
    }
    
    public void onRowUnselect() {
        if (selectedSens == null){
            selectedSensAccion = false;
        } else if (selectedSens.length > 0) {
            selectedSensAccion = true;
        } else {
            selectedSensAccion = false;
        }
    }
    
    public void detalleAccionSen(){
        if (selectedSens != null){
            //selectedSensCantidad = 0;
            selectedSensPreasignar = true;
            selectedSensRecuperar = true;
            selectedSensReservar = true;
            selectedSensLiberar = true;
            String operador = selectedSens[0].getEmrCodigo().getEmrCodigo();
            
            //--- Activar / desactivar botón Preasignar (solo se permite la presignación de un recurso a la vez)
            if (selectedSens.length > 1) {
                selectedSensPreasignar = false;
            } else {
                if (selectedSens[0].getEsnCodigo().getEsnCodigo() == 1) {
                    selectedSensPreasignar = true;
                } else {
                    selectedSensPreasignar = false;
                }
            }
            
            for (SeSenalizacionVO n : selectedSens) {
                //selectedNumsCantidad = selectedNumsCantidad + (n.getNunFin()-n.getNunInicio()+1);

                //--- Activar / desactivar botón Recuperar
                if (selectedSensRecuperar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 3) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedSensRecuperar = true;
                    } else {
                        selectedSensRecuperar = false;
                    }
                }
                //--- Activar / desactivar botón Reservar
                if (selectedSensReservar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 1) {
                        selectedSensReservar = true;
                    } else {
                        selectedSensReservar = false;
                    }
                }
                //--- Activar / desactivar botón Liberar
                if (selectedSensLiberar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 4) {
                        selectedSensLiberar = true;
                    } else {
                        selectedSensLiberar = false;
                    }
                }
                
            }
        } else {
            //selectedNumsCantidad = 0;
            selectedSensPreasignar = false;
            selectedSensRecuperar = false;
            selectedSensReservar = false;
            selectedSensLiberar = false;
        }
    }
    
    public void postProcessXLS(Object document) {
	HSSFWorkbook wb = (HSSFWorkbook) document;
	HSSFSheet sheet = wb.getSheetAt(0);
	HSSFRow header = sheet.getRow(0);
        
	HSSFCellStyle cellStyle = wb.createCellStyle();
	//cellStyle.setFillForegroundColor(HSSFColor.GREY_50_PERCENT.index);
	//cellStyle.setFillPattern(HSSFCellStyle.SOLID_FOREGROUND);
        HSSFFont cellFont = wb.createFont();
        cellFont.setBoldweight(HSSFFont.BOLDWEIGHT_BOLD);
        cellStyle.setFont(cellFont);
        
        HSSFRichTextString titulo;
        titulo = new HSSFRichTextString("CÓDIGO SEÑALIZACION");
        header.getCell(0).setCellValue(titulo);
        header.getCell(0).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("DEPARTAMENTO");
        header.getCell(1).setCellValue(titulo);
        header.getCell(1).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("MUNICIPIO");
        header.getCell(2).setCellValue(titulo);
        header.getCell(2).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("ESTADO");
        header.getCell(3).setCellValue(titulo);
        header.getCell(3).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("OPERADOR");
        header.getCell(4).setCellValue(titulo);
        header.getCell(4).setCellStyle(cellStyle);
        
        /*
	Integer a = header.getPhysicalNumberOfCells();
        HSSFCell headerObservaciones = header.createCell(a);
        HSSFRichTextString text = new HSSFRichTextString("Observaciones");
        headerObservaciones.setCellValue(text);
        
	for(int i=0; i <= a;i++) {
            HSSFCell cell = header.getCell(i);
            cell.setCellStyle(cellStyle);
	}*/

        Iterator itr = lazyModel.iterator();
        for(int i=1; i <= lazyModel.getPageSize(); i++){
            SeSenalizacionVO se = (SeSenalizacionVO)itr.next();
            HSSFRow fila = sheet.getRow(i);
            HSSFRichTextString texto = new HSSFRichTextString(se.getEsnCodigo().getEstNombre());
            fila.getCell(3).setCellValue(texto);
        }
        sheet.autoSizeColumn((short) 0);
        sheet.autoSizeColumn((short) 1);
        sheet.autoSizeColumn((short) 2);
        sheet.autoSizeColumn((short) 3);
        sheet.autoSizeColumn((short) 4);
    }
    
    public String getPsSenalizacion() {
        return psSenalizacion;
    }

    public void setPsSenalizacion(String psSenalizacion) {
        this.psSenalizacion = psSenalizacion;
    }

    public String getZonaSenalizacion() {
        return zonaSenalizacion;
    }

    public void setZonaSenalizacion(String zonaSenalizacion) {
        this.zonaSenalizacion = zonaSenalizacion;
    }

    public EsEstadoVO getEstadoVO() {
        return estadoVO;
    }

    public void setEstadoVO(EsEstadoVO estadoVO) {
        this.estadoVO = estadoVO;
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

    public Collection<SelectItem> getListaRegionSenalizacion() {
        return listaRegionSenalizacion;
    }

    public void setListaRegionSenalizacion(Collection<SelectItem> listaRegionSenalizacion) {
        this.listaRegionSenalizacion = listaRegionSenalizacion;
    }

    public EmOperadorVO getOperadorVO() {
        return operadorVO;
    }

    public void setOperadorVO(EmOperadorVO operadorVO) {
        this.operadorVO = operadorVO;
    }

    public ReRegionVO getRegionSenalizacionVO() {
        return regionSenalizacionVO;
    }

    public void setRegionSenalizacionVO(ReRegionVO regionSenalizacionVO) {
        this.regionSenalizacionVO = regionSenalizacionVO;
    }

    public List<SeSenalizacionVO> getSen() {
        return sen;
    }

    public void setSen(List<SeSenalizacionVO> sen) {
        this.sen = sen;
    }

    public SeSenalizacionVO getSelectedSen() {
        return selectedSen;
    }

    public void setSelectedSen(SeSenalizacionVO selectedSen) {
        this.selectedSen = selectedSen;
        if (selectedSen != null){
            facade fachada = new facade();
            this.tramiteSenalizacion = fachada.buscarTramitePorSenalizacion(selectedSen.getSenCodigo(), 5);
        }
    }

    public SeSenalizacionVO[] getSelectedSens() {
        return selectedSens;
    }

    public void setSelectedSens(SeSenalizacionVO[] selectedSens) {
        this.selectedSens = selectedSens;
    }

    public Boolean getSelectedSensAccion() {
        return selectedSensAccion;
    }

    public void setSelectedSensAccion(Boolean selectedSensAccion) {
        this.selectedSensAccion = selectedSensAccion;
    }

    public Boolean getSelectedSensLiberar() {
        return selectedSensLiberar;
    }

    public void setSelectedSensLiberar(Boolean selectedSensLiberar) {
        this.selectedSensLiberar = selectedSensLiberar;
    }

    public Boolean getSelectedSensRecuperar() {
        return selectedSensRecuperar;
    }

    public void setSelectedSensRecuperar(Boolean selectedSensRecuperar) {
        this.selectedSensRecuperar = selectedSensRecuperar;
    }

    public Boolean getSelectedSensReservar() {
        return selectedSensReservar;
    }

    public void setSelectedSensReservar(Boolean selectedSensReservar) {
        this.selectedSensReservar = selectedSensReservar;
    }

    public SeSenalizacionVO[] getSelectedSensDetalle() {
        return selectedSensDetalle;
    }

    public void setSelectedSensDetalle(SeSenalizacionVO[] selectedSensDetalle) {
        this.selectedSensDetalle = selectedSensDetalle;
    }
    
    public DepartamentosVO getDepartamentoVO() {
        return departamentoVO;
    }

    public void setDepartamentoVO(DepartamentosVO departamentoVO) {
        this.departamentoVO = departamentoVO;
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

    public MunicipiosVO getMunicipioVO() {
        return municipioVO;
    }

    public void setMunicipioVO(MunicipiosVO municipioVO) {
        this.municipioVO = municipioVO;
    }

    public Collection<SelectItem> getListaZona() {
        return listaZona;
    }

    public void setListaZona(Collection<SelectItem> listaZona) {
        this.listaZona = listaZona;
    }

    public List<TsTramiteSenalizacionVO> getTramiteSenalizacion() {
        return tramiteSenalizacion;
    }

    public void setTramiteSenalizacion(List<TsTramiteSenalizacionVO> tramiteSenalizacion) {
        this.tramiteSenalizacion = tramiteSenalizacion;
    }

    public Boolean getSelectedSensPreasignar() {
        return selectedSensPreasignar;
    }

    public void setSelectedSensPreasignar(Boolean selectedSensPreasignar) {
        this.selectedSensPreasignar = selectedSensPreasignar;
    }

    public Collection<SelectItem> getListaTipoSenalizacion() {
        return listaTipoSenalizacion;
    }

    public void setListaTipoSenalizacion(Collection<SelectItem> listaTipoSenalizacion) {
        this.listaTipoSenalizacion = listaTipoSenalizacion;
    }

    public TeTipoSenalizacionVO getTipoSenalizacionVO() {
        return tipoSenalizacionVO;
    }

    public void setTipoSenalizacionVO(TeTipoSenalizacionVO tipoSenalizacionVO) {
        this.tipoSenalizacionVO = tipoSenalizacionVO;
    }

    public Collection<SelectItem> getListaTipoRegionSenalizacion() {
        return listaTipoRegionSenalizacion;
    }

    public void setListaTipoRegionSenalizacion(Collection<SelectItem> listaTipoRegionSenalizacion) {
        this.listaTipoRegionSenalizacion = listaTipoRegionSenalizacion;
    }

    public RtTipoRegionVO getTipoRegionSenalizacionVO() {
        return tipoRegionSenalizacionVO;
    }

    public void setTipoRegionSenalizacionVO(RtTipoRegionVO tipoRegionSenalizacionVO) {
        this.tipoRegionSenalizacionVO = tipoRegionSenalizacionVO;
    }

}
