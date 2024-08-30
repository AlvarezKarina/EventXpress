<%-- 
    Document   : compraEntradasMas
    Created on : 15 oct. 2023, 20:54:17
    Author     : nelki
--%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
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
                <h2 class="text-center">Lista de personas que han comprado entradas para mas de un evento</h2>
                <hr>
                <div align="center">
                    <h3>Compra mas de una Entrada</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Nombre del Usuario</th>
                                <th>Apellido del Usuario</th>
                                <th>Email del Usuario</th>
                                <th>Eventos Asistidos</th>
                            </tr>
                        </thead>
                        <c:forEach items= "${listaCompraMas}" var= "com">
                            <tr>
                                <td>${com.nombre_u}</td>
                                <td>${com.apellido_u}</td>
                                <td>${com.email}</td>
                                <td>${com.eventosAsistidos}</td>
                            </tr>
                        </c:forEach>
                    </table> 
                </div>
            </div>
        </div>
    </body>
</html>
