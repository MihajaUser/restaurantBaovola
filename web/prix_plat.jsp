<%-- 
    Document   : prix_plat
    Created on : 24 mars 2022, 19:06:13
    Author     : Rjr
--%>

<%@page import="model.ViewRecette"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>

    <%
        ViewRecette v = new ViewRecette();
        ViewRecette[] list = (ViewRecette[]) request.getAttribute("list");

        String prixMin = request.getParameter("prixMin");
        String prixMax = request.getParameter("prixMax");

        float prixMin1 = Float.parseFloat(prixMin);
        float prixMax1 = Float.parseFloat(prixMax);
    %>


    <body>
        <h1>Prix estimation plat</h1>
        <% out.println(prixMin);%>
        <% out.println(prixMax);%>
        
        <%                    for (int i = 0; i < list.length; i++) {
                %>

                <% 
                
            out.println(v.estimation(prixMin1, prixMax1,v.totaleReviens(list[i].getIdProduit()))); %>
                    
                    


                </tr>

                <%
                    }
                %>



    </body>
</html>
