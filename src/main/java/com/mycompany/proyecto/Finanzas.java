package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Finanzas {

    private int dni;
    private float ingreso;
    private float gastos;
    private float saldo;

    // Constructor sin parámetros
    public Finanzas() {
        this.dni = 0; // Valor por defecto
        this.ingreso = 0.0f;
        this.gastos = 0.0f;
        this.saldo = 0.0f;
    }

    // Constructor con parámetros
    public Finanzas(int dni, float ingreso, float gastos) {
        this.dni = dni;
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = 0; // Inicializamos saldo a 0, lo calcularemos después
    }

    // Getters y Setters
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public float getIngreso() {
        return ingreso;
    }

    public void setIngreso(float ingreso, Connection connection) throws SQLException {
        this.ingreso = ingreso;
        actualizarFinanzas(connection); // Actualiza los datos en la base de datos
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos, Connection connection) throws SQLException {
        this.gastos = gastos;
        actualizarFinanzas(connection); // Actualiza los datos en la base de datos
    }

    public float getSaldo() {
        return saldo;
    }

    // Método para guardar los datos de Finanzas en la base de datos
    public void guardarFinanzas(Connection connection) throws SQLException {
        String sql = "INSERT INTO finanzas (dni, ingreso, gastos) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.dni);
            pstmt.setFloat(2, this.ingreso);
            pstmt.setFloat(3, this.gastos);
            pstmt.executeUpdate(); // Ejecutamos la inserción
            JOptionPane.showMessageDialog(null, "Las finanzas se han agregado correctamente.");
        }
    }

    // Método para eliminar los datos de Finanzas de la base de datos
    public static void eliminarFinanza(Connection connection, int dni) throws SQLException {
        String sql = "DELETE FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dni);
            pstmt.executeUpdate();
        }
    }

    // Método para listar todas las Finanzas
    public static ArrayList<Finanzas> listarFinanzas(Connection connection) throws SQLException {
        ArrayList<Finanzas> listaFinanzas = new ArrayList<>();
        String sql = "SELECT * FROM finanzas";
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                int dni = rs.getInt("dni");
                float ingreso = rs.getFloat("ingreso");
                float gastos = rs.getFloat("gastos");
                Finanzas finanzas = new Finanzas(dni, ingreso, gastos);
                finanzas.saldo = obtenerBalancePorDNI(connection, dni); // Actualiza el saldo
                listaFinanzas.add(finanzas);
            }
        }
        return listaFinanzas;
    }

    // Método para obtener el balance del usuario por su DNI
    public static float obtenerBalancePorDNI(Connection connection, int dni) throws SQLException {
        String sql = "SELECT (SUM(ingreso) - SUM(gastos)) AS saldo FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dni);
            try (ResultSet rs = pstmt.executeQuery()) {
                if (rs.next()) {
                    return rs.getFloat("saldo");
                }
            }
        }
        return 0; // Retorna 0 si no se encuentra saldo
    }

    // Método para actualizar finanzas en la base de datos
    public void actualizarFinanzas(Connection connection) throws SQLException {
        String sql = "UPDATE finanzas SET ingreso = ?, gastos = ? WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, this.ingreso);
            pstmt.setFloat(2, this.gastos);
            pstmt.setInt(3, this.dni);
            pstmt.executeUpdate();
            JOptionPane.showMessageDialog(null, "Finanzas actualizadas correctamente.");
        }
    }
}
