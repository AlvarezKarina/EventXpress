package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Establecimiento;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Time;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JOptionPane;

/**
 *
 * @author Cisneros
 */
public class EventoProgramadoDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;

    private static final String INSERT_EVE_PROGRA = "INSERT INTO evento_programado(fecha, hora_inicia, hora_fin, suspendido, reprogramado, fecha_reprogramado, id_evento,id_establecimiento ) VALUES (?,?,?,?,?,?,?,?)";
    private static final String UPDATE_EVE_PROGRA = "UPDATE evento_programado SET fecha=?, hora_inicia=?, hora_fin=?, suspendido=?, reprogramado=?, fecha_reprogramado=?, id_evento=?, id_establecimiento=? WHERE id_evento_progra=?";
    private static final String DELETE_EVE_PROGRA = "DELETE FROM evento_programado WHERE id_evento_progra = ?";

    private static final String SELECT_ALL_EVE_PROGRA = "SELECT ep.id_evento_progra, ep.fecha, ep.hora_inicia, ep.hora_fin, "
            + "ep.suspendido, ep.reprogramado, ep.fecha_reprogramado, e.n_evento, es.n_establecimiento "
            + "FROM evento_programado ep "
            + "INNER JOIN eventos e ON ep.id_evento = e.id_evento "
            + "INNER JOIN establecimiento es ON es.id_establecimiento = ep.id_establecimiento";

    public EventoProgramadoDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Evento_Programado> obtenerTodos() throws SQLException {
        ArrayList<Evento_Programado> listaEventosProgramados = new ArrayList<>();

        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_ALL_EVE_PROGRA);
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {
                // Construye objetos Evento_Programado a partir de los resultados de la consulta
                Evento_Programado eventoProgramado = new Evento_Programado();
                eventoProgramado.setId_evento_progra(rs.getInt("id_evento_progra"));
                eventoProgramado.setFecha(rs.getDate("fecha"));
                eventoProgramado.setHora_inicia(rs.getTime("hora_inicia"));
                eventoProgramado.setHora_fin(rs.getTime("hora_fin"));
                eventoProgramado.setSuspendido(rs.getBoolean("suspendido"));
                eventoProgramado.setReprogramado(rs.getBoolean("reprogramado"));
                eventoProgramado.setFecha_reprogramado(rs.getDate("fecha_reprogramado"));

                Eventos evento = new Eventos();
                evento.setnEvento(rs.getString("n_evento"));
                eventoProgramado.setId_evento(evento);

                Establecimiento establecimiento = new Establecimiento();
                establecimiento.setN_establecimiento(rs.getString("n_establecimiento"));
                eventoProgramado.setEstablecimientos(establecimiento);

                listaEventosProgramados.add(eventoProgramado);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SELECT ALL EVENT_PRO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR ALL MOSTRAR LISTA: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos
            cerrarConexiones();
        }

        return listaEventosProgramados;
    }

    public int obtenerIdEvento(String nombreEvento) throws SQLException {
        String sql = "SELECT id_evento FROM eventos WHERE n_evento = ?";
        int id = -1; // Inicializado con un valor por defecto, puedes cambiarlo según tus necesidades

        try {
            accesoDB = conexion.getConexion();
            ps = accesoDB.prepareStatement(sql);
            ps.setString(1, nombreEvento);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_evento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trata el error según tus necesidades
        } finally {
            cerrarConexiones();
        }

        return id;
    }

    public int obtenerIdEstablecimiento(String nombreEstablecimiento) throws SQLException {
        String sql = "SELECT id_establecimiento FROM establecimiento WHERE n_establecimiento = ?";
        int id = -1; // Inicializado con un valor por defecto, puedes cambiarlo según tus necesidades

        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(sql);
            this.ps.setString(1, nombreEstablecimiento);
            this.rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_establecimiento");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE ID ESTable " + e.getMessage());
            // Trata el error según tus necesidades
        } finally {
            cerrarConexiones();
        }

        return id;
    }

    public ArrayList<String> obtenerNombresEstablecimiento() throws SQLException {
        String sql = "SELECT n_establecimiento FROM establecimiento";
        ArrayList<String> nombresEstablecimiento = new ArrayList<>();

        try {
            accesoDB = conexion.getConexion();
            ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreEstablecimiento = rs.getString("n_establecimiento");
                nombresEstablecimiento.add(nombreEstablecimiento);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DE NOMBRE ESTable" + e.getMessage());
            // Trata el error según tus necesidades
        } finally {
            cerrarConexiones();
        }

        return nombresEstablecimiento;
    }

    public ArrayList<String> obtenerNombresEventos() throws SQLException {
        String sql = "SELECT n_evento FROM eventos";
        ArrayList<String> nombresEventos = new ArrayList<>();

        try {
            accesoDB = conexion.getConexion();
            ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreEvento = rs.getString("n_evento");
                nombresEventos.add(nombreEvento);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trata el error según tus necesidades
        } finally {
            cerrarConexiones();
        }

        return nombresEventos;
    }

    public void registrar(Evento_Programado evntPro) throws SQLException {
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(INSERT_EVE_PROGRA);

            // Establece los parámetros en la consulta
            this.ps.setDate(1, (Date) evntPro.getFecha());
            this.ps.setTime(2, evntPro.getHora_inicia());
            this.ps.setTime(3, evntPro.getHora_fin());
            this.ps.setBoolean(4, evntPro.isSuspendido());
            this.ps.setBoolean(5, evntPro.isReprogramado());
            this.ps.setDate(6, (Date) evntPro.getFecha_reprogramado());
            this.ps.setInt(7, evntPro.getId_evento().getIdEvento());
            this.ps.setInt(8, evntPro.getEstablecimientos().getId_establecimiento());

            // Ejecuta la consulta SQL para insertar el evento programado
            int filasAfectadas = this.ps.executeUpdate();

            // Verifica si la inserción fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Evento programado registrado correctamente");
            } else {
                System.out.println("Error al registrar el evento programado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR INSERT EVENT_PRO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos
            cerrarConexiones();
        }
    }

    public void actualizar(Evento_Programado evntPro) throws SQLException {
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(UPDATE_EVE_PROGRA);

            // Establece los parámetros en la consulta
            this.ps.setDate(1, (Date) evntPro.getFecha());
            this.ps.setTime(2, evntPro.getHora_inicia());
            this.ps.setTime(3, evntPro.getHora_fin());
            this.ps.setBoolean(4, evntPro.isSuspendido());
            this.ps.setBoolean(5, evntPro.isReprogramado());
            this.ps.setDate(6, (Date) evntPro.getFecha_reprogramado());
            this.ps.setInt(7, evntPro.getId_evento().getIdEvento());
            this.ps.setInt(8, evntPro.getEstablecimientos().getId_establecimiento());
            this.ps.setInt(9, evntPro.getId_evento_progra());

            int filasAfectadas = this.ps.executeUpdate();

            // Verifica si la actualización fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Evento programado actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el evento programado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR UPDATE EVENT_PRO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos
            cerrarConexiones();
        }
    }

    public void eliminar(int idEventoProgramado) throws SQLException {
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(DELETE_EVE_PROGRA);
            this.ps.setInt(1, idEventoProgramado);
            int filasAfectadas = this.ps.executeUpdate();

            // Verifica si la eliminación fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Evento programado eliminado correctamente");
            } else {
                System.out.println("Error al eliminar el evento programado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DELETE EVENT_PRO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR AL ELIMINAR: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos
            cerrarConexiones();
        }
    }
//    //valida si ya existe un evento programado para el mismo evento, en la misma fecha y en el mismo rango de horas en el establecimiento correspondiente
//   public boolean existeEventoProgramado(String nombreEvento, Date fecha, Time horaInicio, Time horaFin, String nombreEstablecimiento) throws SQLException {
//    String sql = "SELECT COUNT(*) FROM evento_programado ep " +
//                 "INNER JOIN eventos e ON ep.id_evento = e.id_evento " +
//                 "INNER JOIN establecimiento es ON es.id_establecimiento = ep.id_establecimiento " +
//                 "WHERE e.n_evento = ? AND ep.fecha = ? AND ((ep.hora_inicia BETWEEN ? AND ?) OR (ep.hora_fin BETWEEN ? AND ?)) " +
//                 "AND es.n_establecimiento = ?";
//    /*
//    Esto garantiza que no se pueda agregar un nuevo evento programado que coincida en todos 
//    estos aspectos con uno existente. Si el método devuelve true, significa que ya existe un 
//    evento programado que satisface estas condiciones y, por lo tanto, no se debe permitir el 
//    registro del nuevo evento programado. En cambio, si devuelve false, indica que no hay 
//    conflictos y se puede proceder con el registro del nuevo evento programado.
//    */
//
//    try (Connection acceso = this.conexion.getConexion();
//         PreparedStatement preparedStatement = acceso.prepareStatement(sql)) {
//        preparedStatement.setString(1, nombreEvento);
//        preparedStatement.setDate(2, fecha);
//        preparedStatement.setTime(3, horaInicio);
//        preparedStatement.setTime(4, horaFin);
//        preparedStatement.setTime(5, horaInicio);
//        preparedStatement.setTime(6, horaFin);
//        preparedStatement.setString(7, nombreEstablecimiento);
//
//        try (ResultSet resultSet = preparedStatement.executeQuery()) {
//            if (resultSet.next()) {
//                int count = resultSet.getInt(1);
//                return count > 0;
//            }
//        }
//    }
//
//    return false;
//}
//

    /* public boolean existeEventoProgramado(String nombreEvento, Date fecha, Time horaInicio, Time horaFin, String nombreEstablecimiento) throws SQLException {
        String sql = "SELECT COUNT(*) FROM evento_programado ep "
                + "INNER JOIN eventos e ON ep.id_evento = e.id_evento "
                + "INNER JOIN establecimiento es ON es.id_establecimiento = ep.id_establecimiento "
                + "WHERE e.n_evento = ? AND ep.fecha = ? "
                + "AND ((? BETWEEN ep.hora_inicia AND ep.hora_fin) OR (? BETWEEN ep.hora_inicia AND ep.hora_fin) OR (ep.hora_inicia BETWEEN ? AND ?) OR (ep.hora_fin BETWEEN ? AND ?)) "
                + "AND es.n_establecimiento = ?";

        try ( Connection acceso = this.conexion.getConexion();  PreparedStatement preparedStatement = acceso.prepareStatement(sql)) {
            preparedStatement.setString(1, nombreEvento);
            preparedStatement.setDate(2, fecha);
            preparedStatement.setTime(3, horaInicio);
            preparedStatement.setTime(4, horaFin);
            preparedStatement.setTime(5, horaInicio);
            preparedStatement.setTime(6, horaFin);
            preparedStatement.setTime(7, horaInicio);
            preparedStatement.setTime(8, horaFin);
            preparedStatement.setString(9, nombreEstablecimiento);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0;
                }
            }
        }

        return false;
    }*/
    public boolean existeEventoProgramado(Date fecha, Time horaInicio, Time horaFin, String nombreEstablecimiento) throws SQLException {
        String sql = "SELECT COUNT(*) FROM evento_programado ep "
                + "INNER JOIN establecimiento es ON es.id_establecimiento = ep.id_establecimiento "
                + "WHERE ep.fecha = ? "
                + "AND ((? < ep.hora_fin AND ? > ep.hora_inicia) OR (? < ep.hora_fin AND ? > ep.hora_inicia)) "
                + "AND es.n_establecimiento = ?";

        try ( Connection acceso = this.conexion.getConexion();  PreparedStatement preparedStatement = acceso.prepareStatement(sql)) {
            preparedStatement.setDate(1, fecha);
            preparedStatement.setTime(2, horaInicio);
            preparedStatement.setTime(3, horaInicio);
            preparedStatement.setTime(4, horaFin);
            preparedStatement.setTime(5, horaFin);
            preparedStatement.setString(6, nombreEstablecimiento);

            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Devuelve true si encuentra un evento que se superpone
                }
            }
        }
        return false; // No se encontraron eventos que se superponen
    }

    public boolean existeEventoProgramado(Date fecha, Time horaInicio, Time horaFin, String nombreEstablecimiento, Integer idEventoExcluir) throws SQLException {
        String sql = "SELECT COUNT(*) FROM evento_programado ep "
                + "INNER JOIN establecimiento es ON es.id_establecimiento = ep.id_establecimiento "
                + "WHERE ep.fecha = ? "
                + "AND ((? < ep.hora_fin AND ? > ep.hora_inicia) OR (? < ep.hora_fin AND ? > ep.hora_inicia)) "
                + "AND es.n_establecimiento = ? "
                + "AND ep.id_evento_progra <> ?"; // Excluir el evento actual de la verificación

        try ( Connection acceso = this.conexion.getConexion();  PreparedStatement preparedStatement = acceso.prepareStatement(sql)) {
            preparedStatement.setDate(1, fecha);
            preparedStatement.setTime(2, horaInicio);
            preparedStatement.setTime(3, horaInicio);
            preparedStatement.setTime(4, horaFin);
            preparedStatement.setTime(5, horaFin);
            preparedStatement.setString(6, nombreEstablecimiento);
            preparedStatement.setInt(7, idEventoExcluir); // Asegúrate de que idEventoExcluir sea un Integer y esté inicializado


            try ( ResultSet resultSet = preparedStatement.executeQuery()) {
                if (resultSet.next()) {
                    int count = resultSet.getInt(1);
                    return count > 0; // Devuelve true si encuentra un evento que se superpone
                }
            }
        }
        return false; // No se encontraron eventos que se superponen
    }

    private void cerrarConexiones() {
        try {
            if (rs != null) {
                rs.close();
            }
            if (ps != null) {
                ps.close();
            }
            if (accesoDB != null) {
                accesoDB.close();
            }
        } catch (SQLException ex) {
            Logger.getLogger(EventoProgramadoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
