package Utils;

public enum Messaggio {

    RIPETIZIONI_INSERIMENTO{
        @Override
        public String toString() {
            return "L'inserimento causa ripetizioni!";
        }
    },

    RISULTATO_ERRATO {
        @Override
        public String toString() {
            return "L'inserimento non consente di ottenere\nil risultato previsto!";
        }
    },

    SOLUZIONE_VALIDA{
        @Override
        public String toString() {
            return "Complimenti, la tua soluzione è valida!";
        }
    },

    SOLUZIONE_BLOCCO_NON_VALIDA{
        @Override
        public String toString() {
            return "La soluzione di uno o più blocchi non è valida!";
        }
    },

    RIPETIZIONI{
        @Override
        public String toString() {
            return "La griglia presenta delle ripetizioni!";
        }
    },

    GRIGLIA_INCOMPLETA {
        @Override
        public String toString() {
            return "La tua soluzione non è completa!";
        }
    },

    NO_SUGGERIMENTI_DISPONIBILI {
        @Override
        public String toString() {return "Nessun suggerimento disponibile!";}
    }

}//Messaggio
