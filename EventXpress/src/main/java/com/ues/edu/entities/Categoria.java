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
public class Categoria {
    private int idCategoria;
    private String categoria; 
    private ArrayList<Eventos>lsEventos;

    public Categoria() {
    }

    public Categoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public Categoria(int idCategoria, String categoria, ArrayList<Eventos> lsEventos) {
        this.idCategoria = idCategoria;
        this.categoria = categoria;
        this.lsEventos = lsEventos;
    }

    public int getIdCategoria() {
        return idCategoria;
    }

    public void setIdCategoria(int idCategoria) {
        this.idCategoria = idCategoria;
    }

    public String getCategoria() {
        return categoria;
    }

    public void setCategoria(String categoria) {
        this.categoria = categoria;
    }

    public ArrayList<Eventos> getLsEventos() {
        return lsEventos;
    }

    public void setLsEventos(ArrayList<Eventos> lsEventos) {
        this.lsEventos = lsEventos;
    }
    
    
    
}
