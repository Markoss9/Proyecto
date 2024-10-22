/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;


public class Mensajeria {
    private Identificador identificador; // Composición
    private String contacto;
    private String mensaje;
    private String hora;
    private String remitente;

    // Constructor
    public Mensajeria(int id, String fecha, String contacto, String mensaje, String hora, String remitente) {
        this.identificador = new Identificador(id, fecha);
        this.contacto = contacto;
        this.mensaje = mensaje;
        this.hora = hora;
        this.remitente = remitente;
    }

    // Getters y Setters
    public int getId() {
        return identificador.getId();
    }

    public void setId(int id) {
        identificador.setId(id);
    }

    public String getFecha() {
        return identificador.getFecha();
    }

    public void setFecha(String fecha) {
        identificador.setFecha(fecha);
    }

    public String getContacto() {
        return contacto;
    }

    public void setContacto(String contacto) {
        this.contacto = contacto;
    }

    public String getMensaje() {
        return mensaje;
    }

    public void setMensaje(String mensaje) {
        this.mensaje = mensaje;
    }

    public String getHora() {
        return hora;
    }

    public void setHora(String hora) {
        this.hora = hora;
    }

    public String getRemitente() {
        return remitente;
    }

    public void setRemitente(String remitente) {
        this.remitente = remitente;
    }
}


