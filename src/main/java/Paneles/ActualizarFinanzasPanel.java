package Paneles;

import Paneles.EditarFinanzasDialog;
import com.mycompany.proyecto.Finanzas;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class ActualizarFinanzasPanel extends javax.swing.JFrame {

    private Connection conexion;
    private final int dniUsuario;

    public ActualizarFinanzasPanel(Connection conexion, int dniUsuario) {
        initComponents();
        this.conexion = conexion;
        this.dniUsuario = dniUsuario;
        cargarTablaFinanzas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEditarFinanzas = new javax.swing.JButton();
        btnVovler = new javax.swing.JButton();
        btnEliminarFinanzas = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "ID", "DNI", "INGRESOS", "GASTOS", "SALDO"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.Object.class, java.lang.Float.class, java.lang.Float.class, java.lang.Object.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane1.setViewportView(jTable1);

        jScrollPane2.setViewportView(jScrollPane1);

        btnEditarFinanzas.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEditarFinanzas.setText("EditarFinanza");
        btnEditarFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFinanzasActionPerformed(evt);
            }
        });

        btnVovler.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVovler.setText("Volver");
        btnVovler.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVovlerActionPerformed(evt);
            }
        });

        btnEliminarFinanzas.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEliminarFinanzas.setText("EliminarFinanza");
        btnEliminarFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarFinanzasActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditarFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 35, Short.MAX_VALUE)
                .addComponent(btnEliminarFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(35, 35, 35)
                .addComponent(btnVovler, javax.swing.GroupLayout.PREFERRED_SIZE, 170, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarFinanzas)
                    .addComponent(btnVovler)
                    .addComponent(btnEliminarFinanzas))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVovlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVovlerActionPerformed
        // Crea e instancia el panel Finanzas
        FinanzasPanel volverFinanzas = new FinanzasPanel(conexion, dniUsuario);
        volverFinanzas.setVisible(true); // Muestra el panel Finanzas
        volverFinanzas.setLocationRelativeTo(null); // Centra la ventana en la pantalla
        this.dispose(); // Cierra el panel ActualizarFinanzas
    }//GEN-LAST:event_btnVovlerActionPerformed

    // Método para el botón "Editar Finanzas"
    private void btnEditarFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFinanzasActionPerformed
        try {
            // Solicita al usuario el ID de la finanza que desea editar
            String inputId = JOptionPane.showInputDialog(this, "Ingrese el ID de la finanza a editar:");
            int id = Integer.parseInt(inputId);

            // Busca la finanza en la base de datos usando el DNI del usuario y el ID ingresado
            Finanzas finanza = Finanzas.buscarFinanzaPorId(conexion, id, dniUsuario);

            if (finanza != null) {
                // Abre el cuadro de diálogo de edición con los datos de la finanza
                EditarFinanzasDialog editarDialog = new EditarFinanzasDialog(this, id, dniUsuario, conexion, finanza.getIngreso(), finanza.getGastos());
                editarDialog.setVisible(true); // Muestra el diálogo de edición
            } else {
                // Si no se encuentra la finanza, muestra un mensaje de error
                JOptionPane.showMessageDialog(this, "No se encontró ninguna finanza con el ID proporcionado.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al buscar la finanza: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEditarFinanzasActionPerformed

    // Método para el botón "Eliminar Finanzas"
    private void btnEliminarFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarFinanzasActionPerformed
        try {
            // Solicita al usuario el ID de la finanza que desea eliminar
            String inputId = JOptionPane.showInputDialog(this, "Ingrese el ID de la finanza a eliminar:");

            if (inputId != null && !inputId.trim().isEmpty()) {
                int id = Integer.parseInt(inputId);

                // Verifica que la finanza pertenezca al usuario actual
                Finanzas finanza = Finanzas.buscarFinanzaPorId(conexion, id, dniUsuario);

                if (finanza != null) {
                    // Confirma la eliminación
                    int confirmacion = JOptionPane.showConfirmDialog(this,
                            "¿Está seguro que desea eliminar la finanza con ID " + id + "?",
                            "Confirmar eliminación", JOptionPane.YES_NO_OPTION);

                    if (confirmacion == JOptionPane.YES_OPTION) {
                        // Elimina la finanza de la base de datos
                        Finanzas.eliminarFinanza(conexion, id, dniUsuario);
                        JOptionPane.showMessageDialog(this, "Finanza eliminada exitosamente.");
                        cargarTablaFinanzas(); // Recarga la tabla para actualizar la vista
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "La finanza no pertenece a su usuario o no existe.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un ID válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese un ID válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al eliminar la finanza: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnEliminarFinanzasActionPerformed

    // Método para cargar los datos de finanzas en la tabla jTable1
    public void cargarTablaFinanzas() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos

        try {
            // Llama al método listarFinanzas y agrega cada finanza a la tabla
            ArrayList<Finanzas> listaFinanzas = Finanzas.listarFinanzas(conexion, dniUsuario);
            for (Finanzas finanza : listaFinanzas) {
                model.addRow(new Object[]{
                    finanza.getIdentificador().getId(), // ID de la finanza
                    finanza.getDni(), // DNI del usuario
                    finanza.getIngreso(), // Ingreso
                    finanza.getGastos(), // Gasto
                    finanza.getSaldo() // Saldo calculado
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las finanzas: " + e.getMessage());
        }
    }

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarFinanzas;
    private javax.swing.JButton btnEliminarFinanzas;
    private javax.swing.JButton btnVovler;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
