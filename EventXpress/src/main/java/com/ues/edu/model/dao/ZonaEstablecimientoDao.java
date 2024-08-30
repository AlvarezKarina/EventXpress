package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Establecimiento;
import com.ues.edu.entities.Zona_Establec;
import com.ues.edu.entities.Zonas;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

/**
 *
 * @author nelki
 */
public class ZonaEstablecimientoDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;

    private static final String INSERT_ZONA_ESTABLEC = "INSERT INTO zona_establec (precio, capacidad_zona, id_zona, id_establecimiento)VALUES ( ?, ?, ?, ?)";
    private static final String UPDATE_ZONA_ESTABLEC = "UPDATE zona_establec SET precio=?, capacidad_zona=?, id_zona=?, id_establecimiento=? WHERE id_zona_establec=?";
    private static final String DELETE_ZONA_ESTABLEC = " DELETE FROM zona_establec WHERE id_zona_establec = ?";
    private static final String SELECT_ZONA_ESTABLEC_BY_ID = " SELECT * FROM zona_establec WHERE id_zona_establec = ?";
    private static final String SELECT_ALL = " SELECT ze.id_zona_establec, ze.precio, ze.capacidad_zona, z.nombre_zona, es.n_establecimiento"
            + " FROM zona_establec ze"
            + " INNER JOIN zonas z ON ze.id_zona = z.id_zona "
            + " INNER JOIN establecimiento es ON ze.id_establecimiento = es.id_establecimiento";

    public ZonaEstablecimientoDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Zona_Establec> obtenerTodos() throws SQLException {
        ArrayList<Zona_Establec> listaZonasEstablec = new ArrayList<>();

        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_ALL);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                // Construye objetos Evento_Programado a partir de los resultados de la consulta
                Zona_Establec zonaE = new Zona_Establec();
                zonaE.setIdZonaEstablecimiento(rs.getInt("id_zona_establec"));
                zonaE.setPrecio(rs.getDouble("precio"));
                zonaE.setCapacidadZona(rs.getInt("capacidad_zona"));

                Zonas zona = new Zonas();

                zona.setNombreZonas(rs.getString("nombre_zona"));
                zonaE.setIdZona(zona);

                Establecimiento estable = new Establecimiento();
                estable.setN_establecimiento(rs.getString("n_establecimiento"));
                zonaE.setIdEstablecimiento(estable);

                listaZonasEstablec.add(zonaE);
            }

        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SELECT ALL ZONA_ESTABLEC: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR ALL MOSTRAR LISTA: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recurso
            this.conexion.cerrarConexiones();
        }

        return listaZonasEstablec;
    }

    public int obtenerIdZona(String nombreZona) throws SQLException {
        String sql = "SELECT id_zona FROM zonas WHERE nombre_zona = ?";
        int id = -1; // Inicializado con un valor por defecto, puedes cambiarlo según tus necesidades

        try {
            accesoDB = conexion.getConexion();
            ps = accesoDB.prepareStatement(sql);
            ps.setString(1, nombreZona);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_zona");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trata el error según tus necesidades
        } finally {
            this.conexion.cerrarConexiones();
        }

        return id;
    }

    public ArrayList<String> obtenerNombresZona() throws SQLException {
        String sql = "SELECT nombre_zona FROM zonas";
        ArrayList<String> nombresZonasE = new ArrayList<>();

        try {
            accesoDB = conexion.getConexion();
            ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();
            while (rs.next()) {
                String nombreZona = rs.getString("nombre_zona");
                nombresZonasE.add(nombreZona);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trata el error según tus necesidades
        } finally {
            this.conexion.cerrarConexiones();
        }

        return nombresZonasE;
    }

    public int obtenerIdEstablecimiento(String nombreEstablec) throws SQLException {
        String sql = "SELECT id_establecimiento FROM establecimiento WHERE n_establecimiento = ?";
        int id = -1; // Inicializado con un valor por defecto, puedes cambiarlo según tus necesidades

        try {
            accesoDB = conexion.getConexion();
            ps = accesoDB.prepareStatement(sql);
            ps.setString(1, nombreEstablec);
            rs = ps.executeQuery();

            if (rs.next()) {
                id = rs.getInt("id_establecimiento");
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trata el error según tus necesidades
        } finally {
            this.conexion.cerrarConexiones();
        }

        return id;
    }

    public ArrayList<String> obtenerNombresEstablec() throws SQLException {
        String sql = "SELECT n_establecimiento FROM establecimiento";
        ArrayList<String> nombresEstablec = new ArrayList<>();

        try {
            accesoDB = conexion.getConexion();
            ps = accesoDB.prepareStatement(sql);
            rs = ps.executeQuery();

            while (rs.next()) {
                String nombreEstablec = rs.getString("n_establecimiento");
                nombresEstablec.add(nombreEstablec);
            }
        } catch (SQLException e) {
            e.printStackTrace();
            // Trata el error según tus necesidades
        } finally {
            this.conexion.cerrarConexiones();
        }
        return nombresEstablec;
    }

    public void registrar(Zona_Establec ZonaEs) throws SQLException {
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(INSERT_ZONA_ESTABLEC);

            // Establece los parámetros en la consulta
            this.ps.setDouble(1, ZonaEs.getPrecio());
            this.ps.setInt(2, ZonaEs.getCapacidadZona());
            this.ps.setInt(3, ZonaEs.getIdZona().getIdZonas());
            this.ps.setInt(4, ZonaEs.getIdEstablecimiento().getId_establecimiento());

            // Ejecuta la consulta SQL para insertar el evento programado
            int filasAfectadas = this.ps.executeUpdate();

            // Verifica si la inserción fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Evento programado registrado correctamente");
            } else {
                System.out.println("Error al registrar el evento programado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR INSERT ZONA ESTABLECIMIENTO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos
            if (this.ps != null) {
                this.ps.close();
            }
            this.conexion.cerrarConexiones();
        }
    }

    public void actualizar(Zona_Establec zonaEs) throws SQLException {
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(UPDATE_ZONA_ESTABLEC);

            this.ps.setDouble(1, zonaEs.getPrecio());
            this.ps.setInt(2, zonaEs.getCapacidadZona());
            this.ps.setInt(3, zonaEs.getIdZona().getIdZonas());
            this.ps.setInt(4, zonaEs.getIdEstablecimiento().getId_establecimiento());
            this.ps.setInt(5, zonaEs.getIdZonaEstablecimiento());

            int filasAfectadas = this.ps.executeUpdate();

            // Verifica si la actualización fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Evento programado actualizado correctamente");
            } else {
                System.out.println("Error al actualizar el evento programado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SELECT ALL EVENT_PRO: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "ERROR ALL MOSTRAR LISTA: " + ex.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos
            this.conexion.cerrarConexiones();
        }

    }

    public void eliminar(int idZonaEstablecimiento) throws SQLException {
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(DELETE_ZONA_ESTABLEC);
            this.ps.setInt(1, idZonaEstablecimiento);
            int filasAfectadas = this.ps.executeUpdate();

            // Verifica si la eliminación fue exitosa
            if (filasAfectadas > 0) {
                System.out.println("Evento programado eliminado correctamente");
            } else {
                System.out.println("Error al eliminar el evento programado");
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR DELETE ZONA_ESTABLEC: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos

            this.conexion.cerrarConexiones();
        }
    }

    public ArrayList<Zona_Establec> obtenerEventoProgramadoPorId(int idZonaEstablecimiento) throws SQLException {
        ArrayList<Zona_Establec> listaZonasEstablec = new ArrayList<>();
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_ZONA_ESTABLEC_BY_ID);
            this.ps.setInt(1, idZonaEstablecimiento);

            if (rs.next()) {
                // Construir objeto Evento_Programado a partir de los resultados de la consulta
                Zona_Establec zonaE = new Zona_Establec();
                zonaE.setIdZonaEstablecimiento(rs.getInt("id_zona_establec"));
                zonaE.setPrecio(rs.getDouble("precio"));
                zonaE.setCapacidadZona(rs.getInt("capacidad_zona"));

                Zonas zona = new Zonas();
                zona.setIdZonas(rs.getInt("id_zona"));
                zonaE.setIdZona(zona);

                Establecimiento estable = new Establecimiento();
                estable.setId_establecimiento(rs.getInt("id_establecimiento"));
                zonaE.setIdEstablecimiento(estable);

                listaZonasEstablec.add(zonaE);
            }
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(null, "ERROR SELECT ZONA_ESTABLEC BY ID: " + e.getMessage(), "ERROR", JOptionPane.ERROR_MESSAGE);
        } finally {
            // Cierra las conexiones y recursos
            if (this.ps != null) {
                this.ps.close();
            }
            this.conexion.cerrarConexiones();
        }
        return listaZonasEstablec;
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
