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

public class Finanzas {

    private int id;
    private float ingreso;
    private float gastos;
    private float saldo;

    // Constructor
    public Finanzas(int id, float ingreso, float gastos, float saldo) {
        this.id = id;
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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

    // Método para guardar los datos de Finanzas en la base de datos
    public void guardarFinanzas(Connection connection) throws SQLException {
        String sql = "INSERT INTO finanzas (id, ingreso, gastos, saldo) VALUES (?, ?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.setFloat(2, this.ingreso);
            pstmt.setFloat(3, this.gastos);
            pstmt.setFloat(4, this.saldo);
            pstmt.executeUpdate();
        }
    }

    // Método para obtener los datos de Finanzas desde la base de datos
    public static Finanzas obtenerFinanzas(Connection connection, int id) throws SQLException {
        String sql = "SELECT * FROM finanzas WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Finanzas(
                        rs.getInt("id"),
                        rs.getFloat("ingreso"),
                        rs.getFloat("gastos"),
                        rs.getFloat("saldo")
                );
            }
        }
        return null; // O lanza una excepción si no se encuentra
    }

    // Método para actualizar los datos de Finanzas en la base de datos
    public void actualizarFinanzas(Connection connection) throws SQLException {
        String sql = "UPDATE finanzas SET ingreso = ?, gastos = ?, saldo = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, this.ingreso);
            pstmt.setFloat(2, this.gastos);
            pstmt.setFloat(3, this.saldo);
            pstmt.setInt(4, this.id);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar los datos de Finanzas de la base de datos
    public void eliminarFinanzas(Connection connection) throws SQLException {
        String sql = "DELETE FROM finanzas WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.id);
            pstmt.executeUpdate();
        }
    }

    // Método para listar todas las Finanzas
    public static ArrayList<Finanzas> listarFinanzas(Connection connection) throws SQLException {
        ArrayList<Finanzas> listaFinanzas = new ArrayList<>(); // Definimos un ArrayList directamente
        String sql = "SELECT * FROM finanzas";
        try (PreparedStatement pstmt = connection.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {
            while (rs.next()) {
                Finanzas finanzas = new Finanzas(
                        rs.getInt("id"),
                        rs.getFloat("ingreso"),
                        rs.getFloat("gastos"),
                        rs.getFloat("saldo")
                );
                listaFinanzas.add(finanzas); // Añadimos el objeto Finanzas al ArrayList
            }
        }
        return listaFinanzas; // Retornamos el ArrayList
    }

    // Metodo to string para facilitar la impresion
    @Override
    public String toString() {
        return "Finanzas{"
                + "id=" + id
                + ", ingreso=" + ingreso
                + ", gastos=" + gastos
                + ", saldo=" + saldo
                + '}';
    }

    // Método para agregar ingreso
    public void agregarIngreso(float nuevoIngreso, Connection connection) throws SQLException {
        this.ingreso += nuevoIngreso;
        this.saldo += nuevoIngreso; // Actualizamos el saldo
        actualizarFinanzas(connection); // Guardamos los cambios en la base de datos
    }

    // Método para agregar gasto
    public void agregarGasto(float nuevoGasto, Connection connection) throws SQLException {
        this.gastos += nuevoGasto;
        this.saldo -= nuevoGasto; // Actualizamos el saldo
        actualizarFinanzas(connection); // Guardamos los cambios en la base de datos
    }

    // Método para el balance financiero
    public float obtenerBalance() {
        return this.ingreso - this.gastos; // También puedes usar saldo directamente
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
    actualizarFinanzas(connection); // Guardamos los cambios en la base de datos
}

}
