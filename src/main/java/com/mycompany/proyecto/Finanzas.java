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
    public Finanzas(int dni, float ingreso, float gastos, Connection connection) throws SQLException {
        this.dni = dni;
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = obtenerBalancePorDNI(connection, dni); // Calcula el saldo automáticamente al crear la instancia
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
        this.saldo = obtenerBalancePorDNI(connection, this.dni); // Actualiza el saldo al establecer ingreso
        actualizarFinanzas(connection); // Actualiza los datos en la base de datos
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos, Connection connection) throws SQLException {
        this.gastos = gastos;
        this.saldo = obtenerBalancePorDNI(connection, this.dni); // Actualiza el saldo al establecer gastos
        actualizarFinanzas(connection); // Actualiza los datos en la base de datos
    }

    public float getSaldo() {
        return saldo;
    }

    // Método para agregar ingreso
    public void agregarIngreso(float nuevoIngreso, Connection connection) throws SQLException {
        this.ingreso += nuevoIngreso;
        this.saldo = obtenerBalancePorDNI(connection, this.dni); // Actualiza el saldo desde la base de datos
        actualizarFinanzas(connection);
    }

    // Método para agregar gasto
    public void agregarGasto(float nuevoGasto, Connection connection) throws SQLException {
        this.gastos += nuevoGasto;
        this.saldo = obtenerBalancePorDNI(connection, this.dni); // Actualiza el saldo desde la base de datos
        actualizarFinanzas(connection);
    }

    // Método para guardar los datos de Finanzas en la base de datos
    public void guardarFinanzas(Connection connection) throws SQLException {
        if (dniYaExiste(connection, this.dni)) {
            int confirm = JOptionPane.showConfirmDialog(null,
                    "El DNI ingresado ya existe. ¿Desea actualizar los datos de finanzas?",
                    "Confirmar actualización",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                actualizarFinanzas(connection);
                JOptionPane.showMessageDialog(null, "Las finanzas se han actualizado correctamente.");
            }
        } else {
            // Si el DNI no existe, insertamos una nueva entrada
            String sql = "INSERT INTO finanzas (dni, ingreso, gastos) VALUES (?, ?, ?)";
            try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
                pstmt.setInt(1, this.dni);
                pstmt.setFloat(2, this.ingreso);
                pstmt.setFloat(3, this.gastos);
                pstmt.executeUpdate();
                JOptionPane.showMessageDialog(null, "Las finanzas se han agregado correctamente.");
            }
        }
    }

    private boolean dniYaExiste(Connection connection, int dni) throws SQLException {
        String sql = "SELECT COUNT(*) FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            return rs.getInt(1) > 0; // Retorna true si existe al menos un registro
        }
    }

    // Método para obtener los datos de Finanzas desde la base de datos
    public static Finanzas obtenerFinanzas(Connection connection, int dni) throws SQLException {
        String sql = "SELECT * FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return new Finanzas(
                        rs.getInt("dni"),
                        rs.getFloat("ingreso"),
                        rs.getFloat("gastos"),
                        connection // Necesitamos pasar la conexión para calcular el saldo
                );
            }
        }
        return null; // Retorna null si no se encuentra el DNI
    }

    // Método para actualizar los datos de Finanzas en la base de datos
    public void actualizarFinanzas(Connection connection) throws SQLException {
        String sql = "UPDATE finanzas SET ingreso = ?, gastos = ? WHERE dni = ?"; // Omitir 'saldo'
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, this.ingreso);
            pstmt.setFloat(2, this.gastos);
            pstmt.setInt(3, this.dni);
            pstmt.executeUpdate();
        }
    }

    // Método para eliminar los datos de Finanzas de la base de datos
    public void eliminarFinanzas(Connection connection) throws SQLException {
        String sql = "DELETE FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.dni);
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
                        rs.getInt("dni"),
                        rs.getFloat("ingreso"),
                        rs.getFloat("gastos"),
                        connection // Necesitamos pasar la conexión para calcular el saldo
                );
                listaFinanzas.add(finanzas);
            }
        }
        return listaFinanzas;
    }

    // Método para obtener el balance del usuario por su DNI
    public static float obtenerBalancePorDNI(Connection connection, int dni) throws SQLException {
        String sql = "SELECT ingreso, gastos FROM finanzas WHERE dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dni);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                float ingreso = rs.getFloat("ingreso");
                float gastos = rs.getFloat("gastos");
                return ingreso - gastos; // Calcula el balance
            }
        }
        return 0; // Si no se encuentra el DNI, devuelve 0
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
