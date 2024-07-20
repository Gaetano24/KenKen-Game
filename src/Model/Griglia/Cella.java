package Model.Griglia;

import java.awt.*;
import java.awt.geom.Point2D;

public class Cella extends AbstractElement {
    private final int id;
    private Integer valore;
    private Posizione posizione;
    private Rectangle rettangolo;
    private Color colore = Color.white;

    public Cella(int id) {
        this.id = id;
    }

    public Color getColore() {
        return colore;
    }

    public int getId() {
        return id;
    }

    public Posizione getPosizione() {
        return posizione;
    }

    @Override
    public Integer getValore() {
        return valore;
    }

    public void setColore(Color colore) {
        this.colore = colore;
        notifyObservers();
    }

    public void setPosizione(Posizione posizione) {
        this.posizione = posizione;
    }

    public void setRettangolo(Rectangle rettangolo) {
        this.rettangolo = rettangolo;
    }

    public void setValore(Integer valore) {
        this.valore = valore;
        notifyObservers();
    }

    @Override
    public boolean contains(Point2D p) {
        if (rettangolo == null) return false;
        return rettangolo.contains(p);
    }

    @Override
    public String toString() {
        return "Cella:%d Posizione:%s Valore:%d\n".formatted(this.getId(), this.posizione, this.valore);
    }

}//Cella
