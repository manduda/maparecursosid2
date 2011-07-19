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

/**
 *
 * @author miguel.duran
 */
public class main {
    public static void main(String[] args) {
        facade inicio = new facade();

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
        List<NuNumeracionVO> vo = new ArrayList<NuNumeracionVO>();
//        BigDecimal ndc = new BigDecimal("-1");
        vo = inicio.cargarNumeracion("-1",-1,30,31);
        for(int i=1; i < vo.size();i++) {
            BigDecimal codigo = vo.get(i).getNunCodigo();
            String operador = vo.get(i).getEmrCodigo().getEmtNombre();
            String estado = vo.get(i).getEsnCodigo().getEstNombre();
            String region = vo.get(i).getSkRegionCode().getSkRegionNombre();
            System.out.println(codigo+"-"+operador+"-"+estado+"-"+region);
	}
//        //-------------------------------------
//        facade fachada = new facade();
//       
//        List<EmOperadorVO> ope = fachada.listaOperador();
//        
//        System.out.println("hola:"+ope.size());
//        
//        for (int i = 0; i < ope.size(); i++){
//            System.out.println(ope.get(i).getEmtNombre());
//        }
        
    
    }
}