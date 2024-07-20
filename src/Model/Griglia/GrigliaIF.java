package Model.Griglia;

import Model.Command.CommandIF;
import Utils.Messaggio;

public interface GrigliaIF extends CompositeElement {

    int getDimensione();

    boolean esisteRipetizione(Posizione p, Integer val);

    boolean effettuaInserimento(Blocco b, Cella c, Integer val);

    void toggleControllo();

    void reset();

    CommandIF suggerisciMossa();

    Messaggio verificaSoluzione();

    void mostraSoluzione();

}//GrigliaIF
