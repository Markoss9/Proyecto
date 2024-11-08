package Paneles;

import com.mycompany.proyecto.Notas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class EditarNotaDialog extends JDialog {

    private final Connection conexion;
    private final int dniUsuario;
    private final int id; // El ID de la nota a editar
    private JTextField txtTitulo;
    private JTextArea txtContenido;
    private JButton btnGuardar;

    public EditarNotaDialog(JFrame parent, Connection conexion, int dniUsuario, int id) {
        super(parent, "Editar Nota", true);
        this.conexion = conexion;
        this.dniUsuario = dniUsuario;
        this.id = id;

        initComponents();
        // Cargar la nota existente para editar
        cargarNota();

        // Agregar el ActionListener al botón "Guardar"
        btnGuardar.addActionListener(evt -> guardarNota());
        // Centrar el diálogo en la pantalla
        setLocationRelativeTo(null);
    }

    private void initComponents() {
        setSize(400, 300);
        setLayout(null);

        // Título de la nota
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 20, 100, 25);
        add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(120, 20, 250, 25);
        add(txtTitulo);

        // Contenido de la nota
        JLabel lblContenido = new JLabel("Contenido:");
        lblContenido.setBounds(20, 60, 100, 25);
        add(lblContenido);

        txtContenido = new JTextArea();
        txtContenido.setBounds(120, 60, 250, 150);
        add(txtContenido);

        // Botón de Guardar a la izquierda
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 220, 100, 30); // Coloca el botón "Guardar" a la izquierda
        add(btnGuardar);

        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarNota(); // Guardamos los cambios cuando el usuario haga clic en Guardar
            }
        });

        // Botón de Cancelar a la derecha
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 220, 100, 30); // Coloca el botón "Cancelar" a la derecha
        add(btnCancelar);

        // Acción del botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Cierra el EditarNotaDialog y vuelve al VerNotaDialog
                dispose(); // Cierra la ventana de edición
            }
        });

        // Cargar la información actual de la nota
        cargarNota();
    }

    // Metodo pra cargar el contenido de una nota 
    private void cargarNota() {
        try {
            // Buscar la nota por su ID y dniUsuario
            Notas nota = Notas.buscarNotaPorId(conexion, dniUsuario, id);
            if (nota != null) {
                // Si la nota se encuentra, mostramos su título y contenido en los campos de texto
                txtTitulo.setText(nota.getTitulo());
                txtContenido.setText(nota.getContenido());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la nota: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para guardar los cambios hechos cuando editamos una nota 
    private void guardarNota() {
        try {
            String nuevoTitulo = txtTitulo.getText();
            String nuevoContenido = txtContenido.getText();

            // Actualizar la nota en la base de datos
            Notas.actualizarNota(conexion, dniUsuario, id, nuevoTitulo, nuevoContenido);

            JOptionPane.showMessageDialog(this, "Los cambios se han guardado correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

            // Cerrar el diálogo de edición
            dispose();
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
