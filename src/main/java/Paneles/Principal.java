package Paneles;

//import javax.swing.JFrame;
import com.mycompany.proyecto.Conexion;
import com.mycompany.proyecto.Contactos;
import com.mycompany.proyecto.Salud;

import java.sql.Connection;

public class Principal extends javax.swing.JFrame {
        
    private int dniUsuario; // Variable para almacenar el DNI del usuario
    
    
    public Principal(int dniUsuario) {
        initComponents();
        this.dniUsuario = dniUsuario; // Almacenar el DNI
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jSeparator2 = new javax.swing.JSeparator();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnDatosPersonales = new javax.swing.JButton();
        btnMetas = new javax.swing.JButton();
        btnNotas = new javax.swing.JButton();
        btnTareas = new javax.swing.JButton();
        btnRecordatorio = new javax.swing.JButton();
        btnSalud = new javax.swing.JButton();
        btnCalendario = new javax.swing.JButton();
        btnFinanzas = new javax.swing.JButton();
        btnContactos = new javax.swing.JButton();
        btnHistorial = new javax.swing.JButton();
        jSeparator1 = new javax.swing.JSeparator();
        jSeparator3 = new javax.swing.JSeparator();
        jSeparator4 = new javax.swing.JSeparator();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setText("AGENDA");

        btnDatosPersonales.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnDatosPersonales.setText("DATOS PERSONALES");
        btnDatosPersonales.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnDatosPersonalesActionPerformed(evt);
            }
        });

        btnMetas.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnMetas.setText("METAS");
        btnMetas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnMetasActionPerformed(evt);
            }
        });

        btnNotas.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnNotas.setText("NOTAS");
        btnNotas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnNotasActionPerformed(evt);
            }
        });

        btnTareas.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnTareas.setText("TAREAS");
        btnTareas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnTareasActionPerformed(evt);
            }
        });

        btnRecordatorio.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnRecordatorio.setText("RECORDATORIOS");
        btnRecordatorio.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnRecordatorioActionPerformed(evt);
            }
        });

        btnSalud.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnSalud.setText("SALUD");
        btnSalud.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnSaludActionPerformed(evt);
            }
        });

        btnCalendario.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnCalendario.setText("CALENDARIO");
        btnCalendario.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCalendarioActionPerformed(evt);
            }
        });

        btnFinanzas.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnFinanzas.setText("FINANZAS");
        btnFinanzas.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnFinanzasActionPerformed(evt);
            }
        });

        btnContactos.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnContactos.setText("CONTACTOS");
        btnContactos.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnContactosActionPerformed(evt);
            }
        });

        btnHistorial.setFont(new java.awt.Font("Arial Black", 2, 14)); // NOI18N
        btnHistorial.setText("HISTORIAL");
        btnHistorial.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnHistorialActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(jSeparator4)
                    .addComponent(jSeparator1)
                    .addComponent(btnDatosPersonales, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(btnMetas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(btnTareas, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(btnNotas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(btnSalud, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnFinanzas, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnRecordatorio, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(btnHistorial, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(btnContactos, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                                .addGap(18, 18, 18)
                                .addComponent(btnCalendario, javax.swing.GroupLayout.PREFERRED_SIZE, 194, javax.swing.GroupLayout.PREFERRED_SIZE))))
                    .addComponent(jSeparator3))
                .addGap(19, 19, 19))
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(276, 276, 276)
                .addComponent(jLabel1)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(22, 22, 22)
                .addComponent(jSeparator4, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnDatosPersonales)
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnNotas)
                    .addComponent(btnRecordatorio)
                    .addComponent(btnFinanzas))
                .addGap(27, 27, 27)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnTareas)
                    .addComponent(btnCalendario)
                    .addComponent(btnContactos))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 27, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnMetas)
                    .addComponent(btnSalud)
                    .addComponent(btnHistorial))
                .addGap(18, 18, 18)
                .addComponent(jSeparator3, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(20, 20, 20))
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


    private void btnMetasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnMetasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnMetasActionPerformed

    private void btnNotasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnNotasActionPerformed
        // Establece la conexión
        Connection conexion = Conexion.getConnection();

        // Verifica que la conexión no sea null antes de abrir NotasPanel
        if (conexion != null) {
            // Instancia el panel Notas con la conexión
            NotasPanel irNotas = new NotasPanel(conexion, dniUsuario);

            // Centra la ventana en la pantalla
            irNotas.setLocationRelativeTo(null);

            // Mostrar el panel Notas
            irNotas.setVisible(true);

            // Cierra la ventana actual (panel Principal)
            this.dispose();
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    }//GEN-LAST:event_btnNotasActionPerformed

    private void btnTareasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnTareasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnTareasActionPerformed

    private void btnRecordatorioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnRecordatorioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnRecordatorioActionPerformed

    private void btnSaludActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnSaludActionPerformed
        
        
        Connection conexion = Conexion.getConnection();
        
        Salud salud = new Salud(0,0);
        
        SaludPanel saludPanel = new SaludPanel(salud, dniUsuario);

        saludPanel.setLocationRelativeTo(null);

        saludPanel.setVisible(true);
        
        this.dispose();
    }//GEN-LAST:event_btnSaludActionPerformed

    private void btnCalendarioActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCalendarioActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnCalendarioActionPerformed

    private void btnFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnFinanzasActionPerformed
        // Establece la conexión
        Connection conexion = Conexion.getConnection();

        // Verifica que la conexión no sea null antes de abrir FinanzaasPanel
        if (conexion != null) {
            // Instancia el panel Notas con la conexión
            FinanzasPanel irFinanzas = new FinanzasPanel(conexion, dniUsuario);

            // Centra la ventana en la pantalla
            irFinanzas.setLocationRelativeTo(null);

            // Mostrar el panel Notas
            irFinanzas.setVisible(true);

            // Cierra la ventana actual (panel Principal)
            this.dispose();
        } else {
            System.out.println("No se pudo establecer la conexión a la base de datos.");
        }
    }//GEN-LAST:event_btnFinanzasActionPerformed

    private void btnContactosActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnContactosActionPerformed
        Contactos contactos = new Contactos(0,"","","","");
        ContactosPanel panelContacto = new ContactosPanel(dniUsuario, contactos);
        panelContacto.setLocationRelativeTo(null);
        panelContacto.setVisible(true);
        this.dispose();
    }//GEN-LAST:event_btnContactosActionPerformed

    private void btnHistorialActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnHistorialActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnHistorialActionPerformed

    private void btnDatosPersonalesActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnDatosPersonalesActionPerformed
        // Crear una instancia del panel DatosPersonales
        DatosPersonales datosPersonales = new DatosPersonales();

        // Centrar la ventana en la pantalla
        datosPersonales.setLocationRelativeTo(null);

        // Mostrar el panel
        datosPersonales.setVisible(true);
    }//GEN-LAST:event_btnDatosPersonalesActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCalendario;
    private javax.swing.JButton btnContactos;
    private javax.swing.JButton btnDatosPersonales;
    private javax.swing.JButton btnFinanzas;
    private javax.swing.JButton btnHistorial;
    private javax.swing.JButton btnMetas;
    private javax.swing.JButton btnNotas;
    private javax.swing.JButton btnRecordatorio;
    private javax.swing.JButton btnSalud;
    private javax.swing.JButton btnTareas;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JSeparator jSeparator2;
    private javax.swing.JSeparator jSeparator3;
    private javax.swing.JSeparator jSeparator4;
    // End of variables declaration//GEN-END:variables
}
