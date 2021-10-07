package com.company;

import java.io.File;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Scanner es = new Scanner (System.in);

        boolean seguir = true;

        do{
            System.out.println("------------------");
            System.out.println("|      MENU      |");
            System.out.println("|----------------|");
            System.out.println("| 1- Llista      |");
            System.out.println("| 2- Columnes    |");
            System.out.println("| 3- Taules      |");
            System.out.println("| 4- Eixir       |");
            System.out.println("------------------");

            System.out.print("Opció del Menu:");
            int res = es.nextInt();

            switch (res) {

                case 1 -> Llista();
                case 2 -> {
                    String Activitat = System.getProperty("user.dir");
                    File Carpeta = new File(Activitat);
                    String[] Llistat = Carpeta.list();
                    ListaColumnas(Llistat);
                }
                case 3 -> {
                    File a = new File("D:\\Institut\\2nDAM\\ACD\\Ficheros");
                    Taules(a);
                }

                case 4 -> {
                    System.out.println("Has eixit del programa.");
                    seguir = false;
                }
                default -> {
                    System.out.println(res + "No es una opció valida.");
                    seguir = false;
                }
            }

        }while (seguir);



    }

    public static void Llista(){

        String Activitat = System.getProperty("user.dir");
        File Carpeta = new File(Activitat);
        String[] Llistat = Carpeta.list();

        if (Llistat == null || Llistat.length == 0) {

            System.out.println("No hi ha res dins del directori");

        }
        else {

            for (String i : Llistat) {

                System.out.println(i);

            }
        }
    }

    public static void ListaColumnas(String[] filenames){

        int MAX_FILES_BY_COLUMN = 5;

        int filas = (filenames.length / MAX_FILES_BY_COLUMN) + 1;
        String[][] salida = new String[filas][MAX_FILES_BY_COLUMN];

        for (int i = 0; i < filenames.length; i++){
            salida[i / MAX_FILES_BY_COLUMN][i % MAX_FILES_BY_COLUMN] = filenames[i];
        }

        //bucle para mostrar salida
        for (int i = 0; i <filas; i++){
            for (int j = 0; j < MAX_FILES_BY_COLUMN; j++) {
                System.out.print(salida[i][j] + " - ");
            }
            System.out.println(" /");
        }

    }

    public static void Taules(File a){

        String info = "";

        for (File A : a.listFiles()){

            if (A.isDirectory()){

                info += "D";
            }

            if (A.isFile()){

                info += "F";
            }

            if (A.canRead()){

                info +="R";
            }

            if (A.canWrite()){

                info += "W";
            }

            if (A.isHidden()){

                info += "H";
            }

            info += " / " + A.getName() + " / " +A.length() + " / " + A.lastModified() + "\n";
            System.out.println(info);
        }

    }

}
