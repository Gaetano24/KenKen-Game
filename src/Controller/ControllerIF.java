package Controller;

import Model.Griglia.Blocco;
import Model.Griglia.Cella;
import Model.Griglia.GrigliaIF;
import Utils.Messaggio;
import View.Frames.GUI;

public interface ControllerIF {

    void setGUI(GUI g);

    GrigliaIF getGriglia();

    void toggleControllo();

    void effettuaInserimento(Blocco b, Cella c, Integer valore);

    void annullaMossa();

    void ripetiMossa();

    void ricominciaPartita();

    void verificaSoluzione();

    void mostraMessaggio(Messaggio m);

    void mostraSoluzione();

    void nuovaPartita(int dimensione);

    void rivelaMossa();

}//ControllerIF
