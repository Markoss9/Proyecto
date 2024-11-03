package Paneles;

import paneles.EditarFinanzasDialog;
import com.mycompany.proyecto.Finanzas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ActualizarFinanzasPanel extends javax.swing.JFrame {

    private Connection conexion;

    public ActualizarFinanzasPanel(Connection conexion) {
        initComponents();
        this.conexion = conexion;
        cargarDatosFinanzas();  // Llama al método para cargar datos
        agregarBotonEliminar();  // Agrega botones de eliminar
        agregarBotonEditar();  // Agrega botones de editar
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        scrollTablaFinanzas = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTableFInanzas = new javax.swing.JTable();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTableFInanzas.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null},
                {null, null, null, null}
            },
            new String [] {
                "dni", "ingresos", "gastos", "saldo"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Float.class, java.lang.Float.class, java.lang.Float.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTableFInanzas);

        scrollTablaFinanzas.setViewportView(jScrollPane1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addGap(0, 0, Short.MAX_VALUE)
                .addComponent(scrollTablaFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 481, javax.swing.GroupLayout.PREFERRED_SIZE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(scrollTablaFinanzas, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    public void cargarDatosFinanzas() {
        String sql = "SELECT dni, ingreso, gastos, saldo FROM finanzas"; // Cambia "ingresos" por "ingreso"
        try (PreparedStatement pstmt = conexion.prepareStatement(sql); ResultSet rs = pstmt.executeQuery()) {

            DefaultTableModel model = (DefaultTableModel) jTableFInanzas.getModel();
            model.setRowCount(0); // Limpia la tabla

            while (rs.next()) {
                int dni = rs.getInt("dni");
                float ingreso = rs.getFloat("ingreso"); // Cambia "ingresos" por "ingreso"
                float gastos = rs.getFloat("gastos");
                float saldo = rs.getFloat("saldo");
                model.addRow(new Object[]{dni, ingreso, gastos, saldo}); // Agrega los datos al modelo
            }
        } catch (SQLException e) {
            e.printStackTrace(); // Esto imprimirá el error en la consola
            JOptionPane.showMessageDialog(this, "Error al cargar datos de finanzas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para agregar un botón de eliminar en cada fila
    private void agregarBotonEliminar() {
        ButtonColumn buttonColumn = new ButtonColumn(jTableFInanzas, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jTableFInanzas.getSelectedRow();
                if (row >= 0) {
                    int dni = (Integer) jTableFInanzas.getValueAt(row, 0);
                    eliminarFinanza(dni);
                }
            }
        }, 3); // 3, el indice maximo de las columnas de la tabla 
    }

// Método para eliminar finanza
    private void eliminarFinanza(int dni) {
        try {

            Finanzas.eliminarFinanza(conexion, dni);
            cargarDatosFinanzas();  // Se vuelven a cargar datos después de la eliminación
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al eliminar finanza: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    private void agregarBotonEditar() {
        ButtonColumn buttonColumn = new ButtonColumn(jTableFInanzas, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                int row = jTableFInanzas.getSelectedRow();
                if (row >= 0) {
                    int dni = (Integer) jTableFInanzas.getValueAt(row, 0);
                    float ingresos = (Float) jTableFInanzas.getValueAt(row, 1);
                    float gastos = (Float) jTableFInanzas.getValueAt(row, 2);

                    new EditarFinanzasDialog(ActualizarFinanzasPanel.this, dni, conexion, ingresos, gastos).setVisible(true);

                }
            }
        }, 3); // 3, el indice maximo de las columnas de la tabla 
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTable jTableFInanzas;
    private javax.swing.JScrollPane scrollTablaFinanzas;
    // End of variables declaration//GEN-END:variables
}
