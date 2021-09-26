package com.company;

import java.util.ArrayList;

public class Lienzo {
    private static int cantitat = 10;

    static ArrayList<Figura> con = new ArrayList(cantitat);

    public static void dibujar(){

        for (int i = 0; i < con.size(); i++){

            System.out.println("Dibujamos: " + con.get(i));

        }

    }

    public static void area(){

        for (int i = 0; i < con.size(); i++){

            System.out.println("Area: " + con.get(i).area());

        }

    }

    public static void perimetro(){

        for (int i = 0; i < con.size(); i++){

            System.out.println("Perimetro: " + con.get(i).perimetro());

        }

    }



    public static void llistar(){

        for (int i = 0; i < con.size(); i++){

            System.out.println(con.get(i).toString());

        }

    }

    public static void escalar(int id, int percentatge){

        for (int i = 0; i < con.size(); i++){

            if (con.get(i).getId() == id){

                con.get(i).escalar(percentatge);

            }
            else {

                System.out.println("No hi ha ninguna figura amb eixa ID");

            }

        }

    }

    public static void mover(int id, Punto origen){

        for (int i = 0; i < con.size(); i++){

            if (con.get(i).getId() == id){

                con.get(i).setOrigen(origen);

            }

        }

    }

    public static void desplazarh(int id, double x){

        for (int i = 0; i < con.size(); i++){

            if (con.get(i).getId() == id){

                if (x > 0){

                    con.get(i).origen.setX(con.get(i).origen.getX() + x);

                }
                else {

                    con.get(i).origen.setX(con.get(i).origen.getX() - x);

                }

            }

        }

    }

    public static void desplazarv(int id, double y){

        for (int i = 0; i < con.size(); i++){

            if (con.get(i).getId() == id){

                if (y > 0){

                    con.get(i).origen.setY(con.get(i).origen.getY() + y);

                }
                else {

                    con.get(i).origen.setY(con.get(i).origen.getY() - y);

                }

            }

        }

    }


}
