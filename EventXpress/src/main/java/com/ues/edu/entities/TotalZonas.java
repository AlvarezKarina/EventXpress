/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

/**
 *
 * @author karie
 */
public class TotalZonas {
    
    private String nEstablecimiento;
    private String nZona;
    private int totalZona;

    public TotalZonas() {
    }

    public TotalZonas(String nZona, String nEstablecimiento, int totalZona) {
        this.nZona = nZona;
        this.nEstablecimiento = nEstablecimiento;
        this.totalZona = totalZona;
    }

    public String getnZona() {
        return nZona;
    }

    public void setnZona(String nZona) {
        this.nZona = nZona;
    }

    public String getnEstablecimiento() {
        return nEstablecimiento;
    }

    public void setnEstablecimiento(String nEstablecimiento) {
        this.nEstablecimiento = nEstablecimiento;
    }

    public int getTotalZona() {
        return totalZona;
    }

    public void setTotalZona(int totalZona) {
        this.totalZona = totalZona;
    }
    
    
    
}
