package com.ues.edu.controllers;

import com.ues.edu.entities.Categoria;
import com.ues.edu.model.dao.CategoriaDao;
import jakarta.jms.JMSException;
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

@WebServlet("/categoria")

public class ControllerCategoria extends HttpServlet {

    private CategoriaDao cateDao; 

    @Override
    public void init() throws ServletException {
        super.init();
        // Aquí puedes inicializar tu DAO si es necesario
        cateDao = new CategoriaDao(); // Reemplaza "BoletoDao" con el nombre de tu clase DAO de Boletos
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //doGet se encarga de obtener la lista de boletos y enviarla a la página JSP
        try {
            processRequest(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(ControllerCategoria.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException,IOException,SQLException, JMSException
{
    String accion= request.getServletPath();
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
           listarCategoriaIngresos(request,response);
           break;
    }
}
private void listarCategoriaIngresos(HttpServletRequest request, HttpServletResponse response) throws SQLException, JMSException{
        try {
            request.setAttribute("listaCategoriaIng",cateDao.getIngresosCategoria());
            request.getRequestDispatcher("Evt_Fecha_Especific.jsp").forward(request, response);
        } catch (ServletException|IOException ex) {
            Logger.getLogger(ControllerCategoria.class.getName()).log(Level.SEVERE,null,ex);
        }
        
    }
    

}
