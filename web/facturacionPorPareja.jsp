<%-- 
    Document   : facturacionPorPareja
    Created on : 05-nov-2018, 12:00:46
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
            <br>
            <h4>Total facturado por todos los regalos de todas las parejas: $${total}</h4>
            <br>
            <form  action="reportes?action=2" method="POST">
                <button type="submit" class="btn btn-primary">Actualizar</button>
            </form>
            
        </div>
    </body>
</html>
