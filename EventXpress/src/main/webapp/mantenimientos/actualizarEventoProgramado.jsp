<%-- 
    Document   : actualizarEventoProgramado
    Created on : 8 ene. 2024, 21:34:48
    Author     : Cisneros
--%>

<%@page import="com.ues.edu.model.dao.EventoDao"%>
<%@page import="com.ues.edu.entities.Evento_Programado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ues.edu.model.dao.EventoProgramadoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Actualizar Evento Programado</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet"
              integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css"
              rel="stylesheet"/>
        <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js"
                integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8"
        crossorigin="anonymous"></script>
        <style>
            body {
                background-color: #f8f9fa;
            }
            .container {
                margin-top: 50px;
                max-width: 600px;
            }
            h3 {
                color: #343a40;
                margin-bottom: 30px;
            }
            label {
                font-weight: bold;
                display: inline-block;
                width: 150px;
            }
            .checkbox-group {
                margin-top: 10px;
            }
            .checkbox-group input {
                margin-left: 10px;
            }

        </style>
    </head>
    <body>
        <div class="container mx-auto">
            <h3 class="text-center">Actualizar Evento Programado</h3>

            <%-- Muestra el mensaje de error si existe --%>
            <% if (request.getAttribute("error") != null) {%>
            <div id="errorDivActualizar" class="alert alert-danger">
                <span class="error-text"><%= request.getAttribute("error")%></span>
            </div>
            <% }%>

            <form action="<%=request.getContextPath()%>/EventoProgramadoServlet?action=update" method="post">

                <%-- Recupera valores de los atributos de solicitud o parámetros, si están disponibles --%>
                <%
                    String id = request.getAttribute("id_evento_prograActualizar") != null ? request.getAttribute("id_evento_prograActualizar").toString() : request.getParameter("id");
                    String fecha = request.getAttribute("fechaActualizar") != null ? request.getAttribute("fechaActualizar").toString() : request.getParameter("fecha");
                    String horaInicio = request.getAttribute("horaInicioActualizar") != null ? request.getAttribute("horaInicioActualizar").toString() : request.getParameter("horaInicio");
                    String horaFin = request.getAttribute("horaFinActualizar") != null ? request.getAttribute("horaFinActualizar").toString() : request.getParameter("horaFin");
                    String suspendido = request.getAttribute("suspendidoActualizar") != null ? request.getAttribute("suspendidoActualizar").toString() : request.getParameter("suspendido");
                    String reprogramado = request.getAttribute("reprogramadoActualizar") != null ? request.getAttribute("reprogramadoActualizar").toString() : request.getParameter("reprogramado");
                    String fechaReprogramado = request.getAttribute("fechaReprogramadoActualizar") != null ? request.getAttribute("fechaReprogramadoActualizar").toString() : request.getParameter("fechaReprogramado");
                    String nombreEvento = request.getAttribute("nombreEvento") != null ? request.getAttribute("nombreEvento").toString() : request.getParameter("nombreEvento");
                    String nombreEstablecimiento = request.getAttribute("nombreEstablecimiento") != null ? request.getAttribute("nombreEstablecimiento").toString() : request.getParameter("nombreEstablecimiento");


                %>
                <div  class="modal-body">
                    <div class="form-floating mb-3">
                        <input style="background: #eeeeef" type="text" class="form-control" name="id_evento_prograActualizar"
                               id="id_evento_prograActualizar" value="<%= id != null ? id : ""%>" readonly="" required>
                        <label for="floatingInput" style="font-weight: bold;">ID:</label>
                    </div>
                </div>

                <div class="form-group">
                    <label for="fechaActualizar">Fecha:</label>
                    <input type="date" class="form-control" id="fechaActualizar" name="fechaActualizar" value="<%= fecha != null ? fecha : ""%>" required>
                </div>

                <div class="form-group">
                    <label for="horaInicioActualizar">Hora Inicio:</label>
                    <input type="time" class="form-control" id="horaInicioActualizar" name="horaInicioActualizar" value="<%= horaInicio != null ? horaInicio : ""%>" required>
                </div>

                <div class="form-group">
                    <label for="horaFinActualizar">Hora Fin:</label>
                    <input type="time" class="form-control" id="horaFinActualizar" name="horaFinActualizar" value="<%= horaFin != null ? horaFin : ""%>" required>
                </div>

                <div class="checkbox-group">
                    <input type="checkbox" id="suspendidoActualizar" name="suspendidoActualizar" <%= "on".equals(suspendido) ? "checked" : ""%>>
                    <label for="suspendidoActualizar">Suspendido</label>
                </div>

                <div class="checkbox-group">
                    <input type="checkbox" id="reprogramadoActualizar" name="reprogramadoActualizar" <%= "on".equals(reprogramado) ? "checked" : ""%>>
                    <label for="reprogramadoActualizar">Reprogramado</label>
                </div>

                <div class="form-group">
                    <label for="fechaReprogramadoActualizar">Fecha Reprogramado:</label>
                    <input type="date" class="form-control" id="fechaReprogramadoActualizar" name="fechaReprogramadoActualizar" value="<%= fechaReprogramado != null ? fechaReprogramado : ""%>">
                </div>

                <!-- Combo para seleccionar el nombre del evento -->
                <div class="form-floating mb-3">
                    <select name="nombreEvento" id="nombreEvento" class="form-select" aria-label="Default select example">
                        <option value="seleccionEvento" selected>Seleccione un evento</option>
                        <%
                            EventoProgramadoDao eventoDao = new EventoProgramadoDao();
                            ArrayList<String> nombresEventos = eventoDao.obtenerNombresEventos();
                            String nombreEventoSeleccionado = request.getParameter("nombreEvento");
                            for (String nombreEvt : nombresEventos) {
                                String selected = nombreEventoSeleccionado != null && nombreEventoSeleccionado.equals(nombreEvt) ? "selected" : "";
                        %>
                        <option value="<%=nombreEvt%>" <%= selected%>><%= nombreEvt%></option>
                        <% } %>
                    </select>
                </div>

                <!-- Combo para seleccionar el nombre del establecimiento -->
                <div class="form-floating mb-3">
                    <select name="nombreEstablecimiento" id="nombreEstablecimiento" class="form-select" aria-label="Default select example">
                        <option value="seleccionEstablecimiento" selected>Seleccione un establecimiento</option>
                        <%
                            ArrayList<String> nombresEstablecimientos = eventoDao.obtenerNombresEstablecimiento();
                            String nombreEstablecimientoSeleccionado = request.getParameter("nombreEstablecimiento");
                            for (String nombreEstablec : nombresEstablecimientos) {
                                String selected = nombreEstablecimientoSeleccionado != null && nombreEstablecimientoSeleccionado.equals(nombreEstablec) ? "selected" : "";
                        %>
                        <option value="<%=nombreEstablec%>" <%= selected%>><%= nombreEstablec%></option>
                        <% }%>
                    </select>
                </div>


                <div class="d-flex justify-content-between mt-2">
                    <button type="submit" class="btn btn-primary">Actualizar</button>
                    <a href="<%=request.getContextPath()%>/EventoProgramadoServlet?action=listar" class="btn btn-danger ">Cancelar</a>

                </div>
            </form>
        </div>
                    
                    
        <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
        <script>
            $(document).ready(function () {
                // Revisa si existe el mensaje de error y lo oculta después de 5 segundos
                if ($('#errorDivActualizar').length) {
                    setTimeout(function () {
                        $('#errorDivActualizar').fadeOut('slow', function () {
                            $(this).remove();
                        });
                    }, 5000); // 5000 milisegundos = 5 segundos
                }
            });
        </script>




        <script>
            // Función para validar el formulario
            function validarFormulario() {
                var fecha = document.getElementById("fechaActualizar").value;
                var horaInicio = document.getElementById("horaInicioActualizar").value;
                var horaFin = document.getElementById("horaFinActualizar").value;
                var fechaReprogramado = document.getElementById("fechaReprogramadoActualizar").value;
                var eventoSeleccionado = document.getElementsByName("nombreEvento")[0].value;
                var establecimientoSeleccionado = document.getElementsByName("nombreEstablecimiento")[0].value;

                // Validación de fechas
                if (new Date(fecha) < new Date()) {
                    alert("La fecha del evento no puede ser anterior o igual a la fecha actual.");
                    return false;
                }

                if (fecha && horaInicio && horaFin && new Date(fecha + " " + horaInicio) >= new Date(fecha + " " + horaFin)) {
                    alert("La hora de inicio debe ser anterior a la hora de fin.");
                    return false;
                }

                if (fechaReprogramado && new Date(fechaReprogramado) <= new Date(fecha)) {
                    alert("La fecha reprogramada debe ser posterior a la fecha original del evento.");
                    return false;
                }

                // Validación de selección de evento y establecimiento
                if (eventoSeleccionado === "seleccionEvento") {
                    alert("Por favor, seleccione un evento.");
                    return false;
                }

                if (establecimientoSeleccionado === "seleccionEstablecimiento") {
                    alert("Por favor, seleccione un establecimiento.");
                    return false;
                }

                return true; // Todo parece correcto
            }

            // Asigna la función de validación al formulario
            document.querySelector("form").onsubmit = validarFormulario;
        </script>

    </body>
</html>
