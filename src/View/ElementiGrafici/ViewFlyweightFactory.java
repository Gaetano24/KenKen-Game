package View.ElementiGrafici;

import Model.Griglia.Cella;
import Model.Griglia.Element;

import java.util.HashMap;
import java.util.Map;
import Model.Griglia.Blocco;

public class ViewFlyweightFactory {
    private final Map<Class<? extends Element>, ElementView> viewsMap = new HashMap<>();

    public ElementView getView(Element g) {
        ElementView vista = viewsMap.get(g.getClass());
        if(vista == null) {
            installView(g.getClass());
        }
        return viewsMap.get(g.getClass());
    }

    private void installView(Class<? extends Element> c) {
        if(c == Blocco.class) {
            viewsMap.put(c, new BloccoView());
        }
        else if (c == Cella.class) {
            viewsMap.put(c, new CellaView());
        }
    }

}//ViewFlyweightFactory

