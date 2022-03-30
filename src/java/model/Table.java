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
public class Table {
    int idTable;
    boolean disponible;

    public Table(int idTable, boolean disponible) {
        this.idTable = idTable;
        this.disponible = disponible;
    }

    public Table() {
    }

    public int getIdTable() {
        return idTable;
    }

    public void setIdTable(int idTable) {
        this.idTable = idTable;
    }

    public boolean isDisponible() {
        return disponible;
    }

    public void setDisponible(boolean disponible) {
        this.disponible = disponible;
    }
    
    public List<Table> listeTables () throws Exception{
        Connexion con = new Connexion();
        Connection c = con.getConnect();
        List<Table> liste = new ArrayList();
        String req = "select*from tables";
        System.out.println(req);
        Statement stmt = c.createStatement();
        ResultSet res = stmt.executeQuery(req);
        while(res.next()){
            Table tab = new Table(res.getInt("idTables"),res.getBoolean("disponible"));
            System.out.println(tab.getIdTable()+" - "+tab.isDisponible());
            liste.add(tab);
        }
        return liste;
    }
    
    public static void main(String[]args) throws Exception{
        new Table().listeTables();
    }
}
