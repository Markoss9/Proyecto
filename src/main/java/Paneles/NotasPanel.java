
package Paneles;

import com.mycompany.proyecto.Notas; // Se importa la clase notas
import java.time.LocalDate;
import java.sql.Connection;
import java.sql.SQLException;
import java.time.LocalDate;

public class NotasPanel extends javax.swing.JPanel {

    private Notas notaActual; // Intanciamos para poder manejar las notas
    private Connection conexion; // La conexion a la base de datos 

    public NotasPanel() {
        initComponents();
        this.conexion = conexion; // Iniciamos la conexion
    }

    // Método para verificar la conexión a la base de datos
    public boolean verificarConexion() {
        try {
            if (conexion != null && !conexion.isClosed()) {
                System.out.println("Conexión a la base de datos exitosa.");
                return true;
            } else {
                System.out.println("Conexión a la base de datos fallida.");
                return false;
            }
        } catch (SQLException e) {
            System.out.println("Error al verificar la conexión: " + e.getMessage());
            return false;
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btnCrearNota = new javax.swing.JButton();
        btnEditarNota = new javax.swing.JButton();
        btnEliminarNota = new javax.swing.JButton();
        btnVerNota = new javax.swing.JButton();

        jLabel1.setFont(new java.awt.Font("Arial", 1, 18)); // NOI18N
        jLabel1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel1.setText("NOTAS");
        jLabel1.setToolTipText("");

        btnCrearNota.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnCrearNota.setText("Crear Nota");
        btnCrearNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnCrearNotaActionPerformed(evt);
            }
        });

        btnEditarNota.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEditarNota.setText("Editar Nota");
        btnEditarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarNotaActionPerformed(evt);
            }
        });

        btnEliminarNota.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
        btnEliminarNota.setText("Eliminar Nota");
        btnEliminarNota.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEliminarNotaActionPerformed(evt);
            }
        });

        btnVerNota.setFont(new java.awt.Font("Arial", 1, 14)); // NOI18N
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
                .addContainerGap(69, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btnEliminarNota)
                    .addComponent(btnEditarNota, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnCrearNota, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnVerNota, javax.swing.GroupLayout.PREFERRED_SIZE, 128, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(58, 58, 58))
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
                .addContainerGap(50, Short.MAX_VALUE))
        );
    }// </editor-fold>//GEN-END:initComponents

    // Método para crear una nota
    private void btnCrearNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnCrearNotaActionPerformed

        if (verificarConexion()) {
            // Lógica para crear una nota
            String titulo = "Mi Nota";  // Esto podría venir de un campo de texto en la interfaz
            String contenido = "Este es el contenido de mi nota";
            notaActual = new Notas(1, "fechaActual", titulo, contenido, LocalDate.now(), null);

            try {
                notaActual.crearNota(titulo, contenido, conexion);  // Crear la nota en la base de datos
                System.out.println("Nota creada: " + notaActual.mostrarNota());
            } catch (SQLException e) {
                System.out.println("Error al crear la nota: " + e.getMessage());
            }
        } else {
            System.out.println("No se pudo establecer conexión con la base de datos.");
        }

        // Mostrar mensaje o feedback
        System.out.println("Nota creada: " + notaActual.mostrarNota());
    }//GEN-LAST:event_btnCrearNotaActionPerformed

    // Método para ver la nota deseada
    private void btnVerNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnVerNotaActionPerformed
        if (verificarConexion()) {
        if (notaActual != null) {
            System.out.println("Ver Nota: " + notaActual.mostrarNota());
        } else {
            System.out.println("No hay nota creada.");
        }
    } else {
        System.out.println("No se pudo establecer conexión con la base de datos.");
    }
    }//GEN-LAST:event_btnVerNotaActionPerformed

    // Método para editar alguna nota existente
    private void btnEditarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarNotaActionPerformed
        if (verificarConexion()) {
            if (notaActual != null) {
                if (notaActual.getTitulo() != null && notaActual.getContenido() != null) {
                    String nuevoContenido = "Nuevo contenido de la nota";  // Este debe ser un String válido
                    try {
                        notaActual.editarNota(nuevoContenido, conexion);  // Editar la nota en la base de datos
                        System.out.println("Nota editada: " + notaActual.mostrarNota());
                    } catch (SQLException e) {
                        System.out.println("Error al editar la nota: " + e.getMessage());
                    }
                } else {
                    System.out.println("La nota no tiene un título o contenido válido.");
                }
            } else {
                System.out.println("No hay nota creada para editar.");
            }
        } else {
            System.out.println("No se pudo establecer conexión con la base de datos.");
        }
    }//GEN-LAST:event_btnEditarNotaActionPerformed

    // Método para eliminar una nota
    private void btnEliminarNotaActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEliminarNotaActionPerformed
        if (verificarConexion()) {
        if (notaActual != null) {
            try {
                notaActual.eliminarNota(conexion);  // Elimina el contenido a nivel de aplicación
                System.out.println("Nota eliminada.");
                notaActual = null;  // Para limpiar la instancia actual y evitar errores posteriores
            } catch (SQLException e) {
                System.out.println("Error al eliminar la nota: " + e.getMessage());
            }
        } else {
            System.out.println("No hay nota para eliminar.");
        }
    } else {
        System.out.println("No se pudo establecer conexión con la base de datos.");
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
