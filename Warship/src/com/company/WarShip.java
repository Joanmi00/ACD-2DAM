package com.company;

import Utils.ConsoleColors;
import Utils.Leer;
import java.io.*;
import java.util.*;
import java.util.Random;

public class WarShip {

    static int MAX_JUGADAS = 100;
    public static boolean salir = false;

    private Random r;
    private Board board;
    private WarShip ws;
    private int e;

    public static void main(String[] args) {
        // TODO code application logic here
        WarShip ws = new WarShip();
        int opcio = 0;
        do {
            System.out.println(ConsoleColors.GREEN + "--    Escollir   --");
            System.out.println(ConsoleColors.GREEN + "1. Joc automàtic...");
            System.out.println(ConsoleColors.GREEN + "2. Joc manual......");
            System.out.println(ConsoleColors.GREEN + "3. Joc cargat......");
            opcio = Leer.leerEntero(ConsoleColors.CYAN + "Indica el tipus de joc que vols: " + ConsoleColors.RESET);
        } while (opcio < 1 || opcio > 3);

        switch (opcio) {

            case 1 -> ws.autoPlay();
            case 2 -> ws.play();
            case 3 -> ws.juegoCargado();
        }

        guardaConfi();
    }

    public static void config() {
        Properties configuracion = new Properties();

        try {
            configuracion.load(new FileInputStream("warship.properties"));
            Board.BOARD_DIM = Integer.parseInt(configuracion.getProperty("Tamaño_tablero"));
            Board.BOARD_BOATS_COUNT = Integer.parseInt(configuracion.getProperty("Numero_barcos"));
            MAX_JUGADAS = Integer.parseInt(configuracion.getProperty("Numero_jugadas"));

        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());
        }
    }

    public static void guardaConfi() {

        Properties configuracion = new Properties();
        configuracion.setProperty("Tamaño_tablero", String.valueOf(Board.BOARD_DIM));
        configuracion.setProperty("Numero_barcos", String.valueOf(Board.BOARD_BOATS_COUNT));
        configuracion.setProperty("Numero_jugadas", String.valueOf(MAX_JUGADAS));

        try {
            configuracion.store(new FileOutputStream("warship.properties"), "Fichero de configuracion");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public WarShip() {

        config();

        r = new Random(System.currentTimeMillis());
        board = new Board();

        do {

            System.out.println("1. Generar barcos aleatoriamente");
            System.out.println("2. Cargar barcos de la anterior partida");
            e = Leer.leerEntero("Elija un opcion: ");

        }while (e<1 || e>2);

        switch (e) {

            case 1 -> board.initBoats();
            case 2 -> board.FichBarcos();
        }
    }

    private void autoPlay() {
        board.paint();
        // Vamos a realizar 50 jugadas aleatorias ...
        for (int i = 1; i <= MAX_JUGADAS; i++) {

            System.out.println(ConsoleColors.GREEN_BRIGHT + "JUGADA: " + i);
            int fila, columna;

            do {
                fila = r.nextInt(Board.BOARD_DIM);
                columna = r.nextInt(Board.BOARD_DIM);

            } while (board.fired(fila, columna));
            if (board.shot(fila, columna) != Cell.CELL_WATER) {
                board.paint();

            } else {
                System.out.println("(" + fila + "," + columna + ") --> AGUA");

            }
            if (board.getEnd_Game()) {

                System.out.printf("Joc acabat amb %2d jugades\n", i);
                break;
            }
        }
    }

    private void play() {

        int num_jugadas = 0;
        boolean rendit = false;
        String jugada;
        int fila = -1, columna = -1;

        do {
            do {

                jugada = Leer.leerTexto("Dime la jugada en dos letras A3, B5... de A0 a J9: ").toUpperCase();

                if (jugada.equalsIgnoreCase("00")) {

                    System.out.println("Jugador rendit");
                    rendit = true;
                    break;
                }
                if (jugada.length() == 0 || jugada.length() > 2) {

                    System.out.println("Format incorrecte.");
                    continue;
                }

                fila = jugada.charAt(0) - 'A';
                columna = jugada.charAt(1) - '0';
            } while (board.fired(fila, columna));
            // acaba el joc
            if (rendit) {
                break;
            }

            num_jugadas++;
            if (board.shot(fila, columna) != Cell.CELL_WATER) {
                board.paintGame();

            } else {
                System.out.println("(" + fila + "," + columna + ") --> AGUA");

            }
            if (board.getEnd_Game()) {

                System.out.printf("Joc acabat amb %2d jugades\n", num_jugadas);
                break;
            }
        } while (num_jugadas < MAX_JUGADAS);
    }

    private void juegoCargado() {
        FileReader fr = null;

        try {

            File f = new File("moviments_in.txt");
            fr = new FileReader(f);
            BufferedReader bfr = new BufferedReader(fr);

            while (bfr.ready()) {

                String linea = bfr.readLine();
                String[] items = linea.split(";");
                System.out.println(ConsoleColors.GREEN_BRIGHT + "JUGADA: " + items[0]);
                int fila, columna;

                do {
                    fila = Integer.parseInt(items[1]);
                    columna =Integer.parseInt(items[2]) ;

                } while (board.fired(fila, columna));
                if (board.shot(fila, columna) != Cell.CELL_WATER) {
                    board.paint();

                } else {
                    System.out.println("(" + fila + "," + columna + ") --> AGUA");
                }
                if (board.getEnd_Game()) {

                    System.out.printf("Joc acabat amb %2d jugades\n",  items[0]);
                    break;
                }
            }
        } catch (FileNotFoundException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            System.out.println(e.getMessage());

        }
    }

}
