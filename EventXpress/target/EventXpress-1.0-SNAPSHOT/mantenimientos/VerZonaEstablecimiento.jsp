<%-- 
    Document   : VerZonaEstablecimiento
    Created on : 22 nov. 2023, 14:30:24
    Author     : nelki
--%>


<%@page import="com.ues.edu.entities.Zona_Establec"%>
<%@page import="com.ues.edu.model.dao.ZonaEstablecimientoDao"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<!DOCTYPE html>

<head>

    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <<title>Lista de zonas establecimiento</title>
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
    <% ZonaEstablecimientoDao zonaEstabDao = new ZonaEstablecimientoDao(); %>

    <div class="container-fluid">
        <nav style="background: #3ea5c4; display: flex; justify-content: center; align-items: center; height: 60px;" class="navbar navbar-dark">
            <h3 style="font-size: 24px; color: white; margin: 0;">
                Lista de zonas establecimientos
            </h3>
        </nav>
    </div>


    <!-- Tabla de zonas establecimiento -->
    <div style="padding: 10px 10px 10px 10px;" class="table-responsive">
        <table id="example" class="table table-responsive table-bordered navbarResponsive" width="100%" cellspacing="0" >
            <thead>
                <tr class="p-3 mb-2 bg-secondary text-white text-center">
                    <th class="text-center">ID</th>
                    <th class="text-center">PRECIO</th>
                    <th class="text-center">CAPACIDAD ZONA</th>
                    <th class="text-center">ZONA</th>
                    <th class="text-center">ESTABLECIMIENTO</th>                          
                    <th class="text-center">ACCIONES</th>  
                </tr>
            </thead>
            <tbody id="tabla">
                <!-- Iterar sobre la lista de eventos programados -->

                <% ArrayList<Zona_Establec> lisZonaE = zonaEstabDao.obtenerTodos();
                    int fila = 0;
                    for (Zona_Establec registro : lisZonaE) {
                %>
                <tr id="<%= fila%>">
                    <td class="row-data"><%= registro.getIdZonaEstablecimiento()%></td>
                    <td class="row-data">$<%= registro.getPrecio()%></td>
                    <td class="row-data"><%= registro.getCapacidadZona()%></td>
                    <td class="row-data"><%= registro.getIdZona().getNombreZonas()%></td>
                    <td class="row-data"><%= registro.getIdEstablecimiento().getN_establecimiento()%></td>
                    <td>
                        <!-- Enlaces para editar y eliminar cada evento programado -->
                        <a class="btn btn-warning text-center click" id="editar" onclick="editarReg(<%= registro.getIdZonaEstablecimiento()%>)">Editar</a>
                        <a class="btn btn-danger" onclick="confirmar(<%= registro.getIdZonaEstablecimiento()%>)">Eliminar</a>
                    </td>
                </tr>

                <% fila++;
                    }%>

            </tbody>
        </table>
    </div>


    <!-- MODAL ACTUALIZAR -->
    <div class="modal fade" id="modalActualizar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
        <div class="modal-dialog modal-dialog-centered">
            <div class="modal-content shadow-lg">
                <div class="modal-header" style="background: #aad9b9;">
                    <h5 class="modal-title" id="staticBackdropLabel">ACTUALIZAR ZONA ESTABLECIMIENTO</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <form action="<%=request.getContextPath()%>/ZonaEstablecimientoServlet?action=update" method="post">
                    <div class="modal-body" style="background: #eeeeef;">
                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" type="text"
                                   class="form-control" 
                                   name="id_zona_establecActu"
                                   id="id_zona_establecActu" 
                                   placeholder="Ingrese ..." readonly="" required>
                            <label for="floatingInput">ID</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" 
                                   type="number" class="form-control"
                                   name="precioActu" 
                                   id="precioActu" 
                                   placeholder="Precio del evento" required
                                   max="9999999999">
                            <label for="floatingInput">Precio</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" 
                                   type="number" class="form-control"
                                   name="capacidadZonaActu" 
                                   id="capacidadZonaActu" 
                                   placeholder="Capacidad de la zona" required
                                   max="9999999999">
                            <label for="floatingInput">Capacidad de la zona</label>
                        </div>

                        <!-- Combo para seleccionar el nombre de la zona -->
                        <div class="form-floating mb-3">
                            <select id="NombreZona" name="nombreZona" class="form-select" aria-label="Default select example">
                                <option value="seleccioneZona" selected>Seleccione una zona</option>
                                <% ArrayList<String> nombresAcZonas = zonaEstabDao.obtenerNombresZona(); %>
                                <% for (String nombreZona : nombresAcZonas) {%>
                                <option value="<%=nombreZona%>">
                                    <%=nombreZona%>
                                </option>
                                <% }%>
                            </select>
                            <label for="nombreZona">Tipo de zona</label>
                        </div>
                        <!-- Combo para seleccionar el nombre del establecimiento -->
                        <div class="form-floating mb-3">
                            <select id="nombreEstablec" name="nombreEstablec" class="form-select" aria-label="Default select example">
                                <option value="seleccioneEstablec" selected>Seleccione un establecimiento</option>
                                <% ArrayList<String> nombresAcEstablec = zonaEstabDao.obtenerNombresEstablec(); %>
                                <% for (String nombreEstablec : nombresAcEstablec) {%>
                                <option value="<%=nombreEstablec%>">
                                    <%=nombreEstablec%>
                                </option>
                                <% }%>
                            </select>
                            <label for="nombreEstablec">Nombre del establecimiento</label>
                        </div>
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
                                    var rowId = event.target.parentNode.parentNode.id;
                                    var data = document.getElementById(rowId).querySelectorAll(".row-data");
                                    var precio = data[1].innerHTML.trim(); // Asumiendo que data[1] contiene el precio



                                    if (data && data.length >= 5) {
                                        document.getElementById("id_zona_establecActu").value = data[0].innerHTML;
                                        document.getElementById("precioActu").value = precio.replace("$", ""); // Elimina el signo de dólar al cargarlo en el modal
                                        document.getElementById("capacidadZonaActu").value = data[2].innerHTML;
                                        document.getElementById("NombreZona").value = data[3].innerHTML;
                                        document.getElementById("nombreEstablec").value = data[4].innerHTML;

                                        //alert(data[3].innerHTML);

                                        $("#modalActualizar").modal('show');

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
                    window.location = '../ZonaEstablecimientoServlet?action=delete&id=' + idE;
                }
            });
        }
    </script>

    <!-- Enlace para agregar un nuevo evento programado otro  -->
    <a href="<%=request.getContextPath()%>/ZonaEstablecimientoServlet?action=new" class="btn btn-success">Agregar Nueva Zona Establecimiento</a> 

</body>

