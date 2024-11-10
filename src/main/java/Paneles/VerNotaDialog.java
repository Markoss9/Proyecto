package Paneles;

import com.mycompany.proyecto.Notas;
import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.SQLException;

public class VerNotaDialog extends JDialog {

    private final Connection conexion;
    private final int dniUsuario;
    private final int id;
    private JLabel lblTitulo;
    private JTextArea txtContenido;
    private JButton btnEditar;
    public JFrame parent;

    // Constructor que inicializa el diálogo y sus componentes con los datos de la nota seleccionada
    public VerNotaDialog(JFrame parent, Connection conexion, int dniUsuario, int id, String titulo, String contenido) {
        super(parent, "Ver Nota", true); // Configura el diálogo como modal
        this.parent = parent;  // Asignación del JFrame padre
        this.conexion = conexion;
        this.id = id;
        this.dniUsuario = dniUsuario;

        initComponents(titulo, contenido); // Inicializar los componentes de la interfaz
    }

    // Método que configura los componentes visuales del diálogo
    private void initComponents(String titulo, String contenido) {
        setSize(400, 300);
        setLayout(null);

        lblTitulo = new JLabel("Título: " + titulo);
        lblTitulo.setBounds(20, 20, 350, 25);
        add(lblTitulo);

        txtContenido = new JTextArea(contenido);
        txtContenido.setBounds(20, 60, 350, 150);
        txtContenido.setEditable(false); // Bloquea el campo de contenido para que no sea editable inicialmente
        add(txtContenido);
        txtContenido.setLineWrap(true); // Activa el ajuste de línea automático
        txtContenido.setWrapStyleWord(true); // Evita que se corte una palabra en el salto de línea

        // Configuración del botón "Editar" a la izquierda
        btnEditar = new JButton("Editar");
        btnEditar.setBounds(50, 220, 100, 30);
        add(btnEditar);

        // Acción para abrir el cuadro de diálogo de edición
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoEditar(); // Método para abrir el diálogo de edición
            }
        });

        // Configuración del botón "Volver" a la derecha
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(250, 220, 100, 30);
        add(btnVolver);

        // Acción para cerrar el diálogo
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el diálogo actual
            }
        });
    }

    // Método para abrir el diálogo de edición de la nota y actualizar la interfaz tras la edición
    private void abrirDialogoEditar() {
        // Instancia el diálogo de edición, pasando el ID de la nota
        EditarNotaDialog editarDialogo = new EditarNotaDialog(parent, conexion, dniUsuario, id);
        editarDialogo.setLocationRelativeTo(null); // Centra el diálogo en la pantalla
        editarDialogo.setVisible(true); // Muestra el diálogo de edición

        // Intento de recargar la nota para actualizar el contenido después de la edición
        try {
            // Busca y actualiza la nota en el diálogo después de la edición
            Notas nota = Notas.buscarNotaPorId(conexion, dniUsuario, id);
            if (nota != null) {
                lblTitulo.setText("Título: " + nota.getTitulo()); // Actualiza el título en la etiqueta
                txtContenido.setText(nota.getContenido()); // Actualiza el contenido de la nota
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la nota actualizada: " + ex.getMessage()); // Muestra un error en caso de falla
        }
    }
}
