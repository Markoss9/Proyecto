package Paneles;

import com.mycompany.proyecto.Finanzas;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
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

    // Constructor para inicializar el diálogo de edición de finanzas
    public EditarFinanzasDialog(ActualizarFinanzasPanel parent, int id, int dniUsuario, Connection connection, float ingreso, float gastos) {
        super(parent, "Editar Finanzas", true);  // Configura el título y la modalidad del diálogo
        this.connection = connection;  // Conexión a la base de datos
        this.id = id;  // ID de la finanza a editar
        this.dniUsuario = dniUsuario;  // DNI del usuario para asociar las finanzas
        initComponents(ingreso, gastos);  // Inicializa los componentes del diálogo
        this.parentPanel = parent;  // Referencia al panel principal para actualizar la tabla de finanzas
    }

    // Método para inicializar los componentes visuales del diálogo
    private void initComponents(float ingreso, float gastos) {
        // Configuración básica del diálogo
        setLayout(null);  // Layout sin restricciones para controlar el tamaño y la ubicación
        setSize(300, 200);  // Tamaño del diálogo
        setLocationRelativeTo(null);  // Centra el diálogo en la pantalla

        // Etiqueta y campo de texto para el ingreso
        JLabel lblIngreso = new JLabel("Ingreso:");
        lblIngreso.setBounds(20, 20, 100, 25);  // Ubicación y tamaño del componente
        add(lblIngreso);

        txtIngreso = new JTextField(String.valueOf(ingreso));  // Inicializa el campo de texto con el valor de ingreso
        txtIngreso.setBounds(120, 20, 150, 25);  // Ubicación y tamaño del campo de texto
        add(txtIngreso);

        // Etiqueta y campo de texto para los gastos
        JLabel lblGastos = new JLabel("Gastos:");
        lblGastos.setBounds(20, 60, 100, 25);
        add(lblGastos);

        txtGastos = new JTextField(String.valueOf(gastos));  // Inicializa el campo de texto con el valor de gastos
        txtGastos.setBounds(120, 60, 150, 25);
        add(txtGastos);

        // Botón de "Guardar"
        btnGuardar = new JButton("Guardar");
        btnGuardar.setBounds(40, 120, 100, 30);  // Configuración del botón
        add(btnGuardar);

        // Botón de "Cancelar"
        btnCancelar = new JButton("Cancelar");
        btnCancelar.setBounds(160, 120, 100, 30);  // Configuración del botón
        add(btnCancelar);

        // Acción del botón "Guardar" para guardar los cambios en la base de datos
        btnGuardar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                guardarCambios();  // Llama al método para guardar los cambios
            }
        });

        // Acción del botón "Cancelar" para cerrar el diálogo sin guardar cambios
        btnCancelar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();  // Cierra el diálogo sin hacer cambios
            }
        });
    }

    // Método para guardar los cambios realizados en los campos de texto en la base de datos
    private void guardarCambios() {
        try {
            // Obtiene los nuevos valores de ingreso y gastos
            float nuevoIngreso = Float.parseFloat(txtIngreso.getText());
            float nuevoGastos = Float.parseFloat(txtGastos.getText());

            // Imprime el ID que se está utilizando (para depuración)
            System.out.println("ID que se busca: " + id);

            // Busca la finanza en la base de datos utilizando el ID y el DNI del usuario
            Finanzas finanza = Finanzas.buscarFinanzaPorId(connection, id, dniUsuario);
            if (finanza != null) {
                // Si se encuentra la finanza, actualiza los valores de ingreso y gastos
                finanza.setIngreso(nuevoIngreso, connection);
                finanza.setGastos(nuevoGastos, connection);

                // Actualiza la tabla de finanzas en el panel principal
                parentPanel.cargarTablaFinanzas();

                // Cierra el diálogo después de guardar los cambios
                dispose();
            } else {
                // Si no se encuentra la finanza, muestra un mensaje de error
                JOptionPane.showMessageDialog(this, "No se encontró la finanza para actualizar.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (NumberFormatException ex) {
            // Captura errores en el formato de los números (por ejemplo, si el usuario ingresa texto en lugar de un número)
            JOptionPane.showMessageDialog(this, "Por favor ingrese valores numéricos válidos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException ex) {
            // Captura errores al realizar operaciones con la base de datos
            JOptionPane.showMessageDialog(this, "Error al actualizar las finanzas: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
