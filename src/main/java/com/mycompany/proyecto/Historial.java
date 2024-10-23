/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;


import java.time.LocalDateTime;
import java.util.ArrayList;

public class Historial {
    // Atributos
    private ArrayList<Accion> acciones;

    // Constructor
    public Historial() {
        this.acciones = new ArrayList<>();
    }

    // Método para agregar una acción al historial
    public void agregarAccion(String descripcion) {
        Accion nuevaAccion = new Accion(descripcion, LocalDateTime.now());
        acciones.add(nuevaAccion);
    }

    // Método para mostrar el historial de acciones
    public void mostrarHistorial() {
        System.out.println("Historial de Acciones:");
        for (Accion accion : acciones) {
            System.out.println(accion);
        }
    }

    // Clase interna para representar una acción
    private class Accion {
        private String descripcion;
        private LocalDateTime fechaHora;

        public Accion(String descripcion, LocalDateTime fechaHora) {
            this.descripcion = descripcion;
            this.fechaHora = fechaHora;
        }

        @Override
        public String toString() {
            return "Descripción: " + descripcion + ", Fecha y Hora: " + fechaHora;
        }
    }
}

