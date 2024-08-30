<%@page import="com.ues.edu.model.dao.Zona_Establec_Dao"%>
<%@page import="com.ues.edu.entities.TotalZonas"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>




<html>
    <head>
        <title>Capacidad Total</title>
        <link rel="stylesheet"
              href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
    </head>
    <body>
        <%
            Zona_Establec_Dao dao = new Zona_Establec_Dao();

        %>
        <br>
        <div class="row">
            <div class="container">
                <h2 class="text-center">CAPACIDAD TOTAL DE LAS ZONAS EN LOS ESTABLECIMIENTOS </h2>
                <hr>
                <div align="center">
                    <table class="table table-bordered">
                        <thead>
                            <tr>
                                <th>Nombre del establecimiento</th>
                                <th>Nombre de la zona</th>
                                <th>Capacidad total</th>

                            </tr>
                        </thead>

                        <%   
                            List<TotalZonas> listaEstablec = (List<TotalZonas>) request.getAttribute("listaZonas");
                            for (TotalZonas z : listaEstablec) {
                        %> 
                        <tr>
                            <td><%=z.getnEstablecimiento()%></td>
                            <td><%=z.getnZona()%></td>
                            <td><%=z.getTotalZona()%></td>
                                

                            </tr>
                        <%
                            }
                        %>






                    </table>
                </div>
            </div>
        </div>
    </body>
</html>