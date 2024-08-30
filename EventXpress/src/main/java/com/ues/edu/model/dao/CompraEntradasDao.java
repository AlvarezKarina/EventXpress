package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.CompraEntradas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CompraEntradasDao {

    private Conexion conexion = null;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private String sql;

    private static final String SELECT_COMPRA_ENTRADAS = "SELECT u.nombre_u, u.apellido_u, u.email, COUNT( b.id_evento_progra) AS eventosasistidos "
            + "FROM usuarios u "
            + "INNER JOIN boletos b ON u.dui = b.dui "
            + "GROUP BY u.nombre_u, u.apellido_u, u.email "
            + "HAVING COUNT( b.id_evento_progra) >1 ";

    public CompraEntradasDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<CompraEntradas> getCompraEntradas() throws SQLException {
        ArrayList<CompraEntradas> listaCompraMas = new ArrayList<>();//lista para almacenar los objetos Boletos que se recuperan de la base de datos.
        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(SELECT_COMPRA_ENTRADAS);//conexión a la BD y ejecuta la consulta SQL definida
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {
                CompraEntradas compra = new CompraEntradas();
                compra.setNombre_u(rs.getString("nombre_u"));
                compra.setApellido_u(rs.getString("apellido_u"));
                compra.setEmail(rs.getString("email"));
                compra.setEventosAsistidos(rs.getInt("eventosAsistidos"));

                listaCompraMas.add(compra);

            }
        } catch (SQLException ex) {
            Logger.getLogger(CompraEntradasDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return listaCompraMas;
    }

}
