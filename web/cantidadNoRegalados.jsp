<%-- 
    Document   : cantidadNoRegalados
    Created on : 05-nov-2018, 12:01:17
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
            <h3>Cantidad de artículos seleccionados pero no regalados, por cada tipo de artículo</h3>
            <table class="table" id="${r.denominacion}">
                <thead>
                    <tr>
                        <th scope="col">Tipo</th>
                        <th scope="col">Cantidad</th>
                    </tr>
                </thead>
                <tbody>
                    <c:forEach items="${cant}" var="s">

                            <tr>
                                <td>${s.a.denominacion}</td>
                                <td>${s.cant}</td>
                            </tr>

                    </c:forEach>
                </tbody>
            </table>
        </div>
    </body>
</html>
