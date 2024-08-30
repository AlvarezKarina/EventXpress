
<%-- 
    Document   : mostrar_evento
    Created on : 11 oct 2023, 19:34:42
    Author     : alexa
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Evento</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <br>
        <div class="row">
            <div class="container">
                <h2 class="text-center">Lista de eventos por id de la categoría</h2>
                <hr>
                <!-- Formulario de búsqueda -->
                <form action="${pageContext.request.contextPath}/ControllerMostrarEvento" method="POST" class="mb-3">
                    <div class="form-group">
                        <label for="id_mostrar" class="form-label">Buscar Evento por id de la categoría:</label>
                        <input type="text" name="id_mostrar" id="id_mostrar" class="form-control" style="width: 10%;">
                    </div>
                    <button type="submit" class="btn btn-primary">Buscar</button>
                </form>
                <div align="center">
                    <h3>Detalles de Eventos</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>id Evento</th>
                                <th>nombre Evento</th>
                                <th>descripcion</th>
                                <th>id Categoría</th>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach items="${listaMostrarEvento}" var="e">
                                <tr>
                                    <td>${e.idEvento}</td>
                                    <td>${e.nEvento}</td>
                                    <td>${e.descripcion}</td>
                                    <td>${e.categoria.idCategoria}</td>
                                </tr>
                            </c:forEach>
                        </tbody>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>