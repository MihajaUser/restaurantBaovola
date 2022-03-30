<%-- 
    Document   : liste_produit
    Created on : 22 mars 2022, 10:24:40
    Author     : Rjr
--%>

<%@page import="model.ViewRecette"%>
<%@page import="java.util.ArrayList"%>
<%@page import="model.Produit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet"> 

        <title>JSP Page</title>
    </head>
    <%
        ViewRecette v = new ViewRecette();
        ViewRecette[] list = (ViewRecette[]) request.getAttribute("list");
    %>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8"><br><br>
                    <h1> Listes des produits disponibles</h1><br>
                    <!--<input id="rechercheId" onkeyup="recherche_nom()" type="text" name="rechercheNom" placeholder="nom Etudiant">-->
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col"> Nom Produit </th>                  
                                <th scope="col"> Totale prix de revient </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < list.length; i++) { %>
                            <tr>
                                <td><% out.print(list[i].getNom()); %></td>
                                <td><% out.print(v.totaleRevient(list[i].getIdProduit())); %> Ariary</td>
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