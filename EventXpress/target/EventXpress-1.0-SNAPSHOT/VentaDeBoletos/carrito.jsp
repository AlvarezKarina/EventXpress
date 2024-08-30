<%-- 
    Document   : carrito
    Created on : 2 ene 2024, 19:00:42
    Author     : herna
--%>

<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@page import="com.ues.edu.entities.Usuarios"%>
<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <%Usuarios usuario = (Usuarios) session.getAttribute("usuario");%>
    <%session.setAttribute("usuario", usuario);%>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
        <link href="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/css/bootstrap.min.css" rel="stylesheet" integrity="sha384-1BmE4kWBq78iYhFldvKuhfTAU6auU8tT94WrHftjDbrCEXSU1oBoqyl2QvZ6jIW3" crossorigin="anonymous">

    </head>
    <body>
        
        <nav class="navbar navbar-expand-lg navbar-dark bg-dark">
            <div class="container-fluid">
                <a class="navbar-brand" href="#">EventXpress</a>
                <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarSupportedContent" aria-controls="navbarSupportedContent" aria-expanded="false" aria-label="Toggle navigation">
                    <span class="navbar-toggler-icon"></span>
                </button>
                <div class="collapse navbar-collapse" id="navbarSupportedContent">
                    <ul class="navbar-nav me-auto mb-2 mb-lg-0">
                        

                        <li class="nav-item">
                            <a style="margin-left: 0px; border: none" class="btn btn-outline-light"  href="ControllerBoletosEstablecimiento?accion=home" aria-disabled="true"><i class="fas fas-cart-plus">(<label style="color: orange">${contador}</label>)</i>Seguir comprando</a>
                        </li>
                    </ul>
                    <form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>
                   

                </div>
            </div>
        </nav>
        <!-- MUESTRA LOS PRODUCTOS QUE TENEMOS EN EL CARRITO -->

        <div class="container mt-4">
            <h3>Carrito</h3>
            <br>
            <div class="row">
                <div class="col-sm-8">
                    <table class="table table-hover">
                        <thead>
                            <tr>
                                <td>ITEM</td>
                                <td>EVENTO</td>
                                <td>ZONA</td>
                                <td>DESCRIPCION</td>
                                <td>DIRECCION</td>
                                <td>BOLETOS DISPONIBLES</td>
                                <td>CANTIDAD A COMPRAR</td>
                                <td>PRECIO</td>
                                <td>SUBTOTAL</td>
                                <td>ACCION</td>
                            </tr>
                        </thead>
                        <tbody>
                            <c:forEach var="car" items="${carrito}">
                                <tr>
                                    <td>${car.getItem()}</td>
                                    <td>${car.getnEvento()}</td>
                                    <td>${car.getnZona()}</td>
                                    <td>${car.getDescripcion()}</td>
                                    <td>${car.getDireccion()}</td>
                                    <td>${car.getBoletosDisponibles()}</td>
                                    <td>
                                        <input type="hidden" id="idze" value="${car.getIdZonaEstable()}">
                                        <input type="hidden" id="idep" value="${car.getIdEventoProgra()}">
                                        <input type="number" id="Cantidad" value="${car.getCantidad()}" class="form-control text-center" min="1" max="${car.getBoletosDisponibles()}">
                                    </td>

                                    <td>$${car.getPrecio()}</td>
                                    <td>$${car.getSubTotal()}</td>
                                    <td>
                                        <!-- TOMAREMOS LOS ID DE ZONA_ESTABLEC Y DE EVENTO_PROGRAMADO PARA QUE VERIFIQUEMOS QUE ESTAMOS ELIMINANDO EL REGISTRO CORRECTO DE L 
                                        CARRITO-->
                                        <input type="hidden" id="idZonaE" value="${car.getIdZonaEstable()}"><!-- HIDDEN ES PARA QUE EL ELEMENTO ESTE EN LA PAGINA, PERO OCULTO -->
                                        <input type="hidden" id="idEvenP" value="${car.getIdEventoProgra()}"><!-- HIDDEN ES PARA QUE EL ELEMENTO ESTE EN LA PAGINA, PERO OCULTO -->
                                        <a href="#" id="btnDelete">Eliminar</a>
                                    </td>
                                </tr>
                            </c:forEach>

                        </tbody>

                    </table>

                </div>
                <div class="col-sm-4">
                    <div class="card">
                        <div class="card-header">
                            <h3>Generar Compra</h3>
                        </div>
                        <form action="ControllerBoletosEstablecimiento"  method="get" onsubmit="return validarFormulario()">
                            
                            <div class="card-body">
                                <input type="hidden" name="idUsuario" id="idUsuario"  value="<%=usuario.getDui()%>" class="form-control">
                                <label>Ingrese tarjeta de credito:</label>
                                <input type="text" name="tarjeta" id="tarjeta" class="form-control">
                                <label>Total a pagar:</label>
                                <input type="text" name="totalPagar"  value="$${totalPagar}" readonly="" class="form-control">
                            </div>
                            <div class="card-footer">
                                <c:choose>
                                    <c:when test="${totalPagar!=0}">
                                        <button type="submit" class="btn btn-info btn-block" >Generar Compra</button>
                                        <!-- Agrega un campo oculto para enviar el parámetro de acción -->
                                        <input type="hidden" name="accion" value="GenerarCompra">
                                    </c:when>
                                    <c:otherwise>
                                        <button type="submit" class="btn btn-info btn-block" id="generarCompra" disabled="">Generar Compra</button>
                                        <!-- Agrega un campo oculto para enviar el parámetro de acción -->
                                        <input type="hidden" name="accion" value="GenerarCompra">
                                    </c:otherwise>
                                </c:choose>
                            </div>
                        </form>
                            

                    </div>
                </div>
            </div>

        </div>
        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
    </body>
</html>