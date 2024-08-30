
package com.ues.edu.controllers;

import com.ues.edu.entities.Establecimiento;
import com.ues.edu.model.dao.Dao_Establecimiento;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 *
 * @author karie
 */
@WebServlet(name = "Controller_Establecimiento", urlPatterns = {"/Controller_Establecimiento"})
public class Controller_Establecimiento extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private Dao_Establecimiento establecDao;

    public void init() {
        establecDao = new Dao_Establecimiento();
    }

    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        //String action = request.getServletPath();

        String action = request.getParameter("accion");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertEstablec(request, response);
                    break;
                case "delete":
                    deleteEstablec(request, response);
                    break;
                //case "edit":
                //	showEditForm(request, response);
                //break;
                case "update":
                    updateEstablec(request, response);
                    break;
                case "altaE":
                    altaEstablec(request, response);
                    break;
                default:
                    listEstablec(request, response);
                    break;
            }
        } catch (SQLException ex) {
            throw new ServletException(ex);
        }
    }

    private void listEstablec(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        ArrayList<Establecimiento> listEstablec = establecDao.selectAllEstablec();
        request.setAttribute("listEstablec", listEstablec);
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-list.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("user-form.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEstablec(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        //int id = Integer.parseInt(request.getParameter("id_establecimiento"));
        String nombreE = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        int capacidadE = Integer.parseInt(request.getParameter("capacidad"));
        //request.getParameter("capacidad_establec");
        //System.out.println(r);

        Establecimiento newEstablec = new Establecimiento(nombreE, direccion, capacidadE);
        this.establecDao.insertEstablec(newEstablec);
        //response.sendRedirect("list");
        response.sendRedirect("mantenimientos/Establecimientos_Lista.jsp");

    }

    private void updateEstablec(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        //int id = Integer.parseInt(request.getParameter("idU")); //nullo idU es el id y no el name

        int id = Integer.parseInt(request.getParameter("idEstablec"));
        String nombreE = request.getParameter("nombre");
        String direccion = request.getParameter("direccion");
        int capacidadE = Integer.parseInt(request.getParameter("capacidad"));

        Establecimiento newEstablec = new Establecimiento(id, nombreE, direccion, capacidadE);
        this.establecDao.updateEstablec(newEstablec);
        //response.sendRedirect("list");
        response.sendRedirect("mantenimientos/Establecimientos_Lista.jsp");

    }

    private void deleteEstablec(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idE = Integer.parseInt(request.getParameter("idEstablec"));
        establecDao.deleteEstablec(idE);
        response.sendRedirect("mantenimientos/Establecimientos_Lista.jsp");

    }

    private void altaEstablec(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idE = Integer.parseInt(request.getParameter("idEstablec"));
        establecDao.altaEstablec(idE);
        response.sendRedirect("mantenimientos/Establecimientos_Lista.jsp");

    }

}
