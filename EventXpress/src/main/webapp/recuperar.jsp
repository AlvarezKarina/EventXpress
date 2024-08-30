<%-- 
    Document   : recuperar
    Created on : 27 dic. 2023, 11:59:27
    Author     : Cisneros
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml"
      xmlns:h="http://xmlns.jcp.org/jsf/html"
      xmlns:p="http://primefaces.org/ui">

    <head>
        <meta charset="utf-8" />
        <meta http-equiv="X-UA-Compatible" content="IE=edge" />
        <title>Recuperar  </title>
        <meta content="width=device-width, initial-scale=1, maximum-scale=1, user-scalable=no" name="viewport" />
        <h:outputStylesheet library="primefaces" name="themes/bootstrap/theme.css" />
        <h:outputStylesheet name="css/custom.css" />
        <style>
            body {
                background-color: #ffffff;
                font-family: "Poppins", sans-serif;
                height: 100vh;
                margin: 0;
            }

            .container {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                margin-top: 0;
            }

            #formContent {
                background-color: #f8f9fa; /* Color de fondo del formulario */
                padding: 20px;
                border-radius: 10px;
                box-shadow: 0 0 10px rgba(0, 0, 0, 0.1); /* Sombra alrededor del formulario */
                text-align: center;
            }

            h2, h4 {
                color: #007bff; /* Color del texto de los títulos */
            }

            #icon {
                height: 50px;
                width: 50px;
                display: block;
                margin: auto;
            }

            form {
                margin-top: 20px;
            }

            .form-floating input {
                background: #eeeeef; /* Color de fondo de los campos de entrada */
                border: 1px solid #ced4da; /* Borde de los campos de entrada */
                border-radius: 5px;
                padding: 10px;
                width: 100%;
                box-sizing: border-box;
            }

            .loginButton input {
                background-color: #007bff; /* Color de fondo del botón */
                color: #ffffff; /* Color del texto del botón */
                border: none;
                padding: 10px 20px;
                border-radius: 5px;
                cursor: pointer;
                transition: background-color 0.3s;
                width: 100%; /* Ancho del botón al 100% */
            }

            .loginButton input:hover {
                background-color: #0056b3; /* Cambio de color al pasar el ratón sobre el botón */
            }

            #formFooter {
                margin-top: 20px;
                color: #6c757d; /* Color del texto del enlace */
            }
        </style>


    </head>



    <body class="bg-light">
    
        <div class="container col-md-5">
            <div id="formContent">
                <!--titulos -->
                <div>
                    <h2><b>Event-Xpress</b></h2>
                    <h4><b>Recuperar Contraseña</b></h4>
                </div>

                <!-- Iconos -->
                <div>

                    <img src="img/email.png" id="icon" alt="Email Icon" class="img-fluid" />
                </div>

                <!-- Password Recovery Form -->
                <form action="UserServelet" method="post">
                    <div class="form-floating mb-3">
                        <input style="background: #eeeeef" type="text" class="form-control" name="dui" placeholder="Ingrese Dui" pattern="^[\w'\-,.][^0-9_!¡?÷?¿/\\+=@#$%ˆ&*(){}|~<>;:[\]]{2,}$" title="Solo letras." required>

                    </div>

                    <div class="form-floating mb-3">
                        <input style="background: #eeeeef" type="email" class="form-control" name="email" placeholder="Correo Electronico" required>

                    </div>

                    <div class="form-group">
                        <button type="submit" name="accion" value="recuperarContra" class="btn btn-primary">Enviar</button>                    
                    </div>
                </form>

                <!-- Back to Login -->
                <div id="formFooter">
                    <a href="login.jsp">Volver a iniciar sesión</a>
                </div>
            </div>
        </div>
    </body>

</html>