/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.model.dao;

import com.ues.edu.entities.Establecimiento;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karie
 */
public class Dao_Establecimiento {
//    private String jdbcURL = "jdbc:postgresql://localhost:5432/EventXpress?useSSL=false";
//    private String jdbcUsername = "postgres";
//    private String jdbcPassword = "root";
    
    private String jdbcURL = "jdbc:postgresql://localhost:5432/EventXpress?useSSL=false&characterEncoding=UTF-8";
    private String jdbcUsername = "postgres";
    private String jdbcPassword = "root";

    private static final String INSERT_ESTABLECIMIENTOS = "INSERT INTO establecimiento (n_establecimiento, direccion, capacidad_establec, fecha_alta, activo) VALUES (?, ?, ?, ?, ?)";

    private static final String SELECT_ESTABLEC_BY_ID = "select id_establecimiento, n_establecimiento, direccion, capacidad_establec from establecimiento where id_establecimiento=?";
    private static final String SELECT_ALL_ESTABLEC = "select id_establecimiento, n_establecimiento, direccion, capacidad_establec, fecha_alta from establecimiento where activo=1";
    private static final String ESTADO_ALL_ESTABLEC = "select id_establecimiento, n_establecimiento, direccion, capacidad_establec, fecha_baja from establecimiento where activo=0";
    private static final String DELETE_ESTABLEC_SQL = "update establecimiento set activo=?, fecha_baja=?, fecha_alta=?  where id_establecimiento = ?";
    private static final String ALTA_ESTABLEC_SQL = "update establecimiento set activo=?, fecha_alta=?, fecha_baja=? where id_establecimiento = ?";
    private static final String UPDATE_ESTABLEC_SQL = "update establecimiento set n_establecimiento =?, direccion =?, capacidad_establec=? where id_establecimiento = ?";

    public Dao_Establecimiento() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            try {
                // Class.forName("com.mysql.jdbc.Driver");
                Class.forName("org.postgresql.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(Dao_Establecimiento.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(Dao_Establecimiento.class.getName()).log(Level.SEVERE, null, ex);
            }
            connection = DriverManager.getConnection(jdbcURL, jdbcUsername, jdbcPassword);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return connection;
    }

    public void insertEstablec(Establecimiento establec) throws SQLException {
        System.out.println(INSERT_ESTABLECIMIENTOS);
        int valor = 1;
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(todayDate);
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(INSERT_ESTABLECIMIENTOS)) {
            preparedStatement.setString(1, establec.getN_establecimiento());
            preparedStatement.setString(2, establec.getDireccion());
            preparedStatement.setInt(3, establec.getCapacidad_establec());
            preparedStatement.setString(4, fechaActual);
            preparedStatement.setInt(5, valor);
            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Establecimiento selectEstablec(int id) {
        Establecimiento establec = null;
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ESTABLEC_BY_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                //int id= rs.getInt("id_establecimiento");
                String name = rs.getString("n_establecimiento");
                String direccion = rs.getString("direccion");
                int capacidad = rs.getInt("capacidad_establec");
                establec = new Establecimiento(name, direccion, capacidad);

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return establec;
    }

    public ArrayList<Establecimiento> selectAllEstablec() {
        ArrayList<Establecimiento> establec = new ArrayList<>();

        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_ESTABLEC);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_establecimiento");
                String name = rs.getString("n_establecimiento");
                String direccion = rs.getString("direccion");
                String alta = rs.getString("fecha_alta");
                int capacidad = rs.getInt("capacidad_establec");

                establec.add(new Establecimiento(id, name, direccion, alta, capacidad));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return establec;

    }

    public ArrayList<Establecimiento> selectAllEstablec2() {
        ArrayList<Establecimiento> establec = new ArrayList<>();

        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(ESTADO_ALL_ESTABLEC);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id = rs.getInt("id_establecimiento");
                String name = rs.getString("n_establecimiento");
                String direccion = rs.getString("direccion");
                int capacidad = rs.getInt("capacidad_establec");
                String baja = rs.getString("fecha_baja");
                establec.add(new Establecimiento(id, name, direccion, capacidad, baja));

            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return establec;

    }

    public boolean deleteEstablec(int id) throws SQLException {
        boolean rowDeleted;
        int valor = 0;
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(todayDate);
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(DELETE_ESTABLEC_SQL);) {
            statement.setInt(1, valor);
            statement.setString(2, fechaActual);
            statement.setString(3, "");
            statement.setInt(4, id);

            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean altaEstablec(int id) throws SQLException {
        boolean rowDeleted;
        int valor = 1;
        Date todayDate = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
        String fechaActual = sdf.format(todayDate);
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(ALTA_ESTABLEC_SQL);) {
            statement.setInt(1, valor);
            statement.setString(2, fechaActual);
            statement.setString(3, "");
            statement.setInt(4, id);

            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    public boolean updateEstablec(Establecimiento establec) throws SQLException {
        boolean rowUpdated;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(UPDATE_ESTABLEC_SQL);) {
            statement.setString(1, establec.getN_establecimiento());
            statement.setString(2, establec.getDireccion());
            statement.setInt(3, establec.getCapacidad_establec());
            statement.setInt(4, establec.getId_establecimiento());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    private void printSQLException(SQLException ex) {
        for (Throwable e : ex) {
            if (e instanceof SQLException) {
                e.printStackTrace(System.err);
                System.err.println("SQLState: " + ((SQLException) e).getSQLState());
                System.err.println("Error Code: " + ((SQLException) e).getErrorCode());
                System.err.println("Message: " + e.getMessage());
                Throwable t = ex.getCause();
                while (t != null) {
                    System.out.println("Cause: " + t);
                    t = t.getCause();
                }
            }
        }
    }

}
