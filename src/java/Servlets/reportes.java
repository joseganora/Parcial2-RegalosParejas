/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controlador.conexion;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Comercio;
import modelo.Pareja;

/**
 *
 * @author PEPE
 */
@WebServlet(name = "reportes", urlPatterns = {"/reportes"})
public class reportes extends HttpServlet {

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
            throws ServletException, IOException {
        String jsp="error";
        try {
            int action = Integer.parseInt(request.getParameter("action"));
            conexion c=new conexion();
            switch(action){
                case 1: 
                    ArrayList<Pareja> p= c.getParejas();
                    ArrayList<Comercio> comCon=c.getComercioConPareja();
                    ArrayList<Comercio> comSin=c.getComercioSinPareja();
                    request.setAttribute("parejas", p);
                    request.setAttribute("comerciosConParejas", comCon);
                    request.setAttribute("comerciosSinParejas", comSin);

                    jsp="parejasPorComercio";
                    break;
                case 2: 
                    jsp="facturacionPorPareja";
                    break;
                case 3:
                    jsp="parejasMasDe10m";
                    break;
                case 4: 
                    jsp="cantidadNoRegalados";
                    break;
                default: ;
                
            }
            
        } catch (NumberFormatException exc) {

        }
        getServletContext().getRequestDispatcher("/"+jsp+".jsp").forward(request, response);
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
        processRequest(request, response);
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
        processRequest(request, response);
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
