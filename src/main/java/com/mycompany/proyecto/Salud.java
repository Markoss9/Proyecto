package com.mycompany.proyecto;


public class Salud {
    private float peso;
    private float altura;
    private float caloriasconsumidas;
    private float caloriasquemadas;

    public Salud() {
    }
    
    
public Salud (float peso, float altura){
    this.peso = peso;
    this.altura = altura;
    this.caloriasconsumidas = 0;
    this.caloriasquemadas = 0;
}

    public float getPeso() {
        return peso;
    }

    public void setPeso(float peso) {
        this.peso = peso;
    }

    public float getAltura() {
        return altura;
    }

    public void setAltura(float altura) {
        this.altura = altura;
    }

    public float getCaloriasconsumidas() {
        return caloriasconsumidas;
    }

    public void setCaloriasconsumidas(float caloriasconsumidas) {
        this.caloriasconsumidas = caloriasconsumidas;
    }

    public float getCaloriasquemadas() {
        return caloriasquemadas;
    }

    public void setCaloriasquemadas(float caloriasquemadas) {
        this.caloriasquemadas = caloriasquemadas;
    }

    public void agregarCalorias(float calorias){
        this.caloriasconsumidas = this.caloriasconsumidas + calorias;
    }
    
    public float calcularIMC(){
        float imc = this.peso / (this.altura*this.altura);
        return imc;
    }
    
    public String evaluarIMC(){
        
        float imc = calcularIMC();
        
        if (imc<=18.5){
            return ("Peso bajo");
        }else if (imc>18.5 & imc<=24.9){
            return ("Peso normal");
        }else if (imc>24.9 & imc<=29.9){
            return ("Sobrepeso");
        }else {
            return ("Obesidad");
        }
    }
    
    
}
