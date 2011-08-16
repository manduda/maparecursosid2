/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import daos.MunicipiosDAO;
import entities.Municipios;
import facade.facade;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Date;
import java.util.List;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.EtEstadoTramiteVO;
import vo.GtGestionTramiteVO;
import vo.MunicipiosVO;
import vo.NuNumeracionVO;
import vo.SeSenalizacionVO;
import vo.TrTramitesVO;
import vo.UsUsuariosVO;

/**
 *
 * @author miguel.duran
 */
public class main {
    public static void main(String[] args) {
        facade fachada = new facade();

        /*//----  CODIGOS LD
        List<ClCodigosLdVO> vo = new ArrayList<ClCodigosLdVO>();
        vo = inicio.ListaCodigosLd();
        for(int i=1; i < vo.size();i++) {
            BigInteger codigo = vo.get(i).getClnCodigoLd();
            String operador = vo.get(i).getEmrCodigo().getEmtNombre();
            String estado = vo.get(i).getEsnCodigo().getEstNombre();
            System.out.println(codigo+"-"+operador+"-"+estado);
	}
        *///-------------------------------------
 
        //----  NUMERACION
        /*List<NuNumeracionVO> vo = new ArrayList<NuNumeracionVO>();
        int cantidad = 0;
        vo = fachada.cargarNumeracion(0, 20, "0163",-1,-1,-1,3);
        cantidad = fachada.countCargarNumeracion("0163",-1,-1,-1,3);
        System.out.println("Cantidad:"+cantidad);
        for(int i=0; i < vo.size();i++) {
            BigDecimal codigo = vo.get(i).getNunCodigo();
            String operador = vo.get(i).getEmrCodigo().getEmtNombre();
            String estado = vo.get(i).getEsnCodigo().getEstNombre();
            String region = vo.get(i).getSkRegionCode().getSkRegionNombre();
            int inicio = vo.get(i).getNunInicio();
            System.out.println(codigo+"-"+operador+"-"+estado+"-"+region+"-"+inicio);
	}*/
        //-------------------------------------
       
        //----  SEÑALIZACION
        /*List<SeSenalizacionVO> vo = new ArrayList<SeSenalizacionVO>();
        int cantidad = 0;
        vo = fachada.cargarSenalizacion(0, -1, "-1",-1,-1,-1,1);
        cantidad = fachada.countCargarSenalizacion("-1",-1,-1,-1,1);
        System.out.println("Cantidad:"+cantidad);
        for(int i=0; i < vo.size();i++) {
            int codigo = vo.get(i).getSenCodigo();
            String operador = vo.get(i).getEmrCodigo().getEmtNombre();
            String estado = vo.get(i).getEsnCodigo().getEstNombre();
            String municipio = vo.get(i).getCodigoMunicipio().getNombreMunicipio();
            int regionSenalizacion = vo.get(i).getRenCodigo().getRenCodigo();
            int zona = vo.get(i).getSenZona();
            int ps = vo.get(i).getSenPs();
            System.out.println(codigo+"-"+operador+"-"+estado+"-"+municipio+"-"+regionSenalizacion+"-"+zona+"-"+ps);
	}*/
        //-------------------------------------
        /*List<MunicipiosVO> vo = new ArrayList<MunicipiosVO>();
        vo = fachada.listaMunicipios();
        for(int i=0; i < vo.size();i++) {
            String codigo = vo.get(i).getCodigoMunicipio();
            String municipio = vo.get(i).getNombreMunicipio();
            String departamento = vo.get(i).getCodigoDepartamento().getNombreDepartamento();
            System.out.println(codigo+"-"+municipio+"-"+departamento);
	}*/
        
        //----  USUARIO
        /*UsUsuariosVO usuario = fachada.iniciarSesion("MDURAN", "MIGUEL01");
        
        if (usuario != null){
            if (usuario.getUsnEstado() == 1){
                System.out.println("Usuario logueado como: " + usuario.getTunCodigo().getTutNombre());
            } else {
                System.out.println("Usuario " + usuario.getCodigoSIUST().getLogin() + " está deshabilitado");
            }
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }*/
        //-------------------------------------
        /*Class itemClass = null;
        itemClass = usuario.getClass();
        System.out.println("Clase: " + itemClass.getName());*/
        
        //---- GET TRAMITES
        /*List<TrTramitesVO> vo = new ArrayList<TrTramitesVO>();
        vo = fachada.cargarTramites(6, 1);
        for (TrTramitesVO t : vo) {
            int codigo = t.getTrnCodigo();
            String estado = t.getEtnCodigo().getEttNombre();
            String operador = t.getEmrCodigo().getEmtNombre();
            String usuario = t.getUsnCodigo().getCodigoSIUST().getLogin();
            Date fecha = t.getTrfFecha();
            System.out.println("* * * * * *");
            System.out.println(codigo+"-"+estado+"-"+operador+"-"+usuario+"-"+fecha);
            System.out.println("- - - - - -");
            Collection<GtGestionTramiteVO> gestionVO = t.getGtGetionTramiteCollection();
            for (GtGestionTramiteVO gt : gestionVO) {
                int codigoGT = gt.getGtnCodigo();
                String usuarioGT = gt.getUsnCodigo().getCodigoSIUST().getLogin();
                String estadoGT = gt.getEtnCodigo().getEttNombre();
                Date fechaGT = gt.getGtfFecha();
                System.out.println(codigoGT+"-"+estadoGT+"-"+usuarioGT+"-"+fechaGT);
            }
        }*/
        //-------------------------------------
        
        //---- CREAR TRAMITES
        TrTramitesVO vo = new TrTramitesVO();
        
        EmOperadorVO operador = new EmOperadorVO();
        operador.setEmrCodigo("01");
        
        EtEstadoTramiteVO estado = new EtEstadoTramiteVO();
        estado.setEtnCodigo(1);
        
        UsUsuariosVO usuario = new UsUsuariosVO();
        usuario.setUsnCodigo(1);
        
        Date fecha = new Date();
        
        GtGestionTramiteVO gestion = new GtGestionTramiteVO();
        gestion.setUsnCodigo(usuario);
        gestion.setGtfFecha(fecha);
        
        vo.setEmrCodigo(operador);
        vo.setEtnCodigo(estado);
        vo.setUsnCodigo(usuario);
        vo.setTrfFecha(fecha);
        
        boolean resultado = fachada.crearTramite(vo);

        System.out.println("Resultado: "+resultado);
        //-------------------------------------
    }
}
