package Generatore;

import Model.Griglia.Blocco;
import Model.Griglia.Cella;
import Model.Griglia.GrigliaImplementorIF;
import Model.Griglia.Operatore;
import Model.Griglia.Posizione;

import java.util.*;

public class Generatore extends AbstractGeneratore{
    private static final int MIN_INDEX = 0;
    private static final int MAX_DIMENSIONE_BLOCCO = 4;
    private final Random random = new Random();
    private final Operatore[] operatori = Operatore.values();
    private final int[][] base;
    private final Map<Integer, ArrayList<Posizione>> mappa = new HashMap<>();

    public Generatore(GrigliaImplementorIF g) {
        super(g);
        this.base = Base.getBaseByDimensione(g.getDimensione());
    }

    @Override
    protected void effettuaScambi() {
        int numeroScambi = random.nextInt(1,dimensione+1);
        while(numeroScambi > 0) {
            boolean scambioRighe = random.nextBoolean();
            int idx1 = random.nextInt(MIN_INDEX, dimensione);
            int idx2 = random.nextInt(MIN_INDEX, dimensione);
            while (idx1 == idx2) {
                idx2 = random.nextInt(dimensione);
            }
            if (scambioRighe) {
                scambiaRighe(idx1, idx2);
            } else {
                scambiaColonne(idx1, idx2);
            }
            numeroScambi--;
        }
    }

    private void scambiaRighe(int idx1, int idx2) {
        int[] temp = base[idx1];
        base[idx1] = base[idx2];
        base[idx2] = temp;
    }

    private void scambiaColonne(int idx1, int idx2) {
        for (int i = 0; i < dimensione; i++) {
            int temp = base[i][idx1];
            base[i][idx1] = base[i][idx2];
            base[i][idx2] = temp;
        }
    }

    @Override
    protected void inserisciValori() {
        int n = dimensione;
        for(int i=0; i<n; i++) {
            for(int j=0; j<n; j++) {
                Posizione p = new Posizione(i,j);
                int valore = base[i][j];
                griglia.effettuaInserimento(p,valore);
            }
        }
    }

    @Override
    protected void costruisciStruttura() {
        int idBlocco = 1;
        for (int i = 0; i < dimensione; i++) {
            for (int j = 0; j < dimensione; j++) {
                Posizione p = new Posizione(i, j);
                if (!griglia.esisteBlocco(p)) {
                    ArrayList<Posizione> posizioni = new ArrayList<>();
                    mappa.put(idBlocco, posizioni);
                    costruisciPercorso(i, j, idBlocco);
                    idBlocco++;
                }
            }
        }
    }

    private void costruisciPercorso(int i, int j, int idBlocco) {
        int dimensioneAttuale = mappa.get(idBlocco).size(); // numero di celle appartenenti al blocco attualmente
        Posizione p = new Posizione(i, j);

        // Casi in cui bisogna fermare la costruzione del percorso:
        if (i < 0 || i >= dimensione || j < 0 || j >= dimensione) return; // Posizione non valida
        if (griglia.esisteBlocco(p)) return; // Posizione già assegnata a un altro blocco
        boolean stop = random.nextBoolean();
        if (dimensioneAttuale > 1 && stop) return; // Possibile terminazione casuale se dimensione > 1
        if (dimensioneAttuale == MAX_DIMENSIONE_BLOCCO) return; // Non creo blocchi di dimensioni > 4

        // Assegno l'ID del blocco in posizione 'p' all'interno della griglia
        griglia.assegnaBlocco(p, idBlocco);
        // Registro nella mappa l'assegnazione fatta
        mappa.get(idBlocco).add(p);

        // Chiamate ricorsive per le celle adiacenti
        costruisciPercorso(i + 1, j, idBlocco);
        costruisciPercorso(i - 1, j, idBlocco);
        costruisciPercorso(i, j + 1, idBlocco);
        costruisciPercorso(i, j - 1, idBlocco);
    }

    @Override
    protected void creaElementiStruttura() {
        for (Integer idBlocco : mappa.keySet()) {
            List<Posizione> posizioni = mappa.get(idBlocco);
            List<Integer> valoriBlocco = griglia.getValoriBlocco(idBlocco);
            int dimensioneBlocco = posizioni.size();

            Blocco b = new Blocco(idBlocco);
            b.setDimensione(dimensioneBlocco);
            Operatore op = trovaOperatore(valoriBlocco);
            b.setOperatore(op);
            int risultato = op.calcolaRisultato(valoriBlocco);
            b.setRisultato(risultato);

            int idCella = 0;
            for (Posizione p : posizioni) {
                Cella c = new Cella(idCella);
                c.setPosizione(p);
                c.setValore(null);
                b.addChild(c);
                idCella++;
            }
            blocchi.add(b);
        }
    }

    private Operatore trovaOperatore(List<Integer> valori) {
        List<Operatore> operatoriValidi =  Arrays.stream(operatori)
                                            .filter(o -> o.assegnabile(valori))
                                            .toList();
        int sceltaRandom = random.nextInt(0, operatoriValidi.size());
        return operatoriValidi.get(sceltaRandom);
    }

}//Generatore
