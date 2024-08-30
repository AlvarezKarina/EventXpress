package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.IngresoCategoria;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class IngresoCategoriaDao {

    private Conexion conexion = null;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private String sql;

    private static final String SELECT_INGRESOS_CATEGORIA = "SELECT c.categoria, SUM(ze.precio * b.cantidad_boletos) AS total_generado "
            + " FROM categorias c "
            + " INNER JOIN eventos e ON c.id_categoria = e.id_categoria "
            + " INNER JOIN evento_programado ep ON e.id_evento = ep.id_evento "
            + " INNER JOIN boletos b ON ep.id_evento_progra = b.id_evento_progra "
            + " INNER JOIN zona_establec ze ON b.id_zona_establec = ze.id_zona_establec "
            + " GROUP BY c.categoria ";

    public IngresoCategoriaDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<IngresoCategoria> getIngresosCategoria() throws SQLException {
        ArrayList<IngresoCategoria> listaCategoriaTotal = new ArrayList<>();

        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_INGRESOS_CATEGORIA);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {

                IngresoCategoria total = new IngresoCategoria();

                total.setCategoria(rs.getString("categoria").charAt(0));
                total.setTotalGenerado(rs.getFloat("total_generado"));

                listaCategoriaTotal.add(total);

            }
        } catch (SQLException ex) {
            Logger.getLogger(IngresoCategoriaDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return listaCategoriaTotal;
    }

}
