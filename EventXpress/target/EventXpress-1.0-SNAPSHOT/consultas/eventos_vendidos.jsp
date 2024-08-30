<%@page import="com.ues.edu.entities.TotalBoletosVendidos"%>
<%@page import="java.util.List"%>
<%@page import="com.ues.edu.entities.Eventos"%>
<%@page import="com.ues.edu.model.dao.TotalBoletosVendidosDao"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- para importar librerias de etiquetas personalizadas -->
<!DOCTYPE html>
<html>
    <%
        TotalBoletosVendidosDao VendidosDao = new TotalBoletosVendidosDao();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos vendidos</title><!-- titulo de la pagina -->
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
    </head>
    <body>
        <br>
        <div class="row">
            <div class="container">
                <h2 class="text-center">Total de boletos vendidos por evento</h2>
                <hr>
                <div align="center">
                    <h3>Detalles Eventos</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Nombre del evento</th>
                                <th>Total boletos vendidos</th>
                            </tr>
                        </thead>
                        <% List<TotalBoletosVendidos> listVendidos = (List<TotalBoletosVendidos>) request.getAttribute("listaEventoProgramado"); //Obtener la lista de eventos que viene del Servlet
                            for (TotalBoletosVendidos e : listVendidos) {
                        %>
                        <tr>
                            <td><%= e.getN_evento() %></td>
                            <td><%= e.getTotalBoletosVendidos() %></td>
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
