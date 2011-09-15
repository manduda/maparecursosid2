/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.EmOperador;
import java.text.DecimalFormat;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class EmOperadorDAO {
    public static List<EmOperador> cargarOperadores(EntityManager em){
        List<EmOperador> operadores = new ArrayList<EmOperador>();
        

        StringBuilder searchQuery = new StringBuilder(
                "SELECT * FROM ( "
                + "SELECT EM.SK_EMPRESA_CODE, EM.DESCRIPCION "
                + "FROM ERPWADM.EMPRESA_X_SERVICIO ES INNER JOIN SA.SK_EMPRESA EM ON ES.EMPRESA_CODE=EM.SK_EMPRESA_CODE "
                + "LEFT JOIN SA.ATRIBUTOS_EMPRESA AE ON EM.SK_EMPRESA_CODE=AE.SK_EMPRESA_CODE AND AE.TIPO_ATRIB_EMPRESA_CODE='C4' "
                + "WHERE ES.SERVICIO_CODE='02' "
                + "AND AE.VALOR IN ('ACTIVA','OPERATIVA','PREOPERATIVA') "
                /*+ "UNION "
                + "SELECT DISTINCT EM.SK_EMPRESA_CODE, EM.DESCRIPCION "
                + "FROM NU_NUMERACION NU INNER JOIN SA.SK_EMPRESA EM ON NU.SK_EMPRESA_CODE=EM.SK_EMPRESA_CODE "
                + "UNION "
                + "SELECT EM.SK_EMPRESA_CODE, EM.DESCRIPCION "
                + "FROM SA.SK_EMPRESA EM "
                + "WHERE EM.SK_EMPRESA_CODE = 'C0159C' "*/
                + ") ORDER BY DESCRIPCION");
        
        Query query = em.createNativeQuery(searchQuery.toString());

        List<Object[]> results = query.getResultList();
        
        for(int i=0; i < results.size(); i++){
            EmOperador operador = new EmOperador();
            String hex = "";
            String cadena = "";
            for(byte b : (byte[])results.get(i)[0]){
                cadena = Integer.toHexString(0xFF & b).toUpperCase();
                if(cadena.length() < 2){
                    cadena = "0"+cadena;
                }
                hex = hex + cadena;
            }
            operador.setEmrCodigo(hex);
            operador.setEmtNombre((String)results.get(i)[1]);
            operadores.add(operador);
        }
        
        return operadores;
    }
}
