package Paneles;

import com.mycompany.proyecto.Notas;
import java.sql.Connection;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CrearNota extends javax.swing.JFrame {

    private Connection conexion;
    private final int dniUsuario;

    public CrearNota(Connection conexion, int dniUsuario) {
        initComponents();
        this.conexion = conexion;
        this.dniUsuario = dniUsuario;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtContenidoNota = new javax.swing.JTextArea();
        btnGuardarNota = new javax.swing.JButton();
        btnVolverPanelNotas = new javax.swing.JButton();
        txtTituloNota = new javax.swing.JTextField();
        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtContenidoNota.setColumns(20);
        txtContenidoNota.setRows(5);
        jScrollPane1.setViewportView(txtContenidoNota);

        btnGuardarNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnGuardarNota.setText("Guardar");
        btnGuardarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarNotaActionPerformed(evt);
            }
        });

        btnVolverPanelNotas.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVolverPanelNotas.setText("Volver");
        btnVolverPanelNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVolverPanelNotasActionPerformed(evt);
            }
        });

        txtTituloNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                txtTituloNotaActionPerformed(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("Título");

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("Contenido");

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(btnVolverPanelNotas, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(txtTituloNota, javax.swing.GroupLayout.PREFERRED_SIZE, 260, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(txtTituloNota, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 30, Short.MAX_VALUE)
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnGuardarNota)
                    .addComponent(btnVolverPanelNotas))
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnGuardarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarNotaActionPerformed
        String titulo = txtTituloNota.getText().trim();
        String contenido = txtContenidoNota.getText().trim();

        if (!titulo.isEmpty() && !contenido.isEmpty()) {
            try {
                // Crear una nueva instancia de Notas usando el constructor simplificado
                Notas nuevaNota = new Notas(dniUsuario, titulo, contenido); // Usa el dniUsuario que se define al iniciar sesión

                // Guardar la nota en la base de datos
                nuevaNota.guardarNota(conexion);

                // Mostrar mensaje de éxito y vaciar campos
                JOptionPane.showMessageDialog(this, "Nota guardada exitosamente.");
                txtTituloNota.setText(""); // Vaciar el campo de título
                txtContenidoNota.setText(""); // Vaciar el campo de contenido
            } catch (SQLException ex) {
                JOptionPane.showMessageDialog(this, "Error al guardar la nota: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "Por favor, complete todos los campos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        }
    }//GEN-LAST:event_btnGuardarNotaActionPerformed

    private void txtTituloNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_txtTituloNotaActionPerformed
        // Cambiar el enfoque al siguiente campo al darle enter
        txtContenidoNota.requestFocus();
    }//GEN-LAST:event_txtTituloNotaActionPerformed

    private void btnVolverPanelNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverPanelNotasActionPerformed
        // Instanciamos el panel Notas
        NotasPanel volverNotas = new NotasPanel(conexion, dniUsuario);
        // Mostramos el panel Notas
        volverNotas.setVisible(true);
        // Centrar la ventana en la pantalla
        volverNotas.setLocationRelativeTo(null);
        // Cerramos el panel Crear Notas 
        this.dispose();
    }//GEN-LAST:event_btnVolverPanelNotasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardarNota;
    private javax.swing.JButton btnVolverPanelNotas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JTextArea txtContenidoNota;
    private javax.swing.JTextField txtTituloNota;
    // End of variables declaration//GEN-END:variables
}
