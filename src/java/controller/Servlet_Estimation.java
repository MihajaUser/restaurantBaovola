/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package controller;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import model.Produit;
import model.ViewRecette;

/**
 *
 * @author Rjr
 */
public class Servlet_Estimation extends HttpServlet {

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        try (PrintWriter out = response.getWriter()) {
            double prixMin = Double.parseDouble(request.getParameter("prixMin"));
            double prixMax = Double.parseDouble(request.getParameter("prixMax"));
            
            ViewRecette recette = new ViewRecette();
            ViewRecette[] getRecette = recette.getGroup();
            
            HashMap data = new HashMap();
            data.put("min", prixMin);
            data.put("max", prixMax);
            data.put("list", getRecette);
            request.setAttribute("data",data );
            RequestDispatcher rd = request.getRequestDispatcher("prix_plat.jsp");
            rd.forward(request, response);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }
}
