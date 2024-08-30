<%-- 
    Document   : CompraDeBoletos
    Created on : 2 ene 2024, 18:20:02
    Author     : herna
--%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!-- para importar librerias de etiquetas personalizadas -->
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
                            <a style="margin-left: 0px; border: none" class="btn btn-outline-light" href="ControllerBoletosEstablecimiento?accion=Carrito" aria-disabled="true"><i class="fas fas-cart-plus">(<label style="color: orange">${contador}</label>)</i>Carrito de compras</a>
                        </li>
                    </ul>
                    <!--<form class="d-flex" role="search">
                        <input class="form-control me-2" type="search" placeholder="Search" aria-label="Search">
                        <button class="btn btn-outline-success" type="submit">Search</button>
                    </form>-->
                    

                </div>
            </div>
        </nav>
        <div class="text-center">
            <h2>COMPRA DE BOLETOS</h2>
        </div>
        <br>
        <div class="container mt-2">

            <div class="container">
                <c:forEach var="p" items="${listaEventosProgramados}" varStatus="loopStatus">
                    <c:if test="${loopStatus.index % 3 == 0}">
                        <div class="row">
                            <div class="card-">
                                <div class="card-header">
                                    <div class="d-flex justify-content-between">
                                        <label><b>${p.getId_evento().getnEvento()}</b></label><!-- FUNCIONA COMO UN IF-ELSE -->
                                            <c:choose>
                                                <c:when test="${p.getId_evento().getCategoria().getCategoria()=='A'}">
                                                    <label class="text-end">PARA TODA LA FAMILIA</label>
                                                </c:when>
                                                    <c:when test="${p.getId_evento().getCategoria().getCategoria()=='B'}">
                                                        <label class="text-end">MAYORES DE 14 AÑOS</label>
                                                    </c:when>
                                                    <c:otherwise>
                                                        <label class="text-end">MAYORES DE 18 AÑOS</label>
                                                    </c:otherwise>
                                            </c:choose>
                                    </div>
                                </div>

                                <div class="card-body">
                                    <p>${p.getId_evento().getDescripcion()}</p>
                                    <label>Dia del evento: ${p.getFecha()}</label><br>
                                    <label>Hora de inicio: ${p.getHora_inicia()} </label><br>
                                    <label>Hora de finalizacion: ${p.getHora_fin()}</label><br>
                                    <label>Establecimiento: ${p.getEstablecimientos().getN_establecimiento()}</label><br>
                                    <label>Lugar: ${p.getEstablecimientos().getDireccion()}</label>

                                </div>
                            </div>
                        </c:if>

                        <div class="col-sm-4">
                            <div class="card">
                                <div class="card">
                                    <label>${p.getEstablecimientos().getZonas().get(0).getIdZona().getNombreZonas()}</label>
                                </div>
                                <div class="card-body">
                                    <i>Boletos disponibles:
                                        <c:choose>
                                            <c:when test="${p.getEstablecimientos().getZonas().get(0).getCapacidadZona()!=0}">
                                                ${p.getEstablecimientos().getZonas().get(0).getCapacidadZona()}</i>
                                            </c:when>
                                            <c:otherwise>
                                            AGOTADO</i>
                                        </c:otherwise>
                                    </c:choose>                              

                                </div>
                                <div class="card-footer text-center">
                                    <label>Precio: $${p.getEstablecimientos().getZonas().get(0).getPrecio()}</label>
                                    <c:choose>
                                        <c:when test="${p.getEstablecimientos().getZonas().get(0).getCapacidadZona()!=0}">
                                            <div>
                                                <a href="ControllerBoletosEstablecimiento?accion=AgregarCarrito&idZE=${p.getEstablecimientos().getZonas().get(0).getIdZonaEstablecimiento()}&idEP=${p.getId_evento_progra()}" class="btn btn-outline-success">Agregar a Carrito</a>
                                                <a href="ControllerBoletosEstablecimiento?accion=Comprar&idZE=${p.getEstablecimientos().getZonas().get(0).getIdZonaEstablecimiento()}&idEP=${p.getId_evento_progra()}" class="btn btn-danger">Comprar</a>
                                            </div>
                                        </c:when>
                                        <c:otherwise>

                                        </c:otherwise>
                                    </c:choose>    

                                </div>
                            </div>
                        </div>
                        <c:if test="${loopStatus.index % 3 == 2 or loopStatus.last}">
                        </div>
                        <br>
                    </c:if>
                </c:forEach>
            </div>






        </div>


        <script src="https://code.jquery.com/jquery-3.6.4.min.js"></script>
        <script src="https://cdn.jsdelivr.net/npm/@popperjs/core@2.10.2/dist/umd/popper.min.js" integrity="sha384-7+zCNj/IqJ95wo16oMtfsKbZ9ccEh31eOz1HGyDuCQ6wgnyJNSYdrPa03rtR1zdB" crossorigin="anonymous"></script>
        <script src="https://cdn.jsdelivr.net/npm/bootstrap@5.1.3/dist/js/bootstrap.min.js" integrity="sha384-QJHtvGhmr9XOIpI6YVutG+2QOK9T+ZnN4kzFN1RtK3zEFEIsxhlmWl5/YESvpZ13" crossorigin="anonymous"></script>
        <script src="js/funciones.js" type="text/javascript"></script>
    </body>
</html>
