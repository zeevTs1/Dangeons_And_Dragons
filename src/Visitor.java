public interface Visitor {
    public void visit(Empty emptyTile);
    public void visit(Wall emptyTile);
    public void visit(Player emptyTile);
    public void visit(Enemy emptyTile);

}
