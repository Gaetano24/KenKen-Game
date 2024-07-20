package Model.Griglia;

import java.util.ArrayList;
import java.util.Arrays;

public class GrigliaImplementor implements GrigliaImplementorIF {
    private final int dimensione;
    private final int[][] matriceInserimenti;
    private final int[][] matriceBlocchi;
    private ArrayList<Element> blocchi = new ArrayList<>();
    private final int[][] soluzione;
    
    public GrigliaImplementor(int dimensione) {
        this.dimensione = dimensione;
        this.matriceInserimenti = new int[dimensione][dimensione];
        this.matriceBlocchi = new int[dimensione][dimensione];
        soluzione = new int[dimensione][dimensione];
    }
    
    @Override
    public int getDimensione() {
        return dimensione;
    }
    
    @Override
    public void reset() {
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                matriceInserimenti[i][j] = 0;
            }
        }
    }

    @Override
    public boolean esisteBlocco(Posizione p) {
        return matriceBlocchi[p.x()][p.y()] != 0;
    }

    @Override
    public void assegnaBlocco(Posizione p, int idBlocco) {
        matriceBlocchi[p.x()][p.y()] = idBlocco;
    }

    @Override
    public boolean esisteRipetizione(Posizione p, Integer valore) {
        //Controllo sulla riga
        for(int j=0; j<dimensione; j++) {
            if(matriceInserimenti[p.x()][j] == valore && j != p.y())
                return true;
        }
        //Controllo sulla colonna
        for(int i=0; i<dimensione; i++) {
            if(matriceInserimenti[i][p.y()] == valore && i != p.x())
                return true;
        }
        return false;
    }

    @Override
    public void effettuaInserimento(Posizione p, Integer valore) {
        matriceInserimenti[p.x()][p.y()] = valore;
    }

    @Override
    public ArrayList<Element> getBlocchi() {
        return blocchi;
    }

    @Override
    public void setBlocchi(ArrayList<Element> blocchi) {
        this.blocchi = blocchi;
    }

    @Override
    public void salvaSoluzione() {
        for(int i=0; i<dimensione; i++)
            System.arraycopy(matriceInserimenti[i], 0, soluzione[i], 0, dimensione);
    }

    @Override
    public void mostraSoluzione(GrigliaIF g) {
        for(Element e: g) {
            Blocco b = (Blocco) e;
            for(Element e1: b) {
                Cella c = (Cella) e1;
                Posizione p = c.getPosizione();
                c.setValore(soluzione[p.x()][p.y()]);
                matriceInserimenti[p.x()][p.y()] = soluzione[p.x()][p.y()];
            }
        }
    }

    @Override
    public ArrayList<Integer> getValoriBlocco(int idBlocco) {
        ArrayList<Integer> valori = new ArrayList<>();
        for(int i=0; i<dimensione; i++) {
            for(int j=0; j<dimensione; j++) {
                if(matriceBlocchi[i][j] == idBlocco) {
                    valori.add(matriceInserimenti[i][j]);
                }
            }
        }
        return valori;
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        for(int i=0; i<dimensione; i++) {
            sb.append(Arrays.toString(matriceInserimenti[i])).append("\n");
        }
        sb.append("\n");
        for(int i=0; i<dimensione; i++) {
            sb.append(Arrays.toString(matriceBlocchi[i])).append("\n");
        }
        return sb.toString();
    }

}//GrigliaImplementor
