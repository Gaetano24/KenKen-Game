package View.Frames;

import Utils.MyButton;

import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.LineBorder;
import java.awt.*;

public class PaginaRegole extends JFrame {
    private static final String TITOLO = "KenKen Game";

    public PaginaRegole(GUI gui) {
        super(TITOLO);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        setSize(600,600);
        setBackground(Color.cyan);
        setLocationRelativeTo(null);

        JButton menuPrincipale = new MyButton("HOME");
        menuPrincipale.addActionListener(_ -> gui.showHome());

        JLabel label = new JLabel("Rules For Playing KenKen");
        label.setForeground(Color.black);
        label.setHorizontalTextPosition(JLabel.CENTER);
        label.setVerticalTextPosition(JLabel.TOP);
        label.setFont(new Font("Times New Roman", Font.BOLD, 40));

        JLabel testo = getLabel();
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBackground(Color.cyan);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 10, 10);
        panel.add(label, gbc);
        gbc.gridy = 1;
        panel.add(testo, gbc);
        gbc.gridy = 2;
        panel.add(menuPrincipale, gbc);
        this.add(panel, BorderLayout.CENTER);
    }

    private static JLabel getLabel() {
        JLabel testo = new JLabel(
                "<html><div style='text-align: justify;'>"
                        + "The numbers you use in a KenKen puzzle depend on the size of the grid you choose. "
                        + "A 3 x 3 grid (3 squares across, 3 squares down) means you use the numbers 1, 2, and 3. "
                        + "In a 4 x 4 grid, use numbers 1 to 4. A 5 x 5 grid requires you use the numbers 1 to 5, and so on.<br><br>"
                        + "The numbers in each heavily outlined set of squares, called cages, must combine (in any order) <br>"
                        + "to produce the target number in the top corner using the mathematics operation indicated (+, -, ×, ÷).<br><br>"
                        + "Here's how you play:<br>"
                        + "1. Use each number only once per row, once per column.<br>"
                        + "2. Cages with just one square should be filled in with the target number in the top corner.<br>"
                        + "3. A number can be repeated within a cage as long as it is not in the same row or column.<br>"
                        + "</div></html>"
        );
        testo.setForeground(Color.black);
        testo.setFont(new Font("Arial", Font.BOLD, 16));
        testo.setHorizontalTextPosition(JLabel.CENTER);
        testo.setVerticalTextPosition(JLabel.CENTER);
        testo.setBackground(Color.white);
        testo.setOpaque(true);
        testo.setBorder(BorderFactory.createCompoundBorder(
                new LineBorder(Color.black),
                new EmptyBorder(10, 20, 10, 20)
        ));
        Dimension d = new Dimension(450, 400);
        testo.setPreferredSize(d);
        testo.setMinimumSize(d);
        return testo;
    }

}//PaginaRegole
