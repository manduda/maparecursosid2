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
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.ReRegionVO;
import vo.SeSenalizacionVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "SenalizacionBean")
@SessionScoped
public class SenalizacionBean {
    private List<SeSenalizacionVO> sen = null;//new ArrayList<NuNumeracionVO>();
    private Collection<SelectItem> listaRegionSenalizacion;
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private ReRegionVO regionSenalizacionVO = new ReRegionVO();
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String zonaSenalizacion;
    private String psSenalizacion;
    private LazyDataModel<SeSenalizacionVO> lazyModel;
    private SeSenalizacionVO selectedSen;
    
    
    /** Creates a new instance of SenalizacionBean */
    public SenalizacionBean() {
        facade fachada = new facade();
       
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaRegionSenalizacion = convertir.createSelectItemsList(fachada.listaRegionSenalizacion(), null, "getRenCodigo", "getRetNombre", true, "");
            listaOperador = convertir.createSelectItemsList(fachada.listaOperadorSenalizacion(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Señalización", e);
        }
          
        lazyModel = new LazyDataModel<SeSenalizacionVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<SeSenalizacionVO> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<SeSenalizacionVO> lazySenalizacion = new ArrayList<SeSenalizacionVO>();
                facade fachada = new facade();
                lazySenalizacion = fachada.cargarSenalizacion(first, pageSize, "-1", -1, -1, -1, -1); 
  
                return lazySenalizacion;
            }  
        };
        lazyModel.setRowCount(fachada.countCargarSenalizacion("-1", -1, -1, -1, -1)); 
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
        System.out.println("Esrtado:"+estadoVO.getEsnCodigo());

        lazyModel = new LazyDataModel<SeSenalizacionVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<SeSenalizacionVO> load(int first, int pageSize, String sortField, boolean sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<SeSenalizacionVO> lazySenalizacion = new ArrayList<SeSenalizacionVO>();
                facade fachada = new facade();
                lazySenalizacion = fachada.cargarSenalizacion(first, pageSize, operadorVO.getEmrCodigo(), regionSenalizacionVO.getRenCodigo(), zona, ps, estadoVO.getEsnCodigo()); 
                
                return lazySenalizacion;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarSenalizacion(operadorVO.getEmrCodigo(), regionSenalizacionVO.getRenCodigo(), zona, ps, estadoVO.getEsnCodigo())); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }

    public String recuperar() {
        selectedSen = null;
        return null;
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
    }
    
}
