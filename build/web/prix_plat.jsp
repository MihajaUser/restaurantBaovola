<%-- 
    Document   : prix_plat
    Created on : 24 mars 2022, 19:06:13
    Author     : Rjr
--%>

<%@page import="java.util.HashMap"%>
<%@page import="model.ViewRecette"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <%
        HashMap data = (HashMap) request.getAttribute("data");
        double min = (double) data.get("min");
        double max = (double) data.get("max");

        ViewRecette[] list = (ViewRecette[]) data.get("list");


    %>


    <body>
        <h1>Prix estimation plat</h1>

        <%      for(int i=0; i<list.length; i++){          %>

        <%out.println(list[i].estimation(min, max, list[i].totaleReviens(list[i].getIdProduit()))); %>




    </tr>

    <%
        }
    %>



</body>
</html>
