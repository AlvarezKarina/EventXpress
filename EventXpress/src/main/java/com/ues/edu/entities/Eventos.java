/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.entities;

/**
 *
 * @author karie
 */
public class Eventos {
    private int idEvento;
    private String nEvento;
    private String descripcion;
    private Categoria categoria; 

    public Eventos() {
    }

    public Eventos(int idEvento) {
        this.idEvento = idEvento;
    }

    public Eventos(int idEvento, String nEvento, String descripcion, Categoria categoria) {
        this.idEvento = idEvento;
        this.nEvento = nEvento;
        this.descripcion = descripcion;
        this.categoria = categoria;
    }

    public int getIdEvento() {
        return idEvento;
    }

    public void setIdEvento(int idEvento) {
        this.idEvento = idEvento;
    }

    public String getnEvento() {
        return nEvento;
    }

    public void setnEvento(String nEvento) {
        this.nEvento = nEvento;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public Categoria getCategoria() {
        return categoria;
    }

    public void setCategoria(Categoria categoria) {
        this.categoria = categoria;
    }

 
    
    
}
