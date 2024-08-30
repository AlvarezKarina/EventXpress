/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.controllers;


import com.ues.edu.entities.Zona_Establec;
import com.ues.edu.model.dao.Zona_Establec_Dao;
import jakarta.jms.JMSException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;
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

@WebServlet("/zona_establec")
public class Zona_Establec_Controller extends HttpServlet{
    
    Zona_Establec_Dao zona_establec = new Zona_Establec_Dao();

    public Zona_Establec_Controller() {
        super();
    }
    
     protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
         response.getWriter().append("Served at: ").append(request.getContextPath());
         try{
             processRequest(request, response);
             
//             List<Zona_Establec> listaBusqueda= zona_establec.getListZona_Establec();
//             request.setAttribute("listaBusqueda", listaBusqueda);
//             request.getRequestDispatcher("capacidad_total.jsp").forward(request, response);
//             
         }catch (SQLException e) {
              e.printStackTrace();
         }catch (JMSException ex) {
            Logger.getLogger(Zona_Establec_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
         
     }
     
      protected void doPost(HttpServletRequest request,HttpServletResponse response)throws ServletException,IOException{
        try {
            processRequest(request, response);
            
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (JMSException ex) {
            Logger.getLogger(Zona_Establec_Controller.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
//
//    private void processRequest(HttpServletRequest request, HttpServletResponse response) {
//        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
//    }

 
    
    protected void processRequest(HttpServletRequest request, HttpServletResponse response) throws IOException,SQLException, JMSException
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
            listarCapacidad(request, response);
            
           //listarMedicos(request,response);
           break;
    }
}
    
    private void listarCapacidad(HttpServletRequest request, HttpServletResponse response) throws SQLException, JMSException{
       
        try {
             request.setAttribute("listaZonas", zona_establec.getListZona_Establec());
            request.getRequestDispatcher("consultas/capacidad_total.jsp").forward(request, response);
        } catch (ServletException |IOException ex) {
            Logger.getLogger(Zona_Establec_Controller.class.getName()).log(Level.SEVERE, null, ex);
        } 
//        catch (IOException ex) {
//            Logger.getLogger(Zona_Establec_Controller.class.getName()).log(Level.SEVERE, null, ex);
//        } 
//        catch (IOException ex) {
//            Logger.getLogger(Zona_Establec_Controller.class.getName()).log(Level.SEVERE, null, ex);
//        }
        
    
        }
    
}
