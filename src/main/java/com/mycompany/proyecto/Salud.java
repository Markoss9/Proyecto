package com.mycompany.proyecto;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.ResultSet;

public class Salud {
    private static int id;
    private int dni;
    private float peso;
    private float altura;
    private float caloriasconsumidas;
    private float caloriasquemadas;

    
    public Salud (float peso, float altura){
        this.peso = peso;
        this.altura = altura;
    }

    public Salud(int id) {
        this.id = id;
    }
    
    

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getCaloriasconsumidas() {
        return caloriasconsumidas;
    }

    public void setCaloriasconsumidas(float caloriasconsumidas) {
        this.caloriasconsumidas = caloriasconsumidas;
    }

    public float getCaloriasquemadas() {
        return caloriasquemadas;
    }

    public void setCaloriasquemadas(float caloriasquemadas) {
        this.caloriasquemadas = caloriasquemadas;
    }

    public void agregarCalorias(float calorias){
        this.caloriasconsumidas = this.caloriasconsumidas + calorias;
    }
    
    public float calcularIMC(){
        float imc = this.peso / (this.altura*this.altura);
        return imc;
    }
    
    public String evaluarIMC(){
        
        float imc = calcularIMC();
        
        if (imc<=18.5){
            return ("Bajo");
        }else if (imc>18.5 & imc<=24.9){
            return ("Normal");
        }else if (imc>24.9 & imc<=29.9){
            return ("Sobrepeso");
        }else {
            return ("Obesidad");
        }
    }
      
    
    public void crearTablaSalud(){
        Connection conn = null;
        Statement stmt = null;
        ResultSet rs = null;

        try {
            
            conn = Conexion.getConnection();

            
            String checkTableSQL = "SELECT name FROM sqlite_master WHERE type='table' AND name='salud';";
            stmt = conn.createStatement();
            rs = stmt.executeQuery(checkTableSQL);

            if (rs.next()) {
                
                System.out.println("La tabla 'salud' ya existe.");
            } else {
                
                String createTableSQL = "CREATE TABLE salud ("
                        + "id INTEGER PRIMARY KEY AUTOINCREMENT, "
                        + "dni INTEGER, "
                        + "peso REAL NOT NULL, "
                        + "altura REAL NOT NULL, "
                        + "calConsumidas REAL NOT NULL, "
                        + "calQuemadas REAL NOT NULL"
                        + ");";
                
                
                stmt.executeUpdate(createTableSQL);
                System.out.println("La tabla 'salud' ha sido creada correctamente.");
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
    
    
    public void guardarDatosPesoyAltura(int dni) {
        String sql = "INSERT INTO salud (dni, peso, altura, calConsumidas, calQuemadas) VALUES (?, ?, ?, ?, ?)";
        try (   Connection conn = Conexion.getConnection();
                PreparedStatement pstmt = conn.prepareStatement(sql, PreparedStatement.RETURN_GENERATED_KEYS)) {
            
            pstmt.setInt(1, dni);
            pstmt.setFloat(2, this.peso);
            pstmt.setFloat(3, this.altura);
            pstmt.setFloat(4, 0);
            pstmt.setFloat(5, 0);
            pstmt.executeUpdate();
            
            System.out.println("Peso y altura guardados correctamente");
            
            var key = pstmt.getGeneratedKeys();
            
            if (key.next()){
                this.id = key.getInt(1);
                System.out.println(this.id);
            }
        }catch (SQLException e) {
            System.out.println("Error al guardar peso y altura: " + e.getMessage());
        }
    }
    
    public void guardarCalorias(float calCon, float calQue){
        String sql = "UPDATE salud SET calConsumidas = ?, calQuemadas = ? WHERE id = ?";
        
        try(Connection conn = Conexion.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql)){
            
            pstmt.setFloat(1, calCon);
            pstmt.setFloat(2, calQue);
            pstmt.setFloat(3, this.id);
            
            pstmt.executeUpdate();
            System.out.println("Calorias Actualizadas");
            System.out.println(this.id);
            
        }catch (SQLException e) {
            System.out.println("Error al guardar calorias: " + e.getMessage());
        }
    }
}
