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
public class ViewCommande {
    int viewCommande;
    int idDetailsCommande;
    int idTable;
    String daty;
    String ora;
    String sorteClient;
    String nomServeur;
    int quantite;
    String nomProduit;
    float prixNormal;
    float prixLounge;

    public ViewCommande(int idDetailsCommande, int idTable, String daty, String ora, String sorteClient, String nomServeur, int quantite, String nomProduit, float prixNormal, float prixLounge) {
        this.idDetailsCommande = idDetailsCommande;
        this.idTable = idTable;
        this.daty = daty;
        this.ora = ora;
        this.sorteClient = sorteClient;
        this.nomServeur = nomServeur;
        this.quantite = quantite;
        this.nomProduit = nomProduit;
        this.prixNormal = prixNormal;
        this.prixLounge = prixLounge;
    }

    public ViewCommande() {
    }

    public int getViewCommande() {
        return viewCommande;
    }

    public void setViewCommande(int viewCommande) {
        this.viewCommande = viewCommande;
    }

    public int getIdDetailsCommande() {
        return idDetailsCommande;
    }

    public void setIdDetailsCommande(int idDetailsCommande) {
        this.idDetailsCommande = idDetailsCommande;
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public String getDaty() {
        return daty;
    }

    public void setDaty(String daty) {
        this.daty = daty;
    }

    public String getOra() {
        return ora;
    }

    public void setOra(String ora) {
        this.ora = ora;
    }

    public String getSorteClient() {
        return sorteClient;
    }

    public void setSorteClient(String sorteClient) {
        this.sorteClient = sorteClient;
    }

    public String getNomServeur() {
        return nomServeur;
    }

    public void setNomServeur(String nomServeur) {
        this.nomServeur = nomServeur;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }

    public String getNomProduit() {
        return nomProduit;
    }

    public void setNomProduit(String nomProduit) {
        this.nomProduit = nomProduit;
    }

    public float getPrixNormal() {
        return prixNormal;
    }

    public void setPrixNormal(float prixNormal) {
        this.prixNormal = prixNormal;
    }

    public float getPrixLounge() {
        return prixLounge;
    }

    public void setPrixLounge(float prixLounge) {
        this.prixLounge = prixLounge;
    }
    
    public List<ViewCommande> listeViewCommande(String daty) throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<ViewCommande> liste = new ArrayList();
        String req = "select*from view_commandes where date='"+daty+"'";
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            ViewCommande commande = new ViewCommande(res.getInt("idDetailsCommandes"),res.getInt("idTable"),res.getString("date"),res.getString("heure"),res.getString("sorteClient"),res.getString("nomServeur"),res.getInt("quantite"),res.getString("nom"),res.getFloat("prixNormal"),res.getFloat("prixLongue"));
            System.out.println(commande.getIdDetailsCommande()+" - "+commande.getIdTable()+" - "+commande.getDaty()+" - "+commande.getOra()+" - "+commande.getSorteClient()+" - "+commande.getNomServeur()+" - "+commande.getQuantite()+" - "+commande.getNomProduit()+" - "+commande.getPrixNormal()+" - "+commande.getPrixLounge());
            liste.add(commande);
        }
        return liste;
    }
    public static void main(String[]args) throws Exception{
        new ViewCommande().listeViewCommande("2022-03-09");
    }
}
