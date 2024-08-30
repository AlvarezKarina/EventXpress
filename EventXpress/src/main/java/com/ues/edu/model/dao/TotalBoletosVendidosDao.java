package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Boletos;
import com.ues.edu.entities.TotalBoletosVendidos;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class TotalBoletosVendidosDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;

    private static final String SELECT_EVENTOS_VENDIDOS = "SELECT e.n_evento, SUM(b.cantidad_boletos) AS total_boletos_vendidos"
            + " FROM evento_programado ep"
            + " JOIN eventos e ON ep.id_evento = e.id_evento"
            + " JOIN boletos b ON ep.id_evento_progra = b.id_evento_progra"
            + " GROUP BY e.n_evento"
            + " ORDER BY total_boletos_vendidos DESC";

    public TotalBoletosVendidosDao() {
        this.conexion = new Conexion();
    }

    public List<TotalBoletosVendidos> getEventosVendidosDes() throws SQLException {
        List<TotalBoletosVendidos> listaEventoProgramado = new ArrayList<>();//lista para almacenar los objetos Boletos que se recuperan de la base de datos.
        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(SELECT_EVENTOS_VENDIDOS);//conexión a la BD y ejecuta la consulta SQL definida
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {

                TotalBoletosVendidos boleto = new TotalBoletosVendidos();
                boleto.setN_evento(rs.getString("n_evento"));
                boleto.setTotalBoletosVendidos(rs.getInt("total_boletos_vendidos"));//

                listaEventoProgramado.add(boleto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(TotalBoletosVendidosDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return listaEventoProgramado;//retorno la lista llena para usarla en la jsp
    }

    // Método para cerrar conexiones (ResultSet, PreparedStatement, Connection)
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
            Logger.getLogger(TotalBoletosVendidosDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
