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

    // Constructor con dni del Usuario, Título y Contenido, asigna la fecha actual de creación
    public Notas(int dniUsuario, String titulo, String contenido) {
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = LocalDate.now(); // Inicializa con la fecha actual
    }

    // Constructor con fecha específica, útil para inicializar con datos ya existentes
    public Notas(int dniUsuario, String titulo, String contenido, LocalDate fechaCreacion) {
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion; // Asigna la fecha pasada como parámetro
    }

    // Constructor completo con ID, utilizado al cargar datos desde la base de datos
    public Notas(int id, int dniUsuario, String titulo, String contenido, LocalDate fechaCreacion) {
        this.identificador = new Identificador(id);
        this.dniUsuario = dniUsuario;
        this.titulo = titulo;
        this.contenido = contenido;
        this.fechaCreacion = fechaCreacion; // Asigna la fecha de creación existente
    }

    // Getters y Setters
    public int getId() {
        return identificador.getId();
    }

    public void setId(int id) {
        identificador.setId(id);
    }

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

    // Método para guardar la nota en la base de datos
    public void guardarNota(Connection conexion) throws SQLException {
        String sql = "INSERT INTO notas (dni, titulo, contenido, fecha_creacion) VALUES (?, ?, ?, ?)";

        // PreparedStatement para prevenir SQL injection
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario);                 // Asigna el DNI del usuario
            pstmt.setString(2, titulo);                  // Asigna el título de la nota
            pstmt.setString(3, contenido);               // Asigna el contenido de la nota
            pstmt.setString(4, fechaCreacion.toString()); // Asigna la fecha en formato de texto
            pstmt.executeUpdate();                       // Ejecuta el comando SQL para guardar la nota
        }
    }

    // Método para actualizar una nota en la base de datos
    public static void actualizarNota(Connection conexion, int dniUsuario, int idNota, String nuevoTitulo, String nuevoContenido) throws SQLException {
        String sql = "UPDATE notas SET titulo = ?, contenido = ? WHERE id = ? AND dni = ?";

        // Utiliza PreparedStatement para evitar SQL injection
        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setString(1, nuevoTitulo);        // Asigna el nuevo título
            stmt.setString(2, nuevoContenido);     // Asigna el nuevo contenido
            stmt.setInt(3, idNota);                // Asigna el ID de la nota a actualizar
            stmt.setInt(4, dniUsuario);            // Verifica el DNI del usuario

            int filasActualizadas = stmt.executeUpdate(); // Ejecuta la actualización
            if (filasActualizadas == 0) {
                throw new SQLException("No se encontró la nota o no pertenece al usuario actual.");
            }
        }
    }

    // Método para listar todas las notas de un usuario
    public static ArrayList<Notas> listarNotas(Connection conexion, int dniUsuario) throws SQLException {
        ArrayList<Notas> listaNotas = new ArrayList<>();
        String sql = "SELECT id, titulo, fecha_creacion FROM notas WHERE dni = ?";

        // Utiliza PreparedStatement para ejecutar la consulta con seguridad
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario); // Filtra las notas por el DNI del usuario
            ResultSet rs = pstmt.executeQuery();

            // Itera por los resultados y construye una lista de objetos Notas
            while (rs.next()) {
                int id = rs.getInt("id");
                String titulo = rs.getString("titulo");
                LocalDate fechaCreacion = LocalDate.parse(rs.getString("fecha_creacion"));
                Notas nota = new Notas(id, dniUsuario, titulo, null, fechaCreacion);
                listaNotas.add(nota); // Añade cada nota a la lista
            }
        }

        return listaNotas; // Devuelve la lista completa
    }

    // Método para buscar una nota específica por ID y DNI
    public static Notas buscarNotaPorId(Connection conexion, int dniUsuario, int id) throws SQLException {
        String sql = "SELECT id, titulo, contenido, fecha_creacion FROM notas WHERE dni = ? AND id = ?";

        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario); // Filtra por DNI
            pstmt.setInt(2, id);         // Filtra por ID
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                // Convierte la fecha de creación (timestamp) a LocalDate
                long fechaMillis = rs.getLong("fecha_creacion");
                LocalDate fechaCreacion = Instant.ofEpochMilli(fechaMillis)
                        .atZone(ZoneId.systemDefault())
                        .toLocalDate();

                // Crea y devuelve una instancia de Notas con los datos encontrados
                return new Notas(
                        rs.getInt("id"),
                        dniUsuario,
                        rs.getString("titulo"),
                        rs.getString("contenido"),
                        fechaCreacion
                );
            } else {
                return null; // Si no se encontró la nota, devuelve null
            }
        }
    }

    // Método para eliminar una nota de la base de datos
    public static void eliminarNota(Connection conexion, int dniUsuario, int idNota) throws SQLException {
        String sql = "DELETE FROM notas WHERE id = ? AND dni = ?";

        try (PreparedStatement stmt = conexion.prepareStatement(sql)) {
            stmt.setInt(1, idNota);      // Filtra por ID de la nota
            stmt.setInt(2, dniUsuario);  // Filtra por DNI del usuario

            int filasEliminadas = stmt.executeUpdate(); // Ejecuta el comando para eliminar
            if (filasEliminadas == 0) {
                throw new SQLException("No se encontró la nota o no pertenece al usuario actual.");
            }
        }
    }
}
