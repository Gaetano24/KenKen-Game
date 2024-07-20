package View.Frames;

import Controller.Controller;
import Controller.ControllerIF;
import Utils.Messaggio;

public class GUI {
    private final PaginaIniziale paginaIniziale;
    private final PaginaPartita paginaPartita;
    private final PaginaRegole paginaRegole;
    private final ControllerIF controller = Controller.INSTANCE;

    public GUI() {
        Controller.INSTANCE.setGUI(this);
        paginaIniziale = new PaginaIniziale(this);
        paginaPartita = new PaginaPartita(this);
        paginaRegole = new PaginaRegole(this);
    }

    public void showHome() {
        paginaPartita.setVisible(false);
        paginaRegole.setVisible(false);
        paginaIniziale.setVisible(true);
    }

    public void showGame() {
        paginaIniziale.setVisible(false);
        paginaRegole.setVisible(false);
        paginaPartita.setVisible(true);
    }

    public void showRules() {
        paginaIniziale.setVisible(false);
        paginaPartita.setVisible(false);
        paginaRegole.setVisible(true);
    }

    public void showMessage(Messaggio m) {
        paginaPartita.showMessage("Messaggio di gioco", m.toString());
    }

    public void avviaPartita(int dimensione) {
        controller.nuovaPartita(dimensione);
        paginaPartita.nuovaPartita();
        showGame();
    }

}//GUI
