<%-- 
    Document   : insertProduit
    Created on : 27 mars 2022, 01:57:38
    Author     : NIKO
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h1>Insertion d'une produit</h1>
        <% if(request.getParameter("error") != null) { 
            out.println("Les champs sont invalides! Entrez votre produit... ");
        } %>
        
        <form action="insertProduit" method="post">
            <p>id : <input type="text" name="id"></p>
            <p>Nom : <input type="text" name="nom"></p>
            <p>Type : <input type="text" name="type"></p>
            <p>Prix normal : <input type="number" name="prixNormal"></p>
            <p>Prix lounge : <input type="number" name="prixLounge"></p>
            <p><input type="submit" value="inserer"></p>
        </form>
        <br>
        <a href="index.jsp">Retour</a>
    </body>
</html>
