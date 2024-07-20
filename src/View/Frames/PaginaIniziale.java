package View.Frames;

import Utils.MyButton;

import javax.swing.*;
import java.awt.*;
import java.net.URL;

public class PaginaIniziale extends JFrame {
    private static final String TITOLO = "KenKen Game";
    private static final String[] DIMENSIONI_GRIGLIA = {"3x3","4x4","5x5","6x6"};
    private final GUI gui;

    public PaginaIniziale(GUI gui) {
        super(TITOLO);
        this.gui = gui;
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setResizable(false);
        setSize(550,550);
        this.getContentPane().setBackground(Color.cyan);
        setLocationRelativeTo(null);
        
        JLabel label = new JLabel(TITOLO);
        label.setForeground(Color.black);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setFont(new Font("Times New Roman",Font.BOLD,45));
        URL urlImage = getClass().getResource("../Immagini/Logo.png");
        assert urlImage != null;
        ImageIcon image = new ImageIcon(urlImage);
        label.setIcon(image);
        label.setIconTextGap(10);

        JButton nuovaPartita = iniziaPartita();

        JButton regole = new MyButton("RULES");
        regole.addActionListener(_ -> gui.showRules());

        //Panel
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.cyan);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(7, 10, 7, 10);
        panel.add(label, gbc);
        gbc.gridy = 1;
        panel.add(nuovaPartita, gbc);
        gbc.gridy = 2;
        panel.add(regole, gbc);
        this.add(panel, BorderLayout.CENTER);
    }

    private JButton iniziaPartita() {
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
                gui.avviaPartita(dimensione);
            }
        });
        return nuovaPartita;
    }

}//PaginaIniziale
