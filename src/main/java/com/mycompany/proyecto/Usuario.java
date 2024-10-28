
package com.mycompany.proyecto;

import static com.mycompany.proyecto.Conexion.getConnection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;


public class Usuario {
    private String nombre;
    private String correo;
    private String contraseña;

    // Constructor
    public Usuario(String nombre, String correo, String contraseña) {
        this.nombre = nombre;
        this.correo = correo;
        this.contraseña = contraseña;
    }

    // Getters y Setters

    public String getNombre() {
        return nombre;
    }

    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getCorreo() {
        return correo;
    }

    public void setCorreo(String correo) {
        this.correo = correo;
    }

    public String getContraseña() {
        return contraseña;
    }

    public void setContraseña(String contraseña) {
        this.contraseña = contraseña;
    }
    //-------------------------------------------------------------------------
    public void CrearUsuario(){
  
    }
    //-------------------------------------------------------------------------
    public static boolean validarUsuario(String dni, String contrasena) {
        String sql = "SELECT * FROM usuarios WHERE dni = ? AND contrasena = ?";
        try (PreparedStatement stmt = getConnection().prepareStatement(sql)) {
            stmt.setString(1, dni.trim());
            stmt.setString(2, contrasena.trim());
            ResultSet rs = stmt.executeQuery();
            if (rs.next()) {
                System.out.println("Usuario encontrado: " + rs.getString("dni"));
                return true; // Usuario encontrado
            } else {
                System.out.println("Usuario no encontrado o datos incorrectos.");
                return false; // No se encontró al usuario
            }
        } catch (SQLException e) {
            System.out.println("Error al validar usuario: " + e.getMessage());
            return false;
        }
    }
}

