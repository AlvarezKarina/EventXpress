<%-- 
    Document   : register
    Created on : 28 dic. 2023, 17:51:14
    Author     : Cisneros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Nueva Cuenta</title>
        <style>
            body {
                background-color: #ffffff;
                font-family: "Poppins", sans-serif;
                height: 100vh;
                margin: 0;
                display: flex;
                align-items: center;
                justify-content: center;
            }

            h2 {
                text-align: center;
                font-size: 16px;
                font-weight: 600;
                text-transform: uppercase;
                color: #cccccc;
            }

            .form-wrapper {
                border-radius: 10px;
                background: #ffffff;
                padding: 30px;
                width: 100%; /* Ancho al 100% */
                max-width: 600px; /* Máximo ancho permitido */
                box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
                text-align: center;
                margin: 20px; /* Margen celeste */
                border: 2px solid #56baed; /* Borde celeste */
            }

            .form-group {
                display: flex;
                flex-wrap: wrap; /* Permitir que los elementos se envuelvan en una nueva línea */
                justify-content: space-between;
                margin-bottom: 20px;
            }

            .form-group label {
                width: 100%;
                text-align: left;
                margin-bottom: 5px;
                color: #666666;
                font-size: 14px;
            }

            .form-control {
                flex: 1;
                width: calc(50% - 10px);
                background-color: #f6f6f6;
                border: none;
                color: #0d0d0d;
                padding: 15px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                font-size: 16px;
                margin: 5px;
                border: 2px solid #f6f6f6;
                transition: all 0.5s ease-in-out;
                border-radius: 5px;
            }


            .form-control.date {
                width: 100%;
            }

            .form-control:focus {
                background-color: #fff;
                border: 2px solid #56baed;
            }

            .btn-primary {
                background-color: #56baed;
                border: none;
                color: white;
                padding: 15px 20px;
                text-align: center;
                text-decoration: none;
                display: inline-block;
                text-transform: uppercase;
                font-size: 13px;
                box-shadow: 0 10px 30px 0 rgba(95, 186, 233, 0.4);
                border-radius: 5px;
                transition: all 0.3s ease-in-out;
                width: 100%;
            }

            .btn-primary:hover {
                background-color: #39ace7;
            }

            .cancel-link {
                color: #dc3545;
                text-decoration: none;
                display: block;
                margin-top: 10px;
            }

            .cancel-link:hover {
                text-decoration: underline;
            }
        </style>
    </head>

    <body>

        <div class="form-wrapper">
            <h2>Registro de Usuario</h2>
            <form action="UserServelet" method="post">

                <div class="form-group">                    
                    <input type="text" class="form-control" name="duiUser" id="duiUser" placeholder="DUI"  required>                    
                    <input type="text" class="form-control" name="nombreUser" id="nombreUser" placeholder="Nombre" pattern="^[A-Za-zñÑáéíóúÁÉÍÓÚüÜ\s]+$" title="Solo letras." required>
                </div>

                <div class="form-group">                   
                    <input type="text" class="form-control" name="apellidoUser" id="apellidoUser" placeholder="Apellido" required>                 
                    <input type="email" class="form-control" name="emailUser" id="emailUser" placeholder="Correo electrónico" required>
                </div>

                <div class="form-group">                
                    <input type="password" class="form-control" name="passwordUser" id="passwordUser" placeholder="Contraseña" required>

                    <select name="generoUser" id="generoUser" class="form-control" required>
                        <option value="F">Femenino</option>
                        <option value="M">Masculino</option>
                    </select>
                </div>

                <div class="form-group">
                    <label for="fechaU">Fecha de Nacimiento</label>             
                    <input type="date" class="form-control date" name="fechaUser" id="fechaUser" required>
                </div>

                <div class="form-group">
                    <button type="submit" name="accion" value="insertComun" class="btn btn-primary">Crear Cuenta</button>                    
                </div>
            </form>
            <a href="login.jsp" class="cancel-link">Cancelar</a>
        </div>

    </body>
</html>