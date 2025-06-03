public class Empty extends Tile{

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Empty(Position position){
        super('.', position);
    }
}
