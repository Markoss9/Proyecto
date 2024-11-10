/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;


public class Metas {
    private int id;
    private int dni;
    private String descripcion;
    private String fechaInicio;
    private String fechaLimite;
    private int completada;
    

    public Metas() {
    }
    
    public Metas(String descripcion,String fechalimite) {
        this.dni = dni;
        this.descripcion = descripcion;
        this.fechaInicio = "";
        this.fechaLimite = fechalimite;
        this.completada = 0;
    }

    public Metas(int id, String descripcion, String fechaInicio, String fechaLimite, int completada) {
        this.id = id;
        this.descripcion = descripcion;
        this.fechaInicio = fechaInicio;
        this.fechaLimite = fechaLimite;
        this.completada = completada;
    }
    
    
    
    
    public Metas(int id) {
        this.id = id;
    }
    
    
    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getDescripcion() {
        return descripcion;
    }

    public void setDescripcion(String descripcion) {
        this.descripcion = descripcion;
    }


    public String getProgreso() {
        if (completada == 0){
            String progreso = "incompleto";
            return progreso;
        }else{
            String progreso = "completo";
            return progreso;
        }
    }

    public static void setCompletada(int completada) {
        completada = completada;
    }

    public String getFechaInicio() {
        return fechaInicio;
    }

    public void setFechaInicio(String fechaInicio) {
        this.fechaInicio = fechaInicio;
    }

    public String getFechaLimite() {
        return fechaLimite;
    }

    public void setFechaLimite(String fechaLimite) {
        this.fechaLimite = fechaLimite;
    }
    
    public void guardarMeta(int numdni, String descripcion, String fechalimite){
        String sql = "INSERT INTO metas (dni ,descripcion, fechaInicio, fechaLimite, completada) VALUES (?, ?, ?, ?, ?)";
        try(Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setInt(1, numdni);
            pstmt.setString(2, descripcion);
            pstmt.setString(3, this.fechaInicio);
            pstmt.setString(4, fechalimite);
            pstmt.setInt(5, this.completada);
            
            pstmt.executeUpdate();
            System.out.println("Datos de contacto subidos con exito");
            
            
        } catch (SQLException e) {
            System.out.println("Error datos: " + e.getMessage());
        }    
    }
    
    public static boolean eliminarMetas(int id){
        String sql = "DELETE FROM metas WHERE id = ?";
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
    
    public static ArrayList<Metas> listarMetas(int dni)throws SQLException{
        ArrayList<Metas> listaMetas = new ArrayList<>();
        String sql = "SELECT * FROM metas WHERE dni = ?";
        
        try(Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            
            while(rs.next()){
                
                int Id = rs.getInt("id");
                String descripcion = rs.getString("descripcion");
                String fechainicio = rs.getString("fechaInicio");
                String fechafinal = rs.getString("fechaLimite");
                int completada = rs.getInt("completada");
                
                Metas metas = new Metas(Id, descripcion, fechainicio, fechafinal, completada);
                listaMetas.add(metas);
                
            }
            
        }
        
        return listaMetas;
    }
    
    public static boolean actualizarProgreso(int id, int progreso) {
        String sql = "UPDATE metas SET completada = ? WHERE id = ?";
        boolean actualizado = false;

        try (Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)) {
 
            pstmt.setInt(1, progreso);
            pstmt.setInt(2, id);
 
            int filasAfectadas = pstmt.executeUpdate();
            actualizado = (filasAfectadas > 0); 

        } catch (SQLException e) {
            System.out.println(e);
        }

        return actualizado;
    }
    
    public void crearTablaMetas(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            
            conn = Conexion.getConnection();

            
            String checkTableSQL = "SELECT name FROM sqlite_master WHERE type='table' AND name='metas';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(checkTableSQL);

            if (rs.next()) {
                
                System.out.println("La tabla 'metas' ya existe.");
            } else {
                
                String createTableSQL = "CREATE TABLE metas ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "dni INTEGER, "
                        + "descripcion TEXT NOT NULL, "
                        + "fechaInicio TEXT NOT NULL, "
                        + "fechaLimite TEXT NOT NULL, "
                        + "completada INTEGER NOT NULL"
                        + ");";
                
                
                stmt.executeUpdate(createTableSQL);
                System.out.println("La tabla 'metas' ha sido creada correctamente.");
            }

        } catch (SQLException e) {
            
            System.out.println("Error al verificar o crear la tabla: " + e.getMessage());
        } finally {
            try {
                if (rs != null) rs.close();
                if (stmt != null) stmt.close();
                
            } catch (SQLException se) {
                System.out.println("Error al cerrar recursos: " + se.getMessage());
            }
        }
    }
    
}

