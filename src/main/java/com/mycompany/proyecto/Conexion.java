
package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

public class Conexion { 
    // Método que retorna la conexión
    public static Connection getConnection() {
        Connection conn = null;
        try {
            // Cargar el driver
            Class.forName("org.sqlite.JDBC");
            // Establecer la conexión
            conn = DriverManager.getConnection("jdbc:sqlite:test01.sqlite");

            if (conn != null) {
                System.out.println("Conexión a base de datos ... Ok");
            }
        } catch (SQLException a) {
            System.out.println("Error SQL: " + a.getMessage());
        } catch (ClassNotFoundException ex) {
            System.out.println("Error: Clase no encontrada " + ex.getMessage());
        } catch (Exception ex) {
            System.out.println("Error: " + ex.getMessage());
        }
        return conn; // Retornar la conexión
    }
    
    public static boolean validarUsuario(String nombre, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE nombre = ? AND contrasena = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, nombre);
            stmt.setString(2, contrasena);
            ResultSet rs = stmt.executeQuery();
            return rs.next(); // Retorna true si encuentra al usuario
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
            return false;
        }
    }        
}
