/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.Vector;

/**
 *
 * @author Rjr
 */
public class ViewRecette {
    private int idRecette;
    private float quantite;
    private int idProduit;
    private String nom;
    private String type;
    private float prixNormal;    
    private float prixLongue;
    private int idComposants;
    private String nomComposants;
    private float prixUnitaire;

    public int getIdRecette() {
        return idRecette;
    }

    public void setIdRecette(int idRecette) {
        this.idRecette = idRecette;
    }

    public float getQuantite() {
        return quantite;
    }

    public void setQuantite(float quantite) {
        this.quantite = quantite;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public float getPrixNormal() {
        return prixNormal;
    }

    public void setPrixNormal(float prixNormal) {
        this.prixNormal = prixNormal;
    }

    public float getPrixLongue() {
        return prixLongue;
    }

    public void setPrixLongue(float prixLongue) {
        this.prixLongue = prixLongue;
    }

    public int getIdComposants() {
        return idComposants;
    }

    public void setIdComposant(int idComposants) {
        this.idComposants = idComposants;
    }

    public String getNomComposants() {
        return nomComposants;
    }

    public void setNomComposants(String nomComposants) {
        this.nomComposants = nomComposants;
    }

    public float getPrixUnitaire() {
        return prixUnitaire;
    }

    public void setPrixUnitaire(float prixUnitaire) {
        this.prixUnitaire = prixUnitaire;
    }

    public ViewRecette(int idRecette, float quantite, int idProduit, String nom, String type, float prixNormal, float prixLongue, int idComposants, String nomComposants, float prixUnitaire) {
        this.idRecette = idRecette;
        this.quantite = quantite;
        this.idProduit = idProduit;
        this.nom = nom;
        this.type = type;
        this.prixNormal = prixNormal;
        this.prixLongue = prixLongue;
        this.idComposants = idComposants;
        this.nomComposants = nomComposants;
        this.prixUnitaire = prixUnitaire;
    }

    
    public ViewRecette(int idProduit, String nom) {
        this.idProduit = idProduit;
        this.nom = nom;
    }
    
 

    public ViewRecette() {
    }
    
    public ViewRecette[] getViewRecette() throws Exception {
        Connexion con = new Connexion();
        Connection connex = con.getConnect();
        String request = "select * from ViewRecette";
        System.out.print(request + "-------------------------------------");
        Statement s = connex.createStatement();
        ResultSet result = s.executeQuery(request);
        Vector<ViewRecette> v = new Vector();
        ViewRecette temp = new ViewRecette();
        ;
        while (result.next()) {
            
            int idRecette = result.getInt(1);
            float quantite = result.getFloat(2);
            int idProduit = result.getInt(3);

            String nom = result.getString(4);
            String type =result.getString(5);
            float prixNormal = result.getFloat(6);
            float prixLongue =  result.getFloat(7);
            int idComposant = result.getInt(8);
            String nomComposants =result.getString(9);
            float prixUnitaire = result.getFloat(10);
            temp = new ViewRecette(idRecette, quantite, idProduit,nom,type, prixNormal, prixLongue,idComposant,nomComposants,prixUnitaire);
            v.addElement(temp);
        }
        Object[] lesClientO = v.toArray();
        ViewRecette[] lesClient = new ViewRecette[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (ViewRecette) lesClientO[i];
        }
        return lesClient;
    }

    public float totaleReviens(int idProduit) throws Exception{
        
        ViewRecette[] view = this.getViewRecette();
        float retour = 0;
        
        for(int i=0 ; i <view.length ; i++){
            if(view[i].idProduit == idProduit){
                retour += view[i].prixUnitaire;
            }
        }
        return retour;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

     public ViewRecette[] getGroup() throws Exception {
        Connexion con = new Connexion();
        Connection connex = con.getConnect();
        String request = "select idProduit,nom_Produit from ViewRecette group by idProduit,nom_Produit";
        System.out.print(request + "-------------------------------------");
        Statement s = connex.createStatement();
        ResultSet result = s.executeQuery(request);
        Vector<ViewRecette> v = new Vector();
        ViewRecette temp = new ViewRecette();
        ;
        while (result.next()) {
            int id = result.getInt(1);
            String nom = result.getString(2);
            
            temp = new ViewRecette(id,nom);
            v.addElement(temp);
        }
        Object[] lesClientO = v.toArray();
        ViewRecette[] lesClient = new ViewRecette[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (ViewRecette) lesClientO[i];
        }
        return lesClient;
    }
     
     public float estimation(float prixMin , float prixMax, float prixReviens){
         float retour = 0;
         if(prixMin < prixReviens){
             retour  = prixReviens + ((prixReviens * 200)/100);
         }
         if(prixMin <= prixReviens && prixReviens< prixMax){
             retour  = prixReviens + ((prixReviens * 100)/100);
         }
         if(prixMax <= prixReviens){
             retour  = prixReviens + ((prixReviens * 50)/100);
         }
         return retour;
     }
    
}