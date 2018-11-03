<%-- 
    Document   : notificar
    Created on : 03-nov-2018, 1:50:56
    Author     : PEPE
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<c:if test="${error!=null}">
                <div class="alert alert-danger alert-dismissible fade show" role="alert">
                    ${error} <strong>8(</strong>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
            <c:if test="${exito!=null}">
                <div class="alert alert-success alert-dismissible fade show" role="alert">
                    ${exito} <strong>8D</strong>
                    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
                        <span aria-hidden="true">&times;</span>
                    </button>
                </div>
            </c:if>
