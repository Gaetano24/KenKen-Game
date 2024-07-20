package View.ElementiGrafici;

import Model.Griglia.Blocco;
import Model.Griglia.Cella;
import Model.Griglia.Element;
import View.Panels.GrigliaPanel;
import java.awt.*;
import java.awt.geom.Area;
import java.awt.geom.Rectangle2D;

public class BloccoView implements ElementView {

    @Override
    public void drawGraphicObject(Element e, Graphics2D g) {
        // Disegno del blocco
        Blocco b = (Blocco) e;
        int dimensioneCella = GrigliaPanel.getMisuraCella();
        Point puntoIniziale = null;
        Area areaBlocco = new Area();

        // Disegna le celle all'interno del blocco
        for (Element ge : b) {
            Cella c = (Cella) ge;
            Rectangle2D rettangolo = new Rectangle2D.Double(
                    c.getPosizione().y() * dimensioneCella,
                    c.getPosizione().x() * dimensioneCella,
                    dimensioneCella,
                    dimensioneCella
            );
            if (puntoIniziale == null) {
                puntoIniziale = rettangolo.getBounds().getLocation();
            }
            areaBlocco.add(new Area(rettangolo));
        }
        b.setArea(areaBlocco);

        // Disegna il contorno del blocco
        g.setColor(Color.BLACK);
        g.setStroke(new BasicStroke(6));
        g.draw(areaBlocco);

        // Disegna il risultato e l'operatore all'interno del blocco
        Font font = new Font("Arial", Font.PLAIN, 15);
        g.setColor(Color.BLACK);
        g.setFont(font);
        String risultatoOperatore = "%d%s".formatted(b.getRisultato(),b.getOperatore());
        assert puntoIniziale != null;
        g.drawString(risultatoOperatore, puntoIniziale.x + 10, puntoIniziale.y + 20);
    }

}//BloccoView


