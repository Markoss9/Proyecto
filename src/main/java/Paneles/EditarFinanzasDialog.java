package Paneles;

import com.mycompany.proyecto.Finanzas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import javax.swing.JButton;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JTextField;

public class EditarFinanzasDialog extends JDialog {

    private final Connection connection;
    private final int id;
    private final int dniUsuario;
    private JTextField txtIngreso;
    private JTextField txtGastos;
    private JButton btnGuardar;
    private JButton btnCancelar;
    private ActualizarFinanzasPanel parentPanel;

    public EditarFinanzasDialog(ActualizarFinanzasPanel parent, int id, int dniUsuario, Connection connection, float ingreso, float gastos) {
        super(parent, "Editar Finanzas", true);
        this.connection = connection;
        // Método que obtiene el ID de la finanza a partir del DNI
        this.id = id;
        this.dniUsuario = dniUsuario;
        initComponents(ingreso, gastos);
        this.parentPanel = parent;
    }

    private void initComponents(float ingreso, float gastos) {
        // Configuración del diálogo
        setLayout(null);
        setSize(300, 200);
        setLocationRelativeTo(null);

        // Labels y TextFields para ingreso y gastos
        JLabel lblIngreso = new JLabel("Ingreso:");
        lblIngreso.setBounds(20, 20, 100, 25);
        add(lblIngreso);

        txtIngreso = new JTextField(String.valueOf(ingreso));
        txtIngreso.setBounds(120, 20, 150, 25);
        add(txtIngreso);

        JLabel lblGastos = new JLabel("Gastos:");
        lblGastos.setBounds(20, 60, 100, 25);
        add(lblGastos);

        txtGastos = new JTextField(String.valueOf(gastos));
        txtGastos.setBounds(120, 60, 150, 25);
        add(txtGastos);

        // Botones de Guardar y Cancelar
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(40, 120, 100, 30);
        add(btnGuardar);

        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(160, 120, 100, 30);
        add(btnCancelar);

        // Acción del botón Guardar
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();
            }
        });

        // Acción del botón Cancelar
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose(); // Cierra el diálogo sin guardar
            }
        });
    }

    // Método para guardar los cambios en la base de datos
    private void guardarCambios() {
        try {
            float nuevoIngreso = Float.parseFloat(txtIngreso.getText());
            float nuevoGastos = Float.parseFloat(txtGastos.getText());

            // Intentar buscar la finanza por ID
            System.out.println("ID que se busca: " + id); // Para ver el ID que se está utilizando
            Finanzas finanza = Finanzas.buscarFinanzaPorId(connection, id, dniUsuario);
            if (finanza != null) {
                // Si se encuentra, establecer los nuevos valores, pasando la conexión
                finanza.setIngreso(nuevoIngreso, connection);
                finanza.setGastos(nuevoGastos, connection);
                parentPanel.cargarTablaFinanzas(); // Llama al método para actualizar la tabla
                dispose(); // Cerrar el diálogo
            } else {
                JOptionPane.showMessageDialog(this, "No se encontró la finanza para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }   
        } catch (NumberFormatException ex) {
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            JOptionPane.showMessageDialog(this, "Error al actualizar las finanzas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
