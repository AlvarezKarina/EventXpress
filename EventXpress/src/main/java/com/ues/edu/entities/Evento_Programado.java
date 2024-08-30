package com.ues.edu.entities;

import java.sql.Time;
import java.util.ArrayList;
import java.util.Date;

/**
 *    
 * @author herna
 */
public class Evento_Programado {

    private int id_evento_progra;
    private Date fecha;
    private Time hora_inicia;
    private Time hora_fin;
    private boolean suspendido;
    private boolean reprogramado;
    private Date fecha_reprogramado;

    private Eventos id_evento;
    private Establecimiento establecimientos;
    private ArrayList<Boletos> lsBoletos;

    public Evento_Programado() {
    }

    public Evento_Programado(int id_evento_progra) {
        this.id_evento_progra = id_evento_progra;
    }

    public Evento_Programado(Date fecha, Time hora_inicia, Time hora_fin, boolean suspendido, boolean reprogramado, Date fecha_reprogramado) {
        this.fecha = fecha;
        this.hora_inicia = hora_inicia;
        this.hora_fin = hora_fin;
        this.suspendido = suspendido;
        this.reprogramado = reprogramado;
        this.fecha_reprogramado = fecha_reprogramado;
        this.id_evento = new Eventos();
        this.lsBoletos = new ArrayList<>();
    }

    public Evento_Programado(int id_evento_progra, Date fecha, Time hora_inicia, Time hora_fin, boolean suspendido, boolean reprogramado, Date fecha_reprogramado) {
        this.id_evento_progra = id_evento_progra;
        this.fecha = fecha;
        this.hora_inicia = hora_inicia;
        this.hora_fin = hora_fin;
        this.suspendido = suspendido;
        this.reprogramado = reprogramado;
        this.fecha_reprogramado = fecha_reprogramado;
        this.id_evento = new Eventos();
        this.lsBoletos = new ArrayList<>();
    }

    public Evento_Programado(int id_evento_progra, Date fecha, Time hora_inicia, Time hora_fin, boolean suspendido, boolean reprogramado, Date fecha_reprogramado, Eventos id_evento, ArrayList<Boletos> lsBoletos) {
        this.id_evento_progra = id_evento_progra;
        this.fecha = fecha;
        this.hora_inicia = hora_inicia;
        this.hora_fin = hora_fin;
        this.suspendido = suspendido;
        this.reprogramado = reprogramado;
        this.fecha_reprogramado = fecha_reprogramado;
        this.id_evento = id_evento;
        this.lsBoletos = lsBoletos;
    }

    public Evento_Programado(int id_evento_progra, Date fecha, Time hora_inicia, Time hora_fin, boolean suspendido, boolean reprogramado, Date fecha_reprogramado, Eventos id_evento, Establecimiento establecimientos, ArrayList<Boletos> lsBoletos) {
        this.id_evento_progra = id_evento_progra;
        this.fecha = fecha;
        this.hora_inicia = hora_inicia;
        this.hora_fin = hora_fin;
        this.suspendido = suspendido;
        this.reprogramado = reprogramado;
        this.fecha_reprogramado = fecha_reprogramado;
        this.id_evento = id_evento;
        this.establecimientos = establecimientos;
        this.lsBoletos = lsBoletos;
    }

    public Establecimiento getEstablecimientos() {
        return establecimientos;
    }

    public void setEstablecimientos(Establecimiento establecimientos) {
        this.establecimientos = establecimientos;
    }

    public int getId_evento_progra() {
        return id_evento_progra;
    }

    public void setId_evento_progra(int id_evento_progra) {
        this.id_evento_progra = id_evento_progra;
    }

    public Date getFecha() {
        return fecha;
    }

    public void setFecha(Date fecha) {
        this.fecha = fecha;
    }

    public Time getHora_inicia() {
        return hora_inicia;
    }

    public void setHora_inicia(Time hora_inicia) {
        this.hora_inicia = hora_inicia;
    }

    public Time getHora_fin() {
        return hora_fin;
    }

    public void setHora_fin(Time hora_fin) {
        this.hora_fin = hora_fin;
    }

    public boolean isSuspendido() {
        return suspendido;
    }

    public void setSuspendido(boolean suspendido) {
        this.suspendido = suspendido;
    }

    public boolean isReprogramado() {
        return reprogramado;
    }

    public void setReprogramado(boolean reprogramado) {
        this.reprogramado = reprogramado;
    }

    public Date getFecha_reprogramado() {
        return fecha_reprogramado;
    }

    public void setFecha_reprogramado(Date fecha_reprogramado) {
        this.fecha_reprogramado = fecha_reprogramado;
    }

    public Eventos getId_evento() {
        return id_evento;
    }

    public void setId_evento(Eventos id_evento) {
        this.id_evento = id_evento;
    }

    public ArrayList<Boletos> getLsBoletos() {
        return lsBoletos;
    }

    public void setLsBoletos(ArrayList<Boletos> lsBoletos) {
        this.lsBoletos = lsBoletos;
    }

}
