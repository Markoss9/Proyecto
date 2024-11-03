package Paneles;

import java.sql.Connection;


public class ActualizarFinanzasPanel extends javax.swing.JFrame {
    
    private Connection conexion;
  
    public ActualizarFinanzasPanel(Connection conexion) {
        initComponents();
        this.conexion = conexion;
    }

  
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jScrollPane2 = new javax.swing.JScrollPane();
        jScrollPane1 = new javax.swing.JScrollPane();
        jTable1 = new javax.swing.JTable();
        btnEditarFinanzas = new javax.swing.JButton();
        btnVovler = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jTable1.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null},
                {null, null, null, null, null}
            },
            new String [] {
                "id", "dni", "ingresos", "gastos", "saldo"
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

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(btnEditarFinanzas, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnVovler, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
            .addComponent(jScrollPane2, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 600, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 240, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnEditarFinanzas)
                    .addComponent(btnVovler))
                .addContainerGap())
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void btnVovlerActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVovlerActionPerformed
       // Instanciamos el panel Finanzas
        FinanzasPanel volverFinanzas = new FinanzasPanel(conexion);
        // Mostramos el panel de Finanzas
        volverFinanzas.setVisible(true);
        // Centrar la ventana en la pantalla
        volverFinanzas.setLocationRelativeTo(null);
        // Cerramos el panel ActualizarFinanzas 
        this.dispose();
    }//GEN-LAST:event_btnVovlerActionPerformed

    private void btnEditarFinanzasActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarFinanzasActionPerformed
        // TODO add your handling code here:
    }//GEN-LAST:event_btnEditarFinanzasActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnEditarFinanzas;
    private javax.swing.JButton btnVovler;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JTable jTable1;
    // End of variables declaration//GEN-END:variables

}
