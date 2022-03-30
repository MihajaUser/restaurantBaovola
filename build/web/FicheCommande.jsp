<%-- 
    Document   : FicheCommande
    Created on : 29 mars 2022, 22:44:46
    Author     : NIKO
--%>

<%@page import="model.ViewCommande"%>
<%@page import="java.util.List"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" href="assets/css/bootstrap.min.css">
        <title>JSP Page</title>
    </head>
    <body><br>
        <div class="container">
            <div class="row">
                <div class="col-md-1"></div>
                <div class="col-md-10"><br>
                    <% 
                        List<ViewCommande> liste = (List<ViewCommande>)request.getAttribute("listeViewCommandes");
                        String date = "";
                        String heure = "";
                        int table = 0;
                        String nomServeur = "";
                        float total = 0;
                        for(int j=0; j<liste.size(); j++) {
                            date = liste.get(j).getDaty();
                            heure = liste.get(j).getOra();
                            table = liste.get(j).getIdTable();
                            nomServeur = liste.get(j).getNomServeur();
                            total = total+liste.get(j).getQuantite()*liste.get(j).getPrixNormal();
                        }
                    %>
                    <h1>Fiche commande valider </h1><br>
                        <p>Table num ° : <% out.print(table); %></p>
                        <p>Date et heure : <% out.print(date); %> <% out.print(heure); %></p>
                        <p>Nom serveur : <% out.print(nomServeur); %></p>
                    <br>
                    <table class="table">
                        <thead class="thead-light">
                            <th>Id</th>
                            <th>Nom produit</th>
                            <th>Quantite</th>
                            <th>Prix normal (Ariary)</th>
                            <th>Prix lounge (Ariary)</th>
                            <th>Montant</th>
                        </thead>
                        <tbody>
                            <% for(int i=0; i<liste.size(); i++) { %>
                            <tr>
                                <td><% out.print(i+1); %></td>
                                <td><% out.print(liste.get(i).getNomProduit()); %></td>
                                <td><% out.print(liste.get(i).getQuantite()); %></td>
                                <td><% out.print(liste.get(i).getPrixNormal()); %></td>
                                <td><% out.print(liste.get(i).getPrixLounge()); %></td>
                                <td><% out.print(liste.get(i).getQuantite()*liste.get(i).getPrixNormal()); %></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table><br><br>
                    <p>Somme a payer est de :  <b><% out.print(total); %> Ar</b></p>
                    <p></p>
                    <p>Merci pour votre confiance! <br><br> A bientot! </p>
                    <br>
                    <input type="submit" class="btn btn-info" value="IMPRIMER" style="float:right">
                    <br><br>
                    <p><a href="index.jsp">Retour</a></p>
                </div>
                <div class="col-md-1"></div>
            </div>
        </div>
    </body>
</html>
