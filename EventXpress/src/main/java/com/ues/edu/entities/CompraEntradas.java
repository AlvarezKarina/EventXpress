package com.ues.edu.entities;

public class CompraEntradas {

    private String nombre_u;
    private String apellido_u;
    private String email;
    private int eventosAsistidos;

    public CompraEntradas() {
    }

    public CompraEntradas(String nombre_u, String apellido_u, String email, int eventosAsistidos) {
        this.nombre_u = nombre_u;
        this.apellido_u = apellido_u;
        this.email = email;
        this.eventosAsistidos = eventosAsistidos;
    }

    public String getNombre_u() {
        return nombre_u;
    }

    public void setNombre_u(String nombre_u) {
        this.nombre_u = nombre_u;
    }

    public String getApellido_u() {
        return apellido_u;
    }

    public void setApellido_u(String apellido_u) {
        this.apellido_u = apellido_u;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getEventosAsistidos() {
        return eventosAsistidos;
    }

    public void setEventosAsistidos(int eventosAsistidos) {
        this.eventosAsistidos = eventosAsistidos;
    }

}
