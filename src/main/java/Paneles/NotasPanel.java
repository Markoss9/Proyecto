
package Paneles;

import com.mycompany.proyecto.Notas; // Se importa la clase notas
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.SQLException;

public class NotasPanel extends javax.swing.JPanel {

    private Notas notasUsuario; // Instanciamos para poder manejar las notas
    private Connection conexion; // La conexión a la base de datos 

    public NotasPanel(Connection conexion) { // Recibimos la conexion en el constructor 
        initComponents();
        this.conexion = conexion; // Asignamos la conexión existente
    }
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCrearNota = new javax.swing.JButton();
        btnEditarNota = new javax.swing.JButton();
        btnEliminarNota = new javax.swing.JButton();
        btnVerNota = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOTAS");
        jLabel1.setToolTipText("");

        btnCrearNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnCrearNota.setText("Crear Nota");
        btnCrearNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearNotaActionPerformed(evt);
            }
        });

        btnEditarNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEditarNota.setText("Editar Nota");
        btnEditarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarNotaActionPerformed(evt);
            }
        });

        btnEliminarNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEliminarNota.setText("Eliminar Nota");
        btnEliminarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNotaActionPerformed(evt);
            }
        });

        btnVerNota.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnVerNota.setText("Ver Nota");
        btnVerNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnVerNotaActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(55, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEliminarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnEditarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerNota, javax.swing.GroupLayout.PREFERRED_SIZE, 150, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(55, 55, 55))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(57, 57, 57)
                .addComponent(btnCrearNota)
                .addGap(18, 18, 18)
                .addComponent(btnEditarNota)
                .addGap(18, 18, 18)
                .addComponent(btnEliminarNota)
                .addGap(18, 18, 18)
                .addComponent(btnVerNota)
                .addContainerGap(30, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Método para crear una nota
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

    // Método para ver la nota deseada
    private void btnVerNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerNotaActionPerformed
        
        if (notasUsuario != null) {
            System.out.println("Ver Nota: " + notasUsuario.mostrarNota());
        } else {
            System.out.println("No hay nota creada.");
        }
    }//GEN-LAST:event_btnVerNotaActionPerformed

    // Método para editar alguna nota existente
    private void btnEditarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarNotaActionPerformed
        
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
    }//GEN-LAST:event_btnEditarNotaActionPerformed

    // Método para eliminar una nota
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


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnCrearNota;
    private javax.swing.JButton btnEditarNota;
    private javax.swing.JButton btnEliminarNota;
    private javax.swing.JButton btnVerNota;
    private javax.swing.JLabel jLabel1;
    // End of variables declaration//GEN-END:variables

}
