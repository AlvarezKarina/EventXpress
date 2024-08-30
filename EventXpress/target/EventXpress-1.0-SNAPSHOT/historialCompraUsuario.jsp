<%-- 
    Document   : historialCompraUsuario
    Created on : 15 ene. 2024, 21:48:36
    Author     : Cisneros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Historial de Compras</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <div class="container my-4">
            <h2 class="text-center">Historial de Compras</h2>
            <hr>
            <div align="center">
                <h3>Detalles de las Compras</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Fecha</th>
                            <th>Evento</th>
                            <th>Precio c/u</th>
                            <th>Cantidad</th>
                            <th>Precio Total</th>
                        </tr>
                    </thead>
                    <tbody>
                        <c:forEach items="${listaHistorialCompras}" var="compra">
                            <tr>
                                <td>${compra.fechaCompra}</td>
                                <td>${compra.nombreEvento}</td>
                                <td>${compra.id_zona_establec.precio}</td>
                                <td>${compra.cantidad}</td>
                                <td>${compra.precioTotal}</td>
                            </tr>
                        </c:forEach>
                    </tbody>
                </table>
            </div>
        </div>
    </body>
</html>
