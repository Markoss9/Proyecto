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

    public VerNotaDialog(JFrame parent, Connection conexion, int dniUsuario, int id, String titulo, String contenido) {
        super(parent, "Ver Nota", true);
        this.parent = parent;  // Asignamos el valor a la variable parent
        this.conexion = conexion;
        this.id = id;
        this.dniUsuario = dniUsuario;

        initComponents(titulo, contenido);
    }

    private void initComponents(String titulo, String contenido) {
        setSize(400, 300);
        setLayout(null);

        lblTitulo = new JLabel("Título: " + titulo);
        lblTitulo.setBounds(20, 20, 350, 25);
        add(lblTitulo);

        txtContenido = new JTextArea(contenido);
        txtContenido.setBounds(20, 60, 350, 150);
        txtContenido.setEditable(false); // Hace el contenido no editable aquí
        add(txtContenido);
        txtContenido.setLineWrap(true); // Activar el salto de línea automático
        txtContenido.setWrapStyleWord(true); // Ajustar por palabras completas (para que no corte palabras a la mitad)

        // Botón Editar a la izquierda
        btnEditar = new JButton("Editar");
        btnEditar.setBounds(50, 220, 100, 30); // Cambia la posición para estar en la izquierda
        add(btnEditar);

        // Acción del botón Editar
        btnEditar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                abrirDialogoEditar(); // Abre el cuadro de diálogo de edición
            }
        });

        // Botón Volver a la derecha
        JButton btnVolver = new JButton("Volver");
        btnVolver.setBounds(250, 220, 100, 30); // Cambia la posición para estar en la derecha
        add(btnVolver);

        // Acción del botón Volver
        btnVolver.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Lógica para volver al VerNotasPanel
                dispose(); // O cierra este diálogo si está en un diálogo
            }
        });

    }

    // Se abre el dialogo de edicion de la nota 
    private void abrirDialogoEditar() {
        EditarNotaDialog editarDialogo = new EditarNotaDialog(parent, conexion, dniUsuario, id);
        // Centrar el diálogo en la pantalla
        editarDialogo.setLocationRelativeTo(null);
        editarDialogo.setVisible(true);

        // Actualizar el contenido después de la edición
        try {
            // Después de la edición, se recarga la nota con los datos actualizados
            Notas nota = Notas.buscarNotaPorId(conexion, dniUsuario, id);
            if (nota != null) {
                lblTitulo.setText("Título: " + nota.getTitulo());
                txtContenido.setText(nota.getContenido());
            }
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al cargar la nota actualizada: " + ex.getMessage());
        }
    }
}
