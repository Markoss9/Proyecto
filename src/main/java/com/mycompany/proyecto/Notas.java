/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;


public class Notas {
    private Identificador identificador; // Composición
    private String titulo;
    private String contenido;
    private String fechacreacion;
    private String fechamodificacion;

    // Constructor
    public Notas(int id, String fecha, String titulo, String contenido, String fechacreacion, String fechamodificacion) {
        this.identificador = new Identificador(id, fecha);
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechacreacion = fechacreacion;
        this.fechamodificacion = fechamodificacion;
    }

    // Getters y Setters para los atributos compartidos
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

    // Getters y Setters para atributos específicos de Notas
    public String getTitulo() {
        return titulo;
    }

    public void setTitulo(String titulo) {
        this.titulo = titulo;
    }

    public String getContenido() {
        return contenido;
    }

    public void setContenido(String contenido) {
        this.contenido = contenido;
    }

    public String getFechacreacion() {
        return fechacreacion;
    }

    public void setFechacreacion(String fechacreacion) {
        this.fechacreacion = fechacreacion;
    }

    public String getFechamodificacion() {
        return fechamodificacion;
    }

    public void setFechamodificacion(String fechamodificacion) {
        this.fechamodificacion = fechamodificacion;
    }
}
