package com.ues.edu.controllers;


import com.ues.edu.model.dao.BusquedaEventosDao;
import jakarta.jms.JMSException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author karie
 */
@WebServlet(name = "BusquedaEventosController", urlPatterns = {"/BusquedaEventosController"})
public class BusquedaEventosController extends HttpServlet{
    private BusquedaEventosDao mostrarEventoDao;

    public BusquedaEventosController() {
        this.mostrarEventoDao= new BusquedaEventosDao();
        //super();
    }
//mostrar y recuperar informacion
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        try {
            processRequest(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(BusquedaEventosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    //enviar y mostrar
      protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.getWriter().append("Served at: ").append(request.getContextPath());
        try {
            processRequest(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(BusquedaEventosController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, JMSException {
        String accion = request.getServletPath();
        switch (accion) {
            case "/BusquedaEventosController":
                 listaMostrarEvento(request, response);
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
               
                break;
        }
    }

    private void listaMostrarEvento(HttpServletRequest request, HttpServletResponse response) throws SQLException, JMSException {
         //String id_mostrar = request.getParameter("id_mostrar"); // Obtiene el id desde la solicitud
         String n_evento = request.getParameter("n_evento");
         
         //int idCategoria = Integer.parseInt(id_mostrar);
        try {
            request.setAttribute("listaMostrarEvento", mostrarEventoDao.getEventosCategoria(n_evento));
            request.getRequestDispatcher("consultas/BusquedaEvento.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(BusquedaEventosController.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
