package Paneles;

import com.mycompany.proyecto.Notas;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import javax.swing.JOptionPane;
import javax.swing.table.DefaultTableModel;

public class VerNotasPanel extends javax.swing.JFrame {

    private Connection conexion;
    private final int dniUsuario;

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
        btnVerNota = new javax.swing.JButton();
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

        btnVerNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVerNota.setText("Ver Nota");
        btnVerNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerNotaActionPerformed(evt);
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
                .addComponent(btnVerNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(114, 114, 114)
                .addComponent(btnEliminrNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnVerNota)
                    .addComponent(btnEliminrNota)
                    .addComponent(btnVolver))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVerNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerNotaActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnVerNotaActionPerformed

    private void btnEliminrNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminrNotaActionPerformed
        // TODO add your handling code here:
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
                    nota.getFechaCreacionModificacion() // fecha de creación/modificación
                });
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(this, "Error al cargar las notas: " + e.getMessage());
        }
    }


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEliminrNota;
    private javax.swing.JButton btnVerNota;
    private javax.swing.JButton btnVolver;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
