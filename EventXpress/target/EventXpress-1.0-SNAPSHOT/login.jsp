<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="UTF-8"%>

<!DOCTYPE html>
<html lang="en">
    <head>
        <meta http-equiv="content-type" content="text/html; charset=UTF-8">
        <meta charset="utf-8">
        <title>Bienvenidos 😂</title>
        <meta name="generator" content="Bootply" />
        <meta name="viewport" content="width=device-width, initial-scale=1, maximum-scale=1">
        <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.3.1/css/bootstrap.min.css"
              integrity="sha384-ggOyR0iXCbMQv3Xipma34MD+dH/1fQ784/j6cY/iJTQUOhcWr7x9JvoRxT2MZw1T"
              crossorigin="anonymous">
        <script src="https://code.jquery.com/jquery-3.4.1.min.js" integrity="sha256-CSXorXvZcTkaix6Yvo6HppcZGetbYMGWSFlBw8HfCJo="
        crossorigin="anonymous"></script>
        <script type="text/javascript" src="https://cdn.jsdelivr.net/npm/jquery-validation@1.19.0/dist/jquery.validate.min.js"></script>



        <style>
            /* Otras reglas de estilo que desees agregar específicamente para esta página JSP */

            body {
                background-color: #56baed;
                font-family: "Poppins", sans-serif;
                height: 100vh;
                margin: 0; /* Establece el margen del cuerpo a cero */
            }

            header {
                margin-bottom: 20px;
            }

            .container {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                margin-top: 0; /* Establece el margen superior a cero */
            }

            .card {
                width: 90%;
                max-width: 450px;
                box-shadow: 0 30px 60px 0 rgba(0, 0, 0, 0.3);
                text-align: center;
            }

            form {
                padding: 30px;
            }

            .form-group {
                margin-bottom: 15px;
            }

            .form-control {
                width: 85%;
                margin: 5px;
                border: 2px solid #f6f6f6;
                transition: all 0.5s ease-in-out;
                border-radius: 5px;
            }

            .form-control:focus {
                background-color: #fff;
                border-bottom: 2px solid #5fbae9;
            }

            .btn-success {
                background-color: #56baed;
                border: none;
                color: white;
                padding: 18px 50px;
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

            .btn-success:hover {
                background-color: #39ace7;
            }

            #formFooter {
                background-color: #f6f6f6;
                border-top: 1px solid #dce8f1;
                padding: 25px;
                text-align: center;
                border-radius: 0 0 10px 10px;
            }

            #icon {
                width: 35%;
                display: block;
                margin: auto;
            }

            td div.form-group label {
                white-space: nowrap;
            }
        </style>

    </head>

    <body>

        <br>
        <div class="container col-md-5">
            <div class="card">
                <div class="card-body">
                    <form id="loginForm" class="form col-md-12 center-block" action="LoginServlet?accion=verificar" method="POST">
                        <table class="table" WIDTH="50%">
                            <div>
                                <img src="img/icon.svg" id="icon" alt="User Icon" class="img-fluid" />
                            </div>
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="form-control input-lg" for="txtUsu">Usuario</label>
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <input type="text" class="form-control input-lg" placeholder="Username" name="txtUsu">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td>
                                    <div class="form-group">
                                        <label class="form-control input-lg" for="txtPass">Password</label>
                                    </div>
                                </td>
                                <td>
                                    <div class="form-group">
                                        <input type="password" class="form-control input-lg" placeholder="Password" name="txtPass">
                                    </div>
                                </td>
                            </tr>
                            <tr>
                                <td colspan="2">
                                    <div class="form-group">
                                        <button class="btn btn-success" type="submit">Sign In</button>
                                    </div>
                                    <div id="formFooter">

                                        <a href="register.jsp">No tienes cuenta, regístrate.</a>
                                        <br />
                                        <a href="recuperar.jsp">¿Olvidaste la contraseña?</a>
                                    </div>
                                </td>
                            </tr>
                        </table>
                    </form>
                </div>
            </div>
        </div>


    </body>
</html>