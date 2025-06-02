public abstract class Player extends Unit {
    protected int experience;
    protected int level;

    public Player(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue){
        super(tile, position, name, health, attack, defense);
        this.experienceValue=experienceValue;
    }

    public abstract void castSpecialAbility();
    public abstract void levelUp();
    public void visit(Enemy enemy){

    }
    public void visit(Player player){

    }
    public void accept(Visitor v){

    }
}
