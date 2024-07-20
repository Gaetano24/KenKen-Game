package View.Frames;

import Utils.MyButton;
import View.Panels.CommandsPanel;
import View.Panels.GrigliaPanel;
import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;

public class PaginaPartita extends JFrame {
    private static final String TITOLO = "KenKen Game";
    private final CommandsPanel commandsPanel;
    private GrigliaPanel grigliaPanel;
    private boolean partitaTerminata = true;

    public PaginaPartita(GUI gui) {
        super(TITOLO);
        this.commandsPanel = new CommandsPanel(this);
        this.grigliaPanel = new GrigliaPanel();
        setLayout(new BorderLayout());
        setResizable(false);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        addWindowListener(new WindowAdapter(){
            public void windowClosing(WindowEvent e) {
            int scelta = JOptionPane.showConfirmDialog(
                    PaginaPartita.this,
                    "Exit game?",
                    "Exit",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (scelta == JOptionPane.OK_OPTION) {
                gui.showHome();
            }
            }
        });
        add(commandsPanel, BorderLayout.EAST);
        add(grigliaPanel);
        this.pack();
        setLocationRelativeTo(null);
    }

    public void nuovaPartita() {
        partitaTerminata = false;
        if(grigliaPanel != null) {
            remove(grigliaPanel);
        }
        grigliaPanel = new GrigliaPanel();
        configuraPannelloGriglia();
        add(grigliaPanel);
        revalidate();
        aggiornaComandi();
    }

    public void configuraPannelloGriglia() {
        grigliaPanel.addMouseListener(new MouseAdapter() {
            public void mousePressed(MouseEvent e) {
                if (!partitaTerminata)
                    grigliaPanel.assegnaValore(e);
            }
        });
    }

    public void terminaPartita() {
        partitaTerminata = true;
        aggiornaComandi();
    }

    private void aggiornaComandi() {
        for (Component c : commandsPanel.getComponents()) {
            if (c instanceof MyButton b) {
                if (!(b.getText().equals("NEW GAME")))
                    b.setEnabled(!partitaTerminata);
            }
        }
    }

    public void showMessage(String titolo, String messaggio){
        JOptionPane.showMessageDialog(
                this,
                messaggio,
                titolo,
                JOptionPane.INFORMATION_MESSAGE,
                null
        );
    }

}//PaginaPartita
