<%-- 
    Document   : welcome
    Created on : 16 sep. 2023, 17:38:41
    Author     : A5155456HP
--%>

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Error</title>
        <style>
           .container {
                display: flex;
                align-items: center;
                justify-content: center;
                height: 100vh;
                margin-top: 0; /* Establece el margen superior a cero */
            }
        </style>
    </head>
    <body>
        <div class="container col-md-5">
      <form action="logout" method="get">
  
        <h2>
            Hello Word !!!
            <%=request.getParameter("name")%>!
        </h2>
        <h3>ERROR AL INGRESAR USUARIO NO ENCONTRADO</h3>
  
       <!-- <br> <input type="submit" value="Logout" />-->
        <a href="login.jsp" >Volver a Ingresar </a>
    </form>
      </div>
    </body>
</html>