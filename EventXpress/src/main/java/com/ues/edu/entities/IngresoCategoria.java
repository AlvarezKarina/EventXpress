package com.ues.edu.entities;

public class IngresoCategoria {

    private char categoria;
    //private double TotalGenerado;
    private float totalGenerado;

    public IngresoCategoria() {
    }

    public IngresoCategoria(char categoria, float totalGenerado) {
        this.categoria = categoria;
        this.totalGenerado = totalGenerado;
    }

    public char getCategoria() {
        return categoria;
    }

    public void setCategoria(char categoria) {
        this.categoria = categoria;
    }

    public float getTotalGenerado() {
        return totalGenerado;
    }

    public void setTotalGenerado(float totalGenerado) {
        this.totalGenerado = totalGenerado;
    }
    

   
    
    

    

}
