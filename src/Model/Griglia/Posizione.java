package Model.Griglia;

public record Posizione(int x, int y) {

    public String toString() {
        return "<%d,%d>".formatted(this.x, this.y);
    }

}//Posizione
