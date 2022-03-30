/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produit;
import model.ViewRecette;

/**
 *
 * @author NIKO
 */
public class Estimation extends HttpServlet {

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
            
        String prixMin = request.getParameter("prixMin");
        String prixMax = request.getParameter("prixMax");
        String idProduit = request.getParameter("idProduit");
        int idProd = Integer.parseInt(idProduit);
        
        if(prixMin.equals("") || prixMax.equals("")) {
            out.print("tsy mety a");
            RequestDispatcher rd = request.getRequestDispatcher("Estimation.jsp?error=1");
            rd.forward(request, response );
            
        }else {
            ViewRecette recette = new ViewRecette();
            ViewRecette[] getRecette = recette.getGroup();
            
            Produit produit = new Produit();
            Produit[] list = produit.getNomProduits(idProd);
            String nomProduit = "";
            String typeProduit = "";
            for(int i=0; i<list.length; i++){
                nomProduit = list[i].getNom();
                typeProduit = list[i].getType();
            }

            out.print(nomProduit+" - "+typeProduit);
            
            request.setAttribute("listeRecette", getRecette);
            request.setAttribute("nomProduit", nomProduit);
            request.setAttribute("typeProduit", typeProduit);
            
            float prixMin1 = Float.parseFloat(prixMin);
            float prixMax1 = Float.parseFloat(prixMax);
            request.setAttribute("prixMin", prixMin1);
            request.setAttribute("prixMax", prixMax1);
            request.setAttribute("idProduit", idProduit);

            out.print(prixMin1+" - "+prixMax1+" - "+idProduit);

            RequestDispatcher rd = request.getRequestDispatcher("prix_plat.jsp");
            rd.forward(request, response);
        }
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
            Logger.getLogger(Estimation.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(Estimation.class.getName()).log(Level.SEVERE, null, ex);
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
