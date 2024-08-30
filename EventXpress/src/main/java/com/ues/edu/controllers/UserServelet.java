package com.ues.edu.controllers;

import com.ues.edu.entities.Usuarios;
import com.ues.edu.model.dao.UserDao;
import com.ues.edu.utilidades.Encriptar;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author alexa
 */
@WebServlet(name = "UserServelet", urlPatterns = {"/UserServelet"})

public class UserServelet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private UserDao userDao;
    private Boolean estado;

    public void init() {
        userDao = new UserDao();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String action = request.getServletPath();

        String action = request.getParameter("accion");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertUser(request, response);
                    break;
                case "insertComun":
                    insertUserComun(request, response);
                    break;
                case "delete":
                    deleteUser(request, response);
                    break;
                case "recuperarContra":
                    recuperarContra(request, response);//para recuperar la contrasenia
                    break;

                case "nuevaContra":
                    nuevaContra(request, response);//para recuperar la contrasenia
                    break;
                case "update":
                    updateUser(request, response);
                    break;
                case "verificarCorreo":
                    verificarCorreo(request, response);
                    break;
                default:
                    listUser(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        List<Usuarios> listUser = userDao.selectAllUsers();
        request.setAttribute("listUser", listUser);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void recuperarContra(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String dui = request.getParameter("dui");
        String email = request.getParameter("email");
        String idUsuario = userDao.verificarUsuario(dui, email);

        if (idUsuario != null) {
            request.getSession().setAttribute("usuarioAutorizado", idUsuario);
            response.sendRedirect("nuevaContrasenia.jsp");
        } else {
            // No se encontró el usuario, establece los atributos del mensaje
            request.setAttribute("messageTitle", "Usuario no encontrado");
            request.setAttribute("messageText", "No se encontró una cuenta con los datos proporcionados.");
            request.setAttribute("messageIcon", "warning");
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorRecuperacion.jsp");
            dispatcher.forward(request, response);

        }

    }

//    private void nuevaContra(HttpServletRequest request, HttpServletResponse response)
//            throws SQLException, IOException, ServletException {
//        // Recupera el dui del usuario autorizado de la sesión
//        String duiUsuario = (String) request.getSession().getAttribute("usuarioAutorizado");
//        String nuevaContrasena = request.getParameter("newPass"); // Asume que el campo de la nueva contraseña en tu formulario se llama 'newPass'
//
//        if (duiUsuario != null && nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
//            boolean actualizada = userDao.updateContra(duiUsuario, nuevaContrasena);
//
//            if (actualizada) {
//                // Si la contraseña fue actualizada exitosamente, redirecciona al usuario a la página de inicio de sesión o alguna otra página.
//                response.sendRedirect("login.jsp"); // O alguna página que indique éxito
//            } else {
//                // Manejo en caso de que la actualización no sea exitosa
//                request.setAttribute("error", "No fue posible actualizar la contraseña.");
//                RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp"); // Página de error o mensaje
//                dispatcher.forward(request, response);
//            }
//        } else {
//            // Si no hay un usuario autorizado o la nueva contraseña es inválida
//            request.setAttribute("error", "No se proporcionó la información necesaria o la sesión ha expirado.");
//            RequestDispatcher dispatcher = request.getRequestDispatcher("Error.jsp"); // Página de error o mensaje
//            dispatcher.forward(request, response);
//        }
    private void nuevaContra(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        String duiUsuario = (String) request.getSession().getAttribute("usuarioAutorizado");
        String nuevaContrasena = request.getParameter("newPass");

        if (duiUsuario != null && nuevaContrasena != null && !nuevaContrasena.isEmpty()) {
            boolean actualizada = userDao.updateContra(duiUsuario, nuevaContrasena);

            if (actualizada) {
                // Redirecciona o muestra mensaje de éxito
                request.setAttribute("messageTitle", "¡Éxito!");
                request.setAttribute("messageText", "Tu contraseña ha sido actualizada.");//correcto
                request.setAttribute("messageIcon", "success");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mensajeExito.jsp"); // o la página que indica éxito
                dispatcher.forward(request, response);
            } else {
                // No fue posible actualizar la contraseña, muestra mensaje de error
                request.setAttribute("messageTitle", "Error al actualizar");
                request.setAttribute("messageText", "No fue posible actualizar tu contraseña.");
                request.setAttribute("messageIcon", "error");
                RequestDispatcher dispatcher = request.getRequestDispatcher("mensageError.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // No hay un usuario autorizado o la nueva contraseña es inválida
            request.setAttribute("messageTitle", "Error de validación");
            request.setAttribute("messageText", "No se proporcionó la información necesaria o la sesión ha expirado.");
            request.setAttribute("messageIcon", "error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("mensageError.jsp");
            dispatcher.forward(request, response);
        }

    }


    /*
	private void showEditForm(HttpServletRequest request, HttpServletResponse response)
			throws SQLException, ServletException, IOException {
		int id = Integer.parseInt(request.getParameter("id"));
		User existingUser = userDAO.selectUser(id);
		RequestDispatcher dispatcher = request.getRequestDispatcher("registrarUsuario.jsp");
		request.setAttribute("user", existingUser);
		dispatcher.forward(request, response);

	}
     */
    private void insertUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {

        String dui = request.getParameter("dui");
        String userName = request.getParameter("nombre_u");
        String apellido = request.getParameter("apellido_u");
        String email = request.getParameter("email");
        String pass = request.getParameter("password");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = request.getParameter("Fecha_nac");
        Date fec = Date.valueOf(fecha);

        String genero = request.getParameter("genero");
        char gene = genero.charAt(0);
        String tipo = request.getParameter("estadoUser");
        System.out.println("*******************************************************" + tipo);

        if (tipo.equals("f")) {
            this.estado = false;
        } else {
            this.estado = true;
        }

// Verificar la unicidad del DUI
        boolean existeDui = userDao.verificacionDui(dui);

        if (!existeDui) {
            // Continuar con la verificación del correo
            boolean existeCorreo = userDao.verificacionEmail(email);

            if (!existeCorreo) {
                // Resto del código para la inserción del usuario
                String passAux = Encriptar.getStringMessageDigest(pass, Encriptar.SHA256);
                Usuarios newUser = new Usuarios(dui, userName, apellido, email, passAux, fec, gene, this.estado);
                this.userDao.insertUser(newUser);
                response.sendRedirect("mantenimientos/registrarUsuario.jsp");
            } else {
                // Mensaje de error por correo duplicado
                request.setAttribute("messageTitle", "Error de Registro");
                request.setAttribute("messageText", "Ya existe un usuario con el correo proporcionado.");
                request.setAttribute("messageIcon", "error");
                RequestDispatcher dispatcher = request.getRequestDispatcher("errorUser.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            // Mensaje de error por DUI duplicado
            request.setAttribute("messageTitle", "Error de Registro");
            request.setAttribute("messageText", "Ya existe un usuario con el DUI proporcionado.");
            request.setAttribute("messageIcon", "error");
            RequestDispatcher dispatcher = request.getRequestDispatcher("errorUser.jsp");
            dispatcher.forward(request, response);
        }

    }

    private void insertUserComun(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        String dui = request.getParameter("duiUser");
        String userName = request.getParameter("nombreUser");
        String apellido = request.getParameter("apellidoUser");
        String email = request.getParameter("emailUser");
        String pass = request.getParameter("passwordUser");

        // Asegúrate de que la fecha en el formulario esté en el formato "yyyy-MM-dd"
//    SimpleDateFormat formatoInput = new SimpleDateFormat("dd-MM-yyyy");
//    SimpleDateFormat formatoOutput = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = request.getParameter("fechaUser");
        Date fec = Date.valueOf(fecha);

        String genero = request.getParameter("generoUser");
        char gene = genero.charAt(0);

        String tipo = "f"; // Siempre será "f" para usuario común

        if (tipo.equals("f")) {
            this.estado = false;
        } else {
            this.estado = true;
        }

        boolean existeCorreo = userDao.verificacionEmail(email);
        if (!existeCorreo) {

            String passAux = Encriptar.getStringMessageDigest(pass, Encriptar.SHA256);
            Usuarios newUser = new Usuarios(dui, userName, apellido, email, passAux, fec, gene, this.estado);
            this.userDao.insertUser(newUser);
            //response.sendRedirect("list");
            response.sendRedirect("login.jsp");

        } else {
            response.sendRedirect("login.jsp");

        }

    }

    private void updateUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //   int id = Integer.parseInt(request.getParameter("idU")); //nullo idU es el id y no el name

        String dui = request.getParameter("idUser");
        String userName = request.getParameter("nombreUser");
        String apellido = request.getParameter("apellidoUser");
        String email = request.getParameter("emailUser");
        String pass = request.getParameter("passwordUser");
        SimpleDateFormat formato = new SimpleDateFormat("yyyy-MM-dd");
        String fecha = request.getParameter("fechaUser");
        Date fec = Date.valueOf(fecha);
        String genero = request.getParameter("generoUser");
        char gene = genero.charAt(0);
        String tipo = request.getParameter("estadoUser");

        if (tipo.equals("t")) {
            this.estado = true;

        } else {
            this.estado = false;
        }

        Usuarios updateUser = new Usuarios(dui, userName, apellido, email, pass, fec, gene, this.estado);

        this.userDao.updateUser(updateUser);
        //response.sendRedirect("list");
        response.sendRedirect("mantenimientos/registrarUsuario.jsp");//falta llamar jsp
    }

    private void deleteUser(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String dui = request.getParameter("dui");
        userDao.deleteUser(dui);
        response.sendRedirect("mantenimientos/registrarUsuario.jsp");//falta llamar y crear jsp

    }

    private void verificarCorreo(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String correo = request.getParameter("correo");
        UserDao daoUser = new UserDao();

        try {
            boolean correoExistente = daoUser.verificacionEmail(correo);
            response.getWriter().write(String.valueOf(correoExistente));
        } catch (SQLException e) {
            // Manejar la excepción según tu necesidad
            e.printStackTrace();
            response.getWriter().write("false"); // En caso de error, considerar como correo no existente
        }

    }

}
