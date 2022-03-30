<%-- 
    Document   : Estimation
    Created on : 27 mars 2022, 03:16:34
    Author     : NIKO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@page import="model.Produit"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <%
            Produit produit = new Produit();
            Produit[] list = produit.getProduits(); 
        %>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-6">
                    <br>
                    <h3>ESTIMATION DE PRIX DE VENTE : </h3><br>
                    <p style="color: red;"><% if(request.getParameter("error")!= null) { 
                        out.print("Veuillez entrer votre prix min et max de l'estimation! ");
                    }%></p><br>
                    <form action="Estimation" method="post">
                        <p> Prix min : <input type="text" name="prixMin">  Ariary</p>
                        <p> Prix max : <input type="text" name="prixMax">  Ariary</p>
                        <p> Nom produit : 
                        <select name="idProduit">
                            <% for(int i=0; i<list.length; i++) { %>
                            <option value="<% out.print(list[i].getId()); %>"><% out.print(list[i].getNom()); %></option>
                            <% } %>
                        </select>
                        </p><br>
                        <input type="submit" class="btn btn-info" value="Estimer" style="margin-left: 2%; width: 40%">
                    </form><br><br>
                    <a href="index.jsp">Retour</a>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>