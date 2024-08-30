<%-- 
    Document   : insertarEventoProgramado
    Created on : 14 nov. 2023, 05:36:31
    Author     : Cisneros
--%>

<%@page import="com.ues.edu.model.dao.EventoDao"%>
<%@page import=" com.ues.edu.entities.Evento_Programado"%>
<%@page import="java.util.ArrayList"%>
<%@page import="com.ues.edu.model.dao.EventoProgramadoDao"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>Nuevo Evento Programado</title>
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
                display: inline-block; /* Hace que los elementos de etiqueta se comporten como bloques en línea */
                width: 150px; /* Ancho de la etiqueta, ajusta según sea necesario */
            }

            .checkbox-group {
                margin-top: 10px; /* Ajusta según sea necesario */
            }

            .checkbox-group input {
                margin-left: 10px; /* Ajusta según sea necesario */
            }

            

            .error-text {
                color: red;
            }
        </style>
    </head>
    <body>
        <div class="container mx-auto">
            <h3 class="text-center">Registrar Nuevo Evento Programado</h3>

            <!-- Muestra el mensaje de error si existe -->
            <% if (request.getAttribute("error") != null) {%>
            <div id="errorDivInsertar" class="alert alert-danger">
                <span class="error-text"><%= request.getAttribute("error")%></span>
            </div>
            <% }%>



            <!-- Formulario para registrar un nuevo evento programado -->
            <form action="<%=request.getContextPath()%>/EventoProgramadoServlet?action=insert" method="post">
                <div class="form-group">
                    <label for="fecha">Fecha:</label>
                    <input type="date" class="form-control" id="fecha" name="fecha" value="<%= request.getParameter("fecha") != null ? request.getParameter("fecha") : ""%>" required>
                </div>
                <div class="form-group">
                    <label for="horaInicio">Hora Inicio:</label>
                    <input type="time" class="form-control" id="horaInicio" name="horaInicio" value="<%= request.getParameter("horaInicio") != null ? request.getParameter("horaInicio") : ""%>" required>
                </div>
                <div class="form-group">
                    <label for="horaFin">Hora Fin:</label>
                    <input type="time" class="form-control" id="horaFin" name="horaFin" value="<%= request.getParameter("horaFin") != null ? request.getParameter("horaFin") : ""%>" required>
                </div>
                <div class="form-group">
                    <label for="suspendido">Suspendido:</label>
                    <input type="checkbox" class="form-check-input" id="suspendido" name="suspendido" <%= request.getParameter("suspendido") != null ? "checked" : ""%>>
                </div>
                <div class="form-group">
                    <label for="reprogramado">Reprogramado:</label>
                    <input type="checkbox" class="form-check-input" id="reprogramado" name="reprogramado" <%= request.getParameter("reprogramado") != null ? "checked" : ""%>>
                </div>
                <div class="form-group">
                    <label for="fechaReprogramado">Fecha Reprogramado:</label>
                    <input type="date" class="form-control" id="fechaReprogramado" name="fechaReprogramado" value="<%= request.getParameter("fechaReprogramado") != null ? request.getParameter("fechaReprogramado") : ""%>" >
                </div>

                <!-- Combo para seleccionar el nombre del evento -->
                <!-- Asegúrate de seleccionar el valor previamente seleccionado si está disponible -->
                <div class="form-floating mb-3">
                    <select name="nombreEvento" class="form-select" aria-label="Default select example">
                        <option value="seleccionEvento" selected>Seleccione un evento</option>
                        <% EventoProgramadoDao eventoDao = new EventoProgramadoDao(); %>
                        <% ArrayList<String> nombresEventos = eventoDao.obtenerNombresEventos(); %>
                        <% for (String nombreEvento : nombresEventos) {%>
                        <option value="<%=nombreEvento%>" <%= nombreEvento.equals(request.getParameter("nombreEvento")) ? "selected" : ""%>><%=nombreEvento%></option>
                        <% }%>
                    </select>
                </div>

                <!-- Combo para seleccionar el nombre del establecimiento -->
                <!-- Asegúrate de seleccionar el valor previamente seleccionado si está disponible -->
                <div class="form-floating mb-3">
                    <select name="nombreEstablecimiento" id="nombreEstablecimiento" class="form-select" aria-label="Default select example">
                        <option value="seleccionEstablecimiento" selected>Seleccione un establecimiento</option>
                        <% ArrayList<String> nombresEstablecimientos = eventoDao.obtenerNombresEstablecimiento(); %>
                        <% for (String nombreEstablecimiento : nombresEstablecimientos) {%>
                        <option value="<%=nombreEstablecimiento%>" <%= nombreEstablecimiento.equals(request.getParameter("nombreEstablecimiento")) ? "selected" : ""%>><%=nombreEstablecimiento%></option>
                        <% }%>
                    </select>
                    <label for="nombreEstablecimiento">Nombre del Establecimento</label>
                </div>

                <div class="d-flex justify-content-between mt-2">
                    <button type="submit" class="btn btn-primary">Registrar Evento Programado</button>
                   
                    <a href="<%=request.getContextPath()%>/EventoProgramadoServlet?action=listar" class="btn btn-danger ">Cancelar</a>
                </div>
            </form>
        </div>
        <script>
            $(document).ready(function () {
                // Revisa si existe el mensaje de error y lo oculta después de 5 segundos
                if ($('#errorDivInsertar').length) {
                    setTimeout(function () {
                        $('#errorDivInsertar').fadeOut('slow', function () {
                            $(this).remove();
                        });
                    }, 5000); // 5000 milisegundos = 5 segundos
                }
            });
        </script>




        <script>
            // Función para validar el formulario
            function validarFormulario() {
                var fecha = document.getElementById("fecha").value;
                var horaInicio = document.getElementById("horaInicio").value;
                var horaFin = document.getElementById("horaFin").value;
                var fechaReprogramado = document.getElementById("fechaReprogramado").value;
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