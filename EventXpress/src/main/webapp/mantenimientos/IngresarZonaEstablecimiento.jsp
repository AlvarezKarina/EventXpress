<%-- 
    Document   : IngresarZonaEstablec
    Created on : 22 nov. 2023, 15:41:20
    Author     : nelki
--%>

<%@page import="com.ues.edu.model.dao.ZonaEstablecimientoDao"%>
<%@page import="java.util.ArrayList"%>

<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta name="viewport" content="width=device-width, initial-scale=1">

        <title>Nueva zona establecimiento</title>
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-iYQeCzEYFbKjA/T2uDLTpkwGzCiq6soy8tYaI1GyVh/UjpbCx/TYkiZhlZB6+fzT" crossorigin="anonymous">
        <link href="https://cdnjs.cloudflare.com/ajax/libs/twitter-bootstrap/5.2.0/css/bootstrap.min.css" rel="stylesheet"/>
        <link href="https://cdn.datatables.net/1.12.1/css/dataTables.bootstrap5.min.css" rel="stylesheet"/>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.1/dist/js/bootstrap.bundle.min.js" integrity="sha384-u1OknCvxWvY5kfmNBILK2hRnQC3Pr17a+RTT6rIHI7NnikvbZlHgTPOOmMi466C8" crossorigin="anonymous"></script>
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

            button {
                margin-top: 20px;
            }
        </style>
    </head>
    <body>

        <div class="container mx-auto"> <!-- Agrega la clase mx-auto para centrar el contenido -->

            <h3 class="text-center">Registrar Nueva zona establecimiento</h3>

            <!-- Formulario para registrar un nuevo evento programado -->
            <form action="<%=request.getContextPath()%>/ZonaEstablecimientoServlet?action=insert" method="post">
               
                         <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="number" class="form-control" name="txtPrecio" placeholder="Ingrese ..." required max="9999999999" >
                                <label for="floatingInput">Precio</label>
                            </div>
                            <div class="form-floating mb-3">
                                <input style="background: #eeeeef" type="number" class="form-control" name="txtCapacidadZona" placeholder="Ingrese ..." required max="9999999999" >
                                <label for="floatingInput">Capacidad de la zona</label>
                            </div>  

                            <!-- Combo para seleccionar el nombre de la zona -->
                            <div class="form-floating mb-3">
                                <select name="cbNombreZona" class="form-select" aria-label="Default select example">
                                    <option value="seleccioneZona" selected>Seleccione una zona</option>
                                    <% ZonaEstablecimientoDao zonaDao = new ZonaEstablecimientoDao(); %>
                                    <% ArrayList<String> nombresZonas = zonaDao.obtenerNombresZona(); %>
                                    <% for (String nombreZonas : nombresZonas) {%>
                                    <option value="<%=nombreZonas%>">
                                        <%=nombreZonas%>
                                    </option>
                                    <% }%>
                                </select>
                            </div>
                            <!-- Combo para seleccionar el nombre de la zona -->
                            <div class="form-floating mb-3">
                                <select name="cbNomEstablec" class="form-select" aria-label="Default select example">
                                    <option value="seleccioneEstablec" selected>Seleccione un establecimiento</option>
                                    <% ZonaEstablecimientoDao establecDao = new ZonaEstablecimientoDao(); %>
                                    <% ArrayList<String> nombresEstablec = establecDao.obtenerNombresEstablec(); %>
                                    <% for (String nombreEstablec : nombresEstablec) {%>
                                    <option value="<%=nombreEstablec%>">
                                        <%=nombreEstablec%>
                                    </option>
                                    <% }%>
                                </select>
                            </div> 

                <button type="submit" class="btn btn-primary">Registrar zona establecimiento</button>
            </form>
        </div>
    </body>
</html>

