/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import helper.ConvertirListasHelper;
import inicio.UserBean;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import javax.faces.context.FacesContext;
import javax.faces.model.SelectItem;
import org.primefaces.context.RequestContext;
import org.primefaces.event.SelectEvent;
import org.primefaces.event.UnselectEvent;
import org.primefaces.model.LazyDataModel;
import org.primefaces.model.SortOrder;
import vo.EsEstadoVO;
import vo.MoModalidad1xyVO;
import vo.Nc1xyVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "Codigos1xyBean")
@ViewScoped
public class Codigos1xyBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private Collection<SelectItem> listaModalidad;
    private Collection<SelectItem> listaEstado;
    private Collection<SelectItem> listaServicios;
    private MoModalidad1xyVO modalidadVO = new MoModalidad1xyVO();
    private EsEstadoVO estadoVO = new EsEstadoVO();
    private String servicio;
    private String codigo1xy = "";
    private LazyDataModel<Nc1xyVO> lazyModel;
    private ArrayList matriz1xy = new ArrayList();
    private Nc1xyVO selected;
    private Boolean selectedEditar;
    private Boolean selectedsAccion;
    private Integer seleccionModalidad;
    private String seleccionServicio;
    private String seleccionObservaciones;
    private String mensaje;

    /** Creates a new instance of CodigosCortosBean */
    public Codigos1xyBean() {
        facade fachada = new facade();
        
        try {
            ConvertirListasHelper convertir = new ConvertirListasHelper();
            listaModalidad = convertir.createSelectItemsList(fachada.listaModalidad1xy(), null, "getMonCodigo", "getMotNombre", true, "");
            listaEstado = convertir.createSelectItemsList(fachada.listaEstado1xy(), null, "getEsnCodigo", "getEstNombre", true, "");
            cargarListaServicios();
        } catch (Exception e) {
            Logger.getAnonymousLogger().log(Level.SEVERE, "Error en el bean de Codigos 1xy", e);
        }
        
        lazyModel = new LazyDataModel<Nc1xyVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<Nc1xyVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<Nc1xyVO> lazyCodigos1xy = new ArrayList<Nc1xyVO>();
                facade fachada = new facade();
                lazyCodigos1xy = fachada.cargarCodigos1xy(first, pageSize, -1, -1, -1, "-1"); 
  
                return lazyCodigos1xy;
            }  
        };
        lazyModel.setRowCount(fachada.countCargarCodigos1xy(-1, -1, -1, "-1"));
        
        modalidadVO.setMonCodigo(-1);
        codigo1xy = "";
        estadoVO.setEsnCodigo(-1);
        servicio = "-1";
        
        
        
        
    }
    
    public String buscar() {
        facade fachada = new facade();

        final Integer codigo;
        
        if (codigo1xy.equals("")) {
            codigo = -1;
        } else {
            codigo = Integer.parseInt(codigo1xy);
        }

        lazyModel = new LazyDataModel<Nc1xyVO>() {  
  
            /** 
             * Dummy implementation of loading a certain segment of data. 
             * In a real application, this method should load data from a datasource 
             */  
            @Override
            public List<Nc1xyVO> load(int first, int pageSize, String sortField, SortOrder sortOrder, Map<String,String> filters) {  
                //logger.log(Level.INFO, "Loading the lazy car data between {0} and {1}", new Object[]{first, (first+pageSize)});  
  
                //Sorting and Filtering information are not used for demo purposes just random dummy data is returned  
                List<Nc1xyVO> lazyCodigos1xy = new ArrayList<Nc1xyVO>();
                facade fachada = new facade();
                lazyCodigos1xy = fachada.cargarCodigos1xy(first, pageSize, codigo, modalidadVO.getMonCodigo(), estadoVO.getEsnCodigo(), servicio); 
                
                return lazyCodigos1xy;  
            }  
        };
        lazyModel.setRowCount(fachada.countCargarCodigos1xy(codigo, modalidadVO.getMonCodigo(), estadoVO.getEsnCodigo(),servicio)); 
        
        
//        numeracion = fachada.cargarNumeracion(0, -1, operadorVO.getEmrCodigo(), ndcVO.getNdnCodigo(), inicio, fin);
//        num = agruparNumeracin(numeracion);
        return null;
    }
    
    public void cargarValores(){
        seleccionModalidad = selected.getMonCodigo().getMonCodigo();
        seleccionServicio = selected.getNctServicio();
        seleccionObservaciones = selected.getNctObservaciones();
    }
    
    private void cargarListaServicios() {
        facade fachada = new facade();
        List<String> servicios = fachada.listaServicios1xy();
        SelectItem servicio = new SelectItem();
        servicio.setLabel("");
        servicio.setValue("-1");
        listaServicios = new ArrayList();
        listaServicios.add(servicio);
        for(String s : servicios) {
            servicio = new SelectItem();
            servicio.setLabel(s);
            servicio.setValue(s);
            listaServicios.add(servicio);
        }
    }
    
    public void onRowSelect() {
        if (selected == null){
            selectedsAccion = false;
        } else {
            selectedsAccion = true;
        }
    }
    
    public void onRowUnselect() {
        if (selected == null){
            selectedsAccion = false;
        } else {
            selectedsAccion = true;
        }
    }
    
    public void detalleAccion(){
        if (selected != null){
            selectedEditar = true;
        } else {
            selectedEditar = false;
        }
        cargarValores();
    }
    
    public String editarCodigo1xy() {
        UserBean usuario = (UserBean) FacesContext.getCurrentInstance().getExternalContext().getSessionMap().get("UserBean");
        
        mensaje = "";
        
        if (usuario == null) {
            mensaje = "<br><b>Error al editar el código 1xy.</b><br><br>No eres un usuario autenticado<br><br>";
            return null;
        } else {
            if (!usuario.getPermisos().isCodigos1xy()) {
                mensaje = "<br><b>Error al editar el código 1xy.</b><br><br>No tienes permisos para realizar esta acción<br><br>";
                return null;
            }
        }
                
        
        
        if (seleccionModalidad == -1) {
            mensaje = "<br><b>Error al editar el código 1xy.</b><br><br>Debes seleccionar la modalidad<br><br>";
            return null;
        }

        boolean cerrarDialog = false;
        
        Nc1xyVO codigo = new Nc1xyVO();
        codigo = selected;
        
        //Modalidad
        MoModalidad1xyVO modalidad = new MoModalidad1xyVO();
        modalidad.setMonCodigo(seleccionModalidad);
        codigo.setMonCodigo(modalidad);
        
        //Estado
        EsEstadoVO estado = new EsEstadoVO();
        if (seleccionModalidad == 5) {
            estado.setEsnCodigo(4);
            codigo.setNctServicio("");
        } else {
            estado.setEsnCodigo(3);
            if (seleccionServicio.equals("")) {
                mensaje = "<br><b>Error al editar el código 1xy.</b><br><br>Debes ingresar el servicio<br><br>";
                return null;
            }
            codigo.setNctServicio(seleccionServicio);
        }
        codigo.setEsnCodigo(estado);
        codigo.setNctObservaciones(seleccionObservaciones);
        
        facade fachada = new facade();
        
        Boolean resultado = false;
        
        resultado = fachada.editarCodigo1xy(codigo);
        
        if(resultado) {
            mensaje = "<br><b>Código 1XY-"+selected.getNcn1xy()+" modificado correctamente.</b><br><br>";
        } else {
            mensaje = "<br><b>Error al modificar el código 1XY-"+selected.getNcn1xy()+" .</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
        }
        
        cerrarDialog = true;
        
        RequestContext context = RequestContext.getCurrentInstance();
        context.addCallbackParam("cerrarDialog", cerrarDialog);

        selectedsAccion = false;
        buscar();
        cargarListaServicios();
        
        return null;
    }
    
    public void matriz1xy() {
        matriz1xy = new ArrayList();
        facade fachada = new facade();
        List<Nc1xyVO> datos = new ArrayList<Nc1xyVO>();
        datos = fachada.cargarCodigos1xy(0, -1, -1, -1, -1, "-1");
        int x = 0;
        for(int i = 0; i < 10; i++){
            matriz1xy.add(new ArrayList());
            ((ArrayList)matriz1xy.get(i)).add(i);
            x = 0;
            for(int j = 0; j < 10; j++){
               ((ArrayList)matriz1xy.get(i)).add(datos.get(i + 10*x));
               x = x + 1;
            }
        }
    }
    
    public LazyDataModel<Nc1xyVO> getLazyModel() {
        return lazyModel;
    }

    public void setLazyModel(LazyDataModel<Nc1xyVO> lazyModel) {
        this.lazyModel = lazyModel;
    }

    public Collection<SelectItem> getListaEstado() {
        return listaEstado;
    }

    public void setListaEstado(Collection<SelectItem> listaEstado) {
        this.listaEstado = listaEstado;
    }

    public Collection<SelectItem> getListaModalidad() {
        return listaModalidad;
    }

    public void setListaModalidad(Collection<SelectItem> listaModalidad) {
        this.listaModalidad = listaModalidad;
    }

    public EsEstadoVO getEstadoVO() {
        return estadoVO;
    }

    public void setEstadoVO(EsEstadoVO estadoVO) {
        this.estadoVO = estadoVO;
    }

    public MoModalidad1xyVO getModalidadVO() {
        return modalidadVO;
    }

    public void setOperadorVO(MoModalidad1xyVO modalidadVO) {
        this.modalidadVO = modalidadVO;
    }

    public Nc1xyVO getSelected() {
        return selected;
    }
    
    public void setSelected(Nc1xyVO selected) {
        this.selected = selected;
    }

    public Boolean getSelectedsAccion() {
        return selectedsAccion;
    }

    public void setSelectedsAccion(Boolean selectedsAccion) {
        this.selectedsAccion = selectedsAccion;
    }

    public Boolean getSelectedEditar() {
        return selectedEditar;
    }

    public void setSelectedEditar(Boolean selectedEditar) {
        this.selectedEditar = selectedEditar;
    }

    public String getCodigo1xy() {
        return codigo1xy;
    }

    public void setCodigo1xy(String codigo1xy) {
        this.codigo1xy = codigo1xy;
    }

    public Integer getSeleccionModalidad() {
        return seleccionModalidad;
    }

    public void setSeleccionModalidad(Integer seleccionModalidad) {
        this.seleccionModalidad = seleccionModalidad;
    }

    public String getSeleccionObservaciones() {
        return seleccionObservaciones;
    }

    public void setSeleccionObservaciones(String seleccionObservaciones) {
        this.seleccionObservaciones = seleccionObservaciones;
    }

    public String getSeleccionServicio() {
        return seleccionServicio;
    }

    public void setSeleccionServicio(String seleccionServicio) {
        this.seleccionServicio = seleccionServicio;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public Collection<SelectItem> getListaServicios() {
        return listaServicios;
    }

    public void setListaServicios(Collection<SelectItem> listaServicios) {
        this.listaServicios = listaServicios;
    }

    public String getServicio() {
        return servicio;
    }

    public void setServicio(String servicio) {
        this.servicio = servicio;
    }

    public ArrayList getMatriz1xy() {
        return matriz1xy;
    }

    public void setMatriz1xy(ArrayList matriz1xy) {
        this.matriz1xy = matriz1xy;
    }

}
