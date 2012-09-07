<%@ page import="java.util.*, javax.management.*" %>

JMX testing..

<%

    ArrayList list = MBeanServerFactory.findMBeanServer(null);

    MBeanServer mbeanServer = (MBeanServer) list.get(0);

    out.print("MBeanServer: " + mbeanServer + "<br>");

    String domain = mbeanServer.getDefaultDomain();

    out.print("Default domain: " + domain + "<br>");

    Set namesSet = mbeanServer.queryNames(null, null);

    Object[] objectNames = namesSet.toArray();

    out.print("mbean object names: <br>");
    String qry = "Catalina:type=DataSource,path=/mapa,host=localhost,class=javax.sql.DataSource,name=\"ds/OracleMapaDS\"";
    ObjectName on = new ObjectName(qry);
    out.print(mbeanServer.getAttribute(on, qry));

    for (int x = 0; x < objectNames.length; x++)

    {

        out.print(objectNames[x] + "<br>");

    }

%>
