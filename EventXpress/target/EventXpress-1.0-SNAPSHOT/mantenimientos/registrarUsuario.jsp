<%-- 
    Document   : usuarios
    Created on : 14 nov 2023, 10:09:34
    Author     : alexa
--%>

<%@page import="com.ues.edu.entities.Usuarios"%>
<%@page import="com.ues.edu.model.dao.UserDao"%>
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

        //  LocalDate todaysDate = LocalDate.now();
        // User user = (User) request.getAttribute("user");
        UserDao daoUser = new UserDao();

        //modelo.dao.Dao_CategoriaLibro daoCL = new Dao_CategoriaLibro();
        //   ArrayList<Categorialibro> listCL = daoCL.getCategoriaLibros();
        //ArrayList<Cargo> listRol = daoRol.selectAllCargos();

    %>
    <head>
        <meta charset="utf-8">
        <meta name="viewport" content="width=device-width, initial-scale=1">
        <title>CRUD USUARIO</title>
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
                    <label>U S U A R I O S</label>
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
            </div>

            <div style="padding: 10px 10px 10px 10px;" class="table-responsive">
                <table id="example" class="table table-responsive table-bordered navbarResponsive" width="100%" cellspacing="0" >
                    <thead>
                        <tr class="p-3 mb-2 bg-secondary text-white text-center">
                            <th class="text-center">DUI</th>
                            <th class="text-center">NOMBRE</th>
                            <th class="text-center">APELLIDO</th>
                            <th class="text-center">EMAIL</th>
                            <th class="text-center">FECHA NACIMIENTO</th>
                            <th class="text-center">GENERO</th>
                            <th class="text-center">TIPO USUARIO</th>
                            <th class="text-center">ACCIONES</th>
                        </tr>
                    </thead>
                    <tbody id="tabla">
                        <% List<Usuarios> lstUser = daoUser.selectAllUsers();
                            int fila = 0;
                            for (Usuarios registro : lstUser) {
                        %>
                        <tr id="<%= fila%>">
                            <td class="row-data"><%= registro.getDui()%></td>
                            <td class="row-data"><%= registro.getNombre_u()%></td>
                            <td class="row-data"><%= registro.getApellido_u()%></td>
                            <td class="row-data"><%= registro.getEmail()%></td>
                            <td class="row-data"><%= registro.getFecha_nac()%></td>
                            <td class="row-data"><%= registro.getGenero_u()%></td>
                            <%
                                if (registro.isTipo_u() == true) {
                            %>
                            <td class="row-data">Administrador</td>

                            <%
                            } else {
                            %>
                            <td class="row-data">Usuario Comun</td>

                            <%
                                }
                            %>

                            <td>
                                <a class="btn btn-warning text-cente click" id="editar"  onclick="editarReg(<%= registro.getDui()%>)">Editar</a>
                                <a class="btn btn-danger" onclick="confirmar('<%= registro.getDui()%>')">Eliminar</a>
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
                        <h5 class="modal-title" id="tituloModal">NUEVO USUARIO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="../UserServelet" method="post">
                        <div style="background: #eeeeef" class="modal-body">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" name="dui" id="dui" placeholder="Ingrese ..." pattern="\d{8}-\d{1}" title="Formato válido: 12345678-9" oninput="validarDUI()" required>                               
                                <label for="floatingInput">Dui</label>
                            </div>
                        </div>
                        <form action="UserServlet" method="post">
                            <div style="background: #eeeeef" class="modal-body">
                                <div class="form-floating mb-3">
                                    <input style="background: #eeeeef" type="text" class="form-control" name="nombre_u" placeholder="Ingrese ..." pattern="^[a-zA-Z\s'\-]+$" title="Solo letras." required>                                    
                                    <label for="floatingInput">Nombre</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input style="background: #eeeeef" type="text" class="form-control" name="apellido_u" placeholder="Ingrese ..." pattern="^[a-zA-Z\s'\-]+$" title="Solo letras." required>                                   
                                    <label for="floatingInput">Apellido</label>
                                </div>


                                <div class="form-floating mb-3">
                                    <input style="background: #eeeeef" type="email" class="form-control" name="email" id="email" placeholder="Ingrese ..." title="YA EXISTE CORREO" required oninput="validarCorreo()">                                   
                                    <label for="floatingInput">Email</label>
                                </div>


                                <div class="form-floating mb-3">
                                    <input style="background: #eeeeef" type="password" class="form-control" name="password" placeholder="Ingrese ..."  title="" required>
                                    <label for="floatingInput">Contraseña</label>
                                </div>

                                <div class="form-floating mb-3">
                                    <input type="date" class="form-control" name="Fecha_nac" id="fechaNac" placeholder="Ingrese ..." required oninput="validarFechaNacimiento()">                                    
                                    <label for="floatingInput">Fecha Nacimiento</label>
                                </div>


                                <div class="form-floating mb-3">

                                    <select  name="genero" style="background: #eeeeef" class="form-control" name="genero"  title="" required>

                                        <option  name="generoF" value="F">Femenino</option>
                                        <option name="generoM" value="M">Masculino</option>

                                    </select>
                                    <label for="floatingInput">Selecciona el genero:</label>
                                </div>



                                <div class="form-floating mb-3">

                                    <select  name="estadoUser" id="estadoU" style="background: #eeeeef" class="form-control"   title="" required>

                                        <option  name="activo" value="t">Administrador</option>
                                        <option name="inactivo" value="f">Usuario Común</option>

                                    </select>
                                    <label for="floatingInput">Selecciona el Tipo de Usuario:</label>
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
                        <h5 class="modal-title" id="staticBackdropLabel">ACTUALIZAR USUARIO</h5>
                        <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
                    </div>
                    <form action="../UserServelet" method="post">
                        <div style="background: #eeeeef" class="modal-body">
                            <div class="form-floating mb-3">
                                <input type="text" class="form-control" name="duiUser" id="duiU" placeholder="Ingrese ..." pattern="\d{8}-\d{1}" title="Formato válido: 12345678-9" oninput="validarDUIActualizar()" required>
                                <label for="floatingInput">DUI</label>
                            </div>


                        </div>
                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" type="text" class="form-control" name="nombreUser" id="nombreU" placeholder="Ingrese ..." pattern="^[a-zA-Z\s'\-]+$" title="Solo letras." oninput="validarNombreU()" required>
                            <label for="floatingInput">Nombre</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" type="text" class="form-control" name="apellidoUser" id="apellidoU" placeholder="Ingrese ..." pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$" title="Solo letras." oninput="validarApellidoU()" required>
                            <label for="floatingInput">Apellido</label>
                        </div>
                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" type="email" class="form-control" name="emailUser" id="email" placeholder="Ingrese ..." title="Correo ya Existente." required oninput="validarCorreoActualizar()">
                            <label for="floatingInput">Email</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input style="background: #eeeeef" type="password" 
                                   class="form-control" name="passwordUser"
                                   id="passwordU" placeholder="Ingrese ..."  required>
                            <label for="floatingInput">CONTRASEÑA</label>
                        </div>

                        <div class="form-floating mb-3">
                            <input type="date" class="form-control" name="Fecha_nac" id="fechaNac" placeholder="Ingrese ..." required oninput="validarFechaNacimientoActualizar()">
                            <label for="floatingInput">Fecha Nacimiento</label>
                        </div>


                        <div class="form-floating mb-3">

                            <select  name="generoUser" id="generoU" style="background: #eeeeef" class="form-control"   title="" required>

                                <option  name="generoF" value="F">Femenino</option>
                                <option name="generoM" value="M">Masculino</option>

                            </select>
                            <label for="floatingInput">Selecciona el genero:</label>
                        </div>



                        <div class="form-floating mb-3">

                            <select  name="estadoUser" id="estadoU" style="background: #eeeeef" class="form-control"   title="" required>

                                <option  name="activo" value="t">Administrador</option>
                                <option name="inactivo" value="f">Usuario Común</option>

                            </select>
                            <label for="floatingInput">Selecciona el Tipo de Usuario:</label>
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
                                            document.getElementById("duiU").value = data[0].innerHTML;
                                            document.getElementById("nombreU").value = data[1].innerHTML;
                                            document.getElementById("apellidoU").value = data[2].innerHTML;
                                            document.getElementById("emailU").value = data[3].innerHTML;
                                            document.getElementById("passwordU").value = data[4].innerHTML;
                                            document.getElementById("fechaU").value = data[5].innerHTML;
                                            document.getElementById("generoU").value = data[6].innerHTML;
                                            let radio = document.getElementById("estadoU");
                                            if ((data[5].innerHTML) === "true") {
                                                radio.value = true;
                                                radio.checked = true;
                                            } else {
                                                radio.value = false;
                                                radio.checked = false;
                                            }
                                            $("#modalActualizar").modal('show');
                                        });
                                    });



        </script>
        <script>
            function confirmar(dui) {
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
                   // alert("eliminar " + dui);
                    if (result.isConfirmed) {
                        window.location = '../UserServelet?accion=delete&dui=' + dui;
                    }
                });
            }
        </script>
        <!-- VALIDACIONES -->   

        <script>
            function validarFechaNacimiento() {
                var fechaNacimientoInput = document.getElementById("fechaNac");
                var fechaNacimientoValue = fechaNacimientoInput.value;
                var fechaNacimientoDate = new Date(fechaNacimientoValue);
                var currentDate = new Date();

                // Calcula la edad
                var edad = currentDate.getFullYear() - fechaNacimientoDate.getFullYear();
                var mesActual = currentDate.getMonth() + 1;
                var mesNacimiento = fechaNacimientoDate.getMonth() + 1;

                // Valida la fecha de nacimiento
                if (fechaNacimientoDate >= currentDate || fechaNacimientoDate.toISOString().split('T')[0] === currentDate.toISOString().split('T')[0]) {
                    alert("La fecha de nacimiento debe ser una correcta");
                    fechaNacimientoInput.value = ""; // Limpiar el campo si la fecha no es válida
                } else if (edad < 18 || (edad === 18 && mesNacimiento > mesActual)) {
                    alert("Debes ser mayor de 18 años para registrarte");
                    fechaNacimientoInput.value = ""; // Limpiar el campo si la persona es menor de 18 años
                } else if (edad > 100) {
                    alert("La edad no puede ser mayor de 100 años");
                    fechaNacimientoInput.value = ""; // Limpiar el campo si la persona tiene más de 100 años
                }
            }
        </script>

        <script>
            function validarFechaNacimientoActualizar() {
                var fechaNacimientoInput = document.getElementById("fechaNac");
                var fechaNacimientoValue = fechaNacimientoInput.value;
                var fechaNacimientoDate = new Date(fechaNacimientoValue);
                var currentDate = new Date();

                // Calcula la edad
                var edad = currentDate.getFullYear() - fechaNacimientoDate.getFullYear();
                var mesActual = currentDate.getMonth() + 1;
                var mesNacimiento = fechaNacimientoDate.getMonth() + 1;

                // Valida la fecha de nacimiento
                if (fechaNacimientoDate >= currentDate || fechaNacimientoDate.toISOString().split('T')[0] === currentDate.toISOString().split('T')[0]) {
                    alert("La fecha de nacimiento debe ser correcta");
                    fechaNacimientoInput.value = ""; // Limpiar el campo si la fecha no es válida
                } else if (edad < 18 || (edad === 18 && mesNacimiento > mesActual)) {
                    alert("Debes ser mayor de 18 años para registrarte");
                    fechaNacimientoInput.value = ""; // Limpiar el campo si la persona es menor de 18 años
                } else if (edad > 100) {
                    alert("tu edad debe de ser menor a 100");
                    fechaNacimientoInput.value = ""; // Limpiar el campo si la persona tiene más de 100 años
                }
            }
        </script>

        <script>
            function validarDUI() {
                var duiInput = document.getElementById("dui");
                var duiValue = duiInput.value.replace(/[^0-9]/g, ""); // Elimina todos los caracteres no numéricos

                if (duiValue.length === 9) {
                    // Añade el guion si la longitud es 9
                    duiInput.value = duiValue.substring(0, 8) + "-" + duiValue.charAt(8);
                } else if (duiValue.length > 9) {
                    alert("El DUI no puede tener más de 9 dígitos");
                    duiInput.value = duiValue.substring(0, 9); // Limita el valor a 9 dígitos
                }
            }
        </script>   
        <script>
            function validarDUIActualizar() {
                var duiInput = document.getElementById("dui");
                var duiValue = duiInput.value.replace(/[^0-9]/g, ""); // Elimina todos los caracteres no numéricos

                if (duiValue.length === 9) {
                    // Añade el guion si la longitud es 9
                    duiInput.value = duiValue.substring(0, 8) + "-" + duiValue.charAt(8);
                } else if (duiValue.length > 9) {
                    alert("El DUI no puede tener más de 9 dígitos");
                    duiInput.value = duiValue.substring(0, 9); // Limita el valor a 9 dígitos
                }
            }
        </script>   

        <script>
            function validarCorreo() {
                var correoInput = document.getElementById("email");
                var correoValue = correoInput.value;

                // Validar el formato del correo electrónico
                var correoValido = validarFormatoCorreo(correoValue);

                if (!correoValido) {
                    alert("Por favor, introduce un correo electrónico válido.");
                    return;
                }

                // Llamar a la función en el servlet para verificar si el correo ya existe
                $.ajax({
                    type: 'POST',
                    url: 'UserServlet', // Ajusta la URL según la configuración de tu aplicación
                    data: {
                        accion: 'verificarCorreo',
                        correo: correoValue
                    },
                    success: function (response) {
                        // Devuelve true si el correo ya existe
                        if (response === 'true') {
                            alert("El correo electrónico ya existe en la base de datos. Por favor, utiliza otro correo.");
                            correoInput.value = ""; // Limpiar el campo si el correo ya existe
                        }
                        // Puedes agregar lógica adicional según tu necesidad
                    },
                    error: function (error) {
                        console.log("Error al verificar el correo electrónico en el servidor.");
                    }
                });
            }

            function validarFormatoCorreo(correo) {
                // Expresión regular para validar el formato de un correo electrónico
                var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                return regex.test(correo);
            }
        </script>

        <script>
            function validarCorreoActualizar() {
                var correoInput = document.getElementById("email");
                var correoValue = correoInput.value;

                // Validar el formato del correo electrónico
                var correoValido = validarFormatoCorreo(correoValue);

                if (!correoValido) {
                    alert("Por favor, introduce un correo electrónico válido.");
                    return;
                }

                // Llamar a la función en el servlet para verificar si el correo ya existe
                $.ajax({
                    type: 'POST',
                    url: 'UserServlet', // Ajusta la URL según la configuración de tu aplicación
                    data: {
                        accion: 'verificarCorreo',
                        correo: correoValue
                    },
                    success: function (response) {
                        // Devuelve true si el correo ya existe
                        if (response === 'true') {
                            alert("El correo electrónico ya existe en la base de datos. Por favor, utiliza otro correo.");
                            correoInput.value = ""; // Limpiar el campo si el correo ya existe
                        }
                        // Puedes agregar lógica adicional según tu necesidad
                    },
                    error: function (error) {
                        console.log("Error al verificar el correo electrónico en el servidor.");
                    }
                });
            }

            function validarFormatoCorreo(correo) {
                // Expresión regular para validar el formato de un correo electrónico
                var regex = /^[^\s@]+@[^\s@]+\.[^\s@]+$/;
                return regex.test(correo);
            }
        </script>
        <script>
            function validarCorreoActualizar() {
                var correoInput = document.getElementById("email");
                var correoValue = correoInput.value;

                // Llamar a la función en el servlet para verificar si el correo ya existe
                $.ajax({
                    type: 'POST',
                    url: 'UserServlet', // Ajusta la URL según la configuración de tu aplicación
                    data: {
                        accion: 'verificarCorreo',
                        correo: correoValue
                    },
                    success: function (response) {
                        // Devuelve true si el correo ya existe
                        if (response === 'true') {
                            alert("El correo electrónico ya existe en la base de datos. Por favor, utiliza otro correo.");
                            correoInput.value = ""; // Limpiar el campo si el correo ya existe
                        }
                        // Puedes agregar lógica adicional según tu necesidad
                    },
                    error: function (error) {
                        console.error("Error al verificar el correo electrónico: " + error);
                    }
                });
            }
        </script>

        <script>
            function validarNombreU() {
                var nombreUInput = document.getElementById("nombreUser");
                var nombreUValue = nombreUInput.value;

                // Expresión regular para permitir solo letras y espacios
                var regex = /^[a-zA-Z\s]+$/;

                if (!regex.test(nombreUValue)) {
                    alert("El nombre solo debe contener letras y espacios.");
                    nombreUInput.value = ""; // Limpiar el campo si el nombre no es válido
                }
            }
        </script>
        <script>
            function validarApellidoU() {
                var apellidoUInput = document.getElementById("apellidoU");
                var apellidoUValue = apellidoUInput.value;

                // Expresión regular para permitir solo letras y espacios
                var regex = /^[a-zA-Z\s]+$/;

                if (!regex.test(apellidoUValue)) {
                    alert("El apellido solo debe contener letra.");

                }
            }
        </script>

    </body>
</html>
