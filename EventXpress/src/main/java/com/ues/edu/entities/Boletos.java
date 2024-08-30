/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

import java.util.Date;

/**
 *
 * @author herna
 */
public class Boletos {
    private int id_boleto;
    private Date fecha_compra;
    private int cantidad_boletos;
    private String num_tarjeta;
    private double precioTotal;
    
    private Zona_Establec id_zona_establec;//
    private Evento_Programado id_evento_programado;
    private Usuarios dui;
    
    public Boletos() {
    }

    public Boletos(int id_boleto) {
        this.id_boleto = id_boleto;
    }

    public Boletos(Date fecha_compra, int cantidad_boletos, String num_tarjeta, Zona_Establec id_zona_establec, Evento_Programado id_evento_programado) {
        this.fecha_compra = fecha_compra;
        this.cantidad_boletos = cantidad_boletos;
        this.num_tarjeta = num_tarjeta;
        this.id_zona_establec = id_zona_establec;
        this.id_evento_programado = id_evento_programado;
        this.dui = new Usuarios();
    }

    public Boletos(int id_boleto, Date fecha_compra, int cantidad_boletos, String num_tarjeta, Zona_Establec id_zona_establec, Evento_Programado id_evento_programado) {
        this.id_boleto = id_boleto;
        this.fecha_compra = fecha_compra;
        this.cantidad_boletos = cantidad_boletos;
        this.num_tarjeta = num_tarjeta;
        this.id_zona_establec = id_zona_establec;
        this.id_evento_programado = id_evento_programado;
        this.dui = new Usuarios();
    }

    public Boletos(int id_boleto, Date fecha_compra, int cantidad_boletos, String num_tarjeta, double precioTotal, Zona_Establec id_zona_establec, Evento_Programado id_evento_programado, Usuarios dui) {
        this.id_boleto = id_boleto;
        this.fecha_compra = fecha_compra;
        this.cantidad_boletos = cantidad_boletos;
        this.num_tarjeta = num_tarjeta;
        this.precioTotal = precioTotal;
        this.id_zona_establec = id_zona_establec;
        this.id_evento_programado = id_evento_programado;
        this.dui = dui;
    }

    public double getPrecioTotal() {
        return precioTotal;
    }

    public void setPrecioTotal(double precioTotal) {
        this.precioTotal = precioTotal;
    }

    
    
    

    
    

    
    

    public int getId_boleto() {
        return id_boleto;
    }

    public void setId_boleto(int id_boleto) {
        this.id_boleto = id_boleto;
    }

    public Date getFecha_compra() {
        return fecha_compra;
    }

    public void setFecha_compra(Date fecha_compra) {
        this.fecha_compra = fecha_compra;
    }

    public int getCantidad_boletos() {
        return cantidad_boletos;
    }

    public void setCantidad_boletos(int cantidad_boletos) {
        this.cantidad_boletos = cantidad_boletos;
    }

    public String getNum_tarjeta() {
        return num_tarjeta;
    }

    public void setNum_tarjeta(String num_tarjeta) {
        this.num_tarjeta = num_tarjeta;
    }

    public Zona_Establec getId_zona_establec() {
        return id_zona_establec;
    }

    public void setId_zona_establec(Zona_Establec id_zona_establec) {
        this.id_zona_establec = id_zona_establec;
    }

    public Evento_Programado getId_evento_programado() {
        return id_evento_programado;
    }

    public void setId_evento_programado(Evento_Programado id_evento_programado) {
        this.id_evento_programado = id_evento_programado;
    }

    public Usuarios getDui() {
        return dui;
    }

    public void setDui(Usuarios dui) {
        this.dui = dui;
    }
}
