/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import helper.ConvertirListasHelper;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.bean.SessionScoped;
import javax.faces.model.SelectItem;
import org.primefaces.model.LazyDataModel;
import vo.EmOperadorVO;
import vo.EsEstadoVO;
import vo.NdNdcVO;
import vo.NuNumeracionVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "NumeracionBean")
@SessionScoped
public class NumeracionBean {

//    private List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();
    private List<NuNumeracionVO> num = null;//new ArrayList<NuNumeracionVO>();
    private Collection<SelectItem> listaNDC;
    private Collection<SelectItem> listaOperador;
    private Collection<SelectItem> listaEstado;
    private NdNdcVO ndcVO = new NdNdcVO();
    private EmOperadorVO operadorVO = new EmOperadorVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String NumInicio;
    private String NumFin;
    private LazyDataModel<NuNumeracionVO> lazyModel;
    
    public NumeracionBean() {
        facade fachada = new facade();
       
//        List<EmOperadorVO> ope = fachada.listaOperador();
//        System.out.println("hola:"+ope.size());
//        for (int i = 0; i < ope.size(); i++){
//            System.out.println(ope.get(i).getEmtNombre());
//        }
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaNDC = convertir.createSelectItemsList(fachada.listaNDC(), null, "getNdnCodigo", "getNdtNombre", true, "");
            listaOperador = convertir.createSelectItemsList(fachada.listaOperador(), "getEmrCodigo", null, "getEmtNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado(), null, "getEsnCodigo", "getEstNombre", true, "");
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
                numera = fachada.cargarNumeracion(first, pageSize, "-1", -1, -1, -1, -1); 
                lazyNumeracion = agruparNumeracin(numera);
  
                return lazyNumeracion;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarNumeracion("-1", -1, -1, -1, -1)); 
    }

    public LazyDataModel<NuNumeracionVO> getLazyModel() {  
        return lazyModel;  
    }
        
    public String buscar() {
        List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();
        facade fachada = new facade();
        System.out.println("operador:" + operadorVO.getEmrCodigo());
        System.out.println("ndc:" + ndcVO.getNdnCodigo());
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
                numera = fachada.cargarNumeracion(first, pageSize, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin, estadoVO.getEsnCodigo()); 
                lazyNumeracion = agruparNumeracin(numera);
                
                for(int i=0; i < numera.size();i++) {
                    BigDecimal codigo = numera.get(i).getNunCodigo();
                    String operador = numera.get(i).getEmrCodigo().getEmtNombre();
                    String estado = numera.get(i).getEsnCodigo().getEstNombre();
                    String region = numera.get(i).getSkRegionCode().getSkRegionNombre();
                    int inicio = numera.get(i).getNunInicio();
                    System.out.println(codigo+"-"+operador+"-"+estado+"-"+region+"-"+inicio);
                }
                
                return lazyNumeracion;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarNumeracion(operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin, estadoVO.getEsnCodigo())); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public List<NuNumeracionVO> agruparNumeracin(List<NuNumeracionVO> numeracion){
        List<NuNumeracionVO> numeros = new ArrayList<NuNumeracionVO>();
        
        NuNumeracionVO numera = new NuNumeracionVO();
        NuNumeracionVO numeraF = new NuNumeracionVO();
        
        int ndc = 0;
        Integer inicio = 0;
        Integer fin = 0;
        String operador = "";
        
        int ndcF = 0;
        Integer inicioF = 0;
        Integer finF = 0;
        String operadorF = "";
        
        int x = 0;

        for(int i = 0; i < numeracion.size(); i++){
            numeraF = numeracion.get(i);
            ndcF = numeraF.getNdnCodigo().getNdnCodigo();
            inicioF = numeraF.getNunInicio();
            finF = numeraF.getNunFin();
            operadorF = numeraF.getEmrCodigo().getEmtNombre();
            
            if ((ndc == ndcF)&&(operador.equals(operadorF))&&(inicio.toString().substring(0,4).equals(inicioF.toString().substring(0,4)))){
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
            
            
        }
        System.out.println("X es:"+x);
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

}
