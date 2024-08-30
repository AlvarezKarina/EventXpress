package com.ues.edu.conexion;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class Conexion {

    private Connection conexion = null;
    private static final ResultSet rs = null;
    private static Statement sentencia = null;
    private static final PreparedStatement ps = null;

    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/EventXpress?useSSL=false&characterEncoding=UTF-8";   
    private static final String jdbcUsername = "postgres";
    private static final String jdbcPassword = "root";

    public Connection getConexion() {
        Connection con = null;
        try {
            Class.forName("org.postgresql.Driver");
            // Obtener la conexion
            con = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
            System.out.println("conectando a la DB");
        } catch (SQLException ex) {
        } catch (Exception e) {
        }
        return con;
    }

    public void cerrarConexiones() {
        if (sentencia != null) {
            try {
                sentencia.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar el Statement" + e);
            }
        }
        if (conexion != null) {
            try {
                conexion.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion a la bd" + e);
            }
        }
        if (rs != null) {
            try {
                rs.close();
            } catch (SQLException e) {
                System.out.println("Error al cerrar la conexion a la bd" + e);
            }
        }
    }
}
