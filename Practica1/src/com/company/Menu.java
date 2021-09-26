package com.company;

import java.util.ArrayList;
import java.util.Scanner;

public class Menu {

    public static void main(String[] args) {

        Scanner escriure = new Scanner(System.in);

        int opcio, opcio2;
        
        boolean seguir= true;
        boolean seguir2 = true;

        for (int i = 0; i <= 10; i++) {

            int ale = (int) (Math.random() * 4 + 1);

            if (ale == 1) {

                double lado = (double) (Math.random() * 1000 + 1);

                Cuadrado cuadrado1 = new Cuadrado(lado);

                Lienzo.con.add(cuadrado1);

            }
            if (ale == 2){

                double baseAleatoria1 = (double) (Math.random()*1000+1);
                double alturaAleatoria1 = (double) (Math.random()*1000+1);

                Rectangulo rectangulo1 = new Rectangulo(baseAleatoria1, alturaAleatoria1);

                Lienzo.con.add(rectangulo1);

            }
            if (ale == 3){

                double radioAleatorio1 = (double) (Math.random()*1000+1);

                Circulo circulo1 = new Circulo(radioAleatorio1);

                Lienzo.con.add(circulo1);

            }
            if (ale == 4){

                double baseAleatoria1 = (double) (Math.random()*1000+1);
                double alturaAleatoria1 = (double) (Math.random()*1000+1);

                Triangulo triangulo1 = new Triangulo(baseAleatoria1, alturaAleatoria1);

                Lienzo.con.add(triangulo1);

            }
        }

        do{
            System.out.println("-------------------------");
            System.out.println("|  FIGURES GEOMETRIQUES |");
            System.out.println("|-----------------------|");
            System.out.println("| 1. Listar             |");
            System.out.println("| 2. Dibujar            |");
            System.out.println("| 3. Perimetros         |");
            System.out.println("| 4. Area               |");
            System.out.println("| 5. Escalar            |");
            System.out.println("| 6. Mover              |");
            System.out.println("| 7. Desplazar          |");
            System.out.println("| 8. Ordenar            |");
            System.out.println("| 9. Salir              |");
            System.out.println("-------------------------");
            System.out.println("Opcio del menu: ");
            opcio = escriure.nextInt();
            escriure.nextLine();

            switch(opcio){
                case 1:

                    System.out.println("--------------");
                    System.out.println("|   LISTAR   |");
                    System.out.println("--------------");

                    Lienzo.llistar();

                    break;

                case 2:

                    System.out.println("---------------");
                    System.out.println("|   DIBUJAR   |");
                    System.out.println("---------------");

                    Lienzo.dibujar();

                    break;

                case 3:

                    System.out.println("-----------------");
                    System.out.println("|   PERIMETRO   |");
                    System.out.println("-----------------");

                    Lienzo.perimetro();

                    break;

                case 4:

                    System.out.println("------------");
                    System.out.println("|   AREA   |");
                    System.out.println("------------");

                    Lienzo.area();

                    break;

                case 5:

                    System.out.println("---------------");
                    System.out.println("|   ESCALAR   |");
                    System.out.println("---------------");

                    System.out.println("ID de figura: ");
                    int id=escriure.nextInt();
                    escriure.nextLine();

                    System.out.println("Percentatge a escalar: ");
                    int per=escriure.nextInt();
                    escriure.nextLine();

                    Lienzo.escalar(id, per);

                    break;

                case 6:

                    System.out.println("-------------");
                    System.out.println("|   MOVER   |");
                    System.out.println("-------------");

                    Punto or=null;

                    System.out.println("ID de figura: ");
                    id=escriure.nextInt();
                    escriure.nextLine();

                    System.out.println("Valor de X: ");
                    double x=escriure.nextDouble();
                    escriure.nextLine();

                    System.out.println("Valor de Y: ");
                    double y=escriure.nextDouble();
                    escriure.nextLine();

                    Lienzo.mover(id, or);

                    break;

                case 7:

                    do {

                        System.out.println("-------------------");
                        System.out.println("|    DESPLAZAR    |");
                        System.out.println("-------------------");
                        System.out.println("| 1.- Horizontal  |");
                        System.out.println("| 2.- Vertical    |");
                        System.out.println("| 4.- Salir       |");
                        System.out.println("-------------------");
                        System.out.println("Opcio per a desplaçar:");
                        opcio2 = escriure.nextInt();
                        escriure.nextLine();

                        switch (opcio2){

                            case 1:

                                System.out.println("HORIZONTAL");
                                System.out.println("");

                                System.out.println("ID de figura:");
                                id=escriure.nextInt();
                                escriure.nextLine();

                                System.out.println("Cuantes vols desplaçar?");
                                x = escriure.nextDouble();
                                escriure.nextLine();

                                Lienzo.desplazarh(id, x);

                                break;

                            case 2:

                                System.out.println("VERTICAL");
                                System.out.println("");

                                System.out.println("ID de figura:");
                                id= escriure.nextInt();
                                escriure.nextLine();

                                System.out.println("Cuantes vols desplaçar?");
                                y = escriure.nextDouble();
                                escriure.nextLine();

                                Lienzo.desplazarv(id, y);

                                break;

                            case 3:

                                System.out.println("Has eixit!");
                                seguir2 = false;

                                break;
                        }


                    }while (seguir2);

                    break;

                case 8:

                    System.out.println("Has eixit!");
                    seguir = false;

                    break;

                default:

                    System.out.println("No es una opcio");
                    seguir = false;

            }

        }while(seguir);

    }
}
