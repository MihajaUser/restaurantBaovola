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
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <title>JSP Page</title>
    </head>
    <%
        ViewRecette v = new ViewRecette();
        ViewRecette[] list = (ViewRecette[]) request.getAttribute("listeRecette");
        request.getAttribute("nomProduit");
        
        Object prixMinimum = request.getAttribute("prixMin");
        String p = String.valueOf(prixMinimum);
        float prixMin = Float.parseFloat(p);
        
        Object prixMaximum = request.getAttribute("prixMax");
        String q = String.valueOf(prixMaximum);
        float prixMax = Float.parseFloat(q);
        
        String idPro = String.valueOf(request.getAttribute("idProduit"));
        int idProduit = Integer.parseInt(idPro);
        
        String nomProduit = String.valueOf(request.getAttribute("nomProduit"));
        String typeProduit = String.valueOf(request.getAttribute("typeProduit"));
        
        float revient = v.totaleRevient(idProduit);
        float estimation = v.estimation(prixMin, prixMax, v.totaleRevient(idProduit));
    %>
    <body>
        <div class="container">
            <div class="row">
                <div class="col-md-3"></div>
                <div class="col-md-7"><br>
                    <h2>Le prix d'estimation d'une plat : </h2><br>
                    <p><b>Nom produit : </b> <% out.println(nomProduit); %></p>
                    <p><b>Type produit : </b> <% out.println(typeProduit); %></p>
                    <p><b>Prix min : </b> <% out.println(prixMin); %> Ariary </p>
                    <p><b>Prix max : </b> <% out.println(prixMax); %> Ariary </p>
                    <p><b>Prix revient : </b> <% out.print(revient); %> Ariary </p>
                    <p><h5>Le prix de vente est de :  <b style="text-decoration: underline"><% out.print(estimation); %> Ariary</b> </h5></p><br>
                    <p><a href="index.jsp">Retour</a></p>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>         
    </body>
</html>