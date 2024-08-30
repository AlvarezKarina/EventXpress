package com.ues.edu.controllers;

/**
 *
 * @author A5155456HP
 */
import com.ues.edu.entities.Usuarios;
import com.ues.edu.model.dao.LoginDao;
import com.ues.edu.utilidades.Encriptar;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

@WebServlet(name = "LoginServlet", urlPatterns = {"/LoginServlet"})
//@WebServlet("/login")
public class LoginServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    //private Boolean encontrado = false;
    private LoginDao loginDAO;

    public void init() {
        loginDAO = new LoginDao(); // Permite llamar a la conexion de bd
    }

    public LoginServlet() {
        super();
    }

    // doPost() method
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        String accion = request.getParameter("accion");
        try {
            if (accion != null) {
                switch (accion) {
                    case "verificar":
                        verificar(request, response);
                        break;
                    //   case "cerrar":
                    //      cerrarsession(request, response);
                    //  default:
                    //   response.sendRedirect("identificar.jsp");
                }
            } else {
                response.sendRedirect("login.jsp");
            }
        } catch (Exception e) {
            try {
                this.getServletConfig().getServletContext().getRequestDispatcher("/Error.jsp").forward(request, response);
            } catch (Exception ex) {
                System.out.println("Error" + e.getMessage());
            }
        }
    }
    //PRIMERAMENTE VENDRA AL METODO VERIFICAR

    private void verificar(HttpServletRequest request, HttpServletResponse response) throws Exception {

        //  HttpSession sesion;
        LoginDao dao;
        //OBJETO USUARIO QUE CREAMOS EN ESTA CLASE
        Usuarios usuario;
        //usuario = new User();
        //E IRA AL METODO OBTENER USUARIO
        usuario = this.obtenerUsuario(request);
        dao = new LoginDao();
        //COMO YA OBTUVIMOS DEL DAO EL OBJETO USUARIO Y LO REGRESAMOS
        //SE LO AGREGAMOS EL OBJETO DE USUARIO QUE CREAMOS EN ESTA CLASE
        usuario = dao.identificar(usuario);
        /*
        if (usuario != null && usuario.getCargo().getNombreCargo().equals("ADMINISTRADOR")) {
            sesion = request.getSession();
            sesion.setAttribute("usuario", usuario);
            request.setAttribute("msje", "Bienvenido al sistema");
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/index.jsp").forward(request, response);
        } else if (usuario != null && usuario.getCargo().getNombreCargo().equals("VENDEDOR")) {
            sesion = request.getSession();
            sesion.setAttribute("vendedor", usuario);
            this.getServletConfig().getServletContext().getRequestDispatcher("/vistas/formVendedor.jsp").forward(request, response);
        } else {
            request.setAttribute("msje", "Credenciales Incorrectas");
            request.getRequestDispatcher("identificar.jsp").forward(request, response);
        }
         */

        //  El usuario existe
        //CUANDO FUIMOS AL DAO EXTRAIMOS UN USUARIO 
        //PERO SI NO HUBIESEMOS ENCONTRADO NINGUN USUARIO EL OBJETO usuario ESTARIA VACIO
        if (usuario != null) {
           // request.setAttribute("usuarios", usuario);
            request.setAttribute("usuarios", usuario);
            
            /*HttpSession session = request.getSession();//después de verificar el usuario, guarda el objeto Usuarios en la sesión:
             */
            request.getRequestDispatcher("menu.jsp").forward(request, response);

        } else {
            request.setAttribute("msje", "Credenciales Incorrectas");
            request.getRequestDispatcher("Error.jsp").forward(request, response);
        }

    }

    /*
    private void cerrarsession(HttpServletRequest request, HttpServletResponse response) throws Exception {
        HttpSession sesion = request.getSession();
        sesion.setAttribute("usuario", null);
        sesion.invalidate();
        response.sendRedirect("identificar.jsp");

    }
     */
    private Usuarios obtenerUsuario(HttpServletRequest request) {
        //VA A EXTRAER LOS DATOS QUE ESTEN EN LOS CAMPOS DE LA PAGINA WEB
        Usuarios u = new Usuarios();
        //ENCRIPTANDO CONTRASEÑA
        String contra = Encriptar.getStringMessageDigest(request.getParameter("txtPass"), Encriptar.SHA256);
        u.setNombre_u(request.getParameter("txtUsu"));
        u.setContraseña(contra);
        return u;
    }

}
