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
public class CommandesNonValider {
    int idCommandeNV;
    String nomProduitNV;
    String typeProduitNV;
    float prixNormalNV;
    float prixLoungeNV;
    int quantiteNV;

    public CommandesNonValider(int idCommandeNV, String nomProduitNV, String typeProduitNV, float prixNormalNV, float prixLoungeNV, int quantiteNV) {
        this.idCommandeNV = idCommandeNV;
        this.nomProduitNV = nomProduitNV;
        this.typeProduitNV = typeProduitNV;
        this.prixNormalNV = prixNormalNV;
        this.prixLoungeNV = prixLoungeNV;
        this.quantiteNV = quantiteNV;
    }

    public CommandesNonValider() {
    }

    public int getIdCommandeNV() {
        return idCommandeNV;
    }

    public void setIdCommandeNV(int idCommandeNV) {
        this.idCommandeNV = idCommandeNV;
    }

    public String getNomProduitNV() {
        return nomProduitNV;
    }

    public void setNomProduitNV(String nomProduitNV) {
        this.nomProduitNV = nomProduitNV;
    }

    public String getTypeProduitNV() {
        return typeProduitNV;
    }

    public void setTypeProduitNV(String typeProduitNV) {
        this.typeProduitNV = typeProduitNV;
    }

    public float getPrixNormalNV() {
        return prixNormalNV;
    }

    public void setPrixNormalNV(float prixNormalNV) {
        this.prixNormalNV = prixNormalNV;
    }

    public float getPrixLoungeNV() {
        return prixLoungeNV;
    }

    public void setPrixLoungeNV(float prixLoungeNV) {
        this.prixLoungeNV = prixLoungeNV;
    }

    public float getQuantiteNV() {
        return quantiteNV;
    }

    public void setQuantiteNV(int quantiteNV) {
        this.quantiteNV = quantiteNV;
    }
    
    public List<CommandesNonValider> listeCommandesNonValider() throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<CommandesNonValider> liste = new ArrayList();
        String req = "select*from CommandesNonValider";
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            CommandesNonValider commande = new CommandesNonValider(res.getInt("idCommandeNV"),res.getString("nomProduitNV"),res.getString("typeProduitNV"),res.getFloat("prixNormalNV"),res.getFloat("prixLoungeNV"),res.getInt("quantite"));
            System.out.println(commande.getIdCommandeNV()+" - "+commande.getNomProduitNV()+" - "+commande.getTypeProduitNV()+" - "+commande.getPrixNormalNV()+" - "+commande.getPrixLoungeNV()+" - "+commande.getQuantiteNV());
            liste.add(commande);
        }
        return liste;
    }
    
    public void insertCommandesNonValider(String nomProduitNV, String typeProduitNV, float prixNormalNV, float prixLoungeNV, int quantite) throws Exception {
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        String req = "insert into CommandesNonValider (nomProduitNV,typeProduitNV,prixNormalNV,prixLoungeNV,quantite) values('"+nomProduitNV+"','"+typeProduitNV+"',"+prixNormalNV+","+prixLoungeNV+","+quantite+")";
        System.out.println(req);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(req);
    }
    
    public void deleteCommandesNonValider(int idCommandesNonValider) throws Exception {
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        String req = "delete from CommandesNonValider where idCommandeNV = "+idCommandesNonValider+"";
        System.out.println(req);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(req);
    }
    
    public static void main(String[]args) throws Exception{
        new CommandesNonValider().listeCommandesNonValider();
    }
}