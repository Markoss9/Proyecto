
package Paneles;

import static Paneles.Login.dniUsuario;
import com.mycompany.proyecto.Conexion;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JOptionPane;

public class CorreoElectronico extends javax.swing.JFrame {

    private final int dniUsuario;
    

    public CorreoElectronico(int dniUsuario) {
        this.dniUsuario = dniUsuario;
        initComponents();
        setDefaultCloseOperation(javax.swing.WindowConstants.DISPOSE_ON_CLOSE);
        cargarCorreo(); // Cargar el correo al abrir el panel
    }
    
    // Método para cargar el correo del usuario desde la base de datos
    private void cargarCorreo() {
    try (Connection conn = Conexion.getConnection()) {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Error: Conexión no establecida", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Cargando correo para el usuario con DNI: " + dniUsuario);

        String query = "SELECT correo_electronico FROM usuarios WHERE dni = ?";
        PreparedStatement stmt = conn.prepareStatement(query);
        stmt.setInt(1, dniUsuario); // Se asegura de que el DNI se pasa como un número
        ResultSet rs = stmt.executeQuery();

        if (rs.next()) {
            String correo = rs.getString("correo_electronico");
            txtCorreo.setText(correo); // Mostrar el correo en el campo de texto
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el usuario", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al cargar el correo", "Error", JOptionPane.ERROR_MESSAGE);
    }
}


    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel2 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel1 = new javax.swing.JLabel();
        jSeparator1 = new javax.swing.JSeparator();
        jLabel3 = new javax.swing.JLabel();
        txtCorreo = new javax.swing.JTextField();
        btnGuardar = new javax.swing.JButton();

        jLabel2.setText("jLabel2");

        setDefaultCloseOperation(javax.swing.WindowConstants.EXIT_ON_CLOSE);

        jLabel1.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        jLabel1.setText("CORREO ELECTRONICO");

        jLabel3.setFont(new java.awt.Font("Arial Black", 1, 12)); // NOI18N
        jLabel3.setText("CORREO:");

        btnGuardar.setFont(new java.awt.Font("Arial Black", 1, 14)); // NOI18N
        btnGuardar.setText("GUARDAR");
        btnGuardar.addActionListener(new java.awt.event.ActionListener() {
            public void actionPerformed(java.awt.event.ActionEvent evt) {
                btnGuardarActionPerformed(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(17, 17, 17)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel3)
                        .addGap(18, 18, 18)
                        .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, 287, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 365, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(15, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(jLabel1)
                        .addGap(101, 101, 101))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel1Layout.createSequentialGroup()
                        .addComponent(btnGuardar)
                        .addGap(143, 143, 143))))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addGap(20, 20, 20)
                .addComponent(jLabel1)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(jSeparator1, javax.swing.GroupLayout.PREFERRED_SIZE, 10, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(txtCorreo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(btnGuardar)
                .addContainerGap(43, Short.MAX_VALUE))
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

    private void btnGuardarActionPerformed(java.awt.event.ActionEvent evt) {//GEN-FIRST:event_btnGuardarActionPerformed
        guardarCorreo(); // Llama al método guardarCorreo cuando se presiona el botón
    }//GEN-LAST:event_btnGuardarActionPerformed

private void guardarCorreo() {
    String correo = txtCorreo.getText();

    try (Connection conn = Conexion.getConnection()) {
        if (conn == null) {
            JOptionPane.showMessageDialog(this, "Error: Conexión no establecida", "Error de Conexión", JOptionPane.ERROR_MESSAGE);
            return;
        }

        System.out.println("Intentando encontrar usuario con DNI: " + dniUsuario);

        // Consulta SELECT para comprobar si el usuario está presente antes de actualizar
        String checkQuery = "SELECT dni FROM usuarios WHERE dni = ?";
        PreparedStatement checkStmt = conn.prepareStatement(checkQuery);
        checkStmt.setInt(1, dniUsuario);
        ResultSet rs = checkStmt.executeQuery();

        if (rs.next()) {
            System.out.println("Usuario encontrado en la base de datos. Procediendo a actualizar...");

            String updateQuery = "UPDATE usuarios SET correo_electronico = ? WHERE dni = ?";
            PreparedStatement stmt = conn.prepareStatement(updateQuery);
            stmt.setString(1, correo);
            stmt.setInt(2, dniUsuario);

            int rowsUpdated = stmt.executeUpdate();
            if (rowsUpdated > 0) {
                JOptionPane.showMessageDialog(this, "Correo guardado correctamente");
                txtCorreo.setText(correo); // Reflejar el correo en el campo después de guardarlo
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró el usuario para actualizar", "Advertencia", JOptionPane.WARNING_MESSAGE);
            }
        } else {
            JOptionPane.showMessageDialog(this, "No se encontró el usuario en la base de datos", "Advertencia", JOptionPane.WARNING_MESSAGE);
        }
    } catch (SQLException e) {
        e.printStackTrace();
        JOptionPane.showMessageDialog(this, "Error al guardar el correo", "Error", JOptionPane.ERROR_MESSAGE);
    }
}

    
    
    
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JButton btnGuardar;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JSeparator jSeparator1;
    private javax.swing.JTextField txtCorreo;
    // End of variables declaration//GEN-END:variables
}