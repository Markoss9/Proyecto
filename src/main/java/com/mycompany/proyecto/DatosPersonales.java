/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.mycompany.proyecto;


public class DatosPersonales {
   
    private String nacimiento;
    private String DNI;
    private String edad;
    private String direccion;
    private String celular;
    private String genero;
    
    
    public DatosPersonales(String nacimiento,String DNI, String edad, String direccion, String celular,String genero) {
        this.nacimiento = nacimiento;
        this.DNI = DNI;
        this.edad = edad;
        this.direccion = direccion;
        this.celular = celular;
        this.genero = genero;
        
    
    }
    @Override
    public String toString () {
        return "(fecha de nacimiento:"+nacimiento+"DNI:"+DNI+"Edad:"+edad+    
                    "Direccion:"+direccion+"Celular"+celular+"Genero:"+genero+")";
    }

    public String getNacimiento() {
        return nacimiento;
    }

    public void setNacimiento(String nacimiento) {
        this.nacimiento = nacimiento;
    }

    public String getDNI() {
        return DNI;
    }

    public void setDNI(String DNI) {
        this.DNI = DNI;
    }

    public String getEdad() {
        return edad;
    }

    public void setEdad(String edad) {
        this.edad = edad;
    }

    public String getDireccion() {
        return direccion;
    }

    public void setDireccion(String direccion) {
        this.direccion = direccion;
    }

    public String getCelular() {
        return celular;
    }

    public void setCelular(String celular) {
        this.celular = celular;
    }

    public String getGenero() {
        return genero;
    }

    public void setGenero(String genero) {
        this.genero = genero;
    }
    
    
}

