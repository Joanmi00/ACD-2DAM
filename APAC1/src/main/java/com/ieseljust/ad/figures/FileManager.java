package com.ieseljust.ad.figures;

import org.json.JSONArray;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.ParserConfigurationException;
import javax.xml.transform.Transformer;
import javax.xml.transform.TransformerException;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.stream.StreamResult;
import java.io.*;


class FileManager {

    public FileManager() {

    }


    private boolean validaInt(String s) {
        try {
            Integer.parseInt(s);
        } catch (NumberFormatException | NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    public Boolean Exists(String file) {
        /**
         * **************************************
         * TO-DO: Mètode a implementar: * Retorna si el fitxer existeix o no *
         * ***************************************
         */

        File fil = new File(file);

        // Comentar o elimina aquestes línies quan implementeu el mètode
        return fil.exists();

    }

    public Escena importFromText(String file) {

        /**
         * *********************************************************
         * TO-DO: Mètode a implementar: * Llegirà el fitxer indicat, en format
         * text, i importarà * la llista de figures. *
         * **********************************************************
         */
        /*
            dimensions 500 500
            rectangle 10 10 480 480 #ccccee
            cercle 250 250 100 #aaaaaa
         */
        Escena escena = null;
        FileReader fr = null;

        try {
            escena = new Escena();
            File fil = new File(file);
            fr = new FileReader(fil);
            BufferedReader bf = new BufferedReader(fr);

            while (bf.ready()) {
                String linea = bf.readLine();
                String i[] = linea.split(" ");
                switch (i[0]) {
                    case "cercle":
                        escena.LlistaFigures.add(new Cercle(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[3]), i[4]));
                        break;
                    case "rectangle":
                        escena.LlistaFigures.add(new Rectangle(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[3]), Integer.parseInt(i[4]), i[5]));
                        break;
                    case "linia":
                        escena.LlistaFigures.add(new Linia(Integer.parseInt(i[1]), Integer.parseInt(i[2]), Integer.parseInt(i[3]), Integer.parseInt(i[4]), i[5]));
                        break;
                    case "dimensions":
                        escena.dimensions(Integer.parseInt(i[1]), Integer.parseInt(i[2]));
                        break;
                    default:
                        System.out.println("ERROR!!");
                }
            }
        }catch (IOException e){
            e.printStackTrace();
        }finally {
            try {
                fr.close();
            }catch (IOException e){
                e.printStackTrace();
            }
        }

        return escena;

    }

    public Escena importFromObj(String file) {

        /**
         * **********************************************************************
         * TO-DO: Mètode a implementar: * Llegirà el fitxer indicat, en format
         * d'objectes seriats, i importa * la llista de figures. *
         * **********************************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        Escena escena = null;
        ObjectInputStream ois = null;

        try {
            escena = new Escena();
            FileInputStream fi = new FileInputStream(file);
            ois = new ObjectInputStream(fi);

            while (ois.available() > 0) {
                Figura figu = (Figura) ois.readObject();
                escena.LlistaFigures.add(figu);
            }
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        } finally {
            try {
                ois.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }

        return escena;

    }

    public Boolean exportText(Escena escena, String file) {

        /**
         * ************************************************
         * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer de
         * text, * en format per poder ser importat posteriorment.*
         * ************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        FileWriter fw = null;
        try {
            File fil = new File(file);
            fw = new FileWriter(fil);
            BufferedWriter bfw = new BufferedWriter(fw);
            bfw.write(escena.getX() + " " + escena.getY());
            bfw.newLine();

            for (Figura figu : escena.LlistaFigures) {
                bfw.write(figu.getAsText());
                bfw.newLine();
            }

            out = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fw.close();
            } catch (IOException e) {
                e.printStackTrace();            }
        }

        return out;

    }

    public Boolean exportObj(Escena escena, String file) {

        /**
         * **********************************************************
         * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * binari d'objectes, * per poder ser importat posteriorment. *
         * **********************************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        FileOutputStream fos = null;

        try {
            File fil = new File(file);
            fos = new FileOutputStream(fil);
            ObjectOutputStream oos = new ObjectOutputStream(fos);

            for (Figura figu : escena.LlistaFigures) {
                oos.writeObject(figu);
            }

            out = true;

        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                fos.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return out;

    }

    public Boolean exportSVG(Escena escena, String file) {
        /**
         * **********************************************************
         * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * SVG (format XML). * El fitxer s'haurà de poder obrir amb Inkscape. *
         * **********************************************************
         */
        /*
            <?xmlversion="1.0"encoding="UTF-8"standalone="no"?> 2 <svgheight="500"width="500">
            <rect fill="#ccccee" height="480" width="480" x="10" y="10"/>
            <circle cx="250" cy="250" fill="#aaaaaa" r="100"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="450" y1="250" y2="250"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="50" x2="50" y1="50" y2="
            450"/>
            <line stroke="#aaaaaa" stroke-width="3" x1="450" x2="450" y1="40" y2= "450"/>
            </svg>
         */

        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        try {
            Document doc = DocumentBuilderFactory.newInstance().newDocumentBuilder().newDocument();
            Element arrel = doc.createElement("svg");

            arrel.setAttribute("height", String.valueOf(escena.getX()));
            arrel.setAttribute("width", String.valueOf(escena.getY()));

            doc.appendChild(arrel);

            for (Figura fig: escena.LlistaFigures){
                arrel.appendChild(fig.getAsXML(doc));
            }

            Transformer trans = TransformerFactory.newInstance().newTransformer();
            DOMSource source = new DOMSource(doc);
            StreamResult result = new StreamResult(new FileOutputStream(file));
            trans.transform(source, result);

            out = true;

        }catch (ParserConfigurationException | FileNotFoundException | TransformerException e) {
            e.printStackTrace();
        }

        return out;

    }

    public Boolean exportJSON(Escena escena, String filename) {

        /**
         * **********************************************
         * TO-DO: Mètode a implementar: * exporta l'escena donada a un fitxer
         * JSON. * **********************************************
         */
        // Comentar o elimina aquestes línies quan implementeu el mètode
        boolean out = false;

        try {
            FileWriter fw = new FileWriter(filename);

            JSONObject eJson = new JSONObject();
            eJson.put("width", escena.getX());
            eJson.put("height", escena.getY());
            JSONArray fi = new JSONArray();

            for (Figura figu: escena.LlistaFigures){
                fi.put(figu.getAsJSON());
            }
            eJson.put("figures", fi);

            JSONObject raiz = new JSONObject();
            raiz.put("escena", escena);

            fw.write(raiz.toString(4));
            fw.close();

            out = true;

        }catch (IOException e) {
            e.printStackTrace();
        }

        return out;

    }

}