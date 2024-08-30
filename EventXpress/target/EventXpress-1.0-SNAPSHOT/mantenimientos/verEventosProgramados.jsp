<%-- 
    Document   : verEventosProgramados
    Created on : 14 nov. 2023, 05:34:13
    Author     : Cisneros
--%>

<%@page import="java.text.SimpleDateFormat"%>
<%@page import="com.ues.edu.model.dao.EventoProgramadoDao"%>
<%@page import="com.ues.edu.entities.Evento_Programado"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<head>

    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    Cisneros 😎<title>Eventos Programados</title>
    <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
    <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet"/>
    <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet"/>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>


    <style>
        body {
            background-color: #f8f9fa;
        }

        header {
            background-color: tomato;
            padding: 10px;
        }

        header a {
            color: white;
        }

        .container {
            margin-top: 20px;
        }

        h3 {
            color: #343a40;
        }

        table {
            margin-top: 20px;
        }

        .btn-primary {
            background-color: #007bff;
            border-color: #007bff;
        }

        .btn-success {
            background-color: #28a745;
            border-color: #28a745;
        }

        .btn-primary:hover,
        .btn-primary:focus,
        .btn-success:hover,
        .btn-success:focus {
            background-color: #0056b3;
            border-color: #0056b3;
        }
    </style>
</head>
<body>

    <% EventoProgramadoDao eventoDao = new EventoProgramadoDao();%>

    <div class="container-fluid">
        <h3 class="text-center">Lista de Eventos Programados</h3>
        <hr>

    </div>
    <!-- Enlace para agregar un nuevo evento programado otro  -->
    <a href="<%=request.getContextPath()%>/EventoProgramadoServlet?action=new" class="btn btn-success">Agregar Nuevo Evento Programado</a> 
    <!-- Enlace para regresar al menú principal -->
    <a href="<%=request.getContextPath()%>/inicio.jsp" class="btn btn-primary">Regresar al Inicio</a>





    <!-- Tabla de eventos programados -->
    <div style="padding: 10px 10px 10px 10px;" class="table-responsive">
        <table id="example" class="table table-responsive table-bordered navbarResponsive" width="100%" cellspacing="0" >
            <thead>
                <tr class="p-3 mb-2 bg-secondary text-white text-center">
                    <th>ID</th>
                    <th>Fecha</th>
                    <th>Hora Inicio</th>
                    <th>Hora Fin</th>
                    <th>Suspendido</th>
                    <th>Reprogramado</th>
                    <th>Fecha Reprogramado</th>
                    <th>Eventos</th>
                    <th>Establecimientos</th>
                    <th>Acciones</th>
                </tr>
            </thead>
            <tbody id="tabla">
                <!-- Iterar sobre la lista de eventos programados -->

                <% ArrayList<Evento_Programado> listaEvt = eventoDao.obtenerTodos();
                    int fila = 0;
                    for (Evento_Programado registro : listaEvt) {

                        SimpleDateFormat format12 = new SimpleDateFormat("hh:mm a"); // Formato AM/PM
                        SimpleDateFormat format24 = new SimpleDateFormat("HH:mm"); // Formato 24 horas

                        String horaInicio12 = format12.format(registro.getHora_inicia());
                        String horaFin12 = format12.format(registro.getHora_fin());

                        String horaInicio24 = format24.format(registro.getHora_inicia());
                        String horaFin24 = format24.format(registro.getHora_fin());

                %>
                <tr id="<%= fila%>">
                    <!-- Mostrar información del evento en cada fila de la tabla -->
                    <td class="row-data"><%= registro.getId_evento_progra()%></td>
                    <td class="row-data"><%= registro.getFecha()%></td>
                    <td class="row-data" data-horainicio24="<%= horaInicio24%>"><%= horaInicio12%></td>
                    <td class="row-data" data-horafin24="<%= horaFin24%>"><%= horaFin12%></td>
                    <td class="row-data"><%= registro.isSuspendido()%></td>
                    <td class="row-data"><%= registro.isReprogramado()%></td>
                    <td class="row-data"><%= registro.getFecha_reprogramado()%></td>
                    <td class="row-data"><%= registro.getId_evento().getnEvento()%></td>
                    <td class="row-data"><%= registro.getEstablecimientos().getN_establecimiento()%></td>
                    <td>
                        <!-- Enlaces para editar y eliminar cada evento programado -->

                        <a class="btn btn-warning text-center click" data-id="<%= registro.getId_evento_progra()%>">Editar</a>


                        <a class="btn btn-danger" onclick="confirmar(<%= registro.getId_evento_progra()%>)">Eliminar</a>
                    </td>
                </tr>
                <%
                        fila++;
                    }
                %>


            </tbody>
        </table>
    </div>


    <!-- Modal ACTUALIZAR -->
    <div class="modal fade" id="modalActualizar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content shadow-lg">
                <div class="modal-header" style="background: #aad9b9;">
                    <h5 class="modal-title" id="staticBackdropLabel">ACTUALIZAR EVENTO PROGRAMADO</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="<%=request.getContextPath()%>/EventoProgramadoServlet?action=update" method="post">
                    <div class="modal-body" style="background: #eeeeef;">

                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" type="text"
                                   class="form-control" 
                                   name="id_evento_prograActualizar"
                                   id="id_evento_prograActualizar" 
                                   placeholder="Ingrese ..." readonly=""
                                   value="<%= request.getAttribute("id_evento_prograActualizar") != null ? request.getAttribute("id_evento_prograActualizar") : ""%>" 
                                   required>
                            <label for="floatingInput">ID</label>
                        </div>

                        <div class="form-group">
                            <label for="fecha">Fecha:</label>
                            <input type="date" class="form-control" id="fechaActualizar" name="fechaActualizar" required
                                   value="<%= request.getAttribute("fechaActualizar") != null ? request.getAttribute("fechaActualizar") : ""%>">
                        </div>
                        <div class="form-group">
                            <label for="horaInicio">Hora Inicio:</label>
                            <input type="time" class="form-control" id="horaInicioActualizar" name="horaInicioActualizar" required
                                   value="<%= request.getAttribute("horaInicioActualizar") != null ? request.getAttribute("horaInicioActualizar") : ""%>">
                        </div>
                        <div class="form-group">
                            <label for="horaFin">Hora Fin:</label>
                            <input type="time" class="form-control" id="horaFinActualizar" name="horaFinActualizar" required
                                   value="<%= request.getAttribute("horaFinActualizar") != null ? request.getAttribute("horaFinActualizar") : ""%>">
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="suspendidoActualizar" name="suspendidoActualizar"
                                   <%= request.getAttribute("suspendidoActualizar") != null && (Boolean) request.getAttribute("suspendidoActualizar") ? "checked" : ""%>>
                            <label class="form-check-label" for="suspendidoActualizar">Suspendido</label>
                        </div>
                        <div class="form-check">
                            <input type="checkbox" class="form-check-input" id="reprogramadoActualizar" name="reprogramadoActualizar"
                                   <%= request.getAttribute("reprogramadoActualizar") != null && (Boolean) request.getAttribute("reprogramadoActualizar") ? "checked" : ""%>>
                            <label class="form-check-label" for="reprogramadoActualizar">Reprogramado</label>
                        </div>
                        <div class="form-group">
                            <label for="fechaReprogramado">Fecha Reprogramado:</label>
                            <input type="date" class="form-control" id="fechaReprogramadoActualizar" name="fechaReprogramadoActualizar"
                                   value="<%= request.getAttribute("fechaReprogramadoActualizar") != null ? request.getAttribute("fechaReprogramadoActualizar") : ""%>">
                        </div>
                        <div class="form-floating mb-3">
                            <select name="nombreEvento" id="nombreEvento" class="form-select" aria-label="Default select example">
                                <option value="seleccionEvento" selected>Seleccione un evento</option>
                                <% ArrayList<String> nombresEventos = eventoDao.obtenerNombresEventos(); %>
                                <% for (String nombreEvt : nombresEventos) {%>
                                <option value="<%=nombreEvt%>" <%= nombreEvt.equals(request.getAttribute("nombreEvento")) ? "selected" : ""%>><%=nombreEvt%></option>
                                <% } %>
                            </select>
                            <label for="nombreEvento">Nombre del Evento</label>
                        </div>

                        <div class="form-floating mb-3">
                            <select name="nombreEstablecimiento" id="nombreEstablecimiento" class="form-select" aria-label="Default select example">
                                <option value="seleccionEstablecimiento" selected>Seleccione un establecimiento</option>
                                <% ArrayList<String> nombresEstablecimientos = eventoDao.obtenerNombresEstablecimiento(); %>
                                <% for (String nombreEstablec : nombresEstablecimientos) {%>
                                <option value="<%=nombreEstablec%>" <%= nombreEstablec.equals(request.getAttribute("nombreEstablec")) ? "selected" : ""%>><%=nombreEstablec%></option>
                                <% }%>
                            </select>
                            <label for="nombreEstablecimiento">Nombre del Establecimento</label>
                        </div>
                        <div class="modal-footer" style="background: #eeeeef;">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" name="accion" value="update" class="btn btn-primary">Actualizar</button>
                        </div>
                </form>
            </div>
        </div>


    </div>

    <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
    <script src="https://code.jquery.com/jquery-3.5.1.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/jquery.dataTables.min.js"></script>
    <script src="https://cdn.datatables.net/1.12.1/js/dataTables.bootstrap5.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.33/dist/sweetalert2.all.min.js"></script>

    <%-- Muestra el mensaje de error si existe --%>
    <script>
                            $(document).ready(function () {
        <% if (request.getAttribute("errorActualizar") != null) {%>
                                // Rellena los campos del formulario con los valores previamente ingresados
                                $("#id_evento_prograActualizar").val("<%= request.getAttribute("id_evento_prograActualizar")%>");
                                $("#fechaActualizar").val("<%= request.getAttribute("fechaActualizar")%>");
                                $("#horaInicioActualizar").val("<%= request.getAttribute("horaInicioActualizar")%>");
                                $("#horaFinActualizar").val("<%= request.getAttribute("horaFinActualizar")%>");
                                $("#suspendidoActualizar").prop('checked', <%= request.getAttribute("suspendidoActualizar")%>);
                                $("#reprogramadoActualizar").prop('checked', <%= request.getAttribute("reprogramadoActualizar")%>);
                                $("#fechaReprogramadoActualizar").val("<%= request.getAttribute("fechaReprogramadoActualizar")%>");
                                $("#nombreEvento").val("<%= request.getAttribute("nombreEvento")%>");
                                $("#nombreEstablecimiento").val("<%= request.getAttribute("nombreEstablec")%>");

                                // Crea el mensaje de error y lo agrega al cuerpo del modal
                                var $errorDiv = $('<div class="alert alert-danger"><%= request.getAttribute("errorActualizar")%></div>');
                                $(".modal-body").prepend($errorDiv);

                                // Abre el modal
                                $("#modalActualizar").modal('show');

                                // Programa la eliminación del mensaje de error después de 10 segundos (10000 milisegundos)
                                setTimeout(function () {
                                    $errorDiv.fadeOut("slow", function () {
                                        $(this).remove();
                                    });
                                }, 5000);
        <% }%>
                            });
    </script>
    <script>
        $(document).ready(function () {
            $('#example').DataTable({
                "language": {
                    "url": "https://cdn.datatables.net/plug-ins/1.12.1/i18n/es-ES.json"
                },
                lengthMenu: [[5, 10, 15, -1], [5, 10, 15, "Todos"]],
                pageLength: 5
            });
        });

        $(function () {
            $(".click").click(function (e) {
                e.preventDefault();
                var idEvento = $(this).data('id');
               // alert("id: " +idEvento);
                var rowId = event.target.parentNode.parentNode.id;
                var data = document.getElementById(rowId).querySelectorAll(".row-data");

                if (data && data.length >= 9) {
                    var horaInicio24 = data[2].getAttribute("data-horainicio24");
                    var horaFin24 = data[3].getAttribute("data-horafin24");
                    var suspendido = data[4].innerText.trim() === 'true' ? 'on' : '';
                    var reprogramado = data[5].innerText.trim() === 'true' ? 'on' : '';

                    var url = "<%=request.getContextPath()%>/mantenimientos/actualizarEventoProgramado.jsp";
                    url += "?id=" + idEvento;
                    url += "&fecha=" + data[1].innerText;
                    url += "&horaInicio=" + horaInicio24;
                    url += "&horaFin=" + horaFin24;
                    url += "&suspendido=" + suspendido;
                    url += "&reprogramado=" + reprogramado;
                    url += "&fechaReprogramado=" + data[6].innerText;
                    url += "&nombreEvento=" + encodeURIComponent(data[7].innerText);
                    url += "&nombreEstablecimiento=" + encodeURIComponent(data[8].innerText);

                    window.location.href = url;
                } else {
                    console.error("La fila no contiene suficientes elementos para acceder a los datos.");
                }
            });
        });



    </script>

    <script>
        function confirmar(idE) {//para eliminar 
            Swal.fire({
                title: '¿Desea eliminar este registro?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Eliminar',
                cancelButtonText: 'Cancelar',
                backdrop: false,
                background: '#eeeeef',
                allowOutsideClick: false
            }).then((result) => {
                if (result.isConfirmed) {
                    Swal.fire({
                        title: '¡Registro eliminado!',
                        icon: 'success',
                        showConfirmButton: false,
                        timer: 1500
                    });
                    // Aquí puedes redirigir o hacer la acción que necesitas
                    window.location = 'EventoProgramadoServlet?action=delete&id=' + idE;
                }
            });
        }
    </script>

    <script>
        document.addEventListener("DOMContentLoaded", function () {
            var formActualizar = document.getElementById("modalActualizar").querySelector("form");

            formActualizar.addEventListener("submit", function (event) {
                if (!validarFormularioActualizar()) {
                    event.preventDefault(); // Previene el envío del formulario si la validación falla
                    // alert("Por favor, corrija los errores antes de continuar.");
                } else {
                    formActualizar.submit(); // Envía el formulario si la validación es correcta
                }
            });
        });

        function validarFormularioActualizar() {
            var fechaActualizar = document.getElementById("fechaActualizar").value;
            var horaInicioActualizar = document.getElementById("horaInicioActualizar").value;
            var horaFinActualizar = document.getElementById("horaFinActualizar").value;
            var fechaReprogramadoActualizar = document.getElementById("fechaReprogramadoActualizar").value;
            var eventoSeleccionado = document.getElementById("nombreEvento").value;
            var establecimientoSeleccionado = document.getElementById("nombreEstablecimiento").value;

            // Validación de la fecha del evento (debe ser hoy o futuro)
            if (new Date(fechaActualizar) < new Date()) {
                alert("La fecha del evento no puede ser anterior a la fecha actual.");
                return false;
            }

            // Validación de la hora de inicio y fin
            if (horaInicioActualizar >= horaFinActualizar) {
                alert("La hora de inicio debe ser anterior a la hora de fin.");
                return false;
            }

            // Validación de la fecha reprogramada si está reprogramado
            if (fechaReprogramadoActualizar && new Date(fechaReprogramadoActualizar) <= new Date(fechaActualizar)) {
                alert("La fecha reprogramada debe ser posterior a la fecha original del evento.");
                return false;
            }

            // Validación de la selección del evento y establecimiento
            if (eventoSeleccionado === "seleccionEvento") {
                alert("Por favor, seleccione un evento.");
                return false;
            }

            if (establecimientoSeleccionado === "seleccionEstablecimiento") {
                alert("Por favor, seleccione un establecimiento.");
                return false;
            }

            // Agrega aquí más validaciones según necesites

            // Si todas las validaciones son correctas, retorna true
            return true;
        }
    </script>

</body>
</html>