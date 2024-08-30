/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

/**
 *
 * @author herna
 */
public class Carrito {
    int item;
    int idZonaEstable;
    int idEventoProgra;
    String nEvento;
    String nZona;
    String descripcion;
    String establecimiento;
    String direccion;
    int boletosDisponibles;
    double precio;
    int cantidad;
    double subTotal;

    public Carrito() {
    }

    public Carrito(int item, int idZonaEstable, int idEventoProgra, String nEvento, String nZona, String descripcion, String establecimiento,String direccion,int boletosDisponibles, double precio, int cantidad, double subTotal) {
        this.item = item;
        this.idZonaEstable = idZonaEstable;
        this.idEventoProgra = idEventoProgra;
        this.nEvento = nEvento;
        this.nZona = nZona;
        this.descripcion = descripcion;
        this.establecimiento = establecimiento;
        this.direccion=direccion;
        this.boletosDisponibles=boletosDisponibles;
        this.precio = precio;
        this.cantidad = cantidad;
        this.subTotal = subTotal;
    }

    public int getItem() {
        return item;
    }

    public void setItem(int item) {
        this.item = item;
    }

    public int getIdZonaEstable() {
        return idZonaEstable;
    }

    public void setIdZonaEstable(int idZonaEstable) {
        this.idZonaEstable = idZonaEstable;
    }

    public int getIdEventoProgra() {
        return idEventoProgra;
    }

    public void setIdEventoProgra(int idEventoProgra) {
        this.idEventoProgra = idEventoProgra;
    }

    public String getnEvento() {
        return nEvento;
    }

    public void setnEvento(String nEvento) {
        this.nEvento = nEvento;
    }

    public String getnZona() {
        return nZona;
    }

    public void setnZona(String nZona) {
        this.nZona = nZona;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getEstablecimiento() {
        return establecimiento;
    }

    public void setEstablecimiento(String establecimiento) {
        this.establecimiento = establecimiento;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public int getBoletosDisponibles() {
        return boletosDisponibles;
    }

    public void setBoletosDisponibles(int boletosDisponibles) {
        this.boletosDisponibles = boletosDisponibles;
    }
    
    

    public double getPrecio() {
        return precio;
    }

    public void setPrecio(double precio) {
        this.precio = precio;
    }

    public int getCantidad() {
        return cantidad;
    }

    public void setCantidad(int cantidad) {
        this.cantidad = cantidad;
    }

    public double getSubTotal() {
        return subTotal;
    }

    public void setSubTotal(double subTotal) {
        this.subTotal = subTotal;
    }

    
    
    
    
    
}
