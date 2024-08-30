<%-- 
    Document   : usuarios_boletos
    Created on : 2 oct. 2023, 16:56:58
    Author     : ASUS
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- para importar librerias de etiquetas personalizadas -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Evento en fecha especifica</title><!-- titulo de la pagina -->
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <br>
        <div class="row">
            <div class="container">
                <h2 class="text-center">Ingresos totales generados por cada categoría de un evento</h2>
                <hr>
                <div align="center">
                    <h3>Ingresos</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Categoría</th>
                                <th>Total generado por categoría</th>
                            </tr>
                        </thead>
                        <c:forEach items="${listaCategoriaTotal}" var="cat">
                            <tr>
                                <td>${cat.categoria}</td>
                                <td>$${cat.totalGenerado}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
