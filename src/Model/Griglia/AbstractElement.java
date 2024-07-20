package Model.Griglia;

import View.Panels.Observer;

import java.util.ArrayList;
import java.util.List;

public abstract class AbstractElement implements Element {
    private final List<Observer> observers = new ArrayList<>();

    @Override
    public void addObserver(Observer o) {
        if(!observers.contains(o))
            observers.add(o);
    }

    protected void notifyObservers() {
        for (Observer o : observers)
            o.graphicChanged();
    }

}//AbstractElement
