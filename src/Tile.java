public abstract class Tile implements Visited {
    protected char tile;
    protected Position position;

    protected Tile(char tile, Position position) {
        this.tile = tile;
        this.position = position;
    }

    public char getCharacter() {
        return tile;
    }

    public void setCharacter(char tile) {
        this.tile = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public String toString() {
        return "Tile{" +
                "tile=" + tile +
                ", position=" + position +
                '}';
    }
}
