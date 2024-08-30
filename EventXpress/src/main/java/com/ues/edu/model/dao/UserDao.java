package com.ues.edu.model.dao;

import com.ues.edu.entities.Usuarios;
import com.ues.edu.utilidades.Encriptar;
import java.sql.Connection;
import java.sql.Date;
import java.sql.DriverManager;
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
public class UserDao {

//    private String jdbcURL = "jdbc:postgresql://localhost:5432/EventXpress?useSSL=false";
//    private String jdbcUsername = "postgres";
//    private String jdbcPassword = "root";
    private static final String jdbcURL = "jdbc:postgresql://localhost:5432/EventXpress?useSSL=false&characterEncoding=UTF-8";
    private static final String jdbcUsername = "postgres";
    private static final String jdbcPassword = "root";

    private static final String INSERT_USERS_SQL = "INSERT INTO usuarios" + "  (dui,nombre_u,apellido_u, email, contraseña, fecha_nac, genero_u,tipo_u) VALUES "
            + " (?, ?, ?, ?, ?, ?, ?, ?)";

    private static final String SELECT_USER_BY_DUI = "select dui,nombre_u,apellido_u,email,contraseña from usuarios where dui =?";
    private static final String SELECT_ALL_USERS = "select * from usuarios order by nombre_u asc";

    private static final String DELETE_USERS_SQL = "delete from usuarios where dui= ?";
    private static final String UPDATE_USERS_SQL = "update usuarios set nombre_u = ?,apellido_u = ?,email = ?, contraseña =?, fecha_nac =?, genero_u=?, tipo_u =? where dui= ?";

    public UserDao() {
    }

    protected Connection getConnection() {
        Connection connection = null;
        try {
            try {
                // Class.forName("com.mysql.jdbc.Driver");
                Class.forName("org.postgresql.Driver").newInstance();
            } catch (InstantiationException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
            } catch (IllegalAccessException ex) {
                Logger.getLogger(UserDao.class.getName()).log(Level.SEVERE, null, ex);
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

    public void insertUser(Usuarios user) throws SQLException {
        System.out.println(INSERT_USERS_SQL);
        // try-with-resource statement will auto close the connection.
        try ( Connection connection = getConnection();  
            PreparedStatement preparedStatement = connection.prepareStatement(INSERT_USERS_SQL)) {
            preparedStatement.setString(1, user.getDui());
            preparedStatement.setString(2, user.getNombre_u());
            preparedStatement.setString(3, user.getApellido_u());
            preparedStatement.setString(4, user.getEmail());
            preparedStatement.setString(5, user.getContraseña());
            preparedStatement.setDate(6, (Date) user.getFecha_nac());
            preparedStatement.setString(7, String.valueOf(user.getGenero_u()));
            preparedStatement.setBoolean(8, user.isTipo_u());

            System.out.println(preparedStatement);
            preparedStatement.executeUpdate();
        } catch (SQLException e) {
            printSQLException(e);
        }
    }

    public Usuarios selectUser(String dui) {
        Usuarios user = null;
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_USER_BY_DUI);) {
            preparedStatement.setString(1, dui);
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {
                String nombre = rs.getString("Nombre_u");
                String email = rs.getString("email");
                String pass = rs.getString("Contraseña");
                user = new Usuarios(dui, nombre, email, pass);
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return user;
    }

    public List<Usuarios> selectAllUsers() {

        // using try-with-resources to avoid closing resources (boiler plate code)
        List<Usuarios> users = new ArrayList<>();
        // Step 1: Establishing a Connection
        try ( Connection connection = getConnection(); // Step 2:Create a statement using connection object
                  PreparedStatement preparedStatement = connection.prepareStatement(SELECT_ALL_USERS);) {
            System.out.println(preparedStatement);
            // Step 3: Execute the query or update query
            ResultSet rs = preparedStatement.executeQuery();

            // Step 4: Process the ResultSet object.
            while (rs.next()) {

                String dui = rs.getString("dui");

                String userName = rs.getString("nombre_u");
                String apellido = rs.getString("apellido_u");
                String email = rs.getString("email");
                String pass = rs.getString("contraseña");
                Date fecha = rs.getDate("fecha_nac");
                String genero = rs.getString("genero_u");
                char gene = genero.charAt(0);
                boolean tipo = rs.getBoolean("tipo_u");

                Usuarios usu = new Usuarios();
                usu.getDui();
                usu.getNombre_u();
                usu.getApellido_u();
                usu.getEmail();
                usu.getContraseña();
                usu.getFecha_nac();
                usu.getGenero_u();
                usu.isTipo_u();

                users.add(new Usuarios(dui, userName, apellido, email, pass, fecha, gene, tipo));
            }
        } catch (SQLException e) {
            printSQLException(e);
        }
        return users;
    }

    public boolean deleteUser(String dui) throws SQLException {
        boolean rowDeleted;
        try ( Connection connection = getConnection();  PreparedStatement statement = connection.prepareStatement(DELETE_USERS_SQL);) {
            statement.setString(1, dui);
            rowDeleted = statement.executeUpdate() > 0;
        }
        return rowDeleted;
    }

    //set username = ?,email= ?, password =?, codigocargo =?, activo =? where id = ?
    public boolean updateUser(Usuarios user) throws SQLException {
        boolean rowUpdated;
        try ( Connection connection = getConnection();  
                PreparedStatement statement = connection.prepareStatement(UPDATE_USERS_SQL);) {

            statement.setString(1, user.getNombre_u());
            statement.setString(2, user.getApellido_u());
            statement.setString(3, user.getEmail());
            statement.setString(4, user.getContraseña());
            statement.setDate(5, (Date) user.getFecha_nac());
            statement.setString(6, String.valueOf(user.getGenero_u()));
            statement.setBoolean(7, user.isTipo_u());
            statement.setString(8, user.getDui());

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

    ///////////////recuperacion de contrasenia ////////////////////
    public String verificarUsuario(String dui, String email) {
        final String SQL_VERIFICAR_USUARIO = "SELECT dui FROM usuarios WHERE dui = ? AND email = ?";
        String duiUsuario = null;

        try ( Connection connection = getConnection();  
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_VERIFICAR_USUARIO)) {
            preparedStatement.setString(1, dui);
            preparedStatement.setString(2, email);

            ResultSet rs = preparedStatement.executeQuery();

            if (rs.next()) { // Si encuentra el usuario
                duiUsuario = rs.getString("dui"); //  identificador a retornar
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return duiUsuario; // Retorna el dui o null si no se encontró
    }

    public boolean updateContra(String dui, String nuevaContrasena) {
        // Asegúrate de cambiar 'contraseña' al nombre real de tu columna de contraseña en la base de datos
        final String SQL_ACTUALIZAR_CONTRASENA = "UPDATE usuarios SET contraseña = ? WHERE dui = ?";
        boolean actualizada = false;

        try ( Connection connection = getConnection();  
                PreparedStatement preparedStatement = connection.prepareStatement(SQL_ACTUALIZAR_CONTRASENA)) {
            // Asegúrate de encriptar la contraseña antes de guardarla
            String contrasenaEncriptada = Encriptar.getStringMessageDigest(nuevaContrasena, Encriptar.SHA256);

            preparedStatement.setString(1, contrasenaEncriptada); // Establece la nueva contraseña encriptada
            preparedStatement.setString(2, dui); // Establece el dui para identificar el usuario

            int filasAfectadas = preparedStatement.executeUpdate();
            if (filasAfectadas > 0) {
                actualizada = true; // Si se actualizó al menos una fila, entonces la contraseña fue actualizada
            }
        } catch (SQLException e) {
            printSQLException(e);
        }

        return actualizada; // Retorna true si la contraseña fue actualizada, false en caso contrario
    }

    public boolean verificacionEmail(String correo) throws SQLException {
        boolean existe = false;

        String sql = "SELECT email FROM usuarios WHERE email=?";

        try ( Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, correo);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Si hay al menos una fila con el correo proporcionado, establecer existe como verdadero
                existe = true;
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return existe;
    }

    public boolean verificacionDui(String dui) throws SQLException {
        boolean existe = false;

        String sql = "SELECT dui FROM usuarios WHERE dui=?";

        try ( Connection connection = getConnection(); 
                PreparedStatement preparedStatement = connection.prepareStatement(sql)) {
            preparedStatement.setString(1, dui);
            ResultSet rs = preparedStatement.executeQuery();
            while (rs.next()) {
                // Si hay al menos una fila con el DUI proporcionado, establecer existe como verdadero
                existe = true;
            }
        } catch (SQLException ex) {
            printSQLException(ex);
        }
        return existe;
    }

}
