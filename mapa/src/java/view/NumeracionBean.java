/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
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
    
    public NumeracionBean() {
        String columna1 = "";
        facade fachada = new facade();
        numeracion = fachada.ListaNumeracion();
        int x = 0;
        for(int i = 0; i < 10; i++){
            num.add(new ArrayList());
            columna1 = numeracion.get(x).getNunInicio().toString().substring(0, 3);
            ((ArrayList)num.get(i)).add(columna1);
            for(int j = 0; j < 10; j++){
               ((ArrayList)num.get(i)).add(numeracion.get(x));
               x = x + 1;
            }
        }
        
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

}
