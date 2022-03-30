/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.CommandesNonValider;
import model.Produit;
import model.Serveur;
import model.Table;

/**
 *
 * @author NIKO
 */
public class ServletInsertCommande extends HttpServlet {

    /**
     * Processes requests for both HTTP <code>GET</code> and <code>POST</code>
     * methods.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    protected void processRequest(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException, Exception {
        response.setContentType("text/html;charset=UTF-8");
        try (PrintWriter out = response.getWriter()) {
            
            Produit produit = new Produit();
            
            String type =  request.getParameter("type");
            out.print(type);
            
            if(type.equals("Choisissez le type ...")){
                out.print("tsy mety");
                RequestDispatcher rd = request.getRequestDispatcher("Commande.jsp?error=1");
                rd.forward(request, response);
            }else{
                out.print("mety");
                Produit[] getProduit = produit.getProduit(type);
                for(int i=0; i<getProduit.length; i++){
                    out.print(getProduit[i].getNom());
                }
                request.setAttribute("list", getProduit);

                Serveur serveur = new Serveur();
                List<Serveur> listeServeur = serveur.listeServeurs();
                request.setAttribute("listeServeurs", listeServeur);

                for(int j=0; j<listeServeur.size(); j++){
                    out.println(listeServeur.get(j).getNomServeur());
                }

                Table table = new Table();
                List<Table> listeTables = table.listeTables();
                request.setAttribute("listeTables", listeTables);
                for(int k=0; k<listeTables.size(); k++){
                    out.print(listeTables.get(k).getIdTable());
                }

                CommandesNonValider commandeNV = new CommandesNonValider();
                List<CommandesNonValider> listeCommandesNonValider = commandeNV.listeCommandesNonValider();
                request.setAttribute("listeCommandesNV", listeCommandesNonValider);
                for(int a=0; a<listeCommandesNonValider.size(); a++){
                    out.print("idCommandeNV : "+listeCommandesNonValider.get(a).getIdCommandeNV());
                }
            }
            RequestDispatcher rd = request.getRequestDispatcher("Commande.jsp");
            rd.forward(request, response);
        }
    }

    // <editor-fold defaultstate="collapsed" desc="HttpServlet methods. Click on the + sign on the left to edit the code.">
    /**
     * Handles the HTTP <code>GET</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletInsertCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Handles the HTTP <code>POST</code> method.
     *
     * @param request servlet request
     * @param response servlet response
     * @throws ServletException if a servlet-specific error occurs
     * @throws IOException if an I/O error occurs
     */
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try {
            processRequest(request, response);
        } catch (Exception ex) {
            Logger.getLogger(ServletInsertCommande.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * Returns a short description of the servlet.
     *
     * @return a String containing servlet description
     */
    @Override
    public String getServletInfo() {
        return "Short description";
    }// </editor-fold>

}
