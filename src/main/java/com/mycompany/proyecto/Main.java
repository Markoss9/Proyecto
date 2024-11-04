
package com.mycompany.proyecto;

import Paneles.Login;
import java.sql.*;

public class Main {
    
public static void main(String[] args) {
    // Crear conexion a base de datos
    Conexion conexion = new Conexion();
    
    
    // Crear la interfaz Login
    Login login = new Login();
    login.setVisible(true);
    login.setLocationRelativeTo(null);
    
    
    
    
  }
}
