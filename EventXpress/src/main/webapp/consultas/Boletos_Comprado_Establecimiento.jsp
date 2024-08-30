
<%-- 
    Document   : eventosCompradosPorUsuarios
    Created on : 11 oct. 2023, 15:23:16
    Author     : ASUS
--%>

<%@page import="com.ues.edu.entities.ReporteBoletosEstablec"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ues.edu.entities.Establecimiento"%>
<%@page import="com.ues.edu.model.dao.BoletosEstablecimientoDao"%>
<%@page import="com.ues.edu.model.dao.Evento_ProDao"%>
<%@page import="com.ues.edu.entities.Evento_Programado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <%
        Evento_ProDao daoEvento = new Evento_ProDao();
        BoletosEstablecimientoDao daoBol = new BoletosEstablecimientoDao();
    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Eventos Programados</title>
        <!-- Bootstrap CSS -->
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css" integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T" crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.3.1.slim.min.js" integrity="sha384-q8i/X+965DzO0rT7abK41JStQIAqVgRVzpbzo5smXKp4YfRvH+abtTE1Pi6jizo" crossorigin="anonymous"></script>
        <script src="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/js/bootstrap.min.js" integrity="sha384-JjSmVgyd0p3pXB1rRibZUAYoIIy6OrQ6VrjIEaFf/nJGzIxFDsf4x0xIM+B07jRM" crossorigin="anonymous"></script>
    </head>
    <body>
        <script>
            function validacion() {
                indice = document.getElementById("botonEnviarEstablecimiento").selectedIndex;
                if (indice === 0) {
                    alert("Seleccione un establecimiento");
                    return false;
                }
                return true;
            }
        </script>
        <br>
        <div class="container">
            <h2 class="text-center">Lista boletos vendidos segun el establecimientos seleccionado</h2>
            <hr>
            <form action="${pageContext.request.contextPath}/ControllerBoletosEstablecimiento" method="post" onsubmit="return validacion()" class="mb-4">
                <div class="form-group">
                    <label>Seleccione un establecimiento</label>
                    <select id="botonEnviarEstablecimiento" name="botonEnviarEstablecimiento" class="form-control" style="width: 50%;">
                        <option value=" ">Seleccione</option>
                        <%for (Establecimiento est : daoBol.getEstablecimientos()) {%>
                        <option value="<%=est.getId_establecimiento()%>">
                            <%=est.getN_establecimiento()%>
                        </option>
                        <%}%>
                    </select>
                </div>
                <button type="submit" class="btn btn-success">Enviar</button>
                <a href="menu.jsp" class="btn btn-danger">Cancelar</a>
            </form>
            <div align="center">
                <h3>Eventos en la fecha especificada</h3>
                <table class="table table-bordered">
                    <thead>
                        <tr>
                            <th>Nombre</th>
                            <th>Apellido</th>
                            <th>Cantidad</th>
                        </tr>
                    </thead>
                    <tbody>
                        <%
                            int total = 0;
                            if (request.getParameter("botonEnviarEstablecimiento") != null && !request.getParameter("botonEnviarEstablecimiento").isEmpty()) {
                                String establecimientoSeleccionado = request.getParameter("botonEnviarEstablecimiento");
                                ArrayList<ReporteBoletosEstablec> listaReporte = daoBol.getBoletosComprados(establecimientoSeleccionado);
                                for (ReporteBoletosEstablec x : listaReporte) {
                        %>
                        <tr>
                            <td><%= x.getNombre()%></td>
                            <td><%= x.getApellido()%></td>
                            <td><%= x.getCantidad()%></td>
                        </tr>
                        <% total += x.getCantidad();
                                }
                            }
                        %>
                    </tbody>
                </table>
                <br><h3>Total vendido en este establecimiento: <%= total%></h3>
            </div>
        </div>
    </body>
</html>