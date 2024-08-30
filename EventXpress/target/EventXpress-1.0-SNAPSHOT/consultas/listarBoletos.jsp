<%-- 
    Document   : usuarios_boletos
    Created on : 2 oct. 2023, 16:56:58
    Author     : CISNEROS
--%>

<%@page import="java.util.List"%>
<%@page import="com.ues.edu.entities.Boletos"%>
<%@page import="com.ues.edu.model.dao.BoletoDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios con Boletos</title>
        <!-- Link to Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <br>
        <div class="row">
            <div class="container">
                <h2 class="text-center">Lista de usuarios que han comprado boletos</h2>
                <hr>
                <div align="center">
                    <h3>Detalles de Boletos</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Fecha de Compra</th>
                                <th>Nombre del Usuario</th>
                                <th>Apellido del Usuario</th>
                                <th>Evento</th>
                            </tr>
                        </thead>
                        <%
                            List<Boletos> listaBoletos = (List<Boletos>) request.getAttribute("listaBoletos");
                            for (Boletos boleto : listaBoletos) {
                        %>
                        <tr>
                            <td><%= boleto.getFecha_compra()%></td>
                            <td><%= boleto.getDui().getNombre_u()%></td>
                            <td><%= boleto.getDui().getApellido_u()%></td>
                            <td><%= boleto.getId_evento_programado().getId_evento().getnEvento()%></td>
                        </tr>
                        <%
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
