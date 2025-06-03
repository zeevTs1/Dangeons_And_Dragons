public abstract class Tile implements Visited, Comparable<Tile> {
    protected char tile;
    protected Position position;

    protected Tile(char tile, Position position) {
        this.tile = tile;
        this.position = position;
    }

    public char getTile() {
        return tile;
    }

    public void setTile(char tile) {
        this.tile = tile;
    }

    public Position getPosition() {
        return position;
    }

    public void setPosition(Position position) {
        this.position = position;
    }

    @Override
    public int compareTo(Tile tile) {
        return getPosition().compareTo(tile.getPosition());
    }

    @Override
    public String toString() {
        return "Tile{" +
                "tile=" + tile +
                ", position=" + position +
                '}';
    }
}
