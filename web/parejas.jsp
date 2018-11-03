<%-- 
    Document   : parejas
    Created on : 02-nov-2018, 2:01:13
    Author     : PEPE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>JSP Page</title>
        <script>
            function borrar(id) {
                var r = confirm("¿Seguro que desea borrar este producto?");
                if (r == true) {
                    location.href = "parejas?action=borrar&id=" + id;
                }
            }
            function editar(id) {
                location.href = "parejas?action=editar&id=" + id;
            }
            function buscar() {
                var cont = $('#contenidoBusqueda').val();
                location.href = "parejas?action=buscar&cont=" + cont;
            }
        </script>
    </head>
    <body>
        <%@include file="menu_admin.jsp" %>
        <div class="container ">

            <h2>ABM Parejas</h2>

            <%@include file="notificar.jsp" %>

            <div class="input-group mb-3">
                <c:choose>
                    <c:when test="${contenidoBusqueda!=null}">
                        <input type="text" class="form-control" id="contenidoBusqueda" value="${contenidoBusqueda}" onkeydown = "if (event.keyCode == 13)
                            $('#btn_enviar').click()" placeholder="Nombre o apellido.." aria-label="Recipient's username" aria-describedby="basic-addon2">
                        </c:when>
                        <c:otherwise>
                        <input type="text" class="form-control" id="contenidoBusqueda" onkeydown = "if (event.keyCode == 13)
                            $('#btn_enviar').click()" placeholder="Nombre o apellido.." aria-label="Recipient's username" aria-describedby="basic-addon2">
                        </c:otherwise>
                    </c:choose>
                
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" onclick="buscar()" id="btn_enviar">Buscar</button>
                </div>

            </div>
            <ul class="list-group">
                <c:forEach items="${list}" var="r">
                    <li class="list-group-item  d-flex justify-content-between align-items-center"> ${r.nombre1} y ${r.nombre2} (${r.fechaCasamiento})
                        <div>
                            <a class="badge-pill"href="#" onclick="editar(${r.id})"><img src="baseline-edit-24px.svg"/></a>
                            <a class="badge-pill" href="#" onclick="borrar(${r.id})"><img src="baseline-delete-24px.svg"/></a>
                        </div>
                        
                    </li>
                </c:forEach>
            </ul>
            <br>

            <button type="button" data-toggle="modal" data-target="#agregar" class="btn btn-primary">Agregar</button>


        </div>


        <div class="modal fade" id="agregar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div  class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Agregar Pareja</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="p-4" action="parejas" method="POST">
                            <input type="hidden" name="action" value="agregar">
                            <div class="form-group">
                                <label for="nombre1">Nombre</label>
                                <input type="name" class="form-control" name="nombre1" placeholder="Nombre" required >
                            </div>
                            <div class="form-group">
                                <label for="apellido1">Apellido</label>
                                <input type="name" class="form-control" name="apellido1" placeholder="Apellido" required >
                            </div>
                            <div class="form-group">
                                <label for="nombre2">Nombre</label>
                                <input type="name" class="form-control" name="nombre2" placeholder="Nombre" required >
                            </div>
                            <div class="form-group">
                                <label for="apellido2">Apellido</label>
                                <input type="name" class="form-control" name="apellido2" placeholder="Apellido" required >
                            </div>
                            <div class="form-group">
                                <label for="fecha">Fecha de casamiento</label>
                                <input type="date" class="form-control" name="fecha" placeholder="Fecha de casamiento" required >
                            </div>
                            <div class="form-group">
                                <label for="comercio">Comercio</label>
                                <select class="form-control" name="comercio" required >
                                    <c:forEach items="${listComercios}" var="r">
                                        <option value="${r.id}">
                                            ${r.denominacion}
                                        </option>
                                    </c:forEach>

                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Agregar</button>
                            <div class="modal" tabindex="-1" role="dialog"> 
                        </form>
                    </div>
                </div>
            </div>
        </div>  
    </div>

    <c:if test="${editar!=null}">
        <div class="modal fade" id="editar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div  class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Editar Pareja</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="p-4" action="parejas" method="POST">
                            <input type="hidden" name="action" value="editarPareja">
                            <input type="hidden" name="id" value="${editar.id}">
                            <div class="form-group">
                                <label for="nombre1">Nombre</label>
                                <input type="name" class="form-control" name="nombre1" value="${editar.nombre1}" placeholder="Nombre" required >
                            </div>
                            <div class="form-group">
                                <label for="apellido1">Apellido</label>
                                <input type="name" class="form-control" name="apellido1" value="${editar.apellido1}"placeholder="Apellido" required >
                            </div>
                            <div class="form-group">
                                <label for="nombre2">Nombre</label>
                                <input type="name" class="form-control" name="nombre2" value="${editar.nombre2}" placeholder="Nombre" required >
                            </div>
                            <div class="form-group">
                                <label for="apellido2">Apellido</label>
                                <input type="name" class="form-control" name="apellido2" value="${editar.apellido2}" placeholder="Apellido" required >
                            </div>
                            <div class="form-group">
                                <label for="fecha">Fecha de casamiento</label>
                                <input type="date" class="form-control" name="fecha" value="${editar.fechaCasamiento}" placeholder="Fecha de casamiento" required >
                            </div>
                            <div class="form-group">
                                <label for="comercio">Comercio</label>
                                <select class="form-control" name="comercio" required >
                                    <c:forEach items="${listComercios}" var="r">
                                        <c:choose>
                                            <c:when test="${r.id==editar.idComercio}">
                                                <option value="${r.id}" selected>
                                                </c:when>
                                                <c:otherwise>
                                                <option value="${r.id}">
                                                </c:otherwise>
                                            </c:choose>

                                            ${r.denominacion}
                                        </option>
                                    </c:forEach>

                                </select>
                            </div>
                            <button type="submit" class="btn btn-primary">Editar</button>
                            <div class="modal" tabindex="-1" role="dialog"> 
                        </form>
                    </div>
                </div>
            </div>
        </div>  
    </div>
    <script>$('#editar').modal("show");</script> 
</c:if>
    </body>
</html>
