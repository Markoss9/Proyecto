/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Notas {
    private Identificador identificador; // Composición
    private String titulo;
    private String contenido;
    private LocalDate fechaCreacion;
    private LocalDate fechaModificacion;

    // Constructor
    public Notas(int id, String fecha, String titulo, String contenido, LocalDate fechaCreacion, LocalDate fechaModificacion) {
        this.identificador = new Identificador(id, fecha);
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion;
        this.fechaModificacion = fechaModificacion;
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    public LocalDate getFechaModificacion() {
        return fechaModificacion;
    }

    public void setFechaModificacion(LocalDate fechaModificacion) {
        this.fechaModificacion = fechaModificacion;
    }


    // Método para crear una nueva nota
    public void crearNota(String titulo, String contenido, Connection conexion) throws SQLException {
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now();  // Captura la fecha actual de creación
        this.fechaModificacion = null; // Puedes inicializarla a null o dejarla como está

        // Guardar la nota en la base de datos
        guardarEnBaseDatos(conexion);
    }

    // Método para editar una nota existente
    public void editarNota(String nuevoContenido, Connection conexion) throws SQLException {
        this.contenido = nuevoContenido;
        this.fechaModificacion = LocalDate.now();  // Actualiza la fecha de modificación

        // Actualizar la nota en la base de datos
        actualizarEnBaseDatos(conexion);
    }

    // Método para eliminar el contenido de una nota en la memoria 
    public void eliminarNota(Connection conexion) throws SQLException {
        this.titulo = null;
        this.contenido = null;
        this.fechaCreacion = null;
        this.fechaModificacion = null;
           
    // Eliminar la nota de la base de datos
     eliminarDeBaseDatos(conexion);
    }

    // Método para mostrar la información de la nota
    public String mostrarNota() {
        return "Título: " + this.titulo + "\nContenido: " + this.contenido + 
               "\nFecha de creación: " + this.fechaCreacion + 
               (this.fechaModificacion != null ? "\nFecha de modificación: " + this.fechaModificacion : "");
    }

    // Método para buscar una nota por su título 
    public boolean buscarNota(String tituloBuscado) {
        return this.titulo != null && this.titulo.equalsIgnoreCase(tituloBuscado);
    }

    // Método para guardar la nota en la base de datos
    public void guardarEnBaseDatos(Connection conexion) throws SQLException {
        String query = "INSERT INTO notas (titulo, contenido, fechaCreacion) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, this.titulo);
            pstmt.setString(2, this.contenido);
            pstmt.setDate(3, java.sql.Date.valueOf(this.fechaCreacion)); // Convierte LocalDate a Date para SQL
            pstmt.executeUpdate();
        }
    }

    // Método para actualizar una nota en la base de datos
    public void actualizarEnBaseDatos(Connection conexion) throws SQLException {
        String query = "UPDATE notas SET contenido = ?, fechaModificacion = ? WHERE titulo = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, this.contenido);
            pstmt.setDate(2, java.sql.Date.valueOf(this.fechaModificacion)); // Convierte LocalDate a Date para SQL
            pstmt.setString(3, this.titulo);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar una nota de la base de datos
    public void eliminarDeBaseDatos(Connection conexion) throws SQLException {
        String query = "DELETE FROM notas WHERE titulo = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, this.titulo);
            pstmt.executeUpdate();
        }
    }
}

