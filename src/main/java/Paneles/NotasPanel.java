package Paneles;

import com.mycompany.proyecto.Notas; // Se importa la clase notas
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.SQLException;

public class NotasPanel extends javax.swing.JFrame {

    private Notas notasUsuario; // Instanciamos para poder manejar las notas
    private Connection conexion; // La conexión a la base de datos 
    private final int dniUsuario;
    
    public NotasPanel(Connection conexion, int dniUsuario) {

        initComponents();
        this.conexion = conexion; // Asignamos la conexión existente
        this.dniUsuario = dniUsuario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCrearNota = new javax.swing.JButton();
        btnVerNota = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 3, 24)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOTAS");
        jLabel1.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);

        btnCrearNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnCrearNota.setText("Crear Nota");
        btnCrearNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearNotaActionPerformed(evt);
            }
        });

        btnVerNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVerNota.setText("Ver Notas");
        btnVerNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerNotaActionPerformed(evt);
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
                .addContainerGap(73, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(75, 75, 75))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(btnCrearNota)
                .addGap(24, 24, 24)
                .addComponent(btnVerNota)
                .addGap(18, 18, 18)
                .addComponent(btnVolver)
                .addContainerGap(20, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

// Boton para crear una nota
    private void btnCrearNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearNotaActionPerformed
        // Pasar la conexión existente a la nueva ventana
        CrearNota crearNota = new CrearNota(conexion,dniUsuario);
        crearNota.setLocationRelativeTo(this); // Centra la ventana respecto a NotasPanel
        crearNota.setVisible(true);
        this.dispose(); // Opcional: Cierra NotasPanel si quieres que solo esté abierta la ventana CrearNota
    }//GEN-LAST:event_btnCrearNotaActionPerformed

    //Boton para Ver una nota
    private void btnVerNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerNotaActionPerformed
        // Instanciamos el panel Principal
        VerNotasPanel irVerNotas = new VerNotasPanel(conexion, dniUsuario);
        // Mostramos el panel principal
        irVerNotas.setVisible(true);
        // Centrar la ventana en la pantalla
        irVerNotas.setLocationRelativeTo(null);
        // Cerramos el panel Notas 
        this.dispose();
    }//GEN-LAST:event_btnVerNotaActionPerformed

    // Boton para volver al panel principal
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        // Instanciamos el panel Principal
        Principal volverPrincipal = new Principal(dniUsuario);
        // Mostramos el panel principal
        volverPrincipal.setVisible(true);
        // Centrar la ventana en la pantalla
        volverPrincipal.setLocationRelativeTo(null);
        // Cerramos el panel Notas 
        this.dispose();
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearNota;
    private javax.swing.JButton btnVerNota;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
