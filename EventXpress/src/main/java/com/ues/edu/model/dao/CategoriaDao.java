/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Boletos;
import com.ues.edu.entities.Categoria;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import com.ues.edu.entities.Zona_Establec;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

public class CategoriaDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private String sql;

    private static final String SELECT_ALL = "SELECT * FROM categorias";
    private static final String SELECT_INGRESOS_CATEGORIA = "SELECT c.categoria, SUM(ze.precio * b.cantidad_boletos) AS total_generado"
            + "FROM categorias c"
            + "INNER JOIN eventos e ON c.id_categoria = e.id_categoria"
            + "INNER JOIN evento_programado ep ON e.id_evento = ep.id_evento"
            + "INNER JOIN boletos b ON ep.id_evento_progra = b.id_evento_progra"
            + "INNER JOIN zona_establec ze ON b.id_zona_establec = ze.id_zona_establec"
            + "GROUP BY c.categoria;";

    public CategoriaDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Categoria> selectAll() throws SQLException {
        return select(SELECT_ALL);
    }

    public ArrayList<Categoria> select(String sql) throws SQLException {

        ArrayList<Categoria> listaCat = new ArrayList<>();
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(sql);
            this.rs = this.ps.executeQuery();

            while (rs.next()) {
                Categoria obj = new Categoria();
                obj.setIdCategoria(rs.getInt("id_categoria"));
                obj.setCategoria(rs.getString("categoria"));

                listaCat.add(obj);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }

        return listaCat;
    }

    // Método para obtener usuarios que han comprado boletos junto con el nombre del evento y la fecha de compra
    public List<Categoria> getIngresosCategoria() throws SQLException {
        List<Categoria> listaCategoria = new ArrayList<>();//lista para almacenar los objetos Boletos que se recuperan de la base de datos.
        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(SELECT_INGRESOS_CATEGORIA);//conexión a la BD y ejecuta la consulta SQL definida
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {

                Zona_Establec zonaE = new Zona_Establec();
                zonaE.setPrecio(rs.getDouble("precio"));

                Boletos boleto = new Boletos();
                boleto.setCantidad_boletos(rs.getInt("cantidad_boletos"));
                Eventos evento = new Eventos();

                ArrayList<Boletos> listaBoletos = new ArrayList<>();
                listaBoletos.add(boleto);

                boleto.setId_zona_establec(zonaE);

                Evento_Programado eProgramado = new Evento_Programado();
                eProgramado.setLsBoletos(listaBoletos);

                boleto.setId_evento_programado(eProgramado);

                Categoria categoria = new Categoria();
                categoria.setCategoria(rs.getString("categoria"));

                listaCategoria.add(categoria);

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            cerrarConexiones();
        }
        return listaCategoria;//retorno la lista llena para usarla en la jsp
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
