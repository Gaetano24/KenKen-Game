package View.Panels;

import Controller.Controller;
import Controller.ControllerIF;
import Model.Griglia.*;
import View.ElementiGrafici.ViewFlyweightFactory;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseEvent;
import java.awt.geom.Point2D;

public class GrigliaPanel extends JPanel implements Observer {
    private static final ViewFlyweightFactory FACTORY = new ViewFlyweightFactory();
    private static int MISURA_CELLA;
    private final ControllerIF controller = Controller.INSTANCE;
    private final GrigliaIF griglia;
    private Object[] cifre;

    public GrigliaPanel() {
        this.griglia = controller.getGriglia();
        setPreferredSize(new Dimension(450, 450));
        setBackground(Color.white);
        setBorder(BorderFactory.createLineBorder(Color.BLACK));
        MISURA_CELLA = getPreferredSize().width / griglia.getDimensione();
    }

    public static int getMisuraCella() {
        return MISURA_CELLA;
    }

    @Override
    protected void paintComponent(Graphics g) {
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        for (Element e : griglia) {
            e.addObserver(this);
            if(e.asComposite() != null) {
                CompositeElement ce = (CompositeElement) e;
                for (Element e1 : ce) {
                    e1.addObserver(this);
                    FACTORY.getView(e1).drawGraphicObject(e1, g2);
                }
                FACTORY.getView(e).drawGraphicObject(e, g2);
            }
        }
    }

    @Override
    public void graphicChanged() {
        this.repaint();
    }

    public void assegnaValore(MouseEvent e){
        Cella cella = (Cella) this.getElementoSelezionato(e.getPoint(), Cella.class);
        Blocco blocco = (Blocco) this.getElementoSelezionato(e.getPoint(), Blocco.class);
        if(blocco != null && cella != null) {
            cella.setColore(Color.LIGHT_GRAY);
            setMenuValori();
            Object valore = JOptionPane.showInputDialog(
                    this,
                    "Seleziona Numero",
                    "Menu Selezione",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    cifre,
                    cifre[0]
            );
            if (valore != null) {
                Integer value;
                if (!(valore.equals("Rimuovi"))) {
                    value = (Integer) valore;
                    controller.effettuaInserimento(blocco, cella, value);
                } else {
                    if (cella.getValore() != null) {
                        controller.effettuaInserimento(blocco, cella, null);
                    }
                }
            }
            cella.setColore(Color.white);
        }
    }

    private Element getElementoSelezionato(Point2D p, Class<? extends Element> classe) {
        for(Element g: griglia) {
            if(classe.isInstance(g) && g.contains(p))
                return g;
            Blocco b = (Blocco) g;
            for(Element e : b) {
                if(classe.isInstance(e) && e.contains(p))
                    return e;
            }
        }
        return null;
    }

    private void setMenuValori() {
        int maxCifra = griglia.getDimensione();
        cifre = new Object[maxCifra + 1];
        cifre[0] = "Rimuovi";
        for(int i = 1; i <= maxCifra; i++){
            cifre[i] = i;
        }
    }


}//PuzzlePanel
