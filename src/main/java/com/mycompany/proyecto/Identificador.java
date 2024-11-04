
package com.mycompany.proyecto;

public class Identificador {
    private int id;
    private String fecha;

    // Constructor
    public Identificador(int id, String fecha) {
        this.id = id;
        this.fecha = fecha;
    }

    // Getters y Setters
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFecha() {
        return fecha;
    }

    public void setFecha(String fecha) {
        this.fecha = fecha;
    }
}


