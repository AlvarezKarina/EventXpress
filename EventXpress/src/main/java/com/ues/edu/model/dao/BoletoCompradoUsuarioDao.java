package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Boletos;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import com.ues.edu.entities.Usuarios;
import com.ues.edu.entities.Zona_Establec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoletoCompradoUsuarioDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private ArrayList<Boletos> listaBoletosComprados;
    private PreparedStatement ps;
    private Connection accesoDB;

    private static final String SELECT_BOLETOS_COMPRADOS_POR_USUARIOS = "SELECT e.n_evento, ep.fecha, ze.precio FROM boletos b JOIN evento_programado ep ON b.id_evento_progra = ep.id_evento_progra JOIN zona_establec ze ON b.id_zona_establec = ze.id_zona_establec JOIN eventos e ON ep.id_evento = e.id_evento WHERE b.dui= ?";

    public BoletoCompradoUsuarioDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Boletos> getUsuariosConBoletosComprados(String dui) throws SQLException {
        this.listaBoletosComprados = new ArrayList<Boletos>();//lista para almacenar los objetos Boletos que se recuperan de la base de datos.
        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(SELECT_BOLETOS_COMPRADOS_POR_USUARIOS);//conexión a la BD y ejecuta la consulta SQL definida
            this.ps.setString(1, dui); // Establece el DUI como parámetro en la consulta
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs
            String duiAux = dui;
            while (rs.next()) {
                Boletos boleto = new Boletos();
                Evento_Programado evento_p = new Evento_Programado();
                Zona_Establec zona = new Zona_Establec();
                Eventos evento = new Eventos();
                Usuarios usuario = new Usuarios();

                evento.setnEvento(rs.getString("n_evento"));
                evento_p.setId_evento(evento);
                evento_p.setFecha(rs.getDate("fecha"));
                boleto.setId_evento_programado(evento_p);

                zona.setPrecio(rs.getDouble("precio"));
                boleto.setId_zona_establec(zona);

                usuario.setDui(duiAux);
                boleto.setDui(usuario);

                this.listaBoletosComprados.add(boleto);

            }
            cerrarConexiones();
            //return this.listaBoletosComprados;
        } catch (SQLException ex) {
            Logger.getLogger(BoletoCompradoUsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return listaBoletosComprados;//retorno la lista llena para usarla en la jsp
    }

    public ArrayList<Boletos> getHistorialComprasPorUsuario(String dui) throws SQLException {
        ArrayList<Boletos> boletosComprados = new ArrayList<>();
        String sql = "SELECT "
                + "    e.n_evento AS nombre_evento,  "
                + "    b.fecha_compra,  "
                + "    b.cantidad_boletos,  "
                + "    (z.precio * b.cantidad_boletos) AS precio_total "
                + "FROM  "
                + "    boletos b  "
                + "JOIN evento_programado ep ON b.id_evento_progra = ep.id_evento_progra  "
                + "JOIN eventos e ON ep.id_evento = e.id_evento  "
                + "JOIN zona_establec z ON b.id_zona_establec = z.id_zona_establec "
                + "WHERE  "
                + " b.dui = ?;"; // Tu consulta SQL aquí

        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(sql);//conexión a la BD y ejecuta la consulta SQL definida
            this.ps.setString(1, dui); // Establece el DUI como parámetro en la consulta
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs
            while (rs.next()) {
                Boletos boleto = new Boletos();
                Zona_Establec zona = new Zona_Establec();
                Evento_Programado evento_p = new Evento_Programado();
                Eventos evento = new Eventos();

                // Configuración de Zona_Establec y cálculo del precio total
                zona.setPrecio(rs.getDouble("precio"));
                boleto.setId_zona_establec(zona);
                boleto.setCantidad_boletos(rs.getInt("cantidad_boletos"));
                boleto.setPrecioTotal(rs.getDouble("precio_total"));
                

                // Configuración de Evento_Programado y Eventos
                evento.setnEvento(rs.getString("nombre_evento"));
                evento_p.setId_evento(evento);
                boleto.setId_evento_programado(evento_p);

                boleto.setFecha_compra(rs.getDate("fecha_compra"));

                Usuarios usuario = new Usuarios();
                usuario.setDui(dui);
                boleto.setDui(usuario);

                boletosComprados.add(boleto);
            }
        } catch (SQLException e) {
            // Manejo de excepciones
        } finally {
            cerrarConexiones();
        }
        return boletosComprados;
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
            Logger.getLogger(BoletoCompradoUsuarioDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public boolean registrar() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean modificar() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }

    public boolean eliminar() throws SQLException {
        // TODO Auto-generated method stub
        return false;
    }
}
