/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package daos;

import entities.Departamentos;
import entities.EmOperador;
import entities.EsEstado;
import entities.Municipios;
import entities.NdNdc;
import entities.NtTipoNdc;
import entities.NuNumeracion;
import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import javax.persistence.EntityManager;
import javax.persistence.Query;

/**
 *
 * @author miguel.duran
 */
public class NuNumeracionDAO {
    public static void persist(NuNumeracion entity, EntityManager em){
        em.persist(entity);
    }

    public static void delete(NuNumeracion entity, EntityManager em){
        em.remove(em.merge(entity));
    }

    public static void merge(NuNumeracion entity, EntityManager em){
        em.merge(entity);
    }

    public static NuNumeracion findbyId(int nunCodigo, EntityManager em){
        return em.find(NuNumeracion.class, nunCodigo);
    }
    
    public static List<NuNumeracion> getListRango(int ndc, int inicio, int fin, EntityManager em){
        Query query = em.createQuery("SELECT n FROM NuNumeracion n "
                + "WHERE n.ndnCodigo.ndnCodigo = ?1 "
                + "AND n.nunInicio >= ?2 AND n.nunFin <= ?3 "
                + "ORDER BY n.nunInicio ASC");
        
        query.setParameter(1, ndc);
        query.setParameter(2, inicio);
        query.setParameter(3, fin);
        
        return query.getResultList();
    }
    
    public static Long getMaxId(EntityManager em){
        Query query = em.createQuery("SELECT MAX(e.id) FROM NuNumeracion e");
        Long en = (Long)query.getSingleResult();
        if (en == null){
            en = 0L;
        }
        return en;
    }
    
    public static List<EmOperador> getListOperadores(EntityManager em){
        //Query query = em.createQuery("SELECT DISTINCT e FROM EmOperador e JOIN e.nuNumeracionCollection n ORDER BY e.emtNombre ASC");
        //return query.getResultList();
        
        List<EmOperador> operadores = new ArrayList<EmOperador>();
        

        StringBuilder searchQuery = new StringBuilder(
                "SELECT N.SK_EMPRESA_CODE, "
                + "       (SELECT E.DESCRIPCION FROM SA.SK_EMPRESA E WHERE N.SK_EMPRESA_CODE = E.SK_EMPRESA_CODE) "
                + "FROM (SELECT DISTINCT SK_EMPRESA_CODE "
                + "      FROM NU_NUMERACION N) N "
                + "ORDER BY (SELECT E.DESCRIPCION FROM SA.SK_EMPRESA E WHERE N.SK_EMPRESA_CODE = E.SK_EMPRESA_CODE) ASC");
        
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
    
    public static List<NuNumeracion> cargarNumeracion(int first, int max, String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento, EntityManager em){
        List<NuNumeracion> numeracion = new ArrayList<NuNumeracion>();
        
        StringBuilder searchQuery1 = new StringBuilder(
                "select min(min_inicio), max(max_inicio), ndc "
                + "from "
                + "( "
                + "  SELECT min_inicio, max_inicio, ndc "
                + "  FROM ( "
                + "    SELECT a.*, ROWNUM rnum "
                + "    from ( "
                + "      with get_data as "
                + "        ( "
                + "          SELECT n.ndn_codigo, n.nun_inicio, floor(n.nun_inicio/1000) bloque, n.sk_empresa_code, n.sk_region_code, n.esn_codigo, "
                + "                 ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) - ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.sk_empresa_code, n.sk_region_code, n.esn_codigo, n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) grp "
                + "          from nu_numeracion n "
                + "        where 1=1 ");
/*                + "SELECT RPAD(MIN(NUM),7,0),RPAD(MAX(NUM),7,9), NDN_CODIGO "
                + "FROM ( "
                + "      SELECT * "
                + "        FROM ( SELECT a.*, ROWNUM rnum "
                + "            from ( "
                + "              SELECT "
                + "              nn.NUM, "
                + "              nn.SK_REGION_CODE, "
                + "              nn.SK_EMPRESA_CODE, "
                + "              nn.ESN_CODIGO, "
                + "              nn.NDN_CODIGO, "
                + "              nn.NDC "
                + "              FROM ( "
                + "                SELECT n.*, "
                + "                SUBSTR(LPAD(n.NUN_INICIO,7,0),1,4) NUM,"
                + "                (SELECT nd.NDT_NOMBRE FROM ND_NDC nd WHERE n.NDN_CODIGO = nd.NDN_CODIGO) NDC"
                + "                FROM NU_NUMERACION n "
                + "                WHERE 1=1 ");*/
      
        StringBuilder searchQuery = new StringBuilder(
                "SELECT n FROM NuNumeracion n " +
                "WHERE 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery1.append("AND n.SK_EMPRESA_CODE = ?1 ");
            searchQuery.append("AND n.emrCodigo.emrCodigo = ?1 ");
        }
        if(!ndc.equals("-1")) {
            searchQuery1.append("AND n.NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NDT_NOMBRE = ?2) ");
            searchQuery.append("AND n.ndnCodigo.ndtNombre = ?2 ");
        }
        if((tipoNdc != -1) && (!ndc.equals("-1"))) {
            searchQuery1.append("AND n.NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NTN_CODIGO = ?3) ");
            searchQuery.append("AND n.ndnCodigo.ntnCodigo.ntnCodigo = ?3 ");
        }
        if(inicio != -1) {
            String numero = String.valueOf(inicio);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            inicio = Integer.parseInt(numero);
            searchQuery1.append("AND n.NUN_INICIO >= ?4 ");
            searchQuery.append("AND n.nunInicio >= ?4 ");
        }
        if(fin != -1) {
            String numero = String.valueOf(fin);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            fin = Integer.parseInt(numero);
            searchQuery1.append("AND n.NUN_FIN <= ?5 ");
            searchQuery.append("AND n.nunFin <= ?5 ");
        }
        if(estado != -1) {
            searchQuery1.append("AND n.ESN_CODIGO = ?6 ");
            searchQuery.append("AND n.esnCodigo.esnCodigo = ?6 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery1.append("AND n.SK_REGION_CODE = ?7 ");
            searchQuery.append("AND n.codigoMunicipio.codigoMunicipio = ?7 ");
        }
        if(!departamento.equals("-1")) {
            searchQuery1.append("AND n.SK_REGION_CODE IN (SELECT m.CODIGO_MUNICIPIO FROM SA.MUNICIPIOS m WHERE m.CODIGO_DEPARTAMENTO = ?8) ");
            searchQuery.append("AND n.codigoMunicipio.codigoDepartamento.codigoDepartamento = ?8 ");
        }
        
            searchQuery1.append("), "
                    + "      sql_data as "
                    + "      ( "
                    + "        select a.bloque,a.ndn_codigo,a.nun_inicio, "
                    + "               min(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) min_inicio, "
                    + "               max(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) max_inicio "
                    + "        from get_data a "
                    + "      ) "
                    + "      select distinct "
                    + "             bloque, "
                    + "             ndn_codigo ndc, "
                    + "             min_inicio, "
                    + "             max_inicio "
                    + "      from sql_data "
                    + "      order by ndn_codigo,min_inicio "
                    + "    ) a "
                    + "    WHERE ROWNUM <= (?10) "
                    + "  ) "
                    + "  WHERE rnum  >= ((?9) - 1)+1 "
                    + ") group by ndc ");
/*                + "    )  nn  "
                + "    GROUP BY nn.NUM,nn.SK_REGION_CODE,nn.SK_EMPRESA_CODE,nn.ESN_CODIGO,nn.NDN_CODIGO,nn.NDC "
                + "    ORDER BY NDC,MIN(NUN_INICIO) "
                + "  ) a "
                + "  WHERE ROWNUM <= (?10) "
                + ") "
                + "WHERE rnum  >= ((?9) - 1)+1 "
                + ")"
                + "GROUP BY NDN_CODIGO ");*/
        
        Query query1 = em.createNativeQuery(searchQuery1.toString());

        if(!operador.equals("-1")) {
            query1.setParameter(1, operador);
        }
        if(!ndc.equals("-1")) {
            query1.setParameter(2, ndc);
        }
        if((tipoNdc != -1) && (!ndc.equals("-1"))) {
            query1.setParameter(3, tipoNdc);
        }
        if(inicio != -1) {
            query1.setParameter(4, inicio);
        }
        if(fin != -1) {
            query1.setParameter(5, fin);
        }
        if(estado != -1) {
            query1.setParameter(6, estado);
        }
        if(!municipio.equals("-1")) {
            query1.setParameter(7, municipio);
        }
        if(!departamento.equals("-1")) {
            query1.setParameter(8, departamento);
        }
        query1.setParameter(9, first + 1);
        query1.setParameter(10, first + max);
        
        List results = query1.getResultList();
//                getResultList();
        
        if (results != null) {
            searchQuery.append("AND (1=0 ");
            for(int i=0; i<results.size();i++){
                searchQuery.append("OR (n.nunInicio >= ?").append(10+3*i)
                    .append(" AND n.nunInicio <= ?").append(11+3*i)
                    .append(" AND n.ndnCodigo.ndnCodigo = ?").append(12+3*i).append(" ) ");
            }
            searchQuery.append(" ) ");
        }
        
        
        searchQuery.append("ORDER BY n.ndnCodigo.ndtNombre, n.nunInicio ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(!ndc.equals("-1")) {
            query.setParameter(2, ndc);
        }
        if((tipoNdc != -1) && (!ndc.equals("-1"))) {
            query.setParameter(3, tipoNdc);
        }
        if(inicio != -1) {
            query.setParameter(4, inicio);
        }
        if(fin != -1) {
            query.setParameter(5, fin);
        }
        if(estado != -1) {
            query.setParameter(6, estado);
        }
        if(!municipio.equals("-1")) {
            query.setParameter(7, municipio);
        }
        if(!departamento.equals("-1")) {
            query.setParameter(8, departamento);
        }
        
        if (results != null){
            for(int i=0; i<results.size();i++){
                Object[] value = (Object [])results.get(i);
                Integer a = Integer.valueOf(value[0].toString());
                Integer b = Integer.valueOf(value[1].toString());
                Integer c = Integer.valueOf(value[2].toString());
                query.setParameter(10+3*i, a);
                query.setParameter(11+3*i, b);
                query.setParameter(12+3*i, c);
            }
        }
        
        
        /*query.setFirstResult(first);
        if(max != -1) {
            query.setMaxResults(max);
        }*/
        numeracion = query.getResultList();        
        return numeracion;
    }
    
    public static int countCargarNumeracion(String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento, EntityManager em){

        StringBuilder searchQuery = new StringBuilder(
                "SELECT COUNT(*) FROM ( "
                + "      with "
                + "      get_data as "
                + "      ( "
                + "        SELECT n.ndn_codigo, n.nun_inicio, floor(n.nun_inicio/1000) bloque, n.sk_empresa_code, n.sk_region_code, n.esn_codigo, "
                + "               ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) - ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.sk_empresa_code, n.sk_region_code, n.esn_codigo, n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) grp "
                + "        from nu_numeracion n "
                + "        where 1=1 ");

        if(!operador.equals("-1")) {
            searchQuery.append("AND n.SK_EMPRESA_CODE = ?1 ");
        }
        if(!ndc.equals("-1")) {
            searchQuery.append("AND n.NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NDT_NOMBRE = ?2) ");
        }
        if((tipoNdc != -1) && (!ndc.equals("-1"))) {
            searchQuery.append("AND n.NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NTN_CODIGO = ?3) ");
        }
        if(inicio != -1) {
            String numero = String.valueOf(inicio);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            inicio = Integer.parseInt(numero);
            searchQuery.append("AND n.NUN_INICIO >= ?4 ");
        }
        if(fin != -1) {
            String numero = String.valueOf(fin);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            fin = Integer.parseInt(numero);
            searchQuery.append("AND n.NUN_FIN <= ?5 ");
        }
        if(estado != -1) {
            searchQuery.append("AND n.ESN_CODIGO = ?6 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery.append("AND n.SK_REGION_CODE = ?7 ");
        }
        if(!departamento.equals("-1")) {
            searchQuery.append("AND n.SK_REGION_CODE in (SELECT m.CODIGO_MUNICIPIO FROM SA.MUNICIPIOS m WHERE m.CODIGO_DEPARTAMENTO = ?8) ");
        }
        
        searchQuery.append("), "
                    + "      sql_data as "
                    + "      ( "
                    + "      select a.bloque,a.ndn_codigo,a.nun_inicio, "
                    + "             min(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) min_inicio, "
                    + "             max(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) max_inicio "
                    + "      from get_data a "
                    + "      ) "
                    + "      select distinct "
                    + "             bloque, "
                    + "             ndn_codigo ndc, "
                    + "             min_inicio, "
                    + "             max_inicio "
                    + "      from sql_data "
                    + "      order by ndn_codigo,min_inicio "
                    + "    ) a ");
        
        Query query = em.createNativeQuery(searchQuery.toString());

        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(!ndc.equals("-1")) {
            query.setParameter(2, ndc);
        }
        if((tipoNdc != -1) && (!ndc.equals("-1"))) {
            query.setParameter(3, tipoNdc);
        }
        if(inicio != -1) {
            query.setParameter(4, inicio);
        }
        if(fin != -1) {
            query.setParameter(5, fin);
        }
        if(estado != -1) {
            query.setParameter(6, estado);
        }
        if(!municipio.equals("-1")) {
            query.setParameter(7, municipio);
        }
        if(!departamento.equals("-1")) {
            query.setParameter(8, departamento);
        }
        Number cResults = (Number) query.getSingleResult();
        return cResults.intValue();
    }
    
    public static List<NuNumeracion> cargarNumeracionBloque(String ndc, int inicio, int fin, EntityManager em){
        List<NuNumeracion> numeracion = new ArrayList<NuNumeracion>();
        
        StringBuilder searchQuery = new StringBuilder(
                "SELECT n FROM NuNumeracion n " +
                "WHERE 1=1 ");

        if(!ndc.equals("-1")) {
            searchQuery.append("AND n.ndnCodigo.ndtNombre = ?1 ");
        }
        if(inicio != -1) {
            String numero = String.valueOf(inicio);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            inicio = Integer.parseInt(numero);
            searchQuery.append("AND n.nunInicio >= ?2 ");
        }
        if(fin != -1) {
            String numero = String.valueOf(fin);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            fin = Integer.parseInt(numero);
            searchQuery.append("AND n.nunFin <= ?3 ");
        }

        searchQuery.append("ORDER BY n.ndnCodigo.ndnCodigo, n.nunInicio ASC");
        
        Query query = em.createQuery(searchQuery.toString());
        
        if(!ndc.equals("-1")) {
            query.setParameter(1, ndc);
        }
        if(inicio != -1) {
            query.setParameter(2, inicio);
        }
        if(fin != -1) {
            query.setParameter(3, fin);
        }
        
        numeracion = query.getResultList();        
        return numeracion;
    }
    
    public static List<NuNumeracion> cargarNumeracionAgrupada(String ndc, int inicio, int fin, EntityManager em){
        List<NuNumeracion> numeracion = new ArrayList<NuNumeracion>();
        StringBuilder searchQuery = new StringBuilder(
                "select a.min_inicio inicio, "
                + "       a.max_inicio + 99 fin, "
                + "       b.*, "
                + "       rawtohex(c.sk_empresa_code), "
                + "       c.descripcion, "
                + "       rawtohex(f.CODIGO_MUNICIPIO), "
                + "       f.NOMBRE_MUNICIPIO, "
                + "       rawtohex(f.CODIGO_DEPARTAMENTO), "
                + "       f.NOMBRE_DEPARTAMENTO , "
                + "       g.* "
                + "from "
                + "( "
                + "with get_data as "
                + "  ( "
                + "  SELECT n.ndn_codigo, n.nun_inicio, floor(n.nun_inicio/1000) bloque, n.sk_empresa_code, n.sk_region_code, n.esn_codigo, "
                + "         ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) - ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.sk_empresa_code, n.sk_region_code, n.esn_codigo, n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) grp "
                + "  from nu_numeracion n "
                + "  where 1=1 "
                + "  AND n.NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NDT_NOMBRE = ?1) "
                + "  AND n.NUN_INICIO >= ?2 "
                + "  AND n.NUN_FIN <= ?3 "
                + "  ), "
                + "sql_data as "
                + "  ( "
                + "  select a.ndn_codigo,a.nun_inicio, a.sk_empresa_code, a.sk_region_code, a.esn_codigo, "
                + "         min(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) min_inicio, "
                + "         max(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) max_inicio "
                + "  from get_data a "
                + "  ) "
                + "select distinct "
                + "       ndn_codigo ndc, "
                + "       min_inicio, "
                + "       max_inicio, "
                + "       sk_empresa_code, "
                + "       sk_region_code, "
                + "       esn_codigo "
                + "from sql_data "
                + "order by ndn_codigo,min_inicio "
                + ") a "
                + "join nd_ndc b on (a.ndc = b.ndn_codigo) "
                + "join sa.sk_empresa c on (a.sk_empresa_code = c.sk_empresa_code) "
                + "join (select d.CODIGO_MUNICIPIO, d.NOMBRE_MUNICIPIO, d.CODIGO_DEPARTAMENTO, e.NOMBRE_DEPARTAMENTO "
                + "      from sa.municipios d "
                + "      join sa.departamentos e "
                + "      on (d.CODIGO_DEPARTAMENTO = e.CODIGO_DEPARTAMENTO)) f on (a.sk_region_code = f.CODIGO_MUNICIPIO) "
                + "join es_estado g on (a.esn_codigo = g.esn_codigo) "
                + "order by b.ndt_nombre, a.min_inicio");
        
        Query query = em.createNativeQuery(searchQuery.toString());
        
        query.setParameter(1, ndc);
        query.setParameter(2, inicio);
        query.setParameter(3, fin);
        
        List<Object> results = query.getResultList();
        
        if (results != null){
            int i = 0;
            for (Object oRow : results) {
                Object[] value = (Object[]) oRow;
                Integer inicioF = Integer.valueOf(value[0].toString());
                Integer finF = Integer.valueOf(value[1].toString());

                //NDC
                NdNdc ndcF = new NdNdc();
                ndcF.setNdnCodigo(Integer.valueOf(value[2].toString()));
                ndcF.setNdtNombre(value[3].toString());
                NtTipoNdc tipoNdc = new NtTipoNdc();
                tipoNdc.setNtnCodigo(Integer.valueOf(value[4].toString()));
                ndcF.setNtnCodigo(tipoNdc);

                //EMPRESA
                EmOperador empresaF = new EmOperador();
                empresaF.setEmrCodigo(value[5].toString());
                empresaF.setEmtNombre(value[6].toString());

                //REGION
                Municipios municipioF = new Municipios();
                municipioF.setCodigoMunicipio((String)(value[7]));
                municipioF.setNombreMunicipio(value[8].toString());
                Departamentos departamentoF = new Departamentos();
                departamentoF.setCodigoDepartamento((String)value[9]);
                departamentoF.setNombreDepartamento(value[10].toString());
                municipioF.setCodigoDepartamento(departamentoF);

                //ESTADO
                EsEstado estadoF = new EsEstado();
                estadoF.setEsnCodigo(Integer.valueOf(value[11].toString()));
                estadoF.setEstNombre((String)value[12]);

                NuNumeracion num = new NuNumeracion();
                num.setNunCodigo(i);
                num.setNdnCodigo(ndcF);
                num.setNunInicio(inicioF);
                num.setNunFin(finF);
                num.setEmrCodigo(empresaF);
                num.setCodigoMunicipio(municipioF);
                num.setEsnCodigo(estadoF);

                numeracion.add(num);
                i++;

            }
        }
        
        return numeracion;
    }
    
    public static void transferirNumeracion(String operadorOrigen, String operadorDestino, EntityManager em){

        String searchQuery = "UPDATE NU_NUMERACION SET SK_EMPRESA_CODE = ?1 WHERE SK_EMPRESA_CODE = ?2";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, operadorDestino);
        query.setParameter(2, operadorOrigen);
        
        query.executeUpdate();
        
    }
    
    public static void updateNumeracion(NuNumeracion entity, EntityManager em){

        String searchQuery = "UPDATE NU_NUMERACION SET "
                + "SK_REGION_CODE = ?1, "
                + "SK_EMPRESA_CODE = ?2, "
                + "ESN_CODIGO = ?3, "
                + "NUT_OBSERVACIONES = ?4 "
                + "WHERE NDN_CODIGO = ?5 "
                + "AND NUN_INICIO >= ?6 "
                + "AND NUN_FIN <= ?7";
                
        Query query = em.createNativeQuery(searchQuery);

        query.setParameter(1, entity.getCodigoMunicipio().getCodigoMunicipio());
        query.setParameter(2, entity.getEmrCodigo().getEmrCodigo());
        query.setParameter(3, entity.getEsnCodigo().getEsnCodigo());
        query.setParameter(4, entity.getNutObservaciones());
        query.setParameter(5, entity.getNdnCodigo().getNdnCodigo());
        query.setParameter(6, entity.getNunInicio());
        query.setParameter(7, entity.getNunFin());
        
        query.executeUpdate();
        
    }
    
    public static List<String> exportarCSV(EntityManager em){
        StringBuilder searchQuery = new StringBuilder(
                "select (select b.ndt_nombre from ND_NDC b WHERE b.NDN_CODIGO = a.NDC) NDC, "
                + "       LPAD(a.min_inicio,7,'0') inicio, "
                + "       LPAD(a.max_inicio + 99,7,'0')  fin, "
                + "       (select c.descripcion from SA.SK_EMPRESA c WHERE c.SK_EMPRESA_CODE = a.SK_EMPRESA_CODE) empresa, "
                + "       (select (select d.NOMBRE_DEPARTAMENTO from SA.DEPARTAMENTOS d WHERE d.CODIGO_DEPARTAMENTO = md.CODIGO_DEPARTAMENTO) "
                + "         from SA.MUNICIPIOS md WHERE md.CODIGO_MUNICIPIO = a.SK_REGION_CODE) DEPARTAMENTO, "
                + "       (select m.descripcion from SA.SK_REGION m WHERE m.SK_REGION_CODE = a.SK_REGION_CODE) MUNICIPIO, "
                + "       (select g.EST_NOMBRE from ES_ESTADO g WHERE g.ESN_CODIGO = a.ESN_CODIGO) estado "
                + "from "
                + "( "
                + "with get_data as "
                + "  ( "
                + "  SELECT n.ndn_codigo, n.nun_inicio, floor(n.nun_inicio/1000) bloque, n.sk_empresa_code, n.sk_region_code, n.esn_codigo, "
                + "         ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) - ROW_NUMBER() OVER(PARTITION BY floor(n.nun_inicio/1000), n.sk_empresa_code, n.sk_region_code, n.esn_codigo, n.ndn_codigo ORDER BY n.ndn_codigo, n.nun_inicio) grp "
                + "  from nu_numeracion n "
                + "  where 1=1 "
                //+ "  AND n.NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NDT_NOMBRE = '800') "
                + "  AND (n.ESN_CODIGO = 2 OR n.ESN_CODIGO = 3) "
                + "  ), "
                + "sql_data as "
                + "  ( "
                + "  select a.ndn_codigo,a.nun_inicio, a.sk_empresa_code, a.sk_region_code, a.esn_codigo, "
                + "         min(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) min_inicio, "
                + "         max(nun_inicio) over (partition by bloque,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo,grp) max_inicio "
                + "  from get_data a "
                + "  ) "
                + "select distinct "
                + "       ndn_codigo ndc, "
                + "       min_inicio, "
                + "       max_inicio, "
                + "       sk_empresa_code, "
                + "       sk_region_code, "
                + "       esn_codigo "
                + "from sql_data "
                + "order by ndn_codigo,min_inicio "
                + ") a ");
        
        Query query = em.createNativeQuery(searchQuery.toString());
        
        List<Object> results = query.getResultList();
        
        List<String> numeracion = new ArrayList<String>();
        
        numeracion.add("NDC;INICIO;FIN;EMPRESA;DEPARTAMENTO;MUNICIPIO;ESTADO");
                
        if (results != null){
            int i = 0;
            for (Object oRow : results) {
                Object[] value = (Object[]) oRow;
                numeracion.add(value[0].toString() + ";" 
                        + value[1].toString() + ";"
                        + value[2].toString() + ";"
                        + value[3].toString() + ";"
                        + value[4].toString() + ";"
                        + value[5].toString() + ";"
                        + value[6].toString() + "");
                i++;

            }
        }
        
        return numeracion;
    }
    
    public static List<String> cargarNumeracionAgrupacionTotal(String operador, String ndc, int tipoNdc, int inicio, int fin, int estado, String municipio, String departamento, EntityManager em){
        StringBuilder searchQuery = new StringBuilder(
                  "select (select b.ndt_nombre from ND_NDC b WHERE b.NDN_CODIGO = a.ndn_codigo) NDC, "
                + "       inicio, "
                + "       fin, "
                + "       (fin - inicio + 1) cantidad, "
                + "       (select c.descripcion from SA.SK_EMPRESA c WHERE c.SK_EMPRESA_CODE = a.SK_EMPRESA_CODE) empresa, "
                + "       f.NOMBRE_DEPARTAMENTO DEPARTAMENTO, "
                + "       f.NOMBRE_MUNICIPIO MUNICIPIO, "
                + "       (select g.EST_NOMBRE from ES_ESTADO g WHERE g.ESN_CODIGO = a.esn_codigo) estado "
                + "from "
                + "( "
                + "  with get_data as "
                + "    ( "
                + "      select nun_inicio, sk_empresa_code,sk_region_code,ndn_codigo,esn_codigo, "
                + "             lag(sk_empresa_code) over (order by ndn_codigo,nun_inicio) prev_empresa, "
                + "             lag(sk_region_code) over (order by ndn_codigo,nun_inicio) prev_region, "
                + "             lag(ndn_codigo) over (order by ndn_codigo,nun_inicio) prev_ndc, "
                + "             lag(nun_inicio) over (order by ndn_codigo,nun_inicio) prev_nun_inicio, "
                + "             lag(esn_codigo) over (order by ndn_codigo,nun_inicio) prev_esn_codigo "
                + "      from nu_numeracion "
                + "      where 1=1 ");
        
        if(!operador.equals("-1")) {
            searchQuery.append("AND SK_EMPRESA_CODE = ?1 ");
        }
        if(!ndc.equals("-1")) {
            searchQuery.append("AND NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NDT_NOMBRE = ?2) ");
        }
        if((tipoNdc != -1) && (!ndc.equals("-1"))) {
            searchQuery.append("AND NDN_CODIGO IN (SELECT nd.NDN_CODIGO FROM ND_NDC nd WHERE nd.NTN_CODIGO = ?3) ");
        }
        if(inicio != -1) {
            String numero = String.valueOf(inicio);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            inicio = Integer.parseInt(numero);
            searchQuery.append("AND NUN_INICIO >= ?4 ");
        }
        if(fin != -1) {
            String numero = String.valueOf(fin);
            do {
                if(numero.length() < 7){
                    numero = numero + "0";
                }
            } while (numero.length() < 7);
            fin = Integer.parseInt(numero);
            searchQuery.append("AND NUN_FIN <= ?5 ");
        }
        if(estado != -1) {
            searchQuery.append("AND ESN_CODIGO = ?6 ");
        }
        if(!municipio.equals("-1")) {
            searchQuery.append("AND SK_REGION_CODE = ?7 ");
        }
        if(!departamento.equals("-1")) {
            searchQuery.append("AND SK_REGION_CODE in (SELECT m.CODIGO_MUNICIPIO FROM SA.MUNICIPIOS m WHERE m.CODIGO_DEPARTAMENTO = ?8) ");
        }
        
        searchQuery.append(
                  "    ), "
                + "    slq_data as "
                + "    ( "
                + "    select nun_inicio,ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo, "
                + "           sum(case when (sk_empresa_code = prev_empresa) "
                + "                     and (sk_region_code = prev_region) "
                + "                     and (ndn_codigo = prev_ndc) "
                + "                     and (nun_inicio = prev_nun_inicio + 100) "
                + "                     and (esn_codigo = prev_esn_codigo) "
                + "               then 0 "
                + "               else 1 "
                + "               end "
                + "           ) over (order by nun_inicio) grps "
                + "    from get_data "
                + "    ) "
                + "    select LPAD(min(nun_inicio),7,'0') inicio, "
                + "           LPAD(max(nun_inicio) + 99,7,'0') fin, "
                + "           sk_empresa_code, "
                + "           sk_region_code, "
                + "           ndn_codigo, "
                + "           esn_codigo"
                + "    from slq_data "
                + "    group by grps, ndn_codigo,sk_empresa_code,sk_region_code,esn_codigo "
                + "    order by min(nun_inicio) "
                + ") a "
                + "join (select d.CODIGO_MUNICIPIO, d.NOMBRE_MUNICIPIO, (select e.NOMBRE_DEPARTAMENTO from sa.departamentos e where e.CODIGO_DEPARTAMENTO = d.CODIGO_DEPARTAMENTO) NOMBRE_DEPARTAMENTO "
                + "      from sa.municipios d  "
                + "      ) f on (a.sk_region_code = f.CODIGO_MUNICIPIO)");
        
        Query query = em.createNativeQuery(searchQuery.toString());
        
        if(!operador.equals("-1")) {
            query.setParameter(1, operador);
        }
        if(!ndc.equals("-1")) {
            query.setParameter(2, ndc);
        }
        if((tipoNdc != -1) && (!ndc.equals("-1"))) {
            query.setParameter(3, tipoNdc);
        }
        if(inicio != -1) {
            query.setParameter(4, inicio);
        }
        if(fin != -1) {
            query.setParameter(5, fin);
        }
        if(estado != -1) {
            query.setParameter(6, estado);
        }
        if(!municipio.equals("-1")) {
            query.setParameter(7, municipio);
        }
        if(!departamento.equals("-1")) {
            query.setParameter(8, departamento);
        }
        
        List<Object> results = query.getResultList();
        
        List<String> numeracion = new ArrayList<String>();
        
        numeracion.add("NDC;INICIO;FIN;CANTIDAD;EMPRESA;DEPARTAMENTO;MUNICIPIO;ESTADO");
                
        if (results != null){
            int i = 0;
            for (Object oRow : results) {
                Object[] value = (Object[]) oRow;
                numeracion.add(value[0].toString() + ";" 
                        + value[1].toString() + ";"
                        + value[2].toString() + ";"
                        + value[3].toString() + ";"
                        + value[4].toString() + ";"
                        + value[5].toString() + ";"
                        + value[6].toString() + ";"
                        + value[7].toString() + "");
                i++;

            }
        }
        
        return numeracion;
    }
    
}
