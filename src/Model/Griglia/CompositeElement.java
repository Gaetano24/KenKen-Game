package Model.Griglia;

public interface CompositeElement extends Element, Iterable<Element> {
    Element getChild(int index);

    void addChild(Element child);

    int getChildCount();

    @Override
    CompositeElement asComposite();

}//CompositeElement
