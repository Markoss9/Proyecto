package com.mycompany.proyecto;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.util.ArrayList;

public class Notas {

    private Identificador identificador;
    private final int dniUsuario;
    private String titulo;
    private String contenido;
    private LocalDate fechaCreacionModificacion;

    // Constructor con dni del Usuario, Titulo y contenido solamente
    public Notas(int dniUsuario, String titulo, String contenido) {
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacionModificacion = LocalDate.now(); // Inicializa con la fecha actual
    }

    // Constructor sin id
    public Notas(int dniUsuario, String titulo, String contenido, LocalDate fechaCreacionModificacion) {
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacionModificacion = LocalDate.now(); // Guarda la fecha actual
    }

    // Constructor completo
    public Notas(int id, int dniUsuario, String titulo, String contenido, LocalDate fechaCreacionModificacion) {
        this.identificador = new Identificador(id);
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacionModificacion = LocalDate.now(); // Guarda la fecha actual
    }

    // Getters y setters para el atributo compartido
    public int getId() {
        return identificador.getId();
    }

    public void setId(int id) {
        identificador.setId(id);
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

    public LocalDate getFechaCreacionModificacion() {
        return fechaCreacionModificacion;
    }

    public void setFechaCreacionModificacion(LocalDate fechaCreacionModificacion) {
        this.fechaCreacionModificacion = fechaCreacionModificacion;
    }

    // Método para guardar la nota en la base de datoS
    public void guardarNota(Connection conexion) throws SQLException {
        String sql = "INSERT INTO notas (dni, titulo, contenido, fecha_creacion_modificacion) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario);
            pstmt.setString(2, titulo);
            pstmt.setString(3, contenido);
            pstmt.setString(4, fechaCreacionModificacion.toString()); // Asigna la fecha en formato de texto
            pstmt.executeUpdate();
        }
    }

    // Método para actualizar una nota en la base de datos
    public void actualizarEnBaseDatos(Connection conexion) throws SQLException {
        String query = "UPDATE notas SET contenido = ?, fechaModificacion = ? WHERE titulo = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(query)) {
            pstmt.setString(1, this.contenido);
            pstmt.setDate(2, java.sql.Date.valueOf(this.fechaCreacionModificacion)); // Convierte LocalDate a Date para SQL
            pstmt.setString(3, this.titulo);
            pstmt.executeUpdate();
        }
    }

    public static ArrayList<Notas> listarNotas(Connection conexion, int dniUsuario) throws SQLException {
        ArrayList<Notas> listaNotas = new ArrayList<>();
        String sql = "SELECT id, titulo, fecha_creacion_modificacion FROM notas WHERE dni = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                LocalDate fechaCreacionModificacion = LocalDate.parse(rs.getString("fecha_creacion_modificacion"));

                // Agregar la nota a la lista
                Notas nota = new Notas(id, dniUsuario, titulo, null, fechaCreacionModificacion);
                listaNotas.add(nota);
            }
        }

        return listaNotas;
    }

}
