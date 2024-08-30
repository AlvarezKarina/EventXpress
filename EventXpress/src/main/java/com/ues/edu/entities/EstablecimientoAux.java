/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

/**
 *
 * @author karie
 */
public class EstablecimientoAux {
    private int id_establecimiento;
    private String n_establecimiento;
    private String direccion;
    private String imagen;
    //private int capacidad_establec;
    private double precio;
    private int capacidadZona;
    private ZonaAux zona;
    
    
    // aca van los constructores

    public EstablecimientoAux() {
    }

    public EstablecimientoAux(int id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

    public EstablecimientoAux(ZonaAux zona) {
        this.zona = zona;
    }
    
    

    
    public EstablecimientoAux(int id_establecimiento, String n_establecimiento, String direccion, String imagen, int capacidad_establec, double precio, int capacidadZona, ZonaAux zona) {
        this.id_establecimiento = id_establecimiento;
        this.n_establecimiento = n_establecimiento;
        this.direccion = direccion;
        this.imagen = imagen;
        //this.capacidad_establec = capacidad_establec;
        this.precio = precio;
        this.capacidadZona = capacidadZona;
        this.zona = zona;
    }
    
    

    public int getId_establecimiento() {
        return id_establecimiento;
    }

    public void setId_establecimiento(int id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

    public String getN_establecimiento() {
        return n_establecimiento;
    }

    public int getCapacidadZona() {
        return capacidadZona;
    }

    public void setCapacidadZona(int capacidadZona) {
        this.capacidadZona = capacidadZona;
    }

    public void setN_establecimiento(String n_establecimiento) {
        this.n_establecimiento = n_establecimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getImagen() {
        return imagen;
    }

    public void setImagen(String imagen) {
        this.imagen = imagen;
    }
//
//    public int getCapacidad_establec() {
//        return capacidad_establec;
//    }
//
//    public void setCapacidad_establec(int capacidad_establec) {
//        this.capacidad_establec = capacidad_establec;
//    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public ZonaAux getZona() {
        return zona;
    }

    public void setZona(ZonaAux zona) {
        this.zona = zona;
    }
    
    
    
    
}
