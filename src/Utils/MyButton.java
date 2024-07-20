package Utils;

import javax.swing.*;
import java.awt.*;

public class MyButton extends JButton {
    private static final Dimension SIZE = new Dimension(150,30);

    public MyButton(String text) {
        super(text);
        setFocusable(false);
        setCursor(new Cursor(Cursor.HAND_CURSOR));
        setFont(new Font("Arial", Font.BOLD, 14));
        setMinimumSize(SIZE);
        setPreferredSize(SIZE);
        setMaximumSize(SIZE);
        setAlignmentX(Component.CENTER_ALIGNMENT);
        setAlignmentY(Component.CENTER_ALIGNMENT);
    }

}

