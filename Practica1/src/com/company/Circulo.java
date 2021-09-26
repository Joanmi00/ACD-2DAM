package com.company;

public class Circulo extends Figura{
    private static double radio;

    public Circulo (double radio){

        this.radio = radio;

    }

    @Override
    public double area() {

        double areaCirculo;

        areaCirculo = Math.PI * getRadio() * getRadio();

        return areaCirculo;

    }

    @Override
    public double perimetro() {

        double perimetroCirculo;

        perimetroCirculo = Math.PI * 2 * getRadio();

        return perimetroCirculo;


    }

    public void escalar(int porcentaje){

        double radio2 = (porcentaje * this.getRadio()) / 100;

        this.setRadio(radio2);

    }


    public static double getRadio() {
        return radio;
    }


    public static void setRadio(double aRadio) {
        radio = aRadio;
    }

    @Override
    public String toString() {

        return("Circulo; Id: " + this.getId() + "; Posición: " + this.getOrigen());

    }

}
