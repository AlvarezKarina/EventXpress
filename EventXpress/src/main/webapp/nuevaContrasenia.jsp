<%-- 
    Document   : nuevaContrasenia
    Created on : 4 ene. 2024, 12:59:53
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
               
                <div>
                    <h2><b>Event-Xpress</b></h2>
                    <h4><b>Nueva Contraseña</b></h4>
                </div>

               
                <div>

                    <img src="img/lock.png" id="icon" alt="contra Icon" class="img-fluid" />
                </div>

             
                <form action="UserServelet" method="post">
                    <div class="form-group">
                        <input type="password" class="form-control input-lg" placeholder="Nueva Contraseña" name="newPass">
                    </div>

                    <div class="form-group">
                        <button type="submit" name="accion" value="nuevaContra" class="btn btn-primary">Enviar</button>                    
                    </div>
                </form>

                
                <div id="formFooter">
                    <a href="login.jsp">Volver a iniciar sesión</a>
                </div>
            </div>
        </div>
    </body>

</html>