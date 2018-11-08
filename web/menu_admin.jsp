<%-- 
    Document   : menu_admin
    Created on : 02-nov-2018, 0:03:19
    Author     : PEPE
--%>

<%@page import="modelo.Usuario"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>

<link rel="stylesheet" href="./bootstrap/css/bootstrap.min.css">
<!-- modernizr css -->
<script src="./bootstrap/jquery.js"></script>
<script src="./bootstrap/js/bootstrap.min.js"></script>
<nav class="navbar navbar-expand-lg navbar-light bg-light">
    <a class="navbar-brand" href="admin.jsp">Administrador</a>
    <button class="navbar-toggler" type="button" data-toggle="collapse" data-target="#navbarNav" aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
        <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
        <ul class="navbar-nav mr-auto">
            <li class="nav-item active">
                <a class="nav-link" href="parejas">Parejas <span class="sr-only">(current)</span></a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="articulos">Articulos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="listarRegalos">Lista de Regalos</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="compradores">Compradores</a>
            </li>
            <li class="nav-item">
                <a class="nav-link" href="regalar.jsp">Regalar</a>
            </li>
            <li class="nav-item dropdown">
                <a class="nav-link dropdown-toggle" href="#" id="navbarDropdown" role="button" data-toggle="dropdown" aria-haspopup="true" aria-expanded="false">
                    Reportes
                </a>
                <div class="dropdown-menu" aria-labelledby="navbarDropdown">
                    <a class="dropdown-item" href="reportes?action=1">Parejas por comercio</a>
                    <a class="dropdown-item" href="reportes?action=2">Total Facturado</a>
                    <a class="dropdown-item" href="reportes?action=3">Parejas con mas de $10000</a>
                    <div class="dropdown-divider"></div>
                    <a class="dropdown-item" href="reportes?action=4">Cantidad de articulos no regalados</a>
                </div>
            </li>

        </ul>

        <%
            Usuario o = (Usuario)request.getSession().getAttribute("Usuario");
            if (o != null && o.isAutenticado()) {
                out.print((String) o.getNombre());
            } else {
                request.setAttribute("error", "error");
                getServletContext().getRequestDispatcher("/index.jsp").forward(request, response);
            }
        %>&nbsp;&nbsp;
        <form class="form-inline my-2 my-lg-0" action="index.jsp" method="POST">
            <input  type="hidden" value="salir" name="salir"/>
            <button class="btn btn-outline-success" type="submit" 
                    >Salir</button>
        </form>
    </div>
</nav>
