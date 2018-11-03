/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controlador.conexion;
import java.io.IOException;
import java.util.ArrayList;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import modelo.Articulo;
import modelo.Pareja;
import modelo.Regalo;
import modelo.TipoArticulo;

/**
 *
 * @author PEPE
 */
@WebServlet(name = "listarRegalos", urlPatterns = {"/listarRegalos"})
public class listarRegalos extends HttpServlet {

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
        String pareja = request.getParameter("parejaSelect");
        conexion c = new conexion();
        ArrayList<Pareja> listaParejas = c.getParejas();
        request.setAttribute("listParejas", listaParejas);
        ArrayList<TipoArticulo> listaTipos = c.getTipoArticulos();
        request.setAttribute("listTipos", listaTipos);
        ArrayList<Articulo> listaArticulos = null;
        
        if (pareja != null) {
            int idPareja = Integer.parseInt(pareja);
            request.setAttribute("pareja", c.getPareja(idPareja));
            String action = request.getParameter("action");
            if (action != null) {
                if (action.equals("agregar")) {
                    try {

                        int idArticulo = Integer.parseInt(request.getParameter("articulo"));
                        if (c.agregarRegalo(new Regalo(idPareja, idArticulo))) {
                            request.setAttribute("exito", "Articulo agregado con exito");
                        } else {
                            request.setAttribute("error", "Algo sucedio mal durante la carga");
                        }
                    } catch (NumberFormatException exc) {
                        request.setAttribute("error", "Error de formato! No incluyas letras ni simbolos en el precio.");

                    }

                }
                if (action.equals("borrar")) {
                    int id = Integer.parseInt(request.getParameter("articulo"));
                    if (c.borrarRegalo(id,idPareja)) {
                        request.setAttribute("exito", "Articulo quitado con exito");
                    } else {
                        request.setAttribute("error", "Algo sucedio mal durante la carga");
                    }
                }
            }
            ArrayList<Articulo> listaArticulosSeleccionados=c.getArticulosSeleccionados(idPareja);
                request.setAttribute("listArticulosSeleccionados", listaArticulosSeleccionados);
                listaArticulos = c.getArticulosNoSeleccionados(idPareja);
        }
        if(listaArticulos==null) listaArticulos=c.getArticulos();
        request.setAttribute("listArticulos", listaArticulos);
        getServletContext().getRequestDispatcher("/listaRegalos.jsp").forward(request, response);
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
