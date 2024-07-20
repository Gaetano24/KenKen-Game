package Controller;

import Model.Command.Command;
import Model.Command.CommandIF;
import Model.Command.Invoker;
import Model.Command.InvokerIF;
import Model.Griglia.Blocco;
import Model.Griglia.Cella;
import Model.Griglia.Griglia;
import Model.Griglia.GrigliaIF;
import Utils.Messaggio;
import View.Frames.GUI;

public enum Controller implements ControllerIF {
    INSTANCE;
    //View:
    private GUI gui;
    //Model:
    private final InvokerIF invoker = new Invoker();
    private GrigliaIF griglia = generaGriglia(3);

    @Override
    public void setGUI(GUI gui) {
        this.gui = gui;
    }

    @Override
    public GrigliaIF getGriglia() {
        return griglia;
    }

    @Override
    public void toggleControllo() {
        griglia.toggleControllo();
    }

    @Override
    public void annullaMossa() {
        invoker.undo();
    }

    @Override
    public void ripetiMossa() {
        invoker.redo();
    }

    @Override
    public void ricominciaPartita() {
        griglia.reset();
        invoker.reset();
    }

    @Override
    public void verificaSoluzione() {
        Messaggio m = griglia.verificaSoluzione();
        mostraMessaggio(m);
    }

    @Override
    public void effettuaInserimento(Blocco b, Cella c, Integer valore) {
        CommandIF cmd = new Command(griglia, b, c, valore);
        invoker.handle(cmd);
    }

    @Override
    public void mostraMessaggio(Messaggio m) {
        gui.showMessage(m);
    }

    @Override
    public void mostraSoluzione() {
        griglia.mostraSoluzione();
    }

    @Override
    public void nuovaPartita(int dimensione) {
        this.griglia = generaGriglia(dimensione);
    }

    private GrigliaIF generaGriglia(int dimensione) {
        griglia = new Griglia(dimensione);
        return griglia;
    }

    @Override
    public void rivelaMossa() {
        CommandIF cmd = griglia.suggerisciMossa();
        if(cmd == null)
            mostraMessaggio(Messaggio.NO_SUGGERIMENTI_DISPONIBILI);
        else
            invoker.handle(cmd);
    }

}//Controller
