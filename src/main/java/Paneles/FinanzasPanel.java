package Paneles;

import java.sql.Connection;
import java.sql.SQLException;
import com.mycompany.proyecto.Finanzas; // Se importa la clase notas

public class FinanzasPanel extends javax.swing.JFrame {

    private Finanzas finanzas; // Instanciamos para poder manejar las notas
    private Connection conexion; // La conexión a la base de datos 

    public FinanzasPanel(Connection conexion) {
        initComponents();
        this.conexion = conexion; // Asignamos la conexión existente
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnAgregarFinanzas = new javax.swing.JButton();
        btnEditarFinanzas = new javax.swing.JButton();
        btnResetearFinanzas = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("FINANZAS");

        btnAgregarFinanzas.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnAgregarFinanzas.setText("Agregar Finanzas");
        btnAgregarFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnAgregarFinanzasActionPerformed(evt);
            }
        });

        btnEditarFinanzas.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEditarFinanzas.setText("Editar Finanzas");
        btnEditarFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarFinanzasActionPerformed(evt);
            }
        });

        btnResetearFinanzas.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnResetearFinanzas.setText("Resetear Finanzas");
        btnResetearFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnResetearFinanzasActionPerformed(evt);
            }
        });

        btnVolver.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVolver.setText("Volver");
        btnVolver.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(80, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnResetearFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnAgregarFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 190, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(80, 80, 80))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnAgregarFinanzas)
                .addGap(30, 30, 30)
                .addComponent(btnEditarFinanzas)
                .addGap(30, 30, 30)
                .addComponent(btnResetearFinanzas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(btnVolver)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnAgregarFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnAgregarFinanzasActionPerformed
        // Pasar la conexión existente a la nueva ventana
        AgregarFinanzasPanel agregarFinanza = new AgregarFinanzasPanel(conexion);
        agregarFinanza.setLocationRelativeTo(this); // Centra la ventana respecto a FinanzasPanel
        agregarFinanza.setVisible(true);
        this.dispose(); // Opcional: Cierra NotasPanel si quieres que solo esté abierta la ventana CrearNota

    }//GEN-LAST:event_btnAgregarFinanzasActionPerformed

    private void btnEditarFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFinanzasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarFinanzasActionPerformed

    private void btnResetearFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnResetearFinanzasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnResetearFinanzasActionPerformed

    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // Instanciamos el panel Principal
        Principal volverPrincipal = new Principal();
        // Mostramos el panel principal
        volverPrincipal.setVisible(true);
        // Centrar la ventana en la pantalla
        volverPrincipal.setLocationRelativeTo(null);
        // Cerramos el panel Notas 
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnAgregarFinanzas;
    private javax.swing.JButton btnEditarFinanzas;
    private javax.swing.JButton btnResetearFinanzas;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables

}
