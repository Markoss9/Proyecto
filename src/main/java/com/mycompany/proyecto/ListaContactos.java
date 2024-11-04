package com.mycompany.proyecto;

import java.util.ArrayList;

public class ListaContactos {
    private ArrayList<Contactos> listaContactos;

    public ListaContactos() {
        this.listaContactos = new ArrayList<>();
    }

    public ArrayList<Contactos> getListaContactos() {
        return listaContactos;
    }

    public void setListaContactos(ArrayList<Contactos> listaContactos) {
        this.listaContactos = listaContactos;
    }
    
    public void agregarContacto(Contactos c){
        listaContactos.add(c);
    }
    
    
    
}
