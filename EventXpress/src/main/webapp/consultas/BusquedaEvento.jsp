<%-- 
    Document   : BusquedaEvento
    Created on : 17 oct. 2023, 20:12:23
    Author     : karie
--%>

<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Mostrar Evento</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <br>
        <div class="container">
            <h2 class="text-center">Lista de eventos buscado por nombre:</h2>
            <hr>
            <!-- Formulario de búsqueda -->
            <form action="BusquedaEventosController" method="POST" class="mb-4">
                <div class="form-group">
                    <label for="n_evento" >Buscar Evento por nombre:</label>
                    <input type="text" name="n_evento" id="n_evento" class="form-control" style="width: 50%;">
                </div>
                <button type="submit" class="btn btn-primary">Buscar</button>
            </form>
            <div align="center">
                <hr>
                <h3>DETALLES DEL EVENTO</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>id Evento</th>
                            <th>nombre Evento</th>
                            <th>descripcion</th>
                            <th>id Categoria</th>
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
    </body>
</html>
