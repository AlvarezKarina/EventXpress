package com.ues.edu.controllers;

import com.ues.edu.model.dao.TotalBoletosVendidosDao;
import java.io.IOException;
import java.sql.SQLException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import jakarta.jms.JMSException;
import java.util.logging.Level;
import java.util.logging.Logger;

@WebServlet("/ControllerTotalBoletosVendidos")
public class ControllerTotalBoletosVendidos extends HttpServlet {

    private TotalBoletosVendidosDao eventoProgramadoDao;

    public ControllerTotalBoletosVendidos() {
        this.eventoProgramadoDao = new TotalBoletosVendidosDao();
        //super();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        try {
            processRequest(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(ControllerTotalBoletosVendidos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, JMSException {
        String accion = request.getServletPath();
        switch (accion) {
            case "/new":
                break;

            case "/insert":
                break;

            case "/delete":
                break;

            case "/edit":
                break;

            case "/update":
                break;
            default:
                listaEventoProgramado(request, response);
                break;
        }
    }

    private void listaEventoProgramado(HttpServletRequest request, HttpServletResponse response) throws SQLException, JMSException {
        try {
            request.setAttribute("listaEventoProgramado", eventoProgramadoDao.getEventosVendidosDes());
            request.getRequestDispatcher("consultas/eventos_vendidos.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ControllerTotalBoletosVendidos.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
}
