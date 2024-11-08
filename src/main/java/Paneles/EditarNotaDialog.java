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
    private final int id; // ID de la nota a editar
    private JTextField txtTitulo;
    private JTextArea txtContenido;
    private JButton btnGuardar;

    // Constructor para inicializar el diálogo de edición con la conexión y el ID de la nota
    public EditarNotaDialog(JFrame parent, Connection conexion, int dniUsuario, int id) {
        super(parent, "Editar Nota", true); // Configura el diálogo como modal
        this.conexion = conexion;
        this.dniUsuario = dniUsuario;
        this.id = id;

        initComponents(); // Inicializa los componentes de la interfaz
        cargarNota(); // Carga la nota actual en los campos de edición

        // Agrega el ActionListener al botón "Guardar"
        btnGuardar.addActionListener(evt -> guardarNota());
        setLocationRelativeTo(null); // Centra el diálogo en la pantalla
    }

    // Método para configurar los componentes visuales del diálogo
    private void initComponents() {
        setSize(400, 300);
        setLayout(null);

        // Campo de texto para el título
        JLabel lblTitulo = new JLabel("Título:");
        lblTitulo.setBounds(20, 20, 100, 25);
        add(lblTitulo);

        txtTitulo = new JTextField();
        txtTitulo.setBounds(120, 20, 250, 25);
        add(txtTitulo);

        // Campo de área de texto para el contenido
        JLabel lblContenido = new JLabel("Contenido:");
        lblContenido.setBounds(20, 60, 100, 25);
        add(lblContenido);

        txtContenido = new JTextArea();
        txtContenido.setBounds(120, 60, 250, 150);
        add(txtContenido);
        txtContenido.setLineWrap(true); // Activa el ajuste de línea automático
        txtContenido.setWrapStyleWord(true); // Ajusta por palabras completas

        // Botón "Guardar" para aplicar los cambios
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(50, 220, 100, 30); // Posiciona el botón a la izquierda
        add(btnGuardar);

        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarNota(); // Método para guardar los cambios
            }
        });

        // Botón "Cancelar" para cerrar sin guardar
        JButton btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(250, 220, 100, 30); // Posiciona el botón a la derecha
        add(btnCancelar);

        // Acción del botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el diálogo de edición
            }
        });
    }

    // Método para cargar el contenido de la nota seleccionada
    private void cargarNota() {
        try {
            // Busca la nota en la base de datos por ID y dniUsuario
            Notas nota = Notas.buscarNotaPorId(conexion, dniUsuario, id);
            if (nota != null) {
                // Si la nota existe, carga su título y contenido en los campos
                txtTitulo.setText(nota.getTitulo());
                txtContenido.setText(nota.getContenido());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la nota: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

    // Método para guardar los cambios realizados en la nota
    private void guardarNota() {
        try {
            String nuevoTitulo = txtTitulo.getText();
            String nuevoContenido = txtContenido.getText();

            // Llama al método para actualizar la nota en la base de datos
            Notas.actualizarNota(conexion, dniUsuario, id, nuevoTitulo, nuevoContenido);

            JOptionPane.showMessageDialog(this, "Los cambios se han guardado correctamente.", "Actualización exitosa", JOptionPane.INFORMATION_MESSAGE);

            dispose(); // Cierra el diálogo de edición después de guardar los cambios
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al guardar los cambios: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
