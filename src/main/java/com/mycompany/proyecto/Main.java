
package com.mycompany.proyecto;
import javax.swing.SwingUtilities;
import Paneles.Login;
import java.sql.*;

public class Main {
    
public static void main(String[] args) {
    // Crear conexion a base de datos
    Conexion conexion = new Conexion();
    Salud salud = new Salud(0);
    salud.crearTablaSalud();
    Metas metas = new Metas();
    metas.crearTablaMetas();
    Contactos contactos = new Contactos(0);
    contactos.crearTablaContactos();
    
    // Crear la interfaz Login
    Login login = new Login();
    login.setVisible(true);
    login.setLocationRelativeTo(null);
    
    
  }
}
