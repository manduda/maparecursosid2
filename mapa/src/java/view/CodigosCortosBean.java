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
import vo.CcCodigosCortosVO;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.MdModalidadCcVO;

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
    private CcCodigosCortosVO selectedCodigoCorto;

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

    public CcCodigosCortosVO getSelectedCodigoCorto() {
        return selectedCodigoCorto;
    }

    public void setSelectedCodigoCorto(CcCodigosCortosVO selectedCodigoCorto) {
        this.selectedCodigoCorto = selectedCodigoCorto;
    }
    
}
