/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.Connection;
import java.sql.Date;
import java.sql.ResultSet;
import java.sql.Statement;
import java.sql.Time;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author NIKO
 */
public class Commande {
    int idCommandes;
    int idTable;
    String sorteClient;
    String date;
    String heure;
    int idServeur;

    public Commande(int idCommandes, int idTable, String sorteClient, String date, String heure, int idServeur) {
        this.idCommandes = idCommandes;
        this.idTable = idTable;
        this.sorteClient = sorteClient;
        this.date = date;
        this.heure = heure;
        this.idServeur = idServeur;
    }

    public Commande(int idCommandes) {
        this.idCommandes = idCommandes;
    }

    public Commande() {
    }

    public int getIdCommandes() {
        return idCommandes;
    }

    public void setIdCommandes(int idCommandes) {
        this.idCommandes = idCommandes;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getSorteClient() {
        return sorteClient;
    }

    public void setSorteClient(String sorteClient) {
        this.sorteClient = sorteClient;
    }

    public String getDate() {
        return date;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public String getHeure() {
        return heure;
    }

    public void setHeure(String heure) {
        this.heure = heure;
    }

    public int getIdServeur() {
        return idServeur;
    }

    public void setIdServeur(int idServeur) {
        this.idServeur = idServeur;
    }
    
    public List<Commande> listeCommandes() throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<Commande> liste = new ArrayList();
        String req = "select*from Commandes";
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            Commande commande = new Commande(res.getInt("idCommandes"),res.getInt("idTable"),res.getString("sorteClient"),res.getString("date"),res.getString("heure"),res.getInt("idServeur"));
            System.out.println(commande.getIdCommandes()+" - "+commande.getIdTable()+" - "+commande.getSorteClient()+" - "+commande.getDate()+" - "+commande.getHeure()+" - "+commande.getIdServeur());
            liste.add(commande);
        }
        return liste;
    }
    
    public List<Commande> getIdParDate(String daty) throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<Commande> liste = new ArrayList();
        String req = "select idCommandes from Commandes where date='"+daty+"' ";
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            Commande commande = new Commande(res.getInt("idCommandes"));
            System.out.println(commande.getIdCommandes());
            liste.add(commande);
        }
        return liste;
    }
    
    public void insertCommande(int idTable, String daty, String ora, int idServeur) throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        String sorteClient = "normal";
        String req = "insert into commandes (idTable, sorteClient, date, heure, idServeur) values("+idTable+",'"+sorteClient+"','"+daty+"','"+ora+"',"+idServeur+")";
        System.out.println(req);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(req);
    }
    
    public static void main(String[]args) throws Exception{
        String daty = "2022-03-16";
        String ora = "00:00";
//        new Commande().insertCommande(5,daty, ora, 2);
//        new Commande().listeCommandes();
        new Commande().getIdParDate(daty);
    }
}
