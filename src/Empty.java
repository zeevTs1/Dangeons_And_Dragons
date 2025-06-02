public class Empty extends Tile{

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Empty(char character, Position position){
        super(character, position);
    }
}
