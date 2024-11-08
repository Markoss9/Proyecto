package Paneles;

import com.mycompany.proyecto.Notas;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerNotasPanel extends javax.swing.JFrame {

    private Connection conexion;
    private final int dniUsuario;
    public JFrame parent;

    public VerNotasPanel(Connection conexion, int dniUsuario) {
        initComponents();
        this.conexion = conexion;
        this.dniUsuario = dniUsuario;
        cargarTablaNotas();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane1 = new javax.swing.JScrollPane();
        jScrollPane2 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnVerContenidoNota = new javax.swing.JButton();
        btnEliminrNota = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setFont(new java.awt.Font("Arial Black", 1, 11)); // NOI18N
        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "ID", "TÍTULO", "FECHA CREACIÓN/MODIFICACIÓN"
            }
        ) {
            Class[] types = new Class [] {
                java.lang.Integer.class, java.lang.String.class, java.lang.String.class
            };

            public Class getColumnClass(int columnIndex) {
                return types [columnIndex];
            }
        });
        jScrollPane2.setViewportView(jTable1);

        jScrollPane1.setViewportView(jScrollPane2);

        btnVerContenidoNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVerContenidoNota.setText("Ver Contenido");
        btnVerContenidoNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerContenidoNotaActionPerformed(evt);
            }
        });

        btnEliminrNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEliminrNota.setText("Eliminar Nota");
        btnEliminrNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminrNotaActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jScrollPane1)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnVerContenidoNota, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(13, 13, 13)
                .addComponent(btnEliminrNota, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 160, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerContenidoNota)
                    .addComponent(btnEliminrNota)
                    .addComponent(btnVolver))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerContenidoNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerContenidoNotaActionPerformed
        // Solicitar ID al usuario
        String idString = JOptionPane.showInputDialog(this, "Ingrese el ID de la nota que desea ver:", "Ver Nota", JOptionPane.PLAIN_MESSAGE);

        // Verificar que el ID ingresado sea válido
        if (idString != null && !idString.trim().isEmpty()) {
            try {
                int idNota = Integer.parseInt(idString.trim());

                // Intentar buscar la nota en la base de datos
                Notas nota = Notas.buscarNotaPorId(conexion, dniUsuario, idNota); // Método de búsqueda en la clase Notas

                if (nota != null) {
                    // Si se encuentra la nota, abrir el diálogo VerNotaDialog con la información de la nota
                    VerNotaDialog verNotaDialog = new VerNotaDialog(this, conexion, dniUsuario, idNota, nota.getTitulo(), nota.getContenido());
                    // Centrar el diálogo en la pantalla
                    verNotaDialog.setLocationRelativeTo(null);
                    verNotaDialog.setVisible(true); // Mostrar el diálogo VerNotaDialog

                } else {
                    // Si no se encuentra, mostrar mensaje de error
                    JOptionPane.showMessageDialog(this, "No se encontró una nota con el ID especificado.", "Nota no encontrada", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException ex) {
                JOptionPane.showMessageDialog(this, "Por favor ingrese un ID válido.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al buscar la nota: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnVerContenidoNotaActionPerformed

    private void btnEliminrNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminrNotaActionPerformed
        // Solicitar al usuario el ID de la nota que desea eliminar
        String idStr = JOptionPane.showInputDialog(this, "Ingrese el ID de la nota a eliminar:");

        if (idStr != null && !idStr.isEmpty()) {
            try {
                int id = Integer.parseInt(idStr); // Convertir el ID a un número entero

                // Verificar si la nota existe y pertenece al usuario actual
                Notas nota = Notas.buscarNotaPorId(conexion, dniUsuario, id);
                if (nota != null) {
                    // Preguntar al usuario si está seguro de eliminar la nota
                    int confirm = JOptionPane.showConfirmDialog(this, "¿Está seguro de que desea eliminar esta nota?", "Confirmar eliminación", JOptionPane.YES_NO_OPTION);
                    if (confirm == JOptionPane.YES_OPTION) {
                        // Eliminar la nota
                        Notas.eliminarNota(conexion, dniUsuario, id);
                        JOptionPane.showMessageDialog(this, "Nota eliminada correctamente.");
                        // Opcionalmente, actualizar la lista de notas en el panel
                        cargarTablaNotas();
                    }
                } else {
                    JOptionPane.showMessageDialog(this, "No se encontró una nota con ese ID o no le pertenece.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } catch (NumberFormatException e) {
                JOptionPane.showMessageDialog(this, "Por favor, ingrese un ID válido.", "Error", JOptionPane.ERROR_MESSAGE);
            } catch (SQLException e) {
                JOptionPane.showMessageDialog(this, "Error al acceder a la base de datos: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        }
    }//GEN-LAST:event_btnEliminrNotaActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // Instanciamos el panel Notas
        NotasPanel volverNotas = new NotasPanel(conexion, dniUsuario);
        // Mostramos el panel Notas
        volverNotas.setVisible(true);
        // Centrar la ventana en la pantalla
        volverNotas.setLocationRelativeTo(null);
        // Cerramos el panel Ver Notas
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    // Método para cargar datos en la tabla jTableNotas
    public void cargarTablaNotas() {
        DefaultTableModel model = (DefaultTableModel) jTable1.getModel();
        model.setRowCount(0); // Limpia la tabla antes de cargar nuevos datos

        try {
            // Llama al método listarNotas y carga los datos en la tabla
            ArrayList<Notas> listaNotas = Notas.listarNotas(conexion, dniUsuario);
            for (Notas nota : listaNotas) {
                model.addRow(new Object[]{
                    nota.getId(), // id de la nota
                    nota.getTitulo(), // título de la nota
                    nota.getFechaCreacion() // fecha de creación/modificación
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las notas: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminrNota;
    private javax.swing.JButton btnVerContenidoNota;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
