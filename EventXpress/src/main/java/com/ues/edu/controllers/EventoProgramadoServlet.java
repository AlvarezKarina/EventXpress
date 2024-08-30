package com.ues.edu.controllers;

import com.ues.edu.entities.Establecimiento;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import com.ues.edu.model.dao.EventoProgramadoDao;
import java.io.IOException;
import java.sql.Date;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.JOptionPane;

/**
 *
 * @author Cisneros
 */
@WebServlet(name = "EventoProgramadoServlet", urlPatterns = {"/EventoProgramadoServlet"})
public class EventoProgramadoServlet extends HttpServlet {

    private static final long serialVersionUID = 1L;
    private EventoProgramadoDao eventoProgramadoDao;

    @Override
    public void init() {
        eventoProgramadoDao = new EventoProgramadoDao();
    }

    @Override
    protected void doPost(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        // Establecer la codificación de caracteres antes de leer los parámetros
        request.setCharacterEncoding("UTF-8");

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
                    insertEventoProgramado(request, response);
                    break;
                case "delete":
                    deleteEventoProgramado(request, response);
                    break;
                case "update":
                    updateEventoProgramado(request, response);
                    break;
                default://si no selecciono niuna opcion anterior x defecto me carga
                    listEventosProgramados(request, response);
                    break;
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(null, "ERROR CASE " + ex);
            throw new ServletException(ex);
        }
    }

    private void listEventosProgramados(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, ServletException, IOException {
        ArrayList<Evento_Programado> listaEventosProgramados = eventoProgramadoDao.obtenerTodos();
        request.setAttribute("listaEventosProgramados", listaEventosProgramados);
        RequestDispatcher dispatcher = request.getRequestDispatcher("mantenimientos/verEventosProgramados.jsp");
        dispatcher.forward(request, response);
    }

    private void showNewForm(HttpServletRequest request, HttpServletResponse response)
            throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("mantenimientos/insertarEventoProgramado.jsp");
        dispatcher.forward(request, response);
    }

    private void insertEventoProgramado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Lógica para obtener los parámetros del formulario y crear un nuevo objeto Evento_Programado
        Date fecha = Date.valueOf(request.getParameter("fecha"));
        Time horaInicio = Time.valueOf(request.getParameter("horaInicio") + ":00");
        Time horaFin = Time.valueOf(request.getParameter("horaFin") + ":00");
        boolean suspendido = request.getParameter("suspendido") != null;
        boolean reprogramado = request.getParameter("reprogramado") != null;
        Date fechaReprogramado = reprogramado ? Date.valueOf(request.getParameter("fechaReprogramado")) : null;
        //int idEvento = Integer.parseInt(request.getParameter("idEvento"));
        String nombreEvento = request.getParameter("nombreEvento"); // Cambia esto al nombre correcto del campo del formulario
        String nombreEstablec = request.getParameter("nombreEstablecimiento"); // Cambia esto al nombre correcto del campo del formulario

        // Utiliza el método para obtener el ID del evento
        int idEvento = eventoProgramadoDao.obtenerIdEvento(nombreEvento);
        // Utiliza el método para obtener el ID del establecimiento
        int idEstablecim = eventoProgramadoDao.obtenerIdEstablecimiento(nombreEstablec);

        Evento_Programado nuevoEvento = new Evento_Programado();
        nuevoEvento.setFecha(fecha);
        nuevoEvento.setHora_inicia(horaInicio);
        nuevoEvento.setHora_fin(horaFin);
        nuevoEvento.setSuspendido(suspendido);
        nuevoEvento.setReprogramado(reprogramado);
        nuevoEvento.setFecha_reprogramado(fechaReprogramado);

        Eventos evento = new Eventos();
        evento.setIdEvento(idEvento);
        nuevoEvento.setId_evento(evento);

        Establecimiento esta = new Establecimiento();
        esta.setId_establecimiento(idEstablecim);
        nuevoEvento.setEstablecimientos(esta);

        // Realizar verificación de duplicados
        // boolean existe = eventoProgramadoDao.existeEventoProgramado(nombreEvento, fecha, horaInicio, horaFin, nombreEstablec);
        boolean existe = eventoProgramadoDao.existeEventoProgramado(fecha, horaInicio, horaFin, nombreEstablec);

        if (existe) {
            // Establece el mensaje de error
            String errorMessage = "Ya existe un evento programado para la misma fecha y hora en ese establecimiento.";
            request.setAttribute("error", errorMessage);

            // Vuelve a cargar el formulario manteniendo el request actual
            RequestDispatcher dispatcher = request.getRequestDispatcher("mantenimientos/insertarEventoProgramado.jsp");
            dispatcher.forward(request, response);
            return;
        } else {
            // Procede con el registro y redirige
            eventoProgramadoDao.registrar(nuevoEvento);
            response.sendRedirect("EventoProgramadoServlet?action=listar");
        }
    }

    private void updateEventoProgramado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException, ServletException {
        // Lógica para obtener los parámetros del formulario y actualizar un objeto Evento_Programado
        int idEventoProgramado = Integer.parseInt(request.getParameter("id_evento_prograActualizar"));
        Date fecha = Date.valueOf(request.getParameter("fechaActualizar"));
        String horaInicioStr = request.getParameter("horaInicioActualizar") + ":00"; // Asegura el formato HH:mm:ss
        String horaFinStr = request.getParameter("horaFinActualizar") + ":00";

        if (horaInicioStr != null && horaFinStr != null && !horaInicioStr.isEmpty() && !horaFinStr.isEmpty()) {
            Time horaInicio;
            Time horaFin;
            try {
                horaInicio = Time.valueOf(horaInicioStr);
                horaFin = Time.valueOf(horaFinStr);
            } catch (IllegalArgumentException e) {
                // Maneja el error, por ejemplo, estableciendo un mensaje de error y reenviando al usuario al formulario de actualización
                return;
            }
            boolean suspendido = request.getParameter("suspendidoActualizar") != null;
            boolean reprogramado = request.getParameter("reprogramadoActualizar") != null;
            Date fechaReprogramado = reprogramado ? Date.valueOf(request.getParameter("fechaReprogramadoActualizar")) : null;
            String nombreEvento = request.getParameter("nombreEvento");
            String nombreEstablec = request.getParameter("nombreEstablecimiento");

            // Utiliza el método del dao para enviar el nombre y recibir el id del evento y del establecimiento
            int idEvento = eventoProgramadoDao.obtenerIdEvento(nombreEvento);
            int idEstablecim = eventoProgramadoDao.obtenerIdEstablecimiento(nombreEstablec);

            Evento_Programado nuevoEventoPro = new Evento_Programado();
            nuevoEventoPro.setId_evento_progra(idEventoProgramado);
            nuevoEventoPro.setFecha(fecha);
            nuevoEventoPro.setHora_inicia(horaInicio);
            nuevoEventoPro.setHora_fin(horaFin);
            nuevoEventoPro.setSuspendido(suspendido);
            nuevoEventoPro.setReprogramado(reprogramado);
            nuevoEventoPro.setFecha_reprogramado(fechaReprogramado);

            Eventos evento = new Eventos();
            evento.setIdEvento(idEvento);

            Establecimiento esta = new Establecimiento();
            esta.setId_establecimiento(idEstablecim);

            nuevoEventoPro.setEstablecimientos(esta);
            nuevoEventoPro.setId_evento(evento);

            // Antes de actualizar, verifica si el nuevo horario se superpone con algún evento existente
            boolean existe = eventoProgramadoDao.existeEventoProgramado(fecha, horaInicio, horaFin, nombreEstablec, idEventoProgramado);
            if (existe) {
                // Establece el mensaje de error
                String errorMessage = "Ya existe un evento programado para la misma fecha y hora en ese establecimiento.";
                request.setAttribute("error", errorMessage);

                // Asigna los valores actuales como atributos del request para reutilizarlos en el formulario
                request.setAttribute("id_evento_prograActualizar", String.valueOf(idEventoProgramado));
                request.setAttribute("fechaActualizar", request.getParameter("fechaActualizar"));
                request.setAttribute("horaInicioActualizar", request.getParameter("horaInicioActualizar"));
                request.setAttribute("horaFinActualizar", request.getParameter("horaFinActualizar"));
                request.setAttribute("suspendidoActualizar", request.getParameter("suspendidoActualizar"));
                request.setAttribute("reprogramadoActualizar", request.getParameter("reprogramadoActualizar"));
                request.setAttribute("fechaReprogramadoActualizar", request.getParameter("fechaReprogramadoActualizar"));
                request.setAttribute("nombreEvento", nombreEvento);
                request.setAttribute("nombreEstablecimiento", nombreEstablec);

                // Vuelve a cargar el formulario manteniendo el request actual
                RequestDispatcher dispatcher = request.getRequestDispatcher("mantenimientos/actualizarEventoProgramado.jsp");
                dispatcher.forward(request, response);
                return;

            } else {
                // Proceso de actualización si no hay superposición
                eventoProgramadoDao.actualizar(nuevoEventoPro);

                response.sendRedirect("EventoProgramadoServlet?action=listar");
            }
        }
    }

   
    private void deleteEventoProgramado(HttpServletRequest request, HttpServletResponse response)
            throws SQLException, IOException {
        int idEventoProgramado = Integer.parseInt(request.getParameter("id"));
        eventoProgramadoDao.eliminar(idEventoProgramado);
        response.sendRedirect("EventoProgramadoServlet?action=listar");
    }
}