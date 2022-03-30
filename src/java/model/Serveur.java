/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NIKO
 */
public class Serveur {
    int idServeur;
    String nomServeur;
    float pourboire;

    public Serveur(int idServeur, String nomServeur, float pourboire) {
        this.idServeur = idServeur;
        this.nomServeur = nomServeur;
        this.pourboire = pourboire;
    }

    public Serveur() {
    }

    public int getIdServeur() {
        return idServeur;
    }

    public void setIdServeur(int idServeur) {
        this.idServeur = idServeur;
    }

    public String getNomServeur() {
        return nomServeur;
    }

    public void setNomServeur(String nomServeur) {
        this.nomServeur = nomServeur;
    }

    public float getPourboire() {
        return pourboire;
    }

    public void setPourboire(float pourboire) {
        this.pourboire = pourboire;
    }
    
    public List<Serveur> listeServeurs () throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<Serveur> liste = new ArrayList();
        String req = "select*from serveur";
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            Serveur serveur = new Serveur(res.getInt("idServeur"),res.getString("nomServeur"),res.getFloat("pourboir"));
            System.out.println(serveur.getIdServeur()+" - "+serveur.getNomServeur()+" - "+serveur.getPourboire());
            liste.add(serveur);
        }
        return liste;
    }
    
    public static void main(String[]args) throws Exception{
        new Serveur().listeServeurs();
    }
}
