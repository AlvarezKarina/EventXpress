<%-- 
    Document   : Establecimientos_Lista
    Created on : 19 nov. 2023, 00:06:22
    Author     : karie
--%>


<%@page import="com.ues.edu.entities.Establecimiento"%>
<%@page import="com.ues.edu.model.dao.Dao_Establecimiento"%>
<%@page import="java.time.LocalDate"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.Date"%>
<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%
        Dao_Establecimiento dao_establec = new Dao_Establecimiento();

        //  LocalDate todaysDate = LocalDate.now();
        // User user = (User) request.getAttribute("user");
        //UserDAO daoUser = new UserDAO();
        //modelo.dao.Dao_CategoriaLibro daoCL = new Dao_CategoriaLibro();
        //   ArrayList<Categorialibro> listCL = daoCL.getCategoriaLibros();
        //ArrayList<Establecimiento> listRol = dao_establec.selectAllEstablec();

    %>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CRUD ESTABLECIMIENTO</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet"/>
        <style>
            .modal-backdrop {
                opacity: 0 !important;
                filter: alpha(opacity=0) !important;
            }
        </style>
    </head>
    <body>
        <div style="height: 10px;" class="container-fluid"></div>
        <div class="container-fluid">
            <nav style="background: #3ea5c4; display: flex; justify-content: center; align-items: center; height: 60px;" class="navbar navbar-dark">
                <a class="navbar-brand" href="#" style="font-size: 24px;">
                    <label>E S T A B L E C I M I E N T O S</label>
                </a>
            </nav>
        </div>


        <div class="container-fluid">
            <div style="width: 100%; height: 20px;" class="container-fluid">
            </div>
            <!-- Button trigger modal -->
            <div style="width: 100%; height: 50px;" class="container-fluid">
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
                    Nuevo
                </button>
                <button type="button" class="btn btn-success" id="estado">
                    Dar de Alta
                </button>
            </div>


            <div style="padding: 10px 10px 10px 10px;" class="table-responsive">
                <table id="example" class="table table-responsive table-bordered navbarResponsive" width="100%" cellspacing="0" >
                    <thead>
                        <tr class="p-3 mb-2 bg-secondary text-white text-center">
                            <th class="text-center">ID</th>
                            <th class="text-center">NOMBRE</th>
                            <th class="text-center">DIRECCION</th>
                            <th class="text-center">CAPACIDAD</th>
                            <th class="text-center">FECHA ALTA</th>
                            <th class="text-center">ACCIONES</th>

                        </tr>
                    </thead>
                    <tbody id="tabla">
                        <% ArrayList<Establecimiento> lstEstablec = dao_establec.selectAllEstablec();
                            int fila = 0;
                            for (Establecimiento registro : lstEstablec) {
                        %>
                        <tr id="<%= fila%>">
                            <td class="row-data"><%= registro.getId_establecimiento()%></td>
                            <td class="row-data"><%= registro.getN_establecimiento()%></td>
                            <td class="row-data"><%= registro.getDireccion()%></td>
                            <td class="row-data"><%= registro.getCapacidad_establec()%></td>
                            <td class="row-data"><%= registro.getFecha_activacion()%></td>

                            <td>
                                <a class="btn btn-warning text-cente click" id="editar"  onclick="editarReg(<%= registro.getId_establecimiento()%>)">Editar</a>
                                <a class="btn btn-danger" onclick="confirmar(<%= registro.getId_establecimiento()%>)">Dar de baja</a>
                            </td>
                        </tr>
                        <% fila++;
                            }%>
                    </tbody>
                </table>
            </div>
        </div>



        <!-- Modal -->
        <div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content shadow-lg">
                    <div style="background: #bccaf4" class="modal-header">
                        <h5 class="modal-title" id="tituloModal">NUEVO ESTABLECIMIENTO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="../Controller_Establecimiento" method="post">
                        <div style="background: #eeeeef" class="modal-body">
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="text" class="form-control" name="nombre" placeholder="Ingrese ..." required>
                                <label for="floatingInput">Nombre</label>
                            </div>


                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="text" class="form-control" name="direccion" placeholder="Ingrese ..." required>
                                <label for="floatingInput">Direcion</label>
                            </div>

                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="number" class="form-control" name="capacidad" placeholder="Ingrese ..."  title="" required>
                                <label for="floatingInput">Capacidad</label>
                            </div>


                        </div>
                        <div style="background: #eeeeef" class="modal-footer">
                            <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                            <button type="submit" name="accion" value="insert" class="btn btn-primary">Guardar</button>
                        </div>
                    </form>
                </div> 
            </div>
        </div>




        <!-- Modal ACTUALIZAR -->
        <div class="modal fade" id="modalActualizar" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog modal-dialog-centered">
                <div class="modal-content shadow-lg">
                    <div style="background: #aad9b9" class="modal-header">
                        <h5 class="modal-title" id="staticBackdropLabel">ACTUALIZAR ESTABLECIMIENTO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="../Controller_Establecimiento" method="post">
                        <div style="background: #eeeeef" class="modal-body">
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="number"
                                       class="form-control" 
                                       name="idEstablec" disabled="idEstablec"
                                       id="idE" 
                                       <label for="floatingInput">ID</label>

                            </div>
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="text" 
                                       class="form-control" 
                                       name="nombre"
                                       id="nombreE"
                                       placeholder="Ingrese ..."  required>
                                <label for="floatingInput">Nombre</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" 

                                       class="form-control"

                                       name="direccion" 
                                       id="direccionE" 
                                       placeholder="Ingrese ..." required>
                                <label for="floatingInput">Direccion</label>
                            </div>


                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" 

                                       class="form-control"

                                       name="capacidad" 
                                       id="capacidad" 
                                       placeholder="Ingrese ..." required>
                                <label for="floatingInput">Capacidad</label>
                            </div>


                            <div style="background: #eeeeef" class="modal-footer">
                                <button type="button" class="btn btn-danger" data-bs-dismiss="modal">Cancelar</button>
                                <button type="submit" name="accion" value="update" class="btn btn-primary">Actualizar</button>
                            </div>
                    </form>
                </div> 
            </div>
        </div>
    </div>

    <!-- Modal Listas -->
    <div class="modal fade" id="md_cliente" data-backdrop="static" data-keyboard="false" tabindex="-1" role="dialog" aria-labelledby="exampleModalLabel" aria-hidden="true" data-bs-keyboard="false" data-bs-backdrop="static" >

        <div class="modal-dialog modal-lg" role="document" >
            <div class="modal-content" style="background-color: #eeeeef;">

                <div style="background: #eeeeef" class="modal-header">
                    <h5 class="modal-title" id="tituloModal">Dar de Alta nuevo establecimiento</h5>
                    <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                </div>
                <div style="padding: 10px 10px 10px 10px;" class="table-responsive">
                    <table id="example" class="table table-responsive table-bordered navbarResponsive" width="100%" cellspacing="0" >
                        <thead>
                            <tr class="p-3 mb-2 bg-secondary text-white text-center">
                                <th class="text-center">ID</th>
                                <th class="text-center">NOMBRE</th>
                                <th class="text-center">DIRECCION</th>
                                <th class="text-center">CAPACIDAD</th>
                                <th class="text-center">FECHA BAJA</th>

                            </tr>
                        </thead>
                        <tbody id="tabla">
                            <% ArrayList<Establecimiento> lstEstablec1 = dao_establec.selectAllEstablec2();
                                int fila1 = 0;
                                for (Establecimiento registro : lstEstablec1) {
                            %>
                            <tr id="<%= fila1%>">
                                <td class="row-data"><%= registro.getId_establecimiento()%></td>
                                <td class="row-data"><%= registro.getN_establecimiento()%></td>
                                <td class="row-data"><%= registro.getDireccion()%></td>
                                <td class="row-data"><%= registro.getCapacidad_establec()%></td>
                                <td class="row-data"><%= registro.getFecha_de_baja()%></td>
                                <td>

                                    <a class="btn btn-success text-cente" id="alta"  onclick="alta(<%= registro.getId_establecimiento()%>)">Dar Alta</a>
                                </td>
                            </tr>
                            <% fila1++;
                                }%>
                        </tbody>
                    </table>
                </div>
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
                                                document.getElementById("idE").value = data[0].innerHTML;
                                                document.getElementById("nombreE").value = data[1].innerHTML;
                                                document.getElementById("direccionE").value = data[2].innerHTML;
                                                document.getElementById("capacidad").value = data[3].innerHTML;

                                                $("#modalActualizar").modal('show');
                                            });


                                        });




    </script>
    <script>
        $(document).on("click", "#estado", function (e) {
            e.preventDefault();
            console.log("Capturando click ");
            $('#md_cliente').modal('show');//levantar el modal
        });

        function confirmar(idE) {
            Swal.fire({
                title: '¿Desea dar de baja este registro?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Dar de baja',
                cancelButtonText: 'Cancelar',
                backdrop: false,
                background: '#eeeeef',
                allowOutsideClick: false
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location = '../Controller_Establecimiento?accion=delete&idEstablec=' + idE;
                }
            });
        }

        function alta(idE) {

            Swal.fire({
                title: '¿Desea dar de alta este registro?',
                icon: 'warning',
                showCancelButton: true,
                confirmButtonColor: '#3085d6',
                cancelButtonColor: '#d33',
                confirmButtonText: 'Dar del Alta',
                cancelButtonText: 'Cancelar',
                backdrop: false,
                background: '#eeeeef',
                allowOutsideClick: false
            }).then((result) => {
                if (result.isConfirmed) {
                    window.location = '../Controller_Establecimiento?accion=altaE&idEstablec=' + idE;
                }
            });
        }
    </script>


</body>
</html>

