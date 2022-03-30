<%-- 
    Document   : index
    Created on : 22 mars 2022, 09:39:50
    Author     : Rjr
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <body>
        <br>
        <div class="container">
            <div class="row">
                <div class="col-md-3">
                    <p><a href="insertProduit.jsp"> Insert produit </a></p>
                </div>
                <div class="col-md-6">
                    <h1>Bienvenue !</h1>
                    <p><a href="NewServlet1?type=entre" class="btn btn-info">  Liste des produits disponibles</a></p>
                    <p><a href="NewServlet1?type=dessert" class="btn btn-info">  Liste dessert </a></p>        
                    <p><a href="servletViewRecette" class="btn btn-info">  Liste prix de reviens </a></p>
                    <p><a href="Estimation.jsp" class="btn btn-info">  Estimation de prix de vente</a></p>
                    <p><a href="Commande.jsp" class="btn btn-info"> Commande </a></p>
                    <p><a href="filtreDate.jsp" class="btn btn-info">  Pourboir </a></p>
                </div>
                <div class="col-md-3"></div>
            </div>
        </div>
    </body>
</html>
