/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

import java.util.ArrayList;

/**
 *
 * @author herna
 */
public class Establecimiento {

    private int id_establecimiento;
    private String n_establecimiento;
    private String direccion;
    private int capacidad_establec;
    private String fecha_activacion;
    private String fecha_de_baja;
    private Boolean estado;
    private ArrayList<Zona_Establec> zonas; // Relación uno a muchos

    public Establecimiento() {

    }

    public Establecimiento(int id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

    public Establecimiento(int id_establecimiento, String n_establecimiento, String direccion, int capacidad_establec, String fecha_activacion, String fecha_de_baja, Boolean estado, ArrayList<Zona_Establec> zonas) {
        this.id_establecimiento = id_establecimiento;
        this.n_establecimiento = n_establecimiento;
        this.direccion = direccion;
        this.capacidad_establec = capacidad_establec;
        this.fecha_activacion = fecha_activacion;
        this.fecha_de_baja = fecha_de_baja;
        this.estado = estado;
        this.zonas = zonas;
    }

    public Establecimiento(String n_establecimiento, String direccion, int capacidad_establec) {
        this.n_establecimiento = n_establecimiento;
        this.direccion = direccion;
        this.capacidad_establec = capacidad_establec;
    }

    public Establecimiento(int id_establecimiento, String n_establecimiento, String direccion, int capacidad_establec) {
        this.id_establecimiento = id_establecimiento;
        this.n_establecimiento = n_establecimiento;
        this.direccion = direccion;
        this.capacidad_establec = capacidad_establec;
    }

    public Establecimiento(int id_establecimiento, String n_establecimiento, String direccion, int capacidad_establec, String fecha_de_baja) {
        this.id_establecimiento = id_establecimiento;
        this.n_establecimiento = n_establecimiento;
        this.direccion = direccion;

        this.capacidad_establec = capacidad_establec;
        this.fecha_de_baja = fecha_de_baja;
    }

    public Establecimiento(int id_establecimiento, String n_establecimiento, String direccion, String fecha_activacion, int capacidad_establec) {
        this.id_establecimiento = id_establecimiento;
        this.n_establecimiento = n_establecimiento;
        this.direccion = direccion;
        this.fecha_activacion = fecha_activacion;
        this.capacidad_establec = capacidad_establec;
    }

//    public Establecimiento(int id_establecimiento, String n_establecimiento, String direccion, int capacidad_establec) {
//        this.id_establecimiento = id_establecimiento;
//        this.n_establecimiento = n_establecimiento;
//        this.direccion = direccion;
//        this.imagen = imagen;
//        this.capacidad_establec = capacidad_establec;
//    }
//    
    public Establecimiento(int id_establecimiento, String n_establecimiento, String direccion, int capacidad_establec, ArrayList<Zona_Establec> zonas) {
        this.id_establecimiento = id_establecimiento;
        this.n_establecimiento = n_establecimiento;
        this.direccion = direccion;
        this.capacidad_establec = capacidad_establec;
        this.zonas = zonas;
    }

//    public Establecimiento(String n_establecimiento, String direccion, int capacidad_establec) {
//        this.n_establecimiento = n_establecimiento;
//        this.direccion = direccion;
//        this.imagen = imagen;
//        this.capacidad_establec = capacidad_establec;
//    }
//    
    public int getId_establecimiento() {
        return id_establecimiento;
    }

    public void setId_establecimiento(int id_establecimiento) {
        this.id_establecimiento = id_establecimiento;
    }

    public String getN_establecimiento() {
        return n_establecimiento;
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

    public int getCapacidad_establec() {
        return capacidad_establec;
    }

    public void setCapacidad_establec(int capacidad_establec) {
        this.capacidad_establec = capacidad_establec;
    }

    public ArrayList<Zona_Establec> getZonas() {
        return zonas;
    }

    public void setZonas(ArrayList<Zona_Establec> zonas) {
        this.zonas = zonas;
    }

    public String getFecha_activacion() {
        return fecha_activacion;
    }

    public void setFecha_activacion(String fecha_activacion) {
        this.fecha_activacion = fecha_activacion;
    }

    public String getFecha_de_baja() {
        return fecha_de_baja;
    }

    public void setFecha_de_baja(String fecha_de_baja) {
        this.fecha_de_baja = fecha_de_baja;
    }

    public Boolean getEstado() {
        return estado;
    }

    public void setEstado(Boolean estado) {
        this.estado = estado;
    }

}
