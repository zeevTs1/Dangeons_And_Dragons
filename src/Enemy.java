public abstract class Enemy extends Unit{
    protected int experienceValue;

    public Enemy(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue){
        super(tile, position, name, health, attack, defense);
        this.experienceValue=experienceValue;
    }

    public void visit(Player player){

    }
    public void visit(Enemy enemy){

    }
}
