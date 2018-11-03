<%-- 
    Document   : listaRegalos
    Created on : 02-nov-2018, 2:03:05
    Author     : PEPE
--%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>JSP Page</title>
        <script>
            function elegirPareja() {
                var cont = $('#parejaSelect').val();
                location.href = "listarRegalos?parejaSelect=" + cont;
            }
            function agregar(id, pareja) {
                location.href = "listarRegalos?action=agregar&articulo=" + id + "&parejaSelect=" + pareja;
            }
            function borrar(id, pareja) {
                location.href = "listarRegalos?action=borrar&articulo=" + id + "&parejaSelect=" + pareja;
            }
        </script>
    </head>
    <body>
        <%@include file="menu_admin.jsp" %>
        <div class="container">
            <h1>Lista de regalos</h1>
            <%@include file="notificar.jsp" %>
            <div class="input-group">
                <select class="custom-select" id="parejaSelect">
                    <option>Seleccione pareja...</option>
                    <c:forEach items="${listParejas}" var="r">
                        <option value="${r.id}"
                        <c:if test="${pareja!=null&&pareja.id==r.id}"> selected </c:if>
                         > ${r.nombre1} y ${r.nombre2} (${r.fechaCasamientoTexto})
                        </option>
                    </c:forEach>
                </select>
                <div class="input-group-append">
                    <button class="btn btn-outline-secondary" type="button" onClick="elegirPareja()">Elegir</button>
                </div>
            </div>
            <c:if test="${pareja!=null}">
                <br>
                <h3>Lista de regalos de ${pareja.nombre1} y ${pareja.nombre2} (${pareja.fechaCasamientoTexto})</h3>
                <p>Los articulos celestes son parte de la lista, puedes quitarlos con el botón de la derecha. Los articulos grises no fueron incluirlos y puedes agregarlos con el botón +</p>
                <ul class="list-group">
                    <c:forEach items="${listArticulosSeleccionados}" var="r">
                        <li class="list-group-item list-group-item-primary  d-flex justify-content-between align-items-center"> (${r.codigo}) ${r.denominacion} 
                            <c:forEach items="${listTipos}" var="s">
                                <c:if test="${s.id==r.idTipo}">
                                    - Tipo: ${s.denominacion}
                                </c:if>
                            </c:forEach>
                            <div>
                                <a class="badge-pill"href="#" onclick="borrar(${r.id},${pareja.id})"><img src="baseline-delete-24px.svg"/></a>       
                            </div>
                        </li>
                    </c:forEach>
                    <c:forEach items="${listArticulos}" var="r">
                        <li class="list-group-item list-group-item-secondary d-flex justify-content-between align-items-center"> (${r.codigo}) ${r.denominacion} 
                            <c:forEach items="${listTipos}" var="s">
                                <c:if test="${s.id==r.idTipo}">
                                    - Tipo: ${s.denominacion}
                                </c:if>
                            </c:forEach>
                            <div>
                                <a class="badge-pill" href="#" onclick="agregar(${r.id},${pareja.id})"><img src="baseline-add-24px.svg"/></a>
                            </div>
                        </li>
                    </c:forEach>

                </ul>
            </c:if>
        </div>
    </body>
</html>
