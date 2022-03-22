/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Vector;

/**
 *
 * @author Rjr
 */
public class Produit {

    private int id;
    private String nom;
    private String type;
    private float prixNormal;
    private float prixLongue;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getNom() {
        return nom;
    }

    public void setNom(String nom) {
        this.nom = nom;
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

    public Produit() {
    }

    public Produit(int id, String nom, String type, float prixNormal, float prixLongue) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prixNormal = prixNormal;
        this.prixLongue = prixLongue;
    }

    public Produit[] getProduit(String types) throws Exception {
        Connexion con = new Connexion();
        Connection connex = con.getConnect();
        String request = "select * from produit where type = '"+ types + "'";
        System.out.print(request + "-------------------------------------");
        Statement s = connex.createStatement();
        ResultSet result = s.executeQuery(request);
        Vector<Produit> v = new Vector();
        Produit temp = new Produit();
        ;
        while (result.next()) {
            int id = result.getInt(1);
            String nom = result.getString(2);
            String type = result.getString(3);
            float prixNormal = result.getFloat(4);
            float prixLongue = result.getFloat(5);
            temp = new Produit(id, nom, type, prixNormal, prixLongue);
            v.addElement(temp);
        }
        Object[] lesClientO = v.toArray();
        Produit[] lesClient = new Produit[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (Produit) lesClientO[i];
        }
        return lesClient;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

}
