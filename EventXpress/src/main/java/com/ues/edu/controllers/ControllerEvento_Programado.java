package com.ues.edu.controllers;

import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.model.dao.Evento_ProDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/boletos")
///@WebServlet("/even_pro")
@WebServlet(name = "ControllerEvento_Programado", urlPatterns = {"/ControllerEvento_Programado"})
public class ControllerEvento_Programado extends HttpServlet {

    private Evento_ProDao evtDao;
    private Evento_Programado evenPro;

    @Override
    public void init() throws ServletException {
        super.init();
        // Aquí puedes inicializar tu DAO si es necesario
        evtDao = new Evento_ProDao(); // Reemplaza "BoletoDao" con el nombre de tu clase DAO de Boletos
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        this.evtDao = new Evento_ProDao();
        this.evenPro = new Evento_Programado();

        if (request.getParameter("botonEnviarFecha") != null) {
            try {
                String fechaSeleccionada = request.getParameter("botonEnviarFecha");
                List<Evento_Programado> listaEventos = this.evtDao.getEventoProgramado(fechaSeleccionada);
                request.setAttribute("listaEventosProgramados", listaEventos);
            } catch (SQLException ex) {
                Logger.getLogger(ControllerEvento_Programado.class.getName()).log(Level.SEVERE, null, ex);
            }
            request.getRequestDispatcher("consultas/Fecha_Evento_Programado.jsp").forward(request, response);
        }
    }

}
