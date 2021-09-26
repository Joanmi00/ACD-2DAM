package com.company;

public class Cuadrado extends Figura{
    private static double lado;

    public Cuadrado(double lado){

        this.lado = lado;

    }

    @Override
    public double area() {

        double areaCuadrado;

        areaCuadrado = getLado() * getLado();

        return areaCuadrado;

    }

    @Override
    public double perimetro() {

        double perimetroCuadrado;

        perimetroCuadrado = getLado() + getLado() + getLado() + getLado();

        return perimetroCuadrado;

    }

    public void escalar(int porcentaje){

        double lado2 = (porcentaje * this.getLado()) / 100;

        this.setLado(lado2);

    }


    public static double getLado() {
        return lado;
    }


    public static void setLado(double aLado) {
        lado = aLado;
    }

    @Override
    public String toString() {

        return("Cuadrado; Id: " + this.getId() + "; Posición: " + this.getOrigen().toString());

    }
}
