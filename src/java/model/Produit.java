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
import java.util.List;
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

    public Produit(int id) {
        this.id = id;
    }

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

    public Produit() {
    }
    
    public Produit(int id, String nom, String type, float prixNormal, float prixLongue) {
        this.id = id;
        this.nom = nom;
        this.type = type;
        this.prixNormal = prixNormal;
        this.prixLongue = prixLongue;
    }

    public Produit(String nom, String type) {
        this.nom = nom;
        this.type = type;
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

        while (result.next()) {
            int id = result.getInt(1);
            String nom = result.getString(2);
            String type = result.getString(3);
            float prixNormal = result.getFloat(4);
            float prixLongue = result.getFloat(5);
            temp = new Produit(id, nom, type, prixNormal, prixLongue);
            v.addElement(temp);
            System.out.println(id+"-"+nom+"-"+prixNormal+"-"+prixLongue);
        }
        Object[] lesClientO = v.toArray();
        Produit[] lesClient = new Produit[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (Produit) lesClientO[i];
        }
        return lesClient;
    }
    
    public Produit[] getProduits() throws Exception {
        Connexion con = new Connexion();
        Connection connex = con.getConnect();
        String request = "select * from produit";
        System.out.println(request + "-------------------------------------");
        Statement s = connex.createStatement();
        ResultSet result = s.executeQuery(request);
        Vector<Produit> v = new Vector();
        Produit temp = new Produit();

        while (result.next()) {
            int id = result.getInt(1);
            String nom = result.getString(2);
            String type = result.getString(3);
            float prixNormal = result.getFloat(4);
            float prixLongue = result.getFloat(5);
            temp = new Produit(id, nom, type, prixNormal, prixLongue);
            v.addElement(temp);
            System.out.println(id+"-"+nom+"-"+prixNormal+"-"+prixLongue);
        }
        Object[] lesClientO = v.toArray();
        Produit[] lesClient = new Produit[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (Produit) lesClientO[i];
        }
        return lesClient;
    }
    
    public Produit[] getNomProduits(int id) throws Exception {
        Connexion con = new Connexion();
        Connection connex = con.getConnect();
        String request = "select nom, type from produit where id = "+id+"";
        System.out.println(request + "-------------------------------------");
        Statement s = connex.createStatement();
        ResultSet result = s.executeQuery(request);
        Vector<Produit> v = new Vector();
        Produit temp = new Produit();

        while (result.next()) {
            String nom = result.getString(1);
            String type = result.getString(2);
            temp = new Produit(nom,type);
            v.addElement(temp);
            System.out.println(nom+"-"+type);
        }
        Object[] lesClientO = v.toArray();
        Produit[] lesClient = new Produit[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (Produit) lesClientO[i];
        }
        return lesClient;
    }
    
    public void insertProduit(int id, String nom, String type, float prixNormal, float prixLounge) throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        String req = "insert into Produit values('"+id+"','"+nom+"','"+type+"',"+prixNormal+","+prixLounge+")";
        System.out.println(req);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(req);
    }
    
    public List<Produit> getProduitId (String nom) throws Exception {
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<Produit> liste = new ArrayList();
        String req = "select id from produit where nom = '"+nom+"'";
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            Produit produit = new Produit(res.getInt("id"));
            System.out.println(produit.getId());
            liste.add(produit);
        }
        return liste;
    }
    
    public static void main(String[]args ) throws Exception {
        Produit p = new Produit();
//        p.getProduit("entre");
//        p.getProduits();
        p.getProduitId("Vary @anana");
    }
}
