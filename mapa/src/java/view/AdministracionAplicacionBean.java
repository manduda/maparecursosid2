/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package view;

import facade.facade;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ViewScoped;
import vo.CmConfiguracionModulosVO;

/**
 *
 * @author miguel.duran
 */
@ManagedBean(name = "AdministracionAplicacionBean")
@ViewScoped
public class AdministracionAplicacionBean implements Serializable {
    private static final long serialVersionUID = 1L;
    
    private List<CmConfiguracionModulosVO> modulos = new ArrayList<CmConfiguracionModulosVO>();
    private CmConfiguracionModulosVO selectedModulo = new CmConfiguracionModulosVO();
    private char activar;
    private String mensajeAdminModulo = "";
    
    public AdministracionAplicacionBean() {
        facade fachada = new facade();
        modulos = fachada.listaModulos();
    }
    
    public String activarDesactivar() {
        if ((activar != 'S') && (activar != 'N')){
            mensajeAdminModulo = "<br><b>Error al agregar recurso al trámite.</b><br><br>Si el error persiste, por favor contacte al Aministrador<br><br>";
            return null;
        }
        
        facade fachada = new facade();
        
        CmConfiguracionModulosVO vo = selectedModulo;
        vo.setCmtActivo(activar);

        fachada.editarConfiguracionModulo(vo);
        
        if (activar == 'S') {
            mensajeAdminModulo = "Módulo activado correctamente";
        }
        else {
            mensajeAdminModulo = "Módulo desactivado correctamente";
        }
        modulos = fachada.listaModulos();
        return null;
    }

    public char getActivar() {
        return activar;
    }

    public void setActivar(char activar) {
        this.activar = activar;
    }

    public String getMensajeAdminModulo() {
        return mensajeAdminModulo;
    }

    public void setMensajeAdminModulo(String mensajeAdminModulo) {
        this.mensajeAdminModulo = mensajeAdminModulo;
    }

    public List<CmConfiguracionModulosVO> getModulos() {
        return modulos;
    }

    public void setModulos(List<CmConfiguracionModulosVO> modulos) {
        this.modulos = modulos;
    }

    public CmConfiguracionModulosVO getSelectedModulo() {
        return selectedModulo;
    }

    public void setSelectedModulo(CmConfiguracionModulosVO selectedModulo) {
        this.selectedModulo = selectedModulo;
    }
    
}
