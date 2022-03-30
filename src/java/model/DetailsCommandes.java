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
public class DetailsCommandes {
    int idDetailsCommandes;
    int idCommandes;
    int idProduit;
    int quantite;

    public DetailsCommandes(int idDetailsCommandes, int idCommandes, int idProduit, int quantite) {
        this.idDetailsCommandes = idDetailsCommandes;
        this.idCommandes = idCommandes;
        this.idProduit = idProduit;
        this.quantite = quantite;
    }

    public DetailsCommandes() {
    }

    public int getIdDetailsCommandes() {
        return idDetailsCommandes;
    }

    public void setIdDetailsCommandes(int idDetailsCommandes) {
        this.idDetailsCommandes = idDetailsCommandes;
    }

    public int getIdCommandes() {
        return idCommandes;
    }

    public void setIdCommandes(int idCommandes) {
        this.idCommandes = idCommandes;
    }

    public int getIdProduit() {
        return idProduit;
    }

    public void setIdProduit(int idProduit) {
        this.idProduit = idProduit;
    }

    public int getQuantite() {
        return quantite;
    }

    public void setQuantite(int quantite) {
        this.quantite = quantite;
    }
    
    public List<DetailsCommandes> listeDetailsCommandes() throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<DetailsCommandes> liste = new ArrayList();
        String req = "select*from DetailsCommandes";
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            DetailsCommandes detail = new DetailsCommandes(res.getInt("idDetailsCommandes"),res.getInt("idCommandes"),res.getInt("idProduit"),res.getInt("quantite"));
            System.out.println(detail.getIdDetailsCommandes()+" - "+detail.getIdCommandes()+" - "+detail.getIdProduit()+" - "+detail.getQuantite());
            liste.add(detail);
        }
        return liste;
    }
    
    public void insertDetailsCommandes (int idCommandes, int idProduit, int quantite) throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        String req = "insert into DetailsCommandes (idCommandes, idProduit, quantite) values("+idCommandes+","+idProduit+","+quantite+")";
        System.out.println(req);
        Statement stmt = c.createStatement();
        stmt.executeUpdate(req);
    }
    
    public static void main(String[]args) throws Exception{
        new DetailsCommandes().listeDetailsCommandes();
//        new DetailsCommandes().insertDetailsCommandes(50,1,2);
    }
}
