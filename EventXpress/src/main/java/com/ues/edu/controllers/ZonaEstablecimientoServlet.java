package com.ues.edu.controllers;

import com.ues.edu.entities.Establecimiento;
import com.ues.edu.entities.Zona_Establec;
import com.ues.edu.entities.Zonas;
import com.ues.edu.model.dao.ZonaEstablecimientoDao;
import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

@WebServlet(name = "ZonaEstablecimientoServlet", urlPatterns = {"/ZonaEstablecimientoServlet"})
public class ZonaEstablecimientoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private ZonaEstablecimientoDao zonaEstabDao;

    public void init() {
        zonaEstabDao = new ZonaEstablecimientoDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        doGet(request, response);
    }

    @Override
    protected void doGet(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        String action = request.getParameter("action");
        try {
            switch (action) {
                case "new":
                    showNewForm(request, response);
                    break;
                case "insert":
                    insertZonaEstablecimiento(request, response);
                    break;
                case "delete":
                    deleteZonaEstablecimiento(request, response);
                    break;
                case "update":
                    updateZonaEstablecimiento(request, response);
                    break;
                default:
                    listZonaEstablecimiento(request, response);
                    break;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR CASE " + ex);
            throw new ServletException(ex);
        }
    }

    private void listZonaEstablecimiento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        ArrayList<Zona_Establec> listaZonaEstablecimiento = zonaEstabDao.obtenerTodos();
        request.setAttribute("listaZonasEstablecimiento", listaZonaEstablecimiento);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mantenimientos/VerZonaEstablecimiento.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("mantenimientos/IngresarZonaEstablecimiento.jsp");
        dispatcher.forward(request, response);
    }

    private void insertZonaEstablecimiento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        double precio = Double.parseDouble(request.getParameter("txtPrecio"));
        int capacidadZona = Integer.parseInt(request.getParameter("txtCapacidadZona"));
        String nombre_zona = request.getParameter("cbNombreZona");
        String n_establecimiento = request.getParameter("cbNomEstablec");

        int idZona = zonaEstabDao.obtenerIdZona(nombre_zona);
        int idEstablecimiento = zonaEstabDao.obtenerIdEstablecimiento(n_establecimiento);

        Zona_Establec nuevaZonaE = new Zona_Establec();
        nuevaZonaE.setPrecio(precio);
        nuevaZonaE.setCapacidadZona(capacidadZona);

        Zonas zonaNueva = new Zonas();
        zonaNueva.setIdZonas(idZona);
        nuevaZonaE.setIdZona(zonaNueva);

        Establecimiento establecNuevo = new Establecimiento();
        establecNuevo.setId_establecimiento(idEstablecimiento);
        nuevaZonaE.setIdEstablecimiento(establecNuevo);

        this.zonaEstabDao.registrar(nuevaZonaE);

        response.sendRedirect("ZonaEstablecimientoServlet?action=listar");
    }

    private void updateZonaEstablecimiento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {

        int idZonaEstablecimiento = Integer.parseInt(request.getParameter("id_zona_establecActu"));
        double precio = Double.parseDouble(request.getParameter("precioActu"));
        int capacidadZona = Integer.parseInt(request.getParameter("capacidadZonaActu"));
        String nombre_zona = request.getParameter("nombreZona");
        String n_establecimiento = request.getParameter("nombreEstablec");

        int idZona = zonaEstabDao.obtenerIdZona(nombre_zona);
        int idEstablecimiento = zonaEstabDao.obtenerIdEstablecimiento(n_establecimiento);

        Zona_Establec nuevaZonaE = new Zona_Establec();
        nuevaZonaE.setIdZonaEstablecimiento(idZonaEstablecimiento);
        nuevaZonaE.setPrecio(precio);
        nuevaZonaE.setCapacidadZona(capacidadZona);

        Zonas zonaNueva = new Zonas();
        zonaNueva.setIdZonas(idZona);
        nuevaZonaE.setIdZona(zonaNueva);

        Establecimiento establecNuevo = new Establecimiento();
        establecNuevo.setId_establecimiento(idEstablecimiento);
        nuevaZonaE.setIdEstablecimiento(establecNuevo);

        this.zonaEstabDao.actualizar(nuevaZonaE);

        response.sendRedirect("ZonaEstablecimientoServlet?action=listar");
    }

    private void deleteZonaEstablecimiento(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int id_zona_establec = Integer.parseInt(request.getParameter("id"));
        System.out.println(id_zona_establec);
        String bandera = "jskla";
        this.zonaEstabDao.eliminar(id_zona_establec);
        response.sendRedirect("ZonaEstablecimientoServlet?action=listar");
    }
}
