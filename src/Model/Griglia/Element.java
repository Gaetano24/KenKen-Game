package Model.Griglia;

import View.Panels.Observer;

import java.awt.geom.Point2D;


public interface Element {
    Integer getValore();

    default CompositeElement asComposite() {return null;}

    default boolean contains(Point2D p) {return false;}

    void addObserver(Observer o);

}//Element
