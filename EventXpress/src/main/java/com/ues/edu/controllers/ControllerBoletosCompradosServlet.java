package com.ues.edu.controllers;

import com.ues.edu.entities.Boletos;
import com.ues.edu.entities.Usuarios;
import com.ues.edu.model.dao.BoletoCompradoUsuarioDao;
import jakarta.jms.JMSException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpSession;

@WebServlet(name = "ControllerBoletosCompradosServlet", urlPatterns = {"/ControllerBoletosCompradosServlet"})

public class ControllerBoletosCompradosServlet extends HttpServlet {

    BoletoCompradoUsuarioDao boletoCompradoDao = new BoletoCompradoUsuarioDao();

    public ControllerBoletosCompradosServlet() {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        //response.getWriter().append("Served at: ").append(request.getContextPath());
        try {
            processRequest(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(ControllerBoletosCompradosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        //response.getWriter().append("Served at: ").append(request.getContextPath());
        try {
            processRequest(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(ControllerBoletosCompradosServlet.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, JMSException {
        //String accion = request.getServletPath();
        String accion = request.getParameter("accion");

        switch (accion) {
            case "/ControllerBoletosCompradosServlet":
                listarBoletosComprados(request, response);
                break;

            case "/insert":
                break;

            case "/delete":
                break;

            case "/edit":
                break;

            case "/update":
                break;

            case "/HistorialCompras":

                listarHistorialComprasUsuario(request, response);

                break;
            default:

                break;
        }
    }

    private void listarBoletosComprados(HttpServletRequest request, HttpServletResponse response) throws SQLException, JMSException {
        String dui = request.getParameter("dui"); // Obtiene el DUI desde la solicitud
        if (dui != null && !dui.isEmpty()) {//no nulo y no vacío
            try {

                request.setAttribute("boletosComprados", this.boletoCompradoDao.getUsuariosConBoletosComprados(dui));
                request.getRequestDispatcher("consultas/eventosCompradosPorUsuarios.jsp").forward(request, response);
            } catch (ServletException | IOException ex) {
                Logger.getLogger(ControllerBoletosCompradosServlet.class.getName()).log(Level.SEVERE, null, ex);
            }
        }

    }

    private void listarHistorialComprasUsuario(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException {
        // Acción para listar el historial de compras.
        HttpSession session = request.getSession(false);
        if (session != null && session.getAttribute("usuarios") != null) {
            //Usuarios usuario = (Usuarios) session.getAttribute("usuarios");

            String dui = request.getParameter("dui");
            List<Boletos> historialCompras = boletoCompradoDao.getHistorialComprasPorUsuario(dui);
            request.setAttribute("listaHistorialCompras", historialCompras);
            request.getRequestDispatcher("historialCompraUsuario.jsp").forward(request, response);
        }
    }

}
