<%-- 
    Document   : mensajeError
    Created on : 4 ene. 2024, 15:20:46
    Author     : Cisneros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Error en la Operación</title>
        <!-- Asegúrate de tener SweetAlert disponible -->
        <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
    </head>
    <body>

        <!-- Script para mostrar el mensaje de error -->
        <script>
            // Asume que los atributos del mensaje se han pasado a esta página
            var messageTitle = '<%= request.getAttribute("messageTitle")%>';
            var messageText = '<%= request.getAttribute("messageText")%>';
            var messageIcon = '<%= request.getAttribute("messageIcon")%>'; // debería ser "error" para mensajes de error

            // Muestra el mensaje utilizando SweetAlert
            Swal.fire({
                title: messageTitle,
                text: messageText,
                icon: messageIcon,
                confirmButtonColor: '#3085d6',
                confirmButtonText: 'Entendido',
                backdrop: false,
                background: '#eeeeef',
                allowOutsideClick: false
            }).then((result) => {
                if (result.isConfirmed) {
                    // Opción para redirigir al usuario a una página específica después del error, como la página de recuperar contraseña.
                    // Cambia 'recuperar.jsp' a la página que prefieras o simplemente omite esta línea para que no redirija.
                    window.location.href = 'recuperar.jsp';
                }
            });
        </script>

    </body>
</html>