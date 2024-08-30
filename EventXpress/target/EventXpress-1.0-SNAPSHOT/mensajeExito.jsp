<%-- 
    Document   : mensajeExito
    Created on : 4 ene. 2024, 15:18:11
    Author     : Cisneros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Operación Exitosa</title>
    <!-- Asegúrate de tener SweetAlert disponible -->
    <script src="//cdn.jsdelivr.net/npm/sweetalert2@11"></script>
</head>
<body>

    <!-- Script para mostrar el mensaje de éxito y redireccionar -->
    <script>
        // Asume que los atributos del mensaje se han pasado a esta página
        var messageTitle = '<%= request.getAttribute("messageTitle") %>';
        var messageText = '<%= request.getAttribute("messageText") %>';
        var messageIcon = '<%= request.getAttribute("messageIcon") %>'; // debería ser "success" para mensajes de éxito

        // Muestra el mensaje utilizando SweetAlert
        Swal.fire({
            title: messageTitle,
            text: messageText,
            icon: messageIcon,
            confirmButtonColor: '#3085d6',
            confirmButtonText: 'Ok',
            backdrop: false,
            background: '#eeeeef',
            allowOutsideClick: false
        }).then((result) => {
            if (result.isConfirmed) {
                // Redirige al usuario a login.jsp
                window.location.href = 'login.jsp';
            }
        });
    </script>

</body>
</html>