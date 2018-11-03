<%-- 
    Document   : regalar
    Created on : 02-nov-2018, 2:04:35
    Author     : PEPE
--%>

<%@page import="modelo.Comprador"%>
<%@page import="modelo.Articulo"%>
<%@page import="controlador.conexion"%>
<%@page import="java.util.ArrayList"%>
<%@page import="modelo.Pareja"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>JSP Page</title>
        <script>
            function elegir(id) {
                location.href = "regalar.jsp?parejaSelect=" + id;
            }
            function regalar(id){
                $('#idArticulo').val(id);
                $('#regalar').modal("show");
            }
        </script>
    </head>
    <body>
        <%@include file="menu_admin.jsp" %>
        <div class="container">
            <h1>A Regalar!</h1>  
            <%@include file="notificar.jsp" %>
            <div class="row">
                <div class="col">
                    <p class="lead">Novios:</p>
                    <div class="list-group ">
                        <%       
                            conexion c = new conexion();
                            ArrayList<Pareja> listParejas = c.getParejas();
                            String pareja = request.getParameter("parejaSelect");
                            int idParejaSeleccionada = -1;

                            if (pareja != null) {
                                idParejaSeleccionada = Integer.parseInt(pareja);
                            }else{
                                pareja = (String)request.getAttribute("parejaSelect");
                                if(pareja!=null) idParejaSeleccionada = Integer.parseInt(pareja);
                            }
                            for (Pareja par : listParejas) {
                        %>
                        <button type="button" onclick="elegir(<%=par.getId()%>)" class=
                                
                                <%
                                    if (idParejaSeleccionada != -1 && par.getId() == idParejaSeleccionada) {
                                %>
                                "list-group-item d-flex justify-content-between align-items-center list-group-item-action active">
                            <%
                            } else {
                            %>
                            "list-group-item d-flex justify-content-between align-items-center list-group-item-action">
                            <%
                                }
                            %>
                            <%=par.getNombre1()%> y <%=par.getNombre2()%> (<%=par.getFechaCasamientoTexto()%>) <span class="badge badge-primary badge-pill"><%=c.countRegalosPendientes(par.getId())%></span></button>
                            <%
                                }
                            %>
                    </div>
                </div>
                <div class="col">
                    <%
                        if (idParejaSeleccionada > 0) {
                            %>
                            <p class="lead">Elige un regalo y haz click en el botón regalar:</p>
                            <%
                            ArrayList<Articulo> regalos = c.getRegalosPendientes(idParejaSeleccionada);
                            if (regalos.isEmpty()) {
                                    %>
                                    <div class="alert alert-primary" role="alert">
                                        Lista de regalos pendientes vacía
                                    </div>
                                    <%
                            } else {
                                %>
                                <ul class="list-group">
                                <%
                                for (Articulo r : regalos) {
                                        %>
                                        <li class="list-group-item d-flex justify-content-between align-items-center"><div>(<%=r.getCodigo()%>) <%=r.getDenominacion()%> - $ <%=r.getPrecioUnitario()%></div><a href="#" onclick="regalar(<%=r.getId()%>)"><img src="regalo.png"/></a></li>
                                            <%
                                    }
                                            %>
                                    </ul>
                                    <%
                                }

                        } else {
                            %>
                                <br><br>
                                <div class="alert alert-primary align-content-center" role="alert">
                                    <img src="baseline-keyboard_backspace-24px.svg"/>  Elige una pareja de la lista de novios
                                </div>
                            <%
                        }
                    %>
                </div>
            </div>
        </div>
        <%
                        if (idParejaSeleccionada > 0) {

                            %>
        <div class="modal fade" id="regalar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div  class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Regalar</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="p-4" action="regalar" method="POST">
                            <input type="hidden" name="action" value="regalar">
                            <input type="hidden" name="idPareja" value="<%=idParejaSeleccionada%>">
                            <input type="hidden" name="idArticulo" id="idArticulo" value="">
                            <div class="form-group">
                                <label for="comprador">Comprador</label>
                                <select class="form-control" name="comprador" required >
                                    <%
                                        ArrayList<Comprador> comp=c.getCompradores(idParejaSeleccionada);
                                        for (Comprador compr : comp) {
                                                
                                           
                                    %>
                                        <option value="<%= compr.getId()%>">
                                            <%= compr.getNombre() %>
                                        </option>
                                    <% } %>

                                </select>
                            </div>
                            <div class="form-group">
                                <label for="cantidad">Cantidad</label>
                                <input type="number" class="form-control" name="cantidad" placeholder="Cantidad" required >
                            </div>
                            <button type="submit" class="btn btn-primary">Regalar</button>
                            <div class="modal" tabindex="-1" role="dialog"> 
                        </form>
                    </div>
                </div>
            </div>
        </div>  
    </div>
         <%
                        }
                    %>
    </body>
</html>
