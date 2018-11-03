/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package Servlets;

import controlador.conexion;
import java.io.IOException;
import java.sql.Date;
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
@WebServlet(name = "parejas", urlPatterns = {"/parejas"})
public class parejas extends HttpServlet {

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

        ArrayList<Comercio> listaComercios = c.getComercio();
        ArrayList<Pareja> lista = null;
        request.setAttribute("listComercios", listaComercios);
        if (action != null) {
            if (action.equals("agregar")) {
                try {
                    String nombre1 = request.getParameter("nombre1");
                    String apellido1 = request.getParameter("apellido1");
                    String nombre2 = request.getParameter("nombre2");
                    String apellido2 = request.getParameter("apellido2");
                    Date fecha = Date.valueOf(request.getParameter("fecha"));
                    int idComercio = Integer.parseInt(request.getParameter("comercio"));
                    if (c.agregarPareja(new Pareja(nombre1, apellido1, nombre2, apellido2, fecha, idComercio))) {
                        request.setAttribute("exito", "Pareja agregada con éxito");
                    } else {
                        request.setAttribute("error", "Algo sucedio mal durante la carga");
                    }
                } catch (NumberFormatException exc) {
                    request.setAttribute("error", "Error de formato!");
                }

            }
            if (action.equals("borrar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                if (c.borrarPareja(id)) {
                    request.setAttribute("exito", "Pareja borrado con éxito");
                } else {
                    request.setAttribute("error", "Algo sucedio mal durante el proceso");
                }
            }
            if (action.equals("editar")) {
                int id = Integer.parseInt(request.getParameter("id"));
                Pareja a = c.getPareja(id);
                if (a != null) {
                    request.setAttribute("editar", a);
                } else {
                    request.setAttribute("error", "Algo sucedio mal durante la busqueda");
                }
            }
            if (action.equals("editarPareja")) {
                String nombre1 = request.getParameter("nombre1");
                String apellido1 = request.getParameter("apellido1");
                String nombre2 = request.getParameter("nombre2");
                String apellido2 = request.getParameter("apellido2");
                Date fecha = Date.valueOf(request.getParameter("fecha"));
                int idComercio = Integer.parseInt(request.getParameter("comercio"));
                int id = Integer.parseInt(request.getParameter("id"));
                Pareja a = new Pareja(id, nombre1, apellido1, nombre2, apellido2, fecha, idComercio);
                
                if (c.setPareja(a)) {
                    request.setAttribute("exito", "Pareja editada exitosamente");
                } else {
                    request.setAttribute("error", "Algo sucedio mal durante la edición");
                }
            }
            if (action.equals("buscar")) {
                String cont=request.getParameter("cont");
                request.setAttribute("contenidoBusqueda", cont);
                lista = c.getParejas(cont);
            }
        }
        if(lista==null) lista = c.getParejas();
        request.setAttribute("list", lista);
        getServletContext().getRequestDispatcher("/parejas.jsp").forward(request, response);
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
