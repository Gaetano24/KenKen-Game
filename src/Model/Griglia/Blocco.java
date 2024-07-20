package Model.Griglia;

import java.awt.geom.Area;
import java.awt.geom.Point2D;
import java.util.ArrayList;
import java.util.List;

public class Blocco extends AbstractCompositeElement {
    private final int id;
    private Area area;
    private int dimensione;
    private int risultato;
    private OperatoreIF operatore;

    public Blocco(int id) {
        this.id = id;
    }

    public int getId() {
        return id;
    }

    public Area getArea() {
        return area;
    }

    public void setArea(Area area) {
        this.area = area;
    }

    public int getDimensione() {
        return dimensione;
    }

    public void setDimensione(int dimensione) {
        this.dimensione = dimensione;
    }

    public OperatoreIF getOperatore() {
        return operatore;
    }

    public void setOperatore(OperatoreIF operatore) {
        this.operatore = operatore;
    }

    public int getRisultato() {
        return risultato;
    }

    public void setRisultato(int risultato) {
        this.risultato = risultato;
    }

    public boolean ultimoInserimento() {
        return numCelleRiempite() == (dimensione-1);
    }

    public boolean completo() {
        return numCelleRiempite() == dimensione;
    }

    private int numCelleRiempite() {
        int inserimenti = 0;
        for(Element e: this) {
            Cella c = (Cella) e;
            if(c.getValore() != null)
                inserimenti++;
        }
        return inserimenti;
    }

    public ArrayList<Integer> getValoriPresenti() {
        ArrayList<Integer> valori = new ArrayList<>();
        for(Element e: this) {
            Cella c = (Cella) e;
            if(c.getValore() != null)
                valori.add(c.getValore());
        }
        return valori;
    }

    public boolean verificaRisultato(List<Integer> valori) {
        return operatore.verificaRisultato(valori, risultato);
    }

    @Override
    public boolean contains(Point2D p) {
        if (area == null) return false;
        return area.contains(p);
    }

    @Override
    public String toString() {
        return "Blocco:%d Dimensione:%d Operatore:%s Risultato:%d\n".formatted(id, dimensione, operatore, risultato);
    }

}//Blocco
