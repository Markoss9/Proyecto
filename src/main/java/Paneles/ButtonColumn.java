package Paneles;

import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class ButtonColumn extends AbstractCellEditor implements TableCellRenderer, TableCellEditor, ActionListener {

    private JButton button;   // Botón que se mostrará en la celda
    private String text;      // Texto que se mostrará en el botón
    private int column;       // Índice de la columna

    public ButtonColumn(JTable table, ActionListener listener, int column) {
        this.column = column;  // Almacena el índice de la columna
        button = new JButton(); // Crea el botón
        button.addActionListener(this); // Agrega el ActionListener al botón
        table.getColumnModel().getColumn(column).setCellRenderer(this); // Establece el renderizador para la columna
        table.getColumnModel().getColumn(column).setCellEditor(this); // Establece el editor para la columna
        // Añade el ActionListener proporcionado para manejar eventos
        button.addActionListener(listener);
    }

    @Override
    public Object getCellEditorValue() {
        return text; // Retorna el texto del botón como valor del editor
    }

    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row) {
        text = (value == null) ? "" : value.toString(); // Obtiene el texto a mostrar
        button.setText(text); // Establece el texto del botón
        return button; // Retorna el botón como componente del editor
    }

    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row) {
        button.setText((value == null) ? "" : value.toString()); // Establece el texto del botón
        if (isSelected) {
            button.setForeground(table.getSelectionForeground()); // Establece color del texto si está seleccionado
            button.setBackground(table.getSelectionBackground()); // Establece color de fondo si está seleccionado
        } else {
            button.setForeground(table.getForeground()); // Color de texto normal
            button.setBackground(UIManager.getColor("Button.background")); // Color de fondo normal
        }
        return button; // Retorna el botón como componente del renderizador
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        fireEditingStopped(); // Notifica que la edición ha terminado
    }

    @Override
    public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }

    @Override
    public Component getTableCellEditorComponent(JTable table, Object value, boolean isSelected, int row, int column) {
        throw new UnsupportedOperationException("Not supported yet."); // Generated from nbfs://nbhost/SystemFileSystem/Templates/Classes/Code/GeneratedMethodBody
    }
}
