public abstract class Enemy extends Unit{
    protected int experienceValue;

    public Enemy(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue){
        super(tile, position, name, health, attack, defense);
        this.experienceValue=experienceValue;
    }

    public void interact(Tile tile){
        tile.accept(this);
    }

    public void visit(Empty emptyTile){
        Position.swapPosition(this, emptyTile);
    }
    public void visit(Wall wall){}
    public void visit(Player player){
        battle(player);
        if(player.health.getAmount()==0){
            player.setCharacter('X');
        }
    }
    public void visit(Enemy enemy){}

    public void accept(Visitor v){
        v.visit(this);
    }
}
