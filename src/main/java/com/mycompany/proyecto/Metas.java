/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;


public class Metas {
    private Identificador identificador; // Composición
    private String descripcion;
    private String fechalimite;
    private float progreso;

    // Constructor
    public Metas(int id, String fecha, String descripcion, String fechalimite, float progreso) {
        this.identificador = new Identificador(id, fecha);
        this.descripcion = descripcion;
        this.fechalimite = fechalimite;
        this.progreso = progreso;
    }

    // Getters y Setters
    public int getId() {
        return identificador.getId();
    }

    public void setId(int id) {
        identificador.setId(id);
    }

    public String getFecha() {
        return identificador.getFecha();
    }

    public void setFecha(String fecha) {
        identificador.setFecha(fecha);
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }

    public String getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(String fechalimite) {
        this.fechalimite = fechalimite;
    }

    public float getProgreso() {
        return progreso;
    }

    public void setProgreso(float progreso) {
        this.progreso = progreso;
    }
}

