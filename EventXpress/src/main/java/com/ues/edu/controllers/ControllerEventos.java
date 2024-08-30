/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.controllers;

import com.ues.edu.entities.Categoria;
import com.ues.edu.entities.Eventos;
import com.ues.edu.model.dao.EventoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author herna
 */
@WebServlet(name = "ControllerEventos", urlPatterns = {"/ControllerEventos"})
public class ControllerEventos extends HttpServlet{
    private EventoDao daoEvt;
    
    
    
    public void init(){
        daoEvt=new EventoDao();
    }
    
    
    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException{
        doGet(request, response);
    }
    
    
    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException {
        String action = request.getParameter("accion");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEvento(request, response);
                    break;
                case "delete":
                    deleteEvento(request, response);
                    break;
                //case "edit":
                //	showEditForm(request, response);
                //break;
                case "update":
                    updateEvento(request, response);
                    break;
                default:
                    listEvt(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }
     private void listEvt(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<Eventos> listEvt = daoEvt.selectAll();
        request.setAttribute("listEvt", listEvt);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mantenimientos/registrarEventos.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }
    
    private void insertEvento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        Categoria cat = new Categoria();
        
        String nombre=request.getParameter("nombre");
        String descripcion=request.getParameter("descripcion");
        String idCategoria=request.getParameter("codiCat");
        
        cat.setIdCategoria(Integer.parseInt(idCategoria));
        Eventos newEvento = new Eventos();
        newEvento.setnEvento(nombre);
        newEvento.setDescripcion(descripcion);
        newEvento.setCategoria(cat);
        this.daoEvt.insertEvento(newEvento);
        //response.sendRedirect("list");
        response.sendRedirect("mantenimientos/registrarEventos.jsp");
    }

    private void updateEvento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
     //   int id = Integer.parseInt(request.getParameter("idU")); //nullo idU es el id y no el name
          
       int id = Integer.parseInt(request.getParameter("idEvento"));
        
        String nombre=request.getParameter("nombreEvento");
        String descripcion = request.getParameter("descripcionEvento");
        int id_categoria = Integer.parseInt(request.getParameter("codigoCategoria"));
        
        Categoria cat = new Categoria();
        cat.setIdCategoria(id_categoria);
        
        Eventos evt = new Eventos();
        evt.setIdEvento(id);
        evt.setnEvento(nombre);
        evt.setDescripcion(descripcion);
        evt.setCategoria(cat);
        
        this.daoEvt.updateEvento(evt);
        //response.sendRedirect("list");
        response.sendRedirect("mantenimientos/registrarEventos.jsp");
    }

    private void deleteEvento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        String id=request.getParameter("idE");
        int id_evento = Integer.parseInt(id);
        
        System.out.println(id_evento);
        String bandera="jskla";
        daoEvt.deleteEvento(id_evento);
        response.sendRedirect("mantenimientos/registrarEventos.jsp");

    }
}
