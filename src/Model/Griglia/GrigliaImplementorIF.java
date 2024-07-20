package Model.Griglia;

import java.util.ArrayList;

public interface GrigliaImplementorIF {

    int getDimensione();

    void reset();

    boolean esisteBlocco(Posizione p);

    void assegnaBlocco(Posizione p, int idBlocco);

    boolean esisteRipetizione(Posizione p, Integer valore);

    void effettuaInserimento(Posizione p, Integer valore);

    ArrayList<Element> getBlocchi();

    void setBlocchi(ArrayList<Element> blocchi);

    void salvaSoluzione();

    void mostraSoluzione(GrigliaIF g);

    ArrayList<Integer> getValoriBlocco(int idBlocco);

}//GrigliaImplementorIF
