package com.ues.edu.model.dao;

/**
 *
 * @author A5155456HP
 */
import com.ues.edu.entities.Categoria;
import com.ues.edu.entities.Eventos;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class EventoDao {
//
//    private String jdbcURL = "jdbc:postgresql://localhost:5432/EventXpress?useSSL=false";
//    private String jdbcUsername = "postgres";
//    private String jdbcPassword = "root";
    
    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/EventXpress?useSSL=false&characterEncoding=UTF-8";
    private static final String jdbcUsername = "postgres";
    private static final String jdbcPassword = "root";

    private static final String SELECT_ALL = "SELECT ev.id_evento, ev.n_evento,ev.descripcion,cat.categoria from eventos ev INNER JOIN categorias cat on ev.id_categoria=cat.id_categoria order by ev.id_evento";
    private static final String INSERT_EVENTO = "INSERT into eventos(n_evento,descripcion,id_categoria) VALUES (?,?,?)";
    private static final String DELETE_EVENTO = "DELETE FROM eventos WHERE id_evento=?";
    private static final String UPDATE_EVENTO = "update eventos set n_evento = ?,descripcion=?,id_categoria=? where id_evento = ?";
    private static final String SELECT_EVENTO_POR_ID = "select id_evento,n_evento,descripcion,id_categoria from eventos where id_evento =?";

    public ArrayList<Eventos> selectAll() throws SQLException {
        return select(SELECT_ALL);
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            try {
                // Class.forName("com.mysql.jdbc.Driver");
                Class.forName("org.postgresql.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(EventoDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public ArrayList<Eventos> select(String sql) throws SQLException {
        ArrayList<Eventos> listaEvt = new ArrayList<>();
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            while (rs.next()) {
                Eventos evt = new Eventos();
                Categoria cat = new Categoria();
                evt.setIdEvento(rs.getInt("id_evento"));
                evt.setnEvento(rs.getString("n_evento"));
                evt.setDescripcion(rs.getString("descripcion"));
                cat.setCategoria(rs.getString("categoria"));
                evt.setCategoria(cat);
                listaEvt.add(evt);
            }

        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
        return listaEvt;
    }

    public void insertEvento(Eventos evt) throws SQLException {
        System.out.println(INSERT_EVENTO);
        // try-with-resource statement will auto close the connection.
        try ( Connection connection = getConnection();  PreparedStatement preparedStatement = connection.prepareStatement(INSERT_EVENTO)) {
            preparedStatement.setString(1, evt.getnEvento());
            preparedStatement.setString(2, evt.getDescripcion());
            preparedStatement.setInt(3, evt.getCategoria().getIdCategoria());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public ArrayList<Eventos> selectAllEventos() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        ArrayList<Eventos> listaEvt = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                Eventos evt = new Eventos();
                Categoria cat = new Categoria();
                evt.setIdEvento(rs.getInt("id_evento"));
                evt.setnEvento(rs.getString("n_evento"));
                evt.setDescripcion(rs.getString("descripcion"));
                cat.setCategoria(rs.getString("categoria"));
                evt.setCategoria(cat);
                listaEvt.add(evt);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return listaEvt;
    }

    public boolean deleteEvento(int id) throws SQLException {
        boolean rowDeleted;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(DELETE_EVENTO);) {
            statement.setInt(1, id);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    //set username = ?,email= ?, password =?, codigocargo =?, activo =? where id = ?
    public boolean updateEvento(Eventos evt) throws SQLException {
        boolean rowUpdated;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(UPDATE_EVENTO);) {
            statement.setString(1, evt.getnEvento());
            statement.setString(2, evt.getDescripcion());
            statement.setInt(3, evt.getCategoria().getIdCategoria());
            statement.setInt(4, evt.getIdEvento());

            rowUpdated = statement.executeUpdate() > 0;
        }
        return rowUpdated;
    }

    public Eventos selectEvento(int id) {
        Eventos evt = null;
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_EVENTO_POR_ID);) {
            preparedStatement.setInt(1, id);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                int id_evento = rs.getInt("id_evento");
                String n_evento = rs.getString("n_evento");
                String descripcion = rs.getString("descripcion");
                int id_categoria = rs.getInt("id_categoria");

                Categoria cat = new Categoria();
                cat.setIdCategoria(id_categoria);
                evt = new Eventos();
                evt.setIdEvento(id_evento);
                evt.setnEvento(n_evento);
                evt.setDescripcion(descripcion);
                evt.setCategoria(cat);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return evt;
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
