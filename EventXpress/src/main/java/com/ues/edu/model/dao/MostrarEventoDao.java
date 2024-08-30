package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Categoria;
import com.ues.edu.entities.Eventos;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author alexa
 */
public class MostrarEventoDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;

    private static final String SELECT_EVENTOS_BY_CATEGORIA = "SELECT * FROM eventos WHERE id_categoria = ?";

    public MostrarEventoDao() {
        this.conexion = new Conexion();

    }

    public ArrayList<Eventos> getEventosCategoria(int id) throws SQLException {

        ArrayList<Eventos> listaEventoCategoria = new ArrayList<>();//lista para almacenar los objetos Boletos que se recuperan de la base de datos.

        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 

            this.ps = accesoDB.prepareStatement(SELECT_EVENTOS_BY_CATEGORIA);//conexión a la BD y ejecuta la consulta SQL definida
            this.ps.setInt(1, id);//el indice en el uno y el segundo lo que recibe por parametro
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {

                Eventos evento = new Eventos();
                Categoria categoria = new Categoria();
                evento.setIdEvento(rs.getInt("id_evento"));
                evento.setnEvento(rs.getString("n_evento"));
                evento.setDescripcion(rs.getString("descripcion"));
                categoria.setIdCategoria(rs.getInt("id_categoria"));
                evento.setCategoria(categoria);

                listaEventoCategoria.add(evento);

            }
        } catch (SQLException ex) {
            Logger.getLogger(MostrarEventoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return listaEventoCategoria;//retorno la lista llena para usarla en la jsp
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
            Logger.getLogger(MostrarEventoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
