package View.ElementiGrafici;

import Model.Griglia.Cella;
import Model.Griglia.Element;
import View.Panels.GrigliaPanel;
import java.awt.*;

public class CellaView implements ElementView {

    @Override
    public void drawGraphicObject(Element e, Graphics2D g) {
        Cella c = (Cella) e;
        int dimensioneCella = GrigliaPanel.getMisuraCella();

        // Calcolo del rettangolo che rappresenta la cella
        Rectangle rettangolo = new Rectangle(
                c.getPosizione().y() * dimensioneCella,
                c.getPosizione().x() * dimensioneCella,
                dimensioneCella,
                dimensioneCella
        );

        // Impostazione del rettangolo nella cella
        c.setRettangolo(rettangolo);

        // Disegno del rettangolo con il colore della cella
        g.setColor(c.getColore());
        g.fill(rettangolo);

        // Disegno del bordo del rettangolo
        g.setColor(Color.GRAY);
        g.setStroke(new BasicStroke(1));
        g.draw(rettangolo);

        // Disegno del valore nella cella, se presente
        Integer valore = c.getValore();
        if (valore != null) {
            String text = String.valueOf(valore);
            Font font = new Font("Arial", Font.PLAIN, 35);
            g.setFont(font);
            g.setColor(Color.BLACK);
            FontMetrics metrics = g.getFontMetrics();
            int textWidth = metrics.stringWidth(text);
            int textHeight = metrics.getHeight();
            int textX = (int) (rettangolo.getCenterX() - textWidth / 2.0);
            int textY = (int) (rettangolo.getCenterY() + textHeight / 4.0);
            g.drawString(text, textX, textY);
        }
    }

}//CellaView
