package com.ieseljust.ad.figures;

import javafx.scene.canvas.GraphicsContext;
import javafx.scene.paint.Color;
import org.json.JSONObject;
import org.w3c.dom.Document;
import org.w3c.dom.Element;

// Definim la classe Línia a partir de la classe figura
// Heretarem per tant, la posició i el color
class Linia extends Figura {

    // Té un nou atribut que serà el radi
    private Punt vector;

    // Constructors
    Linia() {
        // Sense paràmetres:
        super(); // Invoca al constructor del pare
        this.vector = new Punt(0, 0);
    }

    Linia(int x, int y, int x2, int y2, String color) {
        // Amb paràmetres:
        super(x, y, color); // Invoquem al constructor pare
        this.vector = new Punt(x2, y2); // Indiquem el vector de la línia
    }

    public void renderText() {
        // Escriu les propietats de la figura
        System.out.println("com.ieseljust.ad.figures.Linia de (" + this.posicio.getX() + "," + this.posicio.getY() + ") fins a (" + this.vector.getX() + ", " + this.vector.getY() + ") i color " + this.color);
    }

    ;

    public void render(GraphicsContext gc) {
        // Dibuixem la linia amb el seu color
        Color color = Color.web(this.color);
        gc.setLineWidth(3); // Grossor per defecte de la línia: 3
        gc.setStroke(color);
        gc.strokeLine(this.posicio.getX(), this.posicio.getY(), this.vector.getX(), this.vector.getY());

    }

    @Override
    public String getAsText() {

        String resu = "linia" + " " + this.posicio.getX() + " " +  this.posicio.getY() + " " + this.vector.getX() + " " + this.vector.getY() + " " + this.color;

        return resu;
    }

    @Override
    public JSONObject getAsJSON() {
        JSONObject jObect = new JSONObject();
        jObect.put("x", this.posicio.getX());
        jObect.put("y", this.posicio.getY());
        jObect.put("x2", this.vector.getX());
        jObect.put("y2", this.vector.getY());
        jObect.put("stroke-width", 3);
        jObect.put("stroke", this.color);

        JSONObject linia = new JSONObject();
        linia.put("linia", jObect);

        return linia;
    }

    @Override
    public Element getAsXML(Document doc) {
        Element eleme = doc.createElement("line");
        eleme.setAttribute("stroke", this.color);
        eleme.setAttribute("stroke-width","3");
        eleme.setAttribute("x1", String.valueOf(this.posicio.getX()));
        eleme.setAttribute("x2", String.valueOf(this.vector.getX()));
        eleme.setAttribute("y1", String.valueOf(this.posicio.getY()));
        eleme.setAttribute("y2", String.valueOf(this.vector.getY()));

        return eleme;
    }

}
