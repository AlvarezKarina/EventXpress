package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Boletos;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import com.ues.edu.entities.Usuarios;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class BoletoDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private String sql;

    private static final String SELECT_BOLETOS_COMPRADOS = "SELECT u.nombre_u, u.apellido_u, e.n_evento, b.fecha_compra "
            + "FROM boletos b "
            + "INNER JOIN usuarios u ON b.dui = u.dui "
            + "INNER JOIN evento_programado ep ON b.id_evento_progra = ep.id_evento_progra "
            + "INNER JOIN eventos e ON ep.id_evento = e.id_evento";
    private static final String INSERT_COMPRA="Insert into boletos(fecha_compra,cantidad_boletos,num_tarjeta,id_zona_establec,id_evento_progra,dui) VALUES(?,?,?,?,?,?)";
    public BoletoDao() {
        this.conexion = new Conexion();
    }

    // Método para obtener usuarios que han comprado boletos junto con el nombre del evento y la fecha de compra
    public List<Boletos> getUsuariosConBoletos() throws SQLException {
        List<Boletos> listaBoletos = new ArrayList<>();//lista para almacenar los objetos Boletos que se recuperan de la base de datos.
        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(SELECT_BOLETOS_COMPRADOS);//conexión a la BD y ejecuta la consulta SQL definida
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {
                Boletos boleto = new Boletos();
                boleto.setFecha_compra(rs.getDate("fecha_compra"));

                // Crea un usuario y un evento y establece la relación
                Usuarios usuario = new Usuarios();
                usuario.setNombre_u(rs.getString("nombre_u"));
                usuario.setApellido_u(rs.getString("apellido_u"));
                boleto.setDui(usuario);//se agrega el usuario al boleto

                Evento_Programado evento_p = new Evento_Programado();
                Eventos eventoDetalle = new Eventos();
                eventoDetalle.setnEvento(rs.getString("n_evento"));
                evento_p.setId_evento(eventoDetalle);//relacionamos con los objetos
                boleto.setId_evento_programado(evento_p);//relacionamos con los objetos

                listaBoletos.add(boleto);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return listaBoletos;//retorno la lista llena para usarla en la jsp
    }
    
    public boolean comprarBoletos(Boletos bol) throws SQLException{
        boolean bandera=false;
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(INSERT_COMPRA);
            this.ps.setDate(1,new java.sql.Date( bol.getFecha_compra().getTime()));
            this.ps.setInt(2, bol.getCantidad_boletos());
            //this.ps.setString(3, bol.getNum_tarjeta());
            this.ps.setString(3, bol.getNum_tarjeta());
            this.ps.setInt(4, bol.getId_zona_establec().getIdZonaEstablecimiento());
            this.ps.setInt(5, bol.getId_evento_programado().getId_evento_progra());
            this.ps.setString(6, bol.getDui().getDui());
            ps.executeUpdate();
            
            bandera=true;
            
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return bandera;
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
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
