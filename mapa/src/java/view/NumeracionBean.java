/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import entities.NtTipoNdc;
import facade.facade;
import helper.ConvertirListasHelper;
import java.io.Serializable;
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
import javax.faces.event.ValueChangeEvent;
import javax.faces.model.SelectItem;
import org.apache.poi.hssf.usermodel.HSSFCellStyle;
import org.apache.poi.hssf.usermodel.HSSFFont;
import org.apache.poi.hssf.usermodel.HSSFRichTextString;
import org.apache.poi.hssf.usermodel.HSSFRow;
import org.apache.poi.hssf.usermodel.HSSFSheet;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import vo.DepartamentosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.MunicipiosVO;
import vo.NdNdcVO;
import vo.NuNumeracionVO;
import vo.TnTramiteNumeracionVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "NumeracionBean")
@ViewScoped
public class NumeracionBean implements Serializable {
    private static final long serialVersionUID = 1L;

//    private List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();
    private List<NuNumeracionVO> num = null;//new ArrayList<NuNumeracionVO>();
    private Collection<SelectItem> listaNDC;
    private Collection<SelectItem> listaTipoNdc;
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private Collection<SelectItem> listaDepartamento;
    private Collection<SelectItem> listaMunicipio;
    private NdNdcVO ndcVO = new NdNdcVO();
    private NtTipoNdc tipoNdcVO = new NtTipoNdc();
    private DepartamentosVO departamentoVO = new DepartamentosVO();
    private MunicipiosVO municipioVO = new MunicipiosVO();
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String NumInicio;
    private String NumFin;
    private LazyDataModel<NuNumeracionVO> lazyModel;
    private NuNumeracionVO selectedNum;
    private NuNumeracionVO[] selectedNums;
    private Integer selectedNumsCantidad;
    private Boolean selectedNumsPreasignar;
    private Boolean selectedNumsRecuperar;
    private Boolean selectedNumsReservar;
    private Boolean selectedNumsLiberar;
    private Boolean selectedNumsAccion;
    
    private List<NuNumeracionVO> detalleSelectedNum = new ArrayList<NuNumeracionVO>();
    private NuNumeracionVO[] selectedNumsDetalle;
    private List<TnTramiteNumeracionVO> tramiteNumeracion = null;
    
    public NumeracionBean() {
        facade fachada = new facade();
       
//        List<EmOperadorVO> ope = fachada.listaOperador();
//        System.out.println("hola:"+ope.size());
//        for (int i = 0; i < ope.size(); i++){
//            System.out.println(ope.get(i).getEmtNombre());
//        }
        
        ndcVO.setNdtNombre("-1");
        tipoNdcVO.setNtnCodigo(-1);
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaNDC = convertir.createSelectItemsList(fachada.listaNDC(), null, "getNdnCodigo", "getNdtNombre", true, "");
            listaTipoNdc = convertir.createSelectItemsList(fachada.listaTipoNdc(ndcVO.getNdtNombre()), null, "getNtnCodigo", "getNttNombre", true, "");
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorNumeracion(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
            listaDepartamento = convertir.createSelectItemsList(fachada.listaDepartamentos(), "getCodigoDepartamento", null, "getNombreDepartamento", true, "");
            listaMunicipio = convertir.createSelectItemsList(fachada.listaMunicipios(departamentoVO.getCodigoDepartamento()), "getCodigoMunicipio", null, "getNombreMunicipio", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Numeración", e);
        }
          
        lazyModel = new LazyDataModel<NuNumeracionVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<NuNumeracionVO> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<NuNumeracionVO> lazyNumeracion = new ArrayList<NuNumeracionVO>();
                List<NuNumeracionVO> numera = new ArrayList<NuNumeracionVO>();
                facade fachada = new facade();
                numera = fachada.cargarNumeracion(first, pageSize*10, "-1", "-1", -1, -1, -1, -1, "-1", "-1"); 
                lazyNumeracion = agruparNumeracion(numera);
  
                return lazyNumeracion;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarNumeracion("-1", "-1", -1, -1, -1, -1,"-1","-1"));
        
        operadorVO.setEmrCodigo("-1");
        municipioVO.setCodigoMunicipio("-1");
        departamentoVO.setCodigoDepartamento("-1");
        estadoVO.setEsnCodigo(-1);
        NumInicio = "";
        NumFin = "";
        
        
    }

    public LazyDataModel<NuNumeracionVO> getLazyModel() {  
        return lazyModel;  
    }
        
    public String buscar() {
        List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();
        facade fachada = new facade();
        //System.out.println("operador:" + operadorVO.getEmrCodigo());
        //System.out.println("ndc:" + ndcVO.getNdnCodigo());
        final Integer inicio;
        final Integer fin;
        
        if (NumInicio.equals("")) {
            inicio = -1;
        } else {
            inicio = Integer.parseInt(NumInicio);
        }
        
        if (NumFin.equals("")) {
            fin = -1;
        } else {
            fin = Integer.parseInt(NumFin);
        }

        lazyModel = new LazyDataModel<NuNumeracionVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<NuNumeracionVO> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<NuNumeracionVO> lazyNumeracion = new ArrayList<NuNumeracionVO>();
                List<NuNumeracionVO> numera = new ArrayList<NuNumeracionVO>();
                facade fachada = new facade();
                numera = fachada.cargarNumeracion(first, pageSize*10, operadorVO.getEmrCodigo(), ndcVO.getNdtNombre(), tipoNdcVO.getNtnCodigo(), inicio, fin, estadoVO.getEsnCodigo(), municipioVO.getCodigoMunicipio(), departamentoVO.getCodigoDepartamento()); 
                lazyNumeracion = agruparNumeracion(numera);
                
                /*for(int i=0; i < numera.size();i++) {
                    BigDecimal codigo = numera.get(i).getNunCodigo();
                    String operador = numera.get(i).getEmrCodigo().getEmtNombre();
                    String estado = numera.get(i).getEsnCodigo().getEstNombre();
                    String region = numera.get(i).getCodigoMunicipio().getNombreMunicipio();
                    int inicio = numera.get(i).getNunInicio();
                    System.out.println(codigo+"-"+operador+"-"+estado+"-"+region+"-"+inicio);
                }*/
                
                return lazyNumeracion;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarNumeracion(operadorVO.getEmrCodigo(), ndcVO.getNdtNombre(), tipoNdcVO.getNtnCodigo(), inicio, fin, estadoVO.getEsnCodigo(), municipioVO.getCodigoMunicipio(), departamentoVO.getCodigoDepartamento())); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public List<NuNumeracionVO> agruparNumeracion(List<NuNumeracionVO> numeracion){
        List<NuNumeracionVO> numeros = new ArrayList<NuNumeracionVO>();
        
        NuNumeracionVO numera = new NuNumeracionVO();
        NuNumeracionVO numeraF = new NuNumeracionVO();
        
        int ndc = 0;
        Integer inicio = 0;
        Integer fin = 0;
        String operador = "";
        String municipio = "";
        int estado = 0;
        
        int ndcF = 0;
        Integer inicioF = 0;
        Integer finF = 0;
        String operadorF = "";
        String municipioF = "";
        int estadoF = 0;
        
        int x = 0;

        for(int i = 0; i < numeracion.size(); i++){
            numeraF = numeracion.get(i);
            ndcF = numeraF.getNdnCodigo().getNdnCodigo();
            inicioF = numeraF.getNunInicio();
            finF = numeraF.getNunFin();
            operadorF = numeraF.getEmrCodigo().getEmtNombre();
            municipioF = numeraF.getCodigoMunicipio().getCodigoMunicipio();
            estadoF = numeraF.getEsnCodigo().getEsnCodigo();
            
            if ((ndc == ndcF)&&(operador.equals(operadorF))&&(municipio.equals(municipioF))&&(inicio.toString().substring(0,4).equals(inicioF.toString().substring(0,4)))&&(estado == estadoF)){
                numeros.get(x-1).setNunFin(finF);
            } else {
                numeros.add(x, numeraF);
                x++;
            }
            
            numera = numeraF;
            ndc = numera.getNdnCodigo().getNdnCodigo();
            inicio = numera.getNunInicio();
            fin = numera.getNunFin();
            operador = numera.getEmrCodigo().getEmtNombre();
            municipio = numera.getCodigoMunicipio().getCodigoMunicipio();
            estado = estadoF;
            
        }
        //System.out.println("X es:"+x);
//        for(int i = 0; i < numeros.size(); i++){
//            System.out.println(numeros.get(i).getNdnCodigo().getNdtNombre());
//            System.out.println(numeros.get(i).getEmrCodigo().getEmtNombre());
//            System.out.println(numeros.get(i).getNunInicio() + "-" + numeros.get(i).getNunFin());
//            System.out.println("---");
//        }        
        
        return numeros;
    }
    
//    public List<NuNumeracionVO> getNumeracion() {
//        return numeracion;
//    }
//
//    public void setNumeracion(List<NuNumeracionVO> numeracion) {
//        this.numeracion = numeracion;
//    }

    public void cambiarDepartamento() {
        facade fachada = new facade();
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaMunicipio = convertir.createSelectItemsList(fachada.listaMunicipios(departamentoVO.getCodigoDepartamento()), "getCodigoMunicipio", null, "getNombreMunicipio", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Numeración", e);
        }
           
    }
    
    public void cambiarNdc() {
        facade fachada = new facade();
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaTipoNdc = convertir.createSelectItemsList(fachada.listaTipoNdc(ndcVO.getNdtNombre()), null, "getNtnCodigo", "getNttNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Numeración", e);
        }
           
    }
    
    public void detalleNum(){
        facade fachada = new facade();
        detalleSelectedNum = fachada.cargarNumeracion(0, -1, "-1", selectedNum.getNdnCodigo().getNdtNombre(), -1, selectedNum.getNunInicio(), selectedNum.getNunFin(), -1, "-1", "-1");
    }
    
    public void onRowSelect(SelectEvent event) {
        if (selectedNums == null){
            selectedNumsAccion = false;
        } else if (selectedNums.length > 0) {
            selectedNumsAccion = true;
        } else {
            selectedNumsAccion = false;
        }
    }
    
    public void onRowUnselect(UnselectEvent event) {
        if (selectedNums == null){
            selectedNumsAccion = false;
        } else if (selectedNums.length > 0) {
            selectedNumsAccion = true;
        } else {
            selectedNumsAccion = false;
        }
    }
    
    public void detalleAccionNum(){
        if (selectedNums != null){
            selectedNumsCantidad = 0;
            selectedNumsPreasignar = true;
            selectedNumsRecuperar = true;
            selectedNumsReservar = true;
            selectedNumsLiberar = true;
            String operador = selectedNums[0].getEmrCodigo().getEmrCodigo();
            
            for (NuNumeracionVO n : selectedNums) {
                selectedNumsCantidad = selectedNumsCantidad + (n.getNunFin()-n.getNunInicio()+1);
                
                //--- Activar / desactivar botón Pre-Asignar
                if (selectedNumsPreasignar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 1) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedNumsPreasignar = true;
                    } else {
                        selectedNumsPreasignar = false;
                    }
                }
                //--- Activar / desactivar botón Recuperar
                if (selectedNumsRecuperar == true) {
                    if((n.getEsnCodigo().getEsnCodigo() == 3) && (operador.equals(n.getEmrCodigo().getEmrCodigo()))) {
                        selectedNumsRecuperar = true;
                    } else {
                        selectedNumsRecuperar = false;
                    }
                }
                //--- Activar / desactivar botón Reservar
                if (selectedNumsReservar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 1) {
                        selectedNumsReservar = true;
                    } else {
                        selectedNumsReservar = false;
                    }
                }
                //--- Activar / desactivar botón Liberar
                if (selectedNumsLiberar == true) {
                    if(n.getEsnCodigo().getEsnCodigo() == 4) {
                        selectedNumsLiberar = true;
                    } else {
                        selectedNumsLiberar = false;
                    }
                }
                
            }
        } else {
            selectedNumsCantidad = 0;
            selectedNumsPreasignar = false;
            selectedNumsRecuperar = false;
            selectedNumsReservar = false;
            selectedNumsLiberar = false;
        }
    }
    
    public String reservar() {
        int operacion;
        facade fachada = new facade();
        
        ArrayList vo = new ArrayList();
        int size = selectedNums.length;
        for (int i = 0; i < size; i++) {
            vo.add(selectedNums[i]);
        }
        
        operacion = fachada.reservarLiberarRecurso(vo,1);
        
        if(operacion == 1){
            buscar();
            selectedNumsAccion = false;
            return "Reserva de numeración exitosa";
        }else{
            return "Error en el bean de numeración";   
        }
    }
    
    public String liberar() {
        int operacion;
        facade fachada = new facade();
        
        ArrayList vo = new ArrayList();
        int size = selectedNums.length;
        for (int i = 0; i < size; i++) {
            vo.add(selectedNums[i]);
        }
        
        operacion = fachada.reservarLiberarRecurso(vo,0);
        
        if(operacion == 1){
            buscar();
            selectedNumsAccion = false;
            return "Liberación de numeración exitosa";
        }else{
         return "Error en el bean de numeración";   
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
        titulo = new HSSFRichTextString("NDC");
        header.getCell(0).setCellValue(titulo);
        header.getCell(0).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("INICIO");
        header.getCell(1).setCellValue(titulo);
        header.getCell(1).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("FIN");
        header.getCell(2).setCellValue(titulo);
        header.getCell(2).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("CANTIDAD");
        header.getCell(3).setCellValue(titulo);
        header.getCell(3).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("DEPARTAMENTO");
        header.getCell(4).setCellValue(titulo);
        header.getCell(4).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("MUNICIPIO");
        header.getCell(5).setCellValue(titulo);
        header.getCell(5).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("CLASE NUMERACIÓN");
        header.getCell(6).setCellValue(titulo);
        header.getCell(6).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("ESTADO");
        header.getCell(7).setCellValue(titulo);
        header.getCell(7).setCellStyle(cellStyle);
        titulo = new HSSFRichTextString("EMPRESA");
        header.getCell(8).setCellValue(titulo);
        header.getCell(8).setCellStyle(cellStyle);
        
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
            NuNumeracionVO se = (NuNumeracionVO)itr.next();
            HSSFRow fila = sheet.getRow(i);
            HSSFRichTextString texto = new HSSFRichTextString(se.getEsnCodigo().getEstNombre());
            fila.getCell(7).setCellValue(texto);
        }
        sheet.autoSizeColumn((short) 0);
        sheet.autoSizeColumn((short) 1);
        sheet.autoSizeColumn((short) 2);
        sheet.autoSizeColumn((short) 3);
        sheet.autoSizeColumn((short) 4);
        sheet.autoSizeColumn((short) 5);
        sheet.autoSizeColumn((short) 6);
        sheet.autoSizeColumn((short) 7);
        sheet.autoSizeColumn((short) 8);
    }
    
    public List<NuNumeracionVO> getNum() {
        return num;
    }

    public void setNum(List<NuNumeracionVO> num) {
        this.num = num;
    }

    public Collection<SelectItem> getListaNDC() {
        return listaNDC;
    }

    public void setListaNDC(Collection<SelectItem> listaNDC) {
        this.listaNDC = listaNDC;
    }

    public Collection<SelectItem> getListaTipoNdc() {
        return listaTipoNdc;
    }

    public void setListaTipoNDC(Collection<SelectItem> listaTipoNdc) {
        this.listaTipoNdc = listaTipoNdc;
    }
    
    public Collection<SelectItem> getListaOperador() {
        return listaOperador;
    }

    public void setListaOperador(Collection<SelectItem> listaOperador) {
        this.listaOperador = listaOperador;
    }

    public Collection<SelectItem> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(Collection<SelectItem> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public NdNdcVO getNdcVO() {
        return ndcVO;
    }

    public void setNdcVO(NdNdcVO ndcVO) {
        this.ndcVO = ndcVO;
    }

    public NtTipoNdc getTipoNdcVO() {
        return tipoNdcVO;
    }

    public void setTipoNdcVO(NtTipoNdc tipoNdcVO) {
        this.tipoNdcVO = tipoNdcVO;
    }

    public EmOperadorVO getOperadorVO() {
        return operadorVO;
    }

    public void setOperadorVO(EmOperadorVO operadorVO) {
        this.operadorVO = operadorVO;
    }

    public String getNumInicio() {
        return NumInicio;
    }

    public void setNumInicio(String NumInicio) {
        this.NumInicio = NumInicio;
    }

    public String getNumFin() {
        return NumFin;
    }

    public void setNumFin(String NumFin) {
        this.NumFin = NumFin;
    }

    public EsEstadoVO getEstadoVO() {
        return estadoVO;
    }

    public void setEstadoVO(EsEstadoVO estadoVO) {
        this.estadoVO = estadoVO;
    }

    public NuNumeracionVO getSelectedNum() {
        return selectedNum;
    }

    public void setSelectedNum(NuNumeracionVO selectedNum) {
        this.selectedNum = selectedNum;
    }

    public NuNumeracionVO[] getSelectedNums() {
        return selectedNums;
    }

    public void setSelectedNums(NuNumeracionVO[] selectedNums) {
        this.selectedNums = selectedNums;
    }

    public Integer getSelectedNumsCantidad() {
        return selectedNumsCantidad;
    }

    public void setSelectedNumsCantidad(Integer selectedNumsCantidad) {
        this.selectedNumsCantidad = selectedNumsCantidad;
    }

    public Boolean getSelectedNumsPreasignar() {
        return selectedNumsPreasignar;
    }

    public void setSelectedNumsPreasignar(Boolean selectedNumsPreasignar) {
        this.selectedNumsPreasignar = selectedNumsPreasignar;
    }

    public Boolean getSelectedNumsRecuperar() {
        return selectedNumsRecuperar;
    }

    public void setSelectedNumsRecuperar(Boolean selectedNumsRecuperar) {
        this.selectedNumsRecuperar = selectedNumsRecuperar;
    }

    public Boolean getSelectedNumsAccion() {
        return selectedNumsAccion;
    }

    public void setSelectedNumsAccion(Boolean selectedNumsAccion) {
        this.selectedNumsAccion = selectedNumsAccion;
    }

    public Boolean getSelectedNumsLiberar() {
        return selectedNumsLiberar;
    }

    public void setSelectedNumsLiberar(Boolean selectedNumsLiberar) {
        this.selectedNumsLiberar = selectedNumsLiberar;
    }

    public Boolean getSelectedNumsReservar() {
        return selectedNumsReservar;
    }

    public void setSelectedNumsReservar(Boolean selectedNumsReservar) {
        this.selectedNumsReservar = selectedNumsReservar;
    }
    
    public List<NuNumeracionVO> getDetalleSelectedNum() {
        return detalleSelectedNum;
    }

    public void setDetalleSelectedNum(List<NuNumeracionVO> detalleSelectedNum) {
        this.detalleSelectedNum = detalleSelectedNum;
    }

    public NuNumeracionVO[] getSelectedNumsDetalle() {
        return selectedNumsDetalle;
    }

    public void setSelectedNumsDetalle(NuNumeracionVO[] selectedNumsDetalle) {
        this.selectedNumsDetalle = selectedNumsDetalle;
    }
    
    public Collection<SelectItem> getListaDepartamento() {
        return listaDepartamento;
    }

    public void setListaDepartamento(Collection<SelectItem> listaDepartamento) {
        this.listaDepartamento = listaDepartamento;
    }

    public DepartamentosVO getDepartamentoVO() {
        return departamentoVO;
    }

    public void setDepartamentoVO(DepartamentosVO departamentoVO) {
        this.departamentoVO = departamentoVO;
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

    public List<TnTramiteNumeracionVO> getTramiteNumeracion() {
        return tramiteNumeracion;
    }

    public void setTramiteNumeracion(List<TnTramiteNumeracionVO> tramiteNumeracion) {
        this.tramiteNumeracion = tramiteNumeracion;
    }
    
}
