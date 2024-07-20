package Generatore;

import Model.Griglia.Element;
import Model.Griglia.GrigliaImplementorIF;
import java.util.ArrayList;

public abstract class AbstractGeneratore implements GeneratoreIF {
    protected final GrigliaImplementorIF griglia;
    protected final int dimensione;
    protected final ArrayList<Element> blocchi = new ArrayList<>();

    protected AbstractGeneratore(GrigliaImplementorIF g) {
        this.griglia = g;
        this.dimensione = g.getDimensione();
    }

    //Template Method
    @Override
    public void generaGriglia() {
        effettuaScambi();
        inserisciValori();
        costruisciStruttura();
        creaElementiStruttura();
        griglia.setBlocchi(blocchi);
        griglia.salvaSoluzione();
        griglia.reset();
    }

    protected abstract void effettuaScambi();
    protected abstract void inserisciValori();
    protected abstract void costruisciStruttura();
    protected abstract void creaElementiStruttura();

}//AbstractGeneratore
