package com.ues.edu.model.dao;

import com.ues.edu.conexion.Conexion;
import com.ues.edu.entities.TotalZonas;
import com.ues.edu.entities.ZonaAux;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author karie
 */
public class Zona_Establec_Dao extends Conexion {

    //Establecimiento establecimiento;
    ZonaAux zona;
    Conexion conexion = null;
    private ArrayList<TotalZonas> establecimientoList;
    private ResultSet rs = null;
    private PreparedStatement ps;
    private Connection accesoDB;
    private String sql;

    private static final String SELECT_ALL_ESTABLECIMIENTO = " SELECT est.n_establecimiento, z.nombre_zona, ze.capacidad_zona"
            + " FROM establecimiento est"
            + " JOIN zona_establec ze ON est.id_establecimiento = ze.id_establecimiento"
            + " JOIN zonas z ON ze.id_zona = z.id_zona"
            + " GROUP BY est.n_establecimiento, z.nombre_zona,ze.capacidad_zona";

    public Zona_Establec_Dao() {
        this.conexion = new Conexion();
    }

    public ArrayList<TotalZonas> getListZona_Establec() throws SQLException {
        this.establecimientoList = new ArrayList();
        try {
            this.accesoDB = this.conexion.getConexion();
            this.ps = accesoDB.prepareStatement(SELECT_ALL_ESTABLECIMIENTO);
            this.rs = this.ps.executeQuery();
            while (this.rs.next()) {
                TotalZonas total = new TotalZonas();

                total.setnEstablecimiento(rs.getString("n_establecimiento"));
                total.setnZona(rs.getString("nombre_zona"));
                total.setTotalZona(rs.getInt("capacidad_zona"));
                establecimientoList.add(total);
//            this.accesoDB = this.conexion.getConexion();
//            this.ps = accesoDB.prepareStatement(SELECT_ALL_ESTABLECIMIENTO);
//            this.rs = this.ps.executeQuery();
//            while (this.rs.next()){
                //establecimiento=new EstablecimientoAux();
//               zona =new ZonaAux();
//               establecimiento.setN_establecimiento(rs.getString("n_establecimiento"));
//               establecimiento.setCapacidad_establec(0);

            }

//                    Establecimiento establecimiento = new Establecimiento();
//                    Zonas zonas = new Zonas();
//                    Zona_Establec zona_estable = new Zona_Establec();
//                    establecimiento.setN_establecimiento(rs.getString("n_establecimiento"));
//                    zonas.setNombreZonas(rs.getString("nombre_zona"));
//                    zona_estable.setCapacidadZona(rs.getInt("capacidad_zona"));
//                    zona_estable.setIdEstablecimiento(establecimiento);
//                    zona_estable.setIdZona(zonas);
//                    this.establecimientoList.add(zona_estable);
        } catch (SQLException ex) {
            Logger.getLogger(BoletoDao.class.getName()).log(Level.SEVERE, null, ex);
        } finally {
            this.conexion.cerrarConexiones();
        }
        return establecimientoList;

    }

}
