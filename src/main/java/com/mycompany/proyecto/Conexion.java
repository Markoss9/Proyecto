
package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.DriverManager;

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
                System.out.println("Conexion a base de datos ... Ok");
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
  
}
