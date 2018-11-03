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
import modelo.Articulo;
import modelo.TipoArticulo;

/**
 *
 * @author PEPE
 */
@WebServlet(name = "articulos", urlPatterns = {"/articulos"})
public class articulos extends HttpServlet {

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
        String action = request.getParameter("action");
        conexion c = new conexion();

        ArrayList<TipoArticulo> listaTipos = c.getTipoArticulos();
        ArrayList<Articulo> lista = null;
        request.setAttribute("listTipos", listaTipos);
        if (action != null) {
            if (action.equals("agregar")) {
                try {
                    String codigo = request.getParameter("codigo");
                    String denom = request.getParameter("denom");
                    float precio = Float.parseFloat(request.getParameter("precio"));
                    int idTipo = Integer.parseInt(request.getParameter("tipo"));
                    if (c.agregarArticulo(new Articulo(codigo, denom, precio, idTipo))) {
                        request.setAttribute("exito", "Articulo agregado con exito");
                    } else {
                        request.setAttribute("error", "Algo sucedio mal durante la carga");
                    }
                } catch (NumberFormatException exc) {
                    request.setAttribute("error", "Error de formato! No incluyas letras ni simbolos en el precio.");

                }

            }
            if (action.equals("borrar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                if (c.borrarArticulo(id)) {
                    request.setAttribute("exito", "Articulo borrado con exito");
                } else {
                    request.setAttribute("error", "Algo sucedio mal durante la carga");
                }
            }
            if (action.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Articulo a = c.getArticulo(id);
                if (a != null) {
                    request.setAttribute("editar", a);
                } else {
                    request.setAttribute("error", "Algo sucedio mal durante la busqueda");
                }
            }
            if (action.equals("editarArticulo")) {
                String codigo = request.getParameter("codigo");
                String denom = request.getParameter("denom");
                float precio = Float.parseFloat(request.getParameter("precio"));
                int idTipo = Integer.parseInt(request.getParameter("tipo"));
                int id = Integer.parseInt(request.getParameter("id"));
                Articulo a = new Articulo(id, codigo, denom, precio, idTipo);
                
                if (c.setArticulo(a)) {
                    request.setAttribute("exito", "Articulo editado exitosamente");
                } else {
                    request.setAttribute("error", "Algo sucedio mal durante la edición");
                }
            }
            if (action.equals("buscar")) {
                String cont=request.getParameter("cont");
                request.setAttribute("contenidoBusqueda", cont);
                lista = c.getArticulos(cont);
            }
        }
        if(lista==null)     lista = c.getArticulos();
        request.setAttribute("list", lista);
        getServletContext().getRequestDispatcher("/articulos.jsp").forward(request, response);
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
