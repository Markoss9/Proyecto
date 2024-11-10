
package com.mycompany.proyecto;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;

public class CalendarioConNotas extends JFrame {
    private String usuario;
    private JFrame ventanaPrincipal;
    private JComboBox<String> comboMes;
    private JComboBox<Integer> comboAño;
    private JPanel panelCalendario;
    private final String[] diasDeLaSemana = {"L", "Ma", "Mi", "J", "V", "S", "D"};
    private GestorDeBaseDeDatos gestorBD;

    public CalendarioConNotas(String usuario) {
        this.usuario = usuario;
        gestorBD = new GestorDeBaseDeDatos(usuario);
        ventanaPrincipal = new JFrame("Calendario");
        ventanaPrincipal.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        ventanaPrincipal.setSize(400, 300);

        comboMes = new JComboBox<>(new String[]{"Enero", "Febrero", "Marzo", "Abril", "Mayo", "Junio",
                "Julio", "Agosto", "Septiembre", "Octubre", "Noviembre", "Diciembre"});
        comboAño = new JComboBox<>();
        for (int i = 2024; i <= 2030; i++) {
            comboAño.addItem(i);
        }

        JPanel panelSuperior = new JPanel();
        panelSuperior.add(comboMes);
        panelSuperior.add(comboAño);

        panelCalendario = new JPanel(new GridLayout(0, 7));
        for (String dia : diasDeLaSemana) {
            JLabel etiquetaDia = new JLabel(dia, SwingConstants.CENTER);
            etiquetaDia.setOpaque(true);
            etiquetaDia.setBackground(Color.BLUE);
            etiquetaDia.setForeground(Color.WHITE);
            panelCalendario.add(etiquetaDia);
        }

        ventanaPrincipal.add(panelSuperior, BorderLayout.NORTH);
        ventanaPrincipal.add(panelCalendario, BorderLayout.CENTER);

        comboMes.addActionListener(e -> actualizarCalendario());
        comboAño.addActionListener(e -> actualizarCalendario());

        actualizarCalendario();
        ventanaPrincipal.setVisible(true);
    }

    private void actualizarCalendario() {
        panelCalendario.removeAll();
        for (String dia : diasDeLaSemana) {
            JLabel etiquetaDia = new JLabel(dia, SwingConstants.CENTER);
            etiquetaDia.setOpaque(true);
            etiquetaDia.setBackground(Color.BLUE);
            etiquetaDia.setForeground(Color.WHITE);
            panelCalendario.add(etiquetaDia);
        }

        int mesSeleccionado = comboMes.getSelectedIndex();
        int anioSeleccionado = (Integer) comboAño.getSelectedItem();

        java.util.Calendar calendario = java.util.Calendar.getInstance();
        calendario.set(anioSeleccionado, mesSeleccionado, 1);
        int diasEnMes = calendario.getActualMaximum(java.util.Calendar.DAY_OF_MONTH);
        int diaInicio = calendario.get(java.util.Calendar.DAY_OF_WEEK) - 1;

        for (int i = 0; i < diaInicio; i++) {
            panelCalendario.add(new JLabel(""));
        }

        for (int dia = 1; dia <= diasEnMes; dia++) {
            JButton botonDia = new JButton(String.valueOf(dia));
            int diaSeleccionado = dia;

            botonDia.addActionListener(e -> abrirDialogoDeNota(diaSeleccionado, mesSeleccionado, anioSeleccionado));
            panelCalendario.add(botonDia);
        }

        panelCalendario.revalidate();
        panelCalendario.repaint();
    }

    private void abrirDialogoDeNota(int dia, int mes, int anio) {
        String fecha = anio + "-" + (mes + 1) + "-" + dia;
        String notaExistente = gestorBD.obtenerNotaParaFecha(fecha);

        JTextField campoNota = new JTextField(notaExistente);
        int opcion = JOptionPane.showOptionDialog(
            ventanaPrincipal, new Object[]{"Ingrese o edite su nota:", campoNota},
            "Nota para " + fecha, JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE,
            null, new String[]{"Guardar", "Eliminar", "Cancelar"}, "Guardar");

        if (opcion == JOptionPane.OK_OPTION) {
            String nuevaNota = campoNota.getText();
            if (!nuevaNota.isEmpty()) {
                gestorBD.guardarNotaParaFecha(fecha, nuevaNota);
            }
        } else if (opcion == JOptionPane.NO_OPTION) {
            gestorBD.eliminarNotaParaFecha(fecha);
        }
    }
}