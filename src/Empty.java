public class Empty extends Tile{

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public static void swapPosition(Tile tile1, Tile tile2){
        Position temp = tile1.getPosition();
        tile1.setPosition(tile2.getPosition());
        tile2.setPosition(temp);
    }

    public Empty(char character, Position position){
        super(character, position);
    }
}
