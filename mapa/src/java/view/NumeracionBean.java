/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import helper.ConvertirListasHelper;
import java.lang.reflect.InvocationTargetException;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import javax.faces.model.SelectItem;
import vo.EmOperadorVO;
import vo.NdNdcVO;
import vo.NuNumeracionVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean
@RequestScoped
public class NumeracionBean {

//    private List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();
    private List<NuNumeracionVO> num = null;//new ArrayList<NuNumeracionVO>();
    private Collection<SelectItem> listaNDC;
    private Collection<SelectItem> listaOperador;
    private NdNdcVO ndcVO;
    private EmOperadorVO operadorVO;
    
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
            listaOperador = convertir.createSelectItemsList(fachada.listaOperador(), null, null, "getEmtNombre", true, "");
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Numeración", e);
        }
          

        
    }

    public String buscar() {
        System.out.println("HOLA");
        List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();
        facade fachada = new facade();
        numeracion = fachada.ListaNumeracion();
        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public List<NuNumeracionVO> agruparNumeracin(List<NuNumeracionVO> numeracion){
        List<NuNumeracionVO> numeros = new ArrayList<NuNumeracionVO>();
        
        NuNumeracionVO numera = new NuNumeracionVO();
        NuNumeracionVO numeraF = new NuNumeracionVO();
        
        BigDecimal ndc = BigDecimal.ZERO;
        BigInteger inicio = BigInteger.ZERO;
        BigInteger fin = BigInteger.ZERO;
        String operador = "";
        
        BigDecimal ndcF = BigDecimal.ZERO;
        BigInteger inicioF = BigInteger.ZERO;
        BigInteger finF = BigInteger.ZERO;
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

     public NdNdcVO getNdcVO() {
        return ndcVO;
    }

    public void setNdcVO(NdNdcVO ndcVO) {
        this.ndcVO = ndcVO;
    }

    public Collection<SelectItem> getListaOperador() {
        return listaOperador;
    }

    public void setListaOperador(Collection<SelectItem> listaOperador) {
        this.listaOperador = listaOperador;
    }

    public EmOperadorVO getOperadorVO() {
        return operadorVO;
    }

    public void setOperadorVO(EmOperadorVO operadorVO) {
        this.operadorVO = operadorVO;
    }

}
