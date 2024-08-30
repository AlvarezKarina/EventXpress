<%-- 
    Document   : registrarUsuario
    Created on : 19 sep. 2023, 09:05:57
    Author     : A5155456HP
--%>

<%@page import="com.ues.edu.model.dao.EventoProgramadoDao"%>
<%@page import="com.ues.edu.entities.Categoria"%>
<%@page import="com.ues.edu.entities.Eventos"%>
<%@page import="com.ues.edu.model.dao.EventoDao"%>
<%@page import="com.ues.edu.model.dao.CategoriaDao"%>
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
        CategoriaDao daoCat = new CategoriaDao();
        //  LocalDate todaysDate = LocalDate.now();
        // User user = (User) request.getAttribute("user");
        EventoDao daoEvt = new EventoDao();

        //modelo.dao.Dao_CategoriaLibro daoCL = new Dao_CategoriaLibro();
        //   ArrayList<Categorialibro> listCL = daoCL.getCategoriaLibros();
        ArrayList<Categoria> listaCat = daoCat.selectAll();

    %>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>COMPRAR BOLETOS</title>
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
            <nav style="background: #515253" class="navbar navbar-dark">
                <div class="container">
                    <a class="navbar-brand" href="#">
                        <label>COMPRAR BOLETOS</label>
                    </a>
                </div>
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
            </div>
            <label style="text-align: justify;" >AQUI APARECERA EL USUARIO</label>
            <div style="padding: 10px 10px 10px 10px;" class="table-responsive">
                
            </div>
            <div style="width: 100%; height: 50px;" class="container-fluid">
                <label style="text-align: justify;" >Seleccionar evento:</label>
                <button type="button" class="btn btn-primary" data-bs-toggle="modal" data-bs-target="#myModal">
                    Evento
                </button>
            </div>
        </div>



        <!-- Modal -->
        <div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
            <div class="modal-dialog">
                <div class="modal-content shadow-lg">
                    <div style="background: #bccaf4" class="modal-header">
                        <h5 class="modal-title" id="tituloModal">NUEVO EVENTO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <div class="form-floating mb-3">
                    <select name="nombreEvento" class="form-select" aria-label="Default select example">
                        <option value="seleccionEvento" selected>Seleccione un evento</option>
                        <% EventoProgramadoDao eventoDao = new EventoProgramadoDao(); %>
                        <% ArrayList<String> nombresEventos = eventoDao.obtenerNombresEventos(); %>
                        <% for (String nombreEvento : nombresEventos) {%>
                        <option value="<%=nombreEvento%>">
                            <%=nombreEvento%>
                        </option>
                        <% }%>
                    </select>
                </div>
                    <form action="ControllerEventos" method="post">
                        <div style="background: #eeeeef" class="modal-body">
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="text" class="form-control" name="nombre" placeholder="Ingrese ..." pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$" title="Solo letras." required>
                                <label for="floatingInput">Nombre</label>
                            </div>


                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="text" class="form-control" name="descripcion" placeholder="Ingrese ..." required>
                                <label for="floatingInput">Descripcion</label>
                            </div>

                            <div class="form-floating mb-3">
                                <select name="codiCat" id="codiCat" class="form-select" aria-label="Default select example">
                                    <option value="seleccionRol" selected>Seleccione una categoria</option>
                                    <%for (Categoria registro : daoCat.selectAll()) {%>

                                    <%
                                        if (registro.getCategoria().equals("A")) {
                                    %>
                                    <option value="<%=registro.getIdCategoria()%>">
                                        TODA LA FAMILIA
                                    </option>
                                    <%
                                    } else if (registro.getCategoria().equals("B") == true) {
                                    %>
                                    <option value="<%=registro.getIdCategoria()%>">
                                        MAYORES DE 14 AÑOS
                                    </option>
                                    <%
                                    } else if (registro.getCategoria().equals("C") == true) {
                                    %>
                                    <option value="<%=registro.getIdCategoria()%>">
                                        MAYORES DE 18 AÑOS
                                    </option>
                                    <%
                                            }

                                        }%>
                                </select>
                            </div>         
                                <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="number" class="form-control" name="cantidad" placeholder="Ingrese ..." required>
                                <label for="floatingInput">Cantidad</label>
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
                        <h5 class="modal-title" id="staticBackdropLabel">ACTUALIZAR EVENTO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="ControllerEventos" method="post">
                        <div style="background: #eeeeef" class="modal-body">
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="text"
                                       class="form-control" 
                                       name="idEvento"
                                       id="idEv"
                                       placeholder="Ingrese ..." readonly="" required>
                                <label for="floatingInput">ID</label>
                            </div>



                            <div class="form-floating mb-3">
                                <div class="inputform">
                                    <label>Categoria</label>
                                    <select name="codigoCategoria" 
                                            id="idCate">
                                        <option>Seleccione</option>
                                        <option value="TODA LA FAMILIA">A</option><!-- EN EL VALUE DEBE APARECER LA OPCION QUE ESTA EN LA COLUMNA ES DECIR, SI  QUIERO QUE TODA LA FAMILIA SALGA SELECCIONADA CUANDO EDITO ESTA OPCION DEBE APARECER PERO EN EL VALUE DE UNA DE LAS OPCIONES -->
                                        <option value="MAYORES DE 18 AÑOS">B</option>
                                        <option value="MAYORES DE 14 AÑOS">C</option>

                                        <% for (Categoria registro : daoCat.selectAll())
                                                if (registro.getCategoria().equals("A")) {
                                        %>
                                        <option value="<%=registro.getIdCategoria()%>">
                                            TODA LA FAMILIA
                                        </option>
                                        <%
                                        } else if (registro.getCategoria().equals("B") == true) {
                                        %>
                                        <option value="<%=registro.getIdCategoria()%>">
                                            MAYORES DE 14 AÑOS
                                        </option>
                                        <%
                                        } else if (registro.getCategoria().equals("C") == true) {
                                        %>
                                        <option value="<%=registro.getIdCategoria()%>">
                                            MAYORES DE 18 AÑOS
                                        </option>
                                        <%
                                            }
                                        %>


                                    </select>



                                </div>
                            </div>
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="text" 
                                       class="form-control" 
                                       name="nombreEvento"
                                       id="nombreE"
                                       placeholder="Ingrese un nombre" pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$" title="Solo letras." required>
                                <label for="floatingInput">Nombre de evento</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" 
                                       type="text" class="form-control"
                                       name="descripcionEvento" id="descripcionE" 
                                       placeholder="Descripcion del evento" required>
                                <label for="floatingInput">Descripcion</label>
                            </div>
                            <div style="background: #eeeeef" class="modal-footer">
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
                    document.getElementById("idEv").value = data[0].innerHTML;

                    document.getElementById("nombreE").value = data[1].innerHTML;
                    document.getElementById("descripcionE").value = data[2].innerHTML;
                    document.getElementById("idCate").value = data[3].innerHTML;
                    alert(data[3].innerHTML);
                    $("#modalActualizar").modal('show');
                });
            });



        </script>
        <script>
            function confirmar(idE) {
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
                    alert("eliminar " + idE);
                    if (result.isConfirmed) {
                        window.location = 'ControllerEventos?accion=delete&idE=' + idE;
                    }
                });
            }
        </script>
    </body>
</html>
