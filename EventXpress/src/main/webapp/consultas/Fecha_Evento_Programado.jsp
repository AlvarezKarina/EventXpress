
<%@page import="java.util.ArrayList"%>
<%@page import="com.ues.edu.model.dao.Evento_ProDao"%>
<%@page import="com.ues.edu.entities.Evento_Programado"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>
<html>
    <%
        Evento_ProDao daoEvento = new Evento_ProDao();
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
                indice = document.getElementById("botonEnviarFecha").selectedIndex;
                if (indice === null || indice === 0) {
                    alert('Seleccione una fecha');
                    return false;
                }
                return true;
            }
        </script>
        <br>
        <div class="row">
            <div class="container">
                <h2 class="text-center">Lista de eventos programados en una fecha específica</h2>
                <hr>
                <!-- Formulario -->
                <form action="${pageContext.request.contextPath}/ControllerEvento_Programado
                      " method="post" onsubmit="return validacion()">
                    <div class="form-group">
                        <label>Seleccione Una fecha</label>
                        <select id="botonEnviarFecha" name="botonEnviarFecha" class="form-control" style="width: 150px;" >
                            <option value="">Seleccione</option>
                            <%for (Evento_Programado evenP : daoEvento.getFechasProgramadas()) {%>
                            <option value="<%=evenP.getFecha()%>">
                                <%=evenP.getFecha()%>
                            </option>
                            <%}%>
                        </select>
                    </div>
                    <div class="form-group">
                        <button type="submit" class="btn btn-success" value="Consultar" name="botonEnviarConsPrestamo">Enviar</button>
                        <button type="button" class="btn btn-danger">
                            <a href="menu.jsp" style="color:white; text-decoration:none;">Cancelar</a>
                        </button>
                    </div>        
                </form>

                <div align="center">
                    <h3>Eventos en la fecha especificada</h3>
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Nombre del evento</th>
                                <th>Fecha</th>
                            </tr>
                        </thead>
                        <%
                            if (request.getParameter("botonEnviarFecha") != null && request.getParameter("botonEnviarFecha").equals(" ") == false) {
                                String fechaSeleccionada = request.getParameter("botonEnviarFecha");
                                ArrayList<Evento_Programado> listaEvento = daoEvento.getEventoProgramado(fechaSeleccionada);
                                for (Evento_Programado x : listaEvento) {
                        %>
                        <tr>
                            <td><%= x.getId_evento().getnEvento()%></td>
                            <td><%= x.getFecha()%></td>
                        </tr>
                        <%
                                }
                            }
                        %>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>