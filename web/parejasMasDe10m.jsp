<%-- 
    Document   : parejasMasDe10m
    Created on : 05-nov-2018, 12:01:32
    Author     : PEPE
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu_admin.jsp" %>
        <div class="container">
        <h3>Listado de parejas que han recibido más de $10000 en regalos</h3>
        <ul class="list-group list-group-flush">
            <c:forEach items="${parejas}" var="r">
                <li class="list-group-item">${r.nombre1} ${r.apellido1} y ${r.nombre2} ${r.apellido2}  (${r.fechaCasamientoTexto})</li>
            </c:forEach>
          </ul>
        </div>
    </body>
</html>
