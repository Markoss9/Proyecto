package com.mycompany.proyecto;

import java.sql.*;
import java.util.List;
import java.util.ArrayList;

public class GestorDeBaseDeDatos {
    private String usuarioActual;
       public GestorDeBaseDeDatos(String usuario) {
           this.usuarioActual=usuario;
        crearTablaSiNoExiste();  // Crear tabla al inicializar el gestor de la base de datos
    }
    private Connection conectar() {
        String url = "jdbc:sqlite:calendario.db";
        Connection conn = null;
        try {
            conn = DriverManager.getConnection(url);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
        return conn;
    }
      private void crearTablaSiNoExiste() {
        String sql = "CREATE TABLE IF NOT EXISTS eventos ("
                   + "fecha TEXT PRIMARY KEY,"
                   + "nota TEXT"
                   + ");";

        try (Connection conn = this.conectar();
             Statement stmt = conn.createStatement()) {
            stmt.execute(sql);
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

     public void guardarNotaParaFecha(String fecha, String nota) {
        String sql = "INSERT INTO eventos(usuario, fecha, nota) VALUES(?, ?, ?) " +
                     "ON CONFLICT(usuario, fecha) DO UPDATE SET nota=?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuarioActual);
            pstmt.setString(2, fecha);
            pstmt.setString(3, nota);
            pstmt.setString(4, nota);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String obtenerNotaParaFecha(String fecha) {
        String sql = "SELECT nota FROM eventos WHERE usuario = ? AND fecha = ?";
        String nota = "";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuarioActual);
            pstmt.setString(2, fecha);
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                nota = rs.getString("nota");
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return nota;
    }

     public void eliminarNotaParaFecha(String fecha) {
        String sql = "DELETE FROM eventos WHERE usuario = ? AND fecha = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuarioActual);
            pstmt.setString(2, fecha);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
     public List<String> obtenerNotasDelUsuario() {
        List<String> notas = new ArrayList<>();
        String sql = "SELECT fecha, nota FROM eventos WHERE usuario = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, usuarioActual);
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                notas.add(rs.getString("fecha") + ": " + rs.getString("nota"));
            }
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }

        return notas;
    }
}