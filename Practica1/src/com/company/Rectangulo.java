package com.company;

public class Rectangulo extends Figura{

    private static double base;

    private static double altura;

    public Rectangulo (double base, double altura){

        this.base = base;

        this.altura = altura;

    }

    @Override
    public double area() {

        double areaRectangulo;

        areaRectangulo = getBase() * getAltura();

        return areaRectangulo;

    }

    @Override
    public double perimetro() {

        double perimetroRectangulo;

        perimetroRectangulo = getBase() + getBase() + getAltura() + getAltura();

        return perimetroRectangulo;

    }

    public void escalar(int porcentaje){

        double base2 = (porcentaje * this.getBase()) / 100;

        this.setBase(base2);

        double altura2 = (porcentaje * this.getAltura()) / 100;

        this.setAltura(altura2);

    }


    public static double getBase() {
        return base;
    }


    public static void setBase(double aBase) {
        base = aBase;
    }


    public static double getAltura() {
        return altura;
    }


    public static void setAltura(double aAltura) {
        altura = aAltura;
    }

    @Override
    public String toString() {

        return("Rectangulo; Id: " + this.getId() + "; Posición: " + this.getOrigen());

    }
}
