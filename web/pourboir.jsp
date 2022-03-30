<%-- 
    Document   : pourboir
    Created on : 30 mars 2022, 14:41:28
    Author     : NIKO
--%>

<%@page import="model.View_Serveurs"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body><br>
        <%
            View_Serveurs get = new View_Serveurs();
            View_Serveurs[] list = (View_Serveurs[]) request.getAttribute("list");
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8"><br><br>
                    <h1> Serveurs</h1><br>
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col"> Nom Serveurs </th>                  
                                <th scope="col"> Pourboir</th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < list.length; i++) { %>
                            <tr>
                                <td><% out.print(list[i].getNomServeurs()); %></td>
                                <td><% out.print(list[i].getTotal()); %> Ariary</td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table>
                    <br>
                    <a href="index.jsp">Retour</a>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
</html>
