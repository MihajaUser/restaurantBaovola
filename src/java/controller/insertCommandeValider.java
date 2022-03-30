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
import model.Commande;
import model.CommandesNonValider;
import model.DetailsCommandes;
import model.Produit;
import model.ViewCommande;

/**
 *
 * @author NIKO
 */
public class insertCommandeValider extends HttpServlet {

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
            
            String daty = request.getParameter("daty");
            String ora = request.getParameter("ora");
//            System.out.print("+++++++"+request.getParameter("serveur"));
            int idServeur = Integer.parseInt(request.getParameter("serveur"));
            int table = Integer.parseInt(request.getParameter("table"));
            out.print("daty : "+daty+" ===== ora : "+ora+" ===== idServeur : "+idServeur+" ===== idTable : "+table+"<br>");
            
            Produit produit = new Produit();
            CommandesNonValider comNV = new CommandesNonValider();
            DetailsCommandes detailCommande = new DetailsCommandes();
            List<CommandesNonValider> listeCNV = comNV.listeCommandesNonValider();
            List<DetailsCommandes> listeDetailsCommandes = detailCommande.listeDetailsCommandes();
            
            Commande commande = new Commande();
            commande.insertCommande(table, daty, ora, idServeur);
            
            List<Commande> listeCommandes = commande.listeCommandes();
            List<Commande> idParDate = commande.getIdParDate(daty);
            int idCommande = idParDate.get(0).getIdCommandes();
            out.print("id commande : "+idParDate.get(0).getIdCommandes()+"<br>");

            for(int j=0; j<listeCNV.size(); j++){
                out.print(listeCNV.get(j).getQuantiteNV()+"<br>");
            }
            
            for(int i=0; i<listeCNV.size(); i++){
                List<Produit> idPro = produit.getProduitId(listeCNV.get(i).getNomProduitNV());
                for(int q=0; q<idPro.size(); q++){
                    out.print(idPro.get(q).getId()+"<br>");
                    detailCommande.insertDetailsCommandes(idCommande, idPro.get(q).getId(), (int) listeCNV.get(i).getQuantiteNV());
                }
            }
            
            for(int k=0; k<listeCNV.size(); k++){
                comNV.deleteCommandesNonValider(listeCNV.get(k).getIdCommandeNV());
            }
            
            ViewCommande viewCommande = new ViewCommande();
            List<ViewCommande> listeViewCommandes = viewCommande.listeViewCommande(daty);
            request.setAttribute("listeViewCommandes", listeViewCommandes);
            
            RequestDispatcher rd = request.getRequestDispatcher("FicheCommande.jsp");
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
            Logger.getLogger(insertCommandeValider.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(insertCommandeValider.class.getName()).log(Level.SEVERE, null, ex);
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
