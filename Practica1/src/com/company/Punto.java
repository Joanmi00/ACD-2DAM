package com.company;

public class Punto {
    private static double x;
    private static double y;

    public void Punto(double x2, double y2){

        this.x = x2;
        this.y = y2;

    }


    public static double getX() {
        return x;
    }


    public static void setX(double aX) {
        x = aX;
    }


    public static double getY() {
        return y;
    }


    public static void setY(double aY) {
        y = aY;
    }

    @Override
    public String toString() {
        return ("x: " + this.x + "y: " + this.y);
    }
}
