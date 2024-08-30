<%-- 
    Document   : errorRecuperacion
    Created on : 4 ene. 2024, 14:58:28
    Author     : Cisneros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error en la Recuperación</title>
        <!-- Incluye SweetAlert y cualquier otro CSS o JS que necesites -->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
        <style>
            /* Tus estilos personalizados aquí si los necesitas */
        </style>
    </head>
    <body>

        <!-- Mensaje de error que será mostrado -->
        <div id="errorMessage" style="display:none;">
            <% String message = (String) request.getAttribute("messageText");
                if (message == null) {
                    message = "Se ha producido un error inesperado."; // Mensaje por defecto
                }
            %>
            <%= message%>
        </div>

        <!-- Script para mostrar el mensaje de error usando SweetAlert -->
        <script>
            document.addEventListener("DOMContentLoaded", function () {
                // Obtiene el mensaje del elemento oculto
                var messageText = document.getElementById("errorMessage").innerText;
                // Muestra el mensaje utilizando SweetAlert
                Swal.fire({
                    title: '¡Error!',
                    text: messageText,
                    icon: 'error',
                    confirmButtonColor: '#3085d6',
                    confirmButtonText: 'Aceptar',
                    backdrop: false,
                    background: '#eeeeef',
                    allowOutsideClick: false
                }).then((result) => {
                    if (result.isConfirmed) {
                        // Redirige a recuperar.jsp o cualquier otra página deseada
                        window.location.href = 'recuperar.jsp';
                    }
                });
            });
        </script>


    </body>
</html>

</html>