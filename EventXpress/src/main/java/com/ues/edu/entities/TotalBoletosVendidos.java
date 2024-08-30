/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

/**
 *
 * @author ASUS
 */
public class TotalBoletosVendidos {
    private String n_evento;
    private int totalBoletosVendidos;

    public TotalBoletosVendidos() {
    }

    public TotalBoletosVendidos(String n_evento, int totalBoletosVendidos) {
        this.n_evento = n_evento;
        this.totalBoletosVendidos = totalBoletosVendidos;
    }

    public String getN_evento() {
        return n_evento;
    }

    public void setN_evento(String n_evento) {
        this.n_evento = n_evento;
    }

    public int getTotalBoletosVendidos() {
        return totalBoletosVendidos;
    }

    public void setTotalBoletosVendidos(int totalBoletosVendidos) {
        this.totalBoletosVendidos = totalBoletosVendidos;
    }
    
}
