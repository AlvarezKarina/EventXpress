/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.controllers;

import com.ues.edu.model.dao.CompraEntradasDao;
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
 * @author nelki
 */
@WebServlet(name = "ControllersCompraEntradas", urlPatterns = {"/ControllersCompraEntradas"})

public class ControllersCompraEntradas extends HttpServlet {

    private CompraEntradasDao compraDao;

    @Override
    public void init() throws ServletException {
        super.init();

        compraDao = new CompraEntradasDao();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {

        try {
            processRequest(request, response);

        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(ControllersCompraEntradas.class.getName()).log(Level.SEVERE, null, ex);
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
                listarCompraEntradas(request, response);
                break;
        }
    }

    private void listarCompraEntradas(HttpServletRequest request, HttpServletResponse response) throws SQLException, JMSException {
        try {
            request.setAttribute("listaCompraMas", compraDao.getCompraEntradas());
            request.getRequestDispatcher("consultas/compraEntradasMas.jsp").forward(request, response);
        } catch (ServletException | IOException ex) {
            Logger.getLogger(ControllersCompraEntradas.class.getName()).log(Level.SEVERE, null, ex);
        }

    }

}
