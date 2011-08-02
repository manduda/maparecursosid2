/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package main;

import facade.facade;
import java.math.BigDecimal;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import vo.ClCodigosLdVO;
import vo.EmOperadorVO;
import vo.NuNumeracionVO;
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
       
        //----  USUARIO
        UsUsuariosVO usuario = fachada.iniciarSesion("MDURAN", "MIGUEL01");
        
        if (usuario != null){
            if (usuario.getUsnEstado() == 1){
                System.out.println("Usuario logueado como: " + usuario.getTunCodigo().getTutNombre());
            } else {
                System.out.println("Usuario " + usuario.getUsnCodigo().getLogin() + " está deshabilitado");
            }
        } else {
            System.out.println("Usuario o contraseña incorrectos");
        }
        //-------------------------------------
        /*Class itemClass = null;
        itemClass = usuario.getClass();
        System.out.println("Clase: " + itemClass.getName());*/
    }
}
