<%-- 
    Document   : usuarios_boletos
    Created on : 2 oct. 2023, 16:56:58
    Author     : ASUS
--%>


<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%><!<!-- para importar librerias de etiquetas personalizadas -->
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Usuarios con Boletos</title><!<!-- titulo de la pagina -->
        <link rel="stylesheet" type="text/css" href="css/boletosUsuarios.css">

    </head>
    <body><!<!-- cuerpo de la pag. -->
        <br>
        <div class="row"><!<!-- se utiliza para crear filas dentro del contenedor.
Las filas se utilizan para organizar y alinear horizontalmente los elementos de la página en columnas. -->
            <div class="container"><!<!--  crea un contenedor principal que envuelve todo el contenido de la página. -->
                <h2 class="text-center">Lista de Boletos</h2>
                <hr>
                <div align="center">
                    <h3>Detalles de Boletos</h3>
                    <table class="table table-bordered">
                        <thead><!<!-- define las columnas de la tabla -->
                            <tr>
                                <th>Fecha de Compra</th><!<!-- muestra letra en negrita -->
                                <th>Nombre del Usuario</th>
                                <th>Apellido del Usuario</th>
                                <th>Evento</th>
                            </tr>
                        </thead>
                        <c:forEach items="${listaBoletos}" var="boleto"><!<!-- etiqueta forEach de JSTL para iterar sobre la lista de Boletos  -->
                            <tr><!<!-- define una fila dentro de la tabla -->
                                <td>${boleto.fecha_compra}</td>
                                <td>${boleto.dui.nombre_u}</td>
                                <td>${boleto.dui.apellido_u}</td>
                                <td>${boleto.id_evento_programado.id_evento.n_evento}</td>
                            </tr>
                        </c:forEach>
                    </table>
                </div>
            </div>
        </div>
    </body>
</html>
