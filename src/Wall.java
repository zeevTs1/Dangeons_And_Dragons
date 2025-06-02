public class Wall extends Tile{

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Wall(char character, Position position){
        super(character, position);
    }

}
