<%-- 
    Document   : index
    Created on : 31-oct-2018, 13:52:48
    Author     : PEPE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <meta name="viewport" content="width=device-width, user-scalable=no">
        <title>JSP Page</title>
        <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/css/bootstrap.min.css" integrity="sha384-Gn5384xqQ1aoWXA+058RXPxPg6fy4IWvTNh0E263XmFcJlSAwiGgFAW/dAiS6JXm" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.2.1.slim.min.js" integrity="sha384-KJ3o2DKtIkvYIK3UENzmM7KCkRr/rE9/Qpg6aAZGJwFDMVNA/GpGFF93hXpG5KkN" crossorigin="anonymous"></script>
        <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.12.9/umd/popper.min.js" integrity="sha384-ApNbgh9B+Y1QKtv3Rn7W3mgPxhU9K/ScQsAP7hUibX39j7fakFPskvXusvfa0b4Q" crossorigin="anonymous"></script>
        <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.0.0/js/bootstrap.min.js" integrity="sha384-JZR6Spejh4U02d8jOt6vLEHfe/JQGiRRSQQxSfFWpi1MquVdAyjUar5+76PVCmYl" crossorigin="anonymous"></script>
    </head>
    <body>
        <%@include file="header.jsp" %>
        <br>
        <div class="container">

            <div class="row">
                <div class="col">
                    <p class="lead">Novios:</p>
                    <div class="list-group">
                        <button type="button" class="list-group-item list-group-item-action active">Nombre 1 <span class="badge badge-primary badge-pill">14</span></button>
                        <button type="button" class="list-group-item list-group-item-action">Nombre 2 risus <span class="badge badge-primary badge-pill">14</span></button>
                        <button type="button" class="list-group-item list-group-item-action">Nombre 3 consectetur ac <span class="badge badge-primary badge-pill">14</span></button>
                        <button type="button" class="list-group-item list-group-item-action">Nombre 4 at eros <span class="badge badge-primary badge-pill">14</span></button>
                    </div>
                </div>
                <div class="col">
                    <p class="lead">Regalos Pedidos Restantes:</p>
                    <ul class="list-group">
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>
                        <li class="list-group-item">Cras justo odio</li>
                        <li class="list-group-item">Dapibus ac facilisis in</li>

                    </ul>
                </div>
            </div>

        </div>


        <div class="modal fade" id="login" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true">
            <div  class="modal-dialog" role="document">
                <div class="modal-content">
                    <div class="modal-header">
                        <h5 class="modal-title">Ingreso administrador</h5>
                        <button type="button" class="close" data-dismiss="modal" aria-label="Close">
                            <span aria-hidden="true">&times;</span>
                        </button>
                    </div>
                    <div class="modal-body">
                        <div class="alert alert-danger" role="alert">
                            ¡Usuario o contraseña incorrectos!
                        </div>
                        <form class="p-4" action="loguin" method="POST">
                            <div class="form-group">
                                <label for="user">Usuario</label>
                                <input type="user" class="form-control" name="user" id="user" placeholder="Usuario">
                            </div>
                            <div class="form-group">
                                <label for="password">Password</label>
                                <input type="password" class="form-control" name="contra" id="password" placeholder="Contraseña">
                            </div>
                            <div class="form-check">
                                <input type="checkbox" class="form-check-input" id="dropdownCheck2">
                                <label class="form-check-label" for="dropdownCheck2">
                                    Recordarme
                                </label>
                            </div>
                            <button type="submit" class="btn btn-primary">Ingresar</button>
                            <div class="modal" tabindex="-1" role="dialog"> 
                        </form>
                    </div>
                </div>
            </div>
        </div>  
    </div>
    <%
        String error = (String) request.getAttribute("error");
        if (error != null) {
    %>
    <script>
        var m = $('#login');
        m.find('.modal-body').find('.alert').show();
        m.modal("show");
    </script>
    <%
    } else {
    %>
    <script>
        $('#login').find('.modal-body').find('.alert').hide();

    </script>
    <%
        }
    if(request.getParameter("salir")!=null){
    request.getSession().removeAttribute("nombreUsuario");
    }
    %>
</body>
</html>
