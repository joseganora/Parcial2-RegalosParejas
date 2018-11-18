<%-- 
    Document   : articulos
    Created on : 02-nov-2018, 2:01:50
    Author     : PEPE
--%>
<%@page import="modelo.TipoArticulo"%>
<%@page import="controlador.conexion"%>
<%@page import="modelo.Articulo"%>
<%@page import="java.util.ArrayList"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>JSP Page</title>
        <script>
            function borrar(id) {
                var r = confirm("¿Seguro que desea borrar este articulo? Se borrarán tambien los regalos asociados a este producto");
                if (r == true) {
                    location.href = "articulos?action=borrar&id=" + id;
                }
            }
            function editar(id) {
                location.href = "articulos?action=editar&id=" + id;
            }
            function buscar() {
                var cont = $('#contenidoBusqueda').val();
                location.href = "articulos?action=buscar&cont=" + cont;
            }
        </script>
    </head>
    <body>
        <%@include file="menu_admin.jsp" %>

        <div class="container ">

            <h2>ABM Articulos</h2>

            <%@include file="notificar.jsp" %>

            <div class="input-group mb-3">
                <c:choose>
                    <c:when test="${contenidoBusqueda!=null}">
                        <input type="text" class="form-control" id="contenidoBusqueda" value="${contenidoBusqueda}" onkeydown = "if (event.keyCode == 13)
                            $('#btn_enviar').click()" placeholder="denominacion o codigo del articulo.." aria-label="Recipient's username" aria-describedby="basic-addon2">
                        </c:when>
                        <c:otherwise>
                        <input type="text" class="form-control" id="contenidoBusqueda" onkeydown = "if (event.keyCode == 13)
                            $('#btn_enviar').click()" placeholder="denominacion o codigo del articulo.." aria-label="Recipient's username" aria-describedby="basic-addon2">
                        </c:otherwise>
                    </c:choose>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" onclick="buscar()" id="btn_enviar">Buscar</button>
                </div>
            </div>
            
            <div class="container" style="max-height: 400px;display: flex;">

                <ul class="list-group" style="overflow-y:scroll;width: 100%;">
                        <c:forEach items="${list}" var="r">
                            <li class="list-group-item  d-flex justify-content-between align-items-center" style="padding: 20px;"> (${r.codigo}) ${r.denominacion} 
                                <c:forEach items="${listTipos}" var="s">
                                    <c:if test="${s.id==r.idTipo}">
                                     - Tipo: ${s.denominacion}
                                    </c:if>
                                </c:forEach>
                                <div>
                                    <a class="badge-pill"href="#" onclick="editar(${r.id})"><img src="baseline-edit-24px.svg"/></a>
                                    <a class="badge-pill" href="#" onclick="borrar(${r.id})"><img src="baseline-delete-24px.svg"/></a>
                                </div>

                            </li>
                        </c:forEach>
                    </ul>
               
                </div>
            <br>

            <button type="button" data-toggle="modal" data-target="#agregar" class="btn btn-primary">Agregar</button>


        </div>


        <div class="modal fade" id="agregar" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div  class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Agregar Articulo</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="p-4" action="articulos" method="POST">
                            <input type="hidden" name="action" value="agregar">
                            <div class="form-group">
                                <label for="codigo">Codigo</label>
                                <input type="text" class="form-control" name="codigo" id="user" placeholder="codigo" required >
                            </div>
                            <div class="form-group">
                                <label for="denom">Denominacion</label>
                                <input type="text" class="form-control" name="denom" id="denom" placeholder="Denominacion" required >
                            </div>
                            <div class="form-group">
                                <label for="precio">Precio Unitario</label>
                                <input type="text" class="form-control" name="precio" id="denom" placeholder="Precio Unitario" required >
                            </div>
                            <div class="form-group">
                                <label for="tipo">Tipo de Articulo</label>
                                <select class="form-control" name="tipo" required >
                                    <c:forEach items="${listTipos}" var="r">
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
                        <h5 class="modal-title">Editar Articulo</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <form class="p-4" action="articulos" method="POST">
                            <input type="hidden" name="action" value="editarArticulo">
                            <input type="hidden" name="id" value="${editar.id}">
                            <div class="form-group">
                                <label for="codigo">Codigo</label>
                                <input type="text" class="form-control" name="codigo" id="user" placeholder="codigo" value="${editar.codigo}" required >
                            </div>
                            <div class="form-group">
                                <label for="denom">Denominacion</label>
                                <input type="text" class="form-control" name="denom" id="denom" placeholder="Denominacion"value="${editar.denominacion}" required >
                            </div>
                            <div class="form-group">
                                <label for="precio">Precio Unitario</label>
                                <input type="text" class="form-control" name="precio" id="denom" placeholder="Precio Unitario" value="${editar.precioUnitario}"required >
                            </div>
                            <div class="form-group">
                                <label for="tipo">Tipo de Articulo</label>
                                <select class="form-control" name="tipo" required >
                                    <c:forEach items="${listTipos}" var="r">
                                        <c:choose>
                                            <c:when test="${r.id==editar.idTipo}">
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
