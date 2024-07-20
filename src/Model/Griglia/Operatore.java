package Model.Griglia;

import java.util.Comparator;
import java.util.List;

public enum Operatore implements OperatoreIF {

    ADDIZIONE {
        @Override
        public boolean verificaRisultato(List<Integer> valori, int risultato) {
            return calcolaRisultato(valori).equals(risultato);
        }

        @Override
        public Integer calcolaRisultato(List<Integer> valori) {
            return valori.stream().reduce(Integer::sum).orElse(0);
        }

        @Override
        public boolean assegnabile(List<Integer> valori) {
            return true;
        }

        @Override
        public String toString() {
            return "+";
        }

    },

    SOTTRAZIONE {
        @Override
        public boolean verificaRisultato(List<Integer> valori, int risultato) {
            return calcolaRisultato(valori).equals(risultato);
        }

        @Override
        public Integer calcolaRisultato(List<Integer> valori) {
            return valori.stream()
                    .sorted(Comparator.reverseOrder())
                    .reduce((a, b) -> a - b)
                    .orElse(0);
        }

        @Override
        public boolean assegnabile(List<Integer> valori) {
            if(valori.size() == 1)
                return true;
            return calcolaRisultato(valori) > 0;
        }

        @Override
        public String toString() {
            return "-";
        }

    },

    MOLTIPLICAZIONE {
        @Override
        public boolean verificaRisultato(List<Integer> valori, int risultato) {
            return calcolaRisultato(valori).equals(risultato);
        }

        @Override
        public Integer calcolaRisultato(List<Integer> valori) {
            return valori.stream()
                    .reduce((a, b) -> a * b)
                    .orElse(0);
        }

        @Override
        public boolean assegnabile(List<Integer> valori) {
            return valori.size() <= 2; //evito che venga assegnato a blocchi molto grandi
        }

        @Override
        public String toString() {
            return "x";
        }
    },

    DIVISIONE {
        @Override
        public boolean verificaRisultato(List<Integer> valori, int risultato) {
            return calcolaRisultato(valori).equals(risultato);
        }

        @Override
        public Integer calcolaRisultato(List<Integer> valori) {
            return valori.stream()
                    .sorted(Comparator.reverseOrder())
                    .reduce((a, b) -> a / b)
                    .orElse(0);
        }

        @Override
        public boolean assegnabile(List<Integer> valori) {
            if (valori.size() > 2) return false; //evito che venga assegnato a blocchi molto grandi
            if (valori.size() == 1) return true;
            List<Integer> valoriOrdinati = valori.stream()
                    .sorted(Comparator.reverseOrder())
                    .toList();
            return valoriOrdinati.get(0) % valoriOrdinati.get(1) == 0;

        }

        @Override
        public String toString() {
            return "÷";
        }

    }

}//Operatore