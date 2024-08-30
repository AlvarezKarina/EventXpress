/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

import java.util.ArrayList;

/**
 *
 * @author karie
 */
public class ZonaAux {
    private int idZonas;
    private String nombreZonas;
    private int capacidad;
    private ArrayList<Establecimiento> lst_Establec;

    public ZonaAux() {
    }

    public ZonaAux(int idZonas) {
        this.idZonas = idZonas;
    }

    public ZonaAux(int idZonas, String nombreZonas, int capacidad, ArrayList<Establecimiento> lst_Establec) {
        this.idZonas = idZonas;
        this.nombreZonas = nombreZonas;
        this.capacidad = capacidad;
        this.lst_Establec = lst_Establec;
    }

    
    
    public int getCapacidad() {
        return capacidad;
    }

    public void setCapacidad(int capacidad) {
        this.capacidad = capacidad;
    }

    
    
   

    public int getIdZonas() {
        return idZonas;
    }

    public void setIdZonas(int idZonas) {
        this.idZonas = idZonas;
    }

    public String getNombreZonas() {
        return nombreZonas;
    }

    public void setNombreZonas(String nombreZonas) {
        this.nombreZonas = nombreZonas;
    }

    public ArrayList<Establecimiento> getLst_Establec() {
        return lst_Establec;
    }

    public void setLst_Establec(ArrayList<Establecimiento> lst_Establec) {
        this.lst_Establec = lst_Establec;
    }
    
    
    
}
