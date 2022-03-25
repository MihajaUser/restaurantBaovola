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
        <h1>Bienvenue !</h1>
        <p> <a href="NewServlet1?type=entre" class="btn btn-info">  Liste des produits disponibles</a> </p>
        <p> <a href="NewServlet1?type=dessert" class="btn btn-info">  Liste dessert</a> </p>        
        <p> <a href="servletViewRecette" class="btn btn-info">  Liste prix de reviens</a> </p>
        <h3>Estimation</h3>
        <form method="post" action="servletViewRecette">
            <input type="text" name="prixMin" />
            <input type="text" name="prixMax" />
            <input type="submit" value="entrer" />


        </form>




    </body>
</html>
