package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class Finanzas {

    private String dni;
    private float ingreso;
    private float gastos;
    private float saldo;

    // Constructor sin parámetros
    public Finanzas() {
        this.dni = ""; // Valor por defecto
        this.ingreso = 0.0f;
        this.gastos = 0.0f;
        this.saldo = 0.0f;
    }

    // Constructor con parámetros
    public Finanzas(String dni, float ingreso, float gastos, float saldo) {
        this.dni = dni;
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo;
    }

    // Getters y Setters
    public String getDni() {
        return dni;
    }

    public void setDni(String dni) {
        this.dni = dni;
    }

    public float getIngreso() {
        return ingreso;
    }

    public void setIngreso(float ingreso) {
        this.ingreso = ingreso;
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos) {
        this.gastos = gastos;
    }

    public float getSaldo() {
        return saldo;
    }

    public void setSaldo(float saldo) {
        this.saldo = saldo;
    }

    // Método para agregar ingreso
    public void agregarIngreso(float nuevoIngreso, Connection connection) throws SQLException {
        this.ingreso += nuevoIngreso;
        this.saldo += nuevoIngreso;
        actualizarFinanzas(connection);
    }

    // Método para agregar gasto
    public void agregarGasto(float nuevoGasto, Connection connection) throws SQLException {
        this.gastos += nuevoGasto;
        this.saldo -= nuevoGasto;
        actualizarFinanzas(connection);
    }

    // Método para guardar los datos de Finanzas en la base de datos
    public void guardarFinanzas(Connection connection) throws SQLException {
        this.saldo = obtenerBalance();
        String sql = "INSERT INTO finanzas (dni, ingreso, gastos, saldo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, this.dni);
            pstmt.setFloat(2, this.ingreso);
            pstmt.setFloat(3, this.gastos);
            pstmt.setFloat(4, this.saldo);
            pstmt.executeUpdate();
        }
    }

    // Método para obtener los datos de Finanzas desde la base de datos
    public static Finanzas obtenerFinanzas(Connection connection, String dni) throws SQLException {
        String sql = "SELECT * FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Finanzas(
                        rs.getString("dni"),
                        rs.getFloat("ingreso"),
                        rs.getFloat("gastos"),
                        rs.getFloat("saldo")
                );
            }
        }
        return null;
    }

    // Método para actualizar los datos de Finanzas en la base de datos
    public void actualizarFinanzas(Connection connection) throws SQLException {
        this.saldo = obtenerBalance();
        String sql = "UPDATE finanzas SET ingreso = ?, gastos = ?, saldo = ? WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, this.ingreso);
            pstmt.setFloat(2, this.gastos);
            pstmt.setFloat(3, this.saldo);
            pstmt.setString(4, this.dni);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar los datos de Finanzas de la base de datos
    public void eliminarFinanzas(Connection connection) throws SQLException {
        String sql = "DELETE FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setString(1, this.dni);
            pstmt.executeUpdate();
        }
    }

    // Método para listar todas las Finanzas
    public static ArrayList<Finanzas> listarFinanzas(Connection connection) throws SQLException {
        ArrayList<Finanzas> listaFinanzas = new ArrayList<>();
        String sql = "SELECT * FROM finanzas";
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Finanzas finanzas = new Finanzas(
                        rs.getString("dni"),
                        rs.getFloat("ingreso"),
                        rs.getFloat("gastos"),
                        rs.getFloat("saldo")
                );
                listaFinanzas.add(finanzas);
            }
        }
        return listaFinanzas;
    }

    // Método para calcular el balance financiero
    public float obtenerBalance() {
        return this.ingreso - this.gastos;
    }

    // Método para obtener el total de ingresos
    public static float obtenerTotalIngresos(Connection connection) throws SQLException {
        float total = 0;
        String sql = "SELECT SUM(ingreso) AS totalIngresos FROM finanzas";
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getFloat("totalIngresos");
            }
        }
        return total;
    }

    // Método para obtener el total de gastos
    public static float obtenerTotalGastos(Connection connection) throws SQLException {
        float total = 0;
        String sql = "SELECT SUM(gastos) AS totalGastos FROM finanzas";
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            if (rs.next()) {
                total = rs.getFloat("totalGastos");
            }
        }
        return total;
    }

    // Método para resetear los valores financieros
    public void resetearFinanzas(Connection connection) throws SQLException {
        this.ingreso = 0;
        this.gastos = 0;
        this.saldo = 0;
        actualizarFinanzas(connection);
    }

    @Override
    public String toString() {
        return "Finanzas{"
                + "dni=" + dni
                + ", ingreso=" + ingreso
                + ", gastos=" + gastos
                + ", saldo=" + saldo
                + '}';
    }
}
