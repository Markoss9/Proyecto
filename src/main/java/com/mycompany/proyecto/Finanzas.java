package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Finanzas {

    private int dni;
    private Identificador identificador; // Composición
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

    // Constructor que acepta solo dni, ingreso, gastos
    public Finanzas(int dni, float ingreso, float gastos) {
        this.dni = dni;
        this.ingreso = ingreso;
        this.gastos = gastos;
    }

    // Constructor que acepta solo dni, id, ingreso y gastos
    public Finanzas(int dni, int id, float ingreso, float gastos) {
        this.dni = dni;
        this.identificador = new Identificador(id, ""); // O establecer valores predeterminados
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo;
    }

    // Constructor que acepta solo dni, id, ingreso, gastos, saldo
    public Finanzas(int dni, int id, float ingreso, float gastos, float saldo) {
        this.dni = dni;
        this.identificador = new Identificador(id, ""); // Establecer valores predeterminados
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo;
    }

    // Constructor completo
    public Finanzas(int dni, int id, String fecha, float ingreso, float gastos) {
        this.dni = dni;
        this.identificador = new Identificador(id, fecha);
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo;
    }

    // Getters y Setters
    public int getDni() {
        return dni;
    }

    public void setDni(int dni) {
        this.dni = dni;
    }

    public int getId() {
        return identificador.getId();
    }

    public void setId(int id) {
        identificador.setId(id);
    }

    public String getFecha() {
        return identificador.getFecha();
    }

    public void setFecha(String fecha) {
        identificador.setFecha(fecha);
    }

    // Método getter para el identificador
    public Identificador getIdentificador() {
        return this.identificador;
    }

    public float getIngreso() {
        return ingreso;
    }

    public void setIngreso(float ingreso, Connection connection) throws SQLException {
        this.ingreso = ingreso;
        actualizarFinanza(connection); // Actualiza los datos en la base de datos
    }

    public float getGastos() {
        return gastos;
    }

    public void setGastos(float gastos, Connection connection) throws SQLException {
        this.gastos = gastos;
        actualizarFinanza(connection); // Actualiza los datos en la base de datos
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

    // Metodo para listar las finanzas del usuario que ha iniciado sesión
    public static ArrayList<Finanzas> listarFinanzas(Connection connection, int dniUsuario) throws SQLException {
        ArrayList<Finanzas> listaFinanzas = new ArrayList<>();
        String sql = "SELECT id, dni, ingreso, gastos, saldo FROM finanzas WHERE dni = ?"; // Filtrar por dni

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario); // Pasar el dni del usuario
            ResultSet rs = pstmt.executeQuery();

            while (rs.next()) {
                int id = rs.getInt("id");
                int dni = rs.getInt("dni");
                float ingreso = rs.getFloat("ingreso");
                float gastos = rs.getFloat("gastos");
                float saldo = rs.getFloat("saldo"); // Obtener saldo directamente de la base de datos

                Finanzas finanza = new Finanzas(dni, id, ingreso, gastos, saldo); // Usar constructor con saldo
                listaFinanzas.add(finanza);
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
    public void actualizarFinanza(Connection connection) throws SQLException {
        String sql = "UPDATE finanzas SET ingreso = ?, gastos = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, this.ingreso); // Asignamos el nuevo ingreso
            pstmt.setFloat(2, this.gastos);   // Asignamos los nuevos gastos
            System.out.println("Actualizando finanza con ID: " + this.identificador.getId());
            pstmt.setInt(3, this.identificador.getId()); // Usamos el id para buscar la fila

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Las finanzas se han actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la finanza a actualizar.");
            }
        }
    }

    // Método en la clase Finanzas para buscar la finanza solo si el ID y el DNI coinciden
    public static Finanzas buscarFinanzaPorId(Connection connection, int id, int dniUsuario) throws SQLException {
        String sql = "SELECT id, dni, ingreso, gastos, saldo FROM finanzas WHERE id = ? AND dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, dniUsuario); // Solo permite buscar la finanza si el DNI coincide
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int dni = rs.getInt("dni");
                float ingreso = rs.getFloat("ingreso");
                float gastos = rs.getFloat("gastos");
                float saldo = rs.getFloat("saldo");

                return new Finanzas(dni, id, ingreso, gastos, saldo); // Constructor de finanza
            }
        }
        return null; // Retorna null si no se encuentra ninguna finanza
    }

}
