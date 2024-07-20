package Generatore;

public enum Base {
    TRE(3, new int[][]{{1, 2, 3}, {2, 3, 1}, {3, 1, 2}}),
    QUATTRO(4, new int[][]{{1, 2, 3, 4}, {2, 3, 4, 1}, {3, 4, 1, 2}, {4, 1, 2, 3}}),
    CINQUE(5, new int[][]{{1, 2, 3, 4, 5}, {2, 3, 4, 5, 1}, {3, 4, 5, 1, 2}, {4, 5, 1, 2, 3}, {5, 1, 2, 3, 4}}),
    SEI(6, new int[][]{{1, 2, 3, 4, 5, 6}, {2, 3, 4, 5, 6, 1}, {3, 4, 5, 6, 1, 2}, {4, 5, 6, 1, 2, 3}, {5, 6, 1, 2, 3, 4}, {6, 1, 2, 3, 4, 5}});

    private final int dimensione;
    private final int[][] base;

    Base(int dimensione, int[][] base) {
        this.dimensione = dimensione;
        this.base = base;
    }

    public int getDimensione() {
        return dimensione;
    }

    public int[][] getBase() {
        return base;
    }

    public static int[][] getBaseByDimensione(int dimensione) {
        for (Base d : values()) {
            if (d.getDimensione() == dimensione) {
                return d.getBase();
            }
        }
        return null;
    }

}//Base
