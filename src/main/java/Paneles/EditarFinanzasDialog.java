package paneles;

import com.mycompany.proyecto.Finanzas;
import javax.swing.*;
import java.awt.*;
import java.sql.Connection;
import java.sql.SQLException;

public class EditarFinanzasDialog extends JDialog {

    private JTextField txtIngresos;
    private JTextField txtGastos;
    private Connection conexion;
    private int dni;

    private JFrame parent;
    // Atributos para almacenar ingresos y gastos
    private float ingresos;
    private float gastos;

    public EditarFinanzasDialog(JFrame parent, int dni, Connection conexion, float ingresos, float gastos) {
        super(parent, "Editar Finanzas", true);
        this.parent = parent; // Guarda el JFrame padre
        this.dni = dni;
        this.conexion = conexion;
        this.ingresos = ingresos; // Almacena los ingresos
        this.gastos = gastos;     // Almacena los gastos
        inicializarComponentes();  // Inicializa los componentes
    }

    private void inicializarComponentes() {
        setLayout(new GridLayout(3, 2));

        // Crear y agregar campos de texto
        JLabel lblIngresos = new JLabel("Ingresos:");
        txtIngresos = new JTextField(String.valueOf(ingresos)); // Muestra el ingreso actual
        JLabel lblGastos = new JLabel("Gastos:");
        txtGastos = new JTextField(String.valueOf(gastos)); // Muestra el gasto actual

        JButton btnGuardar = new JButton("Guardar");
        btnGuardar.addActionListener(e -> {
            // Lógica para guardar los cambios en la base de datos
            guardarFinanza();
        });

        add(lblIngresos);
        add(txtIngresos);
        add(lblGastos);
        add(txtGastos);
        add(btnGuardar);

        pack(); // Ajusta el tamaño del diálogo
        setLocationRelativeTo(parent); // Centra el diálogo respecto al padre
    }

    private void guardarFinanza() {
        try {
            // Obtener los nuevos ingresos y gastos desde los campos de texto
            float nuevosIngresos = Float.parseFloat(txtIngresos.getText());
            float nuevosGastos = Float.parseFloat(txtGastos.getText());

            // Crear una nueva instancia de Finanzas
            Finanzas finanzas = new Finanzas();

            // Establecer los nuevos valores de ingreso y gasto
            finanzas.setIngreso(nuevosIngresos, conexion);
            finanzas.setGastos(nuevosGastos,conexion);
            finanzas.setDni(dni); // Establecer el DNI del registro que se está editando

            // Actualizar las finanzas en la base de datos
            finanzas.actualizarFinanzas(conexion); // Llama al método que actualiza los datos en la base de datos

            // Cerrar el diálogo después de actualizar
            dispose();
        } catch (NumberFormatException e) {
            // Manejar la excepción si la entrada no es válida
            JOptionPane.showMessageDialog(this, "Por favor, ingresa valores válidos para ingresos y gastos.", "Error de entrada", JOptionPane.ERROR_MESSAGE);
        } catch (SQLException e) {
            // Manejar la excepción en caso de error al actualizar en la base de datos
            JOptionPane.showMessageDialog(this, "Error al actualizar finanzas: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
        }
    }

}
