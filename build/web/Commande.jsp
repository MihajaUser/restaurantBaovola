<%-- 
    Document   : Commande
    Created on : 29 mars 2022, 02:06:59
    Author     : NIKO
--%>

<%@page import="model.CommandesNonValider"%>
<%@page import="model.Table"%>
<%@page import="java.util.List"%>
<%@page import="model.Serveur"%>
<%@page import="model.Produit"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="assets/css/bootstrap.min.css" rel="stylesheet">
        <title> JSP Page </title>
    </head>
    <body><br><br>
        <div class="container">
            <div class="row">
                <div class="col-md-2"></div>
                <div class="col-md-8"><br>
                    <h2> INSERTION COMMANDE : </h2><br>
                    <form action="ServletInsertCommande" method="post">
                        <h5>Type : </h5><br>
                        <% if(request.getParameter("error") != null) { %>
                        <h6 style="color:red;"><% out.println("Entrer le type du produit svp! "); %></h6><br>
                        <% } %>
                        <div class="input-group">
                        <select class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon" name="type">
                          <option selected>Choisissez le type ...</option>
                          <option value="entre">entre</option>
                          <option value="dessert">dessert</option>
                        </select>
                        <div class="input-group-append">
                          <input class="btn btn-info" type="submit" value="VOIR">
                        </div>
                        </div>
                    </form><br>
                    
                    <% if(request.getAttribute("list") != null) { %>
                    <%  
                        Produit v = new Produit();
                        Produit[] list = (Produit[]) request.getAttribute("list");
                        
                        Serveur s = new Serveur();
                        List<Serveur> listeServeurs = (List<Serveur>)request.getAttribute("listeServeurs");
                        
                        Table t = new Table();
                        List<Table> listeTables = (List<Table>)request.getAttribute("listeTables");
                        
                        
                    %>
                    <form action="insertCommandeValider" method="post">
                    <div class="container">
                        <div class="row">
                            <div class="col-md-3">
                                <p><h5> Date : </h5> <input type="date" name="daty"></p>
                            </div>
                            <div class="col-md-3">
                                <p><h5> Heure : </h5> <input type="time" name="ora"></p>
                            </div>
                            <div class="col-md-3">
                                <p><h5> Serveurs : </h5>
                                <div class="input-group">
                                <select class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon" name="serveur">
                                <% for(int i=0; i<listeServeurs.size(); i++) { %>
                                    <option value="<% out.print(listeServeurs.get(i).getIdServeur()); %>"><% out.print(listeServeurs.get(i).getNomServeur()); %></option>
                                <% } %>
                                </select>
                                </div>
                                </p>
                            </div>
                            <div class="col-md-3">
                                <p><h5> Tables : </h5>
                                <div class="input-group">
                                <select class="custom-select" id="inputGroupSelect04" aria-label="Example select with button addon" name="table">
                                  <% for(int i=0; i<listeTables.size(); i++) { %>
                                    <option value="<% out.print(listeTables.get(i).getIdTable()); %>">Num° : <% out.print(listeTables.get(i).getIdTable()); %></option>
                                    <% } %>
                                </select>
                                </div>
                                </p>
                            </div>
                        </div>
                    </div><br>
                    <input type="submit" class="btn btn-info" value="VALIDER" style="margin-left: 20%;width: 50%"><br><br>
                    </form>
                    
                    <h4> Listes des produits : </h4><br>
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col"></th>
                                <th scope="col"> Nom </th>
                                <th scope="col"> Type </th>
                                <th scope="col"> PrixNormal </th>
                                <th scope="col"> PrixLounge </th>
                                <th scope="col"> Quantite </th>
                            </tr>
                        </thead>
                        <tbody>
                        
                            <% for (int i = 0; i < list.length; i++) { %>
                            <tr>
                                <td><% out.print(i+1); %></td>
                                <td><% out.print(list[i].getNom()); %></td>
                                <td><% out.print(list[i].getType()); %></td>
                                <td><% out.print(list[i].getPrixNormal()); %> ariary</td>
                                <td><% out.print(list[i].getPrixLongue()); %> ariary</td>
                                <form action="insertCommandeNV?idProduit=<% out.print(list[i].getId()); %>&typeProduit=<% out.print(list[i].getType()); %>&nomProduit=<% out.print(list[i].getNom()); %>&prixNormalProduit=<% out.print(list[i].getPrixNormal()); %>&prixLoungeProduit=<% out.print(list[i].getPrixLongue()); %>" method="post">
                                <td><input type="number" class="form-control" name="quantite" placeholder="Entrer la quantite"></td>
                                <td><input type="submit" class="btn btn-info" value="Ajouter"></td>
                                </form>
                            </tr>
                            <% } %>
                        </tbody>
                    </table><br><br>
                    <% if(request.getAttribute("listeCommandesNV") != null) { %>
                    <% 
                        CommandesNonValider comNV = new CommandesNonValider();
                        List<CommandesNonValider> listeCommandesNV = (List<CommandesNonValider>)request.getAttribute("listeCommandesNV");
                    %>
                    <h4> Listes des commandes NV : </h4><br>
                    <table class="table">
                        <thead class="thead-light">
                            <tr>
                                <th scope="col"></th>
                                <th scope="col"> Nom </th>
                                <th scope="col"> Type </th>
                                <th scope="col"> PrixNormal </th>
                                <th scope="col"> PrixLounge </th>
                                <th scope="col"> Quantite </th>
                            </tr>
                        </thead>
                        <tbody>
                            <% for (int i = 0; i < listeCommandesNV.size(); i++) { %>
                            <tr>
                                <td><% out.print(i+1); %></td>
                                <td><% out.print(listeCommandesNV.get(i).getNomProduitNV()); %></td>
                                <td><% out.print(listeCommandesNV.get(i).getTypeProduitNV()); %></td>
                                <td><% out.print(listeCommandesNV.get(i).getPrixNormalNV()); %> ariary</td>
                                <td><% out.print(listeCommandesNV.get(i).getPrixLoungeNV()); %> ariary</td>
                                <td><input type="text" style="width:40%;" class="form-control" name="quantite" placeholder="<% out.print(listeCommandesNV.get(i).getQuantiteNV()); %>"></td>
                                <td><a class="btn btn-danger" href="annulationCommandeNV?id=<% out.print(listeCommandesNV.get(i).getIdCommandeNV()); %>&type=<% out.print(listeCommandesNV.get(i).getTypeProduitNV()); %>">ANNULER</a></td>
                            </tr>
                            <% } %>
                        </tbody>
                    </table><br><br>
                    <% } %>
                    <% } %>
                    <a href="index.jsp">Retour</a>
                </div>
                <div class="col-md-2"></div>
            </div>
        </div>
    </body>
</html>
