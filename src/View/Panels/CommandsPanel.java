package View.Panels;

import Controller.Controller;
import Controller.ControllerIF;
import Utils.MyButton;
import View.Frames.PaginaPartita;

import javax.swing.*;
import java.awt.*;

public class CommandsPanel extends JPanel {
    private static final Color RED_COLOR = new Color(255, 102, 102);
    private static final String[] DIMENSIONI_GRIGLIA = {"3x3", "4x4", "5x5", "6x6"};
    private final PaginaPartita paginaPartita;
    private final ControllerIF controller = Controller.INSTANCE;

    public CommandsPanel(PaginaPartita p) {
        this.paginaPartita = p;

        setLayout(new BoxLayout(this, BoxLayout.Y_AXIS));
        setBackground(Color.cyan);
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));

        JButton toggleControllo = getToggleButton();
        JButton nuovaPartita = getNewGameButton();

        JButton annulla = new MyButton("UNDO");
        annulla.addActionListener(_ -> controller.annullaMossa());

        JButton ripeti = new MyButton("REDO");
        ripeti.addActionListener(_ -> controller.ripetiMossa());

        JButton ricomincia = new MyButton("RESTART");
        ricomincia.addActionListener(_ -> {
            int scelta = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to restart?",
                    "Restart",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (scelta == JOptionPane.OK_OPTION) {
                controller.ricominciaPartita();
            }
        });

        JButton verificaSoluzione = new MyButton("CHECK");
        verificaSoluzione.addActionListener(_ -> controller.verificaSoluzione());

        JButton rivelaMossa = new MyButton("REVEAL");
        rivelaMossa.addActionListener(_ -> controller.rivelaMossa());

        JButton mostraSoluzione = new MyButton("SOLUTION");
        mostraSoluzione.addActionListener(_ -> {
            int scelta = JOptionPane.showConfirmDialog(
                    this,
                    "Are you sure you want to view the solution?",
                    "Solution",
                    JOptionPane.OK_CANCEL_OPTION
            );
            if (scelta == JOptionPane.OK_OPTION) {
                controller.mostraSoluzione();
                paginaPartita.terminaPartita();
            }
        });

        JLabel developedByLabel = new JLabel("<html><div style='text-align: center;'>Developed by:<br>Gaetano Marchianò</div></html>");
        developedByLabel.setFont(developedByLabel.getFont().deriveFont(14f));
        developedByLabel.setHorizontalAlignment(SwingConstants.CENTER);
        developedByLabel.setAlignmentX(Component.CENTER_ALIGNMENT);
        developedByLabel.setAlignmentY(Component.CENTER_ALIGNMENT);

        add(Box.createVerticalGlue());
        add(toggleControllo);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(annulla);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(ripeti);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(ricomincia);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(verificaSoluzione);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(rivelaMossa);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(mostraSoluzione);
        add(Box.createRigidArea(new Dimension(0, 15)));
        add(nuovaPartita);
        add(Box.createVerticalGlue());
        add(developedByLabel);
    }

    private JButton getNewGameButton() {
        JButton nuovaPartita = new MyButton("NEW GAME");
        nuovaPartita.addActionListener(_ -> {
            Object obj = JOptionPane.showInputDialog(
                    this,
                    "Puzzle Size",
                    "Options",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    DIMENSIONI_GRIGLIA,
                    DIMENSIONI_GRIGLIA[0]
            );
            String scelta = (String) obj;
            if (scelta != null) {
                int dimensione = switch (scelta) {
                    case "3x3" -> 3;
                    case "4x4" -> 4;
                    case "5x5" -> 5;
                    default    -> 6;
                };
                controller.nuovaPartita(dimensione);
                paginaPartita.nuovaPartita();
            }
        });
        return nuovaPartita;
    }

    private JButton getToggleButton() {
        JButton toggleButton = new MyButton("TOGGLE CONTROL");
        toggleButton.setFont(new Font("Arial", Font.BOLD, 12));
        toggleButton.setBackground(new Color(255, 102, 102));
        toggleButton.addActionListener(_ -> {
            controller.toggleControllo();
            if (toggleButton.getBackground().equals(RED_COLOR)) {
                toggleButton.setBackground(Color.green);

            } else {
                toggleButton.setBackground(RED_COLOR);
            }
        });
        return toggleButton;
    }

}//CommandsPanel
