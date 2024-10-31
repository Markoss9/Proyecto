/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import java.sql.*;

public class GestorDeBaseDeDatos {
    
       public GestorDeBaseDeDatos() {
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
        String sql = "INSERT INTO eventos(fecha, nota) VALUES(?, ?) " +
                     "ON CONFLICT(fecha) DO UPDATE SET nota=?";
        
        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fecha);
            pstmt.setString(2, nota);
            pstmt.setString(3, nota);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }

    public String obtenerNotaParaFecha(String fecha) {
        String sql = "SELECT nota FROM eventos WHERE fecha = ?";
        String nota = "";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fecha);
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
        String sql = "DELETE FROM eventos WHERE fecha = ?";

        try (Connection conn = this.conectar();
             PreparedStatement pstmt = conn.prepareStatement(sql)) {
            pstmt.setString(1, fecha);
            pstmt.executeUpdate();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
        }
    }
}

