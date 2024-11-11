
package Paneles;
import com.mycompany.proyecto.Conexion;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

import javax.swing.table.DefaultTableModel;
public class ContactosDeEmergencia extends javax.swing.JFrame {
    private DefaultTableModel modeloTabla;
    private int dniUsuario; // DNI del usuario logueado, pasado al constructor

    // Constructor
    public ContactosDeEmergencia(int dniUsuario) {
        dniUsuario = Login.getDniUsuario();
        this.dniUsuario = dniUsuario;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cargarContactos();
        
    }
    
    // Método para cargar los contactos de la base de datos
    private void cargarContactos() {
        try (Connection conn = Conexion.getConnection()) {
        String query = "SELECT id, nombre, telefono FROM contactos_emergencia WHERE dni_usuario = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, dniUsuario);  // Cambiar a setInt para que reciba un int
        ResultSet rs = stmt.executeQuery();

        // Limpiar la tabla antes de llenarla
        modeloTabla = (DefaultTableModel) tabla.getModel();
        modeloTabla.setRowCount(0);

        while (rs.next()) {
            Object[] row = new Object[3];
            row[0] = rs.getInt("id");
            row[1] = rs.getString("nombre");
            row[2] = rs.getString("telefono");
            modeloTabla.addRow(row);
        }
    } catch (SQLException e) {
        JOptionPane.showMessageDialog(this, "Error al cargar los contactos: " + e.getMessage());
    }
    }
    
    // Método para guardar un nuevo contacto
    private void guardarContacto() {
        String nombre = txtNombre.getText();
        String telefono = txtTelefono.getText();

        if (nombre.isEmpty() || telefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los campos.");
            return;
        }

        try (Connection conn = Conexion.getConnection()) {
            String query = "INSERT INTO contactos_emergencia (nombre, telefono, dni_usuario) VALUES (?, ?, ?)";
PreparedStatement stmt = conn.prepareStatement(query);
stmt.setString(1, nombre);
stmt.setString(2, telefono);
stmt.setInt(3, dniUsuario);  // Si dniUsuario es int;  // Asegúrate de que esta columna sea válida
stmt.executeUpdate();

            // Limpiar los campos y recargar los contactos
            txtNombre.setText("");
            txtTelefono.setText("");
            cargarContactos();

            JOptionPane.showMessageDialog(this, "Contacto guardado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al guardar el contacto: " + e.getMessage());
        }
        // Después de guardar el contacto:
cargarContactos();
    }

    // Método para borrar un contacto
    private void borrarContacto() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un contacto para borrar.");
            return;
        }

        int idContacto = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        try (Connection conn = Conexion.getConnection()) {
            String query = "DELETE FROM contactos_emergencia WHERE id = ?";
            PreparedStatement stmt = conn.prepareStatement(query);
            stmt.setInt(1, idContacto);
            stmt.executeUpdate();

            // Recargar los contactos
            cargarContactos();

            JOptionPane.showMessageDialog(this, "Contacto borrado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al borrar el contacto: " + e.getMessage());
        }
    }
    
    // Método para editar un contacto
    private void editarContacto() {
        int filaSeleccionada = tabla.getSelectedRow();
        if (filaSeleccionada == -1) {
            JOptionPane.showMessageDialog(this, "Por favor, seleccione un contacto para editar.");
            return;
        }

        int idContacto = (int) modeloTabla.getValueAt(filaSeleccionada, 0);
        String nuevoNombre = txtNombre.getText();
        String nuevoTelefono = txtTelefono.getText();

        if (nuevoNombre.isEmpty() || nuevoTelefono.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Por favor, ingrese todos los campos.");
            return;
        }

        try (Connection conn = Conexion.getConnection()) {
            String query = "UPDATE contactos_emergencia SET nombre = ?, telefono = ? WHERE id = ?";
PreparedStatement stmt = conn.prepareStatement(query);
stmt.setString(1, nuevoNombre);
stmt.setString(2, nuevoTelefono);
stmt.setInt(3, idContacto);
stmt.executeUpdate();

            // Recargar los contactos
            cargarContactos();

            JOptionPane.showMessageDialog(this, "Contacto actualizado exitosamente.");
        } catch (SQLException e) {
            JOptionPane.showMessageDialog(this, "Error al editar el contacto: " + e.getMessage());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel4 = new javax.swing.JLabel();
        txtNombre = new javax.swing.JTextField();
        jLabel5 = new javax.swing.JLabel();
        txtTelefono = new javax.swing.JTextField();
        jPanel2 = new javax.swing.JPanel();
        jPanel3 = new javax.swing.JPanel();
        btnEditar = new javax.swing.JToggleButton();
        jScrollPane2 = new javax.swing.JScrollPane();
        tabla = new javax.swing.JTable();
        btnBorrar = new javax.swing.JToggleButton();
        btngGuardar = new javax.swing.JToggleButton();

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel2.setFont(new java.awt.Font("Arial Black", 1, 18)); // NOI18N
        jLabel2.setText("CONTACTOS DE EMERGENCIA");

        jLabel4.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel4.setText("TELEFONO:");
        jLabel4.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                jLabel4AncestorResized(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel5.setText("NOMBRE:");
        jLabel5.addHierarchyBoundsListener(new java.awt.event.HierarchyBoundsListener() {
            public void ancestorMoved(java.awt.event.HierarchyEvent evt) {
            }
            public void ancestorResized(java.awt.event.HierarchyEvent evt) {
                jLabel5AncestorResized(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(jLabel2)
                        .addGap(64, 64, 64))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jSeparator1)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel5)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(66, 66, 66)))
                        .addContainerGap())
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 51, Short.MAX_VALUE)
                        .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, 320, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(72, 72, 72))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(19, 19, 19)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(19, 19, 19)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtNombre, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 21, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(txtTelefono, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btnEditar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnEditar.setText("EDITAR ");
        btnEditar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnEditarActionPerformed(evt);
            }
        });

        tabla.setModel(new javax.swing.table.DefaultTableModel(
            new Object [][] {
                {null, null, null},
                {null, null, null},
                {null, null, null},
                {null, null, null}
            },
            new String [] {
                "id", "nombre", "telefeno"
            }
        ));
        jScrollPane2.setViewportView(tabla);

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addComponent(btnEditar)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jScrollPane2, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addGap(75, 75, 75)
                .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(btnEditar)
                .addGap(0, 6, Short.MAX_VALUE))
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
        );

        btnBorrar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnBorrar.setText("BORRAR");
        btnBorrar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnBorrarActionPerformed(evt);
            }
        });

        btngGuardar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btngGuardar.setText("GUARDAR");
        btngGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btngGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(getContentPane());
        getContentPane().setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(btnBorrar)
                .addGap(101, 101, 101)
                .addComponent(btngGuardar)
                .addGap(28, 28, 28))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 0, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 0, Short.MAX_VALUE)))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 209, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btnBorrar)
                    .addComponent(btngGuardar))
                .addGap(74, 74, 74))
            .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                .addGroup(layout.createSequentialGroup()
                    .addGap(0, 68, Short.MAX_VALUE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGap(0, 67, Short.MAX_VALUE)))
        );

        pack();
    }// </editor-fold>//GEN-END:initComponents

    private void jLabel4AncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabel4AncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel4AncestorResized

    private void jLabel5AncestorResized(java.awt.event.HierarchyEvent evt) {//GEN-FIRST:event_jLabel5AncestorResized
        // TODO add your handling code here:
    }//GEN-LAST:event_jLabel5AncestorResized

    private void btnEditarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnEditarActionPerformed
        editarContacto();

    }//GEN-LAST:event_btnEditarActionPerformed

    private void btnBorrarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnBorrarActionPerformed
       borrarContacto();  // Llama al método borrarContacto cuando se haga clic en Borrar
    }//GEN-LAST:event_btnBorrarActionPerformed

    private void btngGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btngGuardarActionPerformed
        guardarContacto();  // Llama al método guardarContacto cuando se haga clic en Guardar
    }//GEN-LAST:event_btngGuardarActionPerformed


    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JToggleButton btnBorrar;
    private javax.swing.JToggleButton btnEditar;
    private javax.swing.JToggleButton btngGuardar;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTable tabla;
    private javax.swing.JTextField txtNombre;
    private javax.swing.JTextField txtTelefono;
    // End of variables declaration//GEN-END:variables
}
