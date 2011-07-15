/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.RequestScoped;
import org.primefaces.model.Cell;
import vo.NuNumeracionVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean
@RequestScoped
public class NumeracionBean {

    private List<NuNumeracionVO> numeracion = new ArrayList<NuNumeracionVO>();
    private Cell selectedCell;
    private ArrayList num = new ArrayList();
    private List<NuNumeracionVO> nume = new ArrayList<NuNumeracionVO>();
    
    public NumeracionBean() {
        String columna1 = "";
        facade fachada = new facade();
        numeracion = fachada.ListaNumeracion();
        int x = 0;
        System.out.println(numeracion.size());
        for(int i = 0; i < numeracion.size()/10; i++){
            num.add(new ArrayList());
            columna1 = numeracion.get(x).getNunInicio().toString().substring(0, 3);
            ((ArrayList)num.get(i)).add(columna1);
            for(int j = 0; j < 10; j++){
               ((ArrayList)num.get(i)).add(numeracion.get(x));
               x = x + 1;
            }
        }
        nume = agruparNumeracin(numeracion);
        
        
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
        
        for(int i = 0; i < numeros.size(); i++){
            System.out.println(numeros.get(i).getNdnCodigo().getNdtNombre());
            System.out.println(numeros.get(i).getEmrCodigo().getEmtNombre());
            System.out.println(numeros.get(i).getNunInicio() + "-" + numeros.get(i).getNunFin());
            System.out.println("---");
        }        
        
        return numeros;
    }

    public List<NuNumeracionVO> getNumeracion() {
        return numeracion;
    }

    public void setNumeracion(List<NuNumeracionVO> numeracion) {
        this.numeracion = numeracion;
    }

    public Cell getSelectedCell() {
        return selectedCell;
    }

    public void setSelectedCell(Cell selectedCell) {
        this.selectedCell = selectedCell;
    }

    public ArrayList getNum() {
        return num;
    }

    public void setNum(ArrayList num) {
        this.num = num;
    }

    public List<NuNumeracionVO> getNume() {
        return nume;
    }

    public void setNume(List<NuNumeracionVO> nume) {
        this.nume = nume;
    }

}
