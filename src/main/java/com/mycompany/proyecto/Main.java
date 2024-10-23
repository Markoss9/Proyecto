/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Main.java to edit this template
 */
package com.mycompany.proyecto;

import java.sql.*;
import igu.*;

public class Main {

public static void main(String[] args) {
    Conexion conexion = new Conexion();
    conexion.conectar();
    
    
     Login login = new Login();
     login.setVisible(true);
     login.setLocationRelativeTo(null);
     
  }
}
