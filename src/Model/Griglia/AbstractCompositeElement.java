package Model.Griglia;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public abstract class AbstractCompositeElement extends AbstractElement implements CompositeElement {
    private final List<Element> elements = new ArrayList<>();

    @Override
    public Element getChild(int index) {
        return elements.get(index);
    }

    @Override
    public void addChild(Element child) {
        elements.add(child);
    }

    @Override
    public int getChildCount() {
        return elements.size();
    }

    @Override
    public Integer getValore() {
        return null;
    }

    @Override
    public CompositeElement asComposite() {
        return this;
    }

    @Override
    public Iterator<Element> iterator() {
        return new PuzzleIterator();
    }

    private class PuzzleIterator implements Iterator<Element> {
        Iterator<Element> iterator = elements.iterator();

        @Override
        public boolean hasNext() {
            return iterator.hasNext();
        }

        @Override
        public Element next() {
            return iterator.next();
        }
    }

}//AbstractCompositeElement
