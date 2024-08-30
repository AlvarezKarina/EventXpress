/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Categoria;
import com.ues.edu.entities.Establecimiento;
import com.ues.edu.entities.Evento_Programado;
import com.ues.edu.entities.Eventos;
import com.ues.edu.entities.Zona_Establec;
import com.ues.edu.entities.Zonas;
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
public class Evento_ProDao {

    private Conexion conexion = null; // Para acceder al método de conexión de la base de datos
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private String sql;

    private static final String SELECT_EVENTOS_PROGRAMADOS = "SELECT e.n_evento, ep.fecha FROM eventos e INNER JOIN evento_programado ep ON e.id_evento = ep.id_evento WHERE ep.fecha = ?";
    private static final String SELECT_FECHAS_PROGRAMADAS = "SELECT fecha from evento_programado GROUP BY fecha";
    private static final String SELECT_EVENTOS_DISPONIBLES = "SELECT ep.id_evento_progra,ze.id_zona_establec,ev.n_evento,ev.descripcion,cate.categoria,ze.precio,ep.fecha,ep.hora_inicia,hora_fin,zn.nombre_zona, COALESCE(ze.capacidad_zona - SUM(bl.cantidad_boletos), ze.capacidad_zona) AS boletos_disponibles, et.n_establecimiento,et.direccion FROM zona_establec ze LEFT JOIN boletos bl on ze.id_zona_establec=bl.id_zona_establec INNER JOIN zonas zn on zn.id_zona=ze.id_zona INNER JOIN establecimiento et on et.id_establecimiento=ze.id_establecimiento INNER JOIN evento_programado ep on ep.id_establecimiento=et.id_establecimiento INNER JOIN eventos ev on ep.id_evento=ev.id_evento INNER JOIN categorias cate on cate.id_categoria=ev.id_categoria GROUP BY ep.fecha,et.n_establecimiento,ev.n_evento,ze.id_zona_establec,ze.capacidad_zona,zn.nombre_zona,ep.hora_inicia,ev.descripcion,ze.precio,hora_fin,ep.id_evento_progra,cate.categoria,et.direccion HAVING CURRENT_DATE<=ep.fecha ORDER BY ep.fecha ASC";
    private static final String SELECT_EVENTOS_SELECCIONADO = "SELECT ep.id_evento_progra,ze.id_zona_establec,ev.n_evento,ev.descripcion,cate.categoria,ze.precio,ep.fecha,ep.hora_inicia,hora_fin,zn.nombre_zona, COALESCE(ze.capacidad_zona - SUM(bl.cantidad_boletos), ze.capacidad_zona) AS boletos_disponibles, et.n_establecimiento,et.direccion FROM zona_establec ze LEFT JOIN boletos bl on ze.id_zona_establec=bl.id_zona_establec INNER JOIN zonas zn on zn.id_zona=ze.id_zona INNER JOIN establecimiento et on et.id_establecimiento=ze.id_establecimiento INNER JOIN evento_programado ep on ep.id_establecimiento=et.id_establecimiento INNER JOIN eventos ev on ep.id_evento=ev.id_evento INNER JOIN categorias cate on cate.id_categoria=ev.id_categoria GROUP BY ep.fecha,et.n_establecimiento,ev.n_evento,ze.id_zona_establec,ze.capacidad_zona,zn.nombre_zona,ep.hora_inicia,ev.descripcion,ze.precio,hora_fin,ep.id_evento_progra,cate.categoria,et.direccion HAVING CURRENT_DATE<=ep.fecha  and ze.id_zona_establec=?  and ep.id_evento_progra=? ORDER BY ep.fecha ASC";
    public Evento_ProDao() {
        this.conexion = new Conexion();
    }

    public ArrayList<Evento_Programado> getEventoProgramado(String fecha) throws SQLException {
        ArrayList<Evento_Programado> listaEventosPro = new ArrayList<>();

        Date fechaAux = Date.valueOf(fecha);
        try {
            this.accesoDB = conexion.getConexion(); // Conecta a la base de datos 
            this.ps = accesoDB.prepareStatement(SELECT_EVENTOS_PROGRAMADOS);//conexión a la BD y ejecuta la consulta SQL definida
            this.ps.setDate(1, fechaAux);
            this.rs = this.ps.executeQuery();// ejecuta la consulta SQL y almacena los resultados en rs

            while (rs.next()) {
                Evento_Programado evt = new Evento_Programado();
                evt.setFecha(rs.getDate("fecha"));
                /*evt.setHora_inicia(rs.getTime("hora_inicia"));
                evt.setHora_fin(rs.getTime("hora_fin"));
                evt.setSuspendido(rs.getBoolean("suspendido"));
                evt.setReprogramado(rs.getBoolean("reprogramado"));
                evt.setFecha_reprogramado(rs.getDate("fecha_reprogramado"));*/

                Eventos even = new Eventos();
                //even.setIdEvento(rs.getInt("id_evento"));
                even.setnEvento(rs.getString("n_evento"));

                evt.setId_evento(even);
                listaEventosPro.add(evt);
                /*boleto.setFecha_compra(rs.getDate("fecha_compra"));

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

                listaBoletos.add(boleto);*/

            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return listaEventosPro;//retorno la lista llena para usarla en la jsp

    }

    public ArrayList<Evento_Programado> getFechasProgramadas() throws SQLException {
        ArrayList<Evento_Programado> listaFechasPro = new ArrayList<>();
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_FECHAS_PROGRAMADAS);
            this.rs = ps.executeQuery();

            while (rs.next()) {
                Evento_Programado evt = new Evento_Programado();
                evt.setFecha(rs.getDate("fecha"));

                listaFechasPro.add(evt);
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return listaFechasPro;
    }

    public ArrayList<Evento_Programado> getEventosProximos() throws SQLException {
        ArrayList<Evento_Programado> listaEventos = new ArrayList<>();
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_EVENTOS_DISPONIBLES);
            this.rs = ps.executeQuery();

            while (rs.next()) {
                Evento_Programado ep = new Evento_Programado();
                Categoria cat = new Categoria();
                Eventos ev = new Eventos();
                Establecimiento et = new Establecimiento();
                Zonas zn = new Zonas();
                ArrayList<Zona_Establec> lsZe = new ArrayList<>();
                Zona_Establec ze = new Zona_Establec();

                cat.setCategoria(rs.getString("categoria"));

                ev.setCategoria(cat);
                ev.setnEvento(rs.getString("n_evento"));
                ev.setDescripcion(rs.getString("descripcion"));
                
                zn.setNombreZonas(rs.getString("nombre_zona"));
                
                ze.setIdZona(zn);
                ze.setIdZonaEstablecimiento(rs.getInt("id_zona_establec"));
                ze.setCapacidadZona(rs.getInt("boletos_disponibles"));
                ze.setPrecio(rs.getDouble("precio"));
                lsZe.add(ze);

                et.setN_establecimiento(rs.getString("n_establecimiento"));
                et.setDireccion(rs.getString("direccion"));
               
                et.setZonas(lsZe);
                
                ep.setEstablecimientos(et);
                ep.setId_evento(ev);
                ep.setId_evento_progra(rs.getInt("id_evento_progra"));
                ep.setFecha(rs.getDate("fecha"));
                ep.setHora_inicia(rs.getTime("hora_inicia"));
                ep.setHora_fin(rs.getTime("hora_fin"));

                listaEventos.add(ep);
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return listaEventos;
    }

    public Evento_Programado getEventoPorId(int idZe, int idEp) throws SQLException{
        Evento_Programado ep = new Evento_Programado();
        try {
            this.accesoDB = conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_EVENTOS_SELECCIONADO);
            this.ps.setInt(1,idZe);
            this.ps.setInt(2,idEp);
            this.rs = ps.executeQuery();

            while (rs.next()) {
                
                Categoria cat = new Categoria();
                Eventos ev = new Eventos();
                Establecimiento et = new Establecimiento();
                Zonas zn = new Zonas();
                ArrayList<Zona_Establec> lsZe = new ArrayList<>();
                Zona_Establec ze = new Zona_Establec();

                cat.setCategoria(rs.getString("categoria"));

                ev.setCategoria(cat);
                ev.setnEvento(rs.getString("n_evento"));
                ev.setDescripcion(rs.getString("descripcion"));
                
                zn.setNombreZonas(rs.getString("nombre_zona"));
                
                ze.setIdZona(zn);
                ze.setIdZonaEstablecimiento(rs.getInt("id_zona_establec"));
                ze.setCapacidadZona(rs.getInt("boletos_disponibles"));
                ze.setPrecio(rs.getDouble("precio"));
                lsZe.add(ze);

                et.setN_establecimiento(rs.getString("n_establecimiento"));
                et.setDireccion(rs.getString("direccion"));
                
                et.setZonas(lsZe);
                
                ep.setEstablecimientos(et);
                ep.setId_evento(ev);
                ep.setId_evento_progra(rs.getInt("id_evento_progra"));
                ep.setFecha(rs.getDate("fecha"));
                ep.setHora_inicia(rs.getTime("hora_inicia"));
                ep.setHora_fin(rs.getTime("hora_fin"));

                
                
            }
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return ep;
    }
}
