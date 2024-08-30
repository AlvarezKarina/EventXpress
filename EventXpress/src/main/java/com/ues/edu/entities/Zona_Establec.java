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
public class Zona_Establec {
    private int idZonaEstablecimiento;
    private double precio;
    private int capacidadZona;
    private Zonas idZona;
    private Establecimiento idEstablecimiento;
    private ArrayList<Boletos> lsBoletos;

    public Zona_Establec() {
    }
    public Zona_Establec(int idZonaEstablecimiento) {
        this.idZonaEstablecimiento = idZonaEstablecimiento;
    }

    public Zona_Establec(double precio, int capacidadZona) {
        this.precio = precio;
        this.capacidadZona = capacidadZona;
        this.idZona = new Zonas();
        this.idEstablecimiento = new Establecimiento();
        this.lsBoletos = new ArrayList<>();
    }

    public Zona_Establec(int idZonaEstablecimiento, double precio, int capacidadZona) {
        this.idZonaEstablecimiento = idZonaEstablecimiento;
        this.precio = precio;
        this.capacidadZona = capacidadZona;
        this.idZona = new Zonas();
        this.idEstablecimiento = new Establecimiento();
        this.lsBoletos = new ArrayList<>(); 
    }

    public Zona_Establec(int idZonaEstablecimiento, double precio, int capacidadZona, Zonas idZona, Establecimiento idEstablecimiento, ArrayList<Boletos> lsBoletos) {
        this.idZonaEstablecimiento = idZonaEstablecimiento;
        this.precio = precio;
        this.capacidadZona = capacidadZona;
        this.idZona = idZona;
        this.idEstablecimiento = idEstablecimiento;
        this.lsBoletos = lsBoletos;
    } 
    
    public int getIdZonaEstablecimiento() {
        return idZonaEstablecimiento;
    }

    public void setIdZonaEstablecimiento(int idZonaEstablecimiento) {
        this.idZonaEstablecimiento = idZonaEstablecimiento;
    }

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCapacidadZona() {
        return capacidadZona;
    }

    public void setCapacidadZona(int capacidadZona) {
        this.capacidadZona = capacidadZona;
    }

    public Zonas getIdZona() {
        return idZona;
    }

    public void setIdZona(Zonas idZona) {
        this.idZona = idZona;
    }

    public Establecimiento getIdEstablecimiento() {
        return idEstablecimiento;
    }

    public void setIdEstablecimiento(Establecimiento idEstablecimiento) {
        this.idEstablecimiento = idEstablecimiento;
    }

    public ArrayList<Boletos> getLsBoletos() {
        return lsBoletos;
    }

    public void setLsBoletos(ArrayList<Boletos> lsBoletos) {
        this.lsBoletos = lsBoletos;
    } 
}
