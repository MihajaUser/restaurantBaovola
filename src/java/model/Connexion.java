/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package model;

import java.sql.*;

/**
 *
 * @author Tiavina P(anda)C
 */
public class Connexion {

    private Connection Connect;

    public Connexion() throws Exception {
        Class.forName("org.postgresql.Driver");
        this.Connect = DriverManager.getConnection("jdbc:postgresql://localhost:5432/resto", "test", "test");
    }

    public Connexion(String database, String user, String mdp) throws Exception {
        Class.forName("org.postgresql.Driver");
        String server = "jdbc:postgresql://localhost:5432/" + database;
        this.Connect = DriverManager.getConnection(server, user, mdp);
    }

    public Connection getConnect() throws Exception {
        return this.Connect;
    }

    public void setConnect(String server, String user, String mdp) throws Exception {
        this.Connect = DriverManager.getConnection(server, user, mdp);
    }

}
