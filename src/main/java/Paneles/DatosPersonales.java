
package Paneles;


public class DatosPersonales extends javax.swing.JFrame {

    private int dniUsuario;

    
    public DatosPersonales() {
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator2 = new javax.swing.JSeparator();
        txtDatosPersonales = new javax.swing.JLabel();
        btnDocumento = new javax.swing.JToggleButton();
        btnTelefono = new javax.swing.JToggleButton();
        btnLicenciaDeConducir = new javax.swing.JToggleButton();
        btnContactosDeEmergencia = new javax.swing.JToggleButton();
        jSeparator3 = new javax.swing.JSeparator();
        btnCorreoElectronico = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        txtDatosPersonales.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
        txtDatosPersonales.setText("DATOS PERSONALES");

        btnDocumento.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnDocumento.setText("DOCUMENTO");
        btnDocumento.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDocumentoActionPerformed(evt);
            }
        });

        btnTelefono.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnTelefono.setText("TELEFONO");
        btnTelefono.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTelefonoActionPerformed(evt);
            }
        });

        btnLicenciaDeConducir.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnLicenciaDeConducir.setText("LICENCIA DE CONDUCIR");
        btnLicenciaDeConducir.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnLicenciaDeConducirActionPerformed(evt);
            }
        });

        btnContactosDeEmergencia.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnContactosDeEmergencia.setText("CONTACTOS DE EMERGENCIA");
        btnContactosDeEmergencia.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactosDeEmergenciaActionPerformed(evt);
            }
        });

        btnCorreoElectronico.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        btnCorreoElectronico.setText("CORREO ELECTRONICO");
        btnCorreoElectronico.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCorreoElectronicoActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(31, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jSeparator3, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(jSeparator1)
                            .addComponent(jSeparator2, javax.swing.GroupLayout.DEFAULT_SIZE, 390, Short.MAX_VALUE)
                            .addComponent(btnTelefono, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnLicenciaDeConducir, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnDocumento, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnContactosDeEmergencia, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnCorreoElectronico, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(32, 32, 32))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(txtDatosPersonales)
                        .addGap(73, 73, 73))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(24, 24, 24)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(txtDatosPersonales)
                .addGap(12, 12, 12)
                .addComponent(jSeparator2, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnDocumento)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnLicenciaDeConducir)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnTelefono)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnContactosDeEmergencia)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnCorreoElectronico)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(40, Short.MAX_VALUE))
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

    private void btnDocumentoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDocumentoActionPerformed
        // Crear una instancia del panel Documento
        Documento documento = new Documento();
    
        // Centrar la ventana en la pantalla
        documento.setLocationRelativeTo(null);
    
        // Mostrar el panel
        documento.setVisible(true);
        
    }//GEN-LAST:event_btnDocumentoActionPerformed

    
    private void btnTelefonoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTelefonoActionPerformed

    dniUsuario = Login.getDniUsuario();
    int dniUsuario = this.dniUsuario; // Cambia 'dniUsuario' por la variable o método que uses para obtener el DNI

    // Crear una instancia del panel Telefono y pasarle el DNI del usuario
    Telefono telefono = new Telefono(dniUsuario);
    
    // Centrar la ventana en la pantalla
    telefono.setLocationRelativeTo(null);
    
    // Mostrar el panel
    telefono.setVisible(true);
    }//GEN-LAST:event_btnTelefonoActionPerformed

    private void btnLicenciaDeConducirActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnLicenciaDeConducirActionPerformed
        // Crear una instancia del panel LicenciaDeConducir
        LicenciaDeConducir licencia = new LicenciaDeConducir();
    
        // Centrar la ventana en la pantalla
        licencia.setLocationRelativeTo(null);
    
        // Mostrar el panel
        licencia.setVisible(true);
    }//GEN-LAST:event_btnLicenciaDeConducirActionPerformed

    private void btnContactosDeEmergenciaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactosDeEmergenciaActionPerformed
        // Obtener el DNI del usuario como int
    int dniUsuario = Login.getDniUsuario();
    
    // Crear una instancia del panel ContactosDeEmergencia pasando el DNI
    ContactosDeEmergencia contactos = new ContactosDeEmergencia(dniUsuario);
    
    // Centrar la ventana en la pantalla
    contactos.setLocationRelativeTo(null);
    
    // Mostrar el panel
    contactos.setVisible(true);
    }//GEN-LAST:event_btnContactosDeEmergenciaActionPerformed

    private void btnCorreoElectronicoActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCorreoElectronicoActionPerformed
    // Asegúrate de que el dniUsuario esté correctamente asignado antes de pasar
    int dniUsuario = Login.getDniUsuario();
    dniUsuario = this.dniUsuario;  // Obtener el DNI de la clase DatosPersonales o de Login

    // Crear una instancia del panel CorreoElectronico y pasarle el dniUsuario
    CorreoElectronico correoElectronico = new CorreoElectronico(Login.dniUsuario);
 
    // Centrar la ventana en la pantalla
    correoElectronico.setLocationRelativeTo(null);
 
    // Mostrar el panel
    correoElectronico.setVisible(true);
    }//GEN-LAST:event_btnCorreoElectronicoActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnContactosDeEmergencia;
    private javax.swing.JToggleButton btnCorreoElectronico;
    private javax.swing.JToggleButton btnDocumento;
    private javax.swing.JToggleButton btnLicenciaDeConducir;
    private javax.swing.JToggleButton btnTelefono;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JLabel txtDatosPersonales;
    // End of variables declaration//GEN-END:variables

}
