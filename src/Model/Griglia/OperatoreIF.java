package Model.Griglia;

import java.util.List;

public interface OperatoreIF {

    Integer calcolaRisultato(List<Integer> valori);

    boolean verificaRisultato(List<Integer> valori, int risultato);

    boolean assegnabile(List<Integer> valori);

}//OperatoreIF
