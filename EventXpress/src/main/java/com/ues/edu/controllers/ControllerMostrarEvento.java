
/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.controllers;

import com.ues.edu.model.dao.MostrarEventoDao;
import com.ues.edu.model.dao.TotalBoletosVendidosDao;
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
 * @author alexa
 */
@WebServlet(name = "ControllerMostrarEvento", urlPatterns = {"/ControllerMostrarEvento"})

public class ControllerMostrarEvento extends HttpServlet {
     private MostrarEventoDao mostrarEventoDao;

    public ControllerMostrarEvento() {
        this.mostrarEventoDao= new MostrarEventoDao();
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
            Logger.getLogger(ControllerMostrarEvento.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(ControllerMostrarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException, SQLException, JMSException {
        String accion = request.getServletPath();
        switch (accion) {
            case "/ControllerMostrarEvento":
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
         String id_mostrar = request.getParameter("id_mostrar"); // Obtiene el id desde la solicitud
         
         int idCategoria = Integer.parseInt(id_mostrar);
        try {
            request.setAttribute("listaMostrarEvento", mostrarEventoDao.getEventosCategoria(idCategoria));
            request.getRequestDispatcher("consultas/mostrar_evento.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ControllerMostrarEvento.class.getName()).log(Level.SEVERE, null, ex);
        }

    }
    
}
