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
public class Zonas {
    private int idZonas;
    private String nombreZonas;
    private ArrayList<Zona_Establec> lsZona_Establec;

    public Zonas() {
    }

    public Zonas(int idZonas) {
        this.idZonas = idZonas;
    }

    public Zonas(String nombreZonas) {
        this.nombreZonas = nombreZonas;
        this.lsZona_Establec = new ArrayList<>();
    }

    public Zonas(int idZonas, String nombreZonas) {
        this.idZonas = idZonas;
        this.nombreZonas = nombreZonas;
        this.lsZona_Establec = new ArrayList<>();
    }

    public Zonas(int idZonas, String nombreZonas, ArrayList<Zona_Establec> lsZona_Establec) {
        this.idZonas = idZonas;
        this.nombreZonas = nombreZonas;
        this.lsZona_Establec = lsZona_Establec;
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

    public ArrayList<Zona_Establec> getLsZona_Establec() {
        return lsZona_Establec;
    }

    public void setLsZona_Establec(ArrayList<Zona_Establec> lsZona_Establec) {
        this.lsZona_Establec = lsZona_Establec;
    }
    
    
}
