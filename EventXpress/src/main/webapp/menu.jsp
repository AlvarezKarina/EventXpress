<%-- 
    Document   : menu
    Created on : 17 sep. 2023, 20:42:26
    Author     : A5155456HP
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ues.edu.entities.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%

       // Usuarios usuario = (Usuarios) request.getAttribute("usuarios");

        Usuarios usuario = (Usuarios) request.getAttribute("usuarios");
        String nomUsuario = usuario.getNombre_u();
        session.setAttribute("usuario", usuario);
         

    %>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-EVSTQN3/azprG1Anm3QDgpJLIm9Nao0Yz1ztcQTwFspd3yD65VohhpuuCOmLASjC" crossorigin="anonymous">
        <script src="https://kit.fontawesome.com/dc1abf70bd.js" crossorigin="anonymous"></script>
        <title>EVENTXPRESS</title>
        <style>
            .modal-backdrop {
                opacity: 0 !important;
                filter: alpha(opacity=0) !important;
            }

            body.dark-mode {
                background-color: #343a40;
                color: white;
            }

            .navbar.dark-mode {
                background-color: #1c1c1c;
            }

            .dropdown-menu.dark-mode {
                background-color: #343a40;
                color: white;
            }
        </style>
    </head>

    <body>
        <header>
            <li class="nav-item">
                <a style="margin-left: 0px; border: none" class="btn btn-outline-light" id="toggleDarkMode"><i class="fas fa-moon"></i> Modo Oscuro</a>
            </li>
            <nav class="navbar navbar-expand-xl navbar-dark bg-dark">
                <div class="container-fluid">
                    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                        <span class="navbar-toggler-icon"></span>
                    </button>
                    <div class="collapse navbar-collapse" id="navbarSupportedContent">
                        <ul class="navbar-nav">
                            <li class="nav-item">
                                <a style="margin-left: -5px; border: none" class="btn btn-outline-light" href="inicio.jsp" target="myFrame"><i class="fas fa-home"></i> Inicio</a>
                            </li>

                            <%                                if (usuario != null) {
                                    if (usuario.isTipo_u()) {
                            %>
                            <li class="nav-item"><a style="margin-left: 0px; border: none" class="btn btn-outline-light" href="ControllerBoletosEstablecimiento?accion=home" target="myFrame"><i class="fa-solid fa-cart-shopping"></i> ComprarBoletos</a></li>

                            <li class="nav-item dropdown">
                                <a style="margin-left: 0px; border: none" class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-gears"></i></i> Mantenimiento
                                </a>
                                <ul text-center style="background: white;" class="dropdown-menu text-center" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="mantenimientos/registrarEventos.jsp" target="myFrame">Eventos</a></li>
                                    <li><a class="dropdown-item" href="mantenimientos/registrarUsuario.jsp" target="myFrame">Usuarios</a></li>
                                    <li><a class="dropdown-item" href="mantenimientos/Establecimientos_Lista.jsp" target="myFrame">Establecimientos</a></li>
                                    <li><a class="dropdown-item" href="mantenimientos/verEventosProgramados.jsp" target="myFrame">Eventos programados</a></li>
                                    <li><a class="dropdown-item" href="mantenimientos/VerZonaEstablecimiento.jsp" target="myFrame">Zonas y establecimientos</a></li>
                                </ul>
                            </li>

                            <li class="nav-item dropdown">
                                <a style="margin-left: 0px; border: none" class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-clipboard"></i> Reportes
                                </a>
                                <ul text-center style="background: white;" class="dropdown-menu text-center" aria-labelledby="navbarDropdown">
                                    <li><a class="dropdown-item" href="reportes/ReporteCategoriasYSusEventos.jsp" target="myFrame">Reporte de categorías y sus eventos</a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteEventosSuspendidosYReprogramados.jsp" target="myFrame">Reporte de eventos suspendidos y reprogramados</a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteGananciasAnuales.jsp" target="myFrame">Reporte de ganancias anuales</a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteVentasEstablecimiento.jsp" target="myFrame">Reporte de ventas ganancias establecimiento</a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteTotalVentaEventosZonas.jsp" target="myFrame">Reporte de ventas de zonas y total general </a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteEventosReprogramadosMes.jsp" target="myFrame">Reporte de eventos reprogramados  por un mes </a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteCapacidadTotalPorZonas_Establecimientos.jsp" target="myFrame">Reporte Capacidad Total Por Zonas Establecimientos </a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteTotalBoletosVendidosPorZona.jsp" target="myFrame">Reporte Total Boletos Vendidos Por Zona </a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteUsuariosSinCompras.jsp" target="myFrame">Reporte de Usuarios Sin Compras </a></li>
                                    <li><a class="dropdown-item" href="reportes/ReporteBoletosNoVendidos.jsp" target="myFrame">Reporte de Boletos no Vendidos </a></li>
                                </ul>
                            </li>

                            <li class="nav-item dropdown">
                                <a style="margin-left: 0px; border: none" class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-clipboard-question"></i> Consultas
                                </a>
                                <ul style="background: white;" class="dropdown-menu text-center" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="consultas/Fecha_Evento_Programado.jsp" target="myFrame">Eventos en una fecha específica</a>
                                    <a class="dropdown-item" href="ControllerBoletos" target="myFrame">Lista de usuarios que han comprado boletos</a>
                                    <a class="dropdown-item" href="ControllerTotalBoletosVendidos" target="myFrame">Total de boletos vendidos por evento</a>
                                    <a class="dropdown-item" href="zona_establec" target="myFrame">Capacidad total de las zonas en los establecimientos</a>                                  
                                    <a class="dropdown-item" href="ControllerCategoriaTotal" target="myFrame">Totales de ingresos de cada categoría</a>
                                    <a class="dropdown-item" href="consultas/eventosCompradosPorUsuarios.jsp" target="myFrame">Lista de boletos comprados por un usuario en específico</a>
                                    <a class="dropdown-item" href="consultas/mostrar_evento.jsp" target="myFrame">Buscar evento por Id de categoría</a>
                                    <a class="dropdown-item" href="consultas/Boletos_Comprado_Establecimiento.jsp" target="myFrame">Ver boletos vendidos en un establecimiento</a>
                                    <a class="dropdown-item" href="ControllersCompraEntradas" target="myFrame">Personas que compraron entradas para más de un evento</a>
                                    <a class="dropdown-item" href="BusquedaEventosController" target="myFrame">Buscar eventos por nombre</a>
                                </ul>
                            </li>

                            <li class="nav-item">
                                <a style="margin-left: 0px; border: none" class="btn btn-outline-light" onclick="confirmarBackup()">
                                    <i class="fa-solid fa-database"></i> Backup
                                </a>
                            </li>



                            <% } else {
                            %>
                            <!-- CAMBIE EL TIPO DE BOTON -->
                            <li class="nav-item"><a style="margin-left: 0px; border: none" class="btn btn-outline-light" href="ControllerBoletosEstablecimiento?accion=home" target="myFrame"><i class="fa-solid fa-cart-shopping"></i> ComprarBoletos</a></li>

                            <%
                                    }
                                }%>



                            <!-- Reportes para el Vendedor-->     
                            <%
                                if (usuario != null) {
                                    if (usuario.isTipo_u() == true) {
                            %>                        


                            <% }
                                }%>
                            <li class="nav-item">
                                <a style="margin-left: 0px; border: none" class="btn btn-outline-light" data-bs-toggle="modal" data-bs-target="#myModal"><i class="fa-solid fa-circle-info"></i> Acerca de</a>
                            </li> 
                            <li class="nav-item dropdown">
                                <a style="margin-left: 2px; border: none" class="btn btn-outline-light dropdown-toggle" href="#" id="navbarDropdown" role="button" data-bs-toggle="dropdown" aria-expanded="false">
                                    <i class="fa-solid fa-user"></i><%= usuario.getEmail()%>
                                </a>
                                <ul style="margin-left: -90px; background: white;" class="dropdown-menu text-center" aria-labelledby="navbarDropdown">
                                    <a class="dropdown-item" href="#">
                                        <img src="img/usuario.png" alt="60" width="60"/>
                                    </a>
                                    <a class="dropdown-item"><%= usuario.getNombre_u() + " " + usuario.getApellido_u()%></a><!-- comment -->
                                    <a class="dropdown-item"><%= usuario.getEmail()%></a>
                                    <%  if (usuario.isTipo_u() == true) {%>
                                    <a class="dropdown-item">Administrador</a>
                                    <%
                                    } else {%>
                                    <a class="dropdown-item">Cliente</a>
                                    <%
                                        }
                                    %>
                                    <a class="dropdown-item" >${usuario.getEmail()}</a>
                                    <div class="dropdown-divider"> </div>
                                    <button style="border: none" onclick="confirmar()" class="btn btn-dark">Cerrar Sesión</button>
                                </ul>
                            </li>


                        </ul>
                    </div>
                </div>

            </nav>

            <div class="m-3 embed-responsive" style="height: 570px">
                <iframe name="myFrame" class="embed-responsive-item" style="height: 100%; width: 100%; border: none" src="inicio.jsp"></iframe>
            </div>

            <footer>
                <div class="container-fluid" style="padding: 0px 0px 0px 40px;">
                    &copy; Sistema para la gestión de boleteria
                </div>
            </footer>

            <!-- Modal -->
            <div class="modal fade" id="myModal" data-bs-backdrop="static" data-bs-keyboard="false" tabindex="-1" aria-labelledby="staticBackdropLabel" aria-hidden="true">
                <div class="modal-dialog">
                    <div class="modal-content shadow-lg">
                        <div style="background: #bccaf4" class="modal-header">
                            <h5 class="modal-title" id="tituloModal">ACERCA DE</h5>
                            <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                        </div>
                        <div style="background: #eeeeef" class="modal-body">                        
                            <div class="container-fluid" style="padding: 5px 5px 5px 5px;">
                                <b>
                                    <label style="margin: 0px 15px">SISTEMA DESARROLLADO POR :</label>
                                </b>
                            </div>

                            <div class="container-fluid" style="padding: 10px 5px 5px 5px;">
                                <div class="form-group d-flex" style="margin: 0px 25px">
                                    <img src="img/desarrolladores.png" alt="" style="width: 8%; height: 8%"/>
                                    <label style="margin: 0px 8px; padding: 5px 0px 0px 0px;">Daniela Alexandra Argumedo Cruz AC22065</label>
                                </div>
                            </div>
                            <div class="container-fluid" style="padding: 10px 5px 5px 5px;">
                                <div class="form-group d-flex" style="margin: 0px 25px">
                                    <img src="img/desarrolladores.png" alt="" style="width: 8%; height: 8%"/>
                                    <label style="margin: 0px 8px; padding: 5px 0px 0px 0px;">Karina Elizabeth Alvarez Alfaro AA22027</label>
                                </div>
                            </div>
                            <div class="container-fluid" style="padding: 10px 5px 5px 5px;">
                                <div class="form-group d-flex" style="margin: 0px 25px">
                                    <img src="img/desarrolladores.png" alt="" style="width: 8%; height: 8%"/>
                                    <label style="margin: 0px 8px; padding: 5px 0px 0px 0px;">Oscar Alberto Cisneros Escobar CE22006</label>
                                </div>
                            </div>
                            <div class="container-fluid" style="padding: 10px 5px 5px 5px;">
                                <div class="form-group d-flex" style="margin: 0px 25px">
                                    <img src="img/desarrolladores.png" alt="" style="width: 8%; height: 8%"/>
                                    <label style="margin: 0px 8px; padding: 5px 0px 0px 0px;">Nelkin Florentino Cornejo Navarro CN22010</label>
                                </div>
                            </div>
                            <div class="container-fluid" style="padding: 10px 5px 5px 5px;">
                                <div class="form-group d-flex" style="margin: 0px 25px">
                                    <img src="img/desarrolladores.png" alt="" style="width: 8%; height: 8%"/>
                                    <label style="margin: 0px 8px; padding: 5px 0px 0px 0px;">Gerson Antonio Vasquez Hernandez VH22004</label>
                                </div>
                            </div>
                        </div>
                    </div>
                </div>
            </div>


            <script src="https://ajax.googleapis.com/ajax/libs/jquery/2.1.3/jquery.min.js"></script>
            <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.9.2/dist/umd/popper.min.js" integrity="sha384-IQsoLXl5PILFhosVNubq5LC7Qb9DXgDA9i+tQ8Zj3iwWAwPtgFTxbJ8NT4GN1R8p" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.0.2/dist/js/bootstrap.min.js" integrity="sha384-cVKIPhGWiC2Al4u+LWgxfKTRIcfu0JTxR+EQDz/bgldoEyl4H0zUF0QKbrJ0EcQF" crossorigin="anonymous"></script>
            <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11.4.33/dist/sweetalert2.all.min.js"></script>
            <script>
                                        function confirmar() {
                                            Swal.fire({
                                                title: '¿Desea cerrar sesión?',
                                                icon: 'warning',
                                                showCancelButton: true,
                                                confirmButtonColor: '#3085d6',
                                                cancelButtonColor: '#d33',
                                                confirmButtonText: 'Salir',
                                                cancelButtonText: 'Cancelar',
                                                backdrop: false,
                                                background: '#eeeeef',
                                                allowOutsideClick: false
                                            }).then((result) => {
                                                if (result.isConfirmed) {
                                                    window.location = 'login.jsp';
                                                }
                                            });
                                        }
            </script>
            <script>
                function backupConfirmation() {
                    let swalOptions = {
                        title: '¿Desea generar el Backup?',
                        icon: 'question',
                        showCancelButton: true,
                        confirmButtonColor: '#3085d6',
                        cancelButtonColor: '#808080',
                        confirmButtonText: 'Generar',
                        cancelButtonText: 'Cancelar',
                        backdrop: 'rgba(0,0,0,0)',
                        allowOutsideClick: false
                    };
                    Swal.fire(swalOptions).then((result) => {
                        if (result.isConfirmed) {
                            let ajaxOptions = {
                                url: "BackupController",
                                method: "POST"
                            };
                            $.ajax(ajaxOptions);
                        }
                    });
                }
            </script>
            <script>
                document.getElementById('toggleDarkMode').addEventListener('click', function () {
                    document.body.classList.toggle('dark-mode'); // Esto activa o desactiva el modo oscuro
                    // Aquí puedes agregar más elementos para cambiar, como la barra de navegación o menús desplegables.
                    document.querySelector('.navbar').classList.toggle('dark-mode');
                    var dropdownMenus = document.querySelectorAll('.dropdown-menu');
                    dropdownMenus.forEach(function (menu) {
                        menu.classList.toggle('dark-mode');
                    });
                });
            </script>
    </body>
</html>