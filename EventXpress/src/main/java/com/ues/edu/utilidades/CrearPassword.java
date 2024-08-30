/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.ues.edu.utilidades;

/**
 *
 * @author herna
 */
public class CrearPassword {
    public static void main(String[] args) {
        String cadena = Encriptar.getStringMessageDigest("contra1", Encriptar.SHA256);
        System.out.println(cadena);
    }
}
