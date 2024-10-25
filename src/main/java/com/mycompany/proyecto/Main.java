
package com.mycompany.proyecto;

import igu.Login;
import java.sql.*;
import igu.*;

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
