package com.mycompany.proyecto;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;

public class Finanzas {

    private int dni;                   // DNI del usuario al que pertenecen las finanzas
    private Identificador identificador; // Composición para manejar el ID de la finanza
    private float ingreso;             // Ingreso del usuario
    private float gastos;              // Gastos del usuario
    private float saldo;               // Saldo calculado para el usuario

    // Constructor sin parámetros para inicializar valores por defecto
    public Finanzas() {
        this.dni = 0; // Valor por defecto
        this.ingreso = 0.0f;
        this.gastos = 0.0f;
        this.saldo = 0.0f;
    }

    // Constructor para inicializar finanzas con DNI, ingreso y gastos
    public Finanzas(int dni, float ingreso, float gastos) {
        this.dni = dni;
        this.ingreso = ingreso;
        this.gastos = gastos;
    }

    // Constructor para inicializar con DNI, ID, ingreso y gastos
    public Finanzas(int dni, int id, float ingreso, float gastos) {
        this.dni = dni;
        this.identificador = new Identificador(id); // Se asigna el ID a través de la clase Identificador
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo; // El saldo se calcula en otro método
    }

    // Constructor para inicializar con DNI, ID, ingreso, gastos y saldo
    public Finanzas(int dni, int id, float ingreso, float gastos, float saldo) {
        this.dni = dni;
        this.identificador = new Identificador(id);
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo;
    }

    // Constructor completo con DNI, ID, fecha, ingreso y gastos
    public Finanzas(int dni, int id, String fecha, float ingreso, float gastos) {
        this.dni = dni;
        this.identificador = new Identificador(id);
        this.ingreso = ingreso;
        this.gastos = gastos;
        this.saldo = saldo; // El saldo debe calcularse después de inicializar otros valores
    }

    // Getters y Setters para atributos de la clase
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

    public Identificador getIdentificador() {
        return this.identificador;
    }

    public float getIngreso() {
        return ingreso;
    }

    // Método para establecer ingreso y actualizar la base de datos
    public void setIngreso(float ingreso, Connection connection) throws SQLException {
        this.ingreso = ingreso;
        actualizarFinanza(connection); // Llama a la función para actualizar en base de datos
    }

    public float getGastos() {
        return gastos;
    }

    // Método para establecer gastos y actualizar la base de datos
    public void setGastos(float gastos, Connection connection) throws SQLException {
        this.gastos = gastos;
        actualizarFinanza(connection); // Llama a la función para actualizar en base de datos
    }

    public float getSaldo() {
        return saldo;
    }

    // Método para guardar las finanzas en la base de datos
    public void guardarFinanzas(Connection connection) throws SQLException {
        String sql = "INSERT INTO finanzas (dni, ingreso, gastos) VALUES (?, ?, ?)";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, this.dni);
            pstmt.setFloat(2, this.ingreso);
            pstmt.setFloat(3, this.gastos);
            pstmt.executeUpdate(); // Ejecuta la inserción en la base de datos
            JOptionPane.showMessageDialog(null, "Las finanzas se han agregado correctamente.");
        }
    }

    // Método para eliminar una finanza específica usando ID y DNI del usuario
    public static void eliminarFinanza(Connection connection, int id, int dniUsuario) throws SQLException {
        String sql = "DELETE FROM finanzas WHERE id = ? AND dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);          // ID de la finanza a eliminar
            pstmt.setInt(2, dniUsuario);   // DNI del usuario actual
            int filasEliminadas = pstmt.executeUpdate();

            // Verifica si se eliminó una fila y muestra mensaje de éxito
            if (filasEliminadas == 0) {
                throw new SQLException("No se encontró ninguna finanza para eliminar con el ID proporcionado y el DNI del usuario.");
            }
        }
    }

    // Método para listar las finanzas de un usuario específico
    public static ArrayList<Finanzas> listarFinanzas(Connection connection, int dniUsuario) throws SQLException {
        ArrayList<Finanzas> listaFinanzas = new ArrayList<>();
        String sql = "SELECT id, dni, ingreso, gastos, saldo FROM finanzas WHERE dni = ?"; // Filtra por DNI

        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario); // Asigna el DNI del usuario
            ResultSet rs = pstmt.executeQuery();

            // Crea objetos Finanzas para cada registro y los agrega a la lista
            while (rs.next()) {
                int id = rs.getInt("id");
                int dni = rs.getInt("dni");
                float ingreso = rs.getFloat("ingreso");
                float gastos = rs.getFloat("gastos");
                float saldo = rs.getFloat("saldo"); // Obtiene el saldo directamente

                Finanzas finanza = new Finanzas(dni, id, ingreso, gastos, saldo); // Usa constructor con saldo
                listaFinanzas.add(finanza);
            }
        }

        return listaFinanzas;
    }

    // Método para actualizar ingresos y gastos en la base de datos
    public void actualizarFinanza(Connection connection) throws SQLException {
        String sql = "UPDATE finanzas SET ingreso = ?, gastos = ? WHERE id = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setFloat(1, this.ingreso); // Actualiza ingreso
            pstmt.setFloat(2, this.gastos);   // Actualiza gastos
            System.out.println("Actualizando finanza con ID: " + this.identificador.getId());
            pstmt.setInt(3, this.identificador.getId()); // ID para localizar el registro

            int rowsAffected = pstmt.executeUpdate();
            if (rowsAffected > 0) {
                JOptionPane.showMessageDialog(null, "Las finanzas se han actualizado correctamente.");
            } else {
                JOptionPane.showMessageDialog(null, "No se encontró la finanza a actualizar.");
            }
        }
    }

    // Método para buscar una finanza específica usando ID y DNI del usuario
    public static Finanzas buscarFinanzaPorId(Connection connection, int id, int dniUsuario) throws SQLException {
        String sql = "SELECT id, dni, ingreso, gastos, saldo FROM finanzas WHERE id = ? AND dni = ?";
        try (PreparedStatement pstmt = connection.prepareStatement(sql)) {
            pstmt.setInt(1, id);
            pstmt.setInt(2, dniUsuario); // Permite búsqueda solo si el DNI coincide
            ResultSet rs = pstmt.executeQuery();

            if (rs.next()) {
                int dni = rs.getInt("dni");
                float ingreso = rs.getFloat("ingreso");
                float gastos = rs.getFloat("gastos");
                float saldo = rs.getFloat("saldo");

                return new Finanzas(dni, id, ingreso, gastos, saldo); // Crea el objeto Finanzas encontrado
            }
        }
        return null; // Retorna null si no se encuentra ninguna finanza
    }

    // Método para resetear las finanzas de un usuario específico
    public static void resetearFinanzas(Connection conexion, int dniUsuario) throws SQLException {
        String sql = "UPDATE finanzas SET ingreso = 0, gastos = 0 WHERE dni = ?";
        try (PreparedStatement pstmt = conexion.prepareStatement(sql)) {
            pstmt.setInt(1, dniUsuario);
            int filasAfectadas = pstmt.executeUpdate();
            System.out.println("Se han reseteado " + filasAfectadas + " registros de finanzas para el usuario con DNI: " + dniUsuario);
        }
    }
}
