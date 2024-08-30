/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Boletos;
import com.ues.edu.entities.Establecimiento;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import com.ues.edu.entities.ReporteBoletosEstablec;
import com.ues.edu.entities.Usuarios;
import com.ues.edu.entities.Zona_Establec;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author herna
 */
public class BoletosEstablecimientoDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private String sql;

    private static final String SELECT_BOLETOS_ESTABLECIMIENTO = "SELECT u.nombre_u, u.apellido_u, SUM(b.cantidad_boletos) AS cantidad_boletos FROM boletos b JOIN usuarios u ON b.dui = u.dui JOIN evento_programado ep ON b.id_evento_progra = ep.id_evento_progra JOIN zona_establec ze ON b.id_zona_establec=ze.id_zona_establec JOIN establecimiento est ON ze.id_establecimiento = est.id_establecimiento WHERE est.id_establecimiento = ? GROUP BY u.nombre_u, u.apellido_u";
    private static final String SELECT_ESTABLECIMIENTOS = "SELECT id_establecimiento, n_establecimiento FROM establecimiento;";

    public BoletosEstablecimientoDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<ReporteBoletosEstablec> getBoletosComprados(String establecimientoSelec) throws SQLException {
        ArrayList<ReporteBoletosEstablec> listaReporte = new ArrayList<>();

        int id_Esta_Selec = Integer.parseInt(establecimientoSelec);
        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(SELECT_BOLETOS_ESTABLECIMIENTO);//conexión a la BD y ejecuta la consulta SQL definida
            this.ps.setInt(1, id_Esta_Selec);
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {

                ReporteBoletosEstablec reporte = new ReporteBoletosEstablec();

                reporte.setNombre(rs.getString("nombre_u"));
                reporte.setApellido(rs.getString("apellido_u"));
                reporte.setCantidad(rs.getInt("cantidad_boletos"));

                listaReporte.add(reporte);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return listaReporte;//retorno la lista llena para usarla en la jsp

    }

    public ArrayList<Establecimiento> getEstablecimientos() throws SQLException {
        ArrayList<Establecimiento> listaEstablecimientos = new ArrayList<>();
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_ESTABLECIMIENTOS);
            this.rs = ps.executeQuery();

            while (rs.next()) {
                Establecimiento est = new Establecimiento();
                est.setId_establecimiento(rs.getInt("id_establecimiento"));
                est.setN_establecimiento(rs.getString("n_establecimiento"));
                listaEstablecimientos.add(est);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return listaEstablecimientos;
    }

}
