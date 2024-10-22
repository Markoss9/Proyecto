/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

public class Tareas {
    private Identificador identificador; // Composición
    private String descripcion;
    private boolean completada;
    private String fechalimite;

    // Constructor
    public Tareas(int id, String fecha, String descripcion, boolean completada, String fechalimite) {
        this.identificador = new Identificador(id, fecha);
        this.descripcion = descripcion;
        this.completada = completada;
        this.fechalimite = fechalimite;
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

    public boolean isCompletada() {
        return completada;
    }

    public void setCompletada(boolean completada) {
        this.completada = completada;
    }

    public String getFechalimite() {
        return fechalimite;
    }

    public void setFechalimite(String fechalimite) {
        this.fechalimite = fechalimite;
    }
}

