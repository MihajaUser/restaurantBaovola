<%-- 
    Document   : liste_produit
    Created on : 22 mars 2022, 10:24:40
    Author     : Rjr
--%>

<%@page import="java.util.ArrayList"%>
<%@page import="model.Produit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/style.css" rel="stylesheet"> 
        <title>JSP Page</title>
    </head>
    <%
        Produit v = new Produit();
        Produit[] list = (Produit[]) request.getAttribute("list");

    %>


    <body>
        <h1> Listes des produits disponibles</h1>

        <!--        <input id="rechercheId" onkeyup="recherche_nom()" type="text" name="rechercheNom" placeholder="nom Etudiant">-->


        <table style="border-collapse: collapse">
            <thead >
                <tr  >
                    <th class="nom">Nom Produit </th>
                    <th> Type</th>
                    <th> Prix normal</th>
                </tr>
            </thead>
            <tbody>
                <%                    for (int i = 0; i < list.length; i++) {
                %>

                <tr>
                    <td><% out.print(list[i].getNom()); %></td>
                    <td><% out.print(list[i].getType()); %></td>
                    <td><% out.print(list[i].getPrixNormal()); %> ariary</td>
                    


                </tr>

                <%
                    }
                %>
            </tbody>
        </table>

    </body>
</html>
