
package Paneles;

import com.mycompany.proyecto.Notas; // Se importa la clase notas
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.SQLException;


public class NotasPanel extends javax.swing.JFrame {

    private Notas notasUsuario; // Instanciamos para poder manejar las notas
    private Connection conexion; // La conexión a la base de datos 
 
    
    public NotasPanel() {
        
        
        initComponents();
        this.conexion = conexion; // Asignamos la conexión existente
        
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        btnCrearNota = new javax.swing.JButton();
        bntEditarNota = new javax.swing.JButton();
        btnVerNota = new javax.swing.JButton();
        btnEliminarNota = new javax.swing.JButton();
        btnVolver = new javax.swing.JButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 24)); // NOI18N
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

        bntEditarNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        bntEditarNota.setText("Editar Nota");
        bntEditarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                bntEditarNotaActionPerformed(evt);
            }
        });

        btnVerNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVerNota.setText("Ver Nota");
        btnVerNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerNotaActionPerformed(evt);
            }
        });

        btnEliminarNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEliminarNota.setText("Eliminar Nota");
        btnEliminarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNotaActionPerformed(evt);
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
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(125, 125, 125)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addComponent(btnVolver, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(bntEditarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(125, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnCrearNota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(bntEditarNota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVerNota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnEliminarNota)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(btnVolver)
                .addContainerGap(42, Short.MAX_VALUE))
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
    
// Boton para crear una nota
    private void btnCrearNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearNotaActionPerformed
               // Lógica para crear una nota
        String titulo = "Mi Nota";  // Esto podría venir de un campo de texto en la interfaz
        String contenido = "Este es el contenido de mi nota";
        notasUsuario = new Notas(1, "fechaActual", titulo, contenido, LocalDate.now(), null);

        try {
            notasUsuario.crearNota(titulo, contenido, conexion);  // Crear la nota en la base de datos
            System.out.println("Nota creada: " + notasUsuario.mostrarNota());
        } catch (SQLException e) {
            System.out.println("Error al crear la nota: " + e.getMessage());
        }

        // Mostrar mensaje o feedback
        System.out.println("Nota creada: " + notasUsuario.mostrarNota());
    }//GEN-LAST:event_btnCrearNotaActionPerformed
    
    // Boton para editar una nota
    private void bntEditarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_bntEditarNotaActionPerformed
        if (notasUsuario != null) {
            if (notasUsuario.getTitulo() != null && notasUsuario.getContenido() != null) {
                String nuevoContenido = "Nuevo contenido de la nota";  // Este debe ser un String válido
                try {
                    notasUsuario.editarNota(nuevoContenido, conexion);  // Editar la nota en la base de datos
                    System.out.println("Nota editada: " + notasUsuario.mostrarNota());
                } catch (SQLException e) {
                    System.out.println("Error al editar la nota: " + e.getMessage());
                }
            } else {
                System.out.println("La nota no tiene un título o contenido válido.");
            }
        } else {
            System.out.println("No hay nota creada para editar.");
        }
    }//GEN-LAST:event_bntEditarNotaActionPerformed
    
    //Boton para Ver una nota
    private void btnVerNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerNotaActionPerformed
        if (notasUsuario != null) {
            System.out.println("Ver Nota: " + notasUsuario.mostrarNota());
        } else {
            System.out.println("No hay nota creada.");
        }
    }//GEN-LAST:event_btnVerNotaActionPerformed
    
    // Boton para eliminar una nota
    private void btnEliminarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNotaActionPerformed
        if (notasUsuario != null) {
            try {
                notasUsuario.eliminarNota(conexion);  // Elimina el contenido a nivel de aplicación
                System.out.println("Nota eliminada.");
                notasUsuario = null;  // Para limpiar la instancia actual y evitar errores posteriores
            } catch (SQLException e) {
                System.out.println("Error al eliminar la nota: " + e.getMessage());
            }
        } else {
            System.out.println("No hay nota para eliminar.");
        }
    }//GEN-LAST:event_btnEliminarNotaActionPerformed
    
    // Boton para volver al panel principal
    private void btnVolverActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVolverActionPerformed
        
    }//GEN-LAST:event_btnVolverActionPerformed

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton bntEditarNota;
    private javax.swing.JButton btnCrearNota;
    private javax.swing.JButton btnEliminarNota;
    private javax.swing.JButton btnVerNota;
    private javax.swing.JButton btnVolver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel1;
    // End of variables declaration//GEN-END:variables
}
