/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

import java.util.ArrayList;
import java.util.Date;

/**
 *
 * @author herna
 */
public class Usuarios {

    private String dui;
    private String nombre_u;
    private String apellido_u;
    protected String email;
    protected String contraseña;
    private Date fecha_nac;
    private char genero_u;
    private boolean tipo_u;
    private ArrayList<Boletos> lsBoletos;

    public Usuarios() {
    }

    public Usuarios(String dui, String nombre_u, String email, String contraseña) {
        this.dui = dui;
        this.nombre_u = nombre_u;
        this.email = email;
        this.contraseña = contraseña;
    }

    public Usuarios(String nombre_u, String apellido_u, String email, String contraseña, Date fecha_nac, char genero_u, boolean tipo_u) {
        this.nombre_u = nombre_u;
        this.apellido_u = apellido_u;
        this.email = email;
        this.contraseña = contraseña;
        this.fecha_nac = fecha_nac;
        this.genero_u = genero_u;
        this.tipo_u = tipo_u;
        this.lsBoletos = new ArrayList<>();
    }

    public Usuarios(String dui, String nombre_u, String apellido_u, String email, String contraseña, Date fecha_nac, char genero_u, boolean tipo_u) {
        this.dui = dui;
        this.apellido_u = apellido_u;
        this.nombre_u = nombre_u;
        this.email = email;
        this.contraseña = contraseña;
        this.fecha_nac = fecha_nac;
        this.genero_u = genero_u;
        this.tipo_u = tipo_u;
        this.lsBoletos = new ArrayList<>();
    }

    public Usuarios(String dui, String nombre_u, String apellido_u, String email, String contraseña, Date fecha_nac, char genero_u, boolean tipo_u, ArrayList<Boletos> lsBoletos) {
        this.dui = dui;
        this.apellido_u = apellido_u;
        this.nombre_u = nombre_u;
        this.email = email;
        this.contraseña = contraseña;
        this.fecha_nac = fecha_nac;
        this.genero_u = genero_u;
        this.tipo_u = tipo_u;
        this.lsBoletos = lsBoletos;
    }

    public String getDui() {
        return dui;
    }

    public void setDui(String dui) {
        this.dui = dui;
    }

    public String getNombre_u() {
        return nombre_u;
    }

    public void setNombre_u(String nombre_u) {
        this.nombre_u = nombre_u;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }

    public Date getFecha_nac() {
        return fecha_nac;
    }

    public void setFecha_nac(Date fecha_nac) {
        this.fecha_nac = fecha_nac;
    }

    public char getGenero_u() {
        return genero_u;
    }

    public void setGenero_u(char genero_u) {
        this.genero_u = genero_u;
    }

    public boolean isTipo_u() {

        return tipo_u;
    }

    public void setTipo_u(boolean tipo_u) {
        this.tipo_u = tipo_u;
    }

    public String getApellido_u() {
        return apellido_u;
    }

    public void setApellido_u(String apellido_u) {
        this.apellido_u = apellido_u;
    }

    public ArrayList<Boletos> getLsBoletos() {
        return lsBoletos;
    }

    public void setLsBoletos(ArrayList<Boletos> lsBoletos) {
        this.lsBoletos = lsBoletos;
    }

}
