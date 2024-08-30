package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.Usuarios;
import dorkbox.notify.DesktopNotify;
import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import java.util.ArrayList;



public class LoginDao extends Conexion {
    //CREAMOS UNA CLASE DE LA CONEXION
    Conexion conexion = null;
    private ArrayList<Usuarios> userList;
    // private ArrayList<Medico> medicoList;
    private ResultSet rs = null;
    private PreparedStatement pst = null;
    //Y UNA VARIABLE DE TIPO CONEXION DE SQL
    private Connection accesoDB;
    private String sql;
    private Usuarios usu = null;

    private static Connection connection = null;

    // private static final String SELECT_USER = "SELECT * FROM userstbl WHERE username=? and password=?";
    //private static final String SELECT_USER = "SELECT u.id, u.activo, c.nombrecargo, u.username, u.email, u.password FROM userstbl u INNER JOIN cargo c ON u.codigocargo = c.codigo WHERE u.activo = 't' AND u.username=? and u.password=?";
    private static final String SELECT_USER = "SELECT u.dui, u.nombre_u, u.apellido_u, u.email, u.contraseña, u.fecha_nac, u.genero_u, u.tipo_u FROM usuarios u WHERE u.nombre_u=? AND u.contraseña=?;";
    private static final String SELECT_ALL_USER = "SELECT * FROM userstbl LIMIT 100";

    public LoginDao() {
        this.conexion = new Conexion();
    }

    // Método para identificar el usuario si está activo y posee cargo (rol)
    //   public boolean validate(String name, String pass) throws SQLException {
    //VA A RECIBIR EL USUARIO QUE INGRESEMOS EN EL LOGIN
    public Usuarios identificar(Usuarios user) throws SQLException {
        boolean status = false;
        try {
            //VA A TOMAR EN UNA VARIABLE LOS DATOS DEL LOGIN

            String name = user.getNombre_u();
            String pass=user.getContraseña();

            // Estableciendo la conexion a la bd
            //dao = new LoginDao();
            //OBTENEMOS LA CONEXION DEL OBJETO CONEXION Y LA AGREGAMOS A LA VARIABLE DE TIPO CONEXION DE SQL
            this.accesoDB = this.conexion.getConexion(); // Conectando a la bd
            this.pst = accesoDB.prepareStatement(SELECT_USER);
            //COMO EN LA QUERY DEJAMOS DOS ?,? POR MEDIO DE LOS setString LE INDICAREMOS A QUE SIGNO
            //SI ES 1 AL PRIMER ? LO SUSTITUIREMOS POR name
            //Y AL 2 LO SUSTITUIREMOS POR pass
            pst.setString(1, name);
            pst.setString(2, pass);
            //EJECUTAMOS LA QUERY Y GUARDAMOS EL RESULTADO EN rs
            this.rs = this.pst.executeQuery();
            //Y YA PODEMOS RECORRER LOS OBJETOS QUE NOS DEVOLVIO LA CONSULTA
            while (this.rs.next()) {
                status = true; // encontrado    

                this.usu = new Usuarios();

                //EN CADA OBJETO EXTRAEMOS SU RESPECTIVA INFORMACION
                //this.rs.getInt("id); QUIERE DECIR QUE DEL RESULTADO EXTRAEREMOS EL OBJETO QUE ESTE EN LA TABLA id
                this.usu.setDui(this.rs.getString("dui"));
                this.usu.setNombre_u(this.rs.getString("nombre_u"));
                this.usu.setApellido_u(this.rs.getString("apellido_u"));
                this.usu.setEmail(this.rs.getString("email"));
                this.usu.setContraseña(this.rs.getString("contraseña"));
                this.usu.setFecha_nac(this.rs.getDate("fecha_nac"));
                this.usu.setGenero_u(this.rs.getString("genero_u").charAt(0));
                this.usu.setTipo_u(this.rs.getBoolean("tipo_u"));
            }
            this.cerrarConexiones();
 
         } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarConexiones();
  
        }
   //   if (this.usu!=null){
   //DESPUES DE CERRAR TODAS LAS CONEXIONES SIMPLEMENTE RETORNAMOS EL OBJETO USUARIO
        return this.usu;
    //  }else{
     // status=false;
     // }
    //  return this.usu;
    }

    public ArrayList<Usuarios> getAllUsers() {
        this.userList = new ArrayList<Usuarios>();
        try {
            // Estableciendo la conexion a la bd
            this.accesoDB = this.conexion.getConexion(); // Conectando a la bd
            this.pst = accesoDB.prepareStatement(SELECT_ALL_USER);
            this.rs = this.pst.executeQuery();
            while (this.rs.next()) {
                Usuarios user = new Usuarios();
                user.setDui(rs.getString("dui"));
                user.setNombre_u(rs.getString("nombre_u"));
                user.setApellido_u(rs.getString("apellido_u"));
                user.setEmail(rs.getString("email"));
                user.setContraseña(rs.getString("contraseña"));
                user.setFecha_nac(rs.getDate("fecha_nac"));
                //EXTRAEMOS COMO UNA CADENA PERO SOLO EL PRIMER VALOR PARA QUE SE CONVIERTA EN CHAR
                user.setGenero_u(rs.getString("genero_u").charAt(0));
                user.setTipo_u(rs.getBoolean("tipo_u"));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            this.cerrarConexiones();
        }
        return this.userList;
    }
}
