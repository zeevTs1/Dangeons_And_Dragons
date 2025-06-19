import java.util.List;

public abstract class Enemy extends Unit{
    protected int experienceValue;

    public Enemy(char tile, Position position, String name, Resource health, int attack, int defense, int experienceValue){
        super(tile, position, name, health, attack, defense);
        this.experienceValue=experienceValue;
    }

    public abstract Position performAction(List<Enemy> enemy, Player player);

    public void visit(Empty emptyTile){
        swapPosition(emptyTile);
    }

    public void visit(Wall wall){}

    public void visit(Player player){
        battle(player);
        if(!player.alive()){
            player.deathCallBack.Call();
            messageCallBack.send(String.format("%s was killed by %s.", player.getName(), getName()));
        }
    }
    public void visit(Enemy enemy){}

    public void accept(Visitor v){
        v.visit(this);
    }

    public int getExperienceValue() {
        return experienceValue;
    }

    public void setExperienceValue(int experienceValue) {
        this.experienceValue = experienceValue;
    }
}
