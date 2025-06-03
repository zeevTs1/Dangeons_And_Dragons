public class Wall extends Tile{

    @Override
    public void accept(Visitor v) {
        v.visit(this);
    }

    public Wall(Position position){
        super('#', position);
    }

}
