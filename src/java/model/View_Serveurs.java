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
import java.util.Vector;

/**
 *
 * @author NIKO
 */
public class View_Serveurs {
    private int idCommandes;
    private Date daty;
    private Time heure ;
    private int idServeur;
    private String nomServeurs;
    private float pourboir;
    private int idDetailsCommandes ;
    private int quantite; 
    private String nomProduit;
    private float prixNormal;
    private float total;
   
    public int getIdCommandes() {
        return idCommandes;
    }

    public void setIdCommandes(int idCommandes) {
        this.idCommandes = idCommandes;
    }

    public Date getDate() {
        return daty;
    }

    public void setDate(Date date) {
        this.daty = date;
    }

    public Time getHeure() {
        return heure;
    }

    public void setHeure(Time heure) {
        this.heure = heure;
    }

    public int getIdServeur() {
        return idServeur;
    }

    public void setIdServeur(int idServeur) {
        this.idServeur = idServeur;
    }

    public String getNomServeurs() {
        return nomServeurs;
    }

    public void setNomServeurs(String nomServeurs) {
        this.nomServeurs = nomServeurs;
    }

    public float getPourboir() {
        return pourboir;
    }

    public void setPourboir(float pourboir) {
        this.pourboir = pourboir;
    }

    public int getIdDetailsCommandes() {
        return idDetailsCommandes;
    }

    public void setIdDetailsCommandes(int idDetailsCommandes) {
        this.idDetailsCommandes = idDetailsCommandes;
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

    public View_Serveurs(int idCommandes, Date daty, Time heure, int idServeur, String nomServeurs, float pourboir, int idDetailsCommandes, int quantite, String nomProduit, float prixNormal) {
        this.idCommandes = idCommandes;
        this.daty = daty;
        this.heure = heure;
        this.idServeur = idServeur;
        this.nomServeurs = nomServeurs;
        this.pourboir = pourboir;
        this.idDetailsCommandes = idDetailsCommandes;
        this.quantite = quantite;
        this.nomProduit = nomProduit;
        this.prixNormal = prixNormal;
    }
    
    public View_Serveurs() {
    }
    
     public View_Serveurs[] getViewServeurs() throws Exception {
        Connexion con = new Connexion();
        Connection connex = con.getConnect();
        String request = "select * from view_Serveurs";

        System.out.print(request + "-------------------------------------");
        Statement s = connex.createStatement();
        ResultSet result = s.executeQuery(request);
        Vector<View_Serveurs> v = new Vector();
        View_Serveurs temp = new View_Serveurs();
        
        while (result.next()) {
            
            int idCommandes = result.getInt(1);
            Date daty = result.getDate(4);
            Time heure = result.getTime(5);
            int idServeurs = result.getInt(6);
            String nomServeurs = result.getString(7);
            float pourboir =result.getFloat(8) ;
            int idDetailsCommandes =result.getInt(9) ;
            int quantite  = result.getInt(11);
            String nomProduit = result.getString(12);
            float prixNormal = result.getFloat(13);


            temp = new View_Serveurs(idCommandes, daty, heure,idServeurs,nomServeurs, pourboir, idDetailsCommandes,quantite,nomProduit,prixNormal);
            v.addElement(temp);
        }
        Object[] lesClientO = v.toArray();
        View_Serveurs[] lesClient = new View_Serveurs[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (View_Serveurs) lesClientO[i];
        }
        return lesClient;
    }
     
   
     
     public View_Serveurs[] calculPourboir(Date date1,Date date2) throws Exception {
        Connexion con = new Connexion();
        Connection connex = con.getConnect();
        String request = "select date,nomServeur,total from calcul where date between '" + date1 + "' and '" + date2  + "'";

        System.out.print(request + "-------------------------------------");
        Statement s = connex.createStatement();
        ResultSet result = s.executeQuery(request);
        Vector<View_Serveurs> v = new Vector();
        View_Serveurs temp = new View_Serveurs();
        
        while (result.next()) {
            Date date = result.getDate(1);
            
            String nomServeurs = result.getString(2);           
            int total = result.getInt(3);

            temp = new View_Serveurs(date,nomServeurs, total);
            v.addElement(temp);
        }
        Object[] lesClientO = v.toArray();
        View_Serveurs[] lesClient = new View_Serveurs[v.size()];
        for (int i = 0; i < v.size(); i++) {
            lesClient[i] = (View_Serveurs) lesClientO[i];
        }
        return lesClient;
    }
     
    public static void main(String[] args) throws Exception{
        View_Serveurs get = new View_Serveurs();
        Date date1 = Date.valueOf("2022-03-12");            
           Date date2 = Date.valueOf("2022-03-15");

           View_Serveurs[] list = get.getViewServeurs();
        System.out.print(+ list.length);
    }

    public Date getDaty() {
        return daty;
    }

    public void setDaty(Date daty) {
        this.daty = daty;
    }

    public float getPrixNormal() {
        return prixNormal;
    }

    public void setPrixNormal(float prixNormal) {
        this.prixNormal = prixNormal;
    }
    
    public double pourboir(int idServeurs) throws Exception{
        double retour = 0;
        View_Serveurs get = new View_Serveurs();
        View_Serveurs[] list = get.getViewServeurs();
        for(int i=0 ; i<list.length ;i ++){
            if(idServeurs == list[i].getIdServeur()){
                retour = retour + ((list[i].quantite * list[i].prixNormal) * (0.02));
            }
        }
        return retour;
    }

    public View_Serveurs(Date daty, String nomServeurs, float total) {
        this.daty = daty;
        this.nomServeurs = nomServeurs;
        this.total = total;
    }
    public float getTotal() {
        return total;
    }

    public void setTotal(float total) {
        this.total = total;
    }
}