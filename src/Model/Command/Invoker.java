package Model.Command;

import java.util.ArrayList;
import java.util.List;

public class Invoker implements InvokerIF {
    private final List<CommandIF> history = new ArrayList<>();
    private final List<CommandIF> undoHistory = new ArrayList<>();
    private final int maxHistoryLength;

    public Invoker() {
        this(100);
    }

    public Invoker(int maxHistoryLength) {
        if (maxHistoryLength < 0)
            throw new IllegalArgumentException("La lunghezza massima della storia dei comandi deve essere non negativa");
        this.maxHistoryLength = maxHistoryLength;
    }

    @Override
    public void handle(CommandIF cmd) {
        boolean eseguito = cmd.execute();
        if (eseguito) {
            addToHistory(cmd);
        } else {
            history.clear();
        }
        undoHistory.clear();
    }

    private void addToHistory(CommandIF cmd) {
        history.addFirst(cmd);
        if(history.size() > maxHistoryLength) {
            history.removeLast();
        }
    }

    @Override
    public void redo() {
        if(!undoHistory.isEmpty()) {
            CommandIF cmd = undoHistory.removeFirst();
            cmd.execute();
            history.addFirst(cmd);
        }
    }

    @Override
    public void undo() {
        if(!history.isEmpty()) {
            CommandIF cmd = history.removeFirst();
            cmd.undo();
            undoHistory.addFirst(cmd);
        }
    }

    @Override
    public void reset() {
        history.clear();
        undoHistory.clear();
    }

}//Invoker
