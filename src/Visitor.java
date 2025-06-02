public interface Visitor {
    public void visit(Empty emptyTile);
    public void visit(Wall wall);
    public void visit(Player player);
    public void visit(Enemy enemy);
}
