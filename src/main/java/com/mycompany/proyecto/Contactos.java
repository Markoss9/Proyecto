/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Contactos {
    private int id;
    private int dni;
    private String nombre;
    private String apellido;
    private String telefono;
    private String email;

    public Contactos(int id, String nombre, String apellido, String telefono, String email) {
        this.id = id;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }
    
    
    
    
    public Contactos(int id, int dni,String nombre, String apellido, String telefono, String email) {
        this.id=id;
        this.dni=dni;
        this.nombre = nombre;
        this.apellido = apellido;
        this.telefono = telefono;
        this.email = email;
    }
    
    public Contactos(int id){
        this.id = id;
    }
    
    public String getNombre() {
        return nombre;
    }

    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }
    
    
    
    
    public void setNombre(String nombre) {
        this.nombre = nombre;
    }

    public String getApellido() {
        return apellido;
    }

    public void setApellido(String apellido) {
        this.apellido = apellido;
    }

    public String getTelefono() {
        return telefono;
    }

    public void setTelefono(String telefono) {
        this.telefono = telefono;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getId(){
        return this.id;
    }

    public void setId(int id) {
        this.id = id;
    }

  
    @Override
    public String toString() {
        return "Contactos{" + "nombre=" + nombre + ", apellido=" + apellido + ", telefono=" + telefono + ", email=" + email + '}';
    }
    
    public void guardarContacto(int dni, String nombre, String apellido, String email, String telefono){
        String sql = "INSERT INTO contactos (dni, nombre, apellido, correo, telefono) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, dni);
            pstmt.setString(2, nombre);
            pstmt.setString(3, apellido);
            pstmt.setString(4, email);
            pstmt.setString(5, telefono);
            pstmt.executeUpdate();
            System.out.println("Datos de contacto subidos con exito");
            
            
        } catch (SQLException e) {
            System.out.println("Error datos: " + e.getMessage());
        }    
    }
    
    public static boolean eliminarContactos(int id){
        String sql = "DELETE FROM contactos WHERE id = ?";
        try(Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
                
            pstmt.setInt(1, id);
            int filaAfectada = pstmt.executeUpdate();
            
            if (filaAfectada > 0) {
                System.out.println("Contacto eliminado con exito.");
                return true;
            } else {
                System.out.println("No se encontro un contacto con el ID especificado.");
                return false;
            }
        
        }catch (SQLException e){
                  System.out.println("Error al eliminar el contacto: " + e.getMessage());
                  return false;
            }
    }
    
    
    public static ArrayList<Contactos> listarContactos(int dni)throws SQLException{
        ArrayList<Contactos> listaContactos = new ArrayList<>();
        String sql = "SELECT * FROM contactos WHERE dni = ?";
        
        try(Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                
                int Id = rs.getInt("id");
                String Nombre = rs.getString("nombre");
                String Apellido = rs.getString("apellido");
                String Correo = rs.getString("correo");
                String Telefono = rs.getString("telefono");
                
                Contactos contacto = new Contactos(Id,Nombre, Apellido, Correo, Telefono);
                listaContactos.add(contacto);
                
            }
            
        }
        
        return listaContactos;
    }
    
    public static boolean actualizarContacto(int id, String nuevoNombre, String nuevoApellido,String nuevoTelefono, String nuevoCorreo) {
        String sql = "UPDATE contactos SET nombre = ?, apellido = ? ,telefono = ?, correo = ? WHERE id = ?";
        boolean actualizado = false;

        try (Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            pstmt.setString(1, nuevoNombre);
            pstmt.setString(2, nuevoApellido);
            pstmt.setString(3, nuevoTelefono);
            pstmt.setString(4, nuevoCorreo);
            pstmt.setInt(5, id);
 
            int filasAfectadas = pstmt.executeUpdate();
            actualizado = (filasAfectadas > 0); 

        } catch (SQLException e) {
            System.out.println(e);
        }

        return actualizado;
    }

    
    
    
}


