package com.company;

public abstract class Figura implements  Dibujable{
    public static Punto origen = new Punto();

    public abstract double area();

    public abstract double perimetro();


    public abstract void escalar(int porcentaje);

    public void mover(Punto origen2){

        this.setOrigen(origen2);

    }

    private static int id;

    public Punto getOrigen() {
        return origen;
    }


    public void setOrigen(Punto origen) {
        this.origen = origen;
    }


    public static int getId() {
        return id;
    }

    @Override
    public void dibujar(){

    }

    @Override
    public void rellenar(){

    }
}
