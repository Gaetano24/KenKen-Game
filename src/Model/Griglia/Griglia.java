package Model.Griglia;

import Controller.Controller;
import Controller.ControllerIF;
import Generatore.Generatore;
import Generatore.GeneratoreIF;
import Model.Command.Command;
import Model.Command.CommandIF;
import Utils.Messaggio;
import java.util.ArrayList;
import java.util.List;
import java.util.Random;

public class Griglia extends AbstractCompositeElement implements GrigliaIF {
    private final GrigliaImplementorIF impl;
    private boolean controlloInterattivo = false;
    private final ControllerIF controller = Controller.INSTANCE;

    public Griglia(int dimensione) {
        this.impl = new GrigliaImplementor(dimensione);
        genera(impl);
    }

    private void genera(GrigliaImplementorIF impl) {
        GeneratoreIF gen = new Generatore(impl);
        gen.generaGriglia();
        for(Element g: impl.getBlocchi()) {
            Blocco b = (Blocco) g;
            addChild(b);
        }
    }

    @Override
    public int getDimensione() {
        return impl.getDimensione();
    }

    @Override
    public boolean esisteRipetizione(Posizione p, Integer val) {
        return impl.esisteRipetizione(p, val);
    }

    @Override
    public boolean effettuaInserimento(Blocco b, Cella c, Integer val) {
        if(val != null && controlloInterattivo) {
            if(esisteRipetizione(c.getPosizione(), val)) {
                controller.mostraMessaggio(Messaggio.RIPETIZIONI_INSERIMENTO);
                return false;
            }
            if ((b.ultimoInserimento() && c.getValore() == null) || b.completo()) {
                if (!risultatoBloccoValido(b, c, val)) {
                    controller.mostraMessaggio(Messaggio.RISULTATO_ERRATO);
                    return false;
                }
            }
        }
        c.setValore(val);
        int valore = (val == null) ? 0 : val;
        impl.effettuaInserimento(c.getPosizione(), valore);
        return true;
    }

    private boolean risultatoBloccoValido(Blocco b, Cella c, Integer valore) {
        List<Integer> valori = b.getValoriPresenti();
        Integer val = c.getValore();
        if (val != null)
            valori.remove(val);
        valori.add(valore);
        return b.verificaRisultato(valori);
    }

    @Override
    public void toggleControllo() {
        controlloInterattivo = !controlloInterattivo;
    }

    @Override
    public void reset() {
        for (Element ge : this) {
            Blocco b = (Blocco) ge;
            for (Element ge1 : b) {
                Cella c = (Cella) ge1;
                c.setValore(null);
            }
        }
        impl.reset();
    }

    @Override
    public CommandIF suggerisciMossa() {
        ArrayList<Blocco> blocchiIncompleti = trovaBlocchiIncompleti();
        Random r = new Random();
        while(!blocchiIncompleti.isEmpty()) {
            // Si estrae un indice casuale tra 0 e size-1
            int index = r.nextInt(0, blocchiIncompleti.size());
            Blocco b = blocchiIncompleti.get(index);

            for(Element ge1 : b) {
                Cella c = (Cella) ge1;
                if(c.getValore() == null) {
                    // Se la cella è vuota, si cerca un numero casuale valido da inserire
                    ArrayList<Integer> valoriAmmissibili = trovaValoriAmmissibili(b,c);

                    if(!valoriAmmissibili.isEmpty()) {
                        // Se ci sono numeri disponibili, se ne sceglie uno casualmente
                        int valoreCasuale = valoriAmmissibili.get(r.nextInt(0, valoriAmmissibili.size()));

                        // Creiamo e restituiamo l'oggetto CommandIF per rappresentare la mossa suggerita
                        return new Command(this,b,c,valoreCasuale);
                    }
                }
            }
            blocchiIncompleti.remove(b);
        }
        return null;
    }

    private ArrayList<Integer> trovaValoriAmmissibili(Blocco b, Cella c) {
        Posizione pos = c.getPosizione();
        ArrayList<Integer> valoriAmmissibili = new ArrayList<>(); //Lista di tutti i valori inseribili

        for(int val=1; val<=getDimensione(); val++) {
            boolean ripetizione = esisteRipetizione(pos, val);

            boolean risultatoAmmissibile;
            //Se il blocco in questione ha solo una cella vuota, bisogna effettuare un'ulteriore verifica per controllare
            //se l'inserimento di uno specifico valore permette di ottenere, o meno, il risultato previsto dal blocco
            if(!b.ultimoInserimento()) {
                risultatoAmmissibile = true;
            }
            else {
                ArrayList<Integer> valori = b.getValoriPresenti();
                valori.add(val);
                risultatoAmmissibile = b.verificaRisultato(valori);
            }

            //Se entrambe le condizione sono verificate aggiungo il valore ammissibile individuato
            if(!ripetizione && risultatoAmmissibile) {
                valoriAmmissibili.add(val);
            }
        }
        return valoriAmmissibili;
    }

    private ArrayList<Blocco> trovaBlocchiIncompleti() {
        ArrayList<Blocco> blocchiIncompleti = new ArrayList<>();
        for(Element e: this) {
            Blocco b = (Blocco) e;
            if (!b.completo())
                blocchiIncompleti.add(b);
        }
        return blocchiIncompleti;
    }

    @Override
    public Messaggio verificaSoluzione() {
        for (Element ge : this) {
            Blocco b = (Blocco) ge;
            for (Element ge1 : b) {
                Cella c = (Cella) ge1;
                if (c.getValore() == null) {
                    return Messaggio.GRIGLIA_INCOMPLETA;
                } else {
                    if (esisteRipetizione(c.getPosizione(), c.getValore()))
                        return Messaggio.RIPETIZIONI;
                }
            }
            ArrayList<Integer> valori = b.getValoriPresenti();
            boolean soluzioneBloccoValida = b.verificaRisultato(valori);
            if (!soluzioneBloccoValida)
                return Messaggio.SOLUZIONE_BLOCCO_NON_VALIDA;
        }
        return Messaggio.SOLUZIONE_VALIDA;
    }

    @Override
    public void mostraSoluzione() {
        impl.mostraSoluzione(this);
    }

    @Override
    public String toString() {
        return impl.toString();
    }

}//Griglia
