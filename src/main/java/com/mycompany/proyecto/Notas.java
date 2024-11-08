package com.mycompany.proyecto;

import java.time.LocalDate;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.*;
import java.time.Instant;
import java.time.ZoneId;
import java.util.ArrayList;

public class Notas {

    private Identificador identificador;
    private final int dniUsuario;
    private String titulo;
    private String contenido;
    private LocalDate fechaCreacion;

    // Constructor con dni del Usuario, Titulo y contenido solamente
    public Notas(int dniUsuario, String titulo, String contenido) {
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now(); // Inicializa con la fecha actual
    }

    // Constructor sin id
    public Notas(int dniUsuario, String titulo, String contenido, LocalDate fechaCreacion) {
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now(); // Guarda la fecha actual
    }

    // Constructor completo
    public Notas(int id, int dniUsuario, String titulo, String contenido, LocalDate fechaCreacion) {
        this.identificador = new Identificador(id);
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now(); // Guarda la fecha actual
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

    public LocalDate getFechaCreacion() {
        return fechaCreacion;
    }

    public void setFechaCreacionModificacion(LocalDate fechaCreacion) {
        this.fechaCreacion = fechaCreacion;
    }

    // Método para guardar la nota en la base de datoS
    public void guardarNota(Connection conexion) throws SQLException {
        String sql = "INSERT INTO notas (dni, titulo, contenido, fecha_creacion) VALUES (?, ?, ?, ?)";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario);
            pstmt.setString(2, titulo);
            pstmt.setString(3, contenido);
            pstmt.setString(4, fechaCreacion.toString()); // Asigna la fecha en formato de texto
            pstmt.executeUpdate();
        }
    }

    // Metodo para actualizar las notas
    public static void actualizarNota(Connection conexion, int dniUsuario, int idNota, String nuevoTitulo, String nuevoContenido) throws SQLException {
        String sql = "UPDATE notas SET titulo = ?, contenido = ? WHERE id = ? AND dni = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nuevoTitulo);
            stmt.setString(2, nuevoContenido);
            stmt.setInt(3, idNota);
            stmt.setInt(4, dniUsuario);

            int filasActualizadas = stmt.executeUpdate();
            if (filasActualizadas == 0) {
                throw new SQLException("No se encontró la nota o no pertenece al usuario actual.");
            }
        }
    }

    // Metodo para obtener las notas desde la base de datos 
    public static ArrayList<Notas> listarNotas(Connection conexion, int dniUsuario) throws SQLException {
        ArrayList<Notas> listaNotas = new ArrayList<>();
        String sql = "SELECT id, titulo, fecha_creacion FROM notas WHERE dni = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fecha_creacion"));

                // Agregar la nota a la lista
                Notas nota = new Notas(id, dniUsuario, titulo, null, fechaCreacion);
                listaNotas.add(nota);
            }
        }

        return listaNotas;
    }

    // Metodo para buscar una nota por su id correspondiente 
    public static Notas buscarNotaPorId(Connection conexion, int dniUsuario, int id) throws SQLException {
        String sql = "SELECT id, titulo, contenido, fecha_creacion FROM notas WHERE dni = ? AND id = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario);
            pstmt.setInt(2, id);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Obtener la fecha como long (timestamp en milisegundos)
                long fechaMillis = rs.getLong("fecha_creacion");

                // Convertir el timestamp a LocalDate
                LocalDate fechaCreacion = Instant.ofEpochMilli(fechaMillis)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // Crear y devolver una instancia de Notas con los datos obtenidos
                return new Notas(
                        rs.getInt("id"),
                        rs.getString("titulo"),
                        rs.getString("contenido"),
                        fechaCreacion
                );
            } else {
                // No se encontró la nota
                return null;
            }
        }
    }

    public static void eliminarNota(Connection conexion, int dniUsuario, int idNota) throws SQLException {
        String sql = "DELETE FROM notas WHERE id = ? AND dni = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idNota);
            stmt.setInt(2, dniUsuario);

            int filasEliminadas = stmt.executeUpdate();

            if (filasEliminadas == 0) {
                throw new SQLException("No se encontró la nota o no pertenece al usuario actual.");
            }
        }
    }

}
