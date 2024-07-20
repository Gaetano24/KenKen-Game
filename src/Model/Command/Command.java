package Model.Command;

import Model.Griglia.Blocco;
import Model.Griglia.Cella;
import Model.Griglia.GrigliaIF;

public class Command implements CommandIF {
    private final GrigliaIF puzzle;
    private final Blocco blocco;
    private final Cella cella;
    private final Integer oldValue;
    private final Integer newValue;

    public Command(GrigliaIF puzzle, Blocco blocco, Cella cella, Integer newValue) {
        this.puzzle = puzzle;
        this.blocco = blocco;
        this.cella = cella;
        this.oldValue = cella.getValore();
        this.newValue = newValue;
    }

    @Override
    public boolean execute() {
        return puzzle.effettuaInserimento(blocco,cella,newValue);
    }

    @Override
    public void undo() {
        puzzle.effettuaInserimento(blocco, cella, oldValue);
    }

}//Command
