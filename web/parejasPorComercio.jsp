<%-- 
    Document   : parejasPorComercio
    Created on : 05-nov-2018, 11:52:51
    Author     : PEPE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%@include file="menu_admin.jsp" %>
        <div class="container">
            <h2>Parejas por comercio</h2>
            <div class="row">
                
                <div data-spy="scroll" data-target="#list-example" style="width: 100%;padding: 20px;" data-offset="0" class="scrollspy-example">
                    <c:forEach items="${comerciosConParejas}" var="r">
                        
                            <table class="table" id="${r.denominacion}">
                                <thead>
                                    <tr>
                                        <th scope="col" colspan="3">${r.denominacion}</th>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                    <c:forEach items="${parejas}" var="s">
                                        <c:if test="${r.id==s.idComercio}">
                                            <tr>
                                                <td>${s.apellido1}, ${s.nombre1}</td>
                                                <td>${s.apellido2}, ${s.nombre2}</td>
                                                <td>${s.fechaCasamientoTexto}</td>
                                            </tr>
                                        </c:if>
                                    </c:forEach>
                                </tbody>
                            </table>
               
                    </c:forEach>
                    <c:forEach items="${comerciosSinParejas}" var="r">
                            <table class="table" id="${r.denominacion}">
                                <thead>
                                    <tr>
                                        <th scope="col" colspan="3">${r.denominacion}</th>
                                        
                                    </tr>
                                </thead>
                                <tbody>
                                            <tr>
                                                <td colspan="3">No hay parejas para este articulo</td>
                                            </tr>
                                </tbody>
                            </table>
          
                    </c:forEach>
                </div>
            </div>
        </div>

    </body>
</html>
