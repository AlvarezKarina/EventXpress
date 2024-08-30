
package com.ues.edu.controllers;

import com.ues.edu.entities.Boletos; 
import com.ues.edu.model.dao.BoletoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.annotation.WebServlet;

//@WebServlet("/boletos")
@WebServlet(name = "ControllerBoletos", urlPatterns = {"/ControllerBoletos"})

public class ControllerBoletos extends HttpServlet {

    private BoletoDao boletoDao; 

    @Override
    public void init() throws ServletException {
        super.init();
        // Aquí puedes inicializar tu DAO si es necesario
        boletoDao = new BoletoDao(); // Reemplaza "BoletoDao" con el nombre de tu clase DAO de Boletos
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //doGet se encarga de obtener la lista de boletos y enviarla a la página JSP
        try {
            List<Boletos> listaBoletos = boletoDao.getUsuariosConBoletos();
            request.setAttribute("listaBoletos", listaBoletos);
            request.getRequestDispatcher("consultas/listarBoletos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Aquí puedes manejar errores de base de datos 
        }
    }

    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //doGet se encarga de obtener la lista de boletos y enviarla a la página JSP
        try {
            List<Boletos> listaBoletos = boletoDao.getUsuariosConBoletos();
            request.setAttribute("listaBoletos", listaBoletos);
            request.getRequestDispatcher("consultas/listarBoletos.jsp").forward(request, response);
        } catch (SQLException e) {
            e.printStackTrace();
            // Aquí puedes manejar errores de base de datos 
        }
    }
}
